package com.little.g.springcloud.user.web.form;

/**
 * 基类 User: erin Date: 14-10-16 Time: 下午2:58
 */
public class BaseParams {

    private Integer uid;

    public Integer getUid() {
		return uid;
	}

    public void setUid(Integer uid) {
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "BaseParams{" + ", uid=" + uid + '}';
	}

}
