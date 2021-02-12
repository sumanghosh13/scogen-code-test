package com.scala.scogen.datagenerator

import com.scala.scogen.BaseClassTest
import com.scala.scogen.metaData.EmployeeNameList
import org.apache.spark.sql.{Dataset, Row}
import org.junit.Assert

class DetpSelectTest extends BaseClassTest{

  var employeeDeptDataset:Dataset[Row] = spark.emptyDataFrame
  var employeeFinanceDataset: Dataset[Row] = spark.emptyDataFrame
  val listOfEmployees = EmployeeNameList.setofUniqueNames
  val employeeDs = new EmployeeDetailsDataset().employeeTable(spark,listOfEmployees).cache()
  import spark.implicits._

  def initilize(): Unit =
  {
    if(employeeFinanceDataset.isEmpty) {
      val listOfEmployees = EmployeeNameList.setofUniqueNames
      val employeeDs = new EmployeeDetailsDataset().employeeTable(spark, listOfEmployees)

      val eids = employeeDs.rdd.map(x => x.getAs[String]("empid")).collect().toList
      employeeFinanceDataset = new EmployeeFinanceDataset().employeeFinanceData(spark, eids).cache()
    }

    if(employeeDeptDataset.isEmpty) {
      val listOfEmployees = EmployeeNameList.setofUniqueNames
      val employeeDs = new EmployeeDetailsDataset().employeeTable(spark, listOfEmployees)

      val eids = employeeDs.rdd.map(x => x.getAs[String]("empid")).collect().toList
      employeeDeptDataset = new EmployeeDeptDataset().empDeptDataset(spark, eids).cache()
    }
  }

  import spark.implicits._
  "it " should "retun the dept with max no of emp" in {
    initilize()
    val testData= new DetpSelect()
      .filterAndreturnDept(employeeDeptDataset.join(employeeFinanceDataset, "empid").join(employeeDs,"empid"))

    Assert.assertTrue(testData.select("age").filter($"age".lt(35)).count() == 0)
    Assert.assertTrue(testData.select("gratuity").filter($"gratuity".gt(800)).count() == 0)

  }


}
