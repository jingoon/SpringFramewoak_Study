package kr.co.service;

import java.util.List;

import kr.co.domain.BoardVO;
import kr.co.domain.SearchPageTO;

public interface SBoardService {

	public Integer getAmount(SearchPageTO spt);

	public List<BoardVO> list(SearchPageTO spt);
}
