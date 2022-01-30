package cafeorder.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MoneyString {
    private static final DecimalFormat FORMATTER = new DecimalFormat("###,###");
    private static final String UNIT = "원";

    public static String of(int money) {
        return FORMATTER.format(money) + UNIT;
    }

    public static String of(BigDecimal money) {
        return FORMATTER.format(money.intValue()) + UNIT;
    }
}
