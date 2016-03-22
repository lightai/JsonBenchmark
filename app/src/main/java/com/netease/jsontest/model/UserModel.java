package com.netease.jsontest.model;

import android.content.Context;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * desc:
 * Created by light on 2016/3/23.
 */
public class UserModel {
	static UserModel bookModel = new UserModel();
	Map<String, String> jsonMap = new ConcurrentHashMap<>();

	public String getUserJson(Context context, String path) {
		try {
			String json = jsonMap.get(path);
			if (json == null) {
				json = IOUtils.toString(context.getAssets().open("user/" + path + ".json"), "utf-8");
				jsonMap.put(path, json);
			}
			return json;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static UserModel get() {
		return bookModel;
	}
}
