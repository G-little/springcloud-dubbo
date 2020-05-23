package com.little.g.springcloud.admin.api;

import com.little.g.springcloud.admin.dto.AdminUserDTO;
import com.little.g.springcloud.admin.params.AdminUserParams;
import com.little.g.springcloud.admin.params.AdminUserSearchParam;
import com.little.g.springcloud.common.dto.ListResultDTO;
import com.little.g.springcloud.common.dto.Page;
import com.little.g.springcloud.common.exception.ServiceDataException;
import com.little.g.springcloud.common.params.TimeQueryParam;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by lengligang on 2019/3/9.
 */
public interface AdminUserService {

	/**
	 * 添加
	 * @param entity
	 * @return
	 */
	boolean add(@NotNull AdminUserParams entity);

	/**
	 * 根据id获取
	 * @param id
	 * @return
	 */
	AdminUserDTO get(@NotNull Integer id);

	/**
	 * 更新
	 * @param entity
	 * @return
	 */
	boolean update(@NotNull AdminUserParams entity);

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
	ListResultDTO<AdminUserDTO> list(@NotNull TimeQueryParam param);

	/**
	 * 分页查询逻辑
	 * @param param
	 * @return
	 */
	Page<AdminUserDTO> pageList(@NotNull AdminUserSearchParam param);

	/**
	 * 登陆接口
	 * @param mobile
	 * @param passwod
	 * @param smsCode
	 * @return
	 */
	AdminUserDTO login(String mobile, String passwod, String smsCode)
			throws ServiceDataException;

	/**
	 * 发送登陆验证码
	 * @param mobile
	 * @return
	 */
	String sendLoginCode(String mobile) throws ServiceDataException;

	/**
	 * 根据Id查询列表
	 * @param ids
	 * @return
	 */
	List<AdminUserDTO> queryByIds(List<Integer> ids);

	/**
	 * @param mobile
	 * @return
	 */
	boolean mobileExist(String mobile);

	/**
	 * 查询所有管理员信息
	 * @return
	 */
	List<AdminUserDTO> listAll();

}
