package com.scala.scogen.configprovider

import java.io.File

import org.apache.spark.sql.{Dataset, Row, SparkSession}

class SparkSessionProvider {

  val spark: SparkSession = SparkSession
    .builder.master("local")
    .appName("HiveTest")
    .getOrCreate

  val ds  = spark.read.csv("")
  spark.sparkContext.broadcast(ds);
}
