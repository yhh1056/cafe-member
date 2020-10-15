package cafeorder.controller;

import cafeorder.domain.Member;
import cafeorder.dto.MemberDto;
import cafeorder.dto.MemberListDto;
import cafeorder.dto.MembersDto;
import cafeorder.dto.TotalDto;
import cafeorder.service.MemberService;
import cafeorder.web.CalcForm;
import cafeorder.web.MemberForm;
import cafeorder.web.MembersForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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
        MemberDto dto = new MemberDto(form.getName());
        memberService.add(dto.createMember());

        return "redirect:/";
    }

    @GetMapping("/members/management")
    public String updateForm(Model model) {
        MembersForm members = new MembersForm(memberService.getAll());
        model.addAttribute("members", members.getMembers());

        return "/member/listForm";
    }

    @GetMapping("/members/member/{id}/update")
    public String updateMemberForm(@PathVariable("id") Long id, Model model) {
        MemberForm form = new MemberForm();
        form.addInfo(memberService.findOne(id));
        model.addAttribute("form", form);
        return "/member/updateForm";
    }

    @PostMapping("/members/update")
    public String updateMember(@Valid MemberForm form,
                               BindingResult result,
                               @RequestParam("memberId") Long id) {
        if (result.hasErrors()) {
            return "/member/createMemberForm";
        }
        memberService.updateMember(id, form);
        return "redirect:/member/members";
    }

    @GetMapping("/members/member/{id}/delete")
    public String deleteMember(@PathVariable("id") Long id) {
        memberService.delete(id);
        return "redirect:/member/members";
    }
}
