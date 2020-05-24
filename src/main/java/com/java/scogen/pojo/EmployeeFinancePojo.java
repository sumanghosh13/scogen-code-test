package com.java.scogen.pojo;

import java.util.Random;

public class EmployeeFinancePojo {

    private String empid;
    private int ctc = new Random().nextInt(91)*1_000+10_000;
    private double basic = ctc*0.2;
    private double pf = ctc*0.1;
    private double gratuity  =ctc* 0.05;

    public EmployeeFinancePojo(String empid) {
        this.empid = empid;
    }

    public String getEmpid() {
        return empid;
    }

    public int getCtc() {
        return ctc;
    }

    public double getBasic() {
        return basic;
    }

    public double getPf() {
        return pf;
    }

    public double getGratuity() {
        return gratuity;
    }
}
