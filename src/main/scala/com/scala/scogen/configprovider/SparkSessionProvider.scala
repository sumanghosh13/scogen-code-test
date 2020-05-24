package com.scala.scogen.configprovider

import java.io.File

import org.apache.spark.sql.SparkSession

class SparkSessionProvider {

  val spark: SparkSession = SparkSession
    .builder.master("local")
    .appName("HiveTest")
    .getOrCreate

}
