package com.java.scogen.pojo;

public class EmployeeDeptPojo {

    String empid;
    String deptid;

    public EmployeeDeptPojo(String empid, String deptid) {
        this.empid = empid;
        this.deptid = deptid;
    }

    public String getEmpid() {
        return empid;
    }

    public String getDeptid() {
        return deptid;
    }
}
