package com.little.g.springcloud.common.enums;

public enum BooleanEnum {

	FALSE((byte) 0, "否"), // 女
	TRUE((byte) 1, "是"); // 保密

	BooleanEnum(Byte value, String desc) {
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
