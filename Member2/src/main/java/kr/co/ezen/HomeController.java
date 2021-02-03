package kr.co.ezen;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.domain.MemberVO;
import kr.co.service.MemberService;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private MemberService memberService; // controller는 service를 호출
	
	
	//delete
	@RequestMapping(value = "deleteConfirm/{userId}", method = RequestMethod.GET)
	public String deleteConfirm(@PathVariable("userId") String userId, Model model) {
		model.addAttribute("userId", userId);
		return "delete";
	}
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String delete(MemberVO vo) {
		int seccessCount = memberService.delete(vo);
		if(seccessCount<1) {
			return "redirect:/deleteConfirm/"+vo.getUserId();
		}
		return "redirect:/list";
	}
	
	
	//update
	@RequestMapping(value = "update/{userId}", method = RequestMethod.GET)
	public String update(@PathVariable("userId") String userId, Model model) {
		MemberVO vo = memberService.update(userId);
		model.addAttribute("vo", vo);
		return "update";
	}
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(MemberVO vo) {
		int successCount= memberService.update(vo);
		if(successCount<1) {
			return "redirect:/update/"+vo.getUserId();
		}
		return "redirect:/list";
	}
	
	
	//read
	@RequestMapping(value = "read/{userId}", method = RequestMethod.GET)
	public String read(@PathVariable("userId") String userId, Model model) {
		MemberVO vo = memberService.read(userId);
		model.addAttribute("vo", vo);
		return "read";
	}
	
	
	//list
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(Model model) {
		List<MemberVO> list = memberService.list();
		model.addAttribute("list", list);
		return "list";
	}
	
	//insert view
	@RequestMapping(value = "insert", method = RequestMethod.GET)
	public void insert() {
	}
	//insert method
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public String insert(MemberVO vo) {
		memberService.insert(vo);
		return "redirect:/list";// 일단 임시로. 추후 변경
	}
	
	//getTime 메서드 SYSDATE 반환
	@RequestMapping(value = "getTime", method = RequestMethod.GET)
	public void getTime(Model model) {
		model.addAttribute("time", memberService.getTime());
	}

	//Home
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	
	
}
