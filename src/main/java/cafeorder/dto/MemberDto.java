package cafeorder.dto;

import cafeorder.domain.Member;
import cafeorder.domain.Time;
import cafeorder.domain.Wage;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * author {yhh1056}
 * Create by {2020/09/29}
 */

@Getter
@NoArgsConstructor
public class MemberDto {

    @NotEmpty(message = "이름은 반드시 존재해야 합니다.")
    private String name;

    public MemberDto(String name) {
        this.name = name.trim();
    }

    public Member createMember() {
        return new Member(name);
    }
}
