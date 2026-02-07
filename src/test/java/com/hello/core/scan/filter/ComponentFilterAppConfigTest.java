package com.hello.core.scan.filter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.context.annotation.ComponentScan.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

public class ComponentFilterAppConfigTest {

  @Test
  void filterScan() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        ComponentFilterAppConfig.class);

    //스프링 컨테이너에서 beanA이 정상적으로 등록되어있는지 확인한다
    BeanA beanA = ac.getBean("beanA", BeanA.class);
    assertThat(beanA).isNotNull();

    //스프링 컨테이너에서 beanB를 꺼내려고 하면
    // NoSuchBeanDefinitionException 예외가 발생해야 한다
    assertThrows(
        NoSuchBeanDefinitionException.class,
        () -> ac.getBean("beanB", BeanB.class));
  }

  @Configuration
  //스프링 컴포넌트 스캔을 "내가 정한 규칙"으로 조절하는 코드
  //MyIncludeComponent가 붙은 클래스만 빈으로 등록하고,
  //MyExcludeComponent가 붙은 클래스는 무조건 제외한다.
  //@Filter(type= FilterType.ANNOTATION)은 기본타입이기때문에 빼도 무관하다
  @ComponentScan(
      includeFilters = @Filter(type= FilterType.ANNOTATION, classes = MyIncludeComponent.class),
      excludeFilters = @Filter(type= FilterType.ANNOTATION, classes = MyExcludeComponent.class)
  )
  static class ComponentFilterAppConfig {

  }
}
