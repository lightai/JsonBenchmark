package com.netease.jsonbenchmark;

import com.google.gson.Gson;
import com.netease.jsonbenchmark.model.Book;
import com.netease.jsonbenchmark.model.BookList;
import com.netease.jsonbenchmark.model.JsonModel;
import com.netease.jsonbenchmark.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试数据
 * Created by light on 2016/3/23.
 */
public class JsonTestModel {
    final String[] userPaths = {"android", "c", "js", "lisp", "mac", "music", "php", "python", "lisp"};

    final String[] bookPaths = {"book0", "book1", "book2", "book3", "book4", "book5", "book6", "book7", "book8", "book9", "book0"};

    final String[] bookListPaths = {"android", "android100", "c100", "ios", "java", "js", "mac", "python"};
    
    final Gson gson = new Gson();

    private List<TestBean<User>> userTestBeanList;
    private List<TestBean<Book>> bookTestBeanList;
    private List<TestBean<BookList>> bookListTestBeanList;
    
    public List<TestBean<User>> getUserTestBeanList() {
        if (userTestBeanList == null) {
            userTestBeanList = new ArrayList<>();
            
            for (int i = 0; i < userPaths.length; i++) {
                TestBean<User> item = new TestBean<>();
                item.path = userPaths[i];
                item.json = JsonModel.get().getUserJson("/com/netease/jsontest/user/" + userPaths[i] + ".json");
                item.t = gson.fromJson(item.json, User.class);
                userTestBeanList.add(item);
            }
        }
        
        return userTestBeanList;
    }

    public List<TestBean<Book>> getBookTestBeanList() {
        if (userTestBeanList == null) {
            userTestBeanList = new ArrayList<>();

            for (int i = 0; i < userPaths.length; i++) {
                TestBean<Book> item = new TestBean<>();
                item.path = userPaths[i];
                item.json = JsonModel.get().getUserJson(userPaths[i]);
                item.t = gson.fromJson(item.json, Book.class);
                bookTestBeanList.add(item);
            }
        }

        return bookTestBeanList;
    }

    public List<TestBean<BookList>> getBookListTestBeanList() {
        if (userTestBeanList == null) {
            userTestBeanList = new ArrayList<>();

            for (int i = 0; i < userPaths.length; i++) {
                TestBean<BookList> item = new TestBean<>();
                item.path = userPaths[i];
                item.json = JsonModel.get().getUserJson(userPaths[i]);
                item.t = gson.fromJson(item.json, BookList.class);
                bookListTestBeanList.add(item);
            }
        }

        return bookListTestBeanList;
    }
    
    static final JsonTestModel sInstance = new JsonTestModel();
    public static JsonTestModel instance() {
        return sInstance;
    }
}
