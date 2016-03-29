package com.netease.jsonbenchmark.type;

/**
 * Created by light on 2016/3/26.
 */
public class ConcreteClass extends BaseGenericClassA<String>
  implements InterfaceA, InterfaceB {
  @Override
  public String get(String s) {
    return s;
  }

  @Override
  public void methodA() {
    System.out.print("methodA");
  }

  @Override
  public void methodB() {
    System.out.print("methodB");
  }
}
