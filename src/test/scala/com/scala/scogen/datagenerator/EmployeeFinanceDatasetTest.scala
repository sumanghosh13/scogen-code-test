package com.scala.scogen.datagenerator

import com.scala.scogen.BaseClassTest
import com.scala.scogen.metaData.EmployeeNameList
import org.apache.spark.sql.{Column, Dataset, Row}
import org.junit.Assert

class EmployeeFinanceDatasetTest extends BaseClassTest {

  var employeeFinanceDataset: Dataset[Row] = spark.emptyDataFrame
  import spark.implicits._

  def initilize(): Unit =
  {
    if(employeeFinanceDataset.isEmpty) {
      val listOfEmployees = EmployeeNameList.setofUniqueNames
      val employeeDs = new EmployeeDetailsDataset().employeeTable(spark, listOfEmployees)

      val eids = employeeDs.rdd.map(x => x.getAs[String]("empid")).collect().toList
      employeeFinanceDataset = new EmployeeFinanceDataset().employeeFinanceData(spark, eids).cache()
    }
  }

  "finance data" should "also have only 1000 employes" in {
    initilize()
    Assert.assertEquals(1000, employeeFinanceDataset.count)
  }

  "employee ctc range" should "be 10,000 - 1,00,000" in {
    initilize()
    val count = employeeFinanceDataset
      .filter($"ctc" > 100000 && $"basic"<10000).count
    Assert.assertEquals(0, count)

  }

  "all eployees " should "have basic as 20% of ctc not more" in {

    initilize()
    val result = employeeFinanceDataset
      .select($"ctc",$"basic").rdd.map((x: Row) => {
      def foo(x: Row) = {
        val ctc = x.getAs[Integer]("ctc")
        val basic = x.getAs[Double]("basic")
        basic == ctc * 0.2
      }

      foo(x)
    }).reduce((x: Boolean, y: Boolean) => x && y)

    Assert.assertTrue(result)
  }



}
