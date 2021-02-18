package kr.co.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.co.domain.PageTO;
import kr.co.domain.ReplyVO;
import kr.co.service.ReplyService;

@RestController
@RequestMapping(value = "/reply")
public class ReplyController {

	@Autowired
	private ReplyService replyService;
	
	// 댓글 수정
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public String update(@RequestBody Map<String, Object> map) {
		int i = replyService.update(map);
		if( i> 0) {
			return "o";
		}else {
			return "x";
		}
	}
	
	// 댓글 리스트
	@RequestMapping(value = "/{bno}/{curPage}", method = RequestMethod.GET)
	public PageTO<ReplyVO> list(@PathVariable("bno")int bno ,@PathVariable("curPage") int curPage) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("curPage", curPage);
		map.put("bno", bno);
				
		PageTO<ReplyVO> pt = new PageTO<ReplyVO>(curPage);
		int amount = replyService.getAmount(map); // bno
		pt.setAmount(amount);
		
		List<ReplyVO> list= replyService.list(pt, bno); 
		pt.setList(list);
		
		return pt;
	}
	

	// 댓글 입력
	@RequestMapping(value = "", method = RequestMethod.POST)
	public int insert(@RequestBody ReplyVO vo) {
		System.out.println(vo);
		replyService.insert(vo);
		System.out.println(vo);
		return vo.getBno();
	}
	
}
