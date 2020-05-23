package com.little.g.springcloud.admin.service;

import com.little.g.springcloud.admin.AdminResultJson;
import com.little.g.springcloud.admin.api.AdminUserService;
import com.little.g.springcloud.admin.api.ResourcesService;
import com.little.g.springcloud.admin.dto.AdminUserDTO;
import com.little.g.springcloud.admin.dto.ResourcesDTO;
import com.little.g.springcloud.admin.dto.UserResourceDTO;
import com.little.g.springcloud.admin.enums.AdminTypeEnum;
import com.little.g.springcloud.admin.enums.ResourcesEnum;
import com.little.g.springcloud.admin.mapper.ResourcesMapper;
import com.little.g.springcloud.admin.mapper.ResourcesMapperExt;
import com.little.g.springcloud.admin.model.Resources;
import com.little.g.springcloud.admin.model.ResourcesExample;
import com.little.g.springcloud.common.dto.ListResultDTO;
import com.little.g.springcloud.common.dto.Page;
import com.little.g.springcloud.common.enums.BooleanEnum;
import com.little.g.springcloud.common.exception.ServiceDataException;
import com.little.g.springcloud.common.params.PageQueryParam;
import com.little.g.springcloud.common.params.TimeQueryParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by lengligang on 2019/3/9.
 */
@Service(protocol = "dubbo")
public class ResourcesServiceImpl implements ResourcesService {

	private static final Logger logger = LoggerFactory.getLogger(ResourcesService.class);

	@Resource
	private ResourcesMapper resourcesMapper;

	@Resource
	private ResourcesMapperExt resourcesMapperExt;

	@Resource
	private AdminUserService adminUserService;

	private Map<String, Resources> pathResources = new HashMap<>();

	private Map<String, Resources> wildcardResources = new HashMap<>();

	/**
	 * 5分钟刷新
	 */
	@Scheduled(fixedDelay = 1000 * 600)
	private void loadResources() {

		ResourcesExample example = new ResourcesExample();
		List<Resources> allResources = resourcesMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(allResources)) {
			return;
		}

		Map<String, Resources> pathResourcesTemp = new HashMap<>();

		Map<String, Resources> wildcardResourcesTemp = new HashMap<>();

