package com.little.g.springcloud.pay.dto;

import com.little.g.springcloud.pay.utils.Accounts;

/**
 * 正常用户账户 Created by zhaoyao on 16/5/9.
 */
public class NormalUserAccount implements AccountHasUserId {

	private static final long serialVersionUID = -4068712037983174359L;

    private Integer uid;

	public NormalUserAccount() {
	}

    public NormalUserAccount(Integer uid) {
		this.uid = uid;
	}

    @Override
    public Integer getUid() {
		return uid;
	}

	@Override
	public String getId() {
		return Accounts.NORMAL_USER_PREFIX + uid;
	}

	@Override
	public String getPrefix() {
		return Accounts.NORMAL_USER_PREFIX;
	}

	@Override
	public boolean equals(Object o) {
        if (this == o) {
			return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

		NormalUserAccount that = (NormalUserAccount) o;

        return uid.equals(that.uid);

	}

	@Override
	public int hashCode() {
		return (int) (uid ^ (uid >>> 32));
	}

	@Override
	public String toString() {
		return "NormalUserAccount{" + "uid=" + uid + '}';
	}

}
