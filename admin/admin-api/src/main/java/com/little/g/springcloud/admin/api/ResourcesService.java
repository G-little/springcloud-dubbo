package com.little.g.springcloud.admin.api;

import com.little.g.springcloud.admin.dto.AdminUserDTO;
import com.little.g.springcloud.admin.dto.ResourcesDTO;
import com.little.g.springcloud.admin.dto.UserResourceDTO;
import com.little.g.springcloud.admin.enums.LogicalEnum;
import com.little.g.springcloud.common.dto.ListResultDTO;
import com.little.g.springcloud.common.dto.Page;
import com.little.g.springcloud.common.exception.ServiceDataException;
import com.little.g.springcloud.common.params.PageQueryParam;
import com.little.g.springcloud.common.params.TimeQueryParam;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by lengligang on 2019/3/9.
 */
public interface ResourcesService {

	/**
	 * 添加
	 * @param entity
	 * @return
	 */
	boolean add(@NotNull ResourcesDTO entity);

	/**
	 * 根据id获取
	 * @param id
	 * @return
	 */
	ResourcesDTO get(@NotNull Integer id);

	/**
	 * 更新
	 * @param entity
	 * @return
	 */
	boolean update(@NotNull ResourcesDTO entity);

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	boolean delete(@NotNull Integer id);

	/**
	 * 增量查询
	 * @param param
	 * @return
	 */
	ListResultDTO<ResourcesDTO> list(@NotNull TimeQueryParam param);

	/**
	 * 分页查询逻辑
	 * @param param
	 * @return
	 */
	Page<ResourcesDTO> pageList(@NotNull PageQueryParam param);

	/**
	 * 根据uri获取资源
	 * @param uri
	 * @return
	 */
	ResourcesDTO getResourceByUri(String uri);

	/**
	 * 根据id获取权限数组
	 * @param resourcesArray
	 * @return
	 */
	long[] getPrivilegeArray(Integer[] resourcesArray);

	/**
	 * 查询用户菜单列表
	 * @param adminId
	 * @return
	 */
	List<UserResourceDTO> userResources(Integer adminId);

	/**
	 * 初始登录菜单
	 * @param adminId
	 * @return
	 */
	List<UserResourceDTO> userInitMenu(Integer adminId);

	/**
	 * 查看是否权限
	 * @param permissions
	 * @param l
	 * @param url
	 * @param admin
	 * @return
	 * @throws ServiceDataException
	 */
	boolean hasPrivilege(String[] permissions, LogicalEnum l, String url,
			AdminUserDTO admin) throws ServiceDataException;

}
