package cafeorder.web;

import cafeorder.domain.MenuType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * author {yhh1056}
 * Create by {2020/09/29}
 */
@Getter @Setter
public class MenuForm {

    @NotEmpty(message = "필수 입력 항목입니다")
    private String name;

    @NotNull(message = "필수 입력 항목입니다")
    private int price;

    private MenuType menuType;
}
