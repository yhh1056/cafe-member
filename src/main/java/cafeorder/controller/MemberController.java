package cafeorder.controller;

import cafeorder.dto.MemberNameRequest;
import cafeorder.dto.MemberSaveForm;
import cafeorder.dto.WageRequest;
import cafeorder.service.MemberService;
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

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/form")
    public String create(Model model) {
        model.addAttribute("member", new MemberSaveForm());
        return "members/form";
    }

    @PostMapping("/form")
    public String add(@Valid MemberNameRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return "members/form";
        }

        memberService.addMember(request.getName());
        return "redirect:/";
    }

    @GetMapping("/info")
    public String updateForm(Model model) {
        model.addAttribute("members", memberService.getAllName());
        return "members/info";
    }

    @GetMapping("/{id}/form")
    public String updateMemberForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("member", memberService.getById(id));
        return "members/update";
    }

    @PostMapping("/{id}/form")
    public String updateMember(@PathVariable("id") Long id, @Valid MemberNameRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return "members/form";
        }
        memberService.updateMember(id, request.getName());
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
        model.addAttribute("wage", new WageRequest());
        return "members/wage";
    }

    @PostMapping("/wage")
    public String wage(Model model, @Valid WageRequest wageRequest, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("members", memberService.getAllName());
            model.addAttribute(wageRequest);
            return "members/wage";
        }

        memberService.addWage(wageRequest);
        return "redirect:/";
    }

    @GetMapping("/total")
    public String total(Model model) {
        model.addAttribute("members", memberService.findAll());
        return "/members/total";
    }

}
