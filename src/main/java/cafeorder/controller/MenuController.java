package cafeorder.controller;

import cafeorder.domain.Menu;
import cafeorder.domain.MenuType;
import cafeorder.service.MenuService;
import cafeorder.web.MenuForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * author {yhh1056}
 * Create by {2020/09/20}
 */
@Controller
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    @GetMapping("/menu/new")
    public String list(Model model) {
        model.addAttribute("menuForm", new MenuForm());
        return "menu/createMenuForm";
    }

    /**
     * TODO : 숫자가 문자일 경우 예외처리
     * 스위치문 리팩토링
     */
    @PostMapping("/menu/new")
    public String add(@Valid MenuForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "menu/createMenuForm";
        }
        String name = form.getName();
        int price = form.getPrice();
        MenuType menuType = form.getMenuType();

        System.out.println(name);
        System.out.println(menuType);
        menuService.addMenu(new Menu(name, price, menuType));

        return "redirect:/";
    }
}
