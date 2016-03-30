package com.netease.jsonbenchmark;

import android.content.Context;

import com.google.gson.Gson;
import com.netease.jsonbenchmark.model.Book;
import com.netease.jsonbenchmark.model.BookList;
import com.netease.jsonbenchmark.model.User;
import com.netease.jsontest.AppContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by light on 2016/3/23.
 */
public class Application {
  public static final String TAG = Application.class.getSimpleName();

  static final int MAX_COUNT = 5000;
  static final boolean ANDROID = true;
  static final int sample = 10;

  public static void main(String args[]) {
//        new UserTest().testGsonDeserialize();
//    JSON.DUMP_CLASS = "asm";
//        new UserTest().testFastjsonDeserialize();
//        new UserTest().testJacksonDeserialize();
//    new ReflectionTest().testNormal();
//    new ReflectionTest().testReflection();
    new BookTest().testGsonDeserialize();
  }

  public static class UserTest {
    final List<TestBean<User>> userTestBeanList = JsonTestModel.instance().getUserTestBeanList();
    final JsonTestController controller = new JsonTestController(User.class);
    List<User> pojoList = new ArrayList<>(MAX_COUNT);

    private void testGsonSerialize() {
      List<TestResultBean> list = new ArrayList<>();

      int lastLogCount = 0;
      for (int count = 1; count <= MAX_COUNT; count++) {

        long t0 = System.nanoTime();
        for (TestBean<User> testBean : userTestBeanList) {
          controller.gsonSerialize(testBean.t);
        }
        long t1 = System.nanoTime();

        if (count - lastLogCount >= 100 || count == 1) {
          if (count != 1)
            lastLogCount = count;
          TestResultBean resultBean = new TestResultBean();
          resultBean.parser = TestResultBean.GSON;
          resultBean.serialize = true;
          resultBean.runCount = count;
          resultBean.runTime = t1 - t0;
          list.add(resultBean);
        }
      }
      result2file(list, "result/gson_serialize.json");
    }

    public void testGsonDeserialize() {
      List<TestResultBean> list = new ArrayList<>();

      int lastLogCount = 0;
      for (int count = 1; count <= MAX_COUNT; count++) {

        long t0 = System.nanoTime();
        for (TestBean<User> testBean : userTestBeanList) {
          pojoList.add((User) controller.gsonDeserialize(testBean.json));
        }
        long t1 = System.nanoTime();

        if (count - lastLogCount >= 10 || count == 1) {
          if (count != 1)
            lastLogCount = count;
          TestResultBean resultBean = new TestResultBean();
          resultBean.parser = TestResultBean.GSON;
          resultBean.serialize = false;
          resultBean.runCount = count;
          resultBean.runTime = t1 - t0;
          list.add(resultBean);
        }
      }

      result2file(list, "result/gson_deserialize.json");
    }

	  public void testFastjsonDeserialize() {
      List<TestResultBean> list = new ArrayList<>();

      int lastLogCount = 0;
      for (int count = 1; count <= MAX_COUNT; count++) {

        long t0 = System.nanoTime();
        for (TestBean<User> testBean : userTestBeanList) {
          pojoList.add((User) controller.fastJsonDeserialize(testBean.json));
        }
        long t1 = System.nanoTime();

        if (count - lastLogCount >= 10 || count == 1) {
          if (count != 1)
            lastLogCount = count;
          TestResultBean resultBean = new TestResultBean();
          resultBean.parser = TestResultBean.GSON;
          resultBean.serialize = false;
          resultBean.runCount = count;
          resultBean.runTime = t1 - t0;
          list.add(resultBean);
        }
      }

      result2file(list, "result/fastjson_deserialize.json");
    }

	  public void testJacksonDeserialize() {
      List<TestResultBean> list = new ArrayList<>();

      int lastLogCount = 0;
      for (int count = 1; count <= MAX_COUNT; count++) {

        long t0 = System.nanoTime();
        for (TestBean<User> testBean : userTestBeanList) {
          pojoList.add((User) controller.jacksonDeserialize(testBean.json));
        }
        long t1 = System.nanoTime();

        if (count - lastLogCount >= 10 || count == 1) {
          if (count != 1)
            lastLogCount = count;
          TestResultBean resultBean = new TestResultBean();
          resultBean.parser = TestResultBean.GSON;
          resultBean.serialize = false;
          resultBean.runCount = count;
          resultBean.runTime = t1 - t0;
          list.add(resultBean);
        }
      }

      result2file(list, "result/jackson_deserialize.json");
    }
  }

