package kr.or.bit.controller;

import java.io.FileOutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.bit.dao.EmpDao;
import kr.or.bit.dto.Emp;

@Controller
public class RegisterController {

	private SqlSession sqlSession;

	@Autowired
	public void setSqlsession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@RequestMapping(value = "/Register.do", method = RequestMethod.GET)
	public String ShowView() {
		return "/WEB-INF/views/register/Register.jsp";
	}

	@RequestMapping(value = "/Register.do", method = RequestMethod.POST)
	public String register(Emp emp, HttpServletRequest request, Model model) {
		System.out.println("in ok");
		System.out.println(emp);
		emp.setHiredate(new Date());
		try {
			String uploadpath = request.getServletContext().getRealPath("upload");
			String imagefilename = emp.getMultipartFile().getOriginalFilename();
			String fpath = uploadpath + "\\" + imagefilename;

			if (!imagefilename.equals("")) { // 실 파일 업로드
				FileOutputStream fs = new FileOutputStream(fpath);
				fs.write(emp.getMultipartFile().getBytes());
				fs.close();
			}
			emp.setImagefilename(imagefilename);

			EmpDao dao = sqlSession.getMapper(EmpDao.class);
			emp.setImagefilename(imagefilename);
			int result = dao.insertEmp(emp);

			String msg = "";
			String url = "";
			if (result > 0) {
				msg = "등록 성공! 로그인 페이지로 이동합니다.";
				url = "Login.do";
			} else {
				msg = "등록 실패! 회원 가입 페이지로 재 이동합니다.";
				url = "Register.do";
			}

			model.addAttribute("board_msg", msg);
			model.addAttribute("board_url", url);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "/common/redirect.jsp";
	}
}
