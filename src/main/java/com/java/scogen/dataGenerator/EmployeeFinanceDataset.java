package com.java.scogen.dataGenerator;

import com.java.scogen.pojo.EmployeeFinancePojo;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeFinanceDataset {

    private SparkSession spark;

    public EmployeeFinanceDataset(SparkSession spark) {
        this.spark= spark;
    }

    public Dataset<Row> employeeFinanceData(List<String> empNames)
    {
       return spark.createDataFrame(empNames.stream().map(EmployeeFinancePojo::new)
                .collect(Collectors.toList()),EmployeeFinancePojo.class);
    }

}
