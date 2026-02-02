package com.hello.core.order;

import com.hello.core.discount.DiscountPolicy;
import com.hello.core.member.Member;
import com.hello.core.member.MemberRepository;
import com.hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

  //중요**) DIP지키기 : 인터페이스에만 의존한다, 구체적인 클래스는 전혀 모른다!!
  private MemberRepository memberRepository;
  private DiscountPolicy discountPolicy;

  public OrderServiceImpl(MemberRepository memberRepository,DiscountPolicy discountPolicy) {
    this.discountPolicy = discountPolicy;
    this.memberRepository = memberRepository;
  }

  @Override
  public Order createOrder(Long memberId, String itemName, int itemPrice) {
    Member member = memberRepository.findById(memberId);
    int disCountPrice = discountPolicy.discount(member, itemPrice);

    return new Order(memberId, itemName, itemPrice, disCountPrice);
  }
}
