package com.scala.scogen

import org.apache.spark.sql.SparkSession
import org.scalatest.flatspec.AnyFlatSpec

class BaseClassTest extends AnyFlatSpec {

  val spark: SparkSession = SparkSession
    .builder.master("local")
    .appName("HiveTest")
    .getOrCreate

}
