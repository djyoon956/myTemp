package service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MemberDao;
import vo.Member;

@Service
public class MemberService {

	@Autowired
	private SqlSession sqlsession;

	public Member getMember(String uid) throws ClassNotFoundException, SQLException {
		MemberDao dao = sqlsession.getMapper(MemberDao.class);
		return dao.getMember(uid);
	}

	public int insertMember(Member member) throws ClassNotFoundException, SQLException {
		MemberDao dao = sqlsession.getMapper(MemberDao.class);
		int result = 0;
		result = dao.insertMember(member);
		result = dao.insertRoll(member);
		return result;
	}

	public int updateMember(Member member) throws ClassNotFoundException, SQLException {
		MemberDao dao = sqlsession.getMapper(MemberDao.class);
		return dao.updateMember(member);
	}

	public int loginCheck(String username, String password) throws ClassNotFoundException, SQLException {
		MemberDao dao = sqlsession.getMapper(MemberDao.class);
		return dao.loginCheck(username, password);
	}
}
