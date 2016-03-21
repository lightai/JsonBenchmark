package com.netease.jsontest.model;

import android.content.Context;
import android.text.TextUtils;

import org.apache.commons.io.IOUtils;

import java.io.IOException;

/**
 * Created by light on 16-3-20.
 */
public class BookModel {
    static BookModel bookModel = new BookModel();
    String json;
    String python;

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
            return IOUtils.toString(context.getAssets().open(path + ".json"), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BookModel get() {
        return bookModel;
    }
}
