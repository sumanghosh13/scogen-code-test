package com.java.scogen.process;

import com.java.scogen.datagenerator.EmployeeDeptDataset;
import com.java.scogen.datagenerator.EmployeeDetailsDataset;
import com.java.scogen.datagenerator.EmployeeFinanceDataset;
import com.java.scogen.metadata.EmployeeNameList;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.List;
import java.util.Set;

import static org.apache.spark.sql.functions.*;

public class ExecuteProcess {
    
    private SparkSession spark;

    public ExecuteProcess(SparkSession spark) {
        this.spark = spark;
    }

    public void execute()
    {
        EmployeeNameList employeeNameList = new EmployeeNameList();
        Set<String> nameSet = employeeNameList.setofUniqueNames();

        EmployeeDetailsDataset employeeDetailsDataset = new EmployeeDetailsDataset(spark);
        Dataset<Row> employee = employeeDetailsDataset.EmployeeTable(nameSet).cache();
        List<String> ids = employee.select("empid").toJavaRDD().map(x->x.<String>getAs("empid"))
                .collect();

        EmployeeFinanceDataset employeeFinanceData = new EmployeeFinanceDataset(spark);
        Dataset<Row> employeeFinanceDataset = employeeFinanceData.employeeFinanceData(ids);

        EmployeeDeptDataset employeeDeptData = new EmployeeDeptDataset(spark);
        Dataset<Row> empDeptDataset = employeeDeptData.empDeptDataset(ids);


        Dataset<Row> empFinance = employee.join(employeeFinanceDataset,"empid").cache();

        //# Write a program to find emp with age > 40 & ctc > 30,000
        empFinance.filter(col("age").$greater(40).and(col("ctc").$greater(30_000)))
                .show(500);

        //# Write a program to find dept with max emp with age > 35 & gratuity < 800

        Dataset<Row> employePerDept = empDeptDataset.join(empFinance,"empid")
                .filter(col("age").$greater(35).and(col("gratuity").$less(800)))
                .groupBy("deptid")
                .agg(
                        count("empid").as("numPeople")
                );

        employePerDept.show();

        Long maxPeopleInADept = employePerDept.agg(max("numPeople").as("numPeople"))
                .collectAsList().get(0).<Long>getAs("numPeople");

        employePerDept.filter(col("numPeople").equalTo(maxPeopleInADept))
                .show();

    }
}
