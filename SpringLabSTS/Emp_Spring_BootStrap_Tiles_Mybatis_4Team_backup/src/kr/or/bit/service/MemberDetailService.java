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
import sun.management.snmp.jvmmib.EnumJvmJITCompilerTimeMonitoring;

public class MemberDetailService implements Action {
	private SqlSession sqlSession;

	@Autowired
	public void setSqlsession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		Emp emp = new Emp();
		try {
			EmpDao dao = sqlSession.getMapper(EmpDao.class);
			emp = dao.getEmpByEmpno(Integer.parseInt(request.getParameter("empno")));
			request.setAttribute("empdetail", emp);

			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/views/admin/MemberDetail.jsp");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {

		}
		return forward;
	}
}
