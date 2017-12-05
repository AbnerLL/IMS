package com.navinfo.IMS.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmpExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EmpExample() {
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

        public Criteria andEmpSexIsNull() {
            addCriterion("EMP_SEX is null");
            return (Criteria) this;
        }

        public Criteria andEmpSexIsNotNull() {
            addCriterion("EMP_SEX is not null");
            return (Criteria) this;
        }

        public Criteria andEmpSexEqualTo(String value) {
            addCriterion("EMP_SEX =", value, "empSex");
            return (Criteria) this;
        }

        public Criteria andEmpSexNotEqualTo(String value) {
            addCriterion("EMP_SEX <>", value, "empSex");
            return (Criteria) this;
        }

        public Criteria andEmpSexGreaterThan(String value) {
            addCriterion("EMP_SEX >", value, "empSex");
            return (Criteria) this;
        }

        public Criteria andEmpSexGreaterThanOrEqualTo(String value) {
            addCriterion("EMP_SEX >=", value, "empSex");
            return (Criteria) this;
        }

        public Criteria andEmpSexLessThan(String value) {
            addCriterion("EMP_SEX <", value, "empSex");
            return (Criteria) this;
        }

        public Criteria andEmpSexLessThanOrEqualTo(String value) {
            addCriterion("EMP_SEX <=", value, "empSex");
            return (Criteria) this;
        }

        public Criteria andEmpSexLike(String value) {
            addCriterion("EMP_SEX like", value, "empSex");
            return (Criteria) this;
        }

        public Criteria andEmpSexNotLike(String value) {
            addCriterion("EMP_SEX not like", value, "empSex");
            return (Criteria) this;
        }

        public Criteria andEmpSexIn(List<String> values) {
            addCriterion("EMP_SEX in", values, "empSex");
            return (Criteria) this;
        }

        public Criteria andEmpSexNotIn(List<String> values) {
            addCriterion("EMP_SEX not in", values, "empSex");
            return (Criteria) this;
        }

        public Criteria andEmpSexBetween(String value1, String value2) {
            addCriterion("EMP_SEX between", value1, value2, "empSex");
            return (Criteria) this;
        }

        public Criteria andEmpSexNotBetween(String value1, String value2) {
            addCriterion("EMP_SEX not between", value1, value2, "empSex");
            return (Criteria) this;
        }

        public Criteria andEmpHiredateIsNull() {
            addCriterion("EMP_HIREDATE is null");
            return (Criteria) this;
        }

        public Criteria andEmpHiredateIsNotNull() {
            addCriterion("EMP_HIREDATE is not null");
            return (Criteria) this;
        }

        public Criteria andEmpHiredateEqualTo(Date value) {
            addCriterion("EMP_HIREDATE =", value, "empHiredate");
            return (Criteria) this;
        }

        public Criteria andEmpHiredateNotEqualTo(Date value) {
            addCriterion("EMP_HIREDATE <>", value, "empHiredate");
            return (Criteria) this;
        }

        public Criteria andEmpHiredateGreaterThan(Date value) {
            addCriterion("EMP_HIREDATE >", value, "empHiredate");
            return (Criteria) this;
        }

        public Criteria andEmpHiredateGreaterThanOrEqualTo(Date value) {
            addCriterion("EMP_HIREDATE >=", value, "empHiredate");
            return (Criteria) this;
        }

        public Criteria andEmpHiredateLessThan(Date value) {
            addCriterion("EMP_HIREDATE <", value, "empHiredate");
            return (Criteria) this;
        }

        public Criteria andEmpHiredateLessThanOrEqualTo(Date value) {
            addCriterion("EMP_HIREDATE <=", value, "empHiredate");
            return (Criteria) this;
        }

        public Criteria andEmpHiredateIn(List<Date> values) {
            addCriterion("EMP_HIREDATE in", values, "empHiredate");
            return (Criteria) this;
        }

        public Criteria andEmpHiredateNotIn(List<Date> values) {
            addCriterion("EMP_HIREDATE not in", values, "empHiredate");
            return (Criteria) this;
        }

        public Criteria andEmpHiredateBetween(Date value1, Date value2) {
            addCriterion("EMP_HIREDATE between", value1, value2, "empHiredate");
            return (Criteria) this;
        }

        public Criteria andEmpHiredateNotBetween(Date value1, Date value2) {
            addCriterion("EMP_HIREDATE not between", value1, value2, "empHiredate");
            return (Criteria) this;
        }

        public Criteria andRankIsNull() {
            addCriterion("RANK is null");
            return (Criteria) this;
        }

        public Criteria andRankIsNotNull() {
            addCriterion("RANK is not null");
            return (Criteria) this;
        }

        public Criteria andRankEqualTo(String value) {
            addCriterion("RANK =", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankNotEqualTo(String value) {
            addCriterion("RANK <>", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankGreaterThan(String value) {
            addCriterion("RANK >", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankGreaterThanOrEqualTo(String value) {
            addCriterion("RANK >=", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankLessThan(String value) {
            addCriterion("RANK <", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankLessThanOrEqualTo(String value) {
            addCriterion("RANK <=", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankLike(String value) {
            addCriterion("RANK like", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankNotLike(String value) {
            addCriterion("RANK not like", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankIn(List<String> values) {
            addCriterion("RANK in", values, "rank");
            return (Criteria) this;
        }

        public Criteria andRankNotIn(List<String> values) {
            addCriterion("RANK not in", values, "rank");
            return (Criteria) this;
        }

        public Criteria andRankBetween(String value1, String value2) {
            addCriterion("RANK between", value1, value2, "rank");
            return (Criteria) this;
        }

        public Criteria andRankNotBetween(String value1, String value2) {
            addCriterion("RANK not between", value1, value2, "rank");
            return (Criteria) this;
        }

        public Criteria andEmpEntryAgeIsNull() {
            addCriterion("EMP_ENTRY_AGE is null");
            return (Criteria) this;
        }

        public Criteria andEmpEntryAgeIsNotNull() {
            addCriterion("EMP_ENTRY_AGE is not null");
            return (Criteria) this;
        }

        public Criteria andEmpEntryAgeEqualTo(BigDecimal value) {
            addCriterion("EMP_ENTRY_AGE =", value, "empEntryAge");
            return (Criteria) this;
        }

        public Criteria andEmpEntryAgeNotEqualTo(BigDecimal value) {
            addCriterion("EMP_ENTRY_AGE <>", value, "empEntryAge");
            return (Criteria) this;
        }

        public Criteria andEmpEntryAgeGreaterThan(BigDecimal value) {
            addCriterion("EMP_ENTRY_AGE >", value, "empEntryAge");
            return (Criteria) this;
        }

        public Criteria andEmpEntryAgeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("EMP_ENTRY_AGE >=", value, "empEntryAge");
            return (Criteria) this;
        }

        public Criteria andEmpEntryAgeLessThan(BigDecimal value) {
            addCriterion("EMP_ENTRY_AGE <", value, "empEntryAge");
            return (Criteria) this;
        }

        public Criteria andEmpEntryAgeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("EMP_ENTRY_AGE <=", value, "empEntryAge");
            return (Criteria) this;
        }

        public Criteria andEmpEntryAgeIn(List<BigDecimal> values) {
            addCriterion("EMP_ENTRY_AGE in", values, "empEntryAge");
            return (Criteria) this;
        }

        public Criteria andEmpEntryAgeNotIn(List<BigDecimal> values) {
            addCriterion("EMP_ENTRY_AGE not in", values, "empEntryAge");
            return (Criteria) this;
        }

        public Criteria andEmpEntryAgeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("EMP_ENTRY_AGE between", value1, value2, "empEntryAge");
            return (Criteria) this;
        }

        public Criteria andEmpEntryAgeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("EMP_ENTRY_AGE not between", value1, value2, "empEntryAge");
            return (Criteria) this;
        }

        public Criteria andEmpDeptIsNull() {
            addCriterion("EMP_DEPT is null");
            return (Criteria) this;
        }

        public Criteria andEmpDeptIsNotNull() {
            addCriterion("EMP_DEPT is not null");
            return (Criteria) this;
        }

        public Criteria andEmpDeptEqualTo(String value) {
            addCriterion("EMP_DEPT =", value, "empDept");
            return (Criteria) this;
        }

        public Criteria andEmpDeptNotEqualTo(String value) {
            addCriterion("EMP_DEPT <>", value, "empDept");
            return (Criteria) this;
        }

        public Criteria andEmpDeptGreaterThan(String value) {
            addCriterion("EMP_DEPT >", value, "empDept");
            return (Criteria) this;
        }

        public Criteria andEmpDeptGreaterThanOrEqualTo(String value) {
            addCriterion("EMP_DEPT >=", value, "empDept");
            return (Criteria) this;
        }

        public Criteria andEmpDeptLessThan(String value) {
            addCriterion("EMP_DEPT <", value, "empDept");
            return (Criteria) this;
        }

        public Criteria andEmpDeptLessThanOrEqualTo(String value) {
            addCriterion("EMP_DEPT <=", value, "empDept");
            return (Criteria) this;
        }

        public Criteria andEmpDeptLike(String value) {
            addCriterion("EMP_DEPT like", value, "empDept");
            return (Criteria) this;
        }

        public Criteria andEmpDeptNotLike(String value) {
            addCriterion("EMP_DEPT not like", value, "empDept");
            return (Criteria) this;
        }

        public Criteria andEmpDeptIn(List<String> values) {
            addCriterion("EMP_DEPT in", values, "empDept");
            return (Criteria) this;
        }

        public Criteria andEmpDeptNotIn(List<String> values) {
            addCriterion("EMP_DEPT not in", values, "empDept");
            return (Criteria) this;
        }

        public Criteria andEmpDeptBetween(String value1, String value2) {
            addCriterion("EMP_DEPT between", value1, value2, "empDept");
            return (Criteria) this;
        }

        public Criteria andEmpDeptNotBetween(String value1, String value2) {
            addCriterion("EMP_DEPT not between", value1, value2, "empDept");
            return (Criteria) this;
        }

        public Criteria andEmpSecIsNull() {
            addCriterion("EMP_SEC is null");
            return (Criteria) this;
        }

        public Criteria andEmpSecIsNotNull() {
            addCriterion("EMP_SEC is not null");
            return (Criteria) this;
        }

        public Criteria andEmpSecEqualTo(String value) {
            addCriterion("EMP_SEC =", value, "empSec");
            return (Criteria) this;
        }

        public Criteria andEmpSecNotEqualTo(String value) {
            addCriterion("EMP_SEC <>", value, "empSec");
            return (Criteria) this;
        }

        public Criteria andEmpSecGreaterThan(String value) {
            addCriterion("EMP_SEC >", value, "empSec");
            return (Criteria) this;
        }

        public Criteria andEmpSecGreaterThanOrEqualTo(String value) {
            addCriterion("EMP_SEC >=", value, "empSec");
            return (Criteria) this;
        }

        public Criteria andEmpSecLessThan(String value) {
            addCriterion("EMP_SEC <", value, "empSec");
            return (Criteria) this;
        }

        public Criteria andEmpSecLessThanOrEqualTo(String value) {
            addCriterion("EMP_SEC <=", value, "empSec");
            return (Criteria) this;
        }

        public Criteria andEmpSecLike(String value) {
            addCriterion("EMP_SEC like", value, "empSec");
            return (Criteria) this;
        }

        public Criteria andEmpSecNotLike(String value) {
            addCriterion("EMP_SEC not like", value, "empSec");
            return (Criteria) this;
        }

        public Criteria andEmpSecIn(List<String> values) {
            addCriterion("EMP_SEC in", values, "empSec");
            return (Criteria) this;
        }

        public Criteria andEmpSecNotIn(List<String> values) {
            addCriterion("EMP_SEC not in", values, "empSec");
            return (Criteria) this;
        }

        public Criteria andEmpSecBetween(String value1, String value2) {
            addCriterion("EMP_SEC between", value1, value2, "empSec");
            return (Criteria) this;
        }

        public Criteria andEmpSecNotBetween(String value1, String value2) {
            addCriterion("EMP_SEC not between", value1, value2, "empSec");
            return (Criteria) this;
        }

        public Criteria andEmpPostIsNull() {
            addCriterion("EMP_POST is null");
            return (Criteria) this;
        }

        public Criteria andEmpPostIsNotNull() {
            addCriterion("EMP_POST is not null");
            return (Criteria) this;
        }

        public Criteria andEmpPostEqualTo(String value) {
            addCriterion("EMP_POST =", value, "empPost");
            return (Criteria) this;
        }

        public Criteria andEmpPostNotEqualTo(String value) {
            addCriterion("EMP_POST <>", value, "empPost");
            return (Criteria) this;
        }

        public Criteria andEmpPostGreaterThan(String value) {
            addCriterion("EMP_POST >", value, "empPost");
            return (Criteria) this;
        }

        public Criteria andEmpPostGreaterThanOrEqualTo(String value) {
            addCriterion("EMP_POST >=", value, "empPost");
            return (Criteria) this;
        }

        public Criteria andEmpPostLessThan(String value) {
            addCriterion("EMP_POST <", value, "empPost");
            return (Criteria) this;
        }

        public Criteria andEmpPostLessThanOrEqualTo(String value) {
            addCriterion("EMP_POST <=", value, "empPost");
            return (Criteria) this;
        }

        public Criteria andEmpPostLike(String value) {
            addCriterion("EMP_POST like", value, "empPost");
            return (Criteria) this;
        }

        public Criteria andEmpPostNotLike(String value) {
            addCriterion("EMP_POST not like", value, "empPost");
            return (Criteria) this;
        }

        public Criteria andEmpPostIn(List<String> values) {
            addCriterion("EMP_POST in", values, "empPost");
            return (Criteria) this;
        }

        public Criteria andEmpPostNotIn(List<String> values) {
            addCriterion("EMP_POST not in", values, "empPost");
            return (Criteria) this;
        }

        public Criteria andEmpPostBetween(String value1, String value2) {
            addCriterion("EMP_POST between", value1, value2, "empPost");
            return (Criteria) this;
        }

        public Criteria andEmpPostNotBetween(String value1, String value2) {
            addCriterion("EMP_POST not between", value1, value2, "empPost");
            return (Criteria) this;
        }

        public Criteria andEmpEmailIsNull() {
            addCriterion("EMP_EMAIL is null");
            return (Criteria) this;
        }

        public Criteria andEmpEmailIsNotNull() {
            addCriterion("EMP_EMAIL is not null");
            return (Criteria) this;
        }

        public Criteria andEmpEmailEqualTo(String value) {
            addCriterion("EMP_EMAIL =", value, "empEmail");
            return (Criteria) this;
        }

        public Criteria andEmpEmailNotEqualTo(String value) {
            addCriterion("EMP_EMAIL <>", value, "empEmail");
            return (Criteria) this;
        }

        public Criteria andEmpEmailGreaterThan(String value) {
            addCriterion("EMP_EMAIL >", value, "empEmail");
            return (Criteria) this;
        }

        public Criteria andEmpEmailGreaterThanOrEqualTo(String value) {
            addCriterion("EMP_EMAIL >=", value, "empEmail");
            return (Criteria) this;
        }

        public Criteria andEmpEmailLessThan(String value) {
            addCriterion("EMP_EMAIL <", value, "empEmail");
            return (Criteria) this;
        }

        public Criteria andEmpEmailLessThanOrEqualTo(String value) {
            addCriterion("EMP_EMAIL <=", value, "empEmail");
            return (Criteria) this;
        }

        public Criteria andEmpEmailLike(String value) {
            addCriterion("EMP_EMAIL like", value, "empEmail");
            return (Criteria) this;
        }

        public Criteria andEmpEmailNotLike(String value) {
            addCriterion("EMP_EMAIL not like", value, "empEmail");
            return (Criteria) this;
        }

        public Criteria andEmpEmailIn(List<String> values) {
            addCriterion("EMP_EMAIL in", values, "empEmail");
            return (Criteria) this;
        }

        public Criteria andEmpEmailNotIn(List<String> values) {
            addCriterion("EMP_EMAIL not in", values, "empEmail");
            return (Criteria) this;
        }

        public Criteria andEmpEmailBetween(String value1, String value2) {
            addCriterion("EMP_EMAIL between", value1, value2, "empEmail");
            return (Criteria) this;
        }

        public Criteria andEmpEmailNotBetween(String value1, String value2) {
            addCriterion("EMP_EMAIL not between", value1, value2, "empEmail");
            return (Criteria) this;
        }

        public Criteria andEmpTelIsNull() {
            addCriterion("EMP_TEL is null");
            return (Criteria) this;
        }

        public Criteria andEmpTelIsNotNull() {
            addCriterion("EMP_TEL is not null");
            return (Criteria) this;
        }

        public Criteria andEmpTelEqualTo(String value) {
            addCriterion("EMP_TEL =", value, "empTel");
            return (Criteria) this;
        }

        public Criteria andEmpTelNotEqualTo(String value) {
            addCriterion("EMP_TEL <>", value, "empTel");
            return (Criteria) this;
        }

        public Criteria andEmpTelGreaterThan(String value) {
            addCriterion("EMP_TEL >", value, "empTel");
            return (Criteria) this;
        }

        public Criteria andEmpTelGreaterThanOrEqualTo(String value) {
            addCriterion("EMP_TEL >=", value, "empTel");
            return (Criteria) this;
        }

        public Criteria andEmpTelLessThan(String value) {
            addCriterion("EMP_TEL <", value, "empTel");
            return (Criteria) this;
        }

        public Criteria andEmpTelLessThanOrEqualTo(String value) {
            addCriterion("EMP_TEL <=", value, "empTel");
            return (Criteria) this;
        }

        public Criteria andEmpTelLike(String value) {
            addCriterion("EMP_TEL like", value, "empTel");
            return (Criteria) this;
        }

        public Criteria andEmpTelNotLike(String value) {
            addCriterion("EMP_TEL not like", value, "empTel");
            return (Criteria) this;
        }

        public Criteria andEmpTelIn(List<String> values) {
            addCriterion("EMP_TEL in", values, "empTel");
            return (Criteria) this;
        }

        public Criteria andEmpTelNotIn(List<String> values) {
            addCriterion("EMP_TEL not in", values, "empTel");
            return (Criteria) this;
        }

        public Criteria andEmpTelBetween(String value1, String value2) {
            addCriterion("EMP_TEL between", value1, value2, "empTel");
            return (Criteria) this;
        }

        public Criteria andEmpTelNotBetween(String value1, String value2) {
            addCriterion("EMP_TEL not between", value1, value2, "empTel");
            return (Criteria) this;
        }

        public Criteria andEmpAddressIsNull() {
            addCriterion("EMP_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andEmpAddressIsNotNull() {
            addCriterion("EMP_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andEmpAddressEqualTo(String value) {
            addCriterion("EMP_ADDRESS =", value, "empAddress");
            return (Criteria) this;
        }

        public Criteria andEmpAddressNotEqualTo(String value) {
            addCriterion("EMP_ADDRESS <>", value, "empAddress");
            return (Criteria) this;
        }

        public Criteria andEmpAddressGreaterThan(String value) {
            addCriterion("EMP_ADDRESS >", value, "empAddress");
            return (Criteria) this;
        }

        public Criteria andEmpAddressGreaterThanOrEqualTo(String value) {
            addCriterion("EMP_ADDRESS >=", value, "empAddress");
            return (Criteria) this;
        }

        public Criteria andEmpAddressLessThan(String value) {
            addCriterion("EMP_ADDRESS <", value, "empAddress");
            return (Criteria) this;
        }

        public Criteria andEmpAddressLessThanOrEqualTo(String value) {
            addCriterion("EMP_ADDRESS <=", value, "empAddress");
            return (Criteria) this;
        }

        public Criteria andEmpAddressLike(String value) {
            addCriterion("EMP_ADDRESS like", value, "empAddress");
            return (Criteria) this;
        }

        public Criteria andEmpAddressNotLike(String value) {
            addCriterion("EMP_ADDRESS not like", value, "empAddress");
            return (Criteria) this;
        }

        public Criteria andEmpAddressIn(List<String> values) {
            addCriterion("EMP_ADDRESS in", values, "empAddress");
            return (Criteria) this;
        }

        public Criteria andEmpAddressNotIn(List<String> values) {
            addCriterion("EMP_ADDRESS not in", values, "empAddress");
            return (Criteria) this;
        }

        public Criteria andEmpAddressBetween(String value1, String value2) {
            addCriterion("EMP_ADDRESS between", value1, value2, "empAddress");
            return (Criteria) this;
        }

        public Criteria andEmpAddressNotBetween(String value1, String value2) {
            addCriterion("EMP_ADDRESS not between", value1, value2, "empAddress");
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