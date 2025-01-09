package org.jcq.myspringboot;

/**
 * @Description: Jetty容器对象
 * @Author: jucunqi
 * @Date 2025/1/8
 */
public class JettyWebServer implements WebServer{

    @Override
    public void start() {
        System.out.println("启动Jetty");
    }
}
