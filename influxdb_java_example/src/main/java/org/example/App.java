package org.example;

import org.influxdb.BatchOptions;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;
import org.influxdb.dto.Pong;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;

import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        // configure
        final String serverURL = "http://192.168.100.210:28086", username = "root", password = "root";
        final InfluxDB influxDB = InfluxDBFactory.connect(serverURL, username, password);

        // ping pong check
        Pong response = influxDB.ping();

        if (response.getVersion().equalsIgnoreCase("unknown")) {
            System.out.println("Error pinging server.");
        } else{
            System.out.println("Success pinging server.");
        }

        // creating a database
        //String databaseName = "mobigen_influxdb";
        String databaseName = "telegraf";
//        influxDB.query(new Query("CREATE DATABASE " + databaseName));
        influxDB.setDatabase(databaseName);
//
//        // policy setting
//        String retentionPolicyName = "one_day_only";
//        influxDB.query(new Query("CREATE RETENTION POLICY " + retentionPolicyName +
//                " ON " + databaseName + " DURATION 1d REPLICATION 1 DEFAULT"));
//        influxDB.setRetentionPolicy(retentionPolicyName);
//
//        // enable batch
//        influxDB.enableBatch(BatchOptions.DEFAULTS);
//
//        // write points to influxDB
//        influxDB.write(Point.measurement("mobigen")
//            .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
//            .tag("location", "santa_monica")
//            .addField("level description", "below 3 feet")
//            .addField("water_level", 2.064d)
//            .build());
//
//        influxDB.write(Point.measurement("mobigen")
//                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
//                .tag("location", "coyote_creek")
//                .addField("level description", "between 6 and 9 feet")
//                .addField("water_level", 8.12d)
//                .build());
//
//        // wait a few seconds
//        Thread.sleep(5_000L);

        // query your data using influxql
        QueryResult queryResult = influxDB.query(new Query("SELECT * FROM cpu"));

        System.out.println(queryResult);

        influxDB.close();



    }
}
