package cafeorder.domain;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Money 테스트")
public class MoneyTest {

    @Test
    @DisplayName("double형 생성")
    void newDoubleTest() {
        Money money = new Money(1000.00);

        assertThat(money.toString()).isEqualTo("1,000원");
    }

    @Test
    @DisplayName("BigDecimal 생성")
    void newBigDecimalTest() {
        Money money = new Money(BigDecimal.valueOf(1000.0));

        assertThat(money.toString()).isEqualTo("1,000원");
    }

    @Test
    @DisplayName("기본 0원 머니 객체 생성")
    void newZeroMoneyTest() {
        Money zero = Money.zero();

        assertThat(zero.toString()).isEqualTo("0원");
    }

    @Test
    @DisplayName("금액 더하기 테스트")
    void plusMoneyTest() {
        Money money1 = new Money(1000);
        Money money2 = new Money(1000);

        Money plus = money1.plus(money2);

        assertThat(plus.toString()).isEqualTo("2,000원");
    }
}
