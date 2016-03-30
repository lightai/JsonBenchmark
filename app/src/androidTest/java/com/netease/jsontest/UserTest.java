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

//	@Test
//	public void testGsonDeserialize() {
//		new Application.BookTest().testGsonDeserialize();
//	}

  @Test
  public void testJacksonDeserialize() {
    new Application.BookTest().testJacksonDeserialize();
  }

//  @Test
//  public void testFastjsonDeserialize() {
//    new Application.BookTest().testFastjsonDeserialize();
//  }

//  @Test
//  public void testReflectInvoke() {
//    new Application.ReflectionTest().testReflection();
//  }
//
//  @Test
//  public void testReflectWithouCacheInvoke() {
//    new Application.ReflectionTest().testReflectionWithoutCache();
//  }
//
//  @Test
//  public void testNormalInvoke() {
//    new Application.ReflectionTest().testNormal();
//  }

//    @Test
//  public void testJacksonSerialize() {
//    new Application.BookTest().testJacksonSerialize();
//  }

//  @Test
//  public void testGsonSerialize() {
//    new Application.BookTest().testGsonSerialize();
//  }

//  @Test
//  public void testFastjsonSerialize() {
//    new Application.BookTest().testFastjsonSerialize();
//  }
}
