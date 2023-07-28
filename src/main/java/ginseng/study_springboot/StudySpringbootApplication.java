package ginseng.study_springboot;

import java.io.IOException;
import java.lang.annotation.Annotation;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

//@SpringBootApplication
@ComponentScan
@Configuration
public class StudySpringbootApplication {


    // 서블릿을 다루고 싶지 않아서 컨테이너리스=스프링 을 사용하고 있는데, 자꾸만 서블릿 코드가 복잡해진다.
    public static void main(String[] args) {
        // 스프링 컨테이너 초기화하고 빈을 등록 (refresh) , 등록할 때(onRefresh) 서블릿컨테이너를 생성하고, Frontcontroller를 등록까지
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext(){
          @Override
          protected void onRefresh(){
              super.onRefresh();
              ServletWebServerFactory factory = new TomcatServletWebServerFactory();
              WebServer webServer = factory.getWebServer(servletContext -> {
                  servletContext.addServlet("dispatcherServlet",
                      new DispatcherServlet(this)
                  ).addMapping("/*");
              });
              webServer.start();
          }
        };
        applicationContext.register(StudySpringbootApplication.class);
        applicationContext.refresh();

    }

}
