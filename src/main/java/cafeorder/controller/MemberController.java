package cafeorder.controller;

import cafeorder.domain.Member;
import cafeorder.dto.MemberDto;
import cafeorder.dto.MemberListDto;
import cafeorder.service.MemberService;
import cafeorder.web.MemberCalcForm;
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

    @PostMapping("/members/member/{id}/update")
    public String updateMember(@PathVariable("id") Long id,
                               @Valid MemberForm form,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "/member/createMemberForm";
        }
        memberService.updateMember(form);
        return "redirect:/members";
    }

    @GetMapping("/members/member/{id}/delete")
    public String deleteMember(@PathVariable("id") Long id) {
        memberService.delete(id);
        return "redirect:/members";
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

        return "redirect:/";
    }

    @GetMapping("/member/members")
    public String calcAll(Model model) {
        List<Member> members = memberService.getAll();
        List<MemberListDto> membersDto = new ArrayList<>();
        for (Member member : members) {
            membersDto.add(new MemberListDto(member));
        }

        model.addAttribute("membersDto", membersDto);

        return "/member/members";
    }
}
