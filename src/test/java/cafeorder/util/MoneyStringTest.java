package cafeorder.util;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("MoneyString 테스트")
class MoneyStringTest {

    @Test
    @DisplayName("int형 한화 기준으로 스트링 변경")
    void intToWon() {
        String result = MoneyString.of(123456789);

        assertThat(result).isEqualTo("123,456,789원");
    }

    @Test
    @DisplayName("bigDecimal 한화 기준으로 스트링 변경")
    void bigDecimalToWon() {
        String result = MoneyString.of(BigDecimal.valueOf(123456789));

        assertThat(result).isEqualTo("123,456,789원");
    }
}