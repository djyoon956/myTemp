package kr.or.bit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.EmpDao;
import kr.or.bit.dao.EmpDao1;
import kr.or.bit.dto.Emp;

public class MemberEditService implements Action {
	private SqlSession sqlSession;
	@Autowired	
	public void setSqlsession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();

		int no = Integer.parseInt(request.getParameter("empno"));
		EmpDao  dao =sqlSession.getMapper(EmpDao.class);
		try {

			Emp emp = dao.getEmpByEmpno(no);

			request.setAttribute("emp", emp);
			forward.setPath("/WEB-INF/views/admin/MemberEdit.jsp");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}

}
