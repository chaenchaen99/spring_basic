package com.hello.core.discount;

import com.hello.core.member.Grade;
import com.hello.core.member.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RateDiscountPolicyTest {
  RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

  @Test
  @DisplayName("VIP는 10%할인이 적용되어야 한다")
  void vip_o() {
    //given
    Member member = new Member(1L, "memberVip", Grade.VIP);
    //when
    int discount = rateDiscountPolicy.discount(member, 100000);
    //then
    Assertions.assertEquals(10000, discount);
  }

  @Test
  @DisplayName("VIP는 10%할인이 적용되어야 한다")
  void vip_x() {
    //given
    Member member = new Member(1L, "memberVip", Grade.BASIC);
    //when
    int discount = rateDiscountPolicy.discount(member, 100000);
    //then
    Assertions.assertEquals(0, discount);
  }
}
