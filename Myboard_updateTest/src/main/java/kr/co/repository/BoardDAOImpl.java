package kr.co.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.domain.BoardVO;
import kr.co.domain.PageTO;
import kr.co.domain.ReplyVO;

@Repository
public class BoardDAOImpl implements BoardDAO{

	@Autowired
	private SqlSession sqlSession;
	
	private final String NS= "kr.co.board";
	
	@Override
	public List<BoardVO> list(PageTO<BoardVO> to) {
		RowBounds rb = new RowBounds(to.getStartNum()-1, to.getPerPage());
		return sqlSession.selectList(NS+".list", null, rb);
	}

	@Override
	public BoardVO read(int bno) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NS+".read", bno);
	}

	@Override
	public void insert(BoardVO vo) {
		sqlSession.insert(NS+".insert", vo);
		
	}

	@Override
	public void update(BoardVO vo) {
		sqlSession.update(NS+".update", vo);
		
	}

	@Override
	public void delete(int bno) {
		sqlSession.delete(NS+".delete", bno);
	}

	@Override
	public Integer getAmount() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NS+".getAmount");
	}

	@Override
	public void updateViewCnt(int bno) {
		sqlSession.update(NS+".updateViewCnt", bno);
		
	}

	@Override
	public void replyCntPlus(ReplyVO vo) {
		sqlSession.update(NS+".replyCntPlus", vo);
	}

	@Override
	public void replyCntMinus(Map<String, Object> map) {
		sqlSession.update(NS+".replyCntMinus", map);
		
	}


}
