package com.navinfo.core.vo;

/**
 * 此类是用来展示父模块、子模块以及子模块的权限的树结构类
 * Created by luozhihui on 2018/1/28.
 */
public class PermissionTreeVO {
    private String pid;//树结构节点的父ID
    private String id;//树结构节点的ID
    private String name;//树结构节点的名称
    private boolean open=false;//该节点是否打开(默认关闭)
    private boolean checked=false;//设置该节点是否被选中（设置为多选框有效，默认为不选）

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PermissionTreeVO that = (PermissionTreeVO) o;

        if (open != that.open) return false;
        if (checked != that.checked) return false;
        if (pid != null ? !pid.equals(that.pid) : that.pid != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = pid != null ? pid.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (open ? 1 : 0);
        result = 31 * result + (checked ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PermissionTreeVO{" +
                "pid='" + pid + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", open=" + open +
                ", checked=" + checked +
                '}';
    }
}
