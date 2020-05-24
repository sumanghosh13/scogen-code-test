package com.scala.scogen.datagenerator

import com.scala.scogen.caseclasses.EmployeeFinancePojo
import org.apache.spark.sql.{Dataset, Row, SparkSession}

import scala.util.Random

class EmployeeFinanceDataset {

  def employeeFinanceData(spark:SparkSession,empIds: List[String]): Dataset[Row] = {
    import spark.implicits._
    val empFinancelist = empIds.map(empid=> {
      val ctc = new Random().nextInt(91) * 1000 + 10000
      EmployeeFinancePojo(empid,ctc,ctc*0.2,ctc*0.1,ctc*0.05)
    })

    spark.sparkContext.parallelize(empFinancelist).toDF()
  }

}
