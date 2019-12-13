package kr.or.bit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.EmpDao;
import kr.or.bit.dao.EmpDao1;

public class MemberDeleteService implements Action {
	private SqlSession sqlSession;
	@Autowired	
	public void setSqlsession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		int empno = Integer.parseInt(request.getParameter("empno"));
		
		EmpDao  dao =sqlSession.getMapper(EmpDao.class);
		int row = dao.deleteEmpByEmpno(empno);		
		
		String url = "";
		String msg ="";
		if(row > 0){
			url="MemberList.do";
			msg= empno + "님이 삭제되었습니다";
		} 
	  	request.setAttribute("board_msg", msg);
	  	request.setAttribute("board_url", url);
	
		forward.setRedirect(false);
		forward.setPath("/common/redirect.jsp");

		return forward;
	}
}
