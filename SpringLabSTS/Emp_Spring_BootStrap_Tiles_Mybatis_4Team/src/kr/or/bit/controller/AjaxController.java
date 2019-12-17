package kr.or.bit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.View;

import kr.or.bit.dao.EmpDao;
import kr.or.bit.dto.Emp;

@RestController
public class AjaxController {

	private SqlSession sqlSession;

	@Autowired
	public void setSqlsession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@RequestMapping("/GetSelectBoxData.do")
	public Map<String, Object> getMemberAddData() {
		EmpDao dao = sqlSession.getMapper(EmpDao.class);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deptnos", dao.getDethNos());
		map.put("jobs", dao.getJobRegister());
		map.put("emps", dao.getEmps());
		System.out.println("end set");
		return map;
	}

	@RequestMapping("/GetDeptNos.do")
	public List<Integer> getDeptnos() {
		EmpDao dao = sqlSession.getMapper(EmpDao.class);
		List<Integer> deptnos = dao.getDethNos();

		return deptnos;
	}

	@RequestMapping("/CheckEmpno.do")
	public boolean checkEmpno(int empno, ModelMap map) {
		EmpDao dao = sqlSession.getMapper(EmpDao.class);

		boolean isUse = dao.getEmpByEmpno(empno) != null;
		return isUse;
	}
}
