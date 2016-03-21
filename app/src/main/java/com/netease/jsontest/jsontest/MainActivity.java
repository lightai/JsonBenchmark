package com.netease.jsontest.jsontest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.netease.jsontest.jsontest.model.Book;
import com.netease.jsontest.jsontest.model.BookList;
import com.netease.jsontest.jsontest.model.BookModel;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    final String[] paths = {"android", "android100", "c100", "ios", "java", "js", "mac", "python"};
    final JsonTestController testController = new JsonTestController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((ListView) findViewById(R.id.list_view)).setAdapter(new ThisAdapter());
    }


    class ThisAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return paths.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            Button button = new Button(parent.getContext());
            button.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            button.setText(paths[position]);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    testController.test(
                            BookModel.get().getJson(v.getContext(), paths[position]),
                            paths[position]);
                }
            });
            return button;
        }
    }
}
