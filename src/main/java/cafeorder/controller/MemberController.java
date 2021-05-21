package cafeorder.controller;

import cafeorder.service.MemberSaveService;
import cafeorder.service.MemberViewService;
import cafeorder.dto.MemberDto;
import cafeorder.dto.WageDto;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * author {yhh1056}
 * Create by {2020/09/29}
 */

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberSaveService memberSaveService;
    private final MemberViewService memberViewService;

    @GetMapping("/form")
    public String create(Model model) {
        model.addAttribute(new MemberDto());
        return "members/form";
    }

    /**
     * todo : 예외처리
     */
    @PostMapping("/form")
    public String add(@Valid MemberDto memberDto, BindingResult result) {
        if (result.hasErrors()) {
            return "members/form";
        }
        memberSaveService.addMember(memberDto);
        return "redirect:/";
    }

    @GetMapping("/info")
    public String updateForm(Model model) {
        model.addAttribute("members", memberViewService.getAllName());

        return "members/info";
    }

    @GetMapping("/{id}/update")
    public String updateMemberForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("memberDto", memberViewService.getBy(id));
        return "members/update";
    }

    @PostMapping("/{id}/update")
    public String updateMember(@PathVariable("id") Long id, @Valid MemberDto form,
            BindingResult result) {
        if (result.hasErrors()) {
            return "members/form";
        }
        memberSaveService.updateMember(id, form);
        return "redirect:/members/info";
    }

    @PostMapping("/{id}/delete")
    public String deleteMember(@PathVariable("id") Long id) {
        memberSaveService.deleteMember(id);
        return "redirect:/members/info";
    }

    @GetMapping("/wage")
    public String calcForm(Model model) {
        model.addAttribute("members", memberViewService.getAllName());
        model.addAttribute("wageDto", new WageDto());
        return "members/wage";
    }

    /**
     * TOdo : 뷰에서 예외 처리
     * 에러페이지 추가
     */
    @PostMapping("/wage")
    public String wage(@RequestParam("memberId") Long id, @Valid WageDto wageDto, BindingResult result) {
        if (result.hasErrors()) {
            return "members/wage";
        }

        memberSaveService.addWage(id, wageDto);
        return "redirect:/";
    }

    @GetMapping("/total")
    public String total(Model model) {
        model.addAttribute("members", memberViewService.getAll());
        model.addAttribute("total", memberViewService.getTotal());

        return "/members/total";
    }

}
