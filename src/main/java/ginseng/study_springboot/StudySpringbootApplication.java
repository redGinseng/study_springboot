package ginseng.study_springboot;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.embedded.jetty.JettyWebServer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;

//@SpringBootApplication
public class StudySpringbootApplication {

    public static void main(String[] args) {
        ServletWebServerFactory factory = new TomcatServletWebServerFactory();
        // 서블릿컨테이너에 서블릿을 등록해보자.  외우려고 하지말고 적당히 보자
        // 인터페이스르르 구현한 클래스를 넣어도 되는데, 한번쓰고 말거니까 그냥 익명클래스를 파라미터로 주자. //functional Interface
        WebServer webServer = factory.getWebServer(servletContext -> {
            // 서블릿을 추가
            servletContext.addServlet("hello", new HttpServlet() {
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp)
                    throws ServletException, IOException {
                    // 요청을 받아오는데 필요한 오브젝트와 응답을 만들어내는데 필요한 오브젝트 전달
                    resp.setStatus(200);
                    resp.setHeader("Content-Type", "text/plain");
                    resp.getWriter().print("Hello Servlet");
                }
                //이 서블렛은 어떤역할을 할지 매핑추가
            }).addMapping("/hello");


        });
        webServer.start();
    }

}
