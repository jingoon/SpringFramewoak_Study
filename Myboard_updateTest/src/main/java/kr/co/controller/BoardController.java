package kr.co.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.domain.BoardVO;
import kr.co.domain.PageTO;
import kr.co.service.BoardService;

@org.springframework.stereotype.Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
		
	@RequestMapping(value = "/delete/{curPage}/{bno}", method = RequestMethod.GET)
	public String delete(@PathVariable("bno") int bno) {
		boardService.delete(bno);
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/update/{curPage}/{bno}", method = RequestMethod.GET)
	public String update(@PathVariable("bno") int bno,
			@PathVariable("curPage") int curPage, Model model) {
		BoardVO vo= boardService.update(bno);
		model.addAttribute("vo", vo);
		model.addAttribute("curPage", curPage);
		return "/board/update";
	}
	@RequestMapping(value = "/update/{curPage}", method = RequestMethod.POST)
	public String update(BoardVO vo, @PathVariable("curPage")int curPage) {
		boardService.update(vo);
		return "redirect:/board/read/"+curPage+"/"+vo.getBno();
	}
	
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert() {
		return "/board/insert";
	}
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(BoardVO vo) {
		boardService.insert(vo);
		return "redirect:/board/read/1/"+vo.getBno();
	}
	
	
	@RequestMapping(value = "/read/{curPage}/{bno}", method = RequestMethod.GET)
	public String read(@PathVariable("bno") int bno,
			@PathVariable("curPage") int curPage,Model model ) {
		BoardVO vo = boardService.read(bno);
		model.addAttribute("vo", vo);
		model.addAttribute("curPage", curPage);
		return "/board/read";
	}
	
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public String list(Model model) {
		int curPage = 1;
		return "redirect:/board/list/"+curPage;
	}
	@RequestMapping(value = "/list/{curPage}", method = RequestMethod.GET)
	public String list(Model model,@PathVariable ("curPage") int curPage ) {
		Integer amount = boardService.getAmount();
		if(amount ==null) {
			amount = 0;
		}
		PageTO<BoardVO> to = new PageTO<BoardVO>(curPage);
		to.setAmount(amount);
		if(curPage<1) {
			return "redirect:/board/list/";
		}else if(curPage > to.getTotalPage()) {
			return "redirect:/board/list/"+to.getTotalPage();
		}
		List<BoardVO> list = boardService.list(to);
		to.setList(list);
	
		model.addAttribute("to", to);
		return "/board/list";
	}

}
