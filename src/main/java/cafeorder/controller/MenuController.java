package cafeorder.controller;

import cafeorder.domain.Menu;
import cafeorder.domain.MenuType;
import cafeorder.dto.MenuDto;
import cafeorder.service.MenuService;
import cafeorder.web.MenuForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.lang.reflect.Member;
import java.util.List;

/**
 * author {yhh1056}
 * Create by {2020/09/20}
 */
@Controller
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    @GetMapping("/menu/new")
    public String create(Model model) {
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

        /**
         * 이렇게 쓰는게 맞는지 알아볼 것
         */
        MenuDto dto = new MenuDto();
        dto.setName(form.getName());
        dto.setPrice(form.getPrice());
        dto.setMenuType(form.getMenuType());

        menuService.addMenu(dto.toEntity());

        return "redirect:/";
    }

    @GetMapping("/menu/menus")
    public String list(Model model) {
        MenuDto dto = new MenuDto();
        dto.setMenus(menuService.getAllMenu());
        model.addAttribute("menus", dto.getMenus());
        return "menu/list";
    }
}