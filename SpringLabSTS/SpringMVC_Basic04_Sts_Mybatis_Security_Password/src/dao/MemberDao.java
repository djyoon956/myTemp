package dao;

import java.sql.SQLException;
import vo.Member;

public interface MemberDao {

	// 회원정보 얻기
	public Member getMember(String uid) throws ClassNotFoundException, SQLException;

	// 회원가입
	public int insertMember(Member member) throws ClassNotFoundException, SQLException;
	public int insertRoll(Member member) throws ClassNotFoundException, SQLException;

	// 회원 수정
	public int updateMember(Member member) throws ClassNotFoundException, SQLException;

	// 로그인 체크
	public int loginCheck(String username, String password) throws ClassNotFoundException, SQLException;

	// 회원 ID 검증 (비동기)

}
