package com.hello.core.member;

import com.hello.core.AppConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

  AppConfig appConfig = new AppConfig();
  MemberService memberService = appConfig.memberService();
//  MemberService memberService = new MemberServiceImpl(null);

  @Test
  void join(){
    //given
    Member member = new Member(1L, "memberA", Grade.VIP);

    //when
    memberService.join(member);
    Member findMember = memberService.findMember(2L); //에러날 것

    //then
    Assertions.assertEquals(member,findMember);
  }
}
