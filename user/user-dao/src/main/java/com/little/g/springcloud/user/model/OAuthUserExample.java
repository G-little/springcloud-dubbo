package com.little.g.springcloud.user.model;

import java.util.ArrayList;
import java.util.List;

public class OAuthUserExample {

	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public OAuthUserExample() {
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

		public Criteria andOauthTypeIsNull() {
			addCriterion("oauth_type is null");
			return (Criteria) this;
		}

		public Criteria andOauthTypeIsNotNull() {
			addCriterion("oauth_type is not null");
			return (Criteria) this;
		}

		public Criteria andOauthTypeEqualTo(String value) {
			addCriterion("oauth_type =", value, "oauthType");
			return (Criteria) this;
		}

		public Criteria andOauthTypeNotEqualTo(String value) {
			addCriterion("oauth_type <>", value, "oauthType");
			return (Criteria) this;
		}

		public Criteria andOauthTypeGreaterThan(String value) {
			addCriterion("oauth_type >", value, "oauthType");
			return (Criteria) this;
		}

		public Criteria andOauthTypeGreaterThanOrEqualTo(String value) {
			addCriterion("oauth_type >=", value, "oauthType");
			return (Criteria) this;
		}

		public Criteria andOauthTypeLessThan(String value) {
			addCriterion("oauth_type <", value, "oauthType");
			return (Criteria) this;
		}

		public Criteria andOauthTypeLessThanOrEqualTo(String value) {
			addCriterion("oauth_type <=", value, "oauthType");
			return (Criteria) this;
		}

		public Criteria andOauthTypeLike(String value) {
			addCriterion("oauth_type like", value, "oauthType");
			return (Criteria) this;
		}

		public Criteria andOauthTypeNotLike(String value) {
			addCriterion("oauth_type not like", value, "oauthType");
			return (Criteria) this;
		}

		public Criteria andOauthTypeIn(List<String> values) {
			addCriterion("oauth_type in", values, "oauthType");
			return (Criteria) this;
		}

		public Criteria andOauthTypeNotIn(List<String> values) {
			addCriterion("oauth_type not in", values, "oauthType");
			return (Criteria) this;
		}

		public Criteria andOauthTypeBetween(String value1, String value2) {
			addCriterion("oauth_type between", value1, value2, "oauthType");
			return (Criteria) this;
		}

		public Criteria andOauthTypeNotBetween(String value1, String value2) {
			addCriterion("oauth_type not between", value1, value2, "oauthType");
			return (Criteria) this;
		}

		public Criteria andOpenidIsNull() {
			addCriterion("openid is null");
			return (Criteria) this;
		}

		public Criteria andOpenidIsNotNull() {
			addCriterion("openid is not null");
			return (Criteria) this;
		}

		public Criteria andOpenidEqualTo(String value) {
			addCriterion("openid =", value, "openid");
			return (Criteria) this;
		}

		public Criteria andOpenidNotEqualTo(String value) {
			addCriterion("openid <>", value, "openid");
			return (Criteria) this;
		}

		public Criteria andOpenidGreaterThan(String value) {
			addCriterion("openid >", value, "openid");
			return (Criteria) this;
		}

		public Criteria andOpenidGreaterThanOrEqualTo(String value) {
			addCriterion("openid >=", value, "openid");
			return (Criteria) this;
		}

		public Criteria andOpenidLessThan(String value) {
			addCriterion("openid <", value, "openid");
			return (Criteria) this;
		}

		public Criteria andOpenidLessThanOrEqualTo(String value) {
			addCriterion("openid <=", value, "openid");
			return (Criteria) this;
		}

		public Criteria andOpenidLike(String value) {
			addCriterion("openid like", value, "openid");
			return (Criteria) this;
		}

		public Criteria andOpenidNotLike(String value) {
			addCriterion("openid not like", value, "openid");
			return (Criteria) this;
		}

		public Criteria andOpenidIn(List<String> values) {
			addCriterion("openid in", values, "openid");
			return (Criteria) this;
		}

		public Criteria andOpenidNotIn(List<String> values) {
			addCriterion("openid not in", values, "openid");
			return (Criteria) this;
		}

		public Criteria andOpenidBetween(String value1, String value2) {
			addCriterion("openid between", value1, value2, "openid");
			return (Criteria) this;
		}

		public Criteria andOpenidNotBetween(String value1, String value2) {
			addCriterion("openid not between", value1, value2, "openid");
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

		public Criteria andAccessTokenIsNull() {
			addCriterion("access_token is null");
			return (Criteria) this;
		}

		public Criteria andAccessTokenIsNotNull() {
			addCriterion("access_token is not null");
			return (Criteria) this;
		}

		public Criteria andAccessTokenEqualTo(String value) {
			addCriterion("access_token =", value, "accessToken");
			return (Criteria) this;
		}

		public Criteria andAccessTokenNotEqualTo(String value) {
			addCriterion("access_token <>", value, "accessToken");
			return (Criteria) this;
		}

		public Criteria andAccessTokenGreaterThan(String value) {
			addCriterion("access_token >", value, "accessToken");
			return (Criteria) this;
		}

		public Criteria andAccessTokenGreaterThanOrEqualTo(String value) {
			addCriterion("access_token >=", value, "accessToken");
			return (Criteria) this;
		}

		public Criteria andAccessTokenLessThan(String value) {
			addCriterion("access_token <", value, "accessToken");
			return (Criteria) this;
		}

		public Criteria andAccessTokenLessThanOrEqualTo(String value) {
			addCriterion("access_token <=", value, "accessToken");
			return (Criteria) this;
		}

		public Criteria andAccessTokenLike(String value) {
			addCriterion("access_token like", value, "accessToken");
			return (Criteria) this;
		}

		public Criteria andAccessTokenNotLike(String value) {
			addCriterion("access_token not like", value, "accessToken");
			return (Criteria) this;
		}

		public Criteria andAccessTokenIn(List<String> values) {
			addCriterion("access_token in", values, "accessToken");
			return (Criteria) this;
		}

		public Criteria andAccessTokenNotIn(List<String> values) {
			addCriterion("access_token not in", values, "accessToken");
			return (Criteria) this;
		}

		public Criteria andAccessTokenBetween(String value1, String value2) {
			addCriterion("access_token between", value1, value2, "accessToken");
			return (Criteria) this;
		}

		public Criteria andAccessTokenNotBetween(String value1, String value2) {
			addCriterion("access_token not between", value1, value2, "accessToken");
			return (Criteria) this;
		}

		public Criteria andExpiresInIsNull() {
			addCriterion("expires_in is null");
			return (Criteria) this;
		}

		public Criteria andExpiresInIsNotNull() {
			addCriterion("expires_in is not null");
			return (Criteria) this;
		}

		public Criteria andExpiresInEqualTo(Integer value) {
			addCriterion("expires_in =", value, "expiresIn");
			return (Criteria) this;
		}

		public Criteria andExpiresInNotEqualTo(Integer value) {
			addCriterion("expires_in <>", value, "expiresIn");
			return (Criteria) this;
		}

		public Criteria andExpiresInGreaterThan(Integer value) {
			addCriterion("expires_in >", value, "expiresIn");
			return (Criteria) this;
		}

		public Criteria andExpiresInGreaterThanOrEqualTo(Integer value) {
			addCriterion("expires_in >=", value, "expiresIn");
			return (Criteria) this;
		}

		public Criteria andExpiresInLessThan(Integer value) {
			addCriterion("expires_in <", value, "expiresIn");
			return (Criteria) this;
		}

		public Criteria andExpiresInLessThanOrEqualTo(Integer value) {
			addCriterion("expires_in <=", value, "expiresIn");
			return (Criteria) this;
		}

		public Criteria andExpiresInIn(List<Integer> values) {
			addCriterion("expires_in in", values, "expiresIn");
			return (Criteria) this;
		}

		public Criteria andExpiresInNotIn(List<Integer> values) {
			addCriterion("expires_in not in", values, "expiresIn");
			return (Criteria) this;
		}

		public Criteria andExpiresInBetween(Integer value1, Integer value2) {
			addCriterion("expires_in between", value1, value2, "expiresIn");
			return (Criteria) this;
		}

		public Criteria andExpiresInNotBetween(Integer value1, Integer value2) {
			addCriterion("expires_in not between", value1, value2, "expiresIn");
			return (Criteria) this;
		}

		public Criteria andRefreshTokenIsNull() {
			addCriterion("refresh_token is null");
			return (Criteria) this;
		}

		public Criteria andRefreshTokenIsNotNull() {
			addCriterion("refresh_token is not null");
			return (Criteria) this;
		}

		public Criteria andRefreshTokenEqualTo(String value) {
			addCriterion("refresh_token =", value, "refreshToken");
			return (Criteria) this;
		}

		public Criteria andRefreshTokenNotEqualTo(String value) {
			addCriterion("refresh_token <>", value, "refreshToken");
			return (Criteria) this;
		}

		public Criteria andRefreshTokenGreaterThan(String value) {
			addCriterion("refresh_token >", value, "refreshToken");
			return (Criteria) this;
		}

		public Criteria andRefreshTokenGreaterThanOrEqualTo(String value) {
			addCriterion("refresh_token >=", value, "refreshToken");
			return (Criteria) this;
		}

		public Criteria andRefreshTokenLessThan(String value) {
			addCriterion("refresh_token <", value, "refreshToken");
			return (Criteria) this;
		}

		public Criteria andRefreshTokenLessThanOrEqualTo(String value) {
			addCriterion("refresh_token <=", value, "refreshToken");
			return (Criteria) this;
		}

		public Criteria andRefreshTokenLike(String value) {
			addCriterion("refresh_token like", value, "refreshToken");
			return (Criteria) this;
		}

		public Criteria andRefreshTokenNotLike(String value) {
			addCriterion("refresh_token not like", value, "refreshToken");
			return (Criteria) this;
		}

		public Criteria andRefreshTokenIn(List<String> values) {
			addCriterion("refresh_token in", values, "refreshToken");
			return (Criteria) this;
		}

		public Criteria andRefreshTokenNotIn(List<String> values) {
			addCriterion("refresh_token not in", values, "refreshToken");
			return (Criteria) this;
		}

		public Criteria andRefreshTokenBetween(String value1, String value2) {
			addCriterion("refresh_token between", value1, value2, "refreshToken");
			return (Criteria) this;
		}

		public Criteria andRefreshTokenNotBetween(String value1, String value2) {
			addCriterion("refresh_token not between", value1, value2, "refreshToken");
			return (Criteria) this;
		}

		public Criteria andReExpiresInIsNull() {
			addCriterion("re_expires_in is null");
			return (Criteria) this;
		}

		public Criteria andReExpiresInIsNotNull() {
			addCriterion("re_expires_in is not null");
			return (Criteria) this;
		}

		public Criteria andReExpiresInEqualTo(Integer value) {
			addCriterion("re_expires_in =", value, "reExpiresIn");
			return (Criteria) this;
		}

		public Criteria andReExpiresInNotEqualTo(Integer value) {
			addCriterion("re_expires_in <>", value, "reExpiresIn");
			return (Criteria) this;
		}

		public Criteria andReExpiresInGreaterThan(Integer value) {
			addCriterion("re_expires_in >", value, "reExpiresIn");
			return (Criteria) this;
		}

		public Criteria andReExpiresInGreaterThanOrEqualTo(Integer value) {
			addCriterion("re_expires_in >=", value, "reExpiresIn");
			return (Criteria) this;
		}

		public Criteria andReExpiresInLessThan(Integer value) {
			addCriterion("re_expires_in <", value, "reExpiresIn");
			return (Criteria) this;
		}

		public Criteria andReExpiresInLessThanOrEqualTo(Integer value) {
			addCriterion("re_expires_in <=", value, "reExpiresIn");
			return (Criteria) this;
		}

		public Criteria andReExpiresInIn(List<Integer> values) {
			addCriterion("re_expires_in in", values, "reExpiresIn");
			return (Criteria) this;
		}

		public Criteria andReExpiresInNotIn(List<Integer> values) {
			addCriterion("re_expires_in not in", values, "reExpiresIn");
			return (Criteria) this;
		}

		public Criteria andReExpiresInBetween(Integer value1, Integer value2) {
			addCriterion("re_expires_in between", value1, value2, "reExpiresIn");
			return (Criteria) this;
		}

		public Criteria andReExpiresInNotBetween(Integer value1, Integer value2) {
			addCriterion("re_expires_in not between", value1, value2, "reExpiresIn");
			return (Criteria) this;
		}

		public Criteria andNameIsNull() {
			addCriterion("`name` is null");
			return (Criteria) this;
		}

		public Criteria andNameIsNotNull() {
			addCriterion("`name` is not null");
			return (Criteria) this;
		}

		public Criteria andNameEqualTo(String value) {
			addCriterion("`name` =", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotEqualTo(String value) {
			addCriterion("`name` <>", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThan(String value) {
			addCriterion("`name` >", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThanOrEqualTo(String value) {
			addCriterion("`name` >=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThan(String value) {
			addCriterion("`name` <", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThanOrEqualTo(String value) {
			addCriterion("`name` <=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLike(String value) {
			addCriterion("`name` like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotLike(String value) {
			addCriterion("`name` not like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameIn(List<String> values) {
			addCriterion("`name` in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotIn(List<String> values) {
			addCriterion("`name` not in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameBetween(String value1, String value2) {
			addCriterion("`name` between", value1, value2, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotBetween(String value1, String value2) {
			addCriterion("`name` not between", value1, value2, "name");
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

		public Criteria andUnionidIsNull() {
			addCriterion("unionid is null");
			return (Criteria) this;
		}

		public Criteria andUnionidIsNotNull() {
			addCriterion("unionid is not null");
			return (Criteria) this;
		}

		public Criteria andUnionidEqualTo(String value) {
			addCriterion("unionid =", value, "unionid");
			return (Criteria) this;
		}

		public Criteria andUnionidNotEqualTo(String value) {
			addCriterion("unionid <>", value, "unionid");
			return (Criteria) this;
		}

		public Criteria andUnionidGreaterThan(String value) {
			addCriterion("unionid >", value, "unionid");
			return (Criteria) this;
		}

		public Criteria andUnionidGreaterThanOrEqualTo(String value) {
			addCriterion("unionid >=", value, "unionid");
			return (Criteria) this;
		}

		public Criteria andUnionidLessThan(String value) {
			addCriterion("unionid <", value, "unionid");
			return (Criteria) this;
		}

		public Criteria andUnionidLessThanOrEqualTo(String value) {
			addCriterion("unionid <=", value, "unionid");
			return (Criteria) this;
		}

		public Criteria andUnionidLike(String value) {
			addCriterion("unionid like", value, "unionid");
			return (Criteria) this;
		}

		public Criteria andUnionidNotLike(String value) {
			addCriterion("unionid not like", value, "unionid");
			return (Criteria) this;
		}

		public Criteria andUnionidIn(List<String> values) {
			addCriterion("unionid in", values, "unionid");
			return (Criteria) this;
		}

		public Criteria andUnionidNotIn(List<String> values) {
			addCriterion("unionid not in", values, "unionid");
			return (Criteria) this;
		}

		public Criteria andUnionidBetween(String value1, String value2) {
			addCriterion("unionid between", value1, value2, "unionid");
			return (Criteria) this;
		}

		public Criteria andUnionidNotBetween(String value1, String value2) {
			addCriterion("unionid not between", value1, value2, "unionid");
			return (Criteria) this;
		}

		public Criteria andOauthTypeLikeInsensitive(String value) {
			addCriterion("upper(oauth_type) like", value.toUpperCase(), "oauthType");
			return (Criteria) this;
		}

		public Criteria andOpenidLikeInsensitive(String value) {
			addCriterion("upper(openid) like", value.toUpperCase(), "openid");
			return (Criteria) this;
		}

		public Criteria andAccessTokenLikeInsensitive(String value) {
			addCriterion("upper(access_token) like", value.toUpperCase(), "accessToken");
			return (Criteria) this;
		}

		public Criteria andRefreshTokenLikeInsensitive(String value) {
			addCriterion("upper(refresh_token) like", value.toUpperCase(),
					"refreshToken");
			return (Criteria) this;
		}

		public Criteria andNameLikeInsensitive(String value) {
			addCriterion("upper(`name`) like", value.toUpperCase(), "name");
			return (Criteria) this;
		}

		public Criteria andAvatarLikeInsensitive(String value) {
			addCriterion("upper(avatar) like", value.toUpperCase(), "avatar");
			return (Criteria) this;
		}

		public Criteria andUnionidLikeInsensitive(String value) {
			addCriterion("upper(unionid) like", value.toUpperCase(), "unionid");
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
