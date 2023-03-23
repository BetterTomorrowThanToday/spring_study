package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller //spring container에서 spring bean을 관리 -> 컴포넌트 스캔으로 다 가져와서 스프링빈으로 등록 // 스캔 대상은 어플리케이션에 소속(?) 패키지 하위 항목들
public class MemberController {
    private final MemberService memberService;

    @Autowired //생성자로 해야 중복 생성하지 않고 생성 가능 //@Autowired: 컨테이너 안에 스프링빈들을 엮어줌 -> 의존성 주입
    public MemberController(MemberService memberService) {
        //MemberServie는 annotation이 없어서 자동으로 관리 ㄴ, 그래서 구현체로 가서 annotation(->@Service) 해야함
        this.memberService = memberService;
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
