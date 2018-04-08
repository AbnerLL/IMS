package com.navinfo.core.entity;

import java.util.ArrayList;
import java.util.List;

public class DBFieldManageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DBFieldManageExample() {
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

        public Criteria andUuidIsNull() {
            addCriterion("UUID is null");
            return (Criteria) this;
        }

        public Criteria andUuidIsNotNull() {
            addCriterion("UUID is not null");
            return (Criteria) this;
        }

        public Criteria andUuidEqualTo(String value) {
            addCriterion("UUID =", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotEqualTo(String value) {
            addCriterion("UUID <>", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThan(String value) {
            addCriterion("UUID >", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThanOrEqualTo(String value) {
            addCriterion("UUID >=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThan(String value) {
            addCriterion("UUID <", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThanOrEqualTo(String value) {
            addCriterion("UUID <=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLike(String value) {
            addCriterion("UUID like", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotLike(String value) {
            addCriterion("UUID not like", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidIn(List<String> values) {
            addCriterion("UUID in", values, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotIn(List<String> values) {
            addCriterion("UUID not in", values, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidBetween(String value1, String value2) {
            addCriterion("UUID between", value1, value2, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotBetween(String value1, String value2) {
            addCriterion("UUID not between", value1, value2, "uuid");
            return (Criteria) this;
        }

        public Criteria andFieldNameIsNull() {
            addCriterion("FIELD_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFieldNameIsNotNull() {
            addCriterion("FIELD_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFieldNameEqualTo(String value) {
            addCriterion("FIELD_NAME =", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameNotEqualTo(String value) {
            addCriterion("FIELD_NAME <>", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameGreaterThan(String value) {
            addCriterion("FIELD_NAME >", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameGreaterThanOrEqualTo(String value) {
            addCriterion("FIELD_NAME >=", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameLessThan(String value) {
            addCriterion("FIELD_NAME <", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameLessThanOrEqualTo(String value) {
            addCriterion("FIELD_NAME <=", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameLike(String value) {
            addCriterion("FIELD_NAME like", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameNotLike(String value) {
            addCriterion("FIELD_NAME not like", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameIn(List<String> values) {
            addCriterion("FIELD_NAME in", values, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameNotIn(List<String> values) {
            addCriterion("FIELD_NAME not in", values, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameBetween(String value1, String value2) {
            addCriterion("FIELD_NAME between", value1, value2, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameNotBetween(String value1, String value2) {
            addCriterion("FIELD_NAME not between", value1, value2, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldDescriptIsNull() {
            addCriterion("FIELD_DESCRIPT is null");
            return (Criteria) this;
        }

        public Criteria andFieldDescriptIsNotNull() {
            addCriterion("FIELD_DESCRIPT is not null");
            return (Criteria) this;
        }

        public Criteria andFieldDescriptEqualTo(String value) {
            addCriterion("FIELD_DESCRIPT =", value, "fieldDescript");
            return (Criteria) this;
        }

        public Criteria andFieldDescriptNotEqualTo(String value) {
            addCriterion("FIELD_DESCRIPT <>", value, "fieldDescript");
            return (Criteria) this;
        }

        public Criteria andFieldDescriptGreaterThan(String value) {
            addCriterion("FIELD_DESCRIPT >", value, "fieldDescript");
            return (Criteria) this;
        }

        public Criteria andFieldDescriptGreaterThanOrEqualTo(String value) {
            addCriterion("FIELD_DESCRIPT >=", value, "fieldDescript");
            return (Criteria) this;
        }

        public Criteria andFieldDescriptLessThan(String value) {
            addCriterion("FIELD_DESCRIPT <", value, "fieldDescript");
            return (Criteria) this;
        }

        public Criteria andFieldDescriptLessThanOrEqualTo(String value) {
            addCriterion("FIELD_DESCRIPT <=", value, "fieldDescript");
            return (Criteria) this;
        }

        public Criteria andFieldDescriptLike(String value) {
            addCriterion("FIELD_DESCRIPT like", value, "fieldDescript");
            return (Criteria) this;
        }

        public Criteria andFieldDescriptNotLike(String value) {
            addCriterion("FIELD_DESCRIPT not like", value, "fieldDescript");
            return (Criteria) this;
        }

        public Criteria andFieldDescriptIn(List<String> values) {
            addCriterion("FIELD_DESCRIPT in", values, "fieldDescript");
            return (Criteria) this;
        }

        public Criteria andFieldDescriptNotIn(List<String> values) {
            addCriterion("FIELD_DESCRIPT not in", values, "fieldDescript");
            return (Criteria) this;
        }

        public Criteria andFieldDescriptBetween(String value1, String value2) {
            addCriterion("FIELD_DESCRIPT between", value1, value2, "fieldDescript");
            return (Criteria) this;
        }

        public Criteria andFieldDescriptNotBetween(String value1, String value2) {
            addCriterion("FIELD_DESCRIPT not between", value1, value2, "fieldDescript");
            return (Criteria) this;
        }

        public Criteria andFieldClassIsNull() {
            addCriterion("FIELD_CLASS is null");
            return (Criteria) this;
        }

        public Criteria andFieldClassIsNotNull() {
            addCriterion("FIELD_CLASS is not null");
            return (Criteria) this;
        }

        public Criteria andFieldClassEqualTo(String value) {
            addCriterion("FIELD_CLASS =", value, "fieldClass");
            return (Criteria) this;
        }

        public Criteria andFieldClassNotEqualTo(String value) {
            addCriterion("FIELD_CLASS <>", value, "fieldClass");
            return (Criteria) this;
        }

        public Criteria andFieldClassGreaterThan(String value) {
            addCriterion("FIELD_CLASS >", value, "fieldClass");
            return (Criteria) this;
        }

        public Criteria andFieldClassGreaterThanOrEqualTo(String value) {
            addCriterion("FIELD_CLASS >=", value, "fieldClass");
            return (Criteria) this;
        }

        public Criteria andFieldClassLessThan(String value) {
            addCriterion("FIELD_CLASS <", value, "fieldClass");
            return (Criteria) this;
        }

        public Criteria andFieldClassLessThanOrEqualTo(String value) {
            addCriterion("FIELD_CLASS <=", value, "fieldClass");
            return (Criteria) this;
        }

        public Criteria andFieldClassLike(String value) {
            addCriterion("FIELD_CLASS like", value, "fieldClass");
            return (Criteria) this;
        }

        public Criteria andFieldClassNotLike(String value) {
            addCriterion("FIELD_CLASS not like", value, "fieldClass");
            return (Criteria) this;
        }

        public Criteria andFieldClassIn(List<String> values) {
            addCriterion("FIELD_CLASS in", values, "fieldClass");
            return (Criteria) this;
        }

        public Criteria andFieldClassNotIn(List<String> values) {
            addCriterion("FIELD_CLASS not in", values, "fieldClass");
            return (Criteria) this;
        }

        public Criteria andFieldClassBetween(String value1, String value2) {
            addCriterion("FIELD_CLASS between", value1, value2, "fieldClass");
            return (Criteria) this;
        }

        public Criteria andFieldClassNotBetween(String value1, String value2) {
            addCriterion("FIELD_CLASS not between", value1, value2, "fieldClass");
            return (Criteria) this;
        }

        public Criteria andObjectNameIsNull() {
            addCriterion("OBJECT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andObjectNameIsNotNull() {
            addCriterion("OBJECT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andObjectNameEqualTo(String value) {
            addCriterion("OBJECT_NAME =", value, "objectName");
            return (Criteria) this;
        }

        public Criteria andObjectNameNotEqualTo(String value) {
            addCriterion("OBJECT_NAME <>", value, "objectName");
            return (Criteria) this;
        }

        public Criteria andObjectNameGreaterThan(String value) {
            addCriterion("OBJECT_NAME >", value, "objectName");
            return (Criteria) this;
        }

        public Criteria andObjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("OBJECT_NAME >=", value, "objectName");
            return (Criteria) this;
        }

        public Criteria andObjectNameLessThan(String value) {
            addCriterion("OBJECT_NAME <", value, "objectName");
            return (Criteria) this;
        }

        public Criteria andObjectNameLessThanOrEqualTo(String value) {
            addCriterion("OBJECT_NAME <=", value, "objectName");
            return (Criteria) this;
        }

        public Criteria andObjectNameLike(String value) {
            addCriterion("OBJECT_NAME like", value, "objectName");
            return (Criteria) this;
        }

        public Criteria andObjectNameNotLike(String value) {
            addCriterion("OBJECT_NAME not like", value, "objectName");
            return (Criteria) this;
        }

        public Criteria andObjectNameIn(List<String> values) {
            addCriterion("OBJECT_NAME in", values, "objectName");
            return (Criteria) this;
        }

        public Criteria andObjectNameNotIn(List<String> values) {
            addCriterion("OBJECT_NAME not in", values, "objectName");
            return (Criteria) this;
        }

        public Criteria andObjectNameBetween(String value1, String value2) {
            addCriterion("OBJECT_NAME between", value1, value2, "objectName");
            return (Criteria) this;
        }

        public Criteria andObjectNameNotBetween(String value1, String value2) {
            addCriterion("OBJECT_NAME not between", value1, value2, "objectName");
            return (Criteria) this;
        }

        public Criteria andColumnNameIsNull() {
            addCriterion("COLUMN_NAME is null");
            return (Criteria) this;
        }

        public Criteria andColumnNameIsNotNull() {
            addCriterion("COLUMN_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andColumnNameEqualTo(String value) {
            addCriterion("COLUMN_NAME =", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameNotEqualTo(String value) {
            addCriterion("COLUMN_NAME <>", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameGreaterThan(String value) {
            addCriterion("COLUMN_NAME >", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameGreaterThanOrEqualTo(String value) {
            addCriterion("COLUMN_NAME >=", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameLessThan(String value) {
            addCriterion("COLUMN_NAME <", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameLessThanOrEqualTo(String value) {
            addCriterion("COLUMN_NAME <=", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameLike(String value) {
            addCriterion("COLUMN_NAME like", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameNotLike(String value) {
            addCriterion("COLUMN_NAME not like", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameIn(List<String> values) {
            addCriterion("COLUMN_NAME in", values, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameNotIn(List<String> values) {
            addCriterion("COLUMN_NAME not in", values, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameBetween(String value1, String value2) {
            addCriterion("COLUMN_NAME between", value1, value2, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameNotBetween(String value1, String value2) {
            addCriterion("COLUMN_NAME not between", value1, value2, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnDescriptIsNull() {
            addCriterion("COLUMN_DESCRIPT is null");
            return (Criteria) this;
        }

        public Criteria andColumnDescriptIsNotNull() {
            addCriterion("COLUMN_DESCRIPT is not null");
            return (Criteria) this;
        }

        public Criteria andColumnDescriptEqualTo(String value) {
            addCriterion("COLUMN_DESCRIPT =", value, "columnDescript");
            return (Criteria) this;
        }

        public Criteria andColumnDescriptNotEqualTo(String value) {
            addCriterion("COLUMN_DESCRIPT <>", value, "columnDescript");
            return (Criteria) this;
        }

        public Criteria andColumnDescriptGreaterThan(String value) {
            addCriterion("COLUMN_DESCRIPT >", value, "columnDescript");
            return (Criteria) this;
        }

        public Criteria andColumnDescriptGreaterThanOrEqualTo(String value) {
            addCriterion("COLUMN_DESCRIPT >=", value, "columnDescript");
            return (Criteria) this;
        }

        public Criteria andColumnDescriptLessThan(String value) {
            addCriterion("COLUMN_DESCRIPT <", value, "columnDescript");
            return (Criteria) this;
        }

        public Criteria andColumnDescriptLessThanOrEqualTo(String value) {
            addCriterion("COLUMN_DESCRIPT <=", value, "columnDescript");
            return (Criteria) this;
        }

        public Criteria andColumnDescriptLike(String value) {
            addCriterion("COLUMN_DESCRIPT like", value, "columnDescript");
            return (Criteria) this;
        }

        public Criteria andColumnDescriptNotLike(String value) {
            addCriterion("COLUMN_DESCRIPT not like", value, "columnDescript");
            return (Criteria) this;
        }

        public Criteria andColumnDescriptIn(List<String> values) {
            addCriterion("COLUMN_DESCRIPT in", values, "columnDescript");
            return (Criteria) this;
        }

        public Criteria andColumnDescriptNotIn(List<String> values) {
            addCriterion("COLUMN_DESCRIPT not in", values, "columnDescript");
            return (Criteria) this;
        }

        public Criteria andColumnDescriptBetween(String value1, String value2) {
            addCriterion("COLUMN_DESCRIPT between", value1, value2, "columnDescript");
            return (Criteria) this;
        }

        public Criteria andColumnDescriptNotBetween(String value1, String value2) {
            addCriterion("COLUMN_DESCRIPT not between", value1, value2, "columnDescript");
            return (Criteria) this;
        }

        public Criteria andColumnTypeIsNull() {
            addCriterion("COLUMN_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andColumnTypeIsNotNull() {
            addCriterion("COLUMN_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andColumnTypeEqualTo(String value) {
            addCriterion("COLUMN_TYPE =", value, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnTypeNotEqualTo(String value) {
            addCriterion("COLUMN_TYPE <>", value, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnTypeGreaterThan(String value) {
            addCriterion("COLUMN_TYPE >", value, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnTypeGreaterThanOrEqualTo(String value) {
            addCriterion("COLUMN_TYPE >=", value, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnTypeLessThan(String value) {
            addCriterion("COLUMN_TYPE <", value, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnTypeLessThanOrEqualTo(String value) {
            addCriterion("COLUMN_TYPE <=", value, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnTypeLike(String value) {
            addCriterion("COLUMN_TYPE like", value, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnTypeNotLike(String value) {
            addCriterion("COLUMN_TYPE not like", value, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnTypeIn(List<String> values) {
            addCriterion("COLUMN_TYPE in", values, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnTypeNotIn(List<String> values) {
            addCriterion("COLUMN_TYPE not in", values, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnTypeBetween(String value1, String value2) {
            addCriterion("COLUMN_TYPE between", value1, value2, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnTypeNotBetween(String value1, String value2) {
            addCriterion("COLUMN_TYPE not between", value1, value2, "columnType");
            return (Criteria) this;
        }

        public Criteria andTableNameIsNull() {
            addCriterion("TABLE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTableNameIsNotNull() {
            addCriterion("TABLE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTableNameEqualTo(String value) {
            addCriterion("TABLE_NAME =", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotEqualTo(String value) {
            addCriterion("TABLE_NAME <>", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameGreaterThan(String value) {
            addCriterion("TABLE_NAME >", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameGreaterThanOrEqualTo(String value) {
            addCriterion("TABLE_NAME >=", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLessThan(String value) {
            addCriterion("TABLE_NAME <", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLessThanOrEqualTo(String value) {
            addCriterion("TABLE_NAME <=", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLike(String value) {
            addCriterion("TABLE_NAME like", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotLike(String value) {
            addCriterion("TABLE_NAME not like", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameIn(List<String> values) {
            addCriterion("TABLE_NAME in", values, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotIn(List<String> values) {
            addCriterion("TABLE_NAME not in", values, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameBetween(String value1, String value2) {
            addCriterion("TABLE_NAME between", value1, value2, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotBetween(String value1, String value2) {
            addCriterion("TABLE_NAME not between", value1, value2, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableDescriptIsNull() {
            addCriterion("TABLE_DESCRIPT is null");
            return (Criteria) this;
        }

        public Criteria andTableDescriptIsNotNull() {
            addCriterion("TABLE_DESCRIPT is not null");
            return (Criteria) this;
        }

        public Criteria andTableDescriptEqualTo(String value) {
            addCriterion("TABLE_DESCRIPT =", value, "tableDescript");
            return (Criteria) this;
        }

        public Criteria andTableDescriptNotEqualTo(String value) {
            addCriterion("TABLE_DESCRIPT <>", value, "tableDescript");
            return (Criteria) this;
        }

        public Criteria andTableDescriptGreaterThan(String value) {
            addCriterion("TABLE_DESCRIPT >", value, "tableDescript");
            return (Criteria) this;
        }

        public Criteria andTableDescriptGreaterThanOrEqualTo(String value) {
            addCriterion("TABLE_DESCRIPT >=", value, "tableDescript");
            return (Criteria) this;
        }

        public Criteria andTableDescriptLessThan(String value) {
            addCriterion("TABLE_DESCRIPT <", value, "tableDescript");
            return (Criteria) this;
        }

        public Criteria andTableDescriptLessThanOrEqualTo(String value) {
            addCriterion("TABLE_DESCRIPT <=", value, "tableDescript");
            return (Criteria) this;
        }

        public Criteria andTableDescriptLike(String value) {
            addCriterion("TABLE_DESCRIPT like", value, "tableDescript");
            return (Criteria) this;
        }

        public Criteria andTableDescriptNotLike(String value) {
            addCriterion("TABLE_DESCRIPT not like", value, "tableDescript");
            return (Criteria) this;
        }

        public Criteria andTableDescriptIn(List<String> values) {
            addCriterion("TABLE_DESCRIPT in", values, "tableDescript");
            return (Criteria) this;
        }

        public Criteria andTableDescriptNotIn(List<String> values) {
            addCriterion("TABLE_DESCRIPT not in", values, "tableDescript");
            return (Criteria) this;
        }

        public Criteria andTableDescriptBetween(String value1, String value2) {
            addCriterion("TABLE_DESCRIPT between", value1, value2, "tableDescript");
            return (Criteria) this;
        }

        public Criteria andTableDescriptNotBetween(String value1, String value2) {
            addCriterion("TABLE_DESCRIPT not between", value1, value2, "tableDescript");
            return (Criteria) this;
        }

        public Criteria andSortIndexIsNull() {
            addCriterion("SORT_INDEX is null");
            return (Criteria) this;
        }

        public Criteria andSortIndexIsNotNull() {
            addCriterion("SORT_INDEX is not null");
            return (Criteria) this;
        }

        public Criteria andSortIndexEqualTo(String value) {
            addCriterion("SORT_INDEX =", value, "sortIndex");
            return (Criteria) this;
        }

        public Criteria andSortIndexNotEqualTo(String value) {
            addCriterion("SORT_INDEX <>", value, "sortIndex");
            return (Criteria) this;
        }

        public Criteria andSortIndexGreaterThan(String value) {
            addCriterion("SORT_INDEX >", value, "sortIndex");
            return (Criteria) this;
        }

        public Criteria andSortIndexGreaterThanOrEqualTo(String value) {
            addCriterion("SORT_INDEX >=", value, "sortIndex");
            return (Criteria) this;
        }

        public Criteria andSortIndexLessThan(String value) {
            addCriterion("SORT_INDEX <", value, "sortIndex");
            return (Criteria) this;
        }

        public Criteria andSortIndexLessThanOrEqualTo(String value) {
            addCriterion("SORT_INDEX <=", value, "sortIndex");
            return (Criteria) this;
        }

        public Criteria andSortIndexLike(String value) {
            addCriterion("SORT_INDEX like", value, "sortIndex");
            return (Criteria) this;
        }

        public Criteria andSortIndexNotLike(String value) {
            addCriterion("SORT_INDEX not like", value, "sortIndex");
            return (Criteria) this;
        }

        public Criteria andSortIndexIn(List<String> values) {
            addCriterion("SORT_INDEX in", values, "sortIndex");
            return (Criteria) this;
        }

        public Criteria andSortIndexNotIn(List<String> values) {
            addCriterion("SORT_INDEX not in", values, "sortIndex");
            return (Criteria) this;
        }

        public Criteria andSortIndexBetween(String value1, String value2) {
            addCriterion("SORT_INDEX between", value1, value2, "sortIndex");
            return (Criteria) this;
        }

        public Criteria andSortIndexNotBetween(String value1, String value2) {
            addCriterion("SORT_INDEX not between", value1, value2, "sortIndex");
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