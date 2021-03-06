package com.little.g.springcloud.pay.model;

import java.util.ArrayList;
import java.util.Arrays;

public class PreorderKey {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column preorder.mch_id
	 *
	 * @mbg.generated
	 */
	private String mchId;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column preorder.pre_order_no
	 *
	 * @mbg.generated
	 */
	private String preOrderNo;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of
	 * the database column preorder.mch_id
	 * @return the value of preorder.mch_id
	 *
	 * @mbg.generated
	 */
	public String getMchId() {
		return mchId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the
	 * database column preorder.mch_id
	 * @param mchId the value for preorder.mch_id
	 *
	 * @mbg.generated
	 */
	public void setMchId(String mchId) {
		this.mchId = mchId == null ? null : mchId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of
	 * the database column preorder.pre_order_no
	 * @return the value of preorder.pre_order_no
	 *
	 * @mbg.generated
	 */
	public String getPreOrderNo() {
		return preOrderNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the
	 * database column preorder.pre_order_no
	 * @param preOrderNo the value for preorder.pre_order_no
	 *
	 * @mbg.generated
	 */
	public void setPreOrderNo(String preOrderNo) {
		this.preOrderNo = preOrderNo == null ? null : preOrderNo.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table preorder
	 *
	 * @mbg.generated
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", mchId=").append(mchId);
		sb.append(", preOrderNo=").append(preOrderNo);
		sb.append("]");
		return sb.toString();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table preorder
	 *
	 * @mbg.generated
	 */
	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		PreorderKey other = (PreorderKey) that;
		return (this.getMchId() == null ? other.getMchId() == null
				: this.getMchId().equals(other.getMchId()))
				&& (this.getPreOrderNo() == null ? other.getPreOrderNo() == null
						: this.getPreOrderNo().equals(other.getPreOrderNo()));
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the
	 * database table preorder
	 *
	 * @mbg.generated
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getMchId() == null) ? 0 : getMchId().hashCode());
		result = prime * result
				+ ((getPreOrderNo() == null) ? 0 : getPreOrderNo().hashCode());
		return result;
	}

	/**
	 * This enum was generated by MyBatis Generator. This enum corresponds to the database
	 * table preorder
	 *
	 * @mbg.generated
	 */
	public enum Column {

		mchId("mch_id", "mchId", "VARCHAR", false), preOrderNo("pre_order_no",
				"preOrderNo", "VARCHAR",
				false), attach("attach", "attach", "VARCHAR", false), outTradeNo(
						"out_trade_no", "outTradeNo", "VARCHAR",
						false), totalFee("total_fee", "totalFee", "BIGINT",
								false), accountId("account_id", "accountId", "INTEGER",
										false), oppositAccount("opposit_account",
												"oppositAccount", "INTEGER",
												false), tradeType("trade_type",
														"tradeType", "VARCHAR",
														false), btype("btype", "btype",
																"TINYINT",
																false), status("status",
																		"status",
																		"TINYINT",
																		true), notifyUrl(
																				"notify_url",
																				"notifyUrl",
																				"VARCHAR",
																				false), subject(
																						"subject",
																						"subject",
																						"VARCHAR",
																						false), payType(
																								"pay_type",
																								"payType",
																								"VARCHAR",
																								false), createTime(
																										"create_time",
																										"createTime",
																										"BIGINT",
																										false), updateTime(
																												"update_time",
																												"updateTime",
																												"BIGINT",
																												false);

		/**
		 * This field was generated by MyBatis Generator. This field corresponds to the
		 * database table preorder
		 *
		 * @mbg.generated
		 */
		private static final String BEGINNING_DELIMITER = "`";

		/**
		 * This field was generated by MyBatis Generator. This field corresponds to the
		 * database table preorder
		 *
		 * @mbg.generated
		 */
		private static final String ENDING_DELIMITER = "`";

		/**
		 * This field was generated by MyBatis Generator. This field corresponds to the
		 * database table preorder
		 *
		 * @mbg.generated
		 */
		private final String column;

		/**
		 * This field was generated by MyBatis Generator. This field corresponds to the
		 * database table preorder
		 *
		 * @mbg.generated
		 */
		private final boolean isColumnNameDelimited;

		/**
		 * This field was generated by MyBatis Generator. This field corresponds to the
		 * database table preorder
		 *
		 * @mbg.generated
		 */
		private final String javaProperty;

		/**
		 * This field was generated by MyBatis Generator. This field corresponds to the
		 * database table preorder
		 *
		 * @mbg.generated
		 */
		private final String jdbcType;

		/**
		 * This method was generated by MyBatis Generator. This method corresponds to the
		 * database table preorder
		 *
		 * @mbg.generated
		 */
		public String value() {
			return this.column;
		}

		/**
		 * This method was generated by MyBatis Generator. This method corresponds to the
		 * database table preorder
		 *
		 * @mbg.generated
		 */
		public String getValue() {
			return this.column;
		}

		/**
		 * This method was generated by MyBatis Generator. This method corresponds to the
		 * database table preorder
		 *
		 * @mbg.generated
		 */
		public String getJavaProperty() {
			return this.javaProperty;
		}

		/**
		 * This method was generated by MyBatis Generator. This method corresponds to the
		 * database table preorder
		 *
		 * @mbg.generated
		 */
		public String getJdbcType() {
			return this.jdbcType;
		}

		/**
		 * This method was generated by MyBatis Generator. This method corresponds to the
		 * database table preorder
		 *
		 * @mbg.generated
		 */
		Column(String column, String javaProperty, String jdbcType,
				boolean isColumnNameDelimited) {
			this.column = column;
			this.javaProperty = javaProperty;
			this.jdbcType = jdbcType;
			this.isColumnNameDelimited = isColumnNameDelimited;
		}

		/**
		 * This method was generated by MyBatis Generator. This method corresponds to the
		 * database table preorder
		 *
		 * @mbg.generated
		 */
		public String desc() {
			return this.getEscapedColumnName() + " DESC";
		}

		/**
		 * This method was generated by MyBatis Generator. This method corresponds to the
		 * database table preorder
		 *
		 * @mbg.generated
		 */
		public String asc() {
			return this.getEscapedColumnName() + " ASC";
		}

		/**
		 * This method was generated by MyBatis Generator. This method corresponds to the
		 * database table preorder
		 *
		 * @mbg.generated
		 */
		public static Column[] excludes(Column... excludes) {
			ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
			if (excludes != null && excludes.length > 0) {
				columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
			}
			return columns.toArray(new Column[] {});
		}

		/**
		 * This method was generated by MyBatis Generator. This method corresponds to the
		 * database table preorder
		 *
		 * @mbg.generated
		 */
		public static Column[] all() {
			return Column.values();
		}

		/**
		 * This method was generated by MyBatis Generator. This method corresponds to the
		 * database table preorder
		 *
		 * @mbg.generated
		 */
		public String getEscapedColumnName() {
			if (this.isColumnNameDelimited) {
				return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column)
						.append(ENDING_DELIMITER).toString();
			}
			else {
				return this.column;
			}
		}

		/**
		 * This method was generated by MyBatis Generator. This method corresponds to the
		 * database table preorder
		 *
		 * @mbg.generated
		 */
		public String getAliasedEscapedColumnName() {
			return this.getEscapedColumnName();
		}

	}

}
