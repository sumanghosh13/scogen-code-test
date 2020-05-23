package com.java.scogen.dataGenerator;

import com.java.scogen.pojo.EmployeeDetailsPojo;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EmployeeDetailsDataset {

    private SparkSession spark;

    public EmployeeDetailsDataset(SparkSession spark) {
        this.spark= spark;
    }

    public Dataset<Row> EmployeeTable(Set<String> nameSet)
    {
        List<EmployeeDetailsPojo> namesDetails = nameSet.stream().map(EmployeeDetailsPojo::new )
                .collect(Collectors.toList());

        return spark.createDataFrame(namesDetails,EmployeeDetailsPojo.class);
    }
}
