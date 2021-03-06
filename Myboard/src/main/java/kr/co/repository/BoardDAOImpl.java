package kr.co.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO{

	@Autowired
	private SqlSession sqlSession;
	
	private final String NS= "kr.co.board";
	
	@Override
	public List<BoardVO> list() {
		// TODO Auto-generated method stub
		return sqlSession.selectList(NS+".list");
	}

	@Override
	public BoardVO read(String bno) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NS+".read", bno);
	}

}
