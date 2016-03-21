package com.netease.jsontest;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.netease.jsontest.model.BookList;

import java.io.IOException;

/**
 * Created by light on 16-3-21.
 */
public class JsonTestController {
    final String TAG = "JsonTestController";

    final ObjectMapper objectMapper = new ObjectMapper();
    final Gson gson = new Gson();

    public JsonTestController() {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public void test(String json, String tag) {
        BookList bookList;

        bookList = fastJsonSeri(tag, json);
        bookList = gsonSeri(tag, json);
        bookList = jacksonSeri(tag, json);

        fastJsonDes(tag, bookList);
        gsonDes(tag, bookList);
        jacksonDes(tag, bookList);
    }

    BookList fastJsonSeri(String path, String json) {
        long start = System.currentTimeMillis();
        BookList list = JSON.parseObject(json, BookList.class);
        long end = System.currentTimeMillis();
        Log.i(TAG, "fast json path = " + path + " Seri time:" + (end - start));
        System.out.println(TAG +  " fastJson path:" + path + " Seri time:" + (end - start));
        return list;
    }

    BookList gsonSeri(String path, String json) {
        long start = System.currentTimeMillis();
        BookList list = gson.fromJson(json, BookList.class);
        long end = System.currentTimeMillis();
        Log.i(TAG, "gson  path = " + path + " Seri time:" + (end - start));
        System.out.println(TAG +  " gson path:" + path + " Seri time:" + (end - start));
        return list;
    }

    BookList jacksonSeri(String path, String json) {
        long start = System.currentTimeMillis();
        BookList list = null;
        try {
            list = objectMapper.readValue(json, BookList.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        Log.i(TAG, "jacksonSeri path = " + path + " time:" + (end - start));
        System.out.println(TAG +  " jackson path:" + path + " Seri time:" + (end - start));
        return list;
    }

    String fastJsonDes(String path, BookList bookList) {
        long start = System.currentTimeMillis();
        String json = JSON.toJSONString(bookList);
        long end = System.currentTimeMillis();
        Log.i(TAG, "fast json  path = " + path + " dSeri time:" + (end - start));
        Log.d(TAG, "fastJsonDes json = " + json);
        System.out.println(TAG + " fastjson path = " + path + " dSeri time:" + (end - start));
        return json;
    }

    String gsonDes(String path, BookList bookList) {
        long start = System.currentTimeMillis();
        String json = gson.toJson(bookList);
        long end = System.currentTimeMillis();
        Log.i(TAG, "gson  path = " + path + " dSeri time:" + (end - start));
        Log.d(TAG, "gsonDes json = " + json);
        System.out.println(TAG + " gson path = " + path + " dSeri time:" + (end - start));
        return json;
    }

    String jacksonDes(String path, BookList bookList) {
        long start = System.currentTimeMillis();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(bookList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        Log.i(TAG, "jackson  path = " + path + " dSeri time:" + (end - start));
        Log.d(TAG, "jacksonDes json = " + json);
        System.out.println(TAG + " jackson path = " + path + " dSeri time:" + (end - start));
        return json;
    }
}
