package kr.or.bit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.EmpDao;

public class LoginService implements Action {
	
	private SqlSession sqlSession;
	@Autowired	
	public void setSqlsession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		try {
			String userid = request.getParameter("userid");
			String pwd = request.getParameter("pwd");

			EmpDao  dao =sqlSession.getMapper(EmpDao.class);
			String adminId = dao.checkAdminLogin(userid, pwd);
			
			if (adminId != null && !adminId.isEmpty()) {
				request.getSession().setAttribute("userid", userid);
				forward.setPath("index.jsp");
			}
			else 
				forward.setPath("Login.do");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return forward;
	}
}
