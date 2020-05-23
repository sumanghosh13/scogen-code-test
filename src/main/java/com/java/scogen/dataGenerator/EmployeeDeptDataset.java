package com.java.scogen.dataGenerator;

import com.java.scogen.pojo.EmployeeDeptPojo;
import com.java.scogen.constants.DeptID;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class EmployeeDeptDataset {

    private SparkSession spark;

    public EmployeeDeptDataset(SparkSession spark) {
        this.spark= spark;
    }

    public Dataset<Row> empDeptDataset(List<String> empid)
    {
        List<String> deptlist = Arrays.stream(DeptID.values()).map(Enum::toString)
                .collect(Collectors.toList());
        List<EmployeeDeptPojo>firstDeptList = empid.stream().limit(500)
                .map(x-> new EmployeeDeptPojo(x,deptlist.get(new Random().nextInt(2))))
                .collect(Collectors.toList());

        List<EmployeeDeptPojo>secondDeptList = empid.stream().skip(500)
                .map(x-> new EmployeeDeptPojo(x,deptlist.get(new Random().nextInt(3)+2)))
                .collect(Collectors.toList());

        return spark.createDataFrame(firstDeptList,EmployeeDeptPojo.class)
                .union(spark.createDataFrame(secondDeptList,EmployeeDeptPojo.class));
    }
}
