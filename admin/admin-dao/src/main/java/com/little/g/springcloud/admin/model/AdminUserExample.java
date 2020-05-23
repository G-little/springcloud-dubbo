package com.little.g.springcloud.admin.model;

import java.util.ArrayList;
import java.util.List;

public class AdminUserExample {

	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public AdminUserExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	public boolean isDistinct() {
		return distinct;
	}

	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	protected abstract static class GeneratedCriteria {

		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<Criterion>();
		}

		public boolean isValid() {
			return criteria.size() > 0;
		}

		public List<Criterion> getAllCriteria() {
			return criteria;
		}

		public List<Criterion> getCriteria() {
			return criteria;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}

		protected void addCriterion(String condition, Object value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1, Object value2,
				String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException(
						"Between values for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andIdIsNull() {
			addCriterion("id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andRealNameIsNull() {
			addCriterion("real_name is null");
			return (Criteria) this;
		}

		public Criteria andRealNameIsNotNull() {
			addCriterion("real_name is not null");
			return (Criteria) this;
		}

		public Criteria andRealNameEqualTo(String value) {
			addCriterion("real_name =", value, "realName");
			return (Criteria) this;
		}

		public Criteria andRealNameNotEqualTo(String value) {
			addCriterion("real_name <>", value, "realName");
			return (Criteria) this;
		}

		public Criteria andRealNameGreaterThan(String value) {
			addCriterion("real_name >", value, "realName");
			return (Criteria) this;
		}

		public Criteria andRealNameGreaterThanOrEqualTo(String value) {
			addCriterion("real_name >=", value, "realName");
			return (Criteria) this;
		}

		public Criteria andRealNameLessThan(String value) {
			addCriterion("real_name <", value, "realName");
			return (Criteria) this;
		}

		public Criteria andRealNameLessThanOrEqualTo(String value) {
			addCriterion("real_name <=", value, "realName");
			return (Criteria) this;
		}

		public Criteria andRealNameLike(String value) {
			addCriterion("real_name like", value, "realName");
			return (Criteria) this;
		}

		public Criteria andRealNameNotLike(String value) {
			addCriterion("real_name not like", value, "realName");
			return (Criteria) this;
		}

		public Criteria andRealNameIn(List<String> values) {
			addCriterion("real_name in", values, "realName");
			return (Criteria) this;
		}

		public Criteria andRealNameNotIn(List<String> values) {
			addCriterion("real_name not in", values, "realName");
			return (Criteria) this;
		}

		public Criteria andRealNameBetween(String value1, String value2) {
			addCriterion("real_name between", value1, value2, "realName");
			return (Criteria) this;
		}

		public Criteria andRealNameNotBetween(String value1, String value2) {
			addCriterion("real_name not between", value1, value2, "realName");
			return (Criteria) this;
		}

		public Criteria andGenderIsNull() {
			addCriterion("gender is null");
			return (Criteria) this;
		}

		public Criteria andGenderIsNotNull() {
			addCriterion("gender is not null");
			return (Criteria) this;
		}

		public Criteria andGenderEqualTo(Byte value) {
			addCriterion("gender =", value, "gender");
			return (Criteria) this;
		}

		public Criteria andGenderNotEqualTo(Byte value) {
			addCriterion("gender <>", value, "gender");
			return (Criteria) this;
		}

		public Criteria andGenderGreaterThan(Byte value) {
			addCriterion("gender >", value, "gender");
			return (Criteria) this;
		}

		public Criteria andGenderGreaterThanOrEqualTo(Byte value) {
			addCriterion("gender >=", value, "gender");
			return (Criteria) this;
		}

		public Criteria andGenderLessThan(Byte value) {
			addCriterion("gender <", value, "gender");
			return (Criteria) this;
		}

		public Criteria andGenderLessThanOrEqualTo(Byte value) {
			addCriterion("gender <=", value, "gender");
			return (Criteria) this;
		}

		public Criteria andGenderIn(List<Byte> values) {
			addCriterion("gender in", values, "gender");
			return (Criteria) this;
		}

		public Criteria andGenderNotIn(List<Byte> values) {
			addCriterion("gender not in", values, "gender");
			return (Criteria) this;
		}

		public Criteria andGenderBetween(Byte value1, Byte value2) {
			addCriterion("gender between", value1, value2, "gender");
			return (Criteria) this;
		}

		public Criteria andGenderNotBetween(Byte value1, Byte value2) {
			addCriterion("gender not between", value1, value2, "gender");
			return (Criteria) this;
		}

		public Criteria andCardIdIsNull() {
			addCriterion("card_id is null");
			return (Criteria) this;
		}

		public Criteria andCardIdIsNotNull() {
			addCriterion("card_id is not null");
			return (Criteria) this;
		}

		public Criteria andCardIdEqualTo(String value) {
			addCriterion("card_id =", value, "cardId");
			return (Criteria) this;
		}

		public Criteria andCardIdNotEqualTo(String value) {
			addCriterion("card_id <>", value, "cardId");
			return (Criteria) this;
		}

		public Criteria andCardIdGreaterThan(String value) {
			addCriterion("card_id >", value, "cardId");
			return (Criteria) this;
		}

		public Criteria andCardIdGreaterThanOrEqualTo(String value) {
			addCriterion("card_id >=", value, "cardId");
			return (Criteria) this;
		}

		public Criteria andCardIdLessThan(String value) {
			addCriterion("card_id <", value, "cardId");
			return (Criteria) this;
		}

		public Criteria andCardIdLessThanOrEqualTo(String value) {
			addCriterion("card_id <=", value, "cardId");
			return (Criteria) this;
		}

		public Criteria andCardIdLike(String value) {
			addCriterion("card_id like", value, "cardId");
			return (Criteria) this;
		}

		public Criteria andCardIdNotLike(String value) {
			addCriterion("card_id not like", value, "cardId");
			return (Criteria) this;
		}

		public Criteria andCardIdIn(List<String> values) {
			addCriterion("card_id in", values, "cardId");
			return (Criteria) this;
		}

		public Criteria andCardIdNotIn(List<String> values) {
			addCriterion("card_id not in", values, "cardId");
			return (Criteria) this;
		}

		public Criteria andCardIdBetween(String value1, String value2) {
			addCriterion("card_id between", value1, value2, "cardId");
			return (Criteria) this;
		}

		public Criteria andCardIdNotBetween(String value1, String value2) {
			addCriterion("card_id not between", value1, value2, "cardId");
			return (Criteria) this;
		}

		public Criteria andBirthdayIsNull() {
			addCriterion("birthday is null");
			return (Criteria) this;
		}

		public Criteria andBirthdayIsNotNull() {
			addCriterion("birthday is not null");
			return (Criteria) this;
		}

		public Criteria andBirthdayEqualTo(Long value) {
			addCriterion("birthday =", value, "birthday");
			return (Criteria) this;
		}

		public Criteria andBirthdayNotEqualTo(Long value) {
			addCriterion("birthday <>", value, "birthday");
			return (Criteria) this;
		}

		public Criteria andBirthdayGreaterThan(Long value) {
			addCriterion("birthday >", value, "birthday");
			return (Criteria) this;
		}

		public Criteria andBirthdayGreaterThanOrEqualTo(Long value) {
			addCriterion("birthday >=", value, "birthday");
			return (Criteria) this;
		}

		public Criteria andBirthdayLessThan(Long value) {
			addCriterion("birthday <", value, "birthday");
			return (Criteria) this;
		}

		public Criteria andBirthdayLessThanOrEqualTo(Long value) {
			addCriterion("birthday <=", value, "birthday");
			return (Criteria) this;
		}

		public Criteria andBirthdayIn(List<Long> values) {
			addCriterion("birthday in", values, "birthday");
			return (Criteria) this;
		}

		public Criteria andBirthdayNotIn(List<Long> values) {
			addCriterion("birthday not in", values, "birthday");
			return (Criteria) this;
		}

		public Criteria andBirthdayBetween(Long value1, Long value2) {
			addCriterion("birthday between", value1, value2, "birthday");
			return (Criteria) this;
		}

		public Criteria andBirthdayNotBetween(Long value1, Long value2) {
			addCriterion("birthday not between", value1, value2, "birthday");
			return (Criteria) this;
		}

		public Criteria andWxNumIsNull() {
			addCriterion("wx_num is null");
			return (Criteria) this;
		}

		public Criteria andWxNumIsNotNull() {
			addCriterion("wx_num is not null");
			return (Criteria) this;
		}

		public Criteria andWxNumEqualTo(String value) {
			addCriterion("wx_num =", value, "wxNum");
			return (Criteria) this;
		}

		public Criteria andWxNumNotEqualTo(String value) {
			addCriterion("wx_num <>", value, "wxNum");
			return (Criteria) this;
		}

		public Criteria andWxNumGreaterThan(String value) {
			addCriterion("wx_num >", value, "wxNum");
			return (Criteria) this;
		}

		public Criteria andWxNumGreaterThanOrEqualTo(String value) {
			addCriterion("wx_num >=", value, "wxNum");
			return (Criteria) this;
		}

		public Criteria andWxNumLessThan(String value) {
			addCriterion("wx_num <", value, "wxNum");
			return (Criteria) this;
		}

		public Criteria andWxNumLessThanOrEqualTo(String value) {
			addCriterion("wx_num <=", value, "wxNum");
			return (Criteria) this;
		}

		public Criteria andWxNumLike(String value) {
			addCriterion("wx_num like", value, "wxNum");
			return (Criteria) this;
		}

		public Criteria andWxNumNotLike(String value) {
			addCriterion("wx_num not like", value, "wxNum");
			return (Criteria) this;
		}

		public Criteria andWxNumIn(List<String> values) {
			addCriterion("wx_num in", values, "wxNum");
			return (Criteria) this;
		}

		public Criteria andWxNumNotIn(List<String> values) {
			addCriterion("wx_num not in", values, "wxNum");
			return (Criteria) this;
		}

		public Criteria andWxNumBetween(String value1, String value2) {
			addCriterion("wx_num between", value1, value2, "wxNum");
			return (Criteria) this;
		}

		public Criteria andWxNumNotBetween(String value1, String value2) {
			addCriterion("wx_num not between", value1, value2, "wxNum");
			return (Criteria) this;
		}

		public Criteria andMobileIsNull() {
			addCriterion("mobile is null");
			return (Criteria) this;
		}

		public Criteria andMobileIsNotNull() {
			addCriterion("mobile is not null");
			return (Criteria) this;
		}

		public Criteria andMobileEqualTo(String value) {
			addCriterion("mobile =", value, "mobile");
			return (Criteria) this;
		}

		public Criteria andMobileNotEqualTo(String value) {
			addCriterion("mobile <>", value, "mobile");
			return (Criteria) this;
		}

		public Criteria andMobileGreaterThan(String value) {
			addCriterion("mobile >", value, "mobile");
			return (Criteria) this;
		}

		public Criteria andMobileGreaterThanOrEqualTo(String value) {
			addCriterion("mobile >=", value, "mobile");
			return (Criteria) this;
		}

		public Criteria andMobileLessThan(String value) {
			addCriterion("mobile <", value, "mobile");
			return (Criteria) this;
		}

		public Criteria andMobileLessThanOrEqualTo(String value) {
			addCriterion("mobile <=", value, "mobile");
			return (Criteria) this;
		}

		public Criteria andMobileLike(String value) {
			addCriterion("mobile like", value, "mobile");
			return (Criteria) this;
		}

		public Criteria andMobileNotLike(String value) {
			addCriterion("mobile not like", value, "mobile");
			return (Criteria) this;
		}

		public Criteria andMobileIn(List<String> values) {
			addCriterion("mobile in", values, "mobile");
			return (Criteria) this;
		}

		public Criteria andMobileNotIn(List<String> values) {
			addCriterion("mobile not in", values, "mobile");
			return (Criteria) this;
		}

		public Criteria andMobileBetween(String value1, String value2) {
			addCriterion("mobile between", value1, value2, "mobile");
			return (Criteria) this;
		}

		public Criteria andMobileNotBetween(String value1, String value2) {
			addCriterion("mobile not between", value1, value2, "mobile");
			return (Criteria) this;
		}

		public Criteria andStoreIsNull() {
			addCriterion("store is null");
			return (Criteria) this;
		}

		public Criteria andStoreIsNotNull() {
			addCriterion("store is not null");
			return (Criteria) this;
		}

		public Criteria andStoreEqualTo(Integer value) {
			addCriterion("store =", value, "store");
			return (Criteria) this;
		}

		public Criteria andStoreNotEqualTo(Integer value) {
			addCriterion("store <>", value, "store");
			return (Criteria) this;
		}

		public Criteria andStoreGreaterThan(Integer value) {
			addCriterion("store >", value, "store");
			return (Criteria) this;
		}

		public Criteria andStoreGreaterThanOrEqualTo(Integer value) {
			addCriterion("store >=", value, "store");
			return (Criteria) this;
		}

		public Criteria andStoreLessThan(Integer value) {
			addCriterion("store <", value, "store");
			return (Criteria) this;
		}

		public Criteria andStoreLessThanOrEqualTo(Integer value) {
			addCriterion("store <=", value, "store");
			return (Criteria) this;
		}

		public Criteria andStoreIn(List<Integer> values) {
			addCriterion("store in", values, "store");
			return (Criteria) this;
		}

		public Criteria andStoreNotIn(List<Integer> values) {
			addCriterion("store not in", values, "store");
			return (Criteria) this;
		}

		public Criteria andStoreBetween(Integer value1, Integer value2) {
			addCriterion("store between", value1, value2, "store");
			return (Criteria) this;
		}

		public Criteria andStoreNotBetween(Integer value1, Integer value2) {
			addCriterion("store not between", value1, value2, "store");
			return (Criteria) this;
		}

		public Criteria andContactNameIsNull() {
			addCriterion("contact_name is null");
			return (Criteria) this;
		}

		public Criteria andContactNameIsNotNull() {
			addCriterion("contact_name is not null");
			return (Criteria) this;
		}

		public Criteria andContactNameEqualTo(String value) {
			addCriterion("contact_name =", value, "contactName");
			return (Criteria) this;
		}

		public Criteria andContactNameNotEqualTo(String value) {
			addCriterion("contact_name <>", value, "contactName");
			return (Criteria) this;
		}

		public Criteria andContactNameGreaterThan(String value) {
			addCriterion("contact_name >", value, "contactName");
			return (Criteria) this;
		}

		public Criteria andContactNameGreaterThanOrEqualTo(String value) {
			addCriterion("contact_name >=", value, "contactName");
			return (Criteria) this;
		}

		public Criteria andContactNameLessThan(String value) {
			addCriterion("contact_name <", value, "contactName");
			return (Criteria) this;
		}

		public Criteria andContactNameLessThanOrEqualTo(String value) {
			addCriterion("contact_name <=", value, "contactName");
			return (Criteria) this;
		}

		public Criteria andContactNameLike(String value) {
			addCriterion("contact_name like", value, "contactName");
			return (Criteria) this;
		}

		public Criteria andContactNameNotLike(String value) {
			addCriterion("contact_name not like", value, "contactName");
			return (Criteria) this;
		}

		public Criteria andContactNameIn(List<String> values) {
			addCriterion("contact_name in", values, "contactName");
			return (Criteria) this;
		}

		public Criteria andContactNameNotIn(List<String> values) {
			addCriterion("contact_name not in", values, "contactName");
			return (Criteria) this;
		}

		public Criteria andContactNameBetween(String value1, String value2) {
			addCriterion("contact_name between", value1, value2, "contactName");
			return (Criteria) this;
		}

		public Criteria andContactNameNotBetween(String value1, String value2) {
			addCriterion("contact_name not between", value1, value2, "contactName");
			return (Criteria) this;
		}

		public Criteria andContactMobileIsNull() {
			addCriterion("contact_mobile is null");
			return (Criteria) this;
		}

		public Criteria andContactMobileIsNotNull() {
			addCriterion("contact_mobile is not null");
			return (Criteria) this;
		}

		public Criteria andContactMobileEqualTo(String value) {
			addCriterion("contact_mobile =", value, "contactMobile");
			return (Criteria) this;
		}

		public Criteria andContactMobileNotEqualTo(String value) {
			addCriterion("contact_mobile <>", value, "contactMobile");
			return (Criteria) this;
		}

		public Criteria andContactMobileGreaterThan(String value) {
			addCriterion("contact_mobile >", value, "contactMobile");
			return (Criteria) this;
		}

		public Criteria andContactMobileGreaterThanOrEqualTo(String value) {
			addCriterion("contact_mobile >=", value, "contactMobile");
			return (Criteria) this;
		}

		public Criteria andContactMobileLessThan(String value) {
			addCriterion("contact_mobile <", value, "contactMobile");
			return (Criteria) this;
		}

		public Criteria andContactMobileLessThanOrEqualTo(String value) {
			addCriterion("contact_mobile <=", value, "contactMobile");
			return (Criteria) this;
		}

		public Criteria andContactMobileLike(String value) {
			addCriterion("contact_mobile like", value, "contactMobile");
			return (Criteria) this;
		}

		public Criteria andContactMobileNotLike(String value) {
			addCriterion("contact_mobile not like", value, "contactMobile");
			return (Criteria) this;
		}

		public Criteria andContactMobileIn(List<String> values) {
			addCriterion("contact_mobile in", values, "contactMobile");
			return (Criteria) this;
		}

		public Criteria andContactMobileNotIn(List<String> values) {
			addCriterion("contact_mobile not in", values, "contactMobile");
			return (Criteria) this;
		}

		public Criteria andContactMobileBetween(String value1, String value2) {
			addCriterion("contact_mobile between", value1, value2, "contactMobile");
			return (Criteria) this;
		}

		public Criteria andContactMobileNotBetween(String value1, String value2) {
			addCriterion("contact_mobile not between", value1, value2, "contactMobile");
			return (Criteria) this;
		}

		public Criteria andRelationshipIsNull() {
			addCriterion("relationship is null");
			return (Criteria) this;
		}

		public Criteria andRelationshipIsNotNull() {
			addCriterion("relationship is not null");
			return (Criteria) this;
		}

		public Criteria andRelationshipEqualTo(Byte value) {
			addCriterion("relationship =", value, "relationship");
			return (Criteria) this;
		}

		public Criteria andRelationshipNotEqualTo(Byte value) {
			addCriterion("relationship <>", value, "relationship");
			return (Criteria) this;
		}

		public Criteria andRelationshipGreaterThan(Byte value) {
			addCriterion("relationship >", value, "relationship");
			return (Criteria) this;
		}

		public Criteria andRelationshipGreaterThanOrEqualTo(Byte value) {
			addCriterion("relationship >=", value, "relationship");
			return (Criteria) this;
		}

		public Criteria andRelationshipLessThan(Byte value) {
			addCriterion("relationship <", value, "relationship");
			return (Criteria) this;
		}

		public Criteria andRelationshipLessThanOrEqualTo(Byte value) {
			addCriterion("relationship <=", value, "relationship");
			return (Criteria) this;
		}

		public Criteria andRelationshipIn(List<Byte> values) {
			addCriterion("relationship in", values, "relationship");
			return (Criteria) this;
		}

		public Criteria andRelationshipNotIn(List<Byte> values) {
			addCriterion("relationship not in", values, "relationship");
			return (Criteria) this;
		}

		public Criteria andRelationshipBetween(Byte value1, Byte value2) {
			addCriterion("relationship between", value1, value2, "relationship");
			return (Criteria) this;
		}

		public Criteria andRelationshipNotBetween(Byte value1, Byte value2) {
			addCriterion("relationship not between", value1, value2, "relationship");
			return (Criteria) this;
		}

		public Criteria andIdCardImg1IsNull() {
			addCriterion("id_card_img1 is null");
			return (Criteria) this;
		}

		public Criteria andIdCardImg1IsNotNull() {
			addCriterion("id_card_img1 is not null");
			return (Criteria) this;
		}

		public Criteria andIdCardImg1EqualTo(String value) {
			addCriterion("id_card_img1 =", value, "idCardImg1");
			return (Criteria) this;
		}

		public Criteria andIdCardImg1NotEqualTo(String value) {
			addCriterion("id_card_img1 <>", value, "idCardImg1");
			return (Criteria) this;
		}

		public Criteria andIdCardImg1GreaterThan(String value) {
			addCriterion("id_card_img1 >", value, "idCardImg1");
			return (Criteria) this;
		}

		public Criteria andIdCardImg1GreaterThanOrEqualTo(String value) {
			addCriterion("id_card_img1 >=", value, "idCardImg1");
			return (Criteria) this;
		}

		public Criteria andIdCardImg1LessThan(String value) {
			addCriterion("id_card_img1 <", value, "idCardImg1");
			return (Criteria) this;
		}

		public Criteria andIdCardImg1LessThanOrEqualTo(String value) {
			addCriterion("id_card_img1 <=", value, "idCardImg1");
			return (Criteria) this;
		}

		public Criteria andIdCardImg1Like(String value) {
			addCriterion("id_card_img1 like", value, "idCardImg1");
			return (Criteria) this;
		}

		public Criteria andIdCardImg1NotLike(String value) {
			addCriterion("id_card_img1 not like", value, "idCardImg1");
			return (Criteria) this;
		}

		public Criteria andIdCardImg1In(List<String> values) {
			addCriterion("id_card_img1 in", values, "idCardImg1");
			return (Criteria) this;
		}

		public Criteria andIdCardImg1NotIn(List<String> values) {
			addCriterion("id_card_img1 not in", values, "idCardImg1");
			return (Criteria) this;
		}

		public Criteria andIdCardImg1Between(String value1, String value2) {
			addCriterion("id_card_img1 between", value1, value2, "idCardImg1");
			return (Criteria) this;
		}

		public Criteria andIdCardImg1NotBetween(String value1, String value2) {
			addCriterion("id_card_img1 not between", value1, value2, "idCardImg1");
			return (Criteria) this;
		}

		public Criteria andIdCardImg2IsNull() {
			addCriterion("id_card_img2 is null");
			return (Criteria) this;
		}

		public Criteria andIdCardImg2IsNotNull() {
			addCriterion("id_card_img2 is not null");
			return (Criteria) this;
		}

		public Criteria andIdCardImg2EqualTo(String value) {
			addCriterion("id_card_img2 =", value, "idCardImg2");
			return (Criteria) this;
		}

		public Criteria andIdCardImg2NotEqualTo(String value) {
			addCriterion("id_card_img2 <>", value, "idCardImg2");
			return (Criteria) this;
		}

		public Criteria andIdCardImg2GreaterThan(String value) {
			addCriterion("id_card_img2 >", value, "idCardImg2");
			return (Criteria) this;
		}

		public Criteria andIdCardImg2GreaterThanOrEqualTo(String value) {
			addCriterion("id_card_img2 >=", value, "idCardImg2");
			return (Criteria) this;
		}

		public Criteria andIdCardImg2LessThan(String value) {
			addCriterion("id_card_img2 <", value, "idCardImg2");
			return (Criteria) this;
		}

		public Criteria andIdCardImg2LessThanOrEqualTo(String value) {
			addCriterion("id_card_img2 <=", value, "idCardImg2");
			return (Criteria) this;
		}

		public Criteria andIdCardImg2Like(String value) {
			addCriterion("id_card_img2 like", value, "idCardImg2");
			return (Criteria) this;
		}

		public Criteria andIdCardImg2NotLike(String value) {
			addCriterion("id_card_img2 not like", value, "idCardImg2");
			return (Criteria) this;
		}

		public Criteria andIdCardImg2In(List<String> values) {
			addCriterion("id_card_img2 in", values, "idCardImg2");
			return (Criteria) this;
		}

		public Criteria andIdCardImg2NotIn(List<String> values) {
			addCriterion("id_card_img2 not in", values, "idCardImg2");
			return (Criteria) this;
		}

		public Criteria andIdCardImg2Between(String value1, String value2) {
			addCriterion("id_card_img2 between", value1, value2, "idCardImg2");
			return (Criteria) this;
		}

		public Criteria andIdCardImg2NotBetween(String value1, String value2) {
			addCriterion("id_card_img2 not between", value1, value2, "idCardImg2");
			return (Criteria) this;
		}

		public Criteria andPasswordIsNull() {
			addCriterion("`password` is null");
			return (Criteria) this;
		}

		public Criteria andPasswordIsNotNull() {
			addCriterion("`password` is not null");
			return (Criteria) this;
		}

		public Criteria andPasswordEqualTo(String value) {
			addCriterion("`password` =", value, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordNotEqualTo(String value) {
			addCriterion("`password` <>", value, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordGreaterThan(String value) {
			addCriterion("`password` >", value, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordGreaterThanOrEqualTo(String value) {
			addCriterion("`password` >=", value, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordLessThan(String value) {
			addCriterion("`password` <", value, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordLessThanOrEqualTo(String value) {
			addCriterion("`password` <=", value, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordLike(String value) {
			addCriterion("`password` like", value, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordNotLike(String value) {
			addCriterion("`password` not like", value, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordIn(List<String> values) {
			addCriterion("`password` in", values, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordNotIn(List<String> values) {
			addCriterion("`password` not in", values, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordBetween(String value1, String value2) {
			addCriterion("`password` between", value1, value2, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordNotBetween(String value1, String value2) {
			addCriterion("`password` not between", value1, value2, "password");
			return (Criteria) this;
		}

		public Criteria andBelongToIsNull() {
			addCriterion("belong_to is null");
			return (Criteria) this;
		}

		public Criteria andBelongToIsNotNull() {
			addCriterion("belong_to is not null");
			return (Criteria) this;
		}

		public Criteria andBelongToEqualTo(Byte value) {
			addCriterion("belong_to =", value, "belongTo");
			return (Criteria) this;
		}

		public Criteria andBelongToNotEqualTo(Byte value) {
			addCriterion("belong_to <>", value, "belongTo");
			return (Criteria) this;
		}

		public Criteria andBelongToGreaterThan(Byte value) {
			addCriterion("belong_to >", value, "belongTo");
			return (Criteria) this;
		}

		public Criteria andBelongToGreaterThanOrEqualTo(Byte value) {
			addCriterion("belong_to >=", value, "belongTo");
			return (Criteria) this;
		}

		public Criteria andBelongToLessThan(Byte value) {
			addCriterion("belong_to <", value, "belongTo");
			return (Criteria) this;
		}

		public Criteria andBelongToLessThanOrEqualTo(Byte value) {
			addCriterion("belong_to <=", value, "belongTo");
			return (Criteria) this;
		}

		public Criteria andBelongToIn(List<Byte> values) {
			addCriterion("belong_to in", values, "belongTo");
			return (Criteria) this;
		}

		public Criteria andBelongToNotIn(List<Byte> values) {
			addCriterion("belong_to not in", values, "belongTo");
			return (Criteria) this;
		}

		public Criteria andBelongToBetween(Byte value1, Byte value2) {
			addCriterion("belong_to between", value1, value2, "belongTo");
			return (Criteria) this;
		}

		public Criteria andBelongToNotBetween(Byte value1, Byte value2) {
			addCriterion("belong_to not between", value1, value2, "belongTo");
			return (Criteria) this;
		}

		public Criteria andStatusIsNull() {
			addCriterion("`status` is null");
			return (Criteria) this;
		}

		public Criteria andStatusIsNotNull() {
			addCriterion("`status` is not null");
			return (Criteria) this;
		}

		public Criteria andStatusEqualTo(Byte value) {
			addCriterion("`status` =", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotEqualTo(Byte value) {
			addCriterion("`status` <>", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusGreaterThan(Byte value) {
			addCriterion("`status` >", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
			addCriterion("`status` >=", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLessThan(Byte value) {
			addCriterion("`status` <", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLessThanOrEqualTo(Byte value) {
			addCriterion("`status` <=", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusIn(List<Byte> values) {
			addCriterion("`status` in", values, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotIn(List<Byte> values) {
			addCriterion("`status` not in", values, "status");
			return (Criteria) this;
		}

		public Criteria andStatusBetween(Byte value1, Byte value2) {
			addCriterion("`status` between", value1, value2, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotBetween(Byte value1, Byte value2) {
			addCriterion("`status` not between", value1, value2, "status");
			return (Criteria) this;
		}

		public Criteria andPrivilegeIsNull() {
			addCriterion("privilege is null");
			return (Criteria) this;
		}

		public Criteria andPrivilegeIsNotNull() {
			addCriterion("privilege is not null");
			return (Criteria) this;
		}

		public Criteria andPrivilegeEqualTo(String value) {
			addCriterion("privilege =", value, "privilege");
			return (Criteria) this;
		}

		public Criteria andPrivilegeNotEqualTo(String value) {
			addCriterion("privilege <>", value, "privilege");
			return (Criteria) this;
		}

		public Criteria andPrivilegeGreaterThan(String value) {
			addCriterion("privilege >", value, "privilege");
			return (Criteria) this;
		}

		public Criteria andPrivilegeGreaterThanOrEqualTo(String value) {
			addCriterion("privilege >=", value, "privilege");
			return (Criteria) this;
		}

		public Criteria andPrivilegeLessThan(String value) {
			addCriterion("privilege <", value, "privilege");
			return (Criteria) this;
		}

		public Criteria andPrivilegeLessThanOrEqualTo(String value) {
			addCriterion("privilege <=", value, "privilege");
			return (Criteria) this;
		}

		public Criteria andPrivilegeLike(String value) {
			addCriterion("privilege like", value, "privilege");
			return (Criteria) this;
		}

		public Criteria andPrivilegeNotLike(String value) {
			addCriterion("privilege not like", value, "privilege");
			return (Criteria) this;
		}

		public Criteria andPrivilegeIn(List<String> values) {
			addCriterion("privilege in", values, "privilege");
			return (Criteria) this;
		}

		public Criteria andPrivilegeNotIn(List<String> values) {
			addCriterion("privilege not in", values, "privilege");
			return (Criteria) this;
		}

		public Criteria andPrivilegeBetween(String value1, String value2) {
			addCriterion("privilege between", value1, value2, "privilege");
			return (Criteria) this;
		}

		public Criteria andPrivilegeNotBetween(String value1, String value2) {
			addCriterion("privilege not between", value1, value2, "privilege");
			return (Criteria) this;
		}

		public Criteria andTypeIsNull() {
			addCriterion("`type` is null");
			return (Criteria) this;
		}

		public Criteria andTypeIsNotNull() {
			addCriterion("`type` is not null");
			return (Criteria) this;
		}

		public Criteria andTypeEqualTo(Byte value) {
			addCriterion("`type` =", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotEqualTo(Byte value) {
			addCriterion("`type` <>", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeGreaterThan(Byte value) {
			addCriterion("`type` >", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeGreaterThanOrEqualTo(Byte value) {
			addCriterion("`type` >=", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLessThan(Byte value) {
			addCriterion("`type` <", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLessThanOrEqualTo(Byte value) {
			addCriterion("`type` <=", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeIn(List<Byte> values) {
			addCriterion("`type` in", values, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotIn(List<Byte> values) {
			addCriterion("`type` not in", values, "type");
			return (Criteria) this;
		}

		public Criteria andTypeBetween(Byte value1, Byte value2) {
			addCriterion("`type` between", value1, value2, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotBetween(Byte value1, Byte value2) {
			addCriterion("`type` not between", value1, value2, "type");
			return (Criteria) this;
		}

		public Criteria andCreateTimeIsNull() {
			addCriterion("create_time is null");
			return (Criteria) this;
		}

		public Criteria andCreateTimeIsNotNull() {
			addCriterion("create_time is not null");
			return (Criteria) this;
		}

		public Criteria andCreateTimeEqualTo(Long value) {
			addCriterion("create_time =", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeNotEqualTo(Long value) {
			addCriterion("create_time <>", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeGreaterThan(Long value) {
			addCriterion("create_time >", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeGreaterThanOrEqualTo(Long value) {
			addCriterion("create_time >=", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeLessThan(Long value) {
			addCriterion("create_time <", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeLessThanOrEqualTo(Long value) {
			addCriterion("create_time <=", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeIn(List<Long> values) {
			addCriterion("create_time in", values, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeNotIn(List<Long> values) {
			addCriterion("create_time not in", values, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeBetween(Long value1, Long value2) {
			addCriterion("create_time between", value1, value2, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeNotBetween(Long value1, Long value2) {
			addCriterion("create_time not between", value1, value2, "createTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeIsNull() {
			addCriterion("update_time is null");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeIsNotNull() {
			addCriterion("update_time is not null");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeEqualTo(Long value) {
			addCriterion("update_time =", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeNotEqualTo(Long value) {
			addCriterion("update_time <>", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeGreaterThan(Long value) {
			addCriterion("update_time >", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeGreaterThanOrEqualTo(Long value) {
			addCriterion("update_time >=", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeLessThan(Long value) {
			addCriterion("update_time <", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeLessThanOrEqualTo(Long value) {
			addCriterion("update_time <=", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeIn(List<Long> values) {
			addCriterion("update_time in", values, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeNotIn(List<Long> values) {
			addCriterion("update_time not in", values, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeBetween(Long value1, Long value2) {
			addCriterion("update_time between", value1, value2, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeNotBetween(Long value1, Long value2) {
			addCriterion("update_time not between", value1, value2, "updateTime");
			return (Criteria) this;
		}

		public Criteria andRoleIdIsNull() {
			addCriterion("role_id is null");
			return (Criteria) this;
		}

		public Criteria andRoleIdIsNotNull() {
			addCriterion("role_id is not null");
			return (Criteria) this;
		}

		public Criteria andRoleIdEqualTo(Integer value) {
			addCriterion("role_id =", value, "roleId");
			return (Criteria) this;
		}

		public Criteria andRoleIdNotEqualTo(Integer value) {
			addCriterion("role_id <>", value, "roleId");
			return (Criteria) this;
		}

		public Criteria andRoleIdGreaterThan(Integer value) {
			addCriterion("role_id >", value, "roleId");
			return (Criteria) this;
		}

		public Criteria andRoleIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("role_id >=", value, "roleId");
			return (Criteria) this;
		}

		public Criteria andRoleIdLessThan(Integer value) {
			addCriterion("role_id <", value, "roleId");
			return (Criteria) this;
		}

		public Criteria andRoleIdLessThanOrEqualTo(Integer value) {
			addCriterion("role_id <=", value, "roleId");
			return (Criteria) this;
		}

		public Criteria andRoleIdIn(List<Integer> values) {
			addCriterion("role_id in", values, "roleId");
			return (Criteria) this;
		}

		public Criteria andRoleIdNotIn(List<Integer> values) {
			addCriterion("role_id not in", values, "roleId");
			return (Criteria) this;
		}

		public Criteria andRoleIdBetween(Integer value1, Integer value2) {
			addCriterion("role_id between", value1, value2, "roleId");
			return (Criteria) this;
		}

		public Criteria andRoleIdNotBetween(Integer value1, Integer value2) {
			addCriterion("role_id not between", value1, value2, "roleId");
			return (Criteria) this;
		}

		public Criteria andRealNameLikeInsensitive(String value) {
			addCriterion("upper(real_name) like", value.toUpperCase(), "realName");
			return (Criteria) this;
		}

		public Criteria andCardIdLikeInsensitive(String value) {
			addCriterion("upper(card_id) like", value.toUpperCase(), "cardId");
			return (Criteria) this;
		}

		public Criteria andWxNumLikeInsensitive(String value) {
			addCriterion("upper(wx_num) like", value.toUpperCase(), "wxNum");
			return (Criteria) this;
		}

		public Criteria andMobileLikeInsensitive(String value) {
			addCriterion("upper(mobile) like", value.toUpperCase(), "mobile");
			return (Criteria) this;
		}

		public Criteria andContactNameLikeInsensitive(String value) {
			addCriterion("upper(contact_name) like", value.toUpperCase(), "contactName");
			return (Criteria) this;
		}

		public Criteria andContactMobileLikeInsensitive(String value) {
			addCriterion("upper(contact_mobile) like", value.toUpperCase(),
					"contactMobile");
			return (Criteria) this;
		}

		public Criteria andIdCardImg1LikeInsensitive(String value) {
			addCriterion("upper(id_card_img1) like", value.toUpperCase(), "idCardImg1");
			return (Criteria) this;
		}

		public Criteria andIdCardImg2LikeInsensitive(String value) {
			addCriterion("upper(id_card_img2) like", value.toUpperCase(), "idCardImg2");
			return (Criteria) this;
		}

		public Criteria andPasswordLikeInsensitive(String value) {
			addCriterion("upper(`password`) like", value.toUpperCase(), "password");
			return (Criteria) this;
		}

		public Criteria andPrivilegeLikeInsensitive(String value) {
			addCriterion("upper(privilege) like", value.toUpperCase(), "privilege");
			return (Criteria) this;
		}

	}

	public static class Criteria extends GeneratedCriteria {

		protected Criteria() {
			super();
		}

	}

	public static class Criterion {

		private String condition;

		private Object value;

		private Object secondValue;

		private boolean noValue;

		private boolean singleValue;

		private boolean betweenValue;

		private boolean listValue;

		private String typeHandler;

		public String getCondition() {
			return condition;
		}

		public Object getValue() {
			return value;
		}

		public Object getSecondValue() {
			return secondValue;
		}

		public boolean isNoValue() {
			return noValue;
		}

		public boolean isSingleValue() {
			return singleValue;
		}

		public boolean isBetweenValue() {
			return betweenValue;
		}

		public boolean isListValue() {
			return listValue;
		}

		public String getTypeHandler() {
			return typeHandler;
		}

		protected Criterion(String condition) {
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List<?>) {
				this.listValue = true;
			}
			else {
				this.singleValue = true;
			}
		}

		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}

		protected Criterion(String condition, Object value, Object secondValue,
				String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}

		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}

	}

}