package cafeorder.service;

import cafeorder.domain.Menu;
import cafeorder.repository.MenuRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

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
}