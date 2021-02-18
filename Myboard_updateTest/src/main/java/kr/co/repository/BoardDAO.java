package kr.co.repository;

import java.util.List;

import kr.co.domain.BoardVO;
import kr.co.domain.PageTO;

public interface BoardDAO {
	
	public List<BoardVO> list(PageTO<BoardVO> to);
	public BoardVO read(int bno);
	public void insert(BoardVO vo);
	public void update(BoardVO vo);
	public void delete(int bno);
	public Integer getAmount();
}
