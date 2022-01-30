package cafeorder.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static cafeorder.domain.Week.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Member 테스트")
class MemberTest {

    @Test
    @DisplayName("이름 변경")
    void changeNameTest() {
        Member tester = Member.of("tester");
        String name = "hohoman";
        tester.changeName(name);

        assertThat(tester.getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("월급 계산")
    void totalAmountTest() {
        Member tester = Member.of("tester");

        int workTime = 12;
        Wage.builder().week(WEEK1).workTime(workTime).member(tester).build();
        Wage.builder().week(WEEK2).workTime(workTime).member(tester).build();
        Wage.builder().week(WEEK3).workTime(workTime).member(tester).build();
        Wage.builder().week(WEEK4).workTime(workTime).member(tester).build();

        tester.calculateTotalWage();

        assertThat(tester.getTotalWage()).isEqualTo(new Money(409440));
    }

    @Test
    @DisplayName("주급 정보 조회")
    void findWeekTest() {
        Member tester = Member.of("tester");

        Wage.builder().week(WEEK1).workTime(12).member(tester).build();
        Wage.builder().week(WEEK2).workTime(14).member(tester).build();

        Wage wage = tester.findWeek(WEEK1);

        assertTrue(wage.isEqualsWeek(WEEK1));
        assertThat(wage.getTotalAmount()).isEqualTo(new Money(102360));
    }
}