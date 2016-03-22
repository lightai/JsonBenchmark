package com.netease.jsontest;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
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

        bookList = fastJsonDSeri(tag, json);
        bookList = gsonDSeri(tag, json);
        bookList = jacksonDSeri(tag, json);

        fastJsonSeri(tag, bookList);
        gsonSeri(tag, bookList);
        jacksonSeri(tag, bookList);
    }

    public BookList fastJsonDSeri(String path, String json) {
        long start = System.nanoTime();
        BookList list = JSON.parseObject(json, BookList.class);
        long end = System.nanoTime();
        Log.i(TAG, "fast json path = " + path + " Seri time:" + (end - start));
//        System.out.println(TAG +  " fastJson 反序列化" + path + " 耗时:" + time(end - start) + "ns");
        return list;
    }

    public BookList gsonDSeri(String path, String json) {
        long start = System.nanoTime();
        BookList list = gson.fromJson(json, BookList.class);
        long end = System.nanoTime();
        Log.i(TAG, "gson  path = " + path + " Seri time:" + (end - start));
//        System.out.println(TAG +  " gson 反序列化" + path + " 耗时:" + time(end - start) + "ns");
        return list;
    }

    public BookList jacksonDSeri(String path, String json) {
        long start = System.nanoTime();
        BookList list = null;
        try {
            list = objectMapper.readValue(json, BookList.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.nanoTime();
        Log.i(TAG, "jacksonSeri path = " + path + " time:" + (end - start));
//        System.out.println(TAG +  " jackson 反序列化" + path + " 耗时:" + time(end - start) + "ns");
        return list;
    }

    public String fastJsonSeri(String path, BookList bookList) {
        long start = System.nanoTime();
        String json = JSON.toJSONString(bookList);
        long end = System.nanoTime();
        Log.i(TAG, "fast json  path = " + path + " dSeri time:" + (end - start));
        Log.d(TAG, "fastJsonSeri json = " + json);
//        System.out.println(TAG + " fastjson 序列化" + path + " 耗时:" + time(end - start) + "ns");
        return json;
    }

    public String gsonSeri(String path, BookList bookList) {
        long start = System.nanoTime();
        String json = gson.toJson(bookList);
        long end = System.nanoTime();
        Log.i(TAG, "gson  path = " + path + " dSeri time:" + (end - start));
        Log.d(TAG, "gsonSeri json = " + json);
//        System.out.println(TAG + " gson 序列化" + path + " 耗时:" + time(end - start) + "ns");
        return json;
    }

    public String jacksonSeri(String path, BookList bookList) {
        long start = System.nanoTime();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(bookList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        long end = System.nanoTime();
        Log.i(TAG, "jackson  path = " + path + " dSeri time:" + (end - start));
        Log.d(TAG, "jacksonSeri json = " + json);
//        System.out.println(TAG + " jackson 序列化" + path + " 耗时:" + time(end - start) + "ns");
        return json;
    }

    public static String time(long ns) {
        long ms = ns / 1000 / 1000;
        long us = ns / 1000 % 1000;
        long remain = ns % 1000;
        return String.format("%d,%03d,%03d", ms, us, remain);
    }
}
