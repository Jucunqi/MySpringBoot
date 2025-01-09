package org.jcq.myspringboot;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @Description: 启动Tomcat的条件
 * @Author: jucunqi
 * @Date 2025/1/8
 */
public class TomatCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {


        // 通过类加载其加载Tomcat类，如果能加载到证明用户依赖了Tomcat，从而启动Tomcat
        try {
            context.getClassLoader().loadClass("org.apache.catalina.startup.Tomcat");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
