package org.jcq.myspringboot;

import org.eclipse.jetty.util.Jetty;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @Description: Jetty容器条件
 * @Author: jucunqi
 * @Date 2025/1/8
 */
public class JettyCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        try {
            context.getClassLoader().loadClass("org.eclipse.jetty.util.Jetty");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
