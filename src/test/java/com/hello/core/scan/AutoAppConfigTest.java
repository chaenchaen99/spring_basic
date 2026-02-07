package com.hello.core.scan;

import static org.assertj.core.api.Assertions.assertThat;

import com.hello.core.AutoAppConfig;
import com.hello.core.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class AutoAppConfigTest {

  @Test
  public void autoAppConfigTest() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
    MemberService memberService = ac.getBean(MemberService.class);
    assertThat(memberService).isInstanceOf(MemberService.class);
  }
}
