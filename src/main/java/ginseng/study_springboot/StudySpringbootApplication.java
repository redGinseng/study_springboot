package ginseng.study_springboot;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

//@SpringBootApplication
public class StudySpringbootApplication {

    public static void main(String[] args) {

        ServletWebServerFactory factory = new TomcatServletWebServerFactory();
        WebServer webServer = factory.getWebServer(servletContext -> {
            HelloController helloController = new HelloController();

            servletContext.addServlet("frontController", new HttpServlet() {
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp)
                    throws ServletException, IOException {
                    //  인증, 보안, 다국어 처리 등 공통기능을 처리할 수 있다.

                    //url 이 hello 이면서 GET일때
                    if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {

                        //요청으로부터 파라미터 추출하고
                        String name = req.getParameter("name");
                        // 컨트롤러 로 전달하기. helloController는 웹형식의 리퀘스트를 다루지 않고, 평범한 자바 타입의 오브젝트 (DTO 등) 를 사용할 수 있게 한다.
                        // 이런류의 작업을 바인딩이라고 한다.
                        String ret = helloController.hello(name);

                        resp.setStatus(HttpStatus.OK.value());
                        resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                        resp.getWriter().print(ret);
                    } else if (req.getRequestURI().equals("/user")) {
                        //
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
