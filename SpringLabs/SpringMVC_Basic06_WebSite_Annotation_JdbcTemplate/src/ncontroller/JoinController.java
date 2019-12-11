package ncontroller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.MemberDao;
import vo.Member;

@Controller
@RequestMapping("/joinus/")
public class JoinController {
	private MemberDao memberDao;

	@Autowired
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@RequestMapping(value = "join.htm", method = RequestMethod.GET)
	public String join() {
		return "join.jsp";
	}

	@RequestMapping(value = "join.htm", method = RequestMethod.POST)
	public String join(Member member) {
		System.out.println(member);
		try {
			memberDao.insert(member);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "redirect:/index.htm";
	}
}
