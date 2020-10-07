package cafeorder.service;

import cafeorder.domain.MenuType;
import cafeorder.repository.MenuRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * author {yhh1056}
 * Create by {2020/09/20}
 * <p>
 * TODO : 테스트 코드 수정
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class MenuServiceTests {
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private MenuService menuService;

    @Test
    @DisplayName("메뉴 추가기능")
    void addMenu() {
        Menu menu = createMenu();

        menuService.addMenu(menu);
        assertEquals(1, menuRepository.findAll().size());
    }

    @Test
    @DisplayName("이미 존재하는 메뉴를 등록하는 경우")
    void isExistedMenu() {
        Menu menu = createMenu();
        menuService.addMenu(menu);

        Menu equalsMenu = createMenu();
        assertThrows(IllegalStateException.class,
                () -> menuService.addMenu(equalsMenu));
    }

    private Menu createMenu() {
        String name = "아메리카노";
        int price = 4000;
        MenuType kind = MenuType.COFFEE;

        return new Menu(name, price, kind);
    }
}
