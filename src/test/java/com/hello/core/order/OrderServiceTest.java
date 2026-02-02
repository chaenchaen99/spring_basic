package com.hello.core.order;

import com.hello.core.AppConfig;
import com.hello.core.member.Grade;
import com.hello.core.member.Member;
import com.hello.core.member.MemberService;
import com.hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
  AppConfig appConfig = new AppConfig();
  MemberService memberService = appConfig.memberService();
  OrderService orderService = appConfig.orderService();

  @Test
  public void createOrder() {
    Long memberId = 1L;
    Member member = new Member(memberId,"memberA", Grade.VIP);
    memberService.join(member);

    Order order = orderService.createOrder(memberId, "itemA", 10000000);
    Assertions.assertEquals(order.getDiscountPrice(), 1000);
  }

}
