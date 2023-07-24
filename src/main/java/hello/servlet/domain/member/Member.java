package hello.servlet.domain.member;

import lombok.Getter;
import lombok.Setter;

/**회원 도메인 모델*/

@Getter @Setter
public class Member {

    private Long id;
    private String username;
    private int age;

    public Member() {}

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
        //id는 Member를 회원저장소(DB)에 저장하면 회원저장소가 할당
    }
}
