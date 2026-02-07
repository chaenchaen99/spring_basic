package com.hello.core.scan.filter;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@interface: “MyExcludeComponent라는 새로운 어노테이션을 하나 정의하겠다”
public @interface MyExcludeComponent {

}
