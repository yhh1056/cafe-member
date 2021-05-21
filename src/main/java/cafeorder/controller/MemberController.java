package cafeorder.controller;

import cafeorder.service.MemberService;
import cafeorder.web.MemberDto;
import cafeorder.web.WageDto;
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

    private final MemberService memberService;

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
        memberService.addMember(memberDto);
        return "redirect:/";
    }

    @GetMapping("/info")
    public String updateForm(Model model) {
        model.addAttribute("members", memberService.getAllName());

        return "members/info";
    }

    @GetMapping("/{id}/update")
    public String updateMemberForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("memberDto", memberService.getBy(id));
        return "members/update";
    }

    @PostMapping("/{id}/update")
    public String updateMember(@PathVariable("id") Long id, @Valid MemberDto form,
            BindingResult result) {
        if (result.hasErrors()) {
            return "members/form";
        }
        memberService.updateMember(id, form);
        return "redirect:/members/info";
    }

    @PostMapping("/{id}/delete")
    public String deleteMember(@PathVariable("id") Long id) {
        memberService.deleteMember(id);
        return "redirect:/members/info";
    }

    @GetMapping("/wage")
    public String calcForm(Model model) {
        model.addAttribute("members", memberService.getAllName());
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

        memberService.addWage(id, wageDto);
        return "redirect:/";
    }

    @GetMapping("/total")
    public String total(Model model) {
        model.addAttribute("members", memberService.getAll());
        model.addAttribute("total", memberService.getTotal());

        return "/members/total";
    }

}
