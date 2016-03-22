package com.netease.jsontest;

import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;

import com.netease.jsontest.model.BookList;
import com.netease.jsontest.model.BookModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
/**
 * desc:
 * Created by light on 2016/3/22.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class LargeJsonBenchmarkTest extends ActivityInstrumentationTestCase2<MainActivity> {
	public static final String TAG = "LargeJsonBenchmarkTest";

	final String[] paths = {"android", "android100", "c100", "ios", "java", "js", "mac", "python"};
	final String[] jsons = new String[paths.length];

	final static int COUNT = 1000;

	BookList bookList;
	JsonTestController controller = new JsonTestController();

	public LargeJsonBenchmarkTest() {
		super(MainActivity.class);
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		for (int i = 0; i < paths.length; i++) {
			jsons[i] = BookModel.get().getJson(getActivity(), paths[i]);
		}
		bookList = controller.gsonDSeri(paths[1], jsons[1]);
	}

	@Test
	public void testFastJsonSeri() throws Exception {
		Log.d(TAG, "fastJson 序列化开始-------------------------------------------------------");
		long t0 = System.nanoTime();
		for (int j = 0; j < COUNT; j++) {
			for (int i = 0; i < paths.length; i++) {
				String path = paths[i];
				String json = jsons[i];
				controller.fastJsonSeri(path + ".json", bookList);
			}
		}
		long t1 = System.nanoTime();
		long ns = (t1 - t0) / COUNT;
		Log.d(TAG, "fastJson 序列化 耗时：" + JsonTestController.time(ns) + "ns---------------------------");

		gc();
	}

	private void gc() {
//		Log.d(TAG, "sleep start");
//		System.gc();
//		try {
//			Thread.currentThread().sleep(5000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		Log.d(TAG, "sleep end");
	}

	@Test
	public void testGsonSeri() throws Exception {
		Log.d(TAG, "gson 序列化开始-------------------------------------------------------");
		long t0 = System.nanoTime();
		for (int j = 0; j < COUNT; j++) {
			for (int i = 0; i < paths.length; i++) {
				String path = paths[i];
				String json = jsons[i];
				controller.gsonSeri(path + ".json", bookList);
			}
		}
		long t1 = System.nanoTime();
		long ns = (t1 - t0) / COUNT;
		Log.d(TAG, "gson 序列化 耗时：" + JsonTestController.time(ns) + "ns---------------------------------");

		gc();
	}

	@Test
	public void testJacksonSeri() throws Exception {
		Log.d(TAG, "jackson 序列化开始-------------------------------------------------------");
		long t0 = System.nanoTime();
		for (int j = 0; j < COUNT; j++) {
			for (int i = 0; i < paths.length; i++) {
				String path = paths[i];
				String json = jsons[i];
				controller.jacksonSeri(path + ".json", bookList);
			}
		}
		long t1 = System.nanoTime();
		long ns = (t1 - t0) / COUNT;
		Log.d(TAG, "jackSon 序列化 耗时：" + JsonTestController.time(ns) + "ns--------------------------------");

		gc();
	}

	@Test
	public void testFastJsonDes() throws Exception {
		Log.d(TAG, "fastJson 反序列化开始-------------------------------------------------------");
		long t0 = System.nanoTime();
		for (int j = 0; j < COUNT; j++) {
			for (int i = 0; i < paths.length; i++) {
				String path = paths[i];
				String json = jsons[i];
				controller.fastJsonDSeri(path + ".json", json);
			}
		}
		long t1 = System.nanoTime();
		long ns = (t1 - t0) / COUNT;
		Log.d(TAG, "fastJson 反序列化 耗时：" + JsonTestController.time(ns) + "ns-----------------------------");

		gc();
	}

	@Test
	public void testGsonDes() throws Exception {
		Log.d(TAG, "gson 反序列化开始-------------------------------------------------------");
		long t0 = System.nanoTime();
		for (int j = 0; j < COUNT; j++) {
			for (int i = 0; i < paths.length; i++) {
				String path = paths[i];
				String json = jsons[i];
				controller.gsonDSeri(path + ".json", json);
			}
		}
		long t1 = System.nanoTime();
		long ns = (t1 - t0) / COUNT;
		Log.d(TAG, "gson 反序列化 耗时：" + JsonTestController.time(ns) + "ns--------------------");

		gc();
	}

	@Test
	public void testJacksonDes() throws Exception {
		Log.d(TAG, "jackson 反序列化开始-------------------------------------------------------");
		long t0 = System.nanoTime();
		for (int j = 0; j < COUNT; j++) {
			for (int i = 0; i < paths.length; i++) {
				String path = paths[i];
				String json = jsons[i];
				controller.jacksonDSeri(path + ".json", json);
			}
		}
		long t1 = System.nanoTime();
		long ns = (t1 - t0) / COUNT;
		Log.d(TAG, "jackSon 反序列化 耗时：" + JsonTestController.time(ns) + "ns-----------------------------");

		gc();
	}
}