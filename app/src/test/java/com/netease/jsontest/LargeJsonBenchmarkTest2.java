package com.netease.jsontest;

import com.netease.jsontest.model.BookList;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by light on 16-3-21.
 */
public class LargeJsonBenchmarkTest2 {
	static final int COUNT = 10000;
	
    final String[] paths = {"android"};//, "android100", "c100", "ios", "java", "js", "mac", "python"};
    final String[] jsons = new String[paths.length];
    BookList bookList;

    JsonTestController<BookList> controller = new JsonTestController(BookList.class);

    public LargeJsonBenchmarkTest2() {
        for (int i = 0; i < paths.length; i++) {
            try {
                jsons[i] = IOUtils.toString(
                        this.getClass().getResourceAsStream(paths[i] + ".json"),
                        "utf-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        bookList = controller.gsonDSeri(paths[0], jsons[0]);
    }

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testTest1() throws Exception {
//        for (int i = 0; i < paths.length; i++) {
//            String path = paths[i];
//            String json = jsons[i];
//            controller.test(json, path + ".json");
//        }
    }

    @Test
    public void testFastJsonSeri() throws Exception {
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
        System.out.println("fastJson 序列化 耗时：" + JsonTestController.time(ns) + "ns");
    }

    @Test
    public void testGsonSeri() throws Exception {
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
        System.out.println("gson 序列化 耗时：" + JsonTestController.time(ns) + "ns");
    }

    @Test
    public void testJacksonSeri() throws Exception {
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
        System.out.println("jackSon 序列化 耗时：" + JsonTestController.time(ns) + "ns");
    }

    @Test
    public void testFastJsonDes() throws Exception {
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
        System.out.println("fastJson 反序列化 耗时：" + JsonTestController.time(ns) + "ns");
    }

    @Test
    public void testGsonDes() throws Exception {
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
        System.out.println("gson 反序列化 耗时：" + JsonTestController.time(ns) + "ns");
    }

    @Test
    public void testJacksonDes() throws Exception {
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
        System.out.println("jackSon 反序列化 耗时：" + JsonTestController.time(ns) + "ns");
    }
}