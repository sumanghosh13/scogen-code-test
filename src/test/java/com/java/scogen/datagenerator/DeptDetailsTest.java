package com.java.scogen.datagenerator;

import com.java.scogen.SparkTestSessionGenerator;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.junit.Assert;
import org.junit.Test;

public class DeptDetailsTest extends SparkTestSessionGenerator
{

    private Dataset<Row> deptDataset = new DeptDetailsDataset(getSpark()).deptDataDataset().cache();

    @Test
    public void countTest()
    {
        Assert.assertEquals(5,deptDataset.count());
    }

    @Test
    public void iTDeptExistTest()
    {
        Assert.assertEquals(1,deptDataset.filter(new Column("deptid").equalTo("IT")).count());
    }

    @Test
    public void mgrDeptDpesNotExistTest()
    {
        Assert.assertEquals(0,deptDataset.filter(new Column("deptid").equalTo("MGR")).count());
    }
}
