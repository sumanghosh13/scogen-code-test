package com.java.scogen.datagenerator;

import com.java.scogen.SparkTestSessionGenerator;
import com.java.scogen.metadata.EmployeeNameList;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.junit.Assert;
import org.junit.Test;

public class EmployeeDetailsTest extends SparkTestSessionGenerator {

    private EmployeeNameList empNameList =  new EmployeeNameList();
    private Dataset<Row> empData = new EmployeeDetailsDataset(getSpark())
            .EmployeeTable(empNameList.setofUniqueNames()).cache();

    @Test
    public void uniqieNameTest()
    {
        Assert.assertEquals(1000,empData.select("fname").distinct().count());
    }

    @Test
    public void countTest()
    {
        Assert.assertEquals(1000,empData.count());
    }

    @Test
    public void ageRangeTest()
    {
        Assert.assertEquals(0,empData.filter(new Column("age").$greater(60)
                .or(new Column("age").$less(18))).count());

    }
}
