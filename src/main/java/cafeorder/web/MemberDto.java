package cafeorder.web;

import cafeorder.domain.Member;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * author {yhh1056}
 * Create by {2020/09/29}
 */

@Getter
@Setter
public class MemberDto {
    private Long id;

    @NotBlank(message = "필수 입력 항목입니다")
    @Length(max = 7, message = "이름은 7글자 이내로 등록이 가능합니다.")
    @Pattern(regexp = "^[a-zA-Z가-힣]*$", message = "특수문자는 허용하지 않습니다.")
    private String name;

    public void addInfo(Member member) {
        this.id = member.getId();
        this.name = member.getName();
    }
}
