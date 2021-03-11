package hello.beginspring.repository;

import hello.beginspring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    //클래스를 실행하면 전체 테스트를 실행할 수 있다.

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    // 각 메서드가 실행이 끝날때마다 호출되는 콜백함수라고 생각하기
    public void afterEach() {
        repository.clearStore();
        //테스트는 순서와 상관없이 설계가 되어야 한다.

        //TDD 란 Test-Driven development 로
        // 테스트를 우선 작성하여 틀을 만들고 구현 코드를 작성하는 방법(테스트 -> 구현)
        // 테스트에 대해서 깊이있게 공부할 것!
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findByID(member.getId()).get();
        //System.out.println("result = " + (result == member));
        //출력도 가능하지만 아래와 같이 확인할 수 있다. (assertions)

        //출력없이 테스트하기
        //Assertions.assertEquals(member, result);

        //실무에서 주로 사용하는 방법. member 가 result 와 일치하느냐 확인
        //Assertions.assertThat(member).isEqualTo(result);

        //Assertions 를 static 으로 만들어놓고 사용하기기
       assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

}
