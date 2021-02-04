package kr.co.service;

import java.util.List;

import kr.co.domain.BoardVO;

public interface BoardService {

	public List<BoardVO> list();

	public BoardVO read(String bno);
}
