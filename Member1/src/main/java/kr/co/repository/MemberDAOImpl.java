package kr.co.repository;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements MemberDAO{

	@Inject //connection인 SqlSession과 연결
	private SqlSession sqlSession; //DAO는 mybatis호출
	private final String NS ="kr.co.member"; // 동일 id(CRUD등)의 구분 
	@Override
	public String getTime() {
		return sqlSession.selectOne(NS+".getTime"); //mapper의 id
	}

}
