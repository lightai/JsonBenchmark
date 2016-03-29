package com.netease.jsonbenchmark;

/**
 *
 * Created by light on 2016/3/23.
 */
public class TestResultBean {
    public static final String GSON = "gson";
    public static final String JACLSON = "jackson";
    public static final String FASTJSON = "fastjson";

    public String parser;
    public int runCount;
    public String name;
    public long runTime;
//    public long averageTime;
    public boolean serialize;
}
