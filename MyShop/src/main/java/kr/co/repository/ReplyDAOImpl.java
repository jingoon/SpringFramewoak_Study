package kr.co.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.domain.PageTO;
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

	@Override
	public int update(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.update(NS+".update", map);
	}

	@Override
	public int getAmount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NS+".getAmount", map);
	}

	@Override
	public List<ReplyVO> list(PageTO<ReplyVO> pt, int bno) {
		RowBounds rb = new RowBounds(pt.getStartNum()-1, pt.getPerPage());

		return sqlSession.selectList(NS+".list", bno, rb);
	}

	@Override
	public int delete(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.delete(NS+".delete", map);
	}

	@Override
	public void deleteReplies(int bno) {
		sqlSession.delete(NS+".deleteReplies", bno);
		
	}



	

}
