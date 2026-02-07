package com.hello.core.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import com.hello.core.AppConfig;
import com.hello.core.member.MemberRepository;
import com.hello.core.member.MemberServiceImpl;
import com.hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

  @Test
  void configurationTest() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
    OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
    MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

    MemberRepository memberRepository1 = memberService.getMemberRepository();
    MemberRepository memberRepository2 = orderService.getMemberRepository();

    //결과: new로 생성된 세 인스턴스 참조값이 똑같다
    System.out.println("memberService -> memberRepository = " + memberRepository1);
    System.out.println("orderService -> memberRepository = " + memberRepository2);
    System.out.println("memberRepository = " + memberRepository );

    assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
    assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
  }

  @Test
  void configurationDeep() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    AppConfig bean = ac.getBean(AppConfig.class);

    //순수한 클래스라면 다음과 같이 출력
    //`class.hello.core.AppConfig`
    //실제 출력은 다음과 같다
    //`bean =class com.hello.core.AppConfig$$SpringCGLIB$$0`
    //스프링이 CGLIB라는 바이트코드조작 라이브러리를 사용하여 AppConfig를 상속받은 임의의 다른 클래스를 만들고,
    // 그 다른 클래스를 스프링 빈으로 등록한 것!
    //즉, 그 임의의 다른 클래스가 바로 싱글톤이 보장되도록 해준다.
    System.out.println("bean =" + bean.getClass());
  }

}
