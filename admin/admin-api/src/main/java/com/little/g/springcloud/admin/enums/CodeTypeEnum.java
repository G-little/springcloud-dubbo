package com.little.g.springcloud.admin.enums;

public enum CodeTypeEnum {

	LOGIN((byte) 1, "登陆"); // 保密

	CodeTypeEnum(Byte value, String desc) {
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
