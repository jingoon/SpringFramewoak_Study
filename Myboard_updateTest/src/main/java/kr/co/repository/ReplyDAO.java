package kr.co.repository;

import java.util.List;
import java.util.Map;

import kr.co.domain.PageTO;
import kr.co.domain.ReplyVO;

public interface ReplyDAO {
	
	void insert(ReplyVO vo);

	int update(Map<String, Object> map);

	int getAmount(Map<String, Object> map);

	List<ReplyVO> list(PageTO<ReplyVO> pt,int bno);
	
}
