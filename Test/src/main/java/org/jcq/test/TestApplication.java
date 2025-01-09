package org.jcq.test;

import org.jcq.myspringboot.MySpringApplication;
import org.jcq.myspringboot.MySpringBootApplication;

/**
 * @Description: 使用自定义springboot启动
 * @Author: jucunqi
 * @Date 2025/1/8
 */
@MySpringBootApplication
public class TestApplication {

    public static void main(String[] args) {
        // 模仿SpringBoot启动模式
        MySpringApplication.run(TestApplication.class);
    }
}
