package com.netease.jsontest;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by light on 16-3-21.
 */
public class JsonTestControllerTest {
    final String[] paths = {"android", "android100", "c100", "ios", "java", "js", "mac", "python"};
    final String[] jsons = new String[paths.length];

    JsonTestController controller = new JsonTestController();

    public JsonTestControllerTest() {
        for (int i = 0; i < paths.length; i++) {
            try {
                jsons[i] = IOUtils.toString(
                        this.getClass().getResourceAsStream(paths[i] + ".json"),
                        "utf-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testTest1() throws Exception {
        for (int i = 0; i < paths.length; i++) {
            String path = paths[i];
            String json = jsons[i];
            controller.test(json, path + ".json");
        }
    }

    @Test
    public void testFastJsonSeri() throws Exception {

    }

    @Test
    public void testGsonSeri() throws Exception {

    }

    @Test
    public void testJacksonSeri() throws Exception {

    }

    @Test
    public void testFastJsonDes() throws Exception {

    }

    @Test
    public void testGsonDes() throws Exception {

    }

    @Test
    public void testJacksonDes() throws Exception {

    }
}