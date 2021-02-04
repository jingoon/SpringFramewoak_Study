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
public class Controller {
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping(value = "/read/{bno}", method = RequestMethod.GET)
	public String read(@PathVariable("bno") String bno, Model model ) {
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
