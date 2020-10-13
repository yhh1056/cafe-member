package cafeorder.controller;

import cafeorder.domain.Member;
import cafeorder.dto.MemberDto;
import cafeorder.dto.MembersDto;
import cafeorder.service.MemberService;
import cafeorder.web.MemberCalcForm;
import cafeorder.web.MemberForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
        MembersDto dto = new MembersDto(memberService.getAll());
        model.addAttribute("memberCalcForm", new MemberCalcForm());
        model.addAttribute("members", dto.getMembers());

        return "/member/createCalcMemberForm";
    }

    @PostMapping("/member/calc")
    public String calc(@ModelAttribute("memberCalcForm") MemberCalcForm form,
                       @RequestParam("memberId") Long id) {
        memberService.addTime(id, form.createTimeListForm());

        return "/member/calcOk";
    }

    @GetMapping("/member/members")
    public String calcAll(Model model) {
        List<Member> members = memberService.getAll();

        model.addAttribute("members", members);

        return "/member/members";
    }
}
