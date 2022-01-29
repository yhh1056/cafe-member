package cafeorder.repository;

import cafeorder.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

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

        // Waget week1 = Waget.of(12, false);
        // Waget week2 = Waget.of(12, false);
        // Waget week3 = Waget.of(12, false);
        // Waget week4 = Waget.of(12, false);
        // Waget week5 = Waget.of(12, false);
        //
        // tester.addWage(week1, week2, week3, week4, week5);
        //
        // entityManager.flush();
        // entityManager.clear();
        //
        // Member member = memberRepository.findById(tester.getId()).get();
        //
        // assertThat(member.getWaget1().getWage()).isEqualTo(8530 * 12);

    }
}