package kr.co.ezen;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	//update view
	@RequestMapping(value = "update", method = RequestMethod.GET)
	public void update(String id, Model model) {
		MemberVO vo = memberService.userInfo(id);
		model.addAttribute("vo", vo);
	}
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(MemberVO vo) {
		memberService.update(vo);
		return "redirect:/update?id="+vo.getUserId(); // 임시 추후 변경. // post에서 return 하면 post로 포워딩 하나? redirect없으면 오류
	}
	
	//insert view
	@RequestMapping(value = "insert", method = RequestMethod.GET)
	public void insert() {
	}
	//insert method
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public String insert(MemberVO vo) {
		memberService.insert(vo);
		return "redirect:/getTime";// 일단 임시로. 추후 변경
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
