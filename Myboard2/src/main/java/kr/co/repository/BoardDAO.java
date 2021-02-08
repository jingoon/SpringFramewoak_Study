package kr.co.repository;

import java.util.List;

import kr.co.domain.BoardVO;

public interface BoardDAO {
	
	public List<BoardVO> list();
	public BoardVO read(String bno);
	public void insert(BoardVO vo);

}
