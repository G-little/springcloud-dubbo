package com.little.g.springcloud.admin.service;

import com.alibaba.fastjson.JSONArray;
import com.little.g.springcloud.admin.api.AdminRoleService;
import com.little.g.springcloud.admin.api.ResourcesService;
import com.little.g.springcloud.admin.dto.AdminRoleDTO;
import com.little.g.springcloud.admin.mapper.AdminRoleMapper;
import com.little.g.springcloud.admin.model.AdminRole;
import com.little.g.springcloud.admin.model.AdminRoleExample;
import com.little.g.springcloud.admin.params.AdminRoleParams;
import com.little.g.springcloud.common.dto.ListResultDTO;
import com.little.g.springcloud.common.dto.Page;
import com.little.g.springcloud.common.params.PageQueryParam;
import com.little.g.springcloud.common.params.TimeQueryParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lengligang on 2019/3/9.
 */
@Service(protocol = "dubbo")
public class AdminRoleServiceImpl implements AdminRoleService {

	@Resource
	private AdminRoleMapper adminRoleMapper;

	@Resource
	private ResourcesService resourcesService;

	@Override
	public boolean add(AdminRoleParams entity) {
		AdminRole adminRole = new AdminRole();
		BeanUtils.copyProperties(entity, adminRole);
		setRolePrivilege(entity, adminRole);
		return adminRoleMapper.insertSelective(adminRole) > 0;
	}

	@Override
	public AdminRoleDTO get(Integer id) {
		AdminRole adminRole = adminRoleMapper.selectByPrimaryKey(id);
		if (adminRole == null) {
			return null;
		}
		AdminRoleDTO dto = new AdminRoleDTO();
		BeanUtils.copyProperties(adminRole, dto);
		if (StringUtils.isNotEmpty(adminRole.getPrivilege())) {
			dto.setPrivilege(JSONArray.parseArray(adminRole.getPrivilege(), Long.class)
					.toArray(new Long[] {}));
		}
		return dto;
	}

	@Override
	public boolean update(AdminRoleParams entity) {
		if (entity.getId() == null)
			return false;
		AdminRole adminRole = new AdminRole();
		BeanUtils.copyProperties(entity, adminRole);
		setRolePrivilege(entity, adminRole);
		return adminRoleMapper.updateByPrimaryKeySelective(adminRole) > 0;
	}

	@Override
	public boolean delete(Integer id) {
		return adminRoleMapper.deleteByPrimaryKey(id) > 0;
	}

	@Override
	public ListResultDTO<AdminRoleDTO> list(TimeQueryParam param) {
		ListResultDTO<AdminRoleDTO> result = param.getResult(ListResultDTO.class);

		AdminRoleExample example = new AdminRoleExample();
		example.or().andCreateTimeLessThan(param.getLast());
		example.setOrderByClause(
				String.format("create_time desc limit %d", result.getLimit()));
		List<AdminRole> list = adminRoleMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(list)) {
			return result;
		}
		result.setLast(list.get(list.size() - 1).getCreateTime());
		result.setList(list.stream().map(entity -> {
			AdminRoleDTO dto = new AdminRoleDTO();
			BeanUtils.copyProperties(entity, dto);
			return dto;
		}).collect(Collectors.toList()));

		return result;
	}

	private void setRolePrivilege(AdminRoleParams entity, AdminRole adminRole) {
		if (entity.getResources() != null && entity.getResources().length > 0) {
			// 设置权限了
			long[] privilegeArray = resourcesService
					.getPrivilegeArray(entity.getResources());
			// 根据给定的权限列表计算权限数组
			adminRole.setPrivilege(JSONArray.toJSONString(privilegeArray));
		}
	}

	@Override
	public Page<AdminRoleDTO> pageList(@NotBlank PageQueryParam param) {
		Page<AdminRoleDTO> page = new Page();
		AdminRoleExample example = new AdminRoleExample();
		Number total = adminRoleMapper.countByExample(example);
		page.setCurrentPage(param.getPage());
		page.setPageSize(param.getLimit());
		page.setTotalCount(total.intValue());
		if (total != null && total.intValue() <= 0) {
			return page;
		}
		example.setOrderByClause("id desc");

		RowBounds rowBounds = new RowBounds((param.getPage() - 1) * param.getLimit(),
				param.getLimit());
		List<AdminRole> list = adminRoleMapper.selectByExampleWithRowbounds(example,
				rowBounds);
		if (CollectionUtils.isEmpty(list)) {
			return page;
		}
		page.setResult(list.stream().map(adminRole -> {
			AdminRoleDTO dto = new AdminRoleDTO();
			BeanUtils.copyProperties(adminRole, dto);
			if (StringUtils.isNotEmpty(adminRole.getPrivilege())) {
				dto.setPrivilege(
						JSONArray.parseArray(adminRole.getPrivilege(), Long.class)
								.toArray(new Long[] {}));
			}
			return dto;
		}).collect(Collectors.toList()));
		return page;
	}

}
