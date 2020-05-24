package com.scala.scogen.datagenerator

import com.scala.scogen.BaseClassTest
import com.scala.scogen.metaData.EmployeeNameList
import org.apache.spark.sql.{Dataset, Row}
import org.junit.Assert

class EmployeeDetailsDatasetTest extends BaseClassTest{

  var employeeDeptDataset:Dataset[Row] = spark.emptyDataFrame
  import spark.implicits._

  def initilize(): Unit =
  {
    if(employeeDeptDataset.isEmpty) {
      val listOfEmployees = EmployeeNameList.setofUniqueNames
      val employeeDs = new EmployeeDetailsDataset().employeeTable(spark, listOfEmployees)

      val eids = employeeDs.rdd.map(x => x.getAs[String]("empid")).collect().toList
      employeeDeptDataset = new EmployeeDeptDataset().empDeptDataset(spark, eids).cache()
    }
  }

  "dept employee count" should "equal 1000" in {
    initilize()
    Assert.assertEquals(1000, employeeDeptDataset.count)
  }

  "number of unique dept" should "be equals 5" in
  {
    initilize()
    Assert.assertEquals(5, employeeDeptDataset.selectExpr("deptid").distinct.count)
  }

  "tow of the depts " should "have 500 employees" in
  {
    initilize()
    val count = employeeDeptDataset
      .selectExpr("deptid").filter($"deptid" === "IT" || $"deptid" === "HR")
      .count
    Assert.assertEquals(500, count)
  }




}
