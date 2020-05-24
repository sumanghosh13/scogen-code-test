package com.scala.scogen.datagenerator

import com.scala.scogen.BaseClassTest
import com.scala.scogen.metaData.EmployeeNameList
import org.junit.Assert

class EmployeeDetailsTest extends BaseClassTest {

  val listOfEmployees = EmployeeNameList.setofUniqueNames
  val employeeDs = new EmployeeDetailsDataset().employeeTable(spark,listOfEmployees).cache()
  import spark.implicits._

  "it" should "have 1000 unique first names" in {
    Assert.assertEquals(1000, employeeDs.select("fname").distinct.count)
  }

  "it" should "have 1000 employees in total" in {
    Assert.assertEquals(1000, employeeDs.count)
  }

  "all employees" should "have age range of 18 to 60" in {
    Assert.assertEquals(0, employeeDs.filter(($"age" > 60) || ($"age"<18)).count)
  }

}
