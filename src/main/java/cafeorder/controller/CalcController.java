package cafeorder.controller;

import cafeorder.domain.Member;
import cafeorder.dto.MemberListDto;
import cafeorder.dto.MembersDto;
import cafeorder.dto.TotalDto;
import cafeorder.service.MemberService;
import cafeorder.web.CalcForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * author {yhh1056}
 * Create by {2020/10/15}
 */

@Controller
@RequiredArgsConstructor
public class CalcController {

    private final MemberService memberService;

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

        memberService.addTime(id, form.createTimes());
        memberService.addWage(id, form.createChecks());

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
