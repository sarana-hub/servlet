package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
/**회원 저장소 테스트 코드*/

class MemberRepositoryTest {
    MemberRepository memberRepository = MemberRepository.getInstance();
    //회원 저장소는 싱글톤 패턴을 적용했으므로, new MemberRepository 하면 안됨
    //스프링을 사용하면 싱글톤 쓸 필요 없음 (스프링 빈으로 등록하면 됨)

    @AfterEach
    void afterEach() { //각 테스트가 끝날 때, 다음 테스트에 영향을 주지않도록
        memberRepository.clearStore();
        //각 테스트의 저장소를 clearStore() 를 호출해서 초기화
    }

    @Test
    void save() { //회원 저장
        //given
        Member member = new Member("hello", 20);
        //when
        Member savedMember = memberRepository.save(member);
        //then
        Member findMember = memberRepository.findById(savedMember.getId());

        assertThat(findMember).isEqualTo(savedMember);
    }

    @Test
    void findAll() { //회원 목록 조회
        //given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);

        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> result = memberRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2); //clearStore()안하면 3
        assertThat(result).contains(member1, member2);
    }
}
