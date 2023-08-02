package ginseng.study_springboot;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class HelloServiceTest {

    // SpringbootTest는 Server 띄우고 네트워크로 HTTP 보내고 받고...
    // JavaClass 만 간단히 테스트할거면 UnitTest로
    // 고립된 테스트가 가능하다.
    @Test
    void simpleHelloService(){
        HelloService helloService = new SimpleHelloService();

        String ret = helloService.sayHello("Test");
        Assertions.assertThat(ret).isEqualTo("Hello Test");
    }

    @Test
    void helloDecorator(){
        HelloDecorator helloDecorator = new HelloDecorator(name -> name);

        String ret =helloDecorator.sayHello("Test");
            Assertions.assertThat(ret).isEqualTo("*Test*");

    }
}