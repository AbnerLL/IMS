package com.navinfo.IMS.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WorkDataExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WorkDataExample() {
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

        public Criteria andWorkDataIdIsNull() {
            addCriterion("WORK_DATA_ID is null");
            return (Criteria) this;
        }

        public Criteria andWorkDataIdIsNotNull() {
            addCriterion("WORK_DATA_ID is not null");
            return (Criteria) this;
        }

        public Criteria andWorkDataIdEqualTo(String value) {
            addCriterion("WORK_DATA_ID =", value, "workDataId");
            return (Criteria) this;
        }

        public Criteria andWorkDataIdNotEqualTo(String value) {
            addCriterion("WORK_DATA_ID <>", value, "workDataId");
            return (Criteria) this;
        }

        public Criteria andWorkDataIdGreaterThan(String value) {
            addCriterion("WORK_DATA_ID >", value, "workDataId");
            return (Criteria) this;
        }

        public Criteria andWorkDataIdGreaterThanOrEqualTo(String value) {
            addCriterion("WORK_DATA_ID >=", value, "workDataId");
            return (Criteria) this;
        }

        public Criteria andWorkDataIdLessThan(String value) {
            addCriterion("WORK_DATA_ID <", value, "workDataId");
            return (Criteria) this;
        }

        public Criteria andWorkDataIdLessThanOrEqualTo(String value) {
            addCriterion("WORK_DATA_ID <=", value, "workDataId");
            return (Criteria) this;
        }

        public Criteria andWorkDataIdLike(String value) {
            addCriterion("WORK_DATA_ID like", value, "workDataId");
            return (Criteria) this;
        }

        public Criteria andWorkDataIdNotLike(String value) {
            addCriterion("WORK_DATA_ID not like", value, "workDataId");
            return (Criteria) this;
        }

        public Criteria andWorkDataIdIn(List<String> values) {
            addCriterion("WORK_DATA_ID in", values, "workDataId");
            return (Criteria) this;
        }

        public Criteria andWorkDataIdNotIn(List<String> values) {
            addCriterion("WORK_DATA_ID not in", values, "workDataId");
            return (Criteria) this;
        }

        public Criteria andWorkDataIdBetween(String value1, String value2) {
            addCriterion("WORK_DATA_ID between", value1, value2, "workDataId");
            return (Criteria) this;
        }

        public Criteria andWorkDataIdNotBetween(String value1, String value2) {
            addCriterion("WORK_DATA_ID not between", value1, value2, "workDataId");
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

        public Criteria andWorkTypeIsNull() {
            addCriterion("WORK_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andWorkTypeIsNotNull() {
            addCriterion("WORK_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andWorkTypeEqualTo(String value) {
            addCriterion("WORK_TYPE =", value, "workType");
            return (Criteria) this;
        }

        public Criteria andWorkTypeNotEqualTo(String value) {
            addCriterion("WORK_TYPE <>", value, "workType");
            return (Criteria) this;
        }

        public Criteria andWorkTypeGreaterThan(String value) {
            addCriterion("WORK_TYPE >", value, "workType");
            return (Criteria) this;
        }

        public Criteria andWorkTypeGreaterThanOrEqualTo(String value) {
            addCriterion("WORK_TYPE >=", value, "workType");
            return (Criteria) this;
        }

        public Criteria andWorkTypeLessThan(String value) {
            addCriterion("WORK_TYPE <", value, "workType");
            return (Criteria) this;
        }

        public Criteria andWorkTypeLessThanOrEqualTo(String value) {
            addCriterion("WORK_TYPE <=", value, "workType");
            return (Criteria) this;
        }

        public Criteria andWorkTypeLike(String value) {
            addCriterion("WORK_TYPE like", value, "workType");
            return (Criteria) this;
        }

        public Criteria andWorkTypeNotLike(String value) {
            addCriterion("WORK_TYPE not like", value, "workType");
            return (Criteria) this;
        }

        public Criteria andWorkTypeIn(List<String> values) {
            addCriterion("WORK_TYPE in", values, "workType");
            return (Criteria) this;
        }

        public Criteria andWorkTypeNotIn(List<String> values) {
            addCriterion("WORK_TYPE not in", values, "workType");
            return (Criteria) this;
        }

        public Criteria andWorkTypeBetween(String value1, String value2) {
            addCriterion("WORK_TYPE between", value1, value2, "workType");
            return (Criteria) this;
        }

        public Criteria andWorkTypeNotBetween(String value1, String value2) {
            addCriterion("WORK_TYPE not between", value1, value2, "workType");
            return (Criteria) this;
        }

        public Criteria andQuantityIsNull() {
            addCriterion("QUANTITY is null");
            return (Criteria) this;
        }

        public Criteria andQuantityIsNotNull() {
            addCriterion("QUANTITY is not null");
            return (Criteria) this;
        }

        public Criteria andQuantityEqualTo(BigDecimal value) {
            addCriterion("QUANTITY =", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotEqualTo(BigDecimal value) {
            addCriterion("QUANTITY <>", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityGreaterThan(BigDecimal value) {
            addCriterion("QUANTITY >", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("QUANTITY >=", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityLessThan(BigDecimal value) {
            addCriterion("QUANTITY <", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityLessThanOrEqualTo(BigDecimal value) {
            addCriterion("QUANTITY <=", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityIn(List<BigDecimal> values) {
            addCriterion("QUANTITY in", values, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotIn(List<BigDecimal> values) {
            addCriterion("QUANTITY not in", values, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("QUANTITY between", value1, value2, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("QUANTITY not between", value1, value2, "quantity");
            return (Criteria) this;
        }

        public Criteria andQualityIsNull() {
            addCriterion("QUALITY is null");
            return (Criteria) this;
        }

        public Criteria andQualityIsNotNull() {
            addCriterion("QUALITY is not null");
            return (Criteria) this;
        }

        public Criteria andQualityEqualTo(BigDecimal value) {
            addCriterion("QUALITY =", value, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityNotEqualTo(BigDecimal value) {
            addCriterion("QUALITY <>", value, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityGreaterThan(BigDecimal value) {
            addCriterion("QUALITY >", value, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("QUALITY >=", value, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityLessThan(BigDecimal value) {
            addCriterion("QUALITY <", value, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityLessThanOrEqualTo(BigDecimal value) {
            addCriterion("QUALITY <=", value, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityIn(List<BigDecimal> values) {
            addCriterion("QUALITY in", values, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityNotIn(List<BigDecimal> values) {
            addCriterion("QUALITY not in", values, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("QUALITY between", value1, value2, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("QUALITY not between", value1, value2, "quality");
            return (Criteria) this;
        }

        public Criteria andQErrorIsNull() {
            addCriterion("Q_ERROR is null");
            return (Criteria) this;
        }

        public Criteria andQErrorIsNotNull() {
            addCriterion("Q_ERROR is not null");
            return (Criteria) this;
        }

        public Criteria andQErrorEqualTo(BigDecimal value) {
            addCriterion("Q_ERROR =", value, "qError");
            return (Criteria) this;
        }

        public Criteria andQErrorNotEqualTo(BigDecimal value) {
            addCriterion("Q_ERROR <>", value, "qError");
            return (Criteria) this;
        }

        public Criteria andQErrorGreaterThan(BigDecimal value) {
            addCriterion("Q_ERROR >", value, "qError");
            return (Criteria) this;
        }

        public Criteria andQErrorGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("Q_ERROR >=", value, "qError");
            return (Criteria) this;
        }

        public Criteria andQErrorLessThan(BigDecimal value) {
            addCriterion("Q_ERROR <", value, "qError");
            return (Criteria) this;
        }

        public Criteria andQErrorLessThanOrEqualTo(BigDecimal value) {
            addCriterion("Q_ERROR <=", value, "qError");
            return (Criteria) this;
        }

        public Criteria andQErrorIn(List<BigDecimal> values) {
            addCriterion("Q_ERROR in", values, "qError");
            return (Criteria) this;
        }

        public Criteria andQErrorNotIn(List<BigDecimal> values) {
            addCriterion("Q_ERROR not in", values, "qError");
            return (Criteria) this;
        }

        public Criteria andQErrorBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Q_ERROR between", value1, value2, "qError");
            return (Criteria) this;
        }

        public Criteria andQErrorNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Q_ERROR not between", value1, value2, "qError");
            return (Criteria) this;
        }

        public Criteria andQRateIsNull() {
            addCriterion("Q_RATE is null");
            return (Criteria) this;
        }

        public Criteria andQRateIsNotNull() {
            addCriterion("Q_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andQRateEqualTo(BigDecimal value) {
            addCriterion("Q_RATE =", value, "qRate");
            return (Criteria) this;
        }

        public Criteria andQRateNotEqualTo(BigDecimal value) {
            addCriterion("Q_RATE <>", value, "qRate");
            return (Criteria) this;
        }

        public Criteria andQRateGreaterThan(BigDecimal value) {
            addCriterion("Q_RATE >", value, "qRate");
            return (Criteria) this;
        }

        public Criteria andQRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("Q_RATE >=", value, "qRate");
            return (Criteria) this;
        }

        public Criteria andQRateLessThan(BigDecimal value) {
            addCriterion("Q_RATE <", value, "qRate");
            return (Criteria) this;
        }

        public Criteria andQRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("Q_RATE <=", value, "qRate");
            return (Criteria) this;
        }

        public Criteria andQRateIn(List<BigDecimal> values) {
            addCriterion("Q_RATE in", values, "qRate");
            return (Criteria) this;
        }

        public Criteria andQRateNotIn(List<BigDecimal> values) {
            addCriterion("Q_RATE not in", values, "qRate");
            return (Criteria) this;
        }

        public Criteria andQRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Q_RATE between", value1, value2, "qRate");
            return (Criteria) this;
        }

        public Criteria andQRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Q_RATE not between", value1, value2, "qRate");
            return (Criteria) this;
        }

        public Criteria andMonitorIsNull() {
            addCriterion("MONITOR is null");
            return (Criteria) this;
        }

        public Criteria andMonitorIsNotNull() {
            addCriterion("MONITOR is not null");
            return (Criteria) this;
        }

        public Criteria andMonitorEqualTo(BigDecimal value) {
            addCriterion("MONITOR =", value, "monitor");
            return (Criteria) this;
        }

        public Criteria andMonitorNotEqualTo(BigDecimal value) {
            addCriterion("MONITOR <>", value, "monitor");
            return (Criteria) this;
        }

        public Criteria andMonitorGreaterThan(BigDecimal value) {
            addCriterion("MONITOR >", value, "monitor");
            return (Criteria) this;
        }

        public Criteria andMonitorGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("MONITOR >=", value, "monitor");
            return (Criteria) this;
        }

        public Criteria andMonitorLessThan(BigDecimal value) {
            addCriterion("MONITOR <", value, "monitor");
            return (Criteria) this;
        }

        public Criteria andMonitorLessThanOrEqualTo(BigDecimal value) {
            addCriterion("MONITOR <=", value, "monitor");
            return (Criteria) this;
        }

        public Criteria andMonitorIn(List<BigDecimal> values) {
            addCriterion("MONITOR in", values, "monitor");
            return (Criteria) this;
        }

        public Criteria andMonitorNotIn(List<BigDecimal> values) {
            addCriterion("MONITOR not in", values, "monitor");
            return (Criteria) this;
        }

        public Criteria andMonitorBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MONITOR between", value1, value2, "monitor");
            return (Criteria) this;
        }

        public Criteria andMonitorNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MONITOR not between", value1, value2, "monitor");
            return (Criteria) this;
        }

        public Criteria andMErrorIsNull() {
            addCriterion("M_ERROR is null");
            return (Criteria) this;
        }

        public Criteria andMErrorIsNotNull() {
            addCriterion("M_ERROR is not null");
            return (Criteria) this;
        }

        public Criteria andMErrorEqualTo(BigDecimal value) {
            addCriterion("M_ERROR =", value, "mError");
            return (Criteria) this;
        }

        public Criteria andMErrorNotEqualTo(BigDecimal value) {
            addCriterion("M_ERROR <>", value, "mError");
            return (Criteria) this;
        }

        public Criteria andMErrorGreaterThan(BigDecimal value) {
            addCriterion("M_ERROR >", value, "mError");
            return (Criteria) this;
        }

        public Criteria andMErrorGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("M_ERROR >=", value, "mError");
            return (Criteria) this;
        }

        public Criteria andMErrorLessThan(BigDecimal value) {
            addCriterion("M_ERROR <", value, "mError");
            return (Criteria) this;
        }

        public Criteria andMErrorLessThanOrEqualTo(BigDecimal value) {
            addCriterion("M_ERROR <=", value, "mError");
            return (Criteria) this;
        }

        public Criteria andMErrorIn(List<BigDecimal> values) {
            addCriterion("M_ERROR in", values, "mError");
            return (Criteria) this;
        }

        public Criteria andMErrorNotIn(List<BigDecimal> values) {
            addCriterion("M_ERROR not in", values, "mError");
            return (Criteria) this;
        }

        public Criteria andMErrorBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("M_ERROR between", value1, value2, "mError");
            return (Criteria) this;
        }

        public Criteria andMErrorNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("M_ERROR not between", value1, value2, "mError");
            return (Criteria) this;
        }

        public Criteria andMRateIsNull() {
            addCriterion("M_RATE is null");
            return (Criteria) this;
        }

        public Criteria andMRateIsNotNull() {
            addCriterion("M_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andMRateEqualTo(BigDecimal value) {
            addCriterion("M_RATE =", value, "mRate");
            return (Criteria) this;
        }

        public Criteria andMRateNotEqualTo(BigDecimal value) {
            addCriterion("M_RATE <>", value, "mRate");
            return (Criteria) this;
        }

        public Criteria andMRateGreaterThan(BigDecimal value) {
            addCriterion("M_RATE >", value, "mRate");
            return (Criteria) this;
        }

        public Criteria andMRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("M_RATE >=", value, "mRate");
            return (Criteria) this;
        }

        public Criteria andMRateLessThan(BigDecimal value) {
            addCriterion("M_RATE <", value, "mRate");
            return (Criteria) this;
        }

        public Criteria andMRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("M_RATE <=", value, "mRate");
            return (Criteria) this;
        }

        public Criteria andMRateIn(List<BigDecimal> values) {
            addCriterion("M_RATE in", values, "mRate");
            return (Criteria) this;
        }

        public Criteria andMRateNotIn(List<BigDecimal> values) {
            addCriterion("M_RATE not in", values, "mRate");
            return (Criteria) this;
        }

        public Criteria andMRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("M_RATE between", value1, value2, "mRate");
            return (Criteria) this;
        }

        public Criteria andMRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("M_RATE not between", value1, value2, "mRate");
            return (Criteria) this;
        }

        public Criteria andMajorErrorIsNull() {
            addCriterion("MAJOR_ERROR is null");
            return (Criteria) this;
        }

        public Criteria andMajorErrorIsNotNull() {
            addCriterion("MAJOR_ERROR is not null");
            return (Criteria) this;
        }

        public Criteria andMajorErrorEqualTo(BigDecimal value) {
            addCriterion("MAJOR_ERROR =", value, "majorError");
            return (Criteria) this;
        }

        public Criteria andMajorErrorNotEqualTo(BigDecimal value) {
            addCriterion("MAJOR_ERROR <>", value, "majorError");
            return (Criteria) this;
        }

        public Criteria andMajorErrorGreaterThan(BigDecimal value) {
            addCriterion("MAJOR_ERROR >", value, "majorError");
            return (Criteria) this;
        }

        public Criteria andMajorErrorGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("MAJOR_ERROR >=", value, "majorError");
            return (Criteria) this;
        }

        public Criteria andMajorErrorLessThan(BigDecimal value) {
            addCriterion("MAJOR_ERROR <", value, "majorError");
            return (Criteria) this;
        }

        public Criteria andMajorErrorLessThanOrEqualTo(BigDecimal value) {
            addCriterion("MAJOR_ERROR <=", value, "majorError");
            return (Criteria) this;
        }

        public Criteria andMajorErrorIn(List<BigDecimal> values) {
            addCriterion("MAJOR_ERROR in", values, "majorError");
            return (Criteria) this;
        }

        public Criteria andMajorErrorNotIn(List<BigDecimal> values) {
            addCriterion("MAJOR_ERROR not in", values, "majorError");
            return (Criteria) this;
        }

        public Criteria andMajorErrorBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MAJOR_ERROR between", value1, value2, "majorError");
            return (Criteria) this;
        }

        public Criteria andMajorErrorNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MAJOR_ERROR not between", value1, value2, "majorError");
            return (Criteria) this;
        }

        public Criteria andEfficiencyIsNull() {
            addCriterion("EFFICIENCY is null");
            return (Criteria) this;
        }

        public Criteria andEfficiencyIsNotNull() {
            addCriterion("EFFICIENCY is not null");
            return (Criteria) this;
        }

        public Criteria andEfficiencyEqualTo(BigDecimal value) {
            addCriterion("EFFICIENCY =", value, "efficiency");
            return (Criteria) this;
        }

        public Criteria andEfficiencyNotEqualTo(BigDecimal value) {
            addCriterion("EFFICIENCY <>", value, "efficiency");
            return (Criteria) this;
        }

        public Criteria andEfficiencyGreaterThan(BigDecimal value) {
            addCriterion("EFFICIENCY >", value, "efficiency");
            return (Criteria) this;
        }

        public Criteria andEfficiencyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("EFFICIENCY >=", value, "efficiency");
            return (Criteria) this;
        }

        public Criteria andEfficiencyLessThan(BigDecimal value) {
            addCriterion("EFFICIENCY <", value, "efficiency");
            return (Criteria) this;
        }

        public Criteria andEfficiencyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("EFFICIENCY <=", value, "efficiency");
            return (Criteria) this;
        }

        public Criteria andEfficiencyIn(List<BigDecimal> values) {
            addCriterion("EFFICIENCY in", values, "efficiency");
            return (Criteria) this;
        }

        public Criteria andEfficiencyNotIn(List<BigDecimal> values) {
            addCriterion("EFFICIENCY not in", values, "efficiency");
            return (Criteria) this;
        }

        public Criteria andEfficiencyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("EFFICIENCY between", value1, value2, "efficiency");
            return (Criteria) this;
        }

        public Criteria andEfficiencyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("EFFICIENCY not between", value1, value2, "efficiency");
            return (Criteria) this;
        }

        public Criteria andQEfficiencyIsNull() {
            addCriterion("Q_EFFICIENCY is null");
            return (Criteria) this;
        }

        public Criteria andQEfficiencyIsNotNull() {
            addCriterion("Q_EFFICIENCY is not null");
            return (Criteria) this;
        }

        public Criteria andQEfficiencyEqualTo(BigDecimal value) {
            addCriterion("Q_EFFICIENCY =", value, "qEfficiency");
            return (Criteria) this;
        }

        public Criteria andQEfficiencyNotEqualTo(BigDecimal value) {
            addCriterion("Q_EFFICIENCY <>", value, "qEfficiency");
            return (Criteria) this;
        }

        public Criteria andQEfficiencyGreaterThan(BigDecimal value) {
            addCriterion("Q_EFFICIENCY >", value, "qEfficiency");
            return (Criteria) this;
        }

        public Criteria andQEfficiencyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("Q_EFFICIENCY >=", value, "qEfficiency");
            return (Criteria) this;
        }

        public Criteria andQEfficiencyLessThan(BigDecimal value) {
            addCriterion("Q_EFFICIENCY <", value, "qEfficiency");
            return (Criteria) this;
        }

        public Criteria andQEfficiencyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("Q_EFFICIENCY <=", value, "qEfficiency");
            return (Criteria) this;
        }

        public Criteria andQEfficiencyIn(List<BigDecimal> values) {
            addCriterion("Q_EFFICIENCY in", values, "qEfficiency");
            return (Criteria) this;
        }

        public Criteria andQEfficiencyNotIn(List<BigDecimal> values) {
            addCriterion("Q_EFFICIENCY not in", values, "qEfficiency");
            return (Criteria) this;
        }

        public Criteria andQEfficiencyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Q_EFFICIENCY between", value1, value2, "qEfficiency");
            return (Criteria) this;
        }

        public Criteria andQEfficiencyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Q_EFFICIENCY not between", value1, value2, "qEfficiency");
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