package cafeorder.domain;


import lombok.Getter;

/**
 * author {yhh1056}
 * Create by {2020/09/20}
 */
@Getter
public class Menu {
    private String name;
    private int price;

    public Menu(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
