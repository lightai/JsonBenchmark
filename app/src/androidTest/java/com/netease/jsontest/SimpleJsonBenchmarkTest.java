package com.netease.jsontest;

import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;

import com.netease.jsontest.model.User;
import com.netease.jsontest.model.UserModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
/**
 * desc:
 * Created by light on 2016/3/23.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class SimpleJsonBenchmarkTest extends ActivityInstrumentationTestCase2<MainUIActivity> {
	public static final String TAG = "SimpleJsonBenchmarkTest";

	final static int COUNT = 1000;

	final String[] userPaths = {"android", "c", "js", "lisp", "mac", "music", "php", "python", "lisp"};
	final String[] userJsons = new String[userPaths.length];

	User bookList;
	JsonTestController<User> controller = new JsonTestController(User.class);

	public SimpleJsonBenchmarkTest() {
		super(MainUIActivity.class);
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		for (int i = 0; i < userPaths.length; i++) {
			userJsons[i] = UserModel.get().getUserJson(getActivity(), userPaths[i]);
		}
		bookList = controller.gsonDSeri(userPaths[1], userJsons[1]);
	}

	@Test
	public void testFastJsonSeri() throws Exception {
		Log.d(TAG, "fastJson 序列化开始-------------------------------------------------------");
		long t0 = System.nanoTime();
		for (int j = 0; j < COUNT; j++) {
			for (int i = 0; i < userPaths.length; i++) {
				String path = userPaths[i];
				String json = userJsons[i];
				controller.fastJsonSeri(path + ".json", bookList);
			}
		}
		long t1 = System.nanoTime();
		long ns = (t1 - t0) / COUNT;
		Log.d(TAG, "fastJson 序列化 耗时：" + JsonTestController.time(ns) + "ns---------------------------");


	}

	@Test
	public void testGsonSeri() throws Exception {
		Log.d(TAG, "gson 序列化开始-------------------------------------------------------");
		long t0 = System.nanoTime();
		for (int j = 0; j < COUNT; j++) {
			for (int i = 0; i < userPaths.length; i++) {
				String path = userPaths[i];
				String json = userJsons[i];
				controller.gsonSeri(path + ".json", bookList);
			}
		}
		long t1 = System.nanoTime();
		long ns = (t1 - t0) / COUNT;
		Log.d(TAG, "gson 序列化 耗时：" + JsonTestController.time(ns) + "ns---------------------------------");
	}

	@Test
	public void testJacksonSeri() throws Exception {
		Log.d(TAG, "jackson 序列化开始-------------------------------------------------------");
		long t0 = System.nanoTime();
		for (int j = 0; j < COUNT; j++) {
			for (int i = 0; i < userPaths.length; i++) {
				String path = userPaths[i];
				String json = userJsons[i];
				controller.jacksonSeri(path + ".json", bookList);
			}
		}
		long t1 = System.nanoTime();
		long ns = (t1 - t0) / COUNT;
		Log.d(TAG, "jackSon 序列化 耗时：" + JsonTestController.time(ns) + "ns--------------------------------");
	}

	@Test
	public void testFastJsonDes() throws Exception {
		Log.d(TAG, "fastJson 反序列化开始-------------------------------------------------------");
		long t0 = System.nanoTime();
		for (int j = 0; j < COUNT; j++) {
			for (int i = 0; i < userPaths.length; i++) {
				String path = userPaths[i];
				String json = userJsons[i];
				controller.fastJsonDSeri(path + ".json", json);
			}
		}
		long t1 = System.nanoTime();
		long ns = (t1 - t0) / COUNT;
		Log.d(TAG, "fastJson 反序列化 耗时：" + JsonTestController.time(ns) + "ns-----------------------------");
	}

	@Test
	public void testGsonDes() throws Exception {
		Log.d(TAG, "gson 反序列化开始-------------------------------------------------------");
		long t0 = System.nanoTime();
		for (int j = 0; j < COUNT; j++) {
			for (int i = 0; i < userPaths.length; i++) {
				String path = userPaths[i];
				String json = userJsons[i];
				controller.gsonDSeri(path + ".json", json);
			}
		}
		long t1 = System.nanoTime();
		long ns = (t1 - t0) / COUNT;
		Log.d(TAG, "gson 反序列化 耗时：" + JsonTestController.time(ns) + "ns--------------------");
	}

	@Test
	public void testJacksonDes() throws Exception {
		Log.d(TAG, "jackson 反序列化开始-------------------------------------------------------");
		long t0 = System.nanoTime();
		for (int j = 0; j < COUNT; j++) {
			for (int i = 0; i < userPaths.length; i++) {
				String path = userPaths[i];
				String json = userJsons[i];
				controller.jacksonDSeri(path + ".json", json);
			}
		}
		long t1 = System.nanoTime();
		long ns = (t1 - t0) / COUNT;
		Log.d(TAG, "jackSon 反序列化 耗时：" + JsonTestController.time(ns) + "ns-----------------------------");
	}
}