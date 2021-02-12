package com.java.scogen;

import com.java.scogen.configprovider.SparkSessionProvider;
import org.apache.spark.sql.SparkSession;

public class Test {

    public static void main(String[] args) {

        SparkSession spark = SparkSession.builder()
                .master("local")
                .appName("test")
                .getOrCreate();


    }

}
