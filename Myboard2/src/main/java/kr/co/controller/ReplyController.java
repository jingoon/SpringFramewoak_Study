package kr.co.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.co.domain.ReplyVO;
import kr.co.service.ReplyService;

@RestController
@RequestMapping(value = "/reply")
public class ReplyController {

	@Autowired
	private ReplyService replyService;

	// 댓글 입력
	@RequestMapping(value = "", method = RequestMethod.POST)
	public int insert(@RequestBody ReplyVO vo) {
		System.out.println(vo);
		replyService.insert(vo);
		System.out.println(vo);
		return vo.getBno();
	}
	
}
