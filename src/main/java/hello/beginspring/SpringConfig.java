package hello.beginspring;

import hello.beginspring.repository.*;
import hello.beginspring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

//자바 코드로 직접 스프링 빈 등록하기
@Configuration
public class SpringConfig {

    /* JPA 사용하기 이전 방법들은 DataSource 를 DI 한다.
    private DataSource dataSource;

    @Autowired
    public SpringConfig (DataSource dataSource){
        this.dataSource = dataSource;
    }
    */

    /* JPA 사용하기 위해 Entity 를 DI 해준다.
    private EntityManager em;

    public SpringConfig(EntityManager em) {
        this.em = em;
    }
*/

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    /*
    @Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
        //return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
    }*/
}
