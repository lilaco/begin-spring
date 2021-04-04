package hello.beginspring.service;

import hello.beginspring.domain.Member;
import hello.beginspring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;


class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    //Service 클래스와 Test 클래스 양쪽에서 같은 인스턴스를 사용하기 위해 만든 코드.
    @BeforeEach //AfterEach 와 반대로 메소드가 실행 되기 전에 실행되는 어노테이션
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    // 각 메서드가 실행이 끝날때마다 호출되는 콜백함수라고 생각하기
    public void afterEach() {
        memberRepository.clearStore(); //메소드가 실행될때마다 clear();
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");

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
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

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

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}