package cafeorder.repository;

import cafeorder.domain.Menu;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * author {yhh1056}
 * Create by {2020/09/20}
 */
@Repository
public class MenuRepository {
    @PersistenceContext
    EntityManager em;
    private List<Menu> menus = new ArrayList<>();

    public void save(Menu menu) {
        em.persist(menu);
        em.flush();
        em.close();
    }

    public List<Menu> findAll() {
        List<Menu> menus = em.createQuery("select m from Menu m", Menu.class).getResultList();
        em.close();

        return Collections.unmodifiableList(new ArrayList<>(menus));
    }
}
