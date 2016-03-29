package com.netease.jsontest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.netease.jsontest.model.BookList;
import com.netease.jsontest.model.BookModel;

public class MainUIActivity extends AppCompatActivity {
    public static final String TAG = "MainUIActivity";

    final String[] paths = {"android", "android100", "c100", "ios", "java", "js", "mac", "python"};
    final JsonTestController<BookList> testController = new JsonTestController(BookList.class);

	TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		textView = (TextView) findViewById(R.id.text_view);
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
					String json = BookModel.get().getJson(v.getContext(), paths[position]);
					BookList bookList = testController.gsonDSeri(paths[position], json);
					String text = testController.jacksonSeri(paths[position], bookList);
					textView.setText(text);
                }
            });
            return button;
        }
    }
}
