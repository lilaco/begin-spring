package hello.beginspring;

import hello.beginspring.repository.MemberRepository;
import hello.beginspring.repository.MemoryMemberRepository;
import hello.beginspring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//자바 코드로 직접 스프링 빈 등록하기
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
