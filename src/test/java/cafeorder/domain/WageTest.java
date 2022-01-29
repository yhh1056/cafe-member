package cafeorder.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class WageTest {

    @Test
    @DisplayName("기본 임금 계산")
    void wageTest() {
        int workTime = 12;
        Wage wage = Wage.builder().workTime(workTime).isHolidayPay(false).build();
        assertThat(wage.getTotalAmount()).isEqualTo(new Money(Wage.MINIMUM_WAGE * workTime));
    }

    @Test
    @DisplayName("주휴 수당 계산")
    void holidayPayTest() {
        int workTime = 12;
        Wage wage = Wage.builder().workTime(workTime).isHolidayPay(true).build();
        assertThat(wage.getTotalAmount()).isEqualTo(new Money(Wage.MINIMUM_WAGE * workTime * 1.2));
    }

    @Test
    @DisplayName("주 40시간 넘게 근무시 예외 발생")
    void overWorkTimeTest() {
        int workTime = 41;
        assertThatThrownBy(() -> Wage.builder().workTime(workTime).isHolidayPay(true).build())
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("근무시간을 음수로 입력시 예외 발생")
    void underWorkTimeTest() {
        int workTime = -1;
        assertThatThrownBy(() -> Wage.builder().workTime(workTime).isHolidayPay(true).build())
            .isInstanceOf(IllegalArgumentException.class);
    }
}