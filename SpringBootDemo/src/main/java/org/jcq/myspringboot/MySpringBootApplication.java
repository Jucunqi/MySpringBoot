package org.jcq.myspringboot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Description: 自定义 SpringBootApplication注解
 * @Author: jucunqi
 * @Date 2025/1/7
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ComponentScan
// import注解：指定扫描的类 ，因为不加这个注解的话，用户不能扫描到指定配置，让自动配置类生效
@Import(WebServerAutoConfiguration.class)
public @interface MySpringBootApplication {
}
