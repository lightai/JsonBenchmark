package com.netease.jsontest;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.netease.jsontest.model.Book;

import java.io.IOException;
/**
 * desc:
 * Created by light on 2016/3/23.
 */
public class BookJsonTestController {
	final String TAG = "BookJsonTestController";

	final ObjectMapper objectMapper = new ObjectMapper();
	final Gson gson = new Gson();

	public BookJsonTestController() {
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public Book fastJsonDSeri(String path, String json) {
		Book list = JSON.parseObject(json, Book.class);
		return list;
	}

	public Book gsonDSeri(String path, String json) {
		Book list = gson.fromJson(json, Book.class);
		return list;
	}

	public Book jacksonDSeri(String path, String json) {
		Book list = null;
		try {
			list = objectMapper.readValue(json, Book.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	public String fastJsonSeri(String path, Book Book) {
		String json = JSON.toJSONString(Book);
		return json;
	}

	public String gsonSeri(String path, Book Book) {
		String json = gson.toJson(Book);
		return json;
	}

	public String jacksonSeri(String path, Book Book) {
		String json = null;
		try {
			json = objectMapper.writeValueAsString(Book);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

	public static String time(long ns) {
		long ms = ns / 1000 / 1000;
		long us = ns / 1000 % 1000;
		long remain = ns % 1000;
		return String.format("%d,%03d,%03d", ms, us, remain);
	}
}