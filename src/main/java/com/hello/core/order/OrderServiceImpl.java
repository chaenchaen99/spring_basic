package com.hello.core.order;

import com.hello.core.discount.DiscountPolicy;
import com.hello.core.member.Member;
import com.hello.core.member.MemberRepository;
import com.hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

  //중요**) DIP지키기 : 인터페이스에만 의존한다, 구체적인 클래스는 전혀 모른다!!
  //private final : 얘는 웬만하면 무조건 값 세팅을 해줘! 라는 의미
  private MemberRepository memberRepository;
   private DiscountPolicy discountPolicy;

  //setXXX: 수정자 주입
  //수정자 주입은 선택적인, 변경 가능성 있는 의존성 주입에 사용된다
  @Autowired(required = false) //주입할 대상이 없어도 동작하게하려면
  public void setMemberRepository(MemberRepository memberRepository) {
    System.out.println("memberRepository = " + memberRepository);
    this.memberRepository = memberRepository;
  }
//
  @Autowired
  public void setDiscountPolicy(DiscountPolicy discountPolicy) {
    System.out.println("discountPolicy = " + discountPolicy);
    this.discountPolicy = discountPolicy;
  }

  //@Component가 붙은 클래스에 생성자가 하나있으면 @Autowired 생략해도 상관이 없지만
  // 생성자가 두개 이상이면 스프링이 어떤 생성자를 사용해야할 지 모르므로 하나의 생성자에 @Autowired를 지정해줘야 한다
  @Autowired
  public OrderServiceImpl(MemberRepository memberRepository,DiscountPolicy discountPolicy) {
    System.out.println("1. OrderServiceImpl.OrderServiceImpl");
    this.discountPolicy = discountPolicy;
    this.memberRepository = memberRepository;
  }

  @Override
  public Order createOrder(Long memberId, String itemName, int itemPrice) {
    Member member = memberRepository.findById(memberId);
    int disCountPrice = discountPolicy.discount(member, itemPrice);

    return new Order(memberId, itemName, itemPrice, disCountPrice);
  }

  /// 테스트 용도
  public MemberRepository getMemberRepository() {
    return memberRepository;
  }
}
