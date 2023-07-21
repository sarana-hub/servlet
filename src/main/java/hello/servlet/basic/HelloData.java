package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

//JSON 형식의 데이터를 객체로 바꿀 수 있도록, 객체 생성
@Getter @Setter //getter, setter 자동 추가해준다
public class HelloData {

    private String username;
    private int age;

}
