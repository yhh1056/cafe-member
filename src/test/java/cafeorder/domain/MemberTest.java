package cafeorder.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static cafeorder.domain.Week.*;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Member 테스트")
class MemberTest {

    @Test
    @DisplayName("월급 계산")
    void totalAmount() {
        Member tester = Member.of("tester");

        int workTime = 12;
        Wage.builder().week(WEEK1).workTime(workTime).member(tester).build();
        Wage.builder().week(WEEK2).workTime(workTime).member(tester).build();
        Wage.builder().week(WEEK3).workTime(workTime).member(tester).build();
        Wage.builder().week(WEEK4).workTime(workTime).member(tester).build();

        tester.calculateTotalWage();

        assertThat(tester.getTotalWage()).isEqualTo(new Money(409440));
    }
}