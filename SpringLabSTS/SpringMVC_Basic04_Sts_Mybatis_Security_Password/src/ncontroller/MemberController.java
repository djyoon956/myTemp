package ncontroller;

import java.security.Principal;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.MemberService;
import vo.Member;

@Controller
@RequestMapping("/member/")
public class MemberController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@RequestMapping(value = "memberpage.htm", method = RequestMethod.GET)
	public String memberCheck() {
		return "member.memberCheck";
	}

	@RequestMapping(value = "memberpage.htm", method = RequestMethod.POST)
	public String memberCheck(String password, Principal principal) {
		String viewpage = "";
		System.out.println(principal.getName());
		// 회원정보
		Member member = null;
		try {
			member = memberService.getMember(principal.getName());
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("pwd" + password);
		boolean result = bCryptPasswordEncoder.matches(password, member.getPwd());
		System.out.println("result" + result);
		if (result)
			viewpage = "redirect:memberinfo.htm";
		else
			viewpage = "redirect:membercheck.htm";

		return viewpage;
	}

	@RequestMapping(value = "memberinfo.htm", method = RequestMethod.GET)
	public String memberPage(Model model, Principal principal) {
		Member member = null;
		try {
			member = memberService.getMember(principal.getName());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		model.addAttribute("member", member);

		return "member.memberPage";
	}

	@RequestMapping(value = "memberedit.htm", method = RequestMethod.GET)
	public String memberEdit(Member member, Model model) {
		model.addAttribute("member", member);
		return "member.memberEdit";
	}

	@RequestMapping(value = "memberedit.htm", method = RequestMethod.POST)
	public String memberEdit(Model model, Member member, Principal principal) {
		int result = 0;
		try {
			member.setPwd(bCryptPasswordEncoder.encode(member.getPwd()));
			result = memberService.updateMember(member);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		String view = null;
		if (result > 0) {
			view = "redirect:memberedit.htm";
		} else {
			view = "redirect:membercheck.htm";
		}

		return view;
	}
}
