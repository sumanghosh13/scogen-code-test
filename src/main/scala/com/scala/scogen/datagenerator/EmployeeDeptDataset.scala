package com.scala.scogen.datagenerator

import com.scala.scogen.caseclasses.EmpDeptPojo
import com.scala.scogen.constants.DeptID
import org.apache.spark.sql.{Dataset, Row, SparkSession}

import scala.util.Random

class EmployeeDeptDataset {

  def empDeptDataset(spark:SparkSession,empid: List[String]):Dataset[Row] = {

    import spark.implicits._
    val deptFirstList = DeptID.deptIds.slice(0,2).map(_._1)
    val deptSecondtList = DeptID.deptIds.slice(2,5).map(_._1)
    val FirstSetOfEmp = empid.slice(0,500).map(eid => EmpDeptPojo(eid,deptFirstList(new Random().nextInt(2))))
    val SecondSetOfEmp = empid.slice(500,1000).map(eid => EmpDeptPojo(eid,deptSecondtList(new Random().nextInt(3))))

    spark.sparkContext.parallelize(FirstSetOfEmp ++ SecondSetOfEmp).toDF()
  }
}
