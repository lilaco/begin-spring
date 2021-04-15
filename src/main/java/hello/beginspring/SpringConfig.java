package hello.beginspring;

import hello.beginspring.repository.JdbcMemberRepository;
import hello.beginspring.repository.JdbcTemplateMemberRepository;
import hello.beginspring.repository.MemberRepository;
import hello.beginspring.repository.MemoryMemberRepository;
import hello.beginspring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//자바 코드로 직접 스프링 빈 등록하기
@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig (DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
        return new JdbcTemplateMemberRepository(dataSource);
    }
}
