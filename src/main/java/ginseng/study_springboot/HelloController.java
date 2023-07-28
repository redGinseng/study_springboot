package ginseng.study_springboot;

import java.util.Objects;

public class HelloController {

    private final HelloService helloService;

    // 스프링컨테이너가 HelloService 인터페이스를 구현한 클래스가 있나 뒤져보고, 넣어준다.
    // 그럼 클래스가 두개 이상있다면?
    // 컴파일할 때 에러 발생 NoUniqueBeanDefinitionException
    public  HelloController(HelloService helloService){
        this.helloService = helloService;
    }

    public String hello(String name) {

        return helloService.sayHello(Objects.requireNonNull(name));
    }


}
