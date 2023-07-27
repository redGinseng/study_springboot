package ginseng.study_springboot;

public class ComplexHelloService implements HelloService{
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }

}
