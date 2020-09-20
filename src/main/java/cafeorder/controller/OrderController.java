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
    public String admin() {
        return "admin";
    }

    @GetMapping("/write")
    public String write() {
        return "write";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("menus", menuService.getAllMenu());
        return "/list";
    }

    /**
     * TODO : 숫자가 문자일 경우 예외처리
     */
    @PostMapping("/admin")
    public String add(@RequestParam(value = "name") String name,
                        @RequestParam(value = "price") int price) {
        Menu menu = new Menu(name, price);
        menuService.addMenu(menu);

        return "/admin";
    }
}
