package com.netease.jsontest;

import android.app.Application;
import android.content.Context;
/**
 * desc:
 * Created by light on 2016/3/28.
 */
public class AppContext extends Application {
  static Context sContext;

  @Override
  public void onCreate() {
    super.onCreate();
    sContext = getApplicationContext();
  }

  public static Context getContext() {
    return sContext;
  }
}
