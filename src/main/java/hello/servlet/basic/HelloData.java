package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

//JSON형식으로 파싱할수있게 객체 하나 생성
@Getter @Setter
public class HelloData {

    private String username;
    private int age;

}
