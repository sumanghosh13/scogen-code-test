package com.java.scogen.configprovider;

import org.apache.spark.sql.SparkSession;

import java.io.File;

public class SparkSessionProvider {

    private SparkSession spark;

    public SparkSession getSpark() {
        return spark;
    }

    public SparkSessionProvider(boolean hive)
    {
        if(hive) {
            String warehouseLocation = new File("spark-warehouse").getAbsolutePath();
            this.spark = SparkSession
                    .builder()
                    .master("local")
                    .appName("HiveTest")
                    .config("spark.sql.warehouse.dir", warehouseLocation)
                    .enableHiveSupport()
                    .getOrCreate();
        }else
            this.spark = SparkSession.builder().master("local").appName("HiveTest")
                    .getOrCreate();
    }
}
