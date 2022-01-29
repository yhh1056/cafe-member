package cafeorder.repository;

import cafeorder.domain.Member;
import cafeorder.domain.Wage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("회원 단건 조회 테스트")
    void findMember() {
        Member tester = Member.of("tester");
        memberRepository.save(tester);

        Wage week1 = Wage.of(12, false);
        Wage week2 = Wage.of(12, false);
        Wage week3 = Wage.of(12, false);
        Wage week4 = Wage.of(12, false);
        Wage week5 = Wage.of(12, false);

        tester.addWage(week1, week2, week3, week4, week5);

        entityManager.flush();
        entityManager.clear();

        Member member = memberRepository.findById(tester.getId()).get();

        assertThat(member.getWage1().getWage()).isEqualTo(8530 * 12);

    }
}