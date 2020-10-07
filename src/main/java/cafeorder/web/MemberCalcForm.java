package cafeorder.web;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * author {yhh1056}
 * Create by {2020/09/30}
 */

@Getter
@Setter
public class MemberCalcForm {

    private String name;

    private int time1;

    private int time2;

    private int time3;

    private int time4;

    private int time5;

    @NotNull
    private int hourlyWage;

}
