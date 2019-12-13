package kr.or.bit.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.EmpDao;
import kr.or.bit.dao.EmpDao1;
import kr.or.bit.dto.Emp;


public class MemberListService implements Action{

	private SqlSession sqlSession;
	@Autowired	
	public void setSqlsession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		try {
			EmpDao  dao =sqlSession.getMapper(EmpDao.class);
			List<Emp> emplist = dao.getEmps();
			request.setAttribute("emplist", emplist);
			
			forward = new ActionForward();
			forward.setRedirect(false); //forward 방식
		  	forward.setPath("/WEB-INF/views/admin/MemberList.jsp");
		  	
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			
		}
		return forward;
	}
}
