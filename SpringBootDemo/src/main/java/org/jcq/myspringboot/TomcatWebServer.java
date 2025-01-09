package org.jcq.myspringboot;



/**
 * @Description: Tomcat容器对象
 * @Author: jucunqi
 * @Date 2025/1/7
 */
public class TomcatWebServer implements WebServer {

    @Override
    public void start() {
        System.out.println("启动Tomcat");
    }
}
