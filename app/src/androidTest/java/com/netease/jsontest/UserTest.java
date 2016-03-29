package com.netease.jsontest;

import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import com.netease.jsonbenchmark.Application;

import org.junit.Test;
import org.junit.runner.RunWith;
/**
 * desc:
 * Created by light on 2016/3/28.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class UserTest extends ActivityInstrumentationTestCase2<MainUIActivity> {
	public UserTest() {
		super(MainUIActivity.class);
	}

	@Test
	public void testGsonDeserialize() {
		new Application.UserTest().testGsonDeserialize();
	}

  @Test
  public void testJacksonDeserialize() {
    new Application.UserTest().testJacksonDeserialize();
  }

  @Test
  public void testFastjsonDeserialize() {
    new Application.UserTest().testFastjsonDeserialize();
  }
}
