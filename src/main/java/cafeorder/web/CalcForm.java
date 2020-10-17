package cafeorder.web;

import cafeorder.domain.Time;
import cafeorder.domain.Wage;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

/**
 * author {yhh1056}
 * Create by {2020/09/30}
 */

@Getter
@Setter
public class CalcForm {
    private Long memberId;

    private String name;

    @Max(value = 52, message = "52시간을 넘길 수 없습니다.")
    @NotNull(message = "유효하지 않은 입력입니다.")
    @PositiveOrZero(message = "유효하지 않은 입력입니다.")
    private int time1;

    @Max(value = 52, message = "52시간을 넘길 수 없습니다.")
    @NotNull(message = "유효하지 않은 입력입니다.")
    @PositiveOrZero(message = "유효하지 않은 입력입니다.")
    private int time2;

    @Max(value = 52, message = "52시간을 넘길 수 없습니다.")
    @NotNull(message = "유효하지 않은 입력입니다.")
    @PositiveOrZero(message = "유효하지 않은 입력입니다.")
    private int time3;

    @Max(value = 52, message = "52시간을 넘길 수 없습니다.")
    @NotNull(message = "유효하지 않은 입력입니다.")
    @PositiveOrZero(message = "유효하지 않은 입력입니다.")
    private int time4;

    @Max(value = 52, message = "52시간을 넘길 수 없습니다.")
    @NotNull(message = "유효하지 않은 입력입니다.")
    @PositiveOrZero(message = "유효하지 않은 입력입니다.")
    private int time5;

    private boolean check1;
    private boolean check2;
    private boolean check3;
    private boolean check4;
    private boolean check5;

    //    @NotNull
    private int hourlyWage;

    public Time createTimes() {
        int[] times = new int[5];
        times[0] = time1;
        times[1] = time2;
        times[2] = time3;
        times[3] = time4;
        times[4] = time5;
        return new Time(times);
    }

    public Wage createChecks() {
        boolean[] checks = new boolean[5];
        checks[0] = check1;
        checks[1] = check2;
        checks[2] = check3;
        checks[3] = check4;
        checks[4] = check5;
        return new Wage(checks);
    }
}
