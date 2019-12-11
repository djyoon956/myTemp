package com.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.model.MemberDAO;

/*
 *  로그인 화면 : GET 요청 > loginForm
 *  로그인 처리 : POST 요청 > id, pwd > memberCheck 검증 > loginSuccess(빈페이지)
 *  
 *  조건 : 인증 성공하면 session 담기 > [기존 servlet 과 동일]
 * 
 *  public void test(HttpSession session){
 *    session.setAttribute("USERID", "hong")
 *  }
 * 
 */
@Controller
public class LoginController {
	private MemberDAO memberdao;

	@Autowired  //by type
	public void setMemberdao(MemberDAO memberdao) {
		this.memberdao = memberdao;
	}
	
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String form() {
		return "loginForm";
	}
	
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String submit(@RequestParam(value = "id", required = true)String id
								,@RequestParam(value = "pwd", required = true)String pwd
								, HttpSession session) throws SQLException {
		String view = null;
		boolean result = memberdao.memberCheck(id, pwd);
		if (result) {
			view = "loginSuccess";
			session.setAttribute("USERID", id);
		}
		else
			view = "loginForm";
		
		return view;
	}
}











