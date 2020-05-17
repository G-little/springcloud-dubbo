package com.little.g.springcloud.sample.model;

import java.util.ArrayList;
import java.util.List;

public class AiAssistantExample {

	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public AiAssistantExample() {
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

		public Criteria andIdEqualTo(Long value) {
			addCriterion("id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Long value) {
			addCriterion("id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Long value) {
			addCriterion("id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Long value) {
			addCriterion("id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Long value) {
			addCriterion("id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Long value) {
			addCriterion("id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Long> values) {
			addCriterion("id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Long> values) {
			addCriterion("id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Long value1, Long value2) {
			addCriterion("id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Long value1, Long value2) {
			addCriterion("id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andUidIsNull() {
			addCriterion("`uid` is null");
			return (Criteria) this;
		}

		public Criteria andUidIsNotNull() {
			addCriterion("`uid` is not null");
			return (Criteria) this;
		}

		public Criteria andUidEqualTo(Long value) {
			addCriterion("`uid` =", value, "uid");
			return (Criteria) this;
		}

		public Criteria andUidNotEqualTo(Long value) {
			addCriterion("`uid` <>", value, "uid");
			return (Criteria) this;
		}

		public Criteria andUidGreaterThan(Long value) {
			addCriterion("`uid` >", value, "uid");
			return (Criteria) this;
		}

		public Criteria andUidGreaterThanOrEqualTo(Long value) {
			addCriterion("`uid` >=", value, "uid");
			return (Criteria) this;
		}

		public Criteria andUidLessThan(Long value) {
			addCriterion("`uid` <", value, "uid");
			return (Criteria) this;
		}

		public Criteria andUidLessThanOrEqualTo(Long value) {
			addCriterion("`uid` <=", value, "uid");
			return (Criteria) this;
		}

		public Criteria andUidIn(List<Long> values) {
			addCriterion("`uid` in", values, "uid");
			return (Criteria) this;
		}

		public Criteria andUidNotIn(List<Long> values) {
			addCriterion("`uid` not in", values, "uid");
			return (Criteria) this;
		}

		public Criteria andUidBetween(Long value1, Long value2) {
			addCriterion("`uid` between", value1, value2, "uid");
			return (Criteria) this;
		}

		public Criteria andUidNotBetween(Long value1, Long value2) {
			addCriterion("`uid` not between", value1, value2, "uid");
			return (Criteria) this;
		}

		public Criteria andRoleIsNull() {
			addCriterion("`role` is null");
			return (Criteria) this;
		}

		public Criteria andRoleIsNotNull() {
			addCriterion("`role` is not null");
			return (Criteria) this;
		}

		public Criteria andRoleEqualTo(Byte value) {
			addCriterion("`role` =", value, "role");
			return (Criteria) this;
		}

		public Criteria andRoleNotEqualTo(Byte value) {
			addCriterion("`role` <>", value, "role");
			return (Criteria) this;
		}

		public Criteria andRoleGreaterThan(Byte value) {
			addCriterion("`role` >", value, "role");
			return (Criteria) this;
		}

		public Criteria andRoleGreaterThanOrEqualTo(Byte value) {
			addCriterion("`role` >=", value, "role");
			return (Criteria) this;
		}

		public Criteria andRoleLessThan(Byte value) {
			addCriterion("`role` <", value, "role");
			return (Criteria) this;
		}

		public Criteria andRoleLessThanOrEqualTo(Byte value) {
			addCriterion("`role` <=", value, "role");
			return (Criteria) this;
		}

		public Criteria andRoleIn(List<Byte> values) {
			addCriterion("`role` in", values, "role");
			return (Criteria) this;
		}

		public Criteria andRoleNotIn(List<Byte> values) {
			addCriterion("`role` not in", values, "role");
			return (Criteria) this;
		}

		public Criteria andRoleBetween(Byte value1, Byte value2) {
			addCriterion("`role` between", value1, value2, "role");
			return (Criteria) this;
		}

		public Criteria andRoleNotBetween(Byte value1, Byte value2) {
			addCriterion("`role` not between", value1, value2, "role");
			return (Criteria) this;
		}

		public Criteria andRoleNameIsNull() {
			addCriterion("role_name is null");
			return (Criteria) this;
		}

		public Criteria andRoleNameIsNotNull() {
			addCriterion("role_name is not null");
			return (Criteria) this;
		}

		public Criteria andRoleNameEqualTo(String value) {
			addCriterion("role_name =", value, "roleName");
			return (Criteria) this;
		}

		public Criteria andRoleNameNotEqualTo(String value) {
			addCriterion("role_name <>", value, "roleName");
			return (Criteria) this;
		}

		public Criteria andRoleNameGreaterThan(String value) {
			addCriterion("role_name >", value, "roleName");
			return (Criteria) this;
		}

		public Criteria andRoleNameGreaterThanOrEqualTo(String value) {
			addCriterion("role_name >=", value, "roleName");
			return (Criteria) this;
		}

		public Criteria andRoleNameLessThan(String value) {
			addCriterion("role_name <", value, "roleName");
			return (Criteria) this;
		}

		public Criteria andRoleNameLessThanOrEqualTo(String value) {
			addCriterion("role_name <=", value, "roleName");
			return (Criteria) this;
		}

		public Criteria andRoleNameLike(String value) {
			addCriterion("role_name like", value, "roleName");
			return (Criteria) this;
		}

		public Criteria andRoleNameNotLike(String value) {
			addCriterion("role_name not like", value, "roleName");
			return (Criteria) this;
		}

		public Criteria andRoleNameIn(List<String> values) {
			addCriterion("role_name in", values, "roleName");
			return (Criteria) this;
		}

		public Criteria andRoleNameNotIn(List<String> values) {
			addCriterion("role_name not in", values, "roleName");
			return (Criteria) this;
		}

		public Criteria andRoleNameBetween(String value1, String value2) {
			addCriterion("role_name between", value1, value2, "roleName");
			return (Criteria) this;
		}

		public Criteria andRoleNameNotBetween(String value1, String value2) {
			addCriterion("role_name not between", value1, value2, "roleName");
			return (Criteria) this;
		}

		public Criteria andAvatarIsNull() {
			addCriterion("avatar is null");
			return (Criteria) this;
		}

		public Criteria andAvatarIsNotNull() {
			addCriterion("avatar is not null");
			return (Criteria) this;
		}

		public Criteria andAvatarEqualTo(String value) {
			addCriterion("avatar =", value, "avatar");
			return (Criteria) this;
		}

		public Criteria andAvatarNotEqualTo(String value) {
			addCriterion("avatar <>", value, "avatar");
			return (Criteria) this;
		}

		public Criteria andAvatarGreaterThan(String value) {
			addCriterion("avatar >", value, "avatar");
			return (Criteria) this;
		}

		public Criteria andAvatarGreaterThanOrEqualTo(String value) {
			addCriterion("avatar >=", value, "avatar");
			return (Criteria) this;
		}

		public Criteria andAvatarLessThan(String value) {
			addCriterion("avatar <", value, "avatar");
			return (Criteria) this;
		}

		public Criteria andAvatarLessThanOrEqualTo(String value) {
			addCriterion("avatar <=", value, "avatar");
			return (Criteria) this;
		}

		public Criteria andAvatarLike(String value) {
			addCriterion("avatar like", value, "avatar");
			return (Criteria) this;
		}

		public Criteria andAvatarNotLike(String value) {
			addCriterion("avatar not like", value, "avatar");
			return (Criteria) this;
		}

		public Criteria andAvatarIn(List<String> values) {
			addCriterion("avatar in", values, "avatar");
			return (Criteria) this;
		}

		public Criteria andAvatarNotIn(List<String> values) {
			addCriterion("avatar not in", values, "avatar");
			return (Criteria) this;
		}

		public Criteria andAvatarBetween(String value1, String value2) {
			addCriterion("avatar between", value1, value2, "avatar");
			return (Criteria) this;
		}

		public Criteria andAvatarNotBetween(String value1, String value2) {
			addCriterion("avatar not between", value1, value2, "avatar");
			return (Criteria) this;
		}

		public Criteria andSayHiIsNull() {
			addCriterion("say_hi is null");
			return (Criteria) this;
		}

		public Criteria andSayHiIsNotNull() {
			addCriterion("say_hi is not null");
			return (Criteria) this;
		}

		public Criteria andSayHiEqualTo(String value) {
			addCriterion("say_hi =", value, "sayHi");
			return (Criteria) this;
		}

		public Criteria andSayHiNotEqualTo(String value) {
			addCriterion("say_hi <>", value, "sayHi");
			return (Criteria) this;
		}

		public Criteria andSayHiGreaterThan(String value) {
			addCriterion("say_hi >", value, "sayHi");
			return (Criteria) this;
		}

		public Criteria andSayHiGreaterThanOrEqualTo(String value) {
			addCriterion("say_hi >=", value, "sayHi");
			return (Criteria) this;
		}

		public Criteria andSayHiLessThan(String value) {
			addCriterion("say_hi <", value, "sayHi");
			return (Criteria) this;
		}

		public Criteria andSayHiLessThanOrEqualTo(String value) {
			addCriterion("say_hi <=", value, "sayHi");
			return (Criteria) this;
		}

		public Criteria andSayHiLike(String value) {
			addCriterion("say_hi like", value, "sayHi");
			return (Criteria) this;
		}

		public Criteria andSayHiNotLike(String value) {
			addCriterion("say_hi not like", value, "sayHi");
			return (Criteria) this;
		}

		public Criteria andSayHiIn(List<String> values) {
			addCriterion("say_hi in", values, "sayHi");
			return (Criteria) this;
		}

		public Criteria andSayHiNotIn(List<String> values) {
			addCriterion("say_hi not in", values, "sayHi");
			return (Criteria) this;
		}

		public Criteria andSayHiBetween(String value1, String value2) {
			addCriterion("say_hi between", value1, value2, "sayHi");
			return (Criteria) this;
		}

		public Criteria andSayHiNotBetween(String value1, String value2) {
			addCriterion("say_hi not between", value1, value2, "sayHi");
			return (Criteria) this;
		}

		public Criteria andHiIdIsNull() {
			addCriterion("hi_id is null");
			return (Criteria) this;
		}

		public Criteria andHiIdIsNotNull() {
			addCriterion("hi_id is not null");
			return (Criteria) this;
		}

		public Criteria andHiIdEqualTo(Long value) {
			addCriterion("hi_id =", value, "hiId");
			return (Criteria) this;
		}

		public Criteria andHiIdNotEqualTo(Long value) {
			addCriterion("hi_id <>", value, "hiId");
			return (Criteria) this;
		}

		public Criteria andHiIdGreaterThan(Long value) {
			addCriterion("hi_id >", value, "hiId");
			return (Criteria) this;
		}

		public Criteria andHiIdGreaterThanOrEqualTo(Long value) {
			addCriterion("hi_id >=", value, "hiId");
			return (Criteria) this;
		}

		public Criteria andHiIdLessThan(Long value) {
			addCriterion("hi_id <", value, "hiId");
			return (Criteria) this;
		}

		public Criteria andHiIdLessThanOrEqualTo(Long value) {
			addCriterion("hi_id <=", value, "hiId");
			return (Criteria) this;
		}

		public Criteria andHiIdIn(List<Long> values) {
			addCriterion("hi_id in", values, "hiId");
			return (Criteria) this;
		}

		public Criteria andHiIdNotIn(List<Long> values) {
			addCriterion("hi_id not in", values, "hiId");
			return (Criteria) this;
		}

		public Criteria andHiIdBetween(Long value1, Long value2) {
			addCriterion("hi_id between", value1, value2, "hiId");
			return (Criteria) this;
		}

		public Criteria andHiIdNotBetween(Long value1, Long value2) {
			addCriterion("hi_id not between", value1, value2, "hiId");
			return (Criteria) this;
		}

		public Criteria andAiNameIsNull() {
			addCriterion("ai_name is null");
			return (Criteria) this;
		}

		public Criteria andAiNameIsNotNull() {
			addCriterion("ai_name is not null");
			return (Criteria) this;
		}

		public Criteria andAiNameEqualTo(String value) {
			addCriterion("ai_name =", value, "aiName");
			return (Criteria) this;
		}

		public Criteria andAiNameNotEqualTo(String value) {
			addCriterion("ai_name <>", value, "aiName");
			return (Criteria) this;
		}

		public Criteria andAiNameGreaterThan(String value) {
			addCriterion("ai_name >", value, "aiName");
			return (Criteria) this;
		}

		public Criteria andAiNameGreaterThanOrEqualTo(String value) {
			addCriterion("ai_name >=", value, "aiName");
			return (Criteria) this;
		}

		public Criteria andAiNameLessThan(String value) {
			addCriterion("ai_name <", value, "aiName");
			return (Criteria) this;
		}

		public Criteria andAiNameLessThanOrEqualTo(String value) {
			addCriterion("ai_name <=", value, "aiName");
			return (Criteria) this;
		}

		public Criteria andAiNameLike(String value) {
			addCriterion("ai_name like", value, "aiName");
			return (Criteria) this;
		}

		public Criteria andAiNameNotLike(String value) {
			addCriterion("ai_name not like", value, "aiName");
			return (Criteria) this;
		}

		public Criteria andAiNameIn(List<String> values) {
			addCriterion("ai_name in", values, "aiName");
			return (Criteria) this;
		}

		public Criteria andAiNameNotIn(List<String> values) {
			addCriterion("ai_name not in", values, "aiName");
			return (Criteria) this;
		}

		public Criteria andAiNameBetween(String value1, String value2) {
			addCriterion("ai_name between", value1, value2, "aiName");
			return (Criteria) this;
		}

		public Criteria andAiNameNotBetween(String value1, String value2) {
			addCriterion("ai_name not between", value1, value2, "aiName");
			return (Criteria) this;
		}

		public Criteria andMasterNameIsNull() {
			addCriterion("master_name is null");
			return (Criteria) this;
		}

		public Criteria andMasterNameIsNotNull() {
			addCriterion("master_name is not null");
			return (Criteria) this;
		}

		public Criteria andMasterNameEqualTo(String value) {
			addCriterion("master_name =", value, "masterName");
			return (Criteria) this;
		}

		public Criteria andMasterNameNotEqualTo(String value) {
			addCriterion("master_name <>", value, "masterName");
			return (Criteria) this;
		}

		public Criteria andMasterNameGreaterThan(String value) {
			addCriterion("master_name >", value, "masterName");
			return (Criteria) this;
		}

		public Criteria andMasterNameGreaterThanOrEqualTo(String value) {
			addCriterion("master_name >=", value, "masterName");
			return (Criteria) this;
		}

		public Criteria andMasterNameLessThan(String value) {
			addCriterion("master_name <", value, "masterName");
			return (Criteria) this;
		}

		public Criteria andMasterNameLessThanOrEqualTo(String value) {
			addCriterion("master_name <=", value, "masterName");
			return (Criteria) this;
		}

		public Criteria andMasterNameLike(String value) {
			addCriterion("master_name like", value, "masterName");
			return (Criteria) this;
		}

		public Criteria andMasterNameNotLike(String value) {
			addCriterion("master_name not like", value, "masterName");
			return (Criteria) this;
		}

		public Criteria andMasterNameIn(List<String> values) {
			addCriterion("master_name in", values, "masterName");
			return (Criteria) this;
		}

		public Criteria andMasterNameNotIn(List<String> values) {
			addCriterion("master_name not in", values, "masterName");
			return (Criteria) this;
		}

		public Criteria andMasterNameBetween(String value1, String value2) {
			addCriterion("master_name between", value1, value2, "masterName");
			return (Criteria) this;
		}

		public Criteria andMasterNameNotBetween(String value1, String value2) {
			addCriterion("master_name not between", value1, value2, "masterName");
			return (Criteria) this;
		}

		public Criteria andTimbreIsNull() {
			addCriterion("timbre is null");
			return (Criteria) this;
		}

		public Criteria andTimbreIsNotNull() {
			addCriterion("timbre is not null");
			return (Criteria) this;
		}

		public Criteria andTimbreEqualTo(Byte value) {
			addCriterion("timbre =", value, "timbre");
			return (Criteria) this;
		}

		public Criteria andTimbreNotEqualTo(Byte value) {
			addCriterion("timbre <>", value, "timbre");
			return (Criteria) this;
		}

		public Criteria andTimbreGreaterThan(Byte value) {
			addCriterion("timbre >", value, "timbre");
			return (Criteria) this;
		}

		public Criteria andTimbreGreaterThanOrEqualTo(Byte value) {
			addCriterion("timbre >=", value, "timbre");
			return (Criteria) this;
		}

		public Criteria andTimbreLessThan(Byte value) {
			addCriterion("timbre <", value, "timbre");
			return (Criteria) this;
		}

		public Criteria andTimbreLessThanOrEqualTo(Byte value) {
			addCriterion("timbre <=", value, "timbre");
			return (Criteria) this;
		}

		public Criteria andTimbreIn(List<Byte> values) {
			addCriterion("timbre in", values, "timbre");
			return (Criteria) this;
		}

		public Criteria andTimbreNotIn(List<Byte> values) {
			addCriterion("timbre not in", values, "timbre");
			return (Criteria) this;
		}

		public Criteria andTimbreBetween(Byte value1, Byte value2) {
			addCriterion("timbre between", value1, value2, "timbre");
			return (Criteria) this;
		}

		public Criteria andTimbreNotBetween(Byte value1, Byte value2) {
			addCriterion("timbre not between", value1, value2, "timbre");
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

		public Criteria andHeadAvatarIsNull() {
			addCriterion("head_avatar is null");
			return (Criteria) this;
		}

		public Criteria andHeadAvatarIsNotNull() {
			addCriterion("head_avatar is not null");
			return (Criteria) this;
		}

		public Criteria andHeadAvatarEqualTo(String value) {
			addCriterion("head_avatar =", value, "headAvatar");
			return (Criteria) this;
		}

		public Criteria andHeadAvatarNotEqualTo(String value) {
			addCriterion("head_avatar <>", value, "headAvatar");
			return (Criteria) this;
		}

		public Criteria andHeadAvatarGreaterThan(String value) {
			addCriterion("head_avatar >", value, "headAvatar");
			return (Criteria) this;
		}

		public Criteria andHeadAvatarGreaterThanOrEqualTo(String value) {
			addCriterion("head_avatar >=", value, "headAvatar");
			return (Criteria) this;
		}

		public Criteria andHeadAvatarLessThan(String value) {
			addCriterion("head_avatar <", value, "headAvatar");
			return (Criteria) this;
		}

		public Criteria andHeadAvatarLessThanOrEqualTo(String value) {
			addCriterion("head_avatar <=", value, "headAvatar");
			return (Criteria) this;
		}

		public Criteria andHeadAvatarLike(String value) {
			addCriterion("head_avatar like", value, "headAvatar");
			return (Criteria) this;
		}

		public Criteria andHeadAvatarNotLike(String value) {
			addCriterion("head_avatar not like", value, "headAvatar");
			return (Criteria) this;
		}

		public Criteria andHeadAvatarIn(List<String> values) {
			addCriterion("head_avatar in", values, "headAvatar");
			return (Criteria) this;
		}

		public Criteria andHeadAvatarNotIn(List<String> values) {
			addCriterion("head_avatar not in", values, "headAvatar");
			return (Criteria) this;
		}

		public Criteria andHeadAvatarBetween(String value1, String value2) {
			addCriterion("head_avatar between", value1, value2, "headAvatar");
			return (Criteria) this;
		}

		public Criteria andHeadAvatarNotBetween(String value1, String value2) {
			addCriterion("head_avatar not between", value1, value2, "headAvatar");
			return (Criteria) this;
		}

		public Criteria andSpeedIsNull() {
			addCriterion("speed is null");
			return (Criteria) this;
		}

		public Criteria andSpeedIsNotNull() {
			addCriterion("speed is not null");
			return (Criteria) this;
		}

		public Criteria andSpeedEqualTo(Integer value) {
			addCriterion("speed =", value, "speed");
			return (Criteria) this;
		}

		public Criteria andSpeedNotEqualTo(Integer value) {
			addCriterion("speed <>", value, "speed");
			return (Criteria) this;
		}

		public Criteria andSpeedGreaterThan(Integer value) {
			addCriterion("speed >", value, "speed");
			return (Criteria) this;
		}

		public Criteria andSpeedGreaterThanOrEqualTo(Integer value) {
			addCriterion("speed >=", value, "speed");
			return (Criteria) this;
		}

		public Criteria andSpeedLessThan(Integer value) {
			addCriterion("speed <", value, "speed");
			return (Criteria) this;
		}

		public Criteria andSpeedLessThanOrEqualTo(Integer value) {
			addCriterion("speed <=", value, "speed");
			return (Criteria) this;
		}

		public Criteria andSpeedIn(List<Integer> values) {
			addCriterion("speed in", values, "speed");
			return (Criteria) this;
		}

		public Criteria andSpeedNotIn(List<Integer> values) {
			addCriterion("speed not in", values, "speed");
			return (Criteria) this;
		}

		public Criteria andSpeedBetween(Integer value1, Integer value2) {
			addCriterion("speed between", value1, value2, "speed");
			return (Criteria) this;
		}

		public Criteria andSpeedNotBetween(Integer value1, Integer value2) {
			addCriterion("speed not between", value1, value2, "speed");
			return (Criteria) this;
		}

		public Criteria andAnniImgsIsNull() {
			addCriterion("anni_imgs is null");
			return (Criteria) this;
		}

		public Criteria andAnniImgsIsNotNull() {
			addCriterion("anni_imgs is not null");
			return (Criteria) this;
		}

		public Criteria andAnniImgsEqualTo(String value) {
			addCriterion("anni_imgs =", value, "anniImgs");
			return (Criteria) this;
		}

		public Criteria andAnniImgsNotEqualTo(String value) {
			addCriterion("anni_imgs <>", value, "anniImgs");
			return (Criteria) this;
		}

		public Criteria andAnniImgsGreaterThan(String value) {
			addCriterion("anni_imgs >", value, "anniImgs");
			return (Criteria) this;
		}

		public Criteria andAnniImgsGreaterThanOrEqualTo(String value) {
			addCriterion("anni_imgs >=", value, "anniImgs");
			return (Criteria) this;
		}

		public Criteria andAnniImgsLessThan(String value) {
			addCriterion("anni_imgs <", value, "anniImgs");
			return (Criteria) this;
		}

		public Criteria andAnniImgsLessThanOrEqualTo(String value) {
			addCriterion("anni_imgs <=", value, "anniImgs");
			return (Criteria) this;
		}

		public Criteria andAnniImgsLike(String value) {
			addCriterion("anni_imgs like", value, "anniImgs");
			return (Criteria) this;
		}

		public Criteria andAnniImgsNotLike(String value) {
			addCriterion("anni_imgs not like", value, "anniImgs");
			return (Criteria) this;
		}

		public Criteria andAnniImgsIn(List<String> values) {
			addCriterion("anni_imgs in", values, "anniImgs");
			return (Criteria) this;
		}

		public Criteria andAnniImgsNotIn(List<String> values) {
			addCriterion("anni_imgs not in", values, "anniImgs");
			return (Criteria) this;
		}

		public Criteria andAnniImgsBetween(String value1, String value2) {
			addCriterion("anni_imgs between", value1, value2, "anniImgs");
			return (Criteria) this;
		}

		public Criteria andAnniImgsNotBetween(String value1, String value2) {
			addCriterion("anni_imgs not between", value1, value2, "anniImgs");
			return (Criteria) this;
		}

		public Criteria andAnswerCountIsNull() {
			addCriterion("answer_count is null");
			return (Criteria) this;
		}

		public Criteria andAnswerCountIsNotNull() {
			addCriterion("answer_count is not null");
			return (Criteria) this;
		}

		public Criteria andAnswerCountEqualTo(Integer value) {
			addCriterion("answer_count =", value, "answerCount");
			return (Criteria) this;
		}

		public Criteria andAnswerCountNotEqualTo(Integer value) {
			addCriterion("answer_count <>", value, "answerCount");
			return (Criteria) this;
		}

		public Criteria andAnswerCountGreaterThan(Integer value) {
			addCriterion("answer_count >", value, "answerCount");
			return (Criteria) this;
		}

		public Criteria andAnswerCountGreaterThanOrEqualTo(Integer value) {
			addCriterion("answer_count >=", value, "answerCount");
			return (Criteria) this;
		}

		public Criteria andAnswerCountLessThan(Integer value) {
			addCriterion("answer_count <", value, "answerCount");
			return (Criteria) this;
		}

		public Criteria andAnswerCountLessThanOrEqualTo(Integer value) {
			addCriterion("answer_count <=", value, "answerCount");
			return (Criteria) this;
		}

		public Criteria andAnswerCountIn(List<Integer> values) {
			addCriterion("answer_count in", values, "answerCount");
			return (Criteria) this;
		}

		public Criteria andAnswerCountNotIn(List<Integer> values) {
			addCriterion("answer_count not in", values, "answerCount");
			return (Criteria) this;
		}

		public Criteria andAnswerCountBetween(Integer value1, Integer value2) {
			addCriterion("answer_count between", value1, value2, "answerCount");
			return (Criteria) this;
		}

		public Criteria andAnswerCountNotBetween(Integer value1, Integer value2) {
			addCriterion("answer_count not between", value1, value2, "answerCount");
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

		public Criteria andRoleNameLikeInsensitive(String value) {
			addCriterion("upper(role_name) like", value.toUpperCase(), "roleName");
			return (Criteria) this;
		}

		public Criteria andAvatarLikeInsensitive(String value) {
			addCriterion("upper(avatar) like", value.toUpperCase(), "avatar");
			return (Criteria) this;
		}

		public Criteria andSayHiLikeInsensitive(String value) {
			addCriterion("upper(say_hi) like", value.toUpperCase(), "sayHi");
			return (Criteria) this;
		}

		public Criteria andAiNameLikeInsensitive(String value) {
			addCriterion("upper(ai_name) like", value.toUpperCase(), "aiName");
			return (Criteria) this;
		}

		public Criteria andMasterNameLikeInsensitive(String value) {
			addCriterion("upper(master_name) like", value.toUpperCase(), "masterName");
			return (Criteria) this;
		}

		public Criteria andHeadAvatarLikeInsensitive(String value) {
			addCriterion("upper(head_avatar) like", value.toUpperCase(), "headAvatar");
			return (Criteria) this;
		}

		public Criteria andAnniImgsLikeInsensitive(String value) {
			addCriterion("upper(anni_imgs) like", value.toUpperCase(), "anniImgs");
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