package org.jcq.myspringboot;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Map;

/**
 * @Description: 自定义通用根据类注入条件
 * @Author: jucunqi
 * @Date 2025/1/8
 */
public class MyOnClassCondition implements Condition {

    /**
     * 条件逻辑
     * @param context 上下文对象
     * @param metadata 存放着当前注解所在方法头上的所有注解及注解中的参数信息
     * @return 是否需要注入
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        // 通过类加载其加载Tomcat类，如果能加载到证明用户依赖了Tomcat，从而启动Tomcat
        try {
            String name = MyConditionalOnClass.class.getName();
            Map<String, Object> valueMap = metadata.getAnnotationAttributes(name);
            Object value = valueMap.get("value");
            context.getClassLoader().loadClass(value.toString());
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
