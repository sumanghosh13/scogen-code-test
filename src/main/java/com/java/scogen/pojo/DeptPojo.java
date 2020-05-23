package com.java.scogen.pojo;

public class DeptPojo {
    String deptid;
    String deptName;

    public DeptPojo(String deptid, String deptName) {
        this.deptid = deptid;
        this.deptName = deptName;
    }

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return "DeptPojo{" +
                "DeptId='" + deptid + '\'' +
                ", DeptName='" + deptName + '\'' +
                '}';
    }
}
