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
import java.util.ArrayList;
import java.util.List;
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

    @GetMapping("/info")
    public String updateForm(Model model) {
        MembersForm members = new MembersForm(memberService.getAll());
        model.addAttribute("members", members.getMembers());

        return "members/info";
    }

    @GetMapping("/form")
    public String create(Model model) {
        model.addAttribute(new MemberForm());
        return "members/form";
    }

    @PostMapping("/form")
    public String add(@Valid MemberForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "members/form";
        }
        MemberDto dto = new MemberDto(form.getName());
        memberService.add(dto.createMember());

        return "redirect:/";
    }

    @GetMapping("/{id}/update")
    public String updateMemberForm(@PathVariable("id") Long id, Model model) {
        MemberForm form = new MemberForm();
        form.addInfo(memberService.findOne(id));
        model.addAttribute("form", form);
        return "members/update";
    }

    @PostMapping("/{id}/update")
    public String updateMember(@PathVariable("id") Long id, @Valid MemberForm form,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "members/form";
        }
        memberService.updateMember(id, form);
        return "redirect:/members/info";
    }

    @PostMapping("/{id}/delete")
    public String deleteMember(@PathVariable("id") Long id) {
        memberService.delete(id);
        return "redirect:/members/info";
    }

    @GetMapping("/calc")
    public String calcForm(Model model) {
        MembersDto dto = new MembersDto(memberService.getAll());
        model.addAttribute("members", dto.getMembers());
        model.addAttribute("calcForm", new CalcForm());

        return "members/calc";
    }

    /**
     * TOdo : 뷰에서 예외 처리
     * 에러페이지 추가
     */
    @PostMapping("/calc")
    public String calc(@Valid CalcForm form, @RequestParam("memberId") Long id, BindingResult result) {
        if (result.hasErrors()) {
            return "members/calc";
        }

        memberService.addTime(id, form.createTimes());
        memberService.addWage(id, form.createChecks());
        return "redirect:/";
    }

    @GetMapping("/total")
    public String calcAll(Model model) {
        List<MemberListDto> membersDto = getMemberListDto();
        TotalDto dto = new TotalDto(memberService.getTotal());

        model.addAttribute("membersDto", membersDto);
        model.addAttribute("total", dto.getTotal());

        return "/members/total";
    }

    private List<MemberListDto> getMemberListDto() {
        List<MemberListDto> membersDto = new ArrayList<>();
        for (Member member : memberService.getAll()) {
            membersDto.add(new MemberListDto(member));
        }
        return membersDto;
    }
}
