package com.navinfo.IMS.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoadQuestionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RoadQuestionExample() {
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

        public Criteria andParentTaskCodeIsNull() {
            addCriterion("PARENT_TASK_CODE is null");
            return (Criteria) this;
        }

        public Criteria andParentTaskCodeIsNotNull() {
            addCriterion("PARENT_TASK_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andParentTaskCodeEqualTo(String value) {
            addCriterion("PARENT_TASK_CODE =", value, "parentTaskCode");
            return (Criteria) this;
        }

        public Criteria andParentTaskCodeNotEqualTo(String value) {
            addCriterion("PARENT_TASK_CODE <>", value, "parentTaskCode");
            return (Criteria) this;
        }

        public Criteria andParentTaskCodeGreaterThan(String value) {
            addCriterion("PARENT_TASK_CODE >", value, "parentTaskCode");
            return (Criteria) this;
        }

        public Criteria andParentTaskCodeGreaterThanOrEqualTo(String value) {
            addCriterion("PARENT_TASK_CODE >=", value, "parentTaskCode");
            return (Criteria) this;
        }

        public Criteria andParentTaskCodeLessThan(String value) {
            addCriterion("PARENT_TASK_CODE <", value, "parentTaskCode");
            return (Criteria) this;
        }

        public Criteria andParentTaskCodeLessThanOrEqualTo(String value) {
            addCriterion("PARENT_TASK_CODE <=", value, "parentTaskCode");
            return (Criteria) this;
        }

        public Criteria andParentTaskCodeLike(String value) {
            addCriterion("PARENT_TASK_CODE like", value, "parentTaskCode");
            return (Criteria) this;
        }

        public Criteria andParentTaskCodeNotLike(String value) {
            addCriterion("PARENT_TASK_CODE not like", value, "parentTaskCode");
            return (Criteria) this;
        }

        public Criteria andParentTaskCodeIn(List<String> values) {
            addCriterion("PARENT_TASK_CODE in", values, "parentTaskCode");
            return (Criteria) this;
        }

        public Criteria andParentTaskCodeNotIn(List<String> values) {
            addCriterion("PARENT_TASK_CODE not in", values, "parentTaskCode");
            return (Criteria) this;
        }

        public Criteria andParentTaskCodeBetween(String value1, String value2) {
            addCriterion("PARENT_TASK_CODE between", value1, value2, "parentTaskCode");
            return (Criteria) this;
        }

        public Criteria andParentTaskCodeNotBetween(String value1, String value2) {
            addCriterion("PARENT_TASK_CODE not between", value1, value2, "parentTaskCode");
            return (Criteria) this;
        }

        public Criteria andParentTaskNameIsNull() {
            addCriterion("PARENT_TASK_NAME is null");
            return (Criteria) this;
        }

        public Criteria andParentTaskNameIsNotNull() {
            addCriterion("PARENT_TASK_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andParentTaskNameEqualTo(String value) {
            addCriterion("PARENT_TASK_NAME =", value, "parentTaskName");
            return (Criteria) this;
        }

        public Criteria andParentTaskNameNotEqualTo(String value) {
            addCriterion("PARENT_TASK_NAME <>", value, "parentTaskName");
            return (Criteria) this;
        }

        public Criteria andParentTaskNameGreaterThan(String value) {
            addCriterion("PARENT_TASK_NAME >", value, "parentTaskName");
            return (Criteria) this;
        }

        public Criteria andParentTaskNameGreaterThanOrEqualTo(String value) {
            addCriterion("PARENT_TASK_NAME >=", value, "parentTaskName");
            return (Criteria) this;
        }

        public Criteria andParentTaskNameLessThan(String value) {
            addCriterion("PARENT_TASK_NAME <", value, "parentTaskName");
            return (Criteria) this;
        }

        public Criteria andParentTaskNameLessThanOrEqualTo(String value) {
            addCriterion("PARENT_TASK_NAME <=", value, "parentTaskName");
            return (Criteria) this;
        }

        public Criteria andParentTaskNameLike(String value) {
            addCriterion("PARENT_TASK_NAME like", value, "parentTaskName");
            return (Criteria) this;
        }

        public Criteria andParentTaskNameNotLike(String value) {
            addCriterion("PARENT_TASK_NAME not like", value, "parentTaskName");
            return (Criteria) this;
        }

        public Criteria andParentTaskNameIn(List<String> values) {
            addCriterion("PARENT_TASK_NAME in", values, "parentTaskName");
            return (Criteria) this;
        }

        public Criteria andParentTaskNameNotIn(List<String> values) {
            addCriterion("PARENT_TASK_NAME not in", values, "parentTaskName");
            return (Criteria) this;
        }

        public Criteria andParentTaskNameBetween(String value1, String value2) {
            addCriterion("PARENT_TASK_NAME between", value1, value2, "parentTaskName");
            return (Criteria) this;
        }

        public Criteria andParentTaskNameNotBetween(String value1, String value2) {
            addCriterion("PARENT_TASK_NAME not between", value1, value2, "parentTaskName");
            return (Criteria) this;
        }

        public Criteria andTaskCodeIsNull() {
            addCriterion("TASK_CODE is null");
            return (Criteria) this;
        }

        public Criteria andTaskCodeIsNotNull() {
            addCriterion("TASK_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andTaskCodeEqualTo(String value) {
            addCriterion("TASK_CODE =", value, "taskCode");
            return (Criteria) this;
        }

        public Criteria andTaskCodeNotEqualTo(String value) {
            addCriterion("TASK_CODE <>", value, "taskCode");
            return (Criteria) this;
        }

        public Criteria andTaskCodeGreaterThan(String value) {
            addCriterion("TASK_CODE >", value, "taskCode");
            return (Criteria) this;
        }

        public Criteria andTaskCodeGreaterThanOrEqualTo(String value) {
            addCriterion("TASK_CODE >=", value, "taskCode");
            return (Criteria) this;
        }

        public Criteria andTaskCodeLessThan(String value) {
            addCriterion("TASK_CODE <", value, "taskCode");
            return (Criteria) this;
        }

        public Criteria andTaskCodeLessThanOrEqualTo(String value) {
            addCriterion("TASK_CODE <=", value, "taskCode");
            return (Criteria) this;
        }

        public Criteria andTaskCodeLike(String value) {
            addCriterion("TASK_CODE like", value, "taskCode");
            return (Criteria) this;
        }

        public Criteria andTaskCodeNotLike(String value) {
            addCriterion("TASK_CODE not like", value, "taskCode");
            return (Criteria) this;
        }

        public Criteria andTaskCodeIn(List<String> values) {
            addCriterion("TASK_CODE in", values, "taskCode");
            return (Criteria) this;
        }

        public Criteria andTaskCodeNotIn(List<String> values) {
            addCriterion("TASK_CODE not in", values, "taskCode");
            return (Criteria) this;
        }

        public Criteria andTaskCodeBetween(String value1, String value2) {
            addCriterion("TASK_CODE between", value1, value2, "taskCode");
            return (Criteria) this;
        }

        public Criteria andTaskCodeNotBetween(String value1, String value2) {
            addCriterion("TASK_CODE not between", value1, value2, "taskCode");
            return (Criteria) this;
        }

        public Criteria andTaskNameIsNull() {
            addCriterion("TASK_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTaskNameIsNotNull() {
            addCriterion("TASK_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTaskNameEqualTo(String value) {
            addCriterion("TASK_NAME =", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotEqualTo(String value) {
            addCriterion("TASK_NAME <>", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameGreaterThan(String value) {
            addCriterion("TASK_NAME >", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameGreaterThanOrEqualTo(String value) {
            addCriterion("TASK_NAME >=", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameLessThan(String value) {
            addCriterion("TASK_NAME <", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameLessThanOrEqualTo(String value) {
            addCriterion("TASK_NAME <=", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameLike(String value) {
            addCriterion("TASK_NAME like", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotLike(String value) {
            addCriterion("TASK_NAME not like", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameIn(List<String> values) {
            addCriterion("TASK_NAME in", values, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotIn(List<String> values) {
            addCriterion("TASK_NAME not in", values, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameBetween(String value1, String value2) {
            addCriterion("TASK_NAME between", value1, value2, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotBetween(String value1, String value2) {
            addCriterion("TASK_NAME not between", value1, value2, "taskName");
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

        public Criteria andAuditorIdIsNull() {
            addCriterion("AUDITOR_ID is null");
            return (Criteria) this;
        }

        public Criteria andAuditorIdIsNotNull() {
            addCriterion("AUDITOR_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAuditorIdEqualTo(String value) {
            addCriterion("AUDITOR_ID =", value, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdNotEqualTo(String value) {
            addCriterion("AUDITOR_ID <>", value, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdGreaterThan(String value) {
            addCriterion("AUDITOR_ID >", value, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdGreaterThanOrEqualTo(String value) {
            addCriterion("AUDITOR_ID >=", value, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdLessThan(String value) {
            addCriterion("AUDITOR_ID <", value, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdLessThanOrEqualTo(String value) {
            addCriterion("AUDITOR_ID <=", value, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdLike(String value) {
            addCriterion("AUDITOR_ID like", value, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdNotLike(String value) {
            addCriterion("AUDITOR_ID not like", value, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdIn(List<String> values) {
            addCriterion("AUDITOR_ID in", values, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdNotIn(List<String> values) {
            addCriterion("AUDITOR_ID not in", values, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdBetween(String value1, String value2) {
            addCriterion("AUDITOR_ID between", value1, value2, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdNotBetween(String value1, String value2) {
            addCriterion("AUDITOR_ID not between", value1, value2, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIsNull() {
            addCriterion("AUDITOR is null");
            return (Criteria) this;
        }

        public Criteria andAuditorIsNotNull() {
            addCriterion("AUDITOR is not null");
            return (Criteria) this;
        }

        public Criteria andAuditorEqualTo(String value) {
            addCriterion("AUDITOR =", value, "auditor");
            return (Criteria) this;
        }

        public Criteria andAuditorNotEqualTo(String value) {
            addCriterion("AUDITOR <>", value, "auditor");
            return (Criteria) this;
        }

        public Criteria andAuditorGreaterThan(String value) {
            addCriterion("AUDITOR >", value, "auditor");
            return (Criteria) this;
        }

        public Criteria andAuditorGreaterThanOrEqualTo(String value) {
            addCriterion("AUDITOR >=", value, "auditor");
            return (Criteria) this;
        }

        public Criteria andAuditorLessThan(String value) {
            addCriterion("AUDITOR <", value, "auditor");
            return (Criteria) this;
        }

        public Criteria andAuditorLessThanOrEqualTo(String value) {
            addCriterion("AUDITOR <=", value, "auditor");
            return (Criteria) this;
        }

        public Criteria andAuditorLike(String value) {
            addCriterion("AUDITOR like", value, "auditor");
            return (Criteria) this;
        }

        public Criteria andAuditorNotLike(String value) {
            addCriterion("AUDITOR not like", value, "auditor");
            return (Criteria) this;
        }

        public Criteria andAuditorIn(List<String> values) {
            addCriterion("AUDITOR in", values, "auditor");
            return (Criteria) this;
        }

        public Criteria andAuditorNotIn(List<String> values) {
            addCriterion("AUDITOR not in", values, "auditor");
            return (Criteria) this;
        }

        public Criteria andAuditorBetween(String value1, String value2) {
            addCriterion("AUDITOR between", value1, value2, "auditor");
            return (Criteria) this;
        }

        public Criteria andAuditorNotBetween(String value1, String value2) {
            addCriterion("AUDITOR not between", value1, value2, "auditor");
            return (Criteria) this;
        }

        public Criteria andAuditDateIsNull() {
            addCriterion("AUDIT_DATE is null");
            return (Criteria) this;
        }

        public Criteria andAuditDateIsNotNull() {
            addCriterion("AUDIT_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andAuditDateEqualTo(Date value) {
            addCriterion("AUDIT_DATE =", value, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateNotEqualTo(Date value) {
            addCriterion("AUDIT_DATE <>", value, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateGreaterThan(Date value) {
            addCriterion("AUDIT_DATE >", value, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateGreaterThanOrEqualTo(Date value) {
            addCriterion("AUDIT_DATE >=", value, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateLessThan(Date value) {
            addCriterion("AUDIT_DATE <", value, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateLessThanOrEqualTo(Date value) {
            addCriterion("AUDIT_DATE <=", value, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateIn(List<Date> values) {
            addCriterion("AUDIT_DATE in", values, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateNotIn(List<Date> values) {
            addCriterion("AUDIT_DATE not in", values, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateBetween(Date value1, Date value2) {
            addCriterion("AUDIT_DATE between", value1, value2, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateNotBetween(Date value1, Date value2) {
            addCriterion("AUDIT_DATE not between", value1, value2, "auditDate");
            return (Criteria) this;
        }

        public Criteria andMapCodeIsNull() {
            addCriterion("MAP_CODE is null");
            return (Criteria) this;
        }

        public Criteria andMapCodeIsNotNull() {
            addCriterion("MAP_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andMapCodeEqualTo(String value) {
            addCriterion("MAP_CODE =", value, "mapCode");
            return (Criteria) this;
        }

        public Criteria andMapCodeNotEqualTo(String value) {
            addCriterion("MAP_CODE <>", value, "mapCode");
            return (Criteria) this;
        }

        public Criteria andMapCodeGreaterThan(String value) {
            addCriterion("MAP_CODE >", value, "mapCode");
            return (Criteria) this;
        }

        public Criteria andMapCodeGreaterThanOrEqualTo(String value) {
            addCriterion("MAP_CODE >=", value, "mapCode");
            return (Criteria) this;
        }

        public Criteria andMapCodeLessThan(String value) {
            addCriterion("MAP_CODE <", value, "mapCode");
            return (Criteria) this;
        }

        public Criteria andMapCodeLessThanOrEqualTo(String value) {
            addCriterion("MAP_CODE <=", value, "mapCode");
            return (Criteria) this;
        }

        public Criteria andMapCodeLike(String value) {
            addCriterion("MAP_CODE like", value, "mapCode");
            return (Criteria) this;
        }

        public Criteria andMapCodeNotLike(String value) {
            addCriterion("MAP_CODE not like", value, "mapCode");
            return (Criteria) this;
        }

        public Criteria andMapCodeIn(List<String> values) {
            addCriterion("MAP_CODE in", values, "mapCode");
            return (Criteria) this;
        }

        public Criteria andMapCodeNotIn(List<String> values) {
            addCriterion("MAP_CODE not in", values, "mapCode");
            return (Criteria) this;
        }

        public Criteria andMapCodeBetween(String value1, String value2) {
            addCriterion("MAP_CODE between", value1, value2, "mapCode");
            return (Criteria) this;
        }

        public Criteria andMapCodeNotBetween(String value1, String value2) {
            addCriterion("MAP_CODE not between", value1, value2, "mapCode");
            return (Criteria) this;
        }

        public Criteria andFeatureIsNull() {
            addCriterion("FEATURE is null");
            return (Criteria) this;
        }

        public Criteria andFeatureIsNotNull() {
            addCriterion("FEATURE is not null");
            return (Criteria) this;
        }

        public Criteria andFeatureEqualTo(String value) {
            addCriterion("FEATURE =", value, "feature");
            return (Criteria) this;
        }

        public Criteria andFeatureNotEqualTo(String value) {
            addCriterion("FEATURE <>", value, "feature");
            return (Criteria) this;
        }

        public Criteria andFeatureGreaterThan(String value) {
            addCriterion("FEATURE >", value, "feature");
            return (Criteria) this;
        }

        public Criteria andFeatureGreaterThanOrEqualTo(String value) {
            addCriterion("FEATURE >=", value, "feature");
            return (Criteria) this;
        }

        public Criteria andFeatureLessThan(String value) {
            addCriterion("FEATURE <", value, "feature");
            return (Criteria) this;
        }

        public Criteria andFeatureLessThanOrEqualTo(String value) {
            addCriterion("FEATURE <=", value, "feature");
            return (Criteria) this;
        }

        public Criteria andFeatureLike(String value) {
            addCriterion("FEATURE like", value, "feature");
            return (Criteria) this;
        }

        public Criteria andFeatureNotLike(String value) {
            addCriterion("FEATURE not like", value, "feature");
            return (Criteria) this;
        }

        public Criteria andFeatureIn(List<String> values) {
            addCriterion("FEATURE in", values, "feature");
            return (Criteria) this;
        }

        public Criteria andFeatureNotIn(List<String> values) {
            addCriterion("FEATURE not in", values, "feature");
            return (Criteria) this;
        }

        public Criteria andFeatureBetween(String value1, String value2) {
            addCriterion("FEATURE between", value1, value2, "feature");
            return (Criteria) this;
        }

        public Criteria andFeatureNotBetween(String value1, String value2) {
            addCriterion("FEATURE not between", value1, value2, "feature");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("DESCRIPTION is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("DESCRIPTION is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("DESCRIPTION =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("DESCRIPTION <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("DESCRIPTION >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("DESCRIPTION >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("DESCRIPTION <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("DESCRIPTION <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("DESCRIPTION like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("DESCRIPTION not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("DESCRIPTION in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("DESCRIPTION not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("DESCRIPTION between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("DESCRIPTION not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andErrorReasonIsNull() {
            addCriterion("ERROR_REASON is null");
            return (Criteria) this;
        }

        public Criteria andErrorReasonIsNotNull() {
            addCriterion("ERROR_REASON is not null");
            return (Criteria) this;
        }

        public Criteria andErrorReasonEqualTo(String value) {
            addCriterion("ERROR_REASON =", value, "errorReason");
            return (Criteria) this;
        }

        public Criteria andErrorReasonNotEqualTo(String value) {
            addCriterion("ERROR_REASON <>", value, "errorReason");
            return (Criteria) this;
        }

        public Criteria andErrorReasonGreaterThan(String value) {
            addCriterion("ERROR_REASON >", value, "errorReason");
            return (Criteria) this;
        }

        public Criteria andErrorReasonGreaterThanOrEqualTo(String value) {
            addCriterion("ERROR_REASON >=", value, "errorReason");
            return (Criteria) this;
        }

        public Criteria andErrorReasonLessThan(String value) {
            addCriterion("ERROR_REASON <", value, "errorReason");
            return (Criteria) this;
        }

        public Criteria andErrorReasonLessThanOrEqualTo(String value) {
            addCriterion("ERROR_REASON <=", value, "errorReason");
            return (Criteria) this;
        }

        public Criteria andErrorReasonLike(String value) {
            addCriterion("ERROR_REASON like", value, "errorReason");
            return (Criteria) this;
        }

        public Criteria andErrorReasonNotLike(String value) {
            addCriterion("ERROR_REASON not like", value, "errorReason");
            return (Criteria) this;
        }

        public Criteria andErrorReasonIn(List<String> values) {
            addCriterion("ERROR_REASON in", values, "errorReason");
            return (Criteria) this;
        }

        public Criteria andErrorReasonNotIn(List<String> values) {
            addCriterion("ERROR_REASON not in", values, "errorReason");
            return (Criteria) this;
        }

        public Criteria andErrorReasonBetween(String value1, String value2) {
            addCriterion("ERROR_REASON between", value1, value2, "errorReason");
            return (Criteria) this;
        }

        public Criteria andErrorReasonNotBetween(String value1, String value2) {
            addCriterion("ERROR_REASON not between", value1, value2, "errorReason");
            return (Criteria) this;
        }

        public Criteria andErrorContentIsNull() {
            addCriterion("ERROR_CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andErrorContentIsNotNull() {
            addCriterion("ERROR_CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andErrorContentEqualTo(String value) {
            addCriterion("ERROR_CONTENT =", value, "errorContent");
            return (Criteria) this;
        }

        public Criteria andErrorContentNotEqualTo(String value) {
            addCriterion("ERROR_CONTENT <>", value, "errorContent");
            return (Criteria) this;
        }

        public Criteria andErrorContentGreaterThan(String value) {
            addCriterion("ERROR_CONTENT >", value, "errorContent");
            return (Criteria) this;
        }

        public Criteria andErrorContentGreaterThanOrEqualTo(String value) {
            addCriterion("ERROR_CONTENT >=", value, "errorContent");
            return (Criteria) this;
        }

        public Criteria andErrorContentLessThan(String value) {
            addCriterion("ERROR_CONTENT <", value, "errorContent");
            return (Criteria) this;
        }

        public Criteria andErrorContentLessThanOrEqualTo(String value) {
            addCriterion("ERROR_CONTENT <=", value, "errorContent");
            return (Criteria) this;
        }

        public Criteria andErrorContentLike(String value) {
            addCriterion("ERROR_CONTENT like", value, "errorContent");
            return (Criteria) this;
        }

        public Criteria andErrorContentNotLike(String value) {
            addCriterion("ERROR_CONTENT not like", value, "errorContent");
            return (Criteria) this;
        }

        public Criteria andErrorContentIn(List<String> values) {
            addCriterion("ERROR_CONTENT in", values, "errorContent");
            return (Criteria) this;
        }

        public Criteria andErrorContentNotIn(List<String> values) {
            addCriterion("ERROR_CONTENT not in", values, "errorContent");
            return (Criteria) this;
        }

        public Criteria andErrorContentBetween(String value1, String value2) {
            addCriterion("ERROR_CONTENT between", value1, value2, "errorContent");
            return (Criteria) this;
        }

        public Criteria andErrorContentNotBetween(String value1, String value2) {
            addCriterion("ERROR_CONTENT not between", value1, value2, "errorContent");
            return (Criteria) this;
        }

        public Criteria andErrorLevelIsNull() {
            addCriterion("ERROR_LEVEL is null");
            return (Criteria) this;
        }

        public Criteria andErrorLevelIsNotNull() {
            addCriterion("ERROR_LEVEL is not null");
            return (Criteria) this;
        }

        public Criteria andErrorLevelEqualTo(String value) {
            addCriterion("ERROR_LEVEL =", value, "errorLevel");
            return (Criteria) this;
        }

        public Criteria andErrorLevelNotEqualTo(String value) {
            addCriterion("ERROR_LEVEL <>", value, "errorLevel");
            return (Criteria) this;
        }

        public Criteria andErrorLevelGreaterThan(String value) {
            addCriterion("ERROR_LEVEL >", value, "errorLevel");
            return (Criteria) this;
        }

        public Criteria andErrorLevelGreaterThanOrEqualTo(String value) {
            addCriterion("ERROR_LEVEL >=", value, "errorLevel");
            return (Criteria) this;
        }

        public Criteria andErrorLevelLessThan(String value) {
            addCriterion("ERROR_LEVEL <", value, "errorLevel");
            return (Criteria) this;
        }

        public Criteria andErrorLevelLessThanOrEqualTo(String value) {
            addCriterion("ERROR_LEVEL <=", value, "errorLevel");
            return (Criteria) this;
        }

        public Criteria andErrorLevelLike(String value) {
            addCriterion("ERROR_LEVEL like", value, "errorLevel");
            return (Criteria) this;
        }

        public Criteria andErrorLevelNotLike(String value) {
            addCriterion("ERROR_LEVEL not like", value, "errorLevel");
            return (Criteria) this;
        }

        public Criteria andErrorLevelIn(List<String> values) {
            addCriterion("ERROR_LEVEL in", values, "errorLevel");
            return (Criteria) this;
        }

        public Criteria andErrorLevelNotIn(List<String> values) {
            addCriterion("ERROR_LEVEL not in", values, "errorLevel");
            return (Criteria) this;
        }

        public Criteria andErrorLevelBetween(String value1, String value2) {
            addCriterion("ERROR_LEVEL between", value1, value2, "errorLevel");
            return (Criteria) this;
        }

        public Criteria andErrorLevelNotBetween(String value1, String value2) {
            addCriterion("ERROR_LEVEL not between", value1, value2, "errorLevel");
            return (Criteria) this;
        }

        public Criteria andBigErrorFlagIsNull() {
            addCriterion("BIG_ERROR_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andBigErrorFlagIsNotNull() {
            addCriterion("BIG_ERROR_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andBigErrorFlagEqualTo(String value) {
            addCriterion("BIG_ERROR_FLAG =", value, "bigErrorFlag");
            return (Criteria) this;
        }

        public Criteria andBigErrorFlagNotEqualTo(String value) {
            addCriterion("BIG_ERROR_FLAG <>", value, "bigErrorFlag");
            return (Criteria) this;
        }

        public Criteria andBigErrorFlagGreaterThan(String value) {
            addCriterion("BIG_ERROR_FLAG >", value, "bigErrorFlag");
            return (Criteria) this;
        }

        public Criteria andBigErrorFlagGreaterThanOrEqualTo(String value) {
            addCriterion("BIG_ERROR_FLAG >=", value, "bigErrorFlag");
            return (Criteria) this;
        }

        public Criteria andBigErrorFlagLessThan(String value) {
            addCriterion("BIG_ERROR_FLAG <", value, "bigErrorFlag");
            return (Criteria) this;
        }

        public Criteria andBigErrorFlagLessThanOrEqualTo(String value) {
            addCriterion("BIG_ERROR_FLAG <=", value, "bigErrorFlag");
            return (Criteria) this;
        }

        public Criteria andBigErrorFlagLike(String value) {
            addCriterion("BIG_ERROR_FLAG like", value, "bigErrorFlag");
            return (Criteria) this;
        }

        public Criteria andBigErrorFlagNotLike(String value) {
            addCriterion("BIG_ERROR_FLAG not like", value, "bigErrorFlag");
            return (Criteria) this;
        }

        public Criteria andBigErrorFlagIn(List<String> values) {
            addCriterion("BIG_ERROR_FLAG in", values, "bigErrorFlag");
            return (Criteria) this;
        }

        public Criteria andBigErrorFlagNotIn(List<String> values) {
            addCriterion("BIG_ERROR_FLAG not in", values, "bigErrorFlag");
            return (Criteria) this;
        }

        public Criteria andBigErrorFlagBetween(String value1, String value2) {
            addCriterion("BIG_ERROR_FLAG between", value1, value2, "bigErrorFlag");
            return (Criteria) this;
        }

        public Criteria andBigErrorFlagNotBetween(String value1, String value2) {
            addCriterion("BIG_ERROR_FLAG not between", value1, value2, "bigErrorFlag");
            return (Criteria) this;
        }

        public Criteria andAlterFlagIsNull() {
            addCriterion("ALTER_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andAlterFlagIsNotNull() {
            addCriterion("ALTER_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andAlterFlagEqualTo(String value) {
            addCriterion("ALTER_FLAG =", value, "alterFlag");
            return (Criteria) this;
        }

        public Criteria andAlterFlagNotEqualTo(String value) {
            addCriterion("ALTER_FLAG <>", value, "alterFlag");
            return (Criteria) this;
        }

        public Criteria andAlterFlagGreaterThan(String value) {
            addCriterion("ALTER_FLAG >", value, "alterFlag");
            return (Criteria) this;
        }

        public Criteria andAlterFlagGreaterThanOrEqualTo(String value) {
            addCriterion("ALTER_FLAG >=", value, "alterFlag");
            return (Criteria) this;
        }

        public Criteria andAlterFlagLessThan(String value) {
            addCriterion("ALTER_FLAG <", value, "alterFlag");
            return (Criteria) this;
        }

        public Criteria andAlterFlagLessThanOrEqualTo(String value) {
            addCriterion("ALTER_FLAG <=", value, "alterFlag");
            return (Criteria) this;
        }

        public Criteria andAlterFlagLike(String value) {
            addCriterion("ALTER_FLAG like", value, "alterFlag");
            return (Criteria) this;
        }

        public Criteria andAlterFlagNotLike(String value) {
            addCriterion("ALTER_FLAG not like", value, "alterFlag");
            return (Criteria) this;
        }

        public Criteria andAlterFlagIn(List<String> values) {
            addCriterion("ALTER_FLAG in", values, "alterFlag");
            return (Criteria) this;
        }

        public Criteria andAlterFlagNotIn(List<String> values) {
            addCriterion("ALTER_FLAG not in", values, "alterFlag");
            return (Criteria) this;
        }

        public Criteria andAlterFlagBetween(String value1, String value2) {
            addCriterion("ALTER_FLAG between", value1, value2, "alterFlag");
            return (Criteria) this;
        }

        public Criteria andAlterFlagNotBetween(String value1, String value2) {
            addCriterion("ALTER_FLAG not between", value1, value2, "alterFlag");
            return (Criteria) this;
        }

        public Criteria andRealterFlagIsNull() {
            addCriterion("REALTER_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andRealterFlagIsNotNull() {
            addCriterion("REALTER_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andRealterFlagEqualTo(String value) {
            addCriterion("REALTER_FLAG =", value, "realterFlag");
            return (Criteria) this;
        }

        public Criteria andRealterFlagNotEqualTo(String value) {
            addCriterion("REALTER_FLAG <>", value, "realterFlag");
            return (Criteria) this;
        }

        public Criteria andRealterFlagGreaterThan(String value) {
            addCriterion("REALTER_FLAG >", value, "realterFlag");
            return (Criteria) this;
        }

        public Criteria andRealterFlagGreaterThanOrEqualTo(String value) {
            addCriterion("REALTER_FLAG >=", value, "realterFlag");
            return (Criteria) this;
        }

        public Criteria andRealterFlagLessThan(String value) {
            addCriterion("REALTER_FLAG <", value, "realterFlag");
            return (Criteria) this;
        }

        public Criteria andRealterFlagLessThanOrEqualTo(String value) {
            addCriterion("REALTER_FLAG <=", value, "realterFlag");
            return (Criteria) this;
        }

        public Criteria andRealterFlagLike(String value) {
            addCriterion("REALTER_FLAG like", value, "realterFlag");
            return (Criteria) this;
        }

        public Criteria andRealterFlagNotLike(String value) {
            addCriterion("REALTER_FLAG not like", value, "realterFlag");
            return (Criteria) this;
        }

        public Criteria andRealterFlagIn(List<String> values) {
            addCriterion("REALTER_FLAG in", values, "realterFlag");
            return (Criteria) this;
        }

        public Criteria andRealterFlagNotIn(List<String> values) {
            addCriterion("REALTER_FLAG not in", values, "realterFlag");
            return (Criteria) this;
        }

        public Criteria andRealterFlagBetween(String value1, String value2) {
            addCriterion("REALTER_FLAG between", value1, value2, "realterFlag");
            return (Criteria) this;
        }

        public Criteria andRealterFlagNotBetween(String value1, String value2) {
            addCriterion("REALTER_FLAG not between", value1, value2, "realterFlag");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeIsNull() {
            addCriterion("QUESTION_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeIsNotNull() {
            addCriterion("QUESTION_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeEqualTo(String value) {
            addCriterion("QUESTION_TYPE =", value, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeNotEqualTo(String value) {
            addCriterion("QUESTION_TYPE <>", value, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeGreaterThan(String value) {
            addCriterion("QUESTION_TYPE >", value, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeGreaterThanOrEqualTo(String value) {
            addCriterion("QUESTION_TYPE >=", value, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeLessThan(String value) {
            addCriterion("QUESTION_TYPE <", value, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeLessThanOrEqualTo(String value) {
            addCriterion("QUESTION_TYPE <=", value, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeLike(String value) {
            addCriterion("QUESTION_TYPE like", value, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeNotLike(String value) {
            addCriterion("QUESTION_TYPE not like", value, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeIn(List<String> values) {
            addCriterion("QUESTION_TYPE in", values, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeNotIn(List<String> values) {
            addCriterion("QUESTION_TYPE not in", values, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeBetween(String value1, String value2) {
            addCriterion("QUESTION_TYPE between", value1, value2, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeNotBetween(String value1, String value2) {
            addCriterion("QUESTION_TYPE not between", value1, value2, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionItemIsNull() {
            addCriterion("QUESTION_ITEM is null");
            return (Criteria) this;
        }

        public Criteria andQuestionItemIsNotNull() {
            addCriterion("QUESTION_ITEM is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionItemEqualTo(String value) {
            addCriterion("QUESTION_ITEM =", value, "questionItem");
            return (Criteria) this;
        }

        public Criteria andQuestionItemNotEqualTo(String value) {
            addCriterion("QUESTION_ITEM <>", value, "questionItem");
            return (Criteria) this;
        }

        public Criteria andQuestionItemGreaterThan(String value) {
            addCriterion("QUESTION_ITEM >", value, "questionItem");
            return (Criteria) this;
        }

        public Criteria andQuestionItemGreaterThanOrEqualTo(String value) {
            addCriterion("QUESTION_ITEM >=", value, "questionItem");
            return (Criteria) this;
        }

        public Criteria andQuestionItemLessThan(String value) {
            addCriterion("QUESTION_ITEM <", value, "questionItem");
            return (Criteria) this;
        }

        public Criteria andQuestionItemLessThanOrEqualTo(String value) {
            addCriterion("QUESTION_ITEM <=", value, "questionItem");
            return (Criteria) this;
        }

        public Criteria andQuestionItemLike(String value) {
            addCriterion("QUESTION_ITEM like", value, "questionItem");
            return (Criteria) this;
        }

        public Criteria andQuestionItemNotLike(String value) {
            addCriterion("QUESTION_ITEM not like", value, "questionItem");
            return (Criteria) this;
        }

        public Criteria andQuestionItemIn(List<String> values) {
            addCriterion("QUESTION_ITEM in", values, "questionItem");
            return (Criteria) this;
        }

        public Criteria andQuestionItemNotIn(List<String> values) {
            addCriterion("QUESTION_ITEM not in", values, "questionItem");
            return (Criteria) this;
        }

        public Criteria andQuestionItemBetween(String value1, String value2) {
            addCriterion("QUESTION_ITEM between", value1, value2, "questionItem");
            return (Criteria) this;
        }

        public Criteria andQuestionItemNotBetween(String value1, String value2) {
            addCriterion("QUESTION_ITEM not between", value1, value2, "questionItem");
            return (Criteria) this;
        }

        public Criteria andErrorTypeIsNull() {
            addCriterion("ERROR_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andErrorTypeIsNotNull() {
            addCriterion("ERROR_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andErrorTypeEqualTo(String value) {
            addCriterion("ERROR_TYPE =", value, "errorType");
            return (Criteria) this;
        }

        public Criteria andErrorTypeNotEqualTo(String value) {
            addCriterion("ERROR_TYPE <>", value, "errorType");
            return (Criteria) this;
        }

        public Criteria andErrorTypeGreaterThan(String value) {
            addCriterion("ERROR_TYPE >", value, "errorType");
            return (Criteria) this;
        }

        public Criteria andErrorTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ERROR_TYPE >=", value, "errorType");
            return (Criteria) this;
        }

        public Criteria andErrorTypeLessThan(String value) {
            addCriterion("ERROR_TYPE <", value, "errorType");
            return (Criteria) this;
        }

        public Criteria andErrorTypeLessThanOrEqualTo(String value) {
            addCriterion("ERROR_TYPE <=", value, "errorType");
            return (Criteria) this;
        }

        public Criteria andErrorTypeLike(String value) {
            addCriterion("ERROR_TYPE like", value, "errorType");
            return (Criteria) this;
        }

        public Criteria andErrorTypeNotLike(String value) {
            addCriterion("ERROR_TYPE not like", value, "errorType");
            return (Criteria) this;
        }

        public Criteria andErrorTypeIn(List<String> values) {
            addCriterion("ERROR_TYPE in", values, "errorType");
            return (Criteria) this;
        }

        public Criteria andErrorTypeNotIn(List<String> values) {
            addCriterion("ERROR_TYPE not in", values, "errorType");
            return (Criteria) this;
        }

        public Criteria andErrorTypeBetween(String value1, String value2) {
            addCriterion("ERROR_TYPE between", value1, value2, "errorType");
            return (Criteria) this;
        }

        public Criteria andErrorTypeNotBetween(String value1, String value2) {
            addCriterion("ERROR_TYPE not between", value1, value2, "errorType");
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