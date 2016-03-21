package com.netease.jsontest.model;

import java.util.HashMap;

/**
 * Created by light on 16-3-20.
 */
public class Book {
    public String id;
    public String isbn10;
    public String isbn13;
    public String title;
    public String origin_title;
    public String alt_title;
    public String subtitle;
    public String url;
    public String alt;
    public String publisher;
    public String pubdate;
    public String binding;
    public String price;
    public String pages;
    public String author_intro;
    public String summary;
    public String catalog;
    public String ebook_url;
    public String ebook_price;

    public Tag[] tags;
    public static class Tag {
        public int count;
        public String name;
        public String title;
    }
    public HashMap<String, String> images;
}
