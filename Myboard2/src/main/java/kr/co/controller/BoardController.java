package kr.co.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.domain.BoardVO;
import kr.co.service.BoardService;

@org.springframework.stereotype.Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping(value = "/delete/{bno}", method = RequestMethod.GET)
	public String delete(@PathVariable("bno") int bno) {
		boardService.delete(bno);
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/update/{bno}", method = RequestMethod.GET)
	public String update(@PathVariable("bno") int bno, Model model) {
		BoardVO vo= boardService.update(bno);
		model.addAttribute("vo", vo);
		return "/board/update";
	}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(BoardVO vo) {
		boardService.update(vo);
		return "redirect:/board/read/"+vo.getBno();
	}
	
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert() {
		return "/board/insert";
	}
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(BoardVO vo) {
		boardService.insert(vo);
		return "redirect:/board/read/"+vo.getBno();
	}
	
	
	@RequestMapping(value = "/read/{bno}", method = RequestMethod.GET)
	public String read(@PathVariable("bno") int bno, Model model ) {
		BoardVO vo = boardService.read(bno);
		model.addAttribute("vo", vo);
		return "/board/read";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<BoardVO> list = boardService.list();
		model.addAttribute("list", list);
		return "/board/list";
	}

}
