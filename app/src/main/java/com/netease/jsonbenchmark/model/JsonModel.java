package com.netease.jsonbenchmark.model;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by light on 16-3-20.
 */
public class JsonModel {
    static JsonModel bookModel = new JsonModel();

    Map<String, String> jsonMap = new ConcurrentHashMap<>();

    public static JsonModel get() {
        return bookModel;
    }

    public String getJson(String path) {
        try {
            String json = jsonMap.get(path);
            if (json == null) {
                json = IOUtils.toString(FileUtils.openInputStream(new File(path + ".json")), "utf-8");
                jsonMap.put(path, json);
            }
            return json;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getBookJson(String book) {
        try {
            String json = jsonMap.get(book);
            if (json == null) {
                json = IOUtils.toString(getClass().getResourceAsStream("book/" + book + ".json"), "utf-8");
                jsonMap.put(book, json);
            }
            return json;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getUserJson(String path) {
        try {
            String json = jsonMap.get(path);
            if (json == null) {
                json = IOUtils.toString(getClass().getResourceAsStream(path), "utf-8");
                jsonMap.put(path, json);
            }
            return json;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
