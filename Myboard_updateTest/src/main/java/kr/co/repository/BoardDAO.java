package kr.co.repository;

import java.util.List;
import java.util.Map;

import kr.co.domain.BoardVO;
import kr.co.domain.PageTO;
import kr.co.domain.ReplyVO;

public interface BoardDAO {
	
	public List<BoardVO> list(PageTO<BoardVO> to);
	public BoardVO read(int bno);
	public void insert(BoardVO vo);
	public void update(BoardVO vo);
	public void delete(int bno);
	public Integer getAmount();
	public void updateViewCnt(int bno);
	public void replyCntPlus(ReplyVO vo);
	public void replyCntMinus(Map<String, Object> map);
	public void addAttach(String file, int bno);
	public List<String> getAttach(int bno);
}
