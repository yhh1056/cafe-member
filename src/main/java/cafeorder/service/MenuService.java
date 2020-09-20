package cafeorder.service;

import cafeorder.domain.Menu;
import cafeorder.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author {yhh1056}
 * Create by {2020/09/20}
 */
@Service
public class MenuService {

    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public void addMenu(Menu menu) {
        menuRepository.save(menu);
    }

    public List<Menu> getAllMenu() {
        return menuRepository.getAll();
    }
}
