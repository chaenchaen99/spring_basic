package com.hello.core.singleton;

public class SingletonService {

  private static final SingletonService instance = new SingletonService();

  public static SingletonService getInstance() {
    return instance;
  }

  //SingletonService a = new SingletonService();
  //외부에서 이거 못하게 하려고 다음라인 추가함
  private SingletonService() {}

  public void logic() {
    System.out.println("싱글톤 객체 로직 호출");
  }
}
