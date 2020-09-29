package cafeorder.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * author {yhh1056}
 * Create by {2020/09/20}
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "menu_name")
    private String name;

    private int price;

    @Enumerated(EnumType.STRING)
    private MenuType menuType;

    public Menu(String name, int price, MenuType menuType) {
        this.name = name;
        this.price = price;
        this.menuType = menuType;
    }

    public boolean equals(Menu menu) {
        return menu.getName().equals(this.name);
    }
}
