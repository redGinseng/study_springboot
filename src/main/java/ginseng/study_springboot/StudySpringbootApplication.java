package ginseng.study_springboot;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

//@SpringBootApplication
public class StudySpringbootApplication {

    public static void main(String[] args) {
        // 스프링컨테이너를 사용해보자
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        // 서블릿의 경우에는 new HttpServerlet해서 add 해줬던걸
        // 스프링컨테이너에서는 클래스를 이용해서 Bean Object를 만들어준다.
        applicationContext.registerBean(HelloController.class);
        applicationContext.refresh();

        ServletWebServerFactory factory = new TomcatServletWebServerFactory();
        WebServer webServer = factory.getWebServer(servletContext -> {
            servletContext.addServlet("frontController", new HttpServlet() {
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp)
                    throws ServletException, IOException {
                    if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {

                        String name = req.getParameter("name");

                        // 서블릿 컨테이너가 스프링컨테이너에게 HelloController 타입의 빈을 가져오도록 한다.
                        HelloController helloController = applicationContext.getBean(HelloController.class);
                        String ret = helloController.hello(name);

                        resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
                        resp.getWriter().print(ret);

                    } else {
                        resp.setStatus(HttpStatus.NOT_FOUND.value());
                    }
                }
                //이 서블렛은 어떤역할을 할지 매핑추가
            }).addMapping("/*");
        });
        webServer.start();
    }

}
