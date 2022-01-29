package cafeorder.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
public class MemberNameRequest {

    @NotBlank(message = "필수 입력 항목입니다")
    @Length(max = 7, message = "이름은 7글자 이내로 등록이 가능합니다.")
    @Pattern(regexp = "^[a-zA-Z가-힣]*$", message = "특수문자는 허용하지 않습니다.")
    private String name;
}
