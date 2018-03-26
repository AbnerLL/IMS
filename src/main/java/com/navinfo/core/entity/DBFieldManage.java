package com.navinfo.core.entity;

public class DBFieldManage {
    private String uuid;

    private String fieldName;

    private String fieldDescript;

    private String tableName;

    private String fieldClass;

    private String columnName;

    private String columnDescript;

    private String columnType;

    private String sortIndex;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName == null ? null : fieldName.trim();
    }

    public String getFieldDescript() {
        return fieldDescript;
    }

    public void setFieldDescript(String fieldDescript) {
        this.fieldDescript = fieldDescript == null ? null : fieldDescript.trim();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public String getFieldClass() {
        return fieldClass;
    }

    public void setFieldClass(String fieldClass) {
        this.fieldClass = fieldClass == null ? null : fieldClass.trim();
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName == null ? null : columnName.trim();
    }

    public String getColumnDescript() {
        return columnDescript;
    }

    public void setColumnDescript(String columnDescript) {
        this.columnDescript = columnDescript == null ? null : columnDescript.trim();
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType == null ? null : columnType.trim();
    }

    public String getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(String sortIndex) {
        this.sortIndex = sortIndex == null ? null : sortIndex.trim();
    }
}