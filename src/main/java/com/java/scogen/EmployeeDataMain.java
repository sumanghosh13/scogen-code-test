package com.java.scogen;

import com.java.scogen.configProvider.SparkSessionProvider;
import com.java.scogen.process.ExecuteProcess;

public class EmployeeDataMain extends SparkSessionProvider {

    public EmployeeDataMain(boolean hive) {
        super(hive);
    }

    public static void main(String[] args) {
       new ExecuteProcess(new EmployeeDataMain(false).getSpark()).execute();
    }
}
