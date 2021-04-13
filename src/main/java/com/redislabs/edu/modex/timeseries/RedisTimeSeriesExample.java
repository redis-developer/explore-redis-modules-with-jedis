package com.redislabs.edu.modex.timeseries;

import java.util.HashMap;
import java.util.Map;

import com.redislabs.redistimeseries.Aggregation;
import com.redislabs.redistimeseries.RedisTimeSeries;

public class RedisTimeSeriesExample {
  public static void main(String[] args) {
    System.out.println(">>> RedisTimeSeries in Action...");

    RedisTimeSeries rts = new RedisTimeSeries();

    Map<String, String> labels = new HashMap<>();
    labels.put("country", "US");
    labels.put("cores", "8");
    rts.create("cpu1", 60 * 10 /* 10min */, labels);

    rts.create("cpu1-avg", 60 * 10 /* 10min */, null);
    rts.createRule("cpu1", Aggregation.AVG, 60 /* 1min */, "cpu1-avg");

    rts.add("cpu1", System.currentTimeMillis() / 1000 /* time sec */, 80.0);

    // Get all the timeseries in US in the last 10min average per min
    var last10min = rts.mrange(System.currentTimeMillis() / 1000 - 10 * 60, System.currentTimeMillis() / 1000, Aggregation.AVG, 60,
        "country=US");
    System.out.println(">>>> last10min ==> " + last10min);
  }
}
