package com.little.g.springcloud.admin.enums;

/**
 * User: liuling Date: 15/9/17 Time: 下午1:52
 */
public enum ResourcesEnum {

	WILDCARD((byte) 1, "通配符"), // 女
	SIMPLE((byte) 0, "普通"); // 保密

	ResourcesEnum(Byte value, String desc) {
		this.desc = desc;
		this.value = value;
	}

	public Byte value;

	public String getDesc() {
		return desc;
	}

	public String desc;

	public Byte getValue() {
		return value;
	}

}
