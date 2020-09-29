package cafeorder.dto;

import cafeorder.domain.Menu;
import cafeorder.domain.MenuType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * author {yhh1056}
 * Create by {2020/09/29}
 */
@Getter
@Setter
@NoArgsConstructor
public class MenuDto {
    private String name;
    private int price;
    private MenuType menuType;
    private List<Menu> menus;

    public Menu toEntity() {
        return new Menu(name, price, menuType);
    }

}
