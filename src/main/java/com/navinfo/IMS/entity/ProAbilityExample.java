package com.navinfo.IMS.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProAbilityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProAbilityExample() {
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

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("ID like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("ID not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("VERSION is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("VERSION is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(String value) {
            addCriterion("VERSION =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(String value) {
            addCriterion("VERSION <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(String value) {
            addCriterion("VERSION >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(String value) {
            addCriterion("VERSION >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(String value) {
            addCriterion("VERSION <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(String value) {
            addCriterion("VERSION <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLike(String value) {
            addCriterion("VERSION like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotLike(String value) {
            addCriterion("VERSION not like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<String> values) {
            addCriterion("VERSION in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<String> values) {
            addCriterion("VERSION not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(String value1, String value2) {
            addCriterion("VERSION between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(String value1, String value2) {
            addCriterion("VERSION not between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andEmpIdIsNull() {
            addCriterion("EMP_ID is null");
            return (Criteria) this;
        }

        public Criteria andEmpIdIsNotNull() {
            addCriterion("EMP_ID is not null");
            return (Criteria) this;
        }

        public Criteria andEmpIdEqualTo(String value) {
            addCriterion("EMP_ID =", value, "empId");
            return (Criteria) this;
        }

        public Criteria andEmpIdNotEqualTo(String value) {
            addCriterion("EMP_ID <>", value, "empId");
            return (Criteria) this;
        }

        public Criteria andEmpIdGreaterThan(String value) {
            addCriterion("EMP_ID >", value, "empId");
            return (Criteria) this;
        }

        public Criteria andEmpIdGreaterThanOrEqualTo(String value) {
            addCriterion("EMP_ID >=", value, "empId");
            return (Criteria) this;
        }

        public Criteria andEmpIdLessThan(String value) {
            addCriterion("EMP_ID <", value, "empId");
            return (Criteria) this;
        }

        public Criteria andEmpIdLessThanOrEqualTo(String value) {
            addCriterion("EMP_ID <=", value, "empId");
            return (Criteria) this;
        }

        public Criteria andEmpIdLike(String value) {
            addCriterion("EMP_ID like", value, "empId");
            return (Criteria) this;
        }

        public Criteria andEmpIdNotLike(String value) {
            addCriterion("EMP_ID not like", value, "empId");
            return (Criteria) this;
        }

        public Criteria andEmpIdIn(List<String> values) {
            addCriterion("EMP_ID in", values, "empId");
            return (Criteria) this;
        }

        public Criteria andEmpIdNotIn(List<String> values) {
            addCriterion("EMP_ID not in", values, "empId");
            return (Criteria) this;
        }

        public Criteria andEmpIdBetween(String value1, String value2) {
            addCriterion("EMP_ID between", value1, value2, "empId");
            return (Criteria) this;
        }

        public Criteria andEmpIdNotBetween(String value1, String value2) {
            addCriterion("EMP_ID not between", value1, value2, "empId");
            return (Criteria) this;
        }

        public Criteria andEmpNameIsNull() {
            addCriterion("EMP_NAME is null");
            return (Criteria) this;
        }

        public Criteria andEmpNameIsNotNull() {
            addCriterion("EMP_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andEmpNameEqualTo(String value) {
            addCriterion("EMP_NAME =", value, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpNameNotEqualTo(String value) {
            addCriterion("EMP_NAME <>", value, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpNameGreaterThan(String value) {
            addCriterion("EMP_NAME >", value, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpNameGreaterThanOrEqualTo(String value) {
            addCriterion("EMP_NAME >=", value, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpNameLessThan(String value) {
            addCriterion("EMP_NAME <", value, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpNameLessThanOrEqualTo(String value) {
            addCriterion("EMP_NAME <=", value, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpNameLike(String value) {
            addCriterion("EMP_NAME like", value, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpNameNotLike(String value) {
            addCriterion("EMP_NAME not like", value, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpNameIn(List<String> values) {
            addCriterion("EMP_NAME in", values, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpNameNotIn(List<String> values) {
            addCriterion("EMP_NAME not in", values, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpNameBetween(String value1, String value2) {
            addCriterion("EMP_NAME between", value1, value2, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpNameNotBetween(String value1, String value2) {
            addCriterion("EMP_NAME not between", value1, value2, "empName");
            return (Criteria) this;
        }

        public Criteria andRMarkLogIsNull() {
            addCriterion("R_MARK_LOG is null");
            return (Criteria) this;
        }

        public Criteria andRMarkLogIsNotNull() {
            addCriterion("R_MARK_LOG is not null");
            return (Criteria) this;
        }

        public Criteria andRMarkLogEqualTo(String value) {
            addCriterion("R_MARK_LOG =", value, "rMarkLog");
            return (Criteria) this;
        }

        public Criteria andRMarkLogNotEqualTo(String value) {
            addCriterion("R_MARK_LOG <>", value, "rMarkLog");
            return (Criteria) this;
        }

        public Criteria andRMarkLogGreaterThan(String value) {
            addCriterion("R_MARK_LOG >", value, "rMarkLog");
            return (Criteria) this;
        }

        public Criteria andRMarkLogGreaterThanOrEqualTo(String value) {
            addCriterion("R_MARK_LOG >=", value, "rMarkLog");
            return (Criteria) this;
        }

        public Criteria andRMarkLogLessThan(String value) {
            addCriterion("R_MARK_LOG <", value, "rMarkLog");
            return (Criteria) this;
        }

        public Criteria andRMarkLogLessThanOrEqualTo(String value) {
            addCriterion("R_MARK_LOG <=", value, "rMarkLog");
            return (Criteria) this;
        }

        public Criteria andRMarkLogLike(String value) {
            addCriterion("R_MARK_LOG like", value, "rMarkLog");
            return (Criteria) this;
        }

        public Criteria andRMarkLogNotLike(String value) {
            addCriterion("R_MARK_LOG not like", value, "rMarkLog");
            return (Criteria) this;
        }

        public Criteria andRMarkLogIn(List<String> values) {
            addCriterion("R_MARK_LOG in", values, "rMarkLog");
            return (Criteria) this;
        }

        public Criteria andRMarkLogNotIn(List<String> values) {
            addCriterion("R_MARK_LOG not in", values, "rMarkLog");
            return (Criteria) this;
        }

        public Criteria andRMarkLogBetween(String value1, String value2) {
            addCriterion("R_MARK_LOG between", value1, value2, "rMarkLog");
            return (Criteria) this;
        }

        public Criteria andRMarkLogNotBetween(String value1, String value2) {
            addCriterion("R_MARK_LOG not between", value1, value2, "rMarkLog");
            return (Criteria) this;
        }

        public Criteria andRMarkTestIsNull() {
            addCriterion("R_MARK_TEST is null");
            return (Criteria) this;
        }

        public Criteria andRMarkTestIsNotNull() {
            addCriterion("R_MARK_TEST is not null");
            return (Criteria) this;
        }

        public Criteria andRMarkTestEqualTo(String value) {
            addCriterion("R_MARK_TEST =", value, "rMarkTest");
            return (Criteria) this;
        }

        public Criteria andRMarkTestNotEqualTo(String value) {
            addCriterion("R_MARK_TEST <>", value, "rMarkTest");
            return (Criteria) this;
        }

        public Criteria andRMarkTestGreaterThan(String value) {
            addCriterion("R_MARK_TEST >", value, "rMarkTest");
            return (Criteria) this;
        }

        public Criteria andRMarkTestGreaterThanOrEqualTo(String value) {
            addCriterion("R_MARK_TEST >=", value, "rMarkTest");
            return (Criteria) this;
        }

        public Criteria andRMarkTestLessThan(String value) {
            addCriterion("R_MARK_TEST <", value, "rMarkTest");
            return (Criteria) this;
        }

        public Criteria andRMarkTestLessThanOrEqualTo(String value) {
            addCriterion("R_MARK_TEST <=", value, "rMarkTest");
            return (Criteria) this;
        }

        public Criteria andRMarkTestLike(String value) {
            addCriterion("R_MARK_TEST like", value, "rMarkTest");
            return (Criteria) this;
        }

        public Criteria andRMarkTestNotLike(String value) {
            addCriterion("R_MARK_TEST not like", value, "rMarkTest");
            return (Criteria) this;
        }

        public Criteria andRMarkTestIn(List<String> values) {
            addCriterion("R_MARK_TEST in", values, "rMarkTest");
            return (Criteria) this;
        }

        public Criteria andRMarkTestNotIn(List<String> values) {
            addCriterion("R_MARK_TEST not in", values, "rMarkTest");
            return (Criteria) this;
        }

        public Criteria andRMarkTestBetween(String value1, String value2) {
            addCriterion("R_MARK_TEST between", value1, value2, "rMarkTest");
            return (Criteria) this;
        }

        public Criteria andRMarkTestNotBetween(String value1, String value2) {
            addCriterion("R_MARK_TEST not between", value1, value2, "rMarkTest");
            return (Criteria) this;
        }

        public Criteria andChPoiLogIsNull() {
            addCriterion("CH_POI_LOG is null");
            return (Criteria) this;
        }

        public Criteria andChPoiLogIsNotNull() {
            addCriterion("CH_POI_LOG is not null");
            return (Criteria) this;
        }

        public Criteria andChPoiLogEqualTo(String value) {
            addCriterion("CH_POI_LOG =", value, "chPoiLog");
            return (Criteria) this;
        }

        public Criteria andChPoiLogNotEqualTo(String value) {
            addCriterion("CH_POI_LOG <>", value, "chPoiLog");
            return (Criteria) this;
        }

        public Criteria andChPoiLogGreaterThan(String value) {
            addCriterion("CH_POI_LOG >", value, "chPoiLog");
            return (Criteria) this;
        }

        public Criteria andChPoiLogGreaterThanOrEqualTo(String value) {
            addCriterion("CH_POI_LOG >=", value, "chPoiLog");
            return (Criteria) this;
        }

        public Criteria andChPoiLogLessThan(String value) {
            addCriterion("CH_POI_LOG <", value, "chPoiLog");
            return (Criteria) this;
        }

        public Criteria andChPoiLogLessThanOrEqualTo(String value) {
            addCriterion("CH_POI_LOG <=", value, "chPoiLog");
            return (Criteria) this;
        }

        public Criteria andChPoiLogLike(String value) {
            addCriterion("CH_POI_LOG like", value, "chPoiLog");
            return (Criteria) this;
        }

        public Criteria andChPoiLogNotLike(String value) {
            addCriterion("CH_POI_LOG not like", value, "chPoiLog");
            return (Criteria) this;
        }

        public Criteria andChPoiLogIn(List<String> values) {
            addCriterion("CH_POI_LOG in", values, "chPoiLog");
            return (Criteria) this;
        }

        public Criteria andChPoiLogNotIn(List<String> values) {
            addCriterion("CH_POI_LOG not in", values, "chPoiLog");
            return (Criteria) this;
        }

        public Criteria andChPoiLogBetween(String value1, String value2) {
            addCriterion("CH_POI_LOG between", value1, value2, "chPoiLog");
            return (Criteria) this;
        }

        public Criteria andChPoiLogNotBetween(String value1, String value2) {
            addCriterion("CH_POI_LOG not between", value1, value2, "chPoiLog");
            return (Criteria) this;
        }

        public Criteria andChPoiTestIsNull() {
            addCriterion("CH_POI_TEST is null");
            return (Criteria) this;
        }

        public Criteria andChPoiTestIsNotNull() {
            addCriterion("CH_POI_TEST is not null");
            return (Criteria) this;
        }

        public Criteria andChPoiTestEqualTo(String value) {
            addCriterion("CH_POI_TEST =", value, "chPoiTest");
            return (Criteria) this;
        }

        public Criteria andChPoiTestNotEqualTo(String value) {
            addCriterion("CH_POI_TEST <>", value, "chPoiTest");
            return (Criteria) this;
        }

        public Criteria andChPoiTestGreaterThan(String value) {
            addCriterion("CH_POI_TEST >", value, "chPoiTest");
            return (Criteria) this;
        }

        public Criteria andChPoiTestGreaterThanOrEqualTo(String value) {
            addCriterion("CH_POI_TEST >=", value, "chPoiTest");
            return (Criteria) this;
        }

        public Criteria andChPoiTestLessThan(String value) {
            addCriterion("CH_POI_TEST <", value, "chPoiTest");
            return (Criteria) this;
        }

        public Criteria andChPoiTestLessThanOrEqualTo(String value) {
            addCriterion("CH_POI_TEST <=", value, "chPoiTest");
            return (Criteria) this;
        }

        public Criteria andChPoiTestLike(String value) {
            addCriterion("CH_POI_TEST like", value, "chPoiTest");
            return (Criteria) this;
        }

        public Criteria andChPoiTestNotLike(String value) {
            addCriterion("CH_POI_TEST not like", value, "chPoiTest");
            return (Criteria) this;
        }

        public Criteria andChPoiTestIn(List<String> values) {
            addCriterion("CH_POI_TEST in", values, "chPoiTest");
            return (Criteria) this;
        }

        public Criteria andChPoiTestNotIn(List<String> values) {
            addCriterion("CH_POI_TEST not in", values, "chPoiTest");
            return (Criteria) this;
        }

        public Criteria andChPoiTestBetween(String value1, String value2) {
            addCriterion("CH_POI_TEST between", value1, value2, "chPoiTest");
            return (Criteria) this;
        }

        public Criteria andChPoiTestNotBetween(String value1, String value2) {
            addCriterion("CH_POI_TEST not between", value1, value2, "chPoiTest");
            return (Criteria) this;
        }

        public Criteria andEnPoiLogIsNull() {
            addCriterion("EN_POI_LOG is null");
            return (Criteria) this;
        }

        public Criteria andEnPoiLogIsNotNull() {
            addCriterion("EN_POI_LOG is not null");
            return (Criteria) this;
        }

        public Criteria andEnPoiLogEqualTo(String value) {
            addCriterion("EN_POI_LOG =", value, "enPoiLog");
            return (Criteria) this;
        }

        public Criteria andEnPoiLogNotEqualTo(String value) {
            addCriterion("EN_POI_LOG <>", value, "enPoiLog");
            return (Criteria) this;
        }

        public Criteria andEnPoiLogGreaterThan(String value) {
            addCriterion("EN_POI_LOG >", value, "enPoiLog");
            return (Criteria) this;
        }

        public Criteria andEnPoiLogGreaterThanOrEqualTo(String value) {
            addCriterion("EN_POI_LOG >=", value, "enPoiLog");
            return (Criteria) this;
        }

        public Criteria andEnPoiLogLessThan(String value) {
            addCriterion("EN_POI_LOG <", value, "enPoiLog");
            return (Criteria) this;
        }

        public Criteria andEnPoiLogLessThanOrEqualTo(String value) {
            addCriterion("EN_POI_LOG <=", value, "enPoiLog");
            return (Criteria) this;
        }

        public Criteria andEnPoiLogLike(String value) {
            addCriterion("EN_POI_LOG like", value, "enPoiLog");
            return (Criteria) this;
        }

        public Criteria andEnPoiLogNotLike(String value) {
            addCriterion("EN_POI_LOG not like", value, "enPoiLog");
            return (Criteria) this;
        }

        public Criteria andEnPoiLogIn(List<String> values) {
            addCriterion("EN_POI_LOG in", values, "enPoiLog");
            return (Criteria) this;
        }

        public Criteria andEnPoiLogNotIn(List<String> values) {
            addCriterion("EN_POI_LOG not in", values, "enPoiLog");
            return (Criteria) this;
        }

        public Criteria andEnPoiLogBetween(String value1, String value2) {
            addCriterion("EN_POI_LOG between", value1, value2, "enPoiLog");
            return (Criteria) this;
        }

        public Criteria andEnPoiLogNotBetween(String value1, String value2) {
            addCriterion("EN_POI_LOG not between", value1, value2, "enPoiLog");
            return (Criteria) this;
        }

        public Criteria andEnPoiTestIsNull() {
            addCriterion("EN_POI_TEST is null");
            return (Criteria) this;
        }

        public Criteria andEnPoiTestIsNotNull() {
            addCriterion("EN_POI_TEST is not null");
            return (Criteria) this;
        }

        public Criteria andEnPoiTestEqualTo(String value) {
            addCriterion("EN_POI_TEST =", value, "enPoiTest");
            return (Criteria) this;
        }

        public Criteria andEnPoiTestNotEqualTo(String value) {
            addCriterion("EN_POI_TEST <>", value, "enPoiTest");
            return (Criteria) this;
        }

        public Criteria andEnPoiTestGreaterThan(String value) {
            addCriterion("EN_POI_TEST >", value, "enPoiTest");
            return (Criteria) this;
        }

        public Criteria andEnPoiTestGreaterThanOrEqualTo(String value) {
            addCriterion("EN_POI_TEST >=", value, "enPoiTest");
            return (Criteria) this;
        }

        public Criteria andEnPoiTestLessThan(String value) {
            addCriterion("EN_POI_TEST <", value, "enPoiTest");
            return (Criteria) this;
        }

        public Criteria andEnPoiTestLessThanOrEqualTo(String value) {
            addCriterion("EN_POI_TEST <=", value, "enPoiTest");
            return (Criteria) this;
        }

        public Criteria andEnPoiTestLike(String value) {
            addCriterion("EN_POI_TEST like", value, "enPoiTest");
            return (Criteria) this;
        }

        public Criteria andEnPoiTestNotLike(String value) {
            addCriterion("EN_POI_TEST not like", value, "enPoiTest");
            return (Criteria) this;
        }

        public Criteria andEnPoiTestIn(List<String> values) {
            addCriterion("EN_POI_TEST in", values, "enPoiTest");
            return (Criteria) this;
        }

        public Criteria andEnPoiTestNotIn(List<String> values) {
            addCriterion("EN_POI_TEST not in", values, "enPoiTest");
            return (Criteria) this;
        }

        public Criteria andEnPoiTestBetween(String value1, String value2) {
            addCriterion("EN_POI_TEST between", value1, value2, "enPoiTest");
            return (Criteria) this;
        }

        public Criteria andEnPoiTestNotBetween(String value1, String value2) {
            addCriterion("EN_POI_TEST not between", value1, value2, "enPoiTest");
            return (Criteria) this;
        }

        public Criteria andRoadItemLogIsNull() {
            addCriterion("ROAD_ITEM_LOG is null");
            return (Criteria) this;
        }

        public Criteria andRoadItemLogIsNotNull() {
            addCriterion("ROAD_ITEM_LOG is not null");
            return (Criteria) this;
        }

        public Criteria andRoadItemLogEqualTo(String value) {
            addCriterion("ROAD_ITEM_LOG =", value, "roadItemLog");
            return (Criteria) this;
        }

        public Criteria andRoadItemLogNotEqualTo(String value) {
            addCriterion("ROAD_ITEM_LOG <>", value, "roadItemLog");
            return (Criteria) this;
        }

        public Criteria andRoadItemLogGreaterThan(String value) {
            addCriterion("ROAD_ITEM_LOG >", value, "roadItemLog");
            return (Criteria) this;
        }

        public Criteria andRoadItemLogGreaterThanOrEqualTo(String value) {
            addCriterion("ROAD_ITEM_LOG >=", value, "roadItemLog");
            return (Criteria) this;
        }

        public Criteria andRoadItemLogLessThan(String value) {
            addCriterion("ROAD_ITEM_LOG <", value, "roadItemLog");
            return (Criteria) this;
        }

        public Criteria andRoadItemLogLessThanOrEqualTo(String value) {
            addCriterion("ROAD_ITEM_LOG <=", value, "roadItemLog");
            return (Criteria) this;
        }

        public Criteria andRoadItemLogLike(String value) {
            addCriterion("ROAD_ITEM_LOG like", value, "roadItemLog");
            return (Criteria) this;
        }

        public Criteria andRoadItemLogNotLike(String value) {
            addCriterion("ROAD_ITEM_LOG not like", value, "roadItemLog");
            return (Criteria) this;
        }

        public Criteria andRoadItemLogIn(List<String> values) {
            addCriterion("ROAD_ITEM_LOG in", values, "roadItemLog");
            return (Criteria) this;
        }

        public Criteria andRoadItemLogNotIn(List<String> values) {
            addCriterion("ROAD_ITEM_LOG not in", values, "roadItemLog");
            return (Criteria) this;
        }

        public Criteria andRoadItemLogBetween(String value1, String value2) {
            addCriterion("ROAD_ITEM_LOG between", value1, value2, "roadItemLog");
            return (Criteria) this;
        }

        public Criteria andRoadItemLogNotBetween(String value1, String value2) {
            addCriterion("ROAD_ITEM_LOG not between", value1, value2, "roadItemLog");
            return (Criteria) this;
        }

        public Criteria andRoadItemTestIsNull() {
            addCriterion("ROAD_ITEM_TEST is null");
            return (Criteria) this;
        }

        public Criteria andRoadItemTestIsNotNull() {
            addCriterion("ROAD_ITEM_TEST is not null");
            return (Criteria) this;
        }

        public Criteria andRoadItemTestEqualTo(String value) {
            addCriterion("ROAD_ITEM_TEST =", value, "roadItemTest");
            return (Criteria) this;
        }

        public Criteria andRoadItemTestNotEqualTo(String value) {
            addCriterion("ROAD_ITEM_TEST <>", value, "roadItemTest");
            return (Criteria) this;
        }

        public Criteria andRoadItemTestGreaterThan(String value) {
            addCriterion("ROAD_ITEM_TEST >", value, "roadItemTest");
            return (Criteria) this;
        }

        public Criteria andRoadItemTestGreaterThanOrEqualTo(String value) {
            addCriterion("ROAD_ITEM_TEST >=", value, "roadItemTest");
            return (Criteria) this;
        }

        public Criteria andRoadItemTestLessThan(String value) {
            addCriterion("ROAD_ITEM_TEST <", value, "roadItemTest");
            return (Criteria) this;
        }

        public Criteria andRoadItemTestLessThanOrEqualTo(String value) {
            addCriterion("ROAD_ITEM_TEST <=", value, "roadItemTest");
            return (Criteria) this;
        }

        public Criteria andRoadItemTestLike(String value) {
            addCriterion("ROAD_ITEM_TEST like", value, "roadItemTest");
            return (Criteria) this;
        }

        public Criteria andRoadItemTestNotLike(String value) {
            addCriterion("ROAD_ITEM_TEST not like", value, "roadItemTest");
            return (Criteria) this;
        }

        public Criteria andRoadItemTestIn(List<String> values) {
            addCriterion("ROAD_ITEM_TEST in", values, "roadItemTest");
            return (Criteria) this;
        }

        public Criteria andRoadItemTestNotIn(List<String> values) {
            addCriterion("ROAD_ITEM_TEST not in", values, "roadItemTest");
            return (Criteria) this;
        }

        public Criteria andRoadItemTestBetween(String value1, String value2) {
            addCriterion("ROAD_ITEM_TEST between", value1, value2, "roadItemTest");
            return (Criteria) this;
        }

        public Criteria andRoadItemTestNotBetween(String value1, String value2) {
            addCriterion("ROAD_ITEM_TEST not between", value1, value2, "roadItemTest");
            return (Criteria) this;
        }

        public Criteria andPoiItemLogIsNull() {
            addCriterion("POI_ITEM_LOG is null");
            return (Criteria) this;
        }

        public Criteria andPoiItemLogIsNotNull() {
            addCriterion("POI_ITEM_LOG is not null");
            return (Criteria) this;
        }

        public Criteria andPoiItemLogEqualTo(String value) {
            addCriterion("POI_ITEM_LOG =", value, "poiItemLog");
            return (Criteria) this;
        }

        public Criteria andPoiItemLogNotEqualTo(String value) {
            addCriterion("POI_ITEM_LOG <>", value, "poiItemLog");
            return (Criteria) this;
        }

        public Criteria andPoiItemLogGreaterThan(String value) {
            addCriterion("POI_ITEM_LOG >", value, "poiItemLog");
            return (Criteria) this;
        }

        public Criteria andPoiItemLogGreaterThanOrEqualTo(String value) {
            addCriterion("POI_ITEM_LOG >=", value, "poiItemLog");
            return (Criteria) this;
        }

        public Criteria andPoiItemLogLessThan(String value) {
            addCriterion("POI_ITEM_LOG <", value, "poiItemLog");
            return (Criteria) this;
        }

        public Criteria andPoiItemLogLessThanOrEqualTo(String value) {
            addCriterion("POI_ITEM_LOG <=", value, "poiItemLog");
            return (Criteria) this;
        }

        public Criteria andPoiItemLogLike(String value) {
            addCriterion("POI_ITEM_LOG like", value, "poiItemLog");
            return (Criteria) this;
        }

        public Criteria andPoiItemLogNotLike(String value) {
            addCriterion("POI_ITEM_LOG not like", value, "poiItemLog");
            return (Criteria) this;
        }

        public Criteria andPoiItemLogIn(List<String> values) {
            addCriterion("POI_ITEM_LOG in", values, "poiItemLog");
            return (Criteria) this;
        }

        public Criteria andPoiItemLogNotIn(List<String> values) {
            addCriterion("POI_ITEM_LOG not in", values, "poiItemLog");
            return (Criteria) this;
        }

        public Criteria andPoiItemLogBetween(String value1, String value2) {
            addCriterion("POI_ITEM_LOG between", value1, value2, "poiItemLog");
            return (Criteria) this;
        }

        public Criteria andPoiItemLogNotBetween(String value1, String value2) {
            addCriterion("POI_ITEM_LOG not between", value1, value2, "poiItemLog");
            return (Criteria) this;
        }

        public Criteria andPoiItemTestIsNull() {
            addCriterion("POI_ITEM_TEST is null");
            return (Criteria) this;
        }

        public Criteria andPoiItemTestIsNotNull() {
            addCriterion("POI_ITEM_TEST is not null");
            return (Criteria) this;
        }

        public Criteria andPoiItemTestEqualTo(String value) {
            addCriterion("POI_ITEM_TEST =", value, "poiItemTest");
            return (Criteria) this;
        }

        public Criteria andPoiItemTestNotEqualTo(String value) {
            addCriterion("POI_ITEM_TEST <>", value, "poiItemTest");
            return (Criteria) this;
        }

        public Criteria andPoiItemTestGreaterThan(String value) {
            addCriterion("POI_ITEM_TEST >", value, "poiItemTest");
            return (Criteria) this;
        }

        public Criteria andPoiItemTestGreaterThanOrEqualTo(String value) {
            addCriterion("POI_ITEM_TEST >=", value, "poiItemTest");
            return (Criteria) this;
        }

        public Criteria andPoiItemTestLessThan(String value) {
            addCriterion("POI_ITEM_TEST <", value, "poiItemTest");
            return (Criteria) this;
        }

        public Criteria andPoiItemTestLessThanOrEqualTo(String value) {
            addCriterion("POI_ITEM_TEST <=", value, "poiItemTest");
            return (Criteria) this;
        }

        public Criteria andPoiItemTestLike(String value) {
            addCriterion("POI_ITEM_TEST like", value, "poiItemTest");
            return (Criteria) this;
        }

        public Criteria andPoiItemTestNotLike(String value) {
            addCriterion("POI_ITEM_TEST not like", value, "poiItemTest");
            return (Criteria) this;
        }

        public Criteria andPoiItemTestIn(List<String> values) {
            addCriterion("POI_ITEM_TEST in", values, "poiItemTest");
            return (Criteria) this;
        }

        public Criteria andPoiItemTestNotIn(List<String> values) {
            addCriterion("POI_ITEM_TEST not in", values, "poiItemTest");
            return (Criteria) this;
        }

        public Criteria andPoiItemTestBetween(String value1, String value2) {
            addCriterion("POI_ITEM_TEST between", value1, value2, "poiItemTest");
            return (Criteria) this;
        }

        public Criteria andPoiItemTestNotBetween(String value1, String value2) {
            addCriterion("POI_ITEM_TEST not between", value1, value2, "poiItemTest");
            return (Criteria) this;
        }

        public Criteria andDepthInfoLogIsNull() {
            addCriterion("DEPTH_INFO_LOG is null");
            return (Criteria) this;
        }

        public Criteria andDepthInfoLogIsNotNull() {
            addCriterion("DEPTH_INFO_LOG is not null");
            return (Criteria) this;
        }

        public Criteria andDepthInfoLogEqualTo(String value) {
            addCriterion("DEPTH_INFO_LOG =", value, "depthInfoLog");
            return (Criteria) this;
        }

        public Criteria andDepthInfoLogNotEqualTo(String value) {
            addCriterion("DEPTH_INFO_LOG <>", value, "depthInfoLog");
            return (Criteria) this;
        }

        public Criteria andDepthInfoLogGreaterThan(String value) {
            addCriterion("DEPTH_INFO_LOG >", value, "depthInfoLog");
            return (Criteria) this;
        }

        public Criteria andDepthInfoLogGreaterThanOrEqualTo(String value) {
            addCriterion("DEPTH_INFO_LOG >=", value, "depthInfoLog");
            return (Criteria) this;
        }

        public Criteria andDepthInfoLogLessThan(String value) {
            addCriterion("DEPTH_INFO_LOG <", value, "depthInfoLog");
            return (Criteria) this;
        }

        public Criteria andDepthInfoLogLessThanOrEqualTo(String value) {
            addCriterion("DEPTH_INFO_LOG <=", value, "depthInfoLog");
            return (Criteria) this;
        }

        public Criteria andDepthInfoLogLike(String value) {
            addCriterion("DEPTH_INFO_LOG like", value, "depthInfoLog");
            return (Criteria) this;
        }

        public Criteria andDepthInfoLogNotLike(String value) {
            addCriterion("DEPTH_INFO_LOG not like", value, "depthInfoLog");
            return (Criteria) this;
        }

        public Criteria andDepthInfoLogIn(List<String> values) {
            addCriterion("DEPTH_INFO_LOG in", values, "depthInfoLog");
            return (Criteria) this;
        }

        public Criteria andDepthInfoLogNotIn(List<String> values) {
            addCriterion("DEPTH_INFO_LOG not in", values, "depthInfoLog");
            return (Criteria) this;
        }

        public Criteria andDepthInfoLogBetween(String value1, String value2) {
            addCriterion("DEPTH_INFO_LOG between", value1, value2, "depthInfoLog");
            return (Criteria) this;
        }

        public Criteria andDepthInfoLogNotBetween(String value1, String value2) {
            addCriterion("DEPTH_INFO_LOG not between", value1, value2, "depthInfoLog");
            return (Criteria) this;
        }

        public Criteria andDepthInfoTestIsNull() {
            addCriterion("DEPTH_INFO_TEST is null");
            return (Criteria) this;
        }

        public Criteria andDepthInfoTestIsNotNull() {
            addCriterion("DEPTH_INFO_TEST is not null");
            return (Criteria) this;
        }

        public Criteria andDepthInfoTestEqualTo(String value) {
            addCriterion("DEPTH_INFO_TEST =", value, "depthInfoTest");
            return (Criteria) this;
        }

        public Criteria andDepthInfoTestNotEqualTo(String value) {
            addCriterion("DEPTH_INFO_TEST <>", value, "depthInfoTest");
            return (Criteria) this;
        }

        public Criteria andDepthInfoTestGreaterThan(String value) {
            addCriterion("DEPTH_INFO_TEST >", value, "depthInfoTest");
            return (Criteria) this;
        }

        public Criteria andDepthInfoTestGreaterThanOrEqualTo(String value) {
            addCriterion("DEPTH_INFO_TEST >=", value, "depthInfoTest");
            return (Criteria) this;
        }

        public Criteria andDepthInfoTestLessThan(String value) {
            addCriterion("DEPTH_INFO_TEST <", value, "depthInfoTest");
            return (Criteria) this;
        }

        public Criteria andDepthInfoTestLessThanOrEqualTo(String value) {
            addCriterion("DEPTH_INFO_TEST <=", value, "depthInfoTest");
            return (Criteria) this;
        }

        public Criteria andDepthInfoTestLike(String value) {
            addCriterion("DEPTH_INFO_TEST like", value, "depthInfoTest");
            return (Criteria) this;
        }

        public Criteria andDepthInfoTestNotLike(String value) {
            addCriterion("DEPTH_INFO_TEST not like", value, "depthInfoTest");
            return (Criteria) this;
        }

        public Criteria andDepthInfoTestIn(List<String> values) {
            addCriterion("DEPTH_INFO_TEST in", values, "depthInfoTest");
            return (Criteria) this;
        }

        public Criteria andDepthInfoTestNotIn(List<String> values) {
            addCriterion("DEPTH_INFO_TEST not in", values, "depthInfoTest");
            return (Criteria) this;
        }

        public Criteria andDepthInfoTestBetween(String value1, String value2) {
            addCriterion("DEPTH_INFO_TEST between", value1, value2, "depthInfoTest");
            return (Criteria) this;
        }

        public Criteria andDepthInfoTestNotBetween(String value1, String value2) {
            addCriterion("DEPTH_INFO_TEST not between", value1, value2, "depthInfoTest");
            return (Criteria) this;
        }

        public Criteria andPointLogIsNull() {
            addCriterion("POINT_LOG is null");
            return (Criteria) this;
        }

        public Criteria andPointLogIsNotNull() {
            addCriterion("POINT_LOG is not null");
            return (Criteria) this;
        }

        public Criteria andPointLogEqualTo(String value) {
            addCriterion("POINT_LOG =", value, "pointLog");
            return (Criteria) this;
        }

        public Criteria andPointLogNotEqualTo(String value) {
            addCriterion("POINT_LOG <>", value, "pointLog");
            return (Criteria) this;
        }

        public Criteria andPointLogGreaterThan(String value) {
            addCriterion("POINT_LOG >", value, "pointLog");
            return (Criteria) this;
        }

        public Criteria andPointLogGreaterThanOrEqualTo(String value) {
            addCriterion("POINT_LOG >=", value, "pointLog");
            return (Criteria) this;
        }

        public Criteria andPointLogLessThan(String value) {
            addCriterion("POINT_LOG <", value, "pointLog");
            return (Criteria) this;
        }

        public Criteria andPointLogLessThanOrEqualTo(String value) {
            addCriterion("POINT_LOG <=", value, "pointLog");
            return (Criteria) this;
        }

        public Criteria andPointLogLike(String value) {
            addCriterion("POINT_LOG like", value, "pointLog");
            return (Criteria) this;
        }

        public Criteria andPointLogNotLike(String value) {
            addCriterion("POINT_LOG not like", value, "pointLog");
            return (Criteria) this;
        }

        public Criteria andPointLogIn(List<String> values) {
            addCriterion("POINT_LOG in", values, "pointLog");
            return (Criteria) this;
        }

        public Criteria andPointLogNotIn(List<String> values) {
            addCriterion("POINT_LOG not in", values, "pointLog");
            return (Criteria) this;
        }

        public Criteria andPointLogBetween(String value1, String value2) {
            addCriterion("POINT_LOG between", value1, value2, "pointLog");
            return (Criteria) this;
        }

        public Criteria andPointLogNotBetween(String value1, String value2) {
            addCriterion("POINT_LOG not between", value1, value2, "pointLog");
            return (Criteria) this;
        }

        public Criteria andPointTestIsNull() {
            addCriterion("POINT_TEST is null");
            return (Criteria) this;
        }

        public Criteria andPointTestIsNotNull() {
            addCriterion("POINT_TEST is not null");
            return (Criteria) this;
        }

        public Criteria andPointTestEqualTo(String value) {
            addCriterion("POINT_TEST =", value, "pointTest");
            return (Criteria) this;
        }

        public Criteria andPointTestNotEqualTo(String value) {
            addCriterion("POINT_TEST <>", value, "pointTest");
            return (Criteria) this;
        }

        public Criteria andPointTestGreaterThan(String value) {
            addCriterion("POINT_TEST >", value, "pointTest");
            return (Criteria) this;
        }

        public Criteria andPointTestGreaterThanOrEqualTo(String value) {
            addCriterion("POINT_TEST >=", value, "pointTest");
            return (Criteria) this;
        }

        public Criteria andPointTestLessThan(String value) {
            addCriterion("POINT_TEST <", value, "pointTest");
            return (Criteria) this;
        }

        public Criteria andPointTestLessThanOrEqualTo(String value) {
            addCriterion("POINT_TEST <=", value, "pointTest");
            return (Criteria) this;
        }

        public Criteria andPointTestLike(String value) {
            addCriterion("POINT_TEST like", value, "pointTest");
            return (Criteria) this;
        }

        public Criteria andPointTestNotLike(String value) {
            addCriterion("POINT_TEST not like", value, "pointTest");
            return (Criteria) this;
        }

        public Criteria andPointTestIn(List<String> values) {
            addCriterion("POINT_TEST in", values, "pointTest");
            return (Criteria) this;
        }

        public Criteria andPointTestNotIn(List<String> values) {
            addCriterion("POINT_TEST not in", values, "pointTest");
            return (Criteria) this;
        }

        public Criteria andPointTestBetween(String value1, String value2) {
            addCriterion("POINT_TEST between", value1, value2, "pointTest");
            return (Criteria) this;
        }

        public Criteria andPointTestNotBetween(String value1, String value2) {
            addCriterion("POINT_TEST not between", value1, value2, "pointTest");
            return (Criteria) this;
        }

        public Criteria andAgencyLogIsNull() {
            addCriterion("AGENCY_LOG is null");
            return (Criteria) this;
        }

        public Criteria andAgencyLogIsNotNull() {
            addCriterion("AGENCY_LOG is not null");
            return (Criteria) this;
        }

        public Criteria andAgencyLogEqualTo(String value) {
            addCriterion("AGENCY_LOG =", value, "agencyLog");
            return (Criteria) this;
        }

        public Criteria andAgencyLogNotEqualTo(String value) {
            addCriterion("AGENCY_LOG <>", value, "agencyLog");
            return (Criteria) this;
        }

        public Criteria andAgencyLogGreaterThan(String value) {
            addCriterion("AGENCY_LOG >", value, "agencyLog");
            return (Criteria) this;
        }

        public Criteria andAgencyLogGreaterThanOrEqualTo(String value) {
            addCriterion("AGENCY_LOG >=", value, "agencyLog");
            return (Criteria) this;
        }

        public Criteria andAgencyLogLessThan(String value) {
            addCriterion("AGENCY_LOG <", value, "agencyLog");
            return (Criteria) this;
        }

        public Criteria andAgencyLogLessThanOrEqualTo(String value) {
            addCriterion("AGENCY_LOG <=", value, "agencyLog");
            return (Criteria) this;
        }

        public Criteria andAgencyLogLike(String value) {
            addCriterion("AGENCY_LOG like", value, "agencyLog");
            return (Criteria) this;
        }

        public Criteria andAgencyLogNotLike(String value) {
            addCriterion("AGENCY_LOG not like", value, "agencyLog");
            return (Criteria) this;
        }

        public Criteria andAgencyLogIn(List<String> values) {
            addCriterion("AGENCY_LOG in", values, "agencyLog");
            return (Criteria) this;
        }

        public Criteria andAgencyLogNotIn(List<String> values) {
            addCriterion("AGENCY_LOG not in", values, "agencyLog");
            return (Criteria) this;
        }

        public Criteria andAgencyLogBetween(String value1, String value2) {
            addCriterion("AGENCY_LOG between", value1, value2, "agencyLog");
            return (Criteria) this;
        }

        public Criteria andAgencyLogNotBetween(String value1, String value2) {
            addCriterion("AGENCY_LOG not between", value1, value2, "agencyLog");
            return (Criteria) this;
        }

        public Criteria andAgencyTestIsNull() {
            addCriterion("AGENCY_TEST is null");
            return (Criteria) this;
        }

        public Criteria andAgencyTestIsNotNull() {
            addCriterion("AGENCY_TEST is not null");
            return (Criteria) this;
        }

        public Criteria andAgencyTestEqualTo(String value) {
            addCriterion("AGENCY_TEST =", value, "agencyTest");
            return (Criteria) this;
        }

        public Criteria andAgencyTestNotEqualTo(String value) {
            addCriterion("AGENCY_TEST <>", value, "agencyTest");
            return (Criteria) this;
        }

        public Criteria andAgencyTestGreaterThan(String value) {
            addCriterion("AGENCY_TEST >", value, "agencyTest");
            return (Criteria) this;
        }

        public Criteria andAgencyTestGreaterThanOrEqualTo(String value) {
            addCriterion("AGENCY_TEST >=", value, "agencyTest");
            return (Criteria) this;
        }

        public Criteria andAgencyTestLessThan(String value) {
            addCriterion("AGENCY_TEST <", value, "agencyTest");
            return (Criteria) this;
        }

        public Criteria andAgencyTestLessThanOrEqualTo(String value) {
            addCriterion("AGENCY_TEST <=", value, "agencyTest");
            return (Criteria) this;
        }

        public Criteria andAgencyTestLike(String value) {
            addCriterion("AGENCY_TEST like", value, "agencyTest");
            return (Criteria) this;
        }

        public Criteria andAgencyTestNotLike(String value) {
            addCriterion("AGENCY_TEST not like", value, "agencyTest");
            return (Criteria) this;
        }

        public Criteria andAgencyTestIn(List<String> values) {
            addCriterion("AGENCY_TEST in", values, "agencyTest");
            return (Criteria) this;
        }

        public Criteria andAgencyTestNotIn(List<String> values) {
            addCriterion("AGENCY_TEST not in", values, "agencyTest");
            return (Criteria) this;
        }

        public Criteria andAgencyTestBetween(String value1, String value2) {
            addCriterion("AGENCY_TEST between", value1, value2, "agencyTest");
            return (Criteria) this;
        }

        public Criteria andAgencyTestNotBetween(String value1, String value2) {
            addCriterion("AGENCY_TEST not between", value1, value2, "agencyTest");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreatorIdIsNull() {
            addCriterion("CREATOR_ID is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIdIsNotNull() {
            addCriterion("CREATOR_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorIdEqualTo(String value) {
            addCriterion("CREATOR_ID =", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdNotEqualTo(String value) {
            addCriterion("CREATOR_ID <>", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdGreaterThan(String value) {
            addCriterion("CREATOR_ID >", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdGreaterThanOrEqualTo(String value) {
            addCriterion("CREATOR_ID >=", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdLessThan(String value) {
            addCriterion("CREATOR_ID <", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdLessThanOrEqualTo(String value) {
            addCriterion("CREATOR_ID <=", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdLike(String value) {
            addCriterion("CREATOR_ID like", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdNotLike(String value) {
            addCriterion("CREATOR_ID not like", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdIn(List<String> values) {
            addCriterion("CREATOR_ID in", values, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdNotIn(List<String> values) {
            addCriterion("CREATOR_ID not in", values, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdBetween(String value1, String value2) {
            addCriterion("CREATOR_ID between", value1, value2, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdNotBetween(String value1, String value2) {
            addCriterion("CREATOR_ID not between", value1, value2, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNull() {
            addCriterion("CREATOR is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("CREATOR is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(String value) {
            addCriterion("CREATOR =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(String value) {
            addCriterion("CREATOR <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(String value) {
            addCriterion("CREATOR >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("CREATOR >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(String value) {
            addCriterion("CREATOR <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(String value) {
            addCriterion("CREATOR <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLike(String value) {
            addCriterion("CREATOR like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotLike(String value) {
            addCriterion("CREATOR not like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<String> values) {
            addCriterion("CREATOR in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<String> values) {
            addCriterion("CREATOR not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(String value1, String value2) {
            addCriterion("CREATOR between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(String value1, String value2) {
            addCriterion("CREATOR not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("UPDATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("UPDATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("UPDATE_TIME =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("UPDATE_TIME <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("UPDATE_TIME >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("UPDATE_TIME <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("UPDATE_TIME in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("UPDATE_TIME not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdaterIdIsNull() {
            addCriterion("UPDATER_ID is null");
            return (Criteria) this;
        }

        public Criteria andUpdaterIdIsNotNull() {
            addCriterion("UPDATER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUpdaterIdEqualTo(String value) {
            addCriterion("UPDATER_ID =", value, "updaterId");
            return (Criteria) this;
        }

        public Criteria andUpdaterIdNotEqualTo(String value) {
            addCriterion("UPDATER_ID <>", value, "updaterId");
            return (Criteria) this;
        }

        public Criteria andUpdaterIdGreaterThan(String value) {
            addCriterion("UPDATER_ID >", value, "updaterId");
            return (Criteria) this;
        }

        public Criteria andUpdaterIdGreaterThanOrEqualTo(String value) {
            addCriterion("UPDATER_ID >=", value, "updaterId");
            return (Criteria) this;
        }

        public Criteria andUpdaterIdLessThan(String value) {
            addCriterion("UPDATER_ID <", value, "updaterId");
            return (Criteria) this;
        }

        public Criteria andUpdaterIdLessThanOrEqualTo(String value) {
            addCriterion("UPDATER_ID <=", value, "updaterId");
            return (Criteria) this;
        }

        public Criteria andUpdaterIdLike(String value) {
            addCriterion("UPDATER_ID like", value, "updaterId");
            return (Criteria) this;
        }

        public Criteria andUpdaterIdNotLike(String value) {
            addCriterion("UPDATER_ID not like", value, "updaterId");
            return (Criteria) this;
        }

        public Criteria andUpdaterIdIn(List<String> values) {
            addCriterion("UPDATER_ID in", values, "updaterId");
            return (Criteria) this;
        }

        public Criteria andUpdaterIdNotIn(List<String> values) {
            addCriterion("UPDATER_ID not in", values, "updaterId");
            return (Criteria) this;
        }

        public Criteria andUpdaterIdBetween(String value1, String value2) {
            addCriterion("UPDATER_ID between", value1, value2, "updaterId");
            return (Criteria) this;
        }

        public Criteria andUpdaterIdNotBetween(String value1, String value2) {
            addCriterion("UPDATER_ID not between", value1, value2, "updaterId");
            return (Criteria) this;
        }

        public Criteria andUpdaterIsNull() {
            addCriterion("UPDATER is null");
            return (Criteria) this;
        }

        public Criteria andUpdaterIsNotNull() {
            addCriterion("UPDATER is not null");
            return (Criteria) this;
        }

        public Criteria andUpdaterEqualTo(String value) {
            addCriterion("UPDATER =", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterNotEqualTo(String value) {
            addCriterion("UPDATER <>", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterGreaterThan(String value) {
            addCriterion("UPDATER >", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterGreaterThanOrEqualTo(String value) {
            addCriterion("UPDATER >=", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterLessThan(String value) {
            addCriterion("UPDATER <", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterLessThanOrEqualTo(String value) {
            addCriterion("UPDATER <=", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterLike(String value) {
            addCriterion("UPDATER like", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterNotLike(String value) {
            addCriterion("UPDATER not like", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterIn(List<String> values) {
            addCriterion("UPDATER in", values, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterNotIn(List<String> values) {
            addCriterion("UPDATER not in", values, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterBetween(String value1, String value2) {
            addCriterion("UPDATER between", value1, value2, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterNotBetween(String value1, String value2) {
            addCriterion("UPDATER not between", value1, value2, "updater");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andAttr1IsNull() {
            addCriterion("ATTR1 is null");
            return (Criteria) this;
        }

        public Criteria andAttr1IsNotNull() {
            addCriterion("ATTR1 is not null");
            return (Criteria) this;
        }

        public Criteria andAttr1EqualTo(String value) {
            addCriterion("ATTR1 =", value, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr1NotEqualTo(String value) {
            addCriterion("ATTR1 <>", value, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr1GreaterThan(String value) {
            addCriterion("ATTR1 >", value, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr1GreaterThanOrEqualTo(String value) {
            addCriterion("ATTR1 >=", value, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr1LessThan(String value) {
            addCriterion("ATTR1 <", value, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr1LessThanOrEqualTo(String value) {
            addCriterion("ATTR1 <=", value, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr1Like(String value) {
            addCriterion("ATTR1 like", value, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr1NotLike(String value) {
            addCriterion("ATTR1 not like", value, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr1In(List<String> values) {
            addCriterion("ATTR1 in", values, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr1NotIn(List<String> values) {
            addCriterion("ATTR1 not in", values, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr1Between(String value1, String value2) {
            addCriterion("ATTR1 between", value1, value2, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr1NotBetween(String value1, String value2) {
            addCriterion("ATTR1 not between", value1, value2, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr2IsNull() {
            addCriterion("ATTR2 is null");
            return (Criteria) this;
        }

        public Criteria andAttr2IsNotNull() {
            addCriterion("ATTR2 is not null");
            return (Criteria) this;
        }

        public Criteria andAttr2EqualTo(String value) {
            addCriterion("ATTR2 =", value, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr2NotEqualTo(String value) {
            addCriterion("ATTR2 <>", value, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr2GreaterThan(String value) {
            addCriterion("ATTR2 >", value, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr2GreaterThanOrEqualTo(String value) {
            addCriterion("ATTR2 >=", value, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr2LessThan(String value) {
            addCriterion("ATTR2 <", value, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr2LessThanOrEqualTo(String value) {
            addCriterion("ATTR2 <=", value, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr2Like(String value) {
            addCriterion("ATTR2 like", value, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr2NotLike(String value) {
            addCriterion("ATTR2 not like", value, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr2In(List<String> values) {
            addCriterion("ATTR2 in", values, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr2NotIn(List<String> values) {
            addCriterion("ATTR2 not in", values, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr2Between(String value1, String value2) {
            addCriterion("ATTR2 between", value1, value2, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr2NotBetween(String value1, String value2) {
            addCriterion("ATTR2 not between", value1, value2, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr3IsNull() {
            addCriterion("ATTR3 is null");
            return (Criteria) this;
        }

        public Criteria andAttr3IsNotNull() {
            addCriterion("ATTR3 is not null");
            return (Criteria) this;
        }

        public Criteria andAttr3EqualTo(String value) {
            addCriterion("ATTR3 =", value, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr3NotEqualTo(String value) {
            addCriterion("ATTR3 <>", value, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr3GreaterThan(String value) {
            addCriterion("ATTR3 >", value, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr3GreaterThanOrEqualTo(String value) {
            addCriterion("ATTR3 >=", value, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr3LessThan(String value) {
            addCriterion("ATTR3 <", value, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr3LessThanOrEqualTo(String value) {
            addCriterion("ATTR3 <=", value, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr3Like(String value) {
            addCriterion("ATTR3 like", value, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr3NotLike(String value) {
            addCriterion("ATTR3 not like", value, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr3In(List<String> values) {
            addCriterion("ATTR3 in", values, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr3NotIn(List<String> values) {
            addCriterion("ATTR3 not in", values, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr3Between(String value1, String value2) {
            addCriterion("ATTR3 between", value1, value2, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr3NotBetween(String value1, String value2) {
            addCriterion("ATTR3 not between", value1, value2, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr4IsNull() {
            addCriterion("ATTR4 is null");
            return (Criteria) this;
        }

        public Criteria andAttr4IsNotNull() {
            addCriterion("ATTR4 is not null");
            return (Criteria) this;
        }

        public Criteria andAttr4EqualTo(String value) {
            addCriterion("ATTR4 =", value, "attr4");
            return (Criteria) this;
        }

        public Criteria andAttr4NotEqualTo(String value) {
            addCriterion("ATTR4 <>", value, "attr4");
            return (Criteria) this;
        }

        public Criteria andAttr4GreaterThan(String value) {
            addCriterion("ATTR4 >", value, "attr4");
            return (Criteria) this;
        }

        public Criteria andAttr4GreaterThanOrEqualTo(String value) {
            addCriterion("ATTR4 >=", value, "attr4");
            return (Criteria) this;
        }

        public Criteria andAttr4LessThan(String value) {
            addCriterion("ATTR4 <", value, "attr4");
            return (Criteria) this;
        }

        public Criteria andAttr4LessThanOrEqualTo(String value) {
            addCriterion("ATTR4 <=", value, "attr4");
            return (Criteria) this;
        }

        public Criteria andAttr4Like(String value) {
            addCriterion("ATTR4 like", value, "attr4");
            return (Criteria) this;
        }

        public Criteria andAttr4NotLike(String value) {
            addCriterion("ATTR4 not like", value, "attr4");
            return (Criteria) this;
        }

        public Criteria andAttr4In(List<String> values) {
            addCriterion("ATTR4 in", values, "attr4");
            return (Criteria) this;
        }

        public Criteria andAttr4NotIn(List<String> values) {
            addCriterion("ATTR4 not in", values, "attr4");
            return (Criteria) this;
        }

        public Criteria andAttr4Between(String value1, String value2) {
            addCriterion("ATTR4 between", value1, value2, "attr4");
            return (Criteria) this;
        }

        public Criteria andAttr4NotBetween(String value1, String value2) {
            addCriterion("ATTR4 not between", value1, value2, "attr4");
            return (Criteria) this;
        }

        public Criteria andAttr5IsNull() {
            addCriterion("ATTR5 is null");
            return (Criteria) this;
        }

        public Criteria andAttr5IsNotNull() {
            addCriterion("ATTR5 is not null");
            return (Criteria) this;
        }

        public Criteria andAttr5EqualTo(String value) {
            addCriterion("ATTR5 =", value, "attr5");
            return (Criteria) this;
        }

        public Criteria andAttr5NotEqualTo(String value) {
            addCriterion("ATTR5 <>", value, "attr5");
            return (Criteria) this;
        }

        public Criteria andAttr5GreaterThan(String value) {
            addCriterion("ATTR5 >", value, "attr5");
            return (Criteria) this;
        }

        public Criteria andAttr5GreaterThanOrEqualTo(String value) {
            addCriterion("ATTR5 >=", value, "attr5");
            return (Criteria) this;
        }

        public Criteria andAttr5LessThan(String value) {
            addCriterion("ATTR5 <", value, "attr5");
            return (Criteria) this;
        }

        public Criteria andAttr5LessThanOrEqualTo(String value) {
            addCriterion("ATTR5 <=", value, "attr5");
            return (Criteria) this;
        }

        public Criteria andAttr5Like(String value) {
            addCriterion("ATTR5 like", value, "attr5");
            return (Criteria) this;
        }

        public Criteria andAttr5NotLike(String value) {
            addCriterion("ATTR5 not like", value, "attr5");
            return (Criteria) this;
        }

        public Criteria andAttr5In(List<String> values) {
            addCriterion("ATTR5 in", values, "attr5");
            return (Criteria) this;
        }

        public Criteria andAttr5NotIn(List<String> values) {
            addCriterion("ATTR5 not in", values, "attr5");
            return (Criteria) this;
        }

        public Criteria andAttr5Between(String value1, String value2) {
            addCriterion("ATTR5 between", value1, value2, "attr5");
            return (Criteria) this;
        }

        public Criteria andAttr5NotBetween(String value1, String value2) {
            addCriterion("ATTR5 not between", value1, value2, "attr5");
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
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
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