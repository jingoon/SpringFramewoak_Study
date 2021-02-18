package kr.co.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.domain.PageTO;
import kr.co.domain.ReplyVO;
import kr.co.repository.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService{
	
	@Autowired
	private ReplyDAO replyDAO;

	@Override
	public void insert(ReplyVO vo) {
		replyDAO.insert(vo);		
	}

	@Override
	public int update(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return replyDAO.update(map);
	}

	@Override
	public int getAmount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return replyDAO.getAmount(map);
	}

	@Override
	public List<ReplyVO> list(PageTO<ReplyVO> pt,int bno) {
		// TODO Auto-generated method stub
		return replyDAO.list(pt, bno);
	}


	

}
