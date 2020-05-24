package com.java.scogen.datagenerator;

import com.java.scogen.pojo.DeptPojo;
import com.java.scogen.constants.DeptID;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DeptDetailsDataset {

    private SparkSession spark;

    public DeptDetailsDataset(SparkSession spark) {
        this.spark= spark;
    }

    public Dataset<Row> deptDataDataset()
    {
        List<DeptPojo> deptList =  Arrays.stream(DeptID.values()).map(x-> new DeptPojo(x.toString(),x.label))
                .collect(Collectors.toList());
        return spark.createDataFrame(deptList,DeptPojo.class);
    }
}
