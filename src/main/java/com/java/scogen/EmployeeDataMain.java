package com.java.scogen;

import com.java.scogen.dataGenerator.*;
import com.java.scogen.configProvider.SparkSessionProvider;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import java.util.List;
import java.util.Set;

public class EmployeeDataMain extends SparkSessionProvider {

    public EmployeeDataMain(boolean hive) {
        super(hive);
    }

    public static void main(String[] args) {
       new EmployeeDataMain(false).execute();

    }

    public void execute()
    {
        EmployeeNameList employeeNameList = new EmployeeNameList();
        Set<String> nameSet = employeeNameList.setofUniqueNames();

        EmployeeDetailsDataset employeeDetailsDataset = new EmployeeDetailsDataset(getSpark());
        Dataset<Row> employee = employeeDetailsDataset.EmployeeTable(nameSet);
        List<String> ids = employee.select("empid").toJavaRDD().map(x->x.<String>getAs("empid"))
                .collect();

        EmployeeFinanceDataset employeeFinanceDataset = new EmployeeFinanceDataset(getSpark());
        employeeFinanceDataset.employeeFinanceData(ids).show();

        DeptDetailsDataset deptData = new DeptDetailsDataset(getSpark());
        deptData.deptDataDataset().show();

        EmployeeDeptDataset employeeDeptDataset = new EmployeeDeptDataset(getSpark());
        Dataset<Row> emp = employeeDeptDataset.empDeptDataset(ids).cache();

        emp.show(1000);
        emp.repartition()
        System.out.println(emp.count());
    }
}
