package cafeorder.domain;

import java.nio.file.WatchKey;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Wage 테스트")
class WageTest {

    @Test
    @DisplayName("기본 임금 계산")
    void wageTest() {
        int workTime = 12;
        Wage wage = Wage.builder().workTime(workTime).holidayPay(false).build();
        assertThat(wage.getTotalAmount()).isEqualTo(new Money(Wage.MINIMUM_WAGE * workTime));
    }

    @Test
    @DisplayName("주휴 수당 계산")
    void holidayPayTest() {
        int workTime = 12;
        Wage wage = Wage.builder().workTime(workTime).holidayPay(true).build();
        assertThat(wage.getTotalAmount()).isEqualTo(new Money(Wage.MINIMUM_WAGE * workTime * 1.2));
    }

    @Test
    @DisplayName("주 40시간 넘게 근무시 예외 발생")
    void overWorkTimeTest() {
        int workTime = Wage.MAXIMUM_WORK_TIME + 1;
        assertThatThrownBy(() -> Wage.builder().workTime(workTime).holidayPay(true).build())
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("근무시간을 음수로 입력시 예외 발생")
    void underWorkTimeTest() {
        int workTime = -1;
        assertThatThrownBy(() -> Wage.builder().workTime(workTime).holidayPay(true).build())
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("empty Wage 생성 테스트")
    void emptyWageTest() {
        Wage empty = Wage.empty();

        assertThat(empty.getTotalAmount()).isEqualTo(Money.zero());
    }

    @Test
    @DisplayName("주가 같으면 같은 객체인지 테스트")
    void equalsTest() {
        Wage wage1 = Wage.builder().week(Week.WEEK3).build();
        Wage wage2 = Wage.builder().week(Week.WEEK3).build();

        assertTrue(wage1.equals(wage2));
        assertTrue(wage1.hashCode() == wage2.hashCode());
    }

    @Test
    @DisplayName("임금 객체 생성 테스트")
    void newWageTest() {
        Wage wage = Wage.builder()
            .week(Week.WEEK4)
            .member(new Member("tester"))
            .workTime(16)
            .holidayPay(true)
            .build();

        assertThat(wage.getTotalAmount()).isEqualTo(new Money(163776));
        assertThat(wage.getMember().getName()).isEqualTo("tester");
        assertThat(wage.getWeek()).isEqualTo(Week.WEEK4);
        assertThat(wage.getWorkTime()).isEqualTo(16);
    }
}