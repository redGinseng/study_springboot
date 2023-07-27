package ginseng.study_springboot;


// interface를 사용하면, HelloController는
// 뒷단에 Service에 변경사항이 생겨도
// 코드를 고치지 않아도 된다.
// 근데 그렇게 컴파일타임은 넘어간다 해도, 런타임 때는, 실제로 어떤 클래스를 사용할 것인가 결정이 되어야한다.
// HelloController는 어떤 타입의 오브젝트를 사용해야할지 어떻게 아는가?
// 그걸 매핑해주는게 DI. 그걸 해주는게 Assembler
public interface HelloService {
    String sayHello(String name);
}
