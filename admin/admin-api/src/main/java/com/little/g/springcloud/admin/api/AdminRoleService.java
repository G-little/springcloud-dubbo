package com.little.g.springcloud.admin.api;

import com.little.g.springcloud.admin.dto.AdminRoleDTO;
import com.little.g.springcloud.admin.params.AdminRoleParams;
import com.little.g.springcloud.common.dto.ListResultDTO;
import com.little.g.springcloud.common.dto.Page;
import com.little.g.springcloud.common.params.PageQueryParam;
import com.little.g.springcloud.common.params.TimeQueryParam;

import javax.validation.constraints.NotNull;

/**
 * Created by lengligang on 2019/3/9.
 */
public interface AdminRoleService {

	/**
	 * 添加
	 * @param entity
	 * @return
	 */
	boolean add(@NotNull AdminRoleParams entity);

	/**
	 * 根据id获取
	 * @param id
	 * @return
	 */
	AdminRoleDTO get(@NotNull Integer id);

	/**
	 * 更新
	 * @param entity
	 * @return
	 */
	boolean update(@NotNull AdminRoleParams entity);

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
	ListResultDTO<AdminRoleDTO> list(@NotNull TimeQueryParam param);

	/**
	 * 分页查询逻辑
	 * @param param
	 * @return
	 */
	Page<AdminRoleDTO> pageList(@NotNull PageQueryParam param);

}
