package kr.co.service;

import java.util.List;
import java.util.Map;

import kr.co.domain.PageTO;
import kr.co.domain.ReplyVO;

public interface ReplyService {
	
	void insert(ReplyVO vo);

	int update(Map<String, Object> map);

	int getAmount(Map<String, Object> map);

	List<ReplyVO> list(PageTO<ReplyVO> pt, int bno);

	int delete(Map<String, Object> map);


}
