package cafeorder.repository;

import cafeorder.domain.Menu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * author {yhh1056}
 * Create by {2020/09/20}
 */
@Repository
@RequiredArgsConstructor
public class MenuRepository {
    private final EntityManager em;

    public void save(Menu menu) {
        em.persist(menu);
    }

    public Menu findById(Long id) {
        return em.find(Menu.class, id);
    }

    public List<Menu> findAll() {
        List<Menu> menus = em.createQuery("SELECT m FROM Menu m", Menu.class).getResultList();
        em.close();

        return Collections.unmodifiableList(new ArrayList<>(menus));
    }

    public List<Menu> findByName(String name) {
        return em.createQuery("SELECT m FROM Menu m WHERE m.name = :name", Menu.class).
                setParameter("name", name)
                .getResultList();
    }
}
