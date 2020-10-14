package cafeorder.web;

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

//    @NotNull
    private int hourlyWage;

    public int[] createTimeListForm() {
        int[] times = new int[5];
        times[0] = time1;
        times[1] = time2;
        times[2] = time3;
        times[3] = time4;
        times[4] = time5;
        return times;
    }

}
