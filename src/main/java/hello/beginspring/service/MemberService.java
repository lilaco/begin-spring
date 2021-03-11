package hello.beginspring.service;

import hello.beginspring.domain.Member;
import hello.beginspring.repository.MemberRepository;
import hello.beginspring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

//비즈니스 로직에 가까운 메서드 이름으로 작성하기.
public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

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
