package ginseng.study_springboot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.embedded.jetty.JettyWebServer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;

//@SpringBootApplication
public class StudySpringbootApplication {

    public static void main(String[] args) {
        ServletWebServerFactory factory = new TomcatServletWebServerFactory();
        // ServletWebServerFactory는 인터페이스고, TomcatServletWebServerFactory는 이를 구현해서 쓴다.
        // Jetty  다른 웹서버 팩토리로 대체가 가능하다
//        ServletWebServerFactory factory = new JettyServletWebServerFactory(); //

        // WebServer 는 factory의 타입과 관련없이 가져다 쓸 수 있다. 추상화
        WebServer webServer = factory.getWebServer();
        // 톰캣 서블릿 컨테이너 동작
        webServer.start();
        webServer.getPort();
        webServer.stop();
    }

}
