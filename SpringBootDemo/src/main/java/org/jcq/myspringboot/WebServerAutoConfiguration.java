package org.jcq.myspringboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 容器服务自动配置类，配置所有容器对象bean，根据用户使用的依赖决定启动哪个容器
 * @Author: jucunqi
 * @Date 2025/1/8
 */
@Configuration
public class WebServerAutoConfiguration {

    @Bean
    // 通过执行匹配类中的逻辑确定
    // @Conditional(TomatCondition.class)
    // 模仿springboot通过conditonOnClass注解实现
    @MyConditionalOnClass("org.apache.catalina.startup.Tomcat")
    public TomcatWebServer tomcatWebServer() {
        return new TomcatWebServer();
    }

    @Bean
    // @Conditional(JettyCondition.class)
    @MyConditionalOnClass("org.eclipse.jetty.util.Jetty")
    public JettyWebServer jettyWebServer() {
        return new JettyWebServer();
    }
}
