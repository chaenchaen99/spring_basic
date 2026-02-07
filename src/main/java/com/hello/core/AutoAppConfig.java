package com.hello.core;

import com.hello.core.member.MemberRepository;
import com.hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

//AutoAppConfig.class는
//이미 “수동으로” 스프링 컨테이너에 등록됨
//컴포넌트 스캔으로 찾는 게 아님
//그래서 excludeFilters에 영향받지 않음
@Configuration
@ComponentScan(
    //basePackages를 지정하지 않으면 @ComponentScan이 붙은 설정 정보 클래스의 패키지(com.hello.core)이 시작 위치가 된다.
    //basePackages = "com.hello.core",
    //기존에 만들어두었던 AppConfig.class, TestConfig.class를 제외시키기 위해서 하위 코드 추가
    //실무에서는 굳이 이렇게 하진 않지만 기존 코드를 최대한 남기기 위해 해당 방법 선택
    excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class )
)
public class AutoAppConfig {

}
