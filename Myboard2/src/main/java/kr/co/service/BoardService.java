package kr.co.service;

import java.util.List;

import kr.co.domain.BoardVO;
import kr.co.domain.PageTO;

public interface BoardService {

	public List<BoardVO> list(PageTO to);

	public BoardVO read(int bno);

	public void insert(BoardVO vo);

	public BoardVO update(int bno);

	public void update(BoardVO vo);

	public void delete(int bno);

	public Integer getAmount();
}
