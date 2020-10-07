package cafeorder.controller;

import cafeorder.domain.Member;
import cafeorder.dto.MemberDto;
import cafeorder.service.MemberService;
import cafeorder.web.MemberCalcForm;
import cafeorder.web.MemberForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

/**
 * author {yhh1056}
 * Create by {2020/09/29}
 */

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/new")
    public String create(Model model) {
        model.addAttribute(new MemberForm());
        return "/member/createMemberForm";
    }

    @PostMapping("/member/new")
    public String add(@Valid MemberForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "/member/createMemberForm";
        }
        MemberDto dto = new MemberDto();
        dto.setName(form.getName());

        memberService.add(dto.toEntity());

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.getAll();
        model.addAttribute("members", members);
        for (Member m : members) {
            if (m.getTime() == null) {
                return "redirect:/";
            }
        }

        return "/member/list";
    }

    /**
     * DTO로 바꿀것
     */
    @GetMapping("/member/calc")
    public String input(Model model) {
        List<Member> list = memberService.getAll();
        model.addAttribute("members", list);
        model.addAttribute("memberCalcForm", new MemberCalcForm());

        return "/member/createCalcMemberForm";
    }

    @PostMapping("/member/calc")
    public String calc(MemberCalcForm form) {
        String name = form.getName();
        int hourlyWage = form.getHourlyWage();
        memberService.addTime(name, hourlyWage, form);

        return "redirect:/";
    }

    @GetMapping("/member/members")
    public String calcAll(Model model) {
        List<Member> members = memberService.getAll();

        model.addAttribute("members", members);

        return "/member/members";
    }
}
