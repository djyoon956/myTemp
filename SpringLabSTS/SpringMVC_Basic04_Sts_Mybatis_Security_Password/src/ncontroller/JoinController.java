package ncontroller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.MemberService;
import vo.Member;

@Controller
@RequestMapping("/joinus/")
public class JoinController {
	@Autowired
	private MemberService memberService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	// 회원가입페이지
	@RequestMapping(value = "join.htm", method = RequestMethod.GET)
	public String join() {
		return "joinus.join"; // "join.jsp";
	}

	// 회원가입처리
	@RequestMapping(value = "join.htm", method = RequestMethod.POST)
	public String join(Member member) {
		System.out.println("join");

		int result = 0;
		try {
			member.setPwd(bCryptPasswordEncoder.encode(member.getPwd()));
			System.out.println(member.toString());
			result = memberService.insertMember(member);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result" + result);
		String view = null;
		if (result > 0)
			view = "redirect:/index.htm";
		else
			view = "redirect:/index.htm";

		return view;
	}

	@RequestMapping(value = "login.htm", method = RequestMethod.GET)
	public String login() {
		return "joinus.login"; // Tiles 적용
	}

	@RequestMapping(value = "login.htm", method = RequestMethod.POST)
	public String login(String username, String password) {
		System.out.println("login");
		int result = 0;
		try {
			result = memberService.loginCheck(username, password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String view = null;
		if (result > 0)
			view = "redirect:/index.htm";
		else
			view = "redirect:/login.htm";

		return view;
	}
}
