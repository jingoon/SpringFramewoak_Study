package kr.co.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.domain.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	private final String NS = "kr.co.reply";
	
	@Override
	public void insert(ReplyVO vo) {
		sqlSession.insert(NS+".insert", vo);
	}

}