  public static class BookTest {
    final List<TestBean<Book>> bookTestBeanList = JsonTestModel.instance().getBookTestBeanList();
    final JsonTestController controller = new JsonTestController(Book.class);
    List<Book> pojoList = new ArrayList<>(MAX_COUNT);
    List<WeakReference<String>> jsonList = new ArrayList<>();

    public void testGsonSerialize() {
      List<TestResultBean> list = new ArrayList<>();

      int lastLogCount = 0;
      for (int count = 1; count <= MAX_COUNT; count++) {

        long t0 = System.nanoTime();
        for (TestBean<Book> testBean : bookTestBeanList) {
          String json = controller.gsonSerialize(testBean.t);
          jsonList.add(new WeakReference<String>(json));
        }
        long t1 = System.nanoTime();

        if (count - lastLogCount >= sample || count == 1) {
          if (count != 1)
            lastLogCount = count;
          TestResultBean resultBean = new TestResultBean();
          resultBean.parser = TestResultBean.GSON;
          resultBean.serialize = true;
          resultBean.runCount = count;
          resultBean.runTime = t1 - t0;
          list.add(resultBean);
        }
      }
      result2file(list, "result/gson_serialize.json");
    }

    public void testFastjsonSerialize() {
      List<TestResultBean> list = new ArrayList<>();

      int lastLogCount = 0;
      for (int count = 1; count <= MAX_COUNT; count++) {

        long t0 = System.nanoTime();
        for (TestBean<Book> testBean : bookTestBeanList) {
          String json = controller.fastJsonSerialize(testBean.t);
          jsonList.add(new WeakReference<String>(json));
        }
        long t1 = System.nanoTime();

        if (count - lastLogCount >= sample || count == 1) {
          if (count != 1)
            lastLogCount = count;
          TestResultBean resultBean = new TestResultBean();
          resultBean.parser = TestResultBean.GSON;
          resultBean.serialize = true;
          resultBean.runCount = count;
          resultBean.runTime = t1 - t0;
          list.add(resultBean);
        }
      }
      result2file(list, "result/fastjson_serialize.json");
    }

    public void testJacksonSerialize() {
      List<TestResultBean> list = new ArrayList<>();

      int lastLogCount = 0;
      for (int count = 1; count <= MAX_COUNT; count++) {

        long t0 = System.nanoTime();
        for (TestBean<Book> testBean : bookTestBeanList) {
          String json = controller.jacksonSerialize(testBean.t);
          jsonList.add(new WeakReference<String>(json));
        }
        long t1 = System.nanoTime();

        if (count - lastLogCount >= sample || count == 1) {
          if (count != 1)
            lastLogCount = count;
          TestResultBean resultBean = new TestResultBean();
          resultBean.parser = TestResultBean.GSON;
          resultBean.serialize = true;
          resultBean.runCount = count;
          resultBean.runTime = t1 - t0;
          list.add(resultBean);
        }
      }
      result2file(list, "result/jackson_serialize.json");
    }

    public void testGsonDeserialize() {
      List<TestResultBean> list = new ArrayList<>();

      int lastLogCount = 0;
      for (int count = 1; count <= MAX_COUNT; count++) {

        long t0 = System.nanoTime();
        for (TestBean<Book> testBean : bookTestBeanList) {
          pojoList.add((Book) controller.gsonDeserialize(testBean.json));
        }
        long t1 = System.nanoTime();

        if (count - lastLogCount >= sample || count == 1) {
          if (count != 1)
            lastLogCount = count;
          TestResultBean resultBean = new TestResultBean();
          resultBean.parser = TestResultBean.GSON;
          resultBean.serialize = false;
          resultBean.runCount = count;
          resultBean.runTime = t1 - t0;
          list.add(resultBean);
        }
      }

      result2file(list, "result/gson_deserialize.json");
    }

    public void testFastjsonDeserialize() {
      List<TestResultBean> list = new ArrayList<>();

      int lastLogCount = 0;
      for (int count = 1; count <= MAX_COUNT; count++) {

        long t0 = System.nanoTime();
        for (TestBean<Book> testBean : bookTestBeanList) {
          pojoList.add((Book) controller.fastJsonDeserialize(testBean.json));
        }
        long t1 = System.nanoTime();

        if (count - lastLogCount >= sample || count == 1) {
          if (count != 1)
            lastLogCount = count;
          TestResultBean resultBean = new TestResultBean();
          resultBean.parser = TestResultBean.GSON;
          resultBean.serialize = false;
          resultBean.runCount = count;
          resultBean.runTime = t1 - t0;
          list.add(resultBean);
        }
      }

      result2file(list, "result/fastjson_deserialize.json");
    }

