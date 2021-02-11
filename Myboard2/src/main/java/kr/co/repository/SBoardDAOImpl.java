package kr.co.repository;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.domain.BoardVO;
import kr.co.domain.SearchPageTO;

@Repository
public class SBoardDAOImpl implements SBoardDAO{

	@Autowired
	private SqlSession sqlSession;
	
	private final String NS = "kr.co.search";

	@Override
	public Integer getAmount(SearchPageTO spt) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NS+".getAmount", spt);
	}

	@Override
	public List<BoardVO> list(SearchPageTO spt) {
		RowBounds rb = new RowBounds(spt.getStartNum()-1, spt.getPerPage());
		return sqlSession.selectList(NS+".list", spt, rb);
	}
	


}
