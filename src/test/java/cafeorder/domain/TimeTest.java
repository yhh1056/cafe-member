package cafeorder.domain;

import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * author {yhh1056}
 * Create by {2020/10/07}
 */
class TimeTest {

    @Test
    void formatTime() {
        int test1 = 100000;
        String oneWeekWage;
        DecimalFormat formatter = new DecimalFormat("###,###");
        oneWeekWage = formatter.format(test1);

        assertEquals("100,000", oneWeekWage);
    }
}