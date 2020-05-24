package com.java.scogen.datagenerator;

import com.java.scogen.SparkTestSessionGenerator;
import com.java.scogen.metadata.EmployeeNameList;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class EmployeeFinanceDatasetTest extends SparkTestSessionGenerator {


    private Dataset<Row> employeeFinanceDataset = null;

    private void initilize()
    {
        if(employeeFinanceDataset == null) {
            EmployeeNameList employeeNameList = new EmployeeNameList();
            Set<String> nameSet = employeeNameList.setofUniqueNames();
            EmployeeDetailsDataset employeeDetailsDataset = new EmployeeDetailsDataset(getSpark());
            Dataset<Row> employee = employeeDetailsDataset.EmployeeTable(nameSet);
            List<String> ids = employee.select("empid").toJavaRDD().map(x -> x.<String>getAs("empid"))
                    .collect();
            employeeFinanceDataset = new EmployeeFinanceDataset(getSpark())
                    .employeeFinanceData(ids).cache();
        }

    }

    @Test
    public void employeeCountTest()
    {
        initilize();
        Assert.assertEquals(1000, employeeFinanceDataset.count());

    }

    @Test
    public void employeeCTCRangeTest()
    {
        initilize();
        long count = employeeFinanceDataset.filter(new Column("ctc").$greater(1_00_000)
                .and(new Column("basic").$less(10_000))).count();
        Assert.assertEquals(0, count);

    }

    @Test
    public void employeesBasicRangeTest()
    {
        initilize();
        boolean result = employeeFinanceDataset.select(new Column("ctc"),new Column("basic"))
                .toJavaRDD()
                .map(x-> {
                    int ctc = x.<Integer>getAs("ctc");
                    double basic = x.<Double>getAs("basic");
                    return basic == ctc*0.2;
                }).reduce((x,y)-> x && y);

        Assert.assertTrue(result);

    }

}
