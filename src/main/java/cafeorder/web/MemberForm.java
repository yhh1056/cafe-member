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

    @NotEmpty(message = "필수 입력 항목입니다")
    private String name;
}
