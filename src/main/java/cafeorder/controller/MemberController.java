package cafeorder.controller;

import cafeorder.dto.MemberDto;
import cafeorder.service.MemberService;
import cafeorder.web.MemberForm;
import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

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
}
