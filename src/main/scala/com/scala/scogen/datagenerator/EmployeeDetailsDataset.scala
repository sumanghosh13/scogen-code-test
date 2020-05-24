package com.scala.scogen.datagenerator

import com.scala.scogen.caseclasses.EmployeeDetailsPojo
import org.apache.spark.sql.{Dataset, Row, SparkSession}


class EmployeeDetailsDataset {

  def employeeTable(spark:SparkSession,nameSet: Set[String]): Dataset[Row] = {
    import spark.implicits._
    val namesDetails = nameSet.map(x=>EmployeeDetailsPojo(x)).toList
    spark.sparkContext.parallelize(namesDetails).toDF()
  }

}
