package kr.co.repository;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.domain.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO{

	@Inject //connection인 SqlSession과 연결
	private SqlSession sqlSession; //DAO는 mybatis호출
	private final String NS ="kr.co.member"; // 동일 id(CRUD등)의 구분 
	@Override
	public String getTime() {
		return sqlSession.selectOne(NS+".getTime"); //mapper의 id
	}
	@Override
	public void insert(MemberVO vo) {
		sqlSession.insert(NS+".insert", vo);
	}
	@Override
	public List<MemberVO> list() {
		return sqlSession.selectList(NS+".list");
	}
	@Override
	public MemberVO read(String userId) {
		return sqlSession.selectOne(NS+".read", userId);
	}
	@Override
	public int update(MemberVO vo) {
		int successCount = sqlSession.update(NS+".update", vo);
		return successCount;
	}
	@Override
	public int delete(MemberVO vo) {
		int successCount = sqlSession.delete(NS+".delete", vo);
		return successCount;
	}



}
