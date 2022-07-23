package hello.servlet.domain.member;

import lombok.Getter;
import lombok.Setter;

/**회원 도메인 모델*/

@Getter @Setter
public class Member {
    private Long id;
    private String username;
    private int age;

    public Member() { //기본 생성자
    }

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
        //id 는 Member를 회원저장소에 저장하면 회원 저장소가 할당
    }
}
