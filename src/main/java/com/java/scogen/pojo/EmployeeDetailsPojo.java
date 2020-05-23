package com.java.scogen.pojo;

import com.java.scogen.dataGenerator.EmployeeNameList;

public class EmployeeDetailsPojo {

    private String fname;
    private String lname;
    private String empid;
    private int age;

    public EmployeeDetailsPojo(String fname) {
        this.fname = fname;
        this.lname = EmployeeNameList.randomNameGenerator();
        this.empid = EmployeeNameList.randomIDGenerator();
        this.age = EmployeeNameList.randomAgeGenerator();
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "EmployeePojo{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", empid='" + empid + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