		for (Resources resources : allResources) {
			if (StringUtils.isEmpty(resources.getPath())) {
				continue;
			}
			if (Objects.equals(resources.getType(), ResourcesEnum.SIMPLE.getValue())) {
				// 普通
				pathResourcesTemp.put(resources.getPath(), resources);
			}
			else {
				// 通配符
				wildcardResourcesTemp.put(resources.getPath(), resources);
			}
		}
		synchronized (this) {
			pathResources = pathResourcesTemp;
			wildcardResources = wildcardResourcesTemp;
		}

	}

	public ResourcesDTO getResourceByUri(String uri) {
		if (StringUtils.isEmpty(uri)) {
			return null;
		}
		Resources resources = pathResources.get(uri);
		if (resources == null && !CollectionUtils.isEmpty(wildcardResources)) {
			for (Map.Entry<String, Resources> entry : wildcardResources.entrySet()) {
				// TODO: 这里先假设一个url只有一个通配符匹配
				if (Pattern.matches(entry.getKey(), uri)) {
					resources = entry.getValue();
					break;
				}
			}
		}
		if (resources == null)
			return null;

		ResourcesDTO dto = new ResourcesDTO();
		BeanUtils.copyProperties(resources, dto);

		return dto;

	}

	@Override
	public boolean add(ResourcesDTO entity) {
		Resources resources = new Resources();
		BeanUtils.copyProperties(entity, resources);
		resources.setCreateTime(System.currentTimeMillis());
		if (resources.getParentId() == null) {
			resources.setParentId(0);
		}
		if (resources.getType() == null) {
			resources.setType(ResourcesEnum.SIMPLE.getValue());
		}
		if (resources.getIsMenu() == null) {
			resources.setIsMenu(BooleanEnum.TRUE.getValue());
		}
		if (resources.getNeedAuth() == null) {
			resources.setNeedAuth(BooleanEnum.TRUE.getValue());
		}

		// 计算权限位权限值

		Integer pos = resourcesMapperExt.selectMaxPrivilegePos();
		if (pos == null)
			pos = 0;
		Long val = resourcesMapperExt.selectMaxPrivilegeVal(pos);
		if (val == null || val <= 0) {
			val = 1l;
		}
		else {
			val = (val << 1);
		}
		if (val < 0) {
			val = 1l;
			pos = pos + 1;
		}
		resources.setPrivilegePos(pos);
		resources.setPrivilegeVal(val);

		return resourcesMapper.insertSelective(resources) > 0;
	}

	@Override
	public ResourcesDTO get(Integer id) {
		Resources resources = resourcesMapper.selectByPrimaryKey(id);
		if (resources == null) {
			return null;
		}
		ResourcesDTO dto = new ResourcesDTO();
		BeanUtils.copyProperties(resources, dto);
		return dto;
	}

	@Override
	public boolean update(ResourcesDTO entity) {
		if (entity.getId() == null)
			return false;
		Resources resources = new Resources();
		BeanUtils.copyProperties(entity, resources);
		resources.setPrivilegePos(null);
		resources.setPrivilegeVal(null);
		return resourcesMapper.updateByPrimaryKeySelective(resources) > 0;
	}

	@Override
	public boolean delete(Integer id) {
		return resourcesMapper.deleteByPrimaryKey(id) > 0;
	}

	@Override
	public ListResultDTO<ResourcesDTO> list(TimeQueryParam param) {
		ListResultDTO<ResourcesDTO> result = param.getResult(ListResultDTO.class);

		ResourcesExample example = new ResourcesExample();
		example.or().andCreateTimeLessThan(param.getLast());
		example.setOrderByClause(
				String.format("create_time desc limit %d", result.getLimit()));
		List<Resources> list = resourcesMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(list)) {
			return result;
		}
		result.setLast(list.get(list.size() - 1).getCreateTime());
		result.setList(list.stream().map(entity -> {
			ResourcesDTO dto = new ResourcesDTO();
			BeanUtils.copyProperties(entity, dto);
			return dto;
		}).collect(Collectors.toList()));

		return result;
	}

	@Override
	public Page<ResourcesDTO> pageList(@NotNull PageQueryParam param) {
		Page<ResourcesDTO> page = new Page();
		ResourcesExample example = new ResourcesExample();
		Number total = resourcesMapper.countByExample(example);
		page.setCurrentPage(param.getPage());
		page.setPageSize(param.getLimit());
		page.setTotalCount(total.intValue());
		if (total == null || total.intValue() <= 0) {
			return page;
		}
		example.setOrderByClause("id desc");

		RowBounds rowBounds = new RowBounds((param.getPage() - 1) * param.getLimit(),
				param.getLimit());
		List<Resources> list = resourcesMapper.selectByExampleWithRowbounds(example,
				rowBounds);
		if (CollectionUtils.isEmpty(list)) {
			return page;
		}
		page.setResult(list.stream().map(resources -> {
			ResourcesDTO dto = new ResourcesDTO();
			BeanUtils.copyProperties(resources, dto);
			return dto;
		}).collect(Collectors.toList()));
		return page;
	}

	@Override
	public long[] getPrivilegeArray(Integer[] resourcesArray) {
		if (resourcesArray == null || resourcesArray.length <= 0) {
			return null;
		}
		ResourcesExample example = new ResourcesExample();
		example.or().andIdIn(Arrays.asList(resourcesArray));
		List<Resources> resourcesList = resourcesMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(resourcesList)) {
			return null;
		}

		Optional<Resources> maxResource = resourcesList.stream()
				.max(Comparator.comparingInt(Resources::getPrivilegePos));
		int length = (maxResource.get().getPrivilegePos() + 1);
		long[] privilegeArray = new long[length];

		for (Resources r : resourcesList) {
			privilegeArray[r.getPrivilegePos()] = privilegeArray[r.getPrivilegePos()]
					| r.getPrivilegeVal();
		}

		return privilegeArray;
	}

	public List<UserResourceDTO> userInitMenu(Integer adminId) {
		if (adminId == null) {
			return null;
		}
		AdminUserDTO admin = adminUserService.get(adminId);
		if (admin == null) {
			return null;
		}

		ResourcesExample example = new ResourcesExample();
		example.or().andIsMenuEqualTo(BooleanEnum.TRUE.getValue());
		example.setOrderByClause("sort desc,id desc");
		List<Resources> resourceList = resourcesMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(resourceList)) {
			return null;
		}
		return convertSubList(admin, resourceList, true);
	}

	@Override
	public List<UserResourceDTO> userResources(Integer adminId) {
		AdminUserDTO admin = null;
		if (adminId != null) {
			admin = adminUserService.get(adminId);
		}
		ResourcesExample example = new ResourcesExample();
		example.or().andNeedAuthEqualTo(BooleanEnum.TRUE.getValue());
		List<Resources> resourceList = resourcesMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(resourceList)) {
			return null;
		}
		return convertSubList(admin, resourceList, false);
	}

	private List<UserResourceDTO> convertSubList(AdminUserDTO admin,
			List<Resources> resourceList, boolean filter) {
		List<UserResourceDTO> userresourceList = new ArrayList<>(resourceList.size());
		for (Resources resources : resourceList) {
			UserResourceDTO dto = new UserResourceDTO();
			BeanUtils.copyProperties(resources, dto);
			if (admin != null) {
				if (Objects.equals(admin.getType(), AdminTypeEnum.SUPER.getValue())) {
					dto.setChecked(true);
				}
				else if (admin.getPrivilege() != null
						&& admin.getPrivilege().length >= resources.getPrivilegePos()) {
					if ((resources.getPrivilegeVal()
							& admin.getPrivilege()[resources.getPrivilegePos()]) > 0) {
						dto.setChecked(true);
					}
				}
			}
			if (filter) {
				if (dto.isChecked()) {
					userresourceList.add(dto);
				}
			}
			else {
				userresourceList.add(dto);
			}
		}

		List<UserResourceDTO> parents = userresourceList.stream().filter(
				resurce -> resurce.getParentId() == null || resurce.getParentId() <= 0)
				.collect(Collectors.toList());
		if (CollectionUtils.isEmpty(parents)) {
			return null;
		}

		setSubList(parents, userresourceList);

		parents = parents.stream()
				.filter(list -> !CollectionUtils.isEmpty(list.getSubMenus()))
				.collect(Collectors.toList());
		return parents;
	}

	private List<UserResourceDTO> setSubList(List<UserResourceDTO> parentList,
			List<UserResourceDTO> all) {
		for (UserResourceDTO top : parentList) {
			List<UserResourceDTO> subList = all.stream().filter(
					resource -> Objects.equals(resource.getParentId(), top.getId()))
					.collect(Collectors.toList());
			if (!CollectionUtils.isEmpty(subList)) {
				top.setSubMenus(subList);
				setSubList(subList, all);
			}
		}
		return parentList;
	}

	@Override
	public boolean hasPrivilege(String url, AdminUserDTO adminUserDTO)
			throws ServiceDataException {
		if (StringUtils.isEmpty(url)) {
			return false;
		}
		if (adminUserDTO == null) {
			throw new ServiceDataException(AdminResultJson.ERROR_NEET_LOGIN);
		}
		if (Objects.equals(adminUserDTO.getType(), AdminTypeEnum.SUPER.getValue())) {
			return true;
		}

		ResourcesDTO resources = getResourceByUri(url.replace("//", "/"));
		if (resources == null) {
			throw new ServiceDataException(AdminResultJson.ERR_NOT_ALLOW_OPERATION);
		}
		// 不需要权限
		if (Objects.equals(resources.getNeedAuth(), BooleanEnum.FALSE.getValue())) {
			return true;
		}

		Long[] privileges = adminUserDTO.getPrivilege();

		if (privileges == null || privileges.length < (resources.getPrivilegePos() + 1)) {
			throw new ServiceDataException(AdminResultJson.ERR_NOT_ALLOW_OPERATION);
		}

		if ((privileges[resources.getPrivilegePos()] & resources.getPrivilegeVal()) > 0) {
			return true;
		}

		throw new ServiceDataException(AdminResultJson.ERR_NOT_ALLOW_OPERATION);
	}

}
