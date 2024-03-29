package hello.servlet.web.springmvc.v2;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
@RequestMapping("/springmvc/v2/members")    //메서드 레벨과 조합
public class SpringMemberControllerV2 {
    private MemberRepository memberRepository = MemberRepository.getInstance();


    @RequestMapping("/new-form")    //조합 결과: /springmvc/v2/members/new-form
    public ModelAndView newForm(){
        return new ModelAndView("new-form");
    }


    @RequestMapping("/save")    // /springmvc/v2/members/save
    public ModelAndView save(HttpServletRequest req, HttpServletResponse resp){
        String username = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelAndView mv = new ModelAndView("save-result");
        mv.addObject("member", member); //Model 데이터 추가
        return mv;
    }


    @RequestMapping     // /springmvc/v2/members
    public ModelAndView members() {
        List<Member> members = memberRepository.findAll();
        ModelAndView mv = new ModelAndView("members");
        mv.addObject("members", members);
        return mv;
    }

}
