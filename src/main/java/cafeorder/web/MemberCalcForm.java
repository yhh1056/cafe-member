package cafeorder.web;

import cafeorder.domain.Member;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * author {yhh1056}
 * Create by {2020/09/30}
 */

@Getter
@Setter
public class MemberCalcForm {
    private Long memberId;

    private String name;

    private int time1;

    private int time2;

    private int time3;

    private int time4;

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
