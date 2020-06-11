package com.little.g.springcloud.admin.service;

import com.alibaba.fastjson.JSONArray;
import com.little.g.springcloud.admin.AdminResultJson;
import com.little.g.springcloud.admin.api.AdminRoleService;
import com.little.g.springcloud.admin.api.AdminUserService;
import com.little.g.springcloud.admin.api.ResourcesService;
import com.little.g.springcloud.admin.api.VerifyCodeService;
import com.little.g.springcloud.admin.dto.AdminRoleDTO;
import com.little.g.springcloud.admin.dto.AdminUserDTO;
import com.little.g.springcloud.admin.enums.AdminTypeEnum;
import com.little.g.springcloud.admin.enums.CodeTypeEnum;
import com.little.g.springcloud.admin.mapper.AdminUserMapper;
import com.little.g.springcloud.admin.model.AdminUser;
import com.little.g.springcloud.admin.model.AdminUserExample;
import com.little.g.springcloud.admin.params.AdminUserParams;
import com.little.g.springcloud.admin.params.AdminUserSearchParam;
import com.little.g.springcloud.admin.utils.AliyunSmsUtil;
import com.little.g.springcloud.common.dto.ListResultDTO;
import com.little.g.springcloud.common.dto.Page;
import com.little.g.springcloud.common.encrypt.MD5Utils;
import com.little.g.springcloud.common.enums.StatusEnum;
import com.little.g.springcloud.common.exception.ServiceDataException;
import com.little.g.springcloud.common.params.TimeQueryParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by lengligang on 2019/3/9.
 */
@Service(protocol = "dubbo")
public class AdminUserServiceImpl implements AdminUserService {

    @Resource
    private AdminUserMapper adminUserMapper;

    @Resource
    private VerifyCodeService verifyCodeService;

    @Resource
    private ResourcesService resourcesService;

    @Resource
    private AdminRoleService adminRoleService;

    @Value("${env.online}")
    private boolean online;

    @Override
    public boolean add(AdminUserParams entity) {
        AdminUser adminUser = new AdminUser();
        BeanUtils.copyProperties(entity, adminUser);

        setAdminPrivilege(entity, adminUser);
        adminUser.setCreateTime(System.currentTimeMillis());
        if (!StringUtils.isEmpty(entity.getPassword())) {
            adminUser.setPassword(MD5Utils.encode(entity.getPassword()));
        }
        if (entity.getStatus() == null) {
            entity.setStatus(StatusEnum.SUCCESS.getValue());
        }
        adminUser.setType(AdminTypeEnum.SIMPLE.getValue());
        adminUser.setCreateTime(System.currentTimeMillis());
        return adminUserMapper.insertSelective(adminUser) > 0;
    }

    private void setAdminPrivilege(AdminUserParams entity, AdminUser adminUser) {
        if (entity.getResources() != null && entity.getResources().length > 0) {
            // 设置权限了
            long[] privilegeArray = resourcesService
                    .getPrivilegeArray(entity.getResources());
            // 根据给定的权限列表计算权限数组
            adminUser.setPrivilege(JSONArray.toJSONString(privilegeArray));
        }
    }

    @Override
    public AdminUserDTO get(Integer id) {
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(id);
        if (adminUser == null) {
            return null;
        }
        AdminUserDTO dto = convertDTO(adminUser);
        return dto;
    }

    @Override
    public boolean update(AdminUserParams entity) {
        if (entity.getId() == null) {
            return false;
        }
        AdminUser adminUser = new AdminUser();
        BeanUtils.copyProperties(entity, adminUser);
        setAdminPrivilege(entity, adminUser);
        if (!StringUtils.isEmpty(entity.getPassword())) {
            adminUser.setPassword(MD5Utils.encode(entity.getPassword()));
        }
        adminUser.setUpdateTime(System.currentTimeMillis());
        adminUser.setType(null);
        return adminUserMapper.updateByPrimaryKeySelective(adminUser) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return adminUserMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public ListResultDTO<AdminUserDTO> list(TimeQueryParam param) {
        ListResultDTO<AdminUserDTO> result = param.getResult(ListResultDTO.class);

        AdminUserExample example = new AdminUserExample();
        example.or().andCreateTimeLessThan(param.getLast());
        example.setOrderByClause(
                String.format("create_time desc limit %d", result.getLimit()));
        List<AdminUser> list = adminUserMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            return result;
        }
        result.setLast(list.get(list.size() - 1).getCreateTime());
        result.setList(list.stream().map(entity -> {
            AdminUserDTO dto = convertDTO(entity);
            return dto;
        }).collect(Collectors.toList()));

        return result;
    }

