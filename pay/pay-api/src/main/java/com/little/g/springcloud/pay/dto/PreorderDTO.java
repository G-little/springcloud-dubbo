package com.little.g.springcloud.pay.dto;

import java.io.Serializable;

public class PreorderDTO implements Serializable {

	private String mchId;

	private String preOrderNo;

	private String attach;

	private String outTradeNo;

	private Byte btype;

	private Long totalFee;

	private Integer accountId;

	private Integer oppositAccount;

	private String tradeType;

	private Byte status;

	private String notifyUrl;

	private String subject;

	private String payType;

	private Long createTime;

	private Long updateTime;

	private static final long serialVersionUID = 1L;

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach == null ? null : attach.trim();
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo == null ? null : outTradeNo.trim();
	}

	public Long getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Long totalFee) {
		this.totalFee = totalFee;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getOppositAccount() {
		return oppositAccount;
	}

	public void setOppositAccount(Integer oppositAccount) {
		this.oppositAccount = oppositAccount;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType == null ? null : tradeType.trim();
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl == null ? null : notifyUrl.trim();
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject == null ? null : subject.trim();
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType == null ? null : payType.trim();
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getPreOrderNo() {
		return preOrderNo;
	}

	public void setPreOrderNo(String preOrderNo) {
		this.preOrderNo = preOrderNo;
	}

	public Byte getBtype() {
		return btype;
	}

	public void setBtype(Byte btype) {
		this.btype = btype;
	}

	@Override
	public String toString() {
		return "PreorderDTO{" + "mchId='" + mchId + '\'' + ", preOrderNo='" + preOrderNo
				+ '\'' + ", attach='" + attach + '\'' + ", outTradeNo='" + outTradeNo
				+ '\'' + ", btype=" + btype + ", totalFee=" + totalFee + ", accountId="
				+ accountId + ", oppositAccount=" + oppositAccount + ", tradeType='"
				+ tradeType + '\'' + ", status=" + status + ", notifyUrl='" + notifyUrl
				+ '\'' + ", subject='" + subject + '\'' + ", payType='" + payType + '\''
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + '}';
	}

}
