package hello.hellospring.controller;

import hello.hellospring.dto.MemberFindResponse;
import hello.hellospring.dto.MemberJoinRequest;
import hello.hellospring.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public String list(Model model) {
        List<MemberFindResponse> members = memberService.findMembers();

        model.addAttribute("members", members);
        return "members/list";
    }

    @GetMapping("new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("new")
    public String create(MemberJoinRequest memberJoinRequest) {
        memberService.join(memberJoinRequest);

        return "redirect:/";
    }

}
