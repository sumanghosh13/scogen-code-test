package com.scala.scogen.datagenerator

import org.apache.spark.sql.functions.{col, count, max}
import org.apache.spark.sql.{Dataset, Row}

class DetpSelect {

  def filterAndreturnDept(inEmployePerDept : Dataset[Row] ): Dataset[Row] = {
    inEmployePerDept.filter(col("age").$greater(50).and(col("gratuity").$less(800)))
  }

}
