package cafeorder.controller;

import cafeorder.dto.MemberDto;
import cafeorder.service.MemberService;
import cafeorder.web.MemberForm;
import cafeorder.web.MembersForm;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("/members/update/{id}")
    public String updateMember(@PathVariable("id") Long id, @Valid MemberForm form,
                               BindingResult result) {
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
