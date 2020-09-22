package cafeorder.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * author {yhh1056}
 * Create by {2020/09/20}
 */

@Entity
@NoArgsConstructor
public class Menu {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "menu_name")
    @Getter
    private String name;

    @Getter
    private int price;

    public Menu(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
