package com.navinfo.core.entity;

public class RoleModulePermissionRel {
    private String relId;

    private String roleId;

    private String modulePermissionId;

    public String getRelId() {
        return relId;
    }

    public void setRelId(String relId) {
        this.relId = relId == null ? null : relId.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getModulePermissionId() {
        return modulePermissionId;
    }

    public void setModulePermissionId(String modulePermissionId) {
        this.modulePermissionId = modulePermissionId == null ? null : modulePermissionId.trim();
    }
}