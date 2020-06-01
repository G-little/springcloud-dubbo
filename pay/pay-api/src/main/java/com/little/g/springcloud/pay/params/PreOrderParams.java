package com.little.g.springcloud.pay.params;

import com.little.g.springcloud.pay.enums.MerchantId;
import com.little.g.springcloud.pay.enums.TradeType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class PreOrderParams implements Serializable {

	/**
	 * 商户ID
	 */
	@NotEmpty
	private String mchId = MerchantId.LittelG.getValue();

	@NotEmpty
	/**
	 * 三方关联流水
	 */
	private String outTradeNo;

	@Min(value = 1)
	private Long totalFee;

	@NotNull
	private Integer accountId;

	@NotNull
	private Integer oppositAccount;

	@NotNull
	private TradeType tradeType;

	/**
	 * 回调地址
	 */
	private String notifyUrl;

	/**
	 * 交易描述
	 */
	@NotEmpty
	private String comment;

	/**
	 * 附加参数
	 */
	private String attach;

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
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

	public TradeType getTradeType() {
		return tradeType;
	}

	public void setTradeType(TradeType tradeType) {
		this.tradeType = tradeType;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

}
