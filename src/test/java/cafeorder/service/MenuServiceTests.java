package cafeorder.service;

import cafeorder.domain.Menu;
import cafeorder.repository.MenuRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * author {yhh1056}
 * Create by {2020/09/20}
 */
class MenuServiceTests {
    @MockBean
    private MenuRepository menuRepository = new MenuRepository();

    @Test
    @DisplayName("메뉴 추가기능")
    void addMenu() {
        Menu menu = new Menu("아메리카노", 4000);
        menuRepository.save(menu);

        assertEquals(1, menuRepository.getAll().size());
    }

    @Test
    @DisplayName("전체메뉴가져오기")
    void getAllMenu() {
        Menu menu1 = new Menu("아메리카노", 4000);
        Menu menu2 = new Menu("아메리카노", 4000);
        Menu menu3 = new Menu("아메리카노", 4000);

        menuRepository.save(menu1);
        menuRepository.save(menu2);
        menuRepository.save(menu3);

        assertEquals(3, menuRepository.getAll().size());
    }

    @Test
    @DisplayName("메뉴리스트가 null일 경우")
    void MenusIsNull() {
        List<Menu> isNullMenus = menuRepository.getAll();
        assertEquals(0, isNullMenus.size());
    }
}