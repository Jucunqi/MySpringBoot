package org.jcq.myspringboot;

import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.Map;

public class MySpringApplication {

    private static AnnotationConfigWebApplicationContext applicationContext;

    public static void run(Class<?> configClazz) {

        // 1.创建IOC容器
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(configClazz);
        context.refresh();
        // 2.根据不同依赖，决定启动不同的容器
        WebServer webServer = getWebServer(context);
        webServer.start();
    }

    private static WebServer getWebServer(AnnotationConfigWebApplicationContext applicationContext) {

        // 查看spring容器中依赖了哪个容器bean
        Map<String, WebServer> beansOfType = applicationContext.getBeansOfType(WebServer.class);
        if (beansOfType.isEmpty()) {
            throw new RuntimeException("can't find WebServer");
        }

        if (beansOfType.size() > 1) {
            throw new RuntimeException("more than one WebServer");
        }
        // 根据依赖来决定启动容器
        return beansOfType.values().stream().findFirst().get();
    }

    public static void main(String[] args) {
        startTomcat();
    }
    private static void startTomcat() {

        try {
            // 创建Tomcat实例
            Tomcat tomcat = new Tomcat();
            Server server = tomcat.getServer();
            Service service = server.findService("Tomcat");

            // 创建容器
            Connector connector = new Connector();
            connector.setPort(9999);

            // 创建engine
            Engine engine = new StandardEngine();
            engine.setDefaultHost("localhost");

            // 创建host
            Host host = new StandardHost();
            host.setName("localHost");

            String contextPath = "";
            Context context = new StandardContext();
            context.setPath(contextPath);
            context.addLifecycleListener(new Tomcat.FixContextListener());

            host.addChild(context);
            engine.addChild(host);
            service.addConnector(connector);
            service.setContainer(engine);

            // 启动Tomcat
            tomcat.start();
            System.out.println("tomcat start success..");
            // 异步进行接收请求
            server.await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description: 启动tomcat服务，加载applicationContext
     * @Author: jucunqi
     * @Date 2025/1/7
     */
    private static void startServer()  {

        try {
            // 创建Tomcat实例
            Tomcat tomcat = new Tomcat();
            Server server = tomcat.getServer();
            Service service = server.findService("Tomcat");

            // 创建容器
            Connector connector = new Connector();
            connector.setPort(9999);

            // 创建engine
            Engine engine = new StandardEngine();
            engine.setDefaultHost("localhost");

            // 创建host
            Host host = new StandardHost();
            host.setName("localHost");

            String contextPath = "";
            Context context = new StandardContext();
            context.setPath(contextPath);
            context.addLifecycleListener(new Tomcat.FixContextListener());

            host.addChild(context);
            engine.addChild(host);
            service.addConnector(connector);
            service.setContainer(engine);

            // 创建DispatcherServlet
            DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);
            // 设置Servlet名称
            // dispatcherServlet.setServletName("dispatcherServlet");
            // 将DispatcherServlet添加到Tomcat上下文
            Tomcat.addServlet(context, "dispatcherServlet", dispatcherServlet);
            // 设置Servlet映射路径（这里设置为所有请求都由DispatcherServlet处理）
            context.addServletMappingDecoded("/*", "dispatcherServlet");

            // 启动Tomcat
            tomcat.start();
            System.out.println("tomcat start success..");
            // 异步进行接收请求
            server.await();

        } catch (LifecycleException e) {
            e.printStackTrace();
        }

    }

}
