package com.little.g.springcloud.admin.mapper;

import org.apache.ibatis.annotations.Param;

public interface ResourcesMapperExt {

	/**
	 * 获取最大权限位
	 * @return
	 */
	Integer selectMaxPrivilegePos();

	/**
	 * 获取最大权限位
	 * @return
	 */
	Long selectMaxPrivilegeVal(@Param("pos") Integer pos);

}