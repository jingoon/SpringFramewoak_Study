package kr.co.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.domain.BoardVO;
import kr.co.domain.SearchPageTO;
import kr.co.repository.SBoardDAO;

@Service
public class SBoardServiceImpl implements SBoardService{

	@Autowired
	private SBoardDAO sBoardDAO;
	
	@Override
	public Integer getAmount(SearchPageTO<BoardVO> spt) {
		// TODO Auto-generated method stub
		return sBoardDAO.getAmount(spt);
	}

	@Override
	public List<BoardVO> list(SearchPageTO<BoardVO> spt) {
		// TODO Auto-generated method stub
		return sBoardDAO.list(spt);
	}

}
