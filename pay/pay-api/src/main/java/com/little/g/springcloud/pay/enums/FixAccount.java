package com.little.g.springcloud.pay.enums;

import com.little.g.springcloud.pay.dto.Account;

/**
 * Created by lengligang on 16/1/18. user account status
 */
public enum FixAccount {

	/* 冲帐用 */
	LITTLE_G(10000L, "系统账户",
			new com.little.g.springcloud.pay.dto.FixAccount(10000L, true)), THIRD_PAY(
					9999L, "三方支付",
					new com.little.g.springcloud.pay.dto.FixAccount(9999L, true));

	FixAccount(Long value, String desc, Account account) {
		this.value = value;
		this.desc = desc;
		this.account = account;
	}

	public Long value;

	public String desc;

	private Account account;

	public String getDesc() {
		return desc;
	}

	public Account getAccount() {
		return account;
	}

	public Long getValue() {
		return value;
	}

	public static boolean isFixAccount(Long uid) {
		boolean flag = false;
		for (FixAccount account : FixAccount.values()) {
			if (account.getValue().equals(uid)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

}
