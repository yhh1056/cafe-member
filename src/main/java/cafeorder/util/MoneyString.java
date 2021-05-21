package cafeorder.util;

import java.text.DecimalFormat;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA
 * User: yhh1056@naver.com
 * Date: 2021/05/21 Time: 9:23 오후
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MoneyString {

    private static final DecimalFormat FORMATTER = new DecimalFormat("###,###");
    private static final String UNIT = "원";

    public static String of(int money) {
        return FORMATTER.format(money) + UNIT;
    }
}
