package cafeorder.web;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * author {yhh1056}
 * Create by {2020/09/29}
 */

@Getter
@Setter
public class MemberForm {

    private Long id;

    @NotEmpty(message = "필수 입력 항목입니다")
    private String name;

    private int time1;

    private int time2;

    private int time3;

    private int time4;

    private int time5;
}
