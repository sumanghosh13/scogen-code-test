package com.scala.scogen

import com.scala.scogen.configprovider.SparkSessionProvider
import com.scala.scogen.datagenerator.{EmployeeDeptDataset, EmployeeDetailsDataset, EmployeeFinanceDataset}
import com.scala.scogen.metaData.EmployeeNameList
import org.apache.spark.sql.functions.{col, count, max}

object EmployeeDataMain extends SparkSessionProvider {

  def main(args: Array[String]) : Unit  = {

    val listOfEmployees = EmployeeNameList.setofUniqueNames
    val employeeDs = new EmployeeDetailsDataset().employeeTable(spark,listOfEmployees)

    val eids = employeeDs.rdd.map(x=> x.getAs[String]("empid")).collect().toList

    val employeeDeptDs = new EmployeeDeptDataset().empDeptDataset(spark,eids)
    val employeeFinanceDs = new EmployeeFinanceDataset().employeeFinanceData(spark,eids)

    val empFinance = employeeDs.join(employeeFinanceDs, "empid").cache

    //# Write a program to find emp with age > 40 & ctc > 30,000
    empFinance.filter(col("age").$greater(40)
      .and(col("ctc").$greater(30000)))
      .show(500)

    //# Write a program to find dept with max emp with age > 35 & gratuity < 800
    val employePerDept = employeeDeptDs.join(empFinance, "empid")
      .filter(col("age").$greater(35).and(col("gratuity").$less(800)))
      .groupBy("deptid").agg(count("empid").as("numPeople"))

    val maxPeopleInADept = employePerDept
      .agg(max("numPeople").as("numPeople"))
      .collectAsList.get(0).getAs[Long]("numPeople")

    employePerDept.filter(col("numPeople").equalTo(maxPeopleInADept)).show()

  }

}
