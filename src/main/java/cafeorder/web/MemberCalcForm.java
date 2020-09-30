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

    @NotNull
    private int time;

    @NotNull
    private int hourlyWage;

}
