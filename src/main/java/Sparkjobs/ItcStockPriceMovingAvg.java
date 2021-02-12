package Sparkjobs;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.expressions.Window;
import org.apache.spark.sql.types.DataTypes;

import static org.apache.spark.sql.functions.avg;
import static org.apache.spark.sql.functions.col;

public class ItcStockPriceMovingAvg {
    public static void main(String[] args) {

        SparkSession spark = SparkSession.builder().master("local[2]").getOrCreate();

        Dataset<Row> indata = spark.read()
                .option("header",true)
                .option("inferSchema", "true")
                .csv(args[0])
                .repartition(10);

        indata.printSchema();
        indata.show(5);

        indata = indata
                .withColumn("rollingAvg15days",
                        avg("Close").over(Window.rowsBetween(Window.currentRow()-15, Window.currentRow())))
                .withColumn("rollingAvg22days",
                        avg("Close").over(Window.rowsBetween(Window.currentRow()-22, Window.currentRow())))
                .withColumn("weightedAvg", col("Close")
                        .$plus(col("rollingAvg15days").multiply(3)
                        .$plus(col("rollingAvg22days").multiply(6))).divide(10));

        indata.show(30);
        indata.select("Date","Close","rollingAvg15days","rollingAvg22days","weightedAvg")
                .write().option("header",true)
                .csv(args[1]);

    }
}