    public void testJacksonDeserialize() {
      List<TestResultBean> list = new ArrayList<>();

      int lastLogCount = 0;
      for (int count = 1; count <= MAX_COUNT; count++) {

        long t0 = System.nanoTime();
        for (TestBean<Book> testBean : bookTestBeanList) {
          pojoList.add((Book) controller.jacksonDeserialize(testBean.json));
        }
        long t1 = System.nanoTime();

        if (count - lastLogCount >= sample || count == 1) {
          if (count != 1)
            lastLogCount = count;
          TestResultBean resultBean = new TestResultBean();
          resultBean.parser = TestResultBean.GSON;
          resultBean.serialize = false;
          resultBean.runCount = count;
          resultBean.runTime = t1 - t0;
          list.add(resultBean);
        }
      }

      result2file(list, "result/jackson_deserialize.json");
    }
  }

  public static class ReflectionTest {
    List<Book> userFieldList = new ArrayList<>(MAX_COUNT);
    List<Book> userNormalList = new ArrayList<>(MAX_COUNT);
    String strArray[] = {String.valueOf(System.currentTimeMillis()), String.valueOf(System.currentTimeMillis()),
      String.valueOf(System.currentTimeMillis()), String.valueOf(System.currentTimeMillis()),
      String.valueOf(System.currentTimeMillis()), String.valueOf(System.currentTimeMillis()),
      String.valueOf(System.currentTimeMillis()), String.valueOf(System.currentTimeMillis()),
      String.valueOf(System.currentTimeMillis()), String.valueOf(System.currentTimeMillis()),
      String.valueOf(System.currentTimeMillis()), String.valueOf(System.currentTimeMillis())
    };

