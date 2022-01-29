package cafeorder.repository;

import cafeorder.domain.Member;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // @Query("select m from Member as m join fetch m.wages") 단건 조회시 시급 정보도 필요하다면 사용
    Optional<Member> findById(Long id);

    @Query("select m from Member as m")
    List<Member> findMembers();

    @Query("select distinct m from Member as m join fetch m.wages")
    List<Member> findAll();

    void delete(Member member);

    boolean existsByName(String name);
}
