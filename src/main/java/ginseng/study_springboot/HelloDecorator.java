package ginseng.study_springboot;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary    //이제 HelloService를 구현한 클래스가 두개라서, Autowired되길 기대하면 안된다. 에러가 뜬다. 이 때 @Primary 를 주면 이 클래스가 먼저 주입된다.
public class HelloDecorator implements HelloService {

    private final HelloService helloService;

    public HelloDecorator(HelloService helloService) {
        this.helloService = helloService;
    }


    @Override
    public String sayHello(String name) {
        return "*" + helloService.sayHello(name) + "*";
    }
}