    public void testReflection() {
      Class<Book> bookClass = Book.class;
      Field titleField = null;
      Field idField = null;
      Field isbn10 = null, isbn13 = null, origin_title = null, alt_title = null,
        subtitle = null, url = null, alt = null, publisher = null, pubdate = null, binding = null,
        price = null, author_intro = null, summary = null, ebook_url = null, catalog = null;
      try {
        titleField = bookClass.getDeclaredField("title");
        idField = bookClass.getField("id");
         isbn10 = bookClass.getField("isbn10");
         isbn13 = bookClass.getField("isbn13");
        origin_title = bookClass.getField("origin_title");
        alt_title = bookClass.getField("alt_title");
        subtitle = bookClass.getField("subtitle");
         url = bookClass.getField("url");
         alt = bookClass.getField("alt");
         publisher = bookClass.getField("publisher");
         pubdate = bookClass.getField("pubdate");
         binding = bookClass.getField("binding");
         price = bookClass.getField("price");
         author_intro = bookClass.getField("author_intro");
         summary = bookClass.getField("summary");
         ebook_url = bookClass.getField("ebook_url");
         catalog = bookClass.getField("catalog");
      } catch (NoSuchFieldException e) {
        e.printStackTrace();
      }

      List<TestResultBean> list = new ArrayList<>();

      for (int i = 0; i < MAX_COUNT; i++) {
        String string = String.valueOf(System.currentTimeMillis());
        long t0 = 0;
        long t1 = 0;
        try {
          Book book = bookClass.newInstance();

          t0 = System.nanoTime();

          titleField.set(book, strArray[0]);
          idField.set(book, strArray[1]);
          isbn10.set(book, strArray[3]);
          isbn13.set(book, strArray[5]);
          origin_title.set(book, strArray[7]);
          alt_title.set(book, strArray[2]);
          subtitle.set(book, strArray[4]);
          url.set(book, strArray[6]);
          alt.set(book, strArray[8]);
          publisher.set(book, strArray[9]);
          pubdate.set(book, strArray[1]);
          binding.set(book, strArray[4]);
          price.set(book, strArray[5]);
          author_intro.set(book, strArray[8]);
          summary.set(book, strArray[9]);
          ebook_url.set(book, strArray[0]);
          catalog.set(book, strArray[1]);

          t1 = System.nanoTime();

          TestResultBean resultBean = new TestResultBean();
          resultBean.parser = "";
          resultBean.serialize = false;
          resultBean.runCount = i;
          resultBean.runTime = t1 - t0;
          list.add(resultBean);

          userFieldList.add(book);
        } catch (InstantiationException e) {
          e.printStackTrace();
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      }

      result2file(list, "result/reflect_invoke_test.json");
    }

    public void testReflectionWithoutCache() {
      List<TestResultBean> list = new ArrayList<>();
      Class<Book> bookClass = Book.class;

      for (int i = 0; i < MAX_COUNT; i++) {
        long t0 = 0;
        long t1 = 0;
        try {
          Book book = bookClass.newInstance();

          t0 = System.nanoTime();

          Field titleField = bookClass.getDeclaredField("title");
          Field idField = bookClass.getField("id");
          Field isbn10 = bookClass.getField("isbn10");
          Field isbn13 = bookClass.getField("isbn13");
          Field origin_title = bookClass.getField("origin_title");
          Field alt_title = bookClass.getField("alt_title");
          Field subtitle = bookClass.getField("subtitle");
          Field url = bookClass.getField("url");
          Field alt = bookClass.getField("alt");
          Field publisher = bookClass.getField("publisher");
          Field pubdate = bookClass.getField("pubdate");
          Field binding = bookClass.getField("binding");
          Field price = bookClass.getField("price");
          Field author_intro = bookClass.getField("author_intro");
          Field summary = bookClass.getField("summary");
          Field ebook_url = bookClass.getField("ebook_url");
          Field catalog = bookClass.getField("catalog");
          titleField.set(book, strArray[0]);
          idField.set(book, strArray[1]);
          isbn10.set(book, strArray[3]);
          isbn13.set(book, strArray[5]);
          origin_title.set(book, strArray[7]);
          alt_title.set(book, strArray[2]);
          subtitle.set(book, strArray[4]);
          url.set(book, strArray[6]);
          alt.set(book, strArray[8]);
          publisher.set(book, strArray[9]);
          pubdate.set(book, strArray[1]);
          binding.set(book, strArray[4]);
          price.set(book, strArray[5]);
          author_intro.set(book, strArray[8]);
          summary.set(book, strArray[9]);
          ebook_url.set(book, strArray[0]);
          catalog.set(book, strArray[1]);
          t1 = System.nanoTime();

          TestResultBean resultBean = new TestResultBean();
          resultBean.parser = "";
          resultBean.serialize = false;
          resultBean.runCount = i;
          resultBean.runTime = t1 - t0;
          list.add(resultBean);

          userFieldList.add(book);
        } catch (InstantiationException e) {
          e.printStackTrace();
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        } catch (NoSuchFieldException e) {
          e.printStackTrace();
        }
      }

    result2file(list, "result/reflect_without_cache_invoke_test.json");
  }

    public void testNormal() {
      List<TestResultBean> list = new ArrayList<>();
      long t0, t1;

      for (int i = 0; i < MAX_COUNT; i++) {
        Book book = new Book();

        t0  = System.nanoTime();
        book.title = strArray[0];
        book.id = strArray[1];
        book.isbn10 = strArray[3];
        book.isbn13 = strArray[5];
        book.origin_title = strArray[7];
        book.alt_title = strArray[2];
        book.subtitle = strArray[4];
        book.url = strArray[6];
        book.alt = strArray[8];
        book.publisher = strArray[1];
        book.pubdate = strArray[3];
        book.binding = strArray[5];
        book.price = strArray[9];
        book.author_intro = strArray[10];
        book.summary = strArray[2];
        book.ebook_url = strArray[4];
        book.catalog = strArray[8];

        t1 = System.nanoTime();
        TestResultBean resultBean = new TestResultBean();
        resultBean.parser = "";
        resultBean.serialize = false;
        resultBean.runCount = i;
        resultBean.runTime = t1 - t0;
        list.add(resultBean);

        userNormalList.add(book);
      }
      result2file(list, "result/direct_invoke_test.json");
    }
  }

  public static void result2file(List<TestResultBean> list, String filename) {
    if (ANDROID) {
      result2fileAndroid(list, filename);
      return;
    }

    String result = new Gson().toJson(list);

    FileOutputStream outputStream = null;
    try {
      outputStream = FileUtils.openOutputStream(new File(filename));
      IOUtils.write(result, outputStream);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      IOUtils.closeQuietly(outputStream);
    }
  }

  static void result2fileAndroid(List<TestResultBean> list, String filename) {
    String result = new Gson().toJson(list);

    FileOutputStream outputStream = null;
    try {
      Context context = AppContext.getContext();
      outputStream = FileUtils.openOutputStream(new File(context.getExternalCacheDir().getPath() + "/" + filename));
      IOUtils.write(result, outputStream);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      IOUtils.closeQuietly(outputStream);
    }
  }
}
