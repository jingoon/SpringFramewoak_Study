package kr.co.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
