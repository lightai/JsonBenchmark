package com.netease.jsontest;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.netease.jsontest.model.User;

import java.io.IOException;
/**
 * desc:
 * Created by light on 2016/3/23.
 */
public class UserJsonTestController {
	final String TAG = "UserJsonTestController";

	final ObjectMapper objectMapper = new ObjectMapper();
	final Gson gson = new Gson();

	public UserJsonTestController() {
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public User fastJsonDSeri(String path, String json) {
		User list = JSON.parseObject(json, User.class);
		return list;
	}

	public User gsonDSeri(String path, String json) {
		User list = gson.fromJson(json, User.class);
		return list;
	}

	public User jacksonDSeri(String path, String json) {
		User list = null;
		try {
			list = objectMapper.readValue(json, User.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	public String fastJsonSeri(String path, User User) {
		String json = JSON.toJSONString(User);
		return json;
	}

	public String gsonSeri(String path, User User) {
		String json = gson.toJson(User);
		return json;
	}

	public String jacksonSeri(String path, User User) {
		String json = null;
		try {
			json = objectMapper.writeValueAsString(User);
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
