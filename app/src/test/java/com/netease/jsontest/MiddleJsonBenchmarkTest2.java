package com.netease.jsontest;

import com.netease.jsontest.model.Book;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
/**
 * desc:
 * Created by light on 2016/3/23.
 */
public class MiddleJsonBenchmarkTest2 {
	public static final String TAG = "SimpleJsonBenchmarkTest2";

	final static int COUNT = 1000;

	final String[] userPaths = {"book0", "book1", "book2", "book3", "book4", "book5", "book6", "book7", "book8", "book9", "book0"};
	final String[] userJsons = new String[userPaths.length];

	Book bookList;
	JsonTestController<Book> controller = new JsonTestController<Book>(Book.class);

	public MiddleJsonBenchmarkTest2() throws Exception {
		for (int i = 0; i < userPaths.length; i++) {
			try {
				userJsons[i] = IOUtils.toString(
						this.getClass().getResourceAsStream("book/" + userPaths[i] + ".json"),
						"utf-8");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		bookList = controller.gsonDSeri(userPaths[1], userJsons[1]);
	}


	@Test
	public void testFastJsonSeri() throws Exception {
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
		System.out.print("fastJson 序列化 耗时：" + JsonTestController.time(ns) + "ns\n");
	}

	@Test
	public void testGsonSeri() throws Exception {
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
		System.out.print("gson 序列化 耗时：" + JsonTestController.time(ns) + "ns\n");
	}

	@Test
	public void testJacksonSeri() throws Exception {
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
		System.out.print("jackSon 序列化 耗时：" + JsonTestController.time(ns) + "ns\n");
	}

	@Test
	public void testFastJsonDes() throws Exception {
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
		System.out.print("fastJson 反序列化 耗时：" + JsonTestController.time(ns) + "ns\n");
	}

	@Test
	public void testGsonDes() throws Exception {
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
		System.out.print("gson 反序列化 耗时：" + JsonTestController.time(ns) + "ns\n");
	}

	@Test
	public void testJacksonDes() throws Exception {
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
		System.out.print("jackSon 反序列化 耗时：" + JsonTestController.time(ns) + "ns\n");
	}
}