    private AdminUserDTO convertDTO(AdminUser entity) {
        AdminUserDTO dto = new AdminUserDTO();
        BeanUtils.copyProperties(entity, dto);
        if (StringUtils.isNotEmpty(entity.getPrivilege())) {
            dto.setPrivilege(JSONArray.parseArray(entity.getPrivilege(), Long.class)
                    .toArray(new Long[]{}));
        }
        if (dto.getRoleId() != null && dto.getRoleId() > 0) {
            AdminRoleDTO role = adminRoleService.get(dto.getRoleId());
            if (role != null) {
                if (role.getPrivilege() != null && role.getPrivilege().length > 0) {
                    if (dto.getPrivilege() == null) {
                        dto.setPrivilege(role.getPrivilege());
                    } else {
                        Long[] result = dto.getPrivilege().length > role
                                .getPrivilege().length ? dto.getPrivilege()
                                : role.getPrivilege();
                        Long[] other = dto.getPrivilege().length > role
                                .getPrivilege().length ? role.getPrivilege()
                                : dto.getPrivilege();
                        for (int i = 0; i < result.length; i++) {
                            if (i < other.length) {
                                result[i] = result[i] | other[i];
                            }
                        }
                        dto.setPrivilege(result);
                    }
                }
            }
        }
        /**
         * 清空密码
         */
        dto.setPassword(null);
        return dto;
    }

    @Override
    public Page<AdminUserDTO> pageList(@NotNull AdminUserSearchParam param) {
        Page<AdminUserDTO> page = new Page();
        AdminUserExample example = new AdminUserExample();
        Number total = adminUserMapper.countByExample(example);
        page.setCurrentPage(param.getPage());
        page.setPageSize(param.getLimit());
        page.setTotalCount(total.intValue());
        if (total == null || total.intValue() <= 0) {
            return page;
        }
        AdminUserExample.Criteria c = example.or();
        if (param.getBelongTo() != null) {
            c.andBelongToEqualTo(param.getBelongTo());
        }

        if (StringUtils.isNotEmpty(param.getRealName())) {
            c.andRealNameLike(String.format("%%%s%%", param.getRealName()));
        }
        // 超级管理员不允许被查询
        c.andTypeEqualTo(AdminTypeEnum.SIMPLE.getValue());

        example.setOrderByClause("id desc");

        RowBounds rowBounds = new RowBounds((param.getPage() - 1) * param.getLimit(),
                param.getLimit());
        List<AdminUser> list = adminUserMapper.selectByExampleWithRowbounds(example,
                rowBounds);
        if (CollectionUtils.isEmpty(list)) {
            return page;
        }
        page.setResult(list.stream().map(adminUser -> {
            AdminUserDTO dto = convertDTO(adminUser);
            return dto;
        }).collect(Collectors.toList()));
        return page;
    }

    @Override
    public AdminUserDTO login(String mobile, String passwod, String smsCode)
            throws ServiceDataException {
        String md5pass = MD5Utils.encode(passwod);
        AdminUserExample example = new AdminUserExample();
        example.or().andMobileEqualTo(mobile).andPasswordEqualTo(md5pass);

        List<AdminUser> adminList = adminUserMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(adminList)) {
            return null;
        }
        AdminUser admin = adminList.get(0);
        if (admin.getStatus() <= 0) {
            throw new ServiceDataException(AdminResultJson.ERROR_LOGIN_REFUSED);
        }
        if (!verifyCodeService.checkCode(CodeTypeEnum.LOGIN.getValue(), mobile,
                smsCode)) {
            return null;
        }

        AdminUserDTO dto = convertDTO(admin);
        return dto;

    }

    @Override
    public String sendLoginCode(String mobile) throws ServiceDataException {
        String code = verifyCodeService.sendCode(CodeTypeEnum.LOGIN.getValue(), mobile);
        if (online) {
            // TODO: 短信验证码
            Map<String, Object> params = new HashMap<>();
            params.put("code", code);
            AliyunSmsUtil.sendSms(mobile, "SMS_143910156", params);
            return null;
        }
        return code;
    }

    @Override
    public List<AdminUserDTO> queryByIds(List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return null;
        }

        AdminUserExample example = new AdminUserExample();
        example.or().andIdIn(ids);
        return queryByExample(example);
    }

    private List<AdminUserDTO> queryByExample(AdminUserExample example) {
        List<AdminUser> userList = adminUserMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        }
        return userList.stream().map(admin -> {
            AdminUserDTO dto = new AdminUserDTO();
            BeanUtils.copyProperties(admin, dto);
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public boolean mobileExist(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return false;
        }
        AdminUserExample example = new AdminUserExample();
        example.or().andMobileEqualTo(mobile);
        example.setOrderByClause("id desc limit 1");
        return !CollectionUtils.isEmpty(adminUserMapper.selectByExample(example));
    }

    @Override
    public List<AdminUserDTO> listAll() {
        AdminUserExample example = new AdminUserExample();
        example.setOrderByClause("id desc limit 100");
        return queryByExample(example);
    }

}
