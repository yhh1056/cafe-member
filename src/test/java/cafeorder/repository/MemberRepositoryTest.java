package cafeorder.repository;

import cafeorder.domain.Member;
import cafeorder.domain.Money;
import cafeorder.domain.Wage;
import cafeorder.domain.Week;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static cafeorder.domain.Week.*;
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

        int workTime = 12;
        Wage.builder().week(WEEK1).workTime(workTime).member(tester).build();
        Wage.builder().week(WEEK2).workTime(workTime).member(tester).build();
        Wage.builder().week(WEEK3).workTime(workTime).member(tester).build();
        Wage.builder().week(WEEK4).workTime(workTime).member(tester).build();

        memberRepository.save(tester);

        entityManager.flush();
        entityManager.clear();

        Member member = memberRepository.findById(tester.getId()).get();

        assertThat(member.getName()).isEqualTo("tester");
    }
}