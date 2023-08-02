package ginseng.study_springboot;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class HelloApiTest {

    @Autowired
    HelloController helloController;

    @Test
    void helloApi() {
//        String result = helloController.hello("spring");
        TestRestTemplate rest = new TestRestTemplate();
        ResponseEntity<String> res =
            rest.getForEntity("http://localhost:8080/hello?name={name}", String.class, "Spring");
        // status code 200
        Assertions.assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);

        // header(content-type) text/plain
        //Header가 여러개 있을수 있다고 한다
        Assertions.assertThat(res.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE))
            .startsWith(MediaType.TEXT_PLAIN_VALUE);
        // body Hello Spring
        Assertions.assertThat(res.getBody()).isEqualTo("Hello Spring");
    }

}