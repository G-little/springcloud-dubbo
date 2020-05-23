package com.little.g.springcloud.admin.enums;

public enum AdminTypeEnum {

	SIMPLE((byte) 0, "否"), // 女
	SUPER((byte) 1, "是"); // 保密

	AdminTypeEnum(Byte value, String desc) {
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
