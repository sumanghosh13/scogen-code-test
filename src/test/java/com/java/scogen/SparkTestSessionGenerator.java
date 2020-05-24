package com.java.scogen;

import org.apache.spark.sql.SparkSession;

public class SparkTestSessionGenerator {
    private SparkSession spark;

    public SparkSession getSpark() {
        return spark;
    }

    public SparkTestSessionGenerator()
    {
            this.spark = SparkSession.builder().master("local").appName("HiveTest")
                    .getOrCreate();
    }
}
