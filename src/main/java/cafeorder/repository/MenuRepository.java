package cafeorder.repository;

import cafeorder.domain.Menu;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * author {yhh1056}
 * Create by {2020/09/20}
 */
@Repository
public class MenuRepository {
    private List<Menu> menus = new ArrayList<>();

    public void save(Menu menu) {
        menus.add(menu);
    }

    public List<Menu> getAll() {
        return Collections.unmodifiableList(new ArrayList<>(menus));
    }
}
