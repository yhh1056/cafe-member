package cafeorder.repository;

import cafeorder.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * author {yhh1056}
 * Create by {2020/09/29}
 */

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    public void save(Member member) {
        em.persist(member);
    }

    public List<Member> findByName(String name) {
        return em.createQuery("SELECT m FROM Member m WHERE m.name = :name", Member.class).
                setParameter("name", name).getResultList();
    }

    public List<Member> findAll() {
        return em.createQuery("SELECT m FROM Member m", Member.class).getResultList();
    }
}
