package com.scala.scogen.datagenerator

import com.scala.scogen.caseclasses.DeptPojo
import com.scala.scogen.constants.DeptID
import org.apache.spark.sql.{Dataset, Row, SparkSession}

class DeptDetailsDataset {

  def deptDataDataset(spark:SparkSession): Dataset[Row] = {
    import spark.implicits._
    val deptDetails = DeptID.deptIds.map(x=> DeptPojo(x._1,x._2))
    spark.sparkContext.parallelize(deptDetails).toDF()
  }

}
