package com.little.g.springcloud.admin.model;

import java.util.ArrayList;
import java.util.List;

public class ResourcesExample {

	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public ResourcesExample() {
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

		public Criteria andTitleIsNull() {
			addCriterion("title is null");
			return (Criteria) this;
		}

		public Criteria andTitleIsNotNull() {
			addCriterion("title is not null");
			return (Criteria) this;
		}

		public Criteria andTitleEqualTo(String value) {
			addCriterion("title =", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotEqualTo(String value) {
			addCriterion("title <>", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleGreaterThan(String value) {
			addCriterion("title >", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleGreaterThanOrEqualTo(String value) {
			addCriterion("title >=", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleLessThan(String value) {
			addCriterion("title <", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleLessThanOrEqualTo(String value) {
			addCriterion("title <=", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleLike(String value) {
			addCriterion("title like", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotLike(String value) {
			addCriterion("title not like", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleIn(List<String> values) {
			addCriterion("title in", values, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotIn(List<String> values) {
			addCriterion("title not in", values, "title");
			return (Criteria) this;
		}

		public Criteria andTitleBetween(String value1, String value2) {
			addCriterion("title between", value1, value2, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotBetween(String value1, String value2) {
			addCriterion("title not between", value1, value2, "title");
			return (Criteria) this;
		}

		public Criteria andPathIsNull() {
			addCriterion("`path` is null");
			return (Criteria) this;
		}

		public Criteria andPathIsNotNull() {
			addCriterion("`path` is not null");
			return (Criteria) this;
		}

		public Criteria andPathEqualTo(String value) {
			addCriterion("`path` =", value, "path");
			return (Criteria) this;
		}

		public Criteria andPathNotEqualTo(String value) {
			addCriterion("`path` <>", value, "path");
			return (Criteria) this;
		}

		public Criteria andPathGreaterThan(String value) {
			addCriterion("`path` >", value, "path");
			return (Criteria) this;
		}

		public Criteria andPathGreaterThanOrEqualTo(String value) {
			addCriterion("`path` >=", value, "path");
			return (Criteria) this;
		}

		public Criteria andPathLessThan(String value) {
			addCriterion("`path` <", value, "path");
			return (Criteria) this;
		}

		public Criteria andPathLessThanOrEqualTo(String value) {
			addCriterion("`path` <=", value, "path");
			return (Criteria) this;
		}

		public Criteria andPathLike(String value) {
			addCriterion("`path` like", value, "path");
			return (Criteria) this;
		}

		public Criteria andPathNotLike(String value) {
			addCriterion("`path` not like", value, "path");
			return (Criteria) this;
		}

		public Criteria andPathIn(List<String> values) {
			addCriterion("`path` in", values, "path");
			return (Criteria) this;
		}

		public Criteria andPathNotIn(List<String> values) {
			addCriterion("`path` not in", values, "path");
			return (Criteria) this;
		}

		public Criteria andPathBetween(String value1, String value2) {
			addCriterion("`path` between", value1, value2, "path");
			return (Criteria) this;
		}

		public Criteria andPathNotBetween(String value1, String value2) {
			addCriterion("`path` not between", value1, value2, "path");
			return (Criteria) this;
		}

		public Criteria andParentIdIsNull() {
			addCriterion("parent_id is null");
			return (Criteria) this;
		}

		public Criteria andParentIdIsNotNull() {
			addCriterion("parent_id is not null");
			return (Criteria) this;
		}

		public Criteria andParentIdEqualTo(Integer value) {
			addCriterion("parent_id =", value, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdNotEqualTo(Integer value) {
			addCriterion("parent_id <>", value, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdGreaterThan(Integer value) {
			addCriterion("parent_id >", value, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("parent_id >=", value, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdLessThan(Integer value) {
			addCriterion("parent_id <", value, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdLessThanOrEqualTo(Integer value) {
			addCriterion("parent_id <=", value, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdIn(List<Integer> values) {
			addCriterion("parent_id in", values, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdNotIn(List<Integer> values) {
			addCriterion("parent_id not in", values, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdBetween(Integer value1, Integer value2) {
			addCriterion("parent_id between", value1, value2, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdNotBetween(Integer value1, Integer value2) {
			addCriterion("parent_id not between", value1, value2, "parentId");
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

		public Criteria andIsMenuIsNull() {
			addCriterion("is_menu is null");
			return (Criteria) this;
		}

		public Criteria andIsMenuIsNotNull() {
			addCriterion("is_menu is not null");
			return (Criteria) this;
		}

		public Criteria andIsMenuEqualTo(Byte value) {
			addCriterion("is_menu =", value, "isMenu");
			return (Criteria) this;
		}

		public Criteria andIsMenuNotEqualTo(Byte value) {
			addCriterion("is_menu <>", value, "isMenu");
			return (Criteria) this;
		}

		public Criteria andIsMenuGreaterThan(Byte value) {
			addCriterion("is_menu >", value, "isMenu");
			return (Criteria) this;
		}

		public Criteria andIsMenuGreaterThanOrEqualTo(Byte value) {
			addCriterion("is_menu >=", value, "isMenu");
			return (Criteria) this;
		}

		public Criteria andIsMenuLessThan(Byte value) {
			addCriterion("is_menu <", value, "isMenu");
			return (Criteria) this;
		}

		public Criteria andIsMenuLessThanOrEqualTo(Byte value) {
			addCriterion("is_menu <=", value, "isMenu");
			return (Criteria) this;
		}

		public Criteria andIsMenuIn(List<Byte> values) {
			addCriterion("is_menu in", values, "isMenu");
			return (Criteria) this;
		}

		public Criteria andIsMenuNotIn(List<Byte> values) {
			addCriterion("is_menu not in", values, "isMenu");
			return (Criteria) this;
		}

		public Criteria andIsMenuBetween(Byte value1, Byte value2) {
			addCriterion("is_menu between", value1, value2, "isMenu");
			return (Criteria) this;
		}

		public Criteria andIsMenuNotBetween(Byte value1, Byte value2) {
			addCriterion("is_menu not between", value1, value2, "isMenu");
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

		public Criteria andPrivilegePosIsNull() {
			addCriterion("privilege_pos is null");
			return (Criteria) this;
		}

		public Criteria andPrivilegePosIsNotNull() {
			addCriterion("privilege_pos is not null");
			return (Criteria) this;
		}

		public Criteria andPrivilegePosEqualTo(Integer value) {
			addCriterion("privilege_pos =", value, "privilegePos");
			return (Criteria) this;
		}

		public Criteria andPrivilegePosNotEqualTo(Integer value) {
			addCriterion("privilege_pos <>", value, "privilegePos");
			return (Criteria) this;
		}

		public Criteria andPrivilegePosGreaterThan(Integer value) {
			addCriterion("privilege_pos >", value, "privilegePos");
			return (Criteria) this;
		}

		public Criteria andPrivilegePosGreaterThanOrEqualTo(Integer value) {
			addCriterion("privilege_pos >=", value, "privilegePos");
			return (Criteria) this;
		}

		public Criteria andPrivilegePosLessThan(Integer value) {
			addCriterion("privilege_pos <", value, "privilegePos");
			return (Criteria) this;
		}

		public Criteria andPrivilegePosLessThanOrEqualTo(Integer value) {
			addCriterion("privilege_pos <=", value, "privilegePos");
			return (Criteria) this;
		}

		public Criteria andPrivilegePosIn(List<Integer> values) {
			addCriterion("privilege_pos in", values, "privilegePos");
			return (Criteria) this;
		}

		public Criteria andPrivilegePosNotIn(List<Integer> values) {
			addCriterion("privilege_pos not in", values, "privilegePos");
			return (Criteria) this;
		}

		public Criteria andPrivilegePosBetween(Integer value1, Integer value2) {
			addCriterion("privilege_pos between", value1, value2, "privilegePos");
			return (Criteria) this;
		}

		public Criteria andPrivilegePosNotBetween(Integer value1, Integer value2) {
			addCriterion("privilege_pos not between", value1, value2, "privilegePos");
			return (Criteria) this;
		}

		public Criteria andPrivilegeValIsNull() {
			addCriterion("privilege_val is null");
			return (Criteria) this;
		}

		public Criteria andPrivilegeValIsNotNull() {
			addCriterion("privilege_val is not null");
			return (Criteria) this;
		}

		public Criteria andPrivilegeValEqualTo(Long value) {
			addCriterion("privilege_val =", value, "privilegeVal");
			return (Criteria) this;
		}

		public Criteria andPrivilegeValNotEqualTo(Long value) {
			addCriterion("privilege_val <>", value, "privilegeVal");
			return (Criteria) this;
		}

		public Criteria andPrivilegeValGreaterThan(Long value) {
			addCriterion("privilege_val >", value, "privilegeVal");
			return (Criteria) this;
		}

		public Criteria andPrivilegeValGreaterThanOrEqualTo(Long value) {
			addCriterion("privilege_val >=", value, "privilegeVal");
			return (Criteria) this;
		}

		public Criteria andPrivilegeValLessThan(Long value) {
			addCriterion("privilege_val <", value, "privilegeVal");
			return (Criteria) this;
		}

		public Criteria andPrivilegeValLessThanOrEqualTo(Long value) {
			addCriterion("privilege_val <=", value, "privilegeVal");
			return (Criteria) this;
		}

		public Criteria andPrivilegeValIn(List<Long> values) {
			addCriterion("privilege_val in", values, "privilegeVal");
			return (Criteria) this;
		}

		public Criteria andPrivilegeValNotIn(List<Long> values) {
			addCriterion("privilege_val not in", values, "privilegeVal");
			return (Criteria) this;
		}

		public Criteria andPrivilegeValBetween(Long value1, Long value2) {
			addCriterion("privilege_val between", value1, value2, "privilegeVal");
			return (Criteria) this;
		}

		public Criteria andPrivilegeValNotBetween(Long value1, Long value2) {
			addCriterion("privilege_val not between", value1, value2, "privilegeVal");
			return (Criteria) this;
		}

		public Criteria andNeedAuthIsNull() {
			addCriterion("need_auth is null");
			return (Criteria) this;
		}

		public Criteria andNeedAuthIsNotNull() {
			addCriterion("need_auth is not null");
			return (Criteria) this;
		}

		public Criteria andNeedAuthEqualTo(Byte value) {
			addCriterion("need_auth =", value, "needAuth");
			return (Criteria) this;
		}

		public Criteria andNeedAuthNotEqualTo(Byte value) {
			addCriterion("need_auth <>", value, "needAuth");
			return (Criteria) this;
		}

		public Criteria andNeedAuthGreaterThan(Byte value) {
			addCriterion("need_auth >", value, "needAuth");
			return (Criteria) this;
		}

		public Criteria andNeedAuthGreaterThanOrEqualTo(Byte value) {
			addCriterion("need_auth >=", value, "needAuth");
			return (Criteria) this;
		}

		public Criteria andNeedAuthLessThan(Byte value) {
			addCriterion("need_auth <", value, "needAuth");
			return (Criteria) this;
		}

		public Criteria andNeedAuthLessThanOrEqualTo(Byte value) {
			addCriterion("need_auth <=", value, "needAuth");
			return (Criteria) this;
		}

		public Criteria andNeedAuthIn(List<Byte> values) {
			addCriterion("need_auth in", values, "needAuth");
			return (Criteria) this;
		}

		public Criteria andNeedAuthNotIn(List<Byte> values) {
			addCriterion("need_auth not in", values, "needAuth");
			return (Criteria) this;
		}

		public Criteria andNeedAuthBetween(Byte value1, Byte value2) {
			addCriterion("need_auth between", value1, value2, "needAuth");
			return (Criteria) this;
		}

		public Criteria andNeedAuthNotBetween(Byte value1, Byte value2) {
			addCriterion("need_auth not between", value1, value2, "needAuth");
			return (Criteria) this;
		}

		public Criteria andSortIsNull() {
			addCriterion("sort is null");
			return (Criteria) this;
		}

		public Criteria andSortIsNotNull() {
			addCriterion("sort is not null");
			return (Criteria) this;
		}

		public Criteria andSortEqualTo(Integer value) {
			addCriterion("sort =", value, "sort");
			return (Criteria) this;
		}

		public Criteria andSortNotEqualTo(Integer value) {
			addCriterion("sort <>", value, "sort");
			return (Criteria) this;
		}

		public Criteria andSortGreaterThan(Integer value) {
			addCriterion("sort >", value, "sort");
			return (Criteria) this;
		}

		public Criteria andSortGreaterThanOrEqualTo(Integer value) {
			addCriterion("sort >=", value, "sort");
			return (Criteria) this;
		}

		public Criteria andSortLessThan(Integer value) {
			addCriterion("sort <", value, "sort");
			return (Criteria) this;
		}

		public Criteria andSortLessThanOrEqualTo(Integer value) {
			addCriterion("sort <=", value, "sort");
			return (Criteria) this;
		}

		public Criteria andSortIn(List<Integer> values) {
			addCriterion("sort in", values, "sort");
			return (Criteria) this;
		}

		public Criteria andSortNotIn(List<Integer> values) {
			addCriterion("sort not in", values, "sort");
			return (Criteria) this;
		}

		public Criteria andSortBetween(Integer value1, Integer value2) {
			addCriterion("sort between", value1, value2, "sort");
			return (Criteria) this;
		}

		public Criteria andSortNotBetween(Integer value1, Integer value2) {
			addCriterion("sort not between", value1, value2, "sort");
			return (Criteria) this;
		}

		public Criteria andTitleLikeInsensitive(String value) {
			addCriterion("upper(title) like", value.toUpperCase(), "title");
			return (Criteria) this;
		}

		public Criteria andPathLikeInsensitive(String value) {
			addCriterion("upper(`path`) like", value.toUpperCase(), "path");
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