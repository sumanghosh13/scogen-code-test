package com.java.scogen;

import com.java.scogen.configprovider.SparkSessionProvider;
import com.java.scogen.process.ExecuteProcess;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.elasticsearch.spark.*;
import org.elasticsearch.spark.rdd.api.java.JavaEsSpark;

public class EmployeeDataMain extends SparkSessionProvider {

    public EmployeeDataMain(boolean hive) {
        super(hive);
    }

    public static void main(String[] args) {

        new ExecuteProcess(new EmployeeDataMain(false).getSpark()).execute();
        new EmployeeDataMain(false)
                .getSpark().read()
                .format("org.elasticsearch.spark.sql")
                .option("es.nodes","localhost")
                .option("es.port",9200)
//                .option("es.nodes.wan.only","false")
//                .option("es.net.proxy.http.host","https://search-solytics-tzxvnvrvkscgklaedz5lr3iqxu.ap-south-1.es.amazonaws.com")
                .option("es.resource", ".kibana_1/_doc")
                .load()
                .show();

        SparkSession spark = SparkSession.builder().master("local[2]")
                .config("es.index.auto.create", "true")
                .getOrCreate();

        SparkConf conf = new SparkConf()
                .setMaster("local[2]")
                .set("es.nodes","https://search-solytics-tzxvnvrvkscgklaedz5lr3iqxu.ap-south-1.es.amazonaws.com/")
                .set("es.port","8080")
                .set("es.nodes.wan.only","true")
                .setAppName("test");

        JavaSparkContext jsc = new JavaSparkContext(conf);


        JavaEsSpark.esJsonRDD(jsc,"/news_data/doc").foreach(x-> System.out.println(x));

    }
}
