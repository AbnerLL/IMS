package com.navinfo.IMS.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DepInfoReportExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DepInfoReportExample() {
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

        public Criteria andSectionIsNull() {
            addCriterion("SECTION is null");
            return (Criteria) this;
        }

        public Criteria andSectionIsNotNull() {
            addCriterion("SECTION is not null");
            return (Criteria) this;
        }

        public Criteria andSectionEqualTo(String value) {
            addCriterion("SECTION =", value, "section");
            return (Criteria) this;
        }

        public Criteria andSectionNotEqualTo(String value) {
            addCriterion("SECTION <>", value, "section");
            return (Criteria) this;
        }

        public Criteria andSectionGreaterThan(String value) {
            addCriterion("SECTION >", value, "section");
            return (Criteria) this;
        }

        public Criteria andSectionGreaterThanOrEqualTo(String value) {
            addCriterion("SECTION >=", value, "section");
            return (Criteria) this;
        }

        public Criteria andSectionLessThan(String value) {
            addCriterion("SECTION <", value, "section");
            return (Criteria) this;
        }

        public Criteria andSectionLessThanOrEqualTo(String value) {
            addCriterion("SECTION <=", value, "section");
            return (Criteria) this;
        }

        public Criteria andSectionLike(String value) {
            addCriterion("SECTION like", value, "section");
            return (Criteria) this;
        }

        public Criteria andSectionNotLike(String value) {
            addCriterion("SECTION not like", value, "section");
            return (Criteria) this;
        }

        public Criteria andSectionIn(List<String> values) {
            addCriterion("SECTION in", values, "section");
            return (Criteria) this;
        }

        public Criteria andSectionNotIn(List<String> values) {
            addCriterion("SECTION not in", values, "section");
            return (Criteria) this;
        }

        public Criteria andSectionBetween(String value1, String value2) {
            addCriterion("SECTION between", value1, value2, "section");
            return (Criteria) this;
        }

        public Criteria andSectionNotBetween(String value1, String value2) {
            addCriterion("SECTION not between", value1, value2, "section");
            return (Criteria) this;
        }

        public Criteria andWorkerIdIsNull() {
            addCriterion("WORKER_ID is null");
            return (Criteria) this;
        }

        public Criteria andWorkerIdIsNotNull() {
            addCriterion("WORKER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andWorkerIdEqualTo(String value) {
            addCriterion("WORKER_ID =", value, "workerId");
            return (Criteria) this;
        }

        public Criteria andWorkerIdNotEqualTo(String value) {
            addCriterion("WORKER_ID <>", value, "workerId");
            return (Criteria) this;
        }

        public Criteria andWorkerIdGreaterThan(String value) {
            addCriterion("WORKER_ID >", value, "workerId");
            return (Criteria) this;
        }

        public Criteria andWorkerIdGreaterThanOrEqualTo(String value) {
            addCriterion("WORKER_ID >=", value, "workerId");
            return (Criteria) this;
        }

        public Criteria andWorkerIdLessThan(String value) {
            addCriterion("WORKER_ID <", value, "workerId");
            return (Criteria) this;
        }

        public Criteria andWorkerIdLessThanOrEqualTo(String value) {
            addCriterion("WORKER_ID <=", value, "workerId");
            return (Criteria) this;
        }

        public Criteria andWorkerIdLike(String value) {
            addCriterion("WORKER_ID like", value, "workerId");
            return (Criteria) this;
        }

        public Criteria andWorkerIdNotLike(String value) {
            addCriterion("WORKER_ID not like", value, "workerId");
            return (Criteria) this;
        }

        public Criteria andWorkerIdIn(List<String> values) {
            addCriterion("WORKER_ID in", values, "workerId");
            return (Criteria) this;
        }

        public Criteria andWorkerIdNotIn(List<String> values) {
            addCriterion("WORKER_ID not in", values, "workerId");
            return (Criteria) this;
        }

        public Criteria andWorkerIdBetween(String value1, String value2) {
            addCriterion("WORKER_ID between", value1, value2, "workerId");
            return (Criteria) this;
        }

        public Criteria andWorkerIdNotBetween(String value1, String value2) {
            addCriterion("WORKER_ID not between", value1, value2, "workerId");
            return (Criteria) this;
        }

        public Criteria andWorkerIsNull() {
            addCriterion("WORKER is null");
            return (Criteria) this;
        }

        public Criteria andWorkerIsNotNull() {
            addCriterion("WORKER is not null");
            return (Criteria) this;
        }

        public Criteria andWorkerEqualTo(String value) {
            addCriterion("WORKER =", value, "worker");
            return (Criteria) this;
        }

        public Criteria andWorkerNotEqualTo(String value) {
            addCriterion("WORKER <>", value, "worker");
            return (Criteria) this;
        }

        public Criteria andWorkerGreaterThan(String value) {
            addCriterion("WORKER >", value, "worker");
            return (Criteria) this;
        }

        public Criteria andWorkerGreaterThanOrEqualTo(String value) {
            addCriterion("WORKER >=", value, "worker");
            return (Criteria) this;
        }

        public Criteria andWorkerLessThan(String value) {
            addCriterion("WORKER <", value, "worker");
            return (Criteria) this;
        }

        public Criteria andWorkerLessThanOrEqualTo(String value) {
            addCriterion("WORKER <=", value, "worker");
            return (Criteria) this;
        }

        public Criteria andWorkerLike(String value) {
            addCriterion("WORKER like", value, "worker");
            return (Criteria) this;
        }

        public Criteria andWorkerNotLike(String value) {
            addCriterion("WORKER not like", value, "worker");
            return (Criteria) this;
        }

        public Criteria andWorkerIn(List<String> values) {
            addCriterion("WORKER in", values, "worker");
            return (Criteria) this;
        }

        public Criteria andWorkerNotIn(List<String> values) {
            addCriterion("WORKER not in", values, "worker");
            return (Criteria) this;
        }

        public Criteria andWorkerBetween(String value1, String value2) {
            addCriterion("WORKER between", value1, value2, "worker");
            return (Criteria) this;
        }

        public Criteria andWorkerNotBetween(String value1, String value2) {
            addCriterion("WORKER not between", value1, value2, "worker");
            return (Criteria) this;
        }

        public Criteria andWorkDateIsNull() {
            addCriterion("WORK_DATE is null");
            return (Criteria) this;
        }

        public Criteria andWorkDateIsNotNull() {
            addCriterion("WORK_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andWorkDateEqualTo(Date value) {
            addCriterion("WORK_DATE =", value, "workDate");
            return (Criteria) this;
        }

        public Criteria andWorkDateNotEqualTo(Date value) {
            addCriterion("WORK_DATE <>", value, "workDate");
            return (Criteria) this;
        }

        public Criteria andWorkDateGreaterThan(Date value) {
            addCriterion("WORK_DATE >", value, "workDate");
            return (Criteria) this;
        }

        public Criteria andWorkDateGreaterThanOrEqualTo(Date value) {
            addCriterion("WORK_DATE >=", value, "workDate");
            return (Criteria) this;
        }

        public Criteria andWorkDateLessThan(Date value) {
            addCriterion("WORK_DATE <", value, "workDate");
            return (Criteria) this;
        }

        public Criteria andWorkDateLessThanOrEqualTo(Date value) {
            addCriterion("WORK_DATE <=", value, "workDate");
            return (Criteria) this;
        }

        public Criteria andWorkDateIn(List<Date> values) {
            addCriterion("WORK_DATE in", values, "workDate");
            return (Criteria) this;
        }

        public Criteria andWorkDateNotIn(List<Date> values) {
            addCriterion("WORK_DATE not in", values, "workDate");
            return (Criteria) this;
        }

        public Criteria andWorkDateBetween(Date value1, Date value2) {
            addCriterion("WORK_DATE between", value1, value2, "workDate");
            return (Criteria) this;
        }

        public Criteria andWorkDateNotBetween(Date value1, Date value2) {
            addCriterion("WORK_DATE not between", value1, value2, "workDate");
            return (Criteria) this;
        }

        public Criteria andDepInfoIsNull() {
            addCriterion("DEP_INFO is null");
            return (Criteria) this;
        }

        public Criteria andDepInfoIsNotNull() {
            addCriterion("DEP_INFO is not null");
            return (Criteria) this;
        }

        public Criteria andDepInfoEqualTo(String value) {
            addCriterion("DEP_INFO =", value, "depInfo");
            return (Criteria) this;
        }

        public Criteria andDepInfoNotEqualTo(String value) {
            addCriterion("DEP_INFO <>", value, "depInfo");
            return (Criteria) this;
        }

        public Criteria andDepInfoGreaterThan(String value) {
            addCriterion("DEP_INFO >", value, "depInfo");
            return (Criteria) this;
        }

        public Criteria andDepInfoGreaterThanOrEqualTo(String value) {
            addCriterion("DEP_INFO >=", value, "depInfo");
            return (Criteria) this;
        }

        public Criteria andDepInfoLessThan(String value) {
            addCriterion("DEP_INFO <", value, "depInfo");
            return (Criteria) this;
        }

        public Criteria andDepInfoLessThanOrEqualTo(String value) {
            addCriterion("DEP_INFO <=", value, "depInfo");
            return (Criteria) this;
        }

        public Criteria andDepInfoLike(String value) {
            addCriterion("DEP_INFO like", value, "depInfo");
            return (Criteria) this;
        }

        public Criteria andDepInfoNotLike(String value) {
            addCriterion("DEP_INFO not like", value, "depInfo");
            return (Criteria) this;
        }

        public Criteria andDepInfoIn(List<String> values) {
            addCriterion("DEP_INFO in", values, "depInfo");
            return (Criteria) this;
        }

        public Criteria andDepInfoNotIn(List<String> values) {
            addCriterion("DEP_INFO not in", values, "depInfo");
            return (Criteria) this;
        }

        public Criteria andDepInfoBetween(String value1, String value2) {
            addCriterion("DEP_INFO between", value1, value2, "depInfo");
            return (Criteria) this;
        }

        public Criteria andDepInfoNotBetween(String value1, String value2) {
            addCriterion("DEP_INFO not between", value1, value2, "depInfo");
            return (Criteria) this;
        }

        public Criteria andDcomAuditNumIsNull() {
            addCriterion("DCOM_AUDIT_NUM is null");
            return (Criteria) this;
        }

        public Criteria andDcomAuditNumIsNotNull() {
            addCriterion("DCOM_AUDIT_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andDcomAuditNumEqualTo(BigDecimal value) {
            addCriterion("DCOM_AUDIT_NUM =", value, "dcomAuditNum");
            return (Criteria) this;
        }

        public Criteria andDcomAuditNumNotEqualTo(BigDecimal value) {
            addCriterion("DCOM_AUDIT_NUM <>", value, "dcomAuditNum");
            return (Criteria) this;
        }

        public Criteria andDcomAuditNumGreaterThan(BigDecimal value) {
            addCriterion("DCOM_AUDIT_NUM >", value, "dcomAuditNum");
            return (Criteria) this;
        }

        public Criteria andDcomAuditNumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("DCOM_AUDIT_NUM >=", value, "dcomAuditNum");
            return (Criteria) this;
        }

        public Criteria andDcomAuditNumLessThan(BigDecimal value) {
            addCriterion("DCOM_AUDIT_NUM <", value, "dcomAuditNum");
            return (Criteria) this;
        }

        public Criteria andDcomAuditNumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("DCOM_AUDIT_NUM <=", value, "dcomAuditNum");
            return (Criteria) this;
        }

        public Criteria andDcomAuditNumIn(List<BigDecimal> values) {
            addCriterion("DCOM_AUDIT_NUM in", values, "dcomAuditNum");
            return (Criteria) this;
        }

        public Criteria andDcomAuditNumNotIn(List<BigDecimal> values) {
            addCriterion("DCOM_AUDIT_NUM not in", values, "dcomAuditNum");
            return (Criteria) this;
        }

        public Criteria andDcomAuditNumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DCOM_AUDIT_NUM between", value1, value2, "dcomAuditNum");
            return (Criteria) this;
        }

        public Criteria andDcomAuditNumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DCOM_AUDIT_NUM not between", value1, value2, "dcomAuditNum");
            return (Criteria) this;
        }

        public Criteria andDcomErrorNumIsNull() {
            addCriterion("DCOM_ERROR_NUM is null");
            return (Criteria) this;
        }

        public Criteria andDcomErrorNumIsNotNull() {
            addCriterion("DCOM_ERROR_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andDcomErrorNumEqualTo(BigDecimal value) {
            addCriterion("DCOM_ERROR_NUM =", value, "dcomErrorNum");
            return (Criteria) this;
        }

        public Criteria andDcomErrorNumNotEqualTo(BigDecimal value) {
            addCriterion("DCOM_ERROR_NUM <>", value, "dcomErrorNum");
            return (Criteria) this;
        }

        public Criteria andDcomErrorNumGreaterThan(BigDecimal value) {
            addCriterion("DCOM_ERROR_NUM >", value, "dcomErrorNum");
            return (Criteria) this;
        }

        public Criteria andDcomErrorNumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("DCOM_ERROR_NUM >=", value, "dcomErrorNum");
            return (Criteria) this;
        }

        public Criteria andDcomErrorNumLessThan(BigDecimal value) {
            addCriterion("DCOM_ERROR_NUM <", value, "dcomErrorNum");
            return (Criteria) this;
        }

        public Criteria andDcomErrorNumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("DCOM_ERROR_NUM <=", value, "dcomErrorNum");
            return (Criteria) this;
        }

        public Criteria andDcomErrorNumIn(List<BigDecimal> values) {
            addCriterion("DCOM_ERROR_NUM in", values, "dcomErrorNum");
            return (Criteria) this;
        }

        public Criteria andDcomErrorNumNotIn(List<BigDecimal> values) {
            addCriterion("DCOM_ERROR_NUM not in", values, "dcomErrorNum");
            return (Criteria) this;
        }

        public Criteria andDcomErrorNumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DCOM_ERROR_NUM between", value1, value2, "dcomErrorNum");
            return (Criteria) this;
        }

        public Criteria andDcomErrorNumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DCOM_ERROR_NUM not between", value1, value2, "dcomErrorNum");
            return (Criteria) this;
        }

        public Criteria andDcomPassRateIsNull() {
            addCriterion("DCOM_PASS_RATE is null");
            return (Criteria) this;
        }

        public Criteria andDcomPassRateIsNotNull() {
            addCriterion("DCOM_PASS_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andDcomPassRateEqualTo(BigDecimal value) {
            addCriterion("DCOM_PASS_RATE =", value, "dcomPassRate");
            return (Criteria) this;
        }

        public Criteria andDcomPassRateNotEqualTo(BigDecimal value) {
            addCriterion("DCOM_PASS_RATE <>", value, "dcomPassRate");
            return (Criteria) this;
        }

        public Criteria andDcomPassRateGreaterThan(BigDecimal value) {
            addCriterion("DCOM_PASS_RATE >", value, "dcomPassRate");
            return (Criteria) this;
        }

        public Criteria andDcomPassRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("DCOM_PASS_RATE >=", value, "dcomPassRate");
            return (Criteria) this;
        }

        public Criteria andDcomPassRateLessThan(BigDecimal value) {
            addCriterion("DCOM_PASS_RATE <", value, "dcomPassRate");
            return (Criteria) this;
        }

        public Criteria andDcomPassRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("DCOM_PASS_RATE <=", value, "dcomPassRate");
            return (Criteria) this;
        }

        public Criteria andDcomPassRateIn(List<BigDecimal> values) {
            addCriterion("DCOM_PASS_RATE in", values, "dcomPassRate");
            return (Criteria) this;
        }

        public Criteria andDcomPassRateNotIn(List<BigDecimal> values) {
            addCriterion("DCOM_PASS_RATE not in", values, "dcomPassRate");
            return (Criteria) this;
        }

        public Criteria andDcomPassRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DCOM_PASS_RATE between", value1, value2, "dcomPassRate");
            return (Criteria) this;
        }

        public Criteria andDcomPassRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DCOM_PASS_RATE not between", value1, value2, "dcomPassRate");
            return (Criteria) this;
        }

        public Criteria andDpAuditNumIsNull() {
            addCriterion("DP_AUDIT_NUM is null");
            return (Criteria) this;
        }

        public Criteria andDpAuditNumIsNotNull() {
            addCriterion("DP_AUDIT_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andDpAuditNumEqualTo(BigDecimal value) {
            addCriterion("DP_AUDIT_NUM =", value, "dpAuditNum");
            return (Criteria) this;
        }

        public Criteria andDpAuditNumNotEqualTo(BigDecimal value) {
            addCriterion("DP_AUDIT_NUM <>", value, "dpAuditNum");
            return (Criteria) this;
        }

        public Criteria andDpAuditNumGreaterThan(BigDecimal value) {
            addCriterion("DP_AUDIT_NUM >", value, "dpAuditNum");
            return (Criteria) this;
        }

        public Criteria andDpAuditNumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("DP_AUDIT_NUM >=", value, "dpAuditNum");
            return (Criteria) this;
        }

        public Criteria andDpAuditNumLessThan(BigDecimal value) {
            addCriterion("DP_AUDIT_NUM <", value, "dpAuditNum");
            return (Criteria) this;
        }

        public Criteria andDpAuditNumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("DP_AUDIT_NUM <=", value, "dpAuditNum");
            return (Criteria) this;
        }

        public Criteria andDpAuditNumIn(List<BigDecimal> values) {
            addCriterion("DP_AUDIT_NUM in", values, "dpAuditNum");
            return (Criteria) this;
        }

        public Criteria andDpAuditNumNotIn(List<BigDecimal> values) {
            addCriterion("DP_AUDIT_NUM not in", values, "dpAuditNum");
            return (Criteria) this;
        }

        public Criteria andDpAuditNumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DP_AUDIT_NUM between", value1, value2, "dpAuditNum");
            return (Criteria) this;
        }

        public Criteria andDpAuditNumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DP_AUDIT_NUM not between", value1, value2, "dpAuditNum");
            return (Criteria) this;
        }

        public Criteria andDpErrorNumIsNull() {
            addCriterion("DP_ERROR_NUM is null");
            return (Criteria) this;
        }

        public Criteria andDpErrorNumIsNotNull() {
            addCriterion("DP_ERROR_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andDpErrorNumEqualTo(BigDecimal value) {
            addCriterion("DP_ERROR_NUM =", value, "dpErrorNum");
            return (Criteria) this;
        }

        public Criteria andDpErrorNumNotEqualTo(BigDecimal value) {
            addCriterion("DP_ERROR_NUM <>", value, "dpErrorNum");
            return (Criteria) this;
        }

        public Criteria andDpErrorNumGreaterThan(BigDecimal value) {
            addCriterion("DP_ERROR_NUM >", value, "dpErrorNum");
            return (Criteria) this;
        }

        public Criteria andDpErrorNumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("DP_ERROR_NUM >=", value, "dpErrorNum");
            return (Criteria) this;
        }

        public Criteria andDpErrorNumLessThan(BigDecimal value) {
            addCriterion("DP_ERROR_NUM <", value, "dpErrorNum");
            return (Criteria) this;
        }

        public Criteria andDpErrorNumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("DP_ERROR_NUM <=", value, "dpErrorNum");
            return (Criteria) this;
        }

        public Criteria andDpErrorNumIn(List<BigDecimal> values) {
            addCriterion("DP_ERROR_NUM in", values, "dpErrorNum");
            return (Criteria) this;
        }

        public Criteria andDpErrorNumNotIn(List<BigDecimal> values) {
            addCriterion("DP_ERROR_NUM not in", values, "dpErrorNum");
            return (Criteria) this;
        }

        public Criteria andDpErrorNumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DP_ERROR_NUM between", value1, value2, "dpErrorNum");
            return (Criteria) this;
        }

        public Criteria andDpErrorNumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DP_ERROR_NUM not between", value1, value2, "dpErrorNum");
            return (Criteria) this;
        }

        public Criteria andDpPassRateIsNull() {
            addCriterion("DP_PASS_RATE is null");
            return (Criteria) this;
        }

        public Criteria andDpPassRateIsNotNull() {
            addCriterion("DP_PASS_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andDpPassRateEqualTo(BigDecimal value) {
            addCriterion("DP_PASS_RATE =", value, "dpPassRate");
            return (Criteria) this;
        }

        public Criteria andDpPassRateNotEqualTo(BigDecimal value) {
            addCriterion("DP_PASS_RATE <>", value, "dpPassRate");
            return (Criteria) this;
        }

        public Criteria andDpPassRateGreaterThan(BigDecimal value) {
            addCriterion("DP_PASS_RATE >", value, "dpPassRate");
            return (Criteria) this;
        }

        public Criteria andDpPassRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("DP_PASS_RATE >=", value, "dpPassRate");
            return (Criteria) this;
        }

        public Criteria andDpPassRateLessThan(BigDecimal value) {
            addCriterion("DP_PASS_RATE <", value, "dpPassRate");
            return (Criteria) this;
        }

        public Criteria andDpPassRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("DP_PASS_RATE <=", value, "dpPassRate");
            return (Criteria) this;
        }

        public Criteria andDpPassRateIn(List<BigDecimal> values) {
            addCriterion("DP_PASS_RATE in", values, "dpPassRate");
            return (Criteria) this;
        }

        public Criteria andDpPassRateNotIn(List<BigDecimal> values) {
            addCriterion("DP_PASS_RATE not in", values, "dpPassRate");
            return (Criteria) this;
        }

        public Criteria andDpPassRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DP_PASS_RATE between", value1, value2, "dpPassRate");
            return (Criteria) this;
        }

        public Criteria andDpPassRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DP_PASS_RATE not between", value1, value2, "dpPassRate");
            return (Criteria) this;
        }

        public Criteria andDcarAuditNumIsNull() {
            addCriterion("DCAR_AUDIT_NUM is null");
            return (Criteria) this;
        }

        public Criteria andDcarAuditNumIsNotNull() {
            addCriterion("DCAR_AUDIT_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andDcarAuditNumEqualTo(BigDecimal value) {
            addCriterion("DCAR_AUDIT_NUM =", value, "dcarAuditNum");
            return (Criteria) this;
        }

        public Criteria andDcarAuditNumNotEqualTo(BigDecimal value) {
            addCriterion("DCAR_AUDIT_NUM <>", value, "dcarAuditNum");
            return (Criteria) this;
        }

        public Criteria andDcarAuditNumGreaterThan(BigDecimal value) {
            addCriterion("DCAR_AUDIT_NUM >", value, "dcarAuditNum");
            return (Criteria) this;
        }

        public Criteria andDcarAuditNumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("DCAR_AUDIT_NUM >=", value, "dcarAuditNum");
            return (Criteria) this;
        }

        public Criteria andDcarAuditNumLessThan(BigDecimal value) {
            addCriterion("DCAR_AUDIT_NUM <", value, "dcarAuditNum");
            return (Criteria) this;
        }

        public Criteria andDcarAuditNumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("DCAR_AUDIT_NUM <=", value, "dcarAuditNum");
            return (Criteria) this;
        }

        public Criteria andDcarAuditNumIn(List<BigDecimal> values) {
            addCriterion("DCAR_AUDIT_NUM in", values, "dcarAuditNum");
            return (Criteria) this;
        }

        public Criteria andDcarAuditNumNotIn(List<BigDecimal> values) {
            addCriterion("DCAR_AUDIT_NUM not in", values, "dcarAuditNum");
            return (Criteria) this;
        }

        public Criteria andDcarAuditNumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DCAR_AUDIT_NUM between", value1, value2, "dcarAuditNum");
            return (Criteria) this;
        }

        public Criteria andDcarAuditNumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DCAR_AUDIT_NUM not between", value1, value2, "dcarAuditNum");
            return (Criteria) this;
        }

        public Criteria andDcarErrorNumIsNull() {
            addCriterion("DCAR_ERROR_NUM is null");
            return (Criteria) this;
        }

        public Criteria andDcarErrorNumIsNotNull() {
            addCriterion("DCAR_ERROR_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andDcarErrorNumEqualTo(BigDecimal value) {
            addCriterion("DCAR_ERROR_NUM =", value, "dcarErrorNum");
            return (Criteria) this;
        }

        public Criteria andDcarErrorNumNotEqualTo(BigDecimal value) {
            addCriterion("DCAR_ERROR_NUM <>", value, "dcarErrorNum");
            return (Criteria) this;
        }

        public Criteria andDcarErrorNumGreaterThan(BigDecimal value) {
            addCriterion("DCAR_ERROR_NUM >", value, "dcarErrorNum");
            return (Criteria) this;
        }

        public Criteria andDcarErrorNumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("DCAR_ERROR_NUM >=", value, "dcarErrorNum");
            return (Criteria) this;
        }

        public Criteria andDcarErrorNumLessThan(BigDecimal value) {
            addCriterion("DCAR_ERROR_NUM <", value, "dcarErrorNum");
            return (Criteria) this;
        }

        public Criteria andDcarErrorNumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("DCAR_ERROR_NUM <=", value, "dcarErrorNum");
            return (Criteria) this;
        }

        public Criteria andDcarErrorNumIn(List<BigDecimal> values) {
            addCriterion("DCAR_ERROR_NUM in", values, "dcarErrorNum");
            return (Criteria) this;
        }

        public Criteria andDcarErrorNumNotIn(List<BigDecimal> values) {
            addCriterion("DCAR_ERROR_NUM not in", values, "dcarErrorNum");
            return (Criteria) this;
        }

        public Criteria andDcarErrorNumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DCAR_ERROR_NUM between", value1, value2, "dcarErrorNum");
            return (Criteria) this;
        }

        public Criteria andDcarErrorNumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DCAR_ERROR_NUM not between", value1, value2, "dcarErrorNum");
            return (Criteria) this;
        }

        public Criteria andDcarPassRateIsNull() {
            addCriterion("DCAR_PASS_RATE is null");
            return (Criteria) this;
        }

        public Criteria andDcarPassRateIsNotNull() {
            addCriterion("DCAR_PASS_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andDcarPassRateEqualTo(BigDecimal value) {
            addCriterion("DCAR_PASS_RATE =", value, "dcarPassRate");
            return (Criteria) this;
        }

        public Criteria andDcarPassRateNotEqualTo(BigDecimal value) {
            addCriterion("DCAR_PASS_RATE <>", value, "dcarPassRate");
            return (Criteria) this;
        }

        public Criteria andDcarPassRateGreaterThan(BigDecimal value) {
            addCriterion("DCAR_PASS_RATE >", value, "dcarPassRate");
            return (Criteria) this;
        }

        public Criteria andDcarPassRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("DCAR_PASS_RATE >=", value, "dcarPassRate");
            return (Criteria) this;
        }

        public Criteria andDcarPassRateLessThan(BigDecimal value) {
            addCriterion("DCAR_PASS_RATE <", value, "dcarPassRate");
            return (Criteria) this;
        }

        public Criteria andDcarPassRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("DCAR_PASS_RATE <=", value, "dcarPassRate");
            return (Criteria) this;
        }

        public Criteria andDcarPassRateIn(List<BigDecimal> values) {
            addCriterion("DCAR_PASS_RATE in", values, "dcarPassRate");
            return (Criteria) this;
        }

        public Criteria andDcarPassRateNotIn(List<BigDecimal> values) {
            addCriterion("DCAR_PASS_RATE not in", values, "dcarPassRate");
            return (Criteria) this;
        }

        public Criteria andDcarPassRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DCAR_PASS_RATE between", value1, value2, "dcarPassRate");
            return (Criteria) this;
        }

        public Criteria andDcarPassRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DCAR_PASS_RATE not between", value1, value2, "dcarPassRate");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
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

        public Criteria andDelFlagIsNull() {
            addCriterion("DEL_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNotNull() {
            addCriterion("DEL_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andDelFlagEqualTo(String value) {
            addCriterion("DEL_FLAG =", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotEqualTo(String value) {
            addCriterion("DEL_FLAG <>", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThan(String value) {
            addCriterion("DEL_FLAG >", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThanOrEqualTo(String value) {
            addCriterion("DEL_FLAG >=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThan(String value) {
            addCriterion("DEL_FLAG <", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThanOrEqualTo(String value) {
            addCriterion("DEL_FLAG <=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLike(String value) {
            addCriterion("DEL_FLAG like", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotLike(String value) {
            addCriterion("DEL_FLAG not like", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagIn(List<String> values) {
            addCriterion("DEL_FLAG in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotIn(List<String> values) {
            addCriterion("DEL_FLAG not in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagBetween(String value1, String value2) {
            addCriterion("DEL_FLAG between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotBetween(String value1, String value2) {
            addCriterion("DEL_FLAG not between", value1, value2, "delFlag");
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