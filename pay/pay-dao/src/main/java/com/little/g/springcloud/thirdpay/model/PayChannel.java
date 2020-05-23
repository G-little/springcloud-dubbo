package com.little.g.springcloud.thirdpay.model;

import java.io.Serializable;

public class PayChannel implements Serializable {

	private Byte id;

	private String name;

	private String code;

	private String configVersion;

	private Integer maxPayFee;

	private Integer minPayFee;

	private Byte status;

	private String memo;

	private static final long serialVersionUID = 1L;

	public Byte getId() {
		return id;
	}

	public void setId(Byte id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	public String getConfigVersion() {
		return configVersion;
	}

	public void setConfigVersion(String configVersion) {
		this.configVersion = configVersion == null ? null : configVersion.trim();
	}

	public Integer getMaxPayFee() {
		return maxPayFee;
	}

	public void setMaxPayFee(Integer maxPayFee) {
		this.maxPayFee = maxPayFee;
	}

	public Integer getMinPayFee() {
		return minPayFee;
	}

	public void setMinPayFee(Integer minPayFee) {
		this.minPayFee = minPayFee;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo == null ? null : memo.trim();
	}

	@Override
	public String toString() {
		return "PayChannel{" + "id=" + id + ", name='" + name + '\'' + ", code='" + code
				+ '\'' + ", configVersion='" + configVersion + '\'' + ", maxPayFee="
				+ maxPayFee + ", minPayFee=" + minPayFee + ", status=" + status
				+ ", memo='" + memo + '\'' + '}';
	}

}