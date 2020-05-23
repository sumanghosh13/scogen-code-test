package com.java.scogen.constants;

public enum DeptID {
    IT("Information Technology"), INFRA("Infratucture"), HR("Human Resource"), ADMIN("Admins"), FIN("Finance");

    public final String label;

    private DeptID(String label) {
        this.label = label;
    }
}
