package com.java.scogen.dataGenerator;

import com.java.scogen.SparkTestSessionGenerator;
import com.java.scogen.metaData.EmployeeNameList;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class EmployeeDetailsDatasetTest extends SparkTestSessionGenerator {

    private Dataset<Row> employeeDeptDataset = null;

    private void initilize()
    {
        if(employeeDeptDataset == null) {
            EmployeeNameList employeeNameList = new EmployeeNameList();
            Set<String> nameSet = employeeNameList.setofUniqueNames();
            EmployeeDetailsDataset employeeDetailsDataset = new EmployeeDetailsDataset(getSpark());
            Dataset<Row> employee = employeeDetailsDataset.EmployeeTable(nameSet);
            List<String> ids = employee.select("empid").toJavaRDD().map(x -> x.<String>getAs("empid"))
                    .collect();
            employeeDeptDataset = new EmployeeDeptDataset(getSpark()).empDeptDataset(ids).cache();
        }

    }
    @Test
    public void deptEmployeeCountTest()
    {
        initilize();
        Assert.assertEquals(1000,employeeDeptDataset.count());
    }

    @Test
    public void deptCountTest()
    {
        initilize();
        Assert.assertEquals(5,employeeDeptDataset.selectExpr("deptid").distinct().count());
    }

    @Test
    public void dept500Test()
    {
        initilize();
        long count = employeeDeptDataset.selectExpr("deptid").filter(new Column("deptid").equalTo("IT")
                .or(new Column("deptid").equalTo("INFRA"))).count();
        Assert.assertEquals(500,count);
    }

}
