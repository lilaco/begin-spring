package hello.beginspring.service;

import hello.beginspring.domain.Member;
import hello.beginspring.repository.MemberRepository;
import hello.beginspring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

//비즈니스 로직에 가까운 메서드 이름으로 작성하기.
//@Service

//@Service Annotation 내부에 @Component 가 내장되어있다. (@Controller 와 @Repository 도 마찬가지)
//이를 컴포넌트 스캔과 자동 의존관계 설정이라 말한다(?)
//@Component 가 내장되어 있기 때문에 스프링 빈으로 자동 등록되어있는 것 !

//참고: 스프링은 스프링 컨테이너에 스프링 빈을 등록할 때, 기본으로 싱글톤(singleton)으로 등록한다
// (유일하게 하나만 등록해서 공유한다.)따라서 같은 스프링 빈이면 모두 같은 인스턴스이다.

@Transactional
//JPA 를 사용하려면 항상 Transactional 이 있어야 한다.
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
    * 회원 가입
    */
    public Long join(Member member) {
        //같은 이름이 있는 중복 회원X
        /*
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        */
        // Optional 로 감쌌기 때문에 가능한 코드 (회원 서비스 개발 3:00 다시보기)

        //위 코드를 아래와 같이 요약
        validateDuplicateMember(member);// 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    //Extract Method(Ctrl + Alt + M)
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /*
    * 전체회원 조회
    */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /*
    * 회원 한명만 조회
    */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findByID(memberId);
    }
}
