package hello.beginspring.service;

import hello.beginspring.domain.Member;
import hello.beginspring.repository.MemberRepository;
import hello.beginspring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional

class MemberServiceIntegerationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("springTEST");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        //멤버 이름을 검증
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }
    //예외 플로우를 생성하는것도 중요하다 (회원가입 테스트 메소드는 정상플로우)
    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("springTEST");

        Member member2 = new Member();
        member2.setName("springTEST");

        //when
        memberService.join(member1);

        //TryCatch문을 사용하지 않는 방법
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        /*
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
        */

        //then
    }

}