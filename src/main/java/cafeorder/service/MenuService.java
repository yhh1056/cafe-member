package cafeorder.service;

import cafeorder.domain.Menu;
import cafeorder.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * author {yhh1056}
 * Create by {2020/09/20}
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    @Transactional
    public void addMenu(Menu menu) {
        isExistedMenu(menu);
        menuRepository.save(menu);
    }

    private void isExistedMenu(Menu menu) {
        List<Menu> menus = menuRepository.findByName(menu.getName());
        if (!menus.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 메뉴입니다.");
        }
    }

    public List<Menu> getAllMenu() {
        return menuRepository.findAll();
    }
}
