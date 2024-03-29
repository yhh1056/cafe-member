package cafeorder.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WageRequest {

    @NotNull
    private Long memberId;

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

}
