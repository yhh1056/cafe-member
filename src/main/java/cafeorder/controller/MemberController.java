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

    @GetMapping("/member/calc")
    public String calcForm(Model model) {
        MembersDto dto = new MembersDto(memberService.getAll());
        model.addAttribute("members", dto.getMembers());
        model.addAttribute("calcForm", new CalcForm());

        return "/member/createCalcForm";
    }

    /**
     * TOdo : 뷰에서 예외 처리
     * 에러페이지 추가
     */
    @PostMapping("/member/calc")
    public String calc(@Valid CalcForm form,
                       @RequestParam("memberId") Long id,
                       BindingResult result) {
        if (result.hasErrors()) {
            return "/member/createCalcForm";
        }
        memberService.addTime(id, form.createTimeListForm());

        return "redirect:/";
    }

    @GetMapping("/member/members")
    public String calcAll(Model model) {
        List<MemberListDto> membersDto = getMemberListDto();
        TotalDto dto = new TotalDto(memberService.getTotal());

        model.addAttribute("membersDto", membersDto);
        model.addAttribute("total", dto.getTotal());

        return "/member/members";
    }

    private List<MemberListDto> getMemberListDto() {
        List<MemberListDto> membersDto = new ArrayList<>();
        for (Member member : memberService.getAll()) {
            membersDto.add(new MemberListDto(member));
        }
        return membersDto;
    }
}
