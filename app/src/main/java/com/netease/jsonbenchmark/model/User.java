package com.netease.jsonbenchmark.model;

/**
 * desc:
 * Created by light on 2016/3/23.
 */
public class User {
  public String homepage;
  public String icon;
  public String id;
  public String title;
  public String uid;
  public int r;
  public Stats stats;

  public static class Stats {
    public int board;
    public int bub;
    public int collect;
  }
}
