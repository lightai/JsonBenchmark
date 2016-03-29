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
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by light on 2016/3/23.
 */
public class Application {
  public static final String TAG = Application.class.getSimpleName();

  static final int MAX_COUNT = 50000;
  static final boolean ANDROID = true;

  public static void main(String args[]) {
        new UserTest().testGsonDeserialize();
//    JSON.DUMP_CLASS = "asm";
//        new UserTest().testFastjsonDeserialize();
//        new UserTest().testJacksonDeserialize();
//    new ReflectionTest().testNormal();
//    new ReflectionTest().testReflection();
  }

  final List<TestBean<Book>> bookTestBeanList = JsonTestModel.instance().getBookTestBeanList();
  final List<TestBean<BookList>> bookListTestBeanList = JsonTestModel.instance().getBookListTestBeanList();

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

    void result2file(List<TestResultBean> list, String filename) {
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

    void result2fileAndroid(List<TestResultBean> list, String filename) {
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

  public static class ReflectionTest {
    List<String> userFieldList = new ArrayList<>(MAX_COUNT);
    List<String> userNormalList = new ArrayList<>(MAX_COUNT);

    public void testReflection() {
      long t0 = System.currentTimeMillis();
      Class<User> userClass = User.class;
      for (int i = 0; i < MAX_COUNT; i++) {
        try {
          User user = userClass.newInstance();
          Field field = userClass.getDeclaredField("title");
          field.set(user, "title = " + System.currentTimeMillis());
          String title = (String) field.get(user);
          userFieldList.add(title);
        } catch (InstantiationException e) {
          e.printStackTrace();
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        } catch (NoSuchFieldException e) {
          e.printStackTrace();
        }
      }
      long t1 = System.currentTimeMillis();
      System.out.print("reflect time = " + (t1 - t0) + "\n");
    }

    public void testNormal() {
      long t0 = System.currentTimeMillis();
      for (int i = 0; i < MAX_COUNT; i++) {
        User user = new User();
        user.title = "title = " + System.currentTimeMillis();
        userNormalList.add(user.title);
      }
      long t1 = System.currentTimeMillis();
      System.out.print("normal time = " + (t1 - t0) + "\n");
    }
  }
}
