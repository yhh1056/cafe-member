package cafeorder.controller;

import cafeorder.domain.Menu;
import cafeorder.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * author {yhh1056}
 * Create by {2020/09/20}
 */
@Controller
public class OrderController {

    private final MenuService menuService;

    @Autowired
    public OrderController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/")
    public String list() {
        return "list";
    }

    @GetMapping("/write")
    public String add() {
        return "write";
    }


    /**
     * TODO : 숫자가 문자일 경우 예외처리
     */
    @PostMapping("/write")
    public String write(@RequestParam(value = "name") String name,
                        @RequestParam(value = "price") int price,
                        Model model) {
        Menu menu = new Menu(name, price);
        menuService.addMenu(menu);

        model.addAttribute("menus", menuService.getAllMenu());
        return "list";
    }
}
