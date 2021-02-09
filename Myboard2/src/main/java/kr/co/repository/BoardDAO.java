package kr.co.repository;

import java.util.List;

import kr.co.domain.BoardVO;

public interface BoardDAO {
	
	public List<BoardVO> list();
	public BoardVO read(int bno);
	public void insert(BoardVO vo);
	public void update(BoardVO vo);
	public void delete(int bno);
}
