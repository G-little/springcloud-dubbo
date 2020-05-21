package com.little.g.springcloud.common.enums;

public enum StatusEnum {

	INIT((byte) 0), // 初始
	SUCCESS((byte) 1); // 成功

	StatusEnum(byte value) {
		this.value = value;
	}

	public byte value;

	public byte getValue() {
		return value;
	}

}
