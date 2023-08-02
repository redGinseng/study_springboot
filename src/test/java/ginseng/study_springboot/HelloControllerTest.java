package ginseng.study_springboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class HelloControllerTest {

    @Test
    void helloController() {
//        앞서, HelloService의 테스트 코드에서 언급했듯, 단위테스트는 클래스간의 의존관계를 신경쓰지 않고 자바 오브젝트 자체에만 신경을 쓴다.
//        HelloController의 경우 HelloService에 의존성이 있는데, 이를 테스트코드에서 구현하기엔 무겁다.
//        직접 구현한 HelloService 인스턴스를 사용하면 테스트 코드가 실제 구현과 너무 밀접하게 연결될 수 있어, 유연성이 떨어진다.
//        HelloService helloService = new SimpleHelloService();
//        HelloController helloController = new HelloController(helloService);

//      그냥 여기서 실제 의존관계 생각하지말고, 간단히 익명클래스로 구현하자.
//        익명 클래스는 이름이 없는 클래스로, 일반적으로 한 번만 사용되는 클래스의 경우 유용하게 사용할 수 있다

//        HelloController helloController = new HelloController(new HelloService() {
//            @Override
//            public String sayHello(String name) {
//                return null;
//            }
//        });

        // 메소드가 하나라면 람다로 바꿀수도 있다.
        // 람다 표현식 name -> null이 바로 Test Stub의 역할을 한다.
        // 이 Stub은 HelloService의 sayHello 메서드가 어떻게 동작해야 하는지를 정의하지 않고, 단순히 null 값을 반환하도록 설정되어 있다.
        //실제 HelloService의 복잡한 로직을 테스트에서는 관심 대상이 아니므로, Stub을 사용하여 해당 부분을 간단하게 대체하고, 테스트의 복잡성을 줄인다.
        HelloController helloController = new HelloController(name -> name);
        String ret = helloController.hello("Test");
        Assertions.assertThat(ret).isEqualTo("Test");
    }

    @Test
    void failsHelloWhenNull() {
        HelloController helloController = new HelloController(name -> name);
        Assertions.assertThatThrownBy(() -> {
            String ret = helloController.hello(null);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void failsHelloWhenEmpty() {
        HelloController helloController = new HelloController(name -> name);
        Assertions.assertThatThrownBy(() -> {
            String ret = helloController.hello("");
        }).isInstanceOf(IllegalArgumentException.class);
    }

}