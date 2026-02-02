package com.hello.core.singleton;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

  @Test
  void statefulServiceSingleton(){
    ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
    StatefulService statefulService1 = ac.getBean(StatefulService.class);
    StatefulService statefulService2 = ac.getBean(StatefulService.class);

    //Thread A:A사용자 주문 금액 조회
    statefulService1.order("A",10000);

    //Thread B:B사용자 주문 금액 조회
    statefulService2.order("B",20000);

    //Thread A:사용자 주문 금액 조회
    int price = statefulService1.getPrice();
    System.out.println(price);
  }

  static class TestConfig{

    @Bean
    public StatefulService getStatefulService(){
      return new StatefulService();
    }
  }
}