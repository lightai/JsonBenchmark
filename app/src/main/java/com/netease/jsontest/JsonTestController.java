package com.netease.jsontest;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
/**
 * desc:
 * Created by light on 2016/3/23.
 */
public class JsonTestController<T> {
	final String TAG = "TJsonTestController";

	final ObjectMapper objectMapper = new ObjectMapper();
	final Gson gson = new Gson();

	Class<T> entityClass;

	public JsonTestController(Class cls) {
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		entityClass = cls;
	}

	public T fastJsonDSeri(String path, String json) {
		T list = JSON.parseObject(json, entityClass);
		return list;
	}

	public T gsonDSeri(String path, String json) {
		T list = gson.fromJson(json, entityClass);
		return list;
	}

	public T jacksonDSeri(String path, String json) {
		T list = null;
		try {
			list = objectMapper.readValue(json, entityClass);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	public String fastJsonSeri(String path, T T) {
		String json = JSON.toJSONString(T);
		return json;
	}

	public String gsonSeri(String path, T T) {
		String json = gson.toJson(T);
		return json;
	}

	public String jacksonSeri(String path, T T) {
		String json = null;
		try {
			json = objectMapper.writeValueAsString(T);
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
