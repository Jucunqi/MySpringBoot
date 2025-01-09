package org.jcq.myspringboot;

import org.springframework.context.annotation.Conditional;

import javax.xml.bind.Element;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 自定义注解，判断当存在某个类的时候，需要加载bean
 * @Author: jucunqi
 * @Date 2025/1/8
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Conditional(MyOnClassCondition.class)
public @interface MyConditionalOnClass {

    String value();
}
