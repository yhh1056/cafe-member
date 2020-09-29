package cafeorder.dto;

import cafeorder.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * author {yhh1056}
 * Create by {2020/09/29}
 */

@Getter
@Setter
@NoArgsConstructor
public class MemberDto {

    @NotEmpty(message = "이름은 반드시 존재해야 합니다.")
    private String name;

    public Member toEntity() {
        return new Member(name);
    }
}
