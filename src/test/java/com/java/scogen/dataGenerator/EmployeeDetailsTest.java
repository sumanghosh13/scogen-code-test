package com.java.scogen.dataGenerator;

import com.java.scogen.SparkTestSessionGenerator;
import com.java.scogen.metaData.EmployeeNameList;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.junit.Assert;
import org.junit.Test;

public class EmployeeDetailsTest extends SparkTestSessionGenerator {

    private EmployeeNameList empNameList =  new EmployeeNameList();
    private EmployeeDetailsDataset empObject = new EmployeeDetailsDataset(getSpark());

    @Test
    public void uniqieNameTest()
    {
        Dataset<Row> empData = empObject.EmployeeTable(empNameList.setofUniqueNames()).cache();
        Assert.assertEquals(1000,empData.select("fname").distinct().count());
    }

    @Test
    public void countTest()
    {
        Dataset<Row> empData = empObject.EmployeeTable(empNameList.setofUniqueNames()).cache();
        Assert.assertEquals(1000,empData.count());
    }

    @Test
    public void ageRangeTest()
    {
        Dataset<Row> empData = empObject.EmployeeTable(empNameList.setofUniqueNames()).cache();
        Assert.assertEquals(0,empData.filter(new Column("age").$greater(60)
                .or(new Column("age").$less(18))).count());

    }
}
