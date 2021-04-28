package hello.beginspring.repository;

import hello.beginspring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();

    //Optional 은 JAVA8버젼부터 생성되었으며,
    // ID로 회원을 찾는 기능을 만드는데 findByID 나 findByName 으로 데이터를 찾는데, 없으면 NULL 을 반환하게
    // 하지 않고, Optional 로 감싸서 반환함.(이유는 더 찾아보기 !!)
}
