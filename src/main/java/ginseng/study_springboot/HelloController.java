package ginseng.study_springboot;

import java.util.Objects;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/hello")
//@Controller  // @Component + a
@RestController //@Controller + @ResponseBody
public class HelloController {


    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    // DispahtcherServlet은 스프링컨테이너에 등록된 빈 중에 웹요청을 처리할 수 있는 빈을 찾아서, 매핑테이블에 넣어둔다.
    @GetMapping
    @ResponseBody
    public String hello(String name) {  //DispatcherServlet은 스트링을 받아 뷰를 리턴하는데, 뷰가 없으면 404가 나간다. @ResponseBody를 넣어준다.

        if (name == null || name.trim().length() == 0) {
            throw new IllegalArgumentException();
        }

        return helloService.sayHello(Objects.requireNonNull(name));
    }


}
