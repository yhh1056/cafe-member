package cafeorder.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * author {yhh1056}
 * Create by {2020/09/28}
 */

class MenuTest {

    @Test
    @DisplayName("메뉴 종류 확인")
    void createMenu() {
        MenuType kind = MenuType.COFFEE;

        assertEquals(MenuType.valueOf("COFFEE"), kind);
    }

}