package com.netease.jsontest.model;

import android.content.Context;
import android.text.TextUtils;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by light on 16-3-20.
 */
public class BookModel {
    static BookModel bookModel = new BookModel();
    String json;
    String python;

	Map<String, String> jsonMap = new ConcurrentHashMap<>();

    public String getBookList(Context context) {
        if (TextUtils.isEmpty(json)) {
            try {
                json = IOUtils.toString(context.getAssets().open("com.netease.jsontest.jsontest/android.json"), "utf-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return json;
    }

    public String getPython(Context context) {
        if (TextUtils.isEmpty(json)) {
            try {
                python = IOUtils.toString(context.getAssets().open("com.netease.jsontest.jsontest/python.json"), "utf-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return python;
    }

    public String getJson(Context context, String path) {
        try {
			String json = jsonMap.get(path);
			if (json == null) {
				json = IOUtils.toString(context.getAssets().open(path + ".json"), "utf-8");
				jsonMap.put(path, json);
			}
			return json;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

	public String getBookJson(Context context, String book) {
		try {
			String json = jsonMap.get(book);
			if (json == null) {
				json = IOUtils.toString(context.getAssets().open("book/" + book + ".json"), "utf-8");
				jsonMap.put(book, json);
			}
			return json;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

    public static BookModel get() {
        return bookModel;
    }
}
