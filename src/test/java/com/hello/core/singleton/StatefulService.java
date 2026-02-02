package com.hello.core.singleton;

public class StatefulService {

  //singleton(bean)으로 만들어지기 때문에 공유되는 필드이다.
  //상태를 유지하는 필드
  private int price;

  public void order(String name, int price){
    System.out.println("name=" + name + " price=" + price);
    this.price = price; //여기가 문제!
  }

  public int getPrice() {
    return price;
  }
}
