package kr.co.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.domain.BoardVO;
import kr.co.domain.SearchPageTO;
import kr.co.service.BoardService;
import kr.co.service.SBoardService;

@Controller
@RequestMapping("/sboard")
public class SBoardController {

	@Autowired
	private SBoardService sBoardService;
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value = "/delete/{searchType}/{keyword}/{curPage}/{bno}", method = RequestMethod.GET)
	public String delete(@PathVariable("searchType") String searchType,
						@PathVariable("keyword") String keyword,
						@PathVariable("curPage") int curPage,
						@PathVariable("bno")int bno,
						Model model) throws UnsupportedEncodingException {
		boardService.delete(bno);
		
		String url = null;
		StringBuffer sb = new StringBuffer();
		
		sb.append(searchType);
		sb.append("/");
		sb.append(URLEncoder.encode(keyword, "UTF-8"));
		sb.append("/");
		sb.append(curPage);
		url = sb.toString();
		return "redirect:/sboard/list/"+url;
	}
	
	@RequestMapping(value = "/read/{searchType}/{keyword}/{curPage}/{bno}", method = RequestMethod.GET)
	public String read(@PathVariable("searchType") String searchType,
						@PathVariable("keyword") String keyword,
						@PathVariable("curPage") int curPage,
						@PathVariable("bno")int bno,
						Model model) {
		BoardVO vo = boardService.read(bno);
		model.addAttribute("vo", vo);
		SearchPageTO spt = new SearchPageTO(searchType, keyword, curPage);
		model.addAttribute("to", spt);
		
		return "/sboard/read";
	}
	
	
	@RequestMapping(value = "/list/{searchType}/{keyword}/{curPage}", method = RequestMethod.GET)
	public String list(@PathVariable("searchType") String searchType,
						@PathVariable("keyword") String keyword,
						@PathVariable("curPage") int curPage,
						Model model) {
		SearchPageTO spt = new SearchPageTO(searchType, keyword, curPage);
		Integer amount = sBoardService.getAmount(spt);
		if(amount == null) {
			amount = 0;
		}
		spt.setAmount(amount);
		List<BoardVO> list= sBoardService.list(spt);
		spt.setList(list);
		model.addAttribute("to", spt);
		
		return "/sboard/list";
	}
}
