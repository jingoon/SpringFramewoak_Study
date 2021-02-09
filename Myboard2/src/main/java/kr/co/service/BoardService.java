package kr.co.service;

import java.util.List;

import kr.co.domain.BoardVO;

public interface BoardService {

	public List<BoardVO> list();

	public BoardVO read(int bno);

	public void insert(BoardVO vo);

	public BoardVO update(int bno);

	public void update(BoardVO vo);
}
