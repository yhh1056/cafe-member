package cafeorder.repository;

import cafeorder.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * author {yhh1056}
 * Create by {2020/09/29}
 */

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findById(Long id);

    List<Member> findAll();

    void delete(Member member);

    boolean existsByName(String name);
}
