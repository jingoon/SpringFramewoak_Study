package kr.co.ezen;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.domain.MemberDTO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
//	1. URI(servlet path)와 View파일의 이름이 같으면 mvc핸들러의 반환형을 void 처리해도 괜찮다
	@RequestMapping("testVoid")
	public void testVoid(Model model) {
		model.addAttribute("msg", "testVoid");
	}
//	2. 클라이언트가 보내준 데이터를 request.getPrameter()를 이용하지 않고 mvc핸들러의 파라미터 변수를 파라미터네임과 똑같이 해주면 클라이언트가 보내준 데이터가 해당 변수에 대입된다
	@RequestMapping("testparam")
	public String testParam(String msg, Model model) {
		System.out.println("testparam: "+msg);
		model.addAttribute("msg", msg);
		return "testVoid";
	}
//	3. 클라이언트가 보내준 데이터를 model객체를 이용하지 않고, 클라이언트에 그대로 보내줄 수 있는 문법이 제공됨(@ModelAttribute)
	@RequestMapping("testma")
	public String testModelAttribute(@ModelAttribute("msg") String msg) {
		System.out.println("testma:" +msg);
		return "testVoid";
	}
//	4. 클라이언트가 보내준 데이터를 조합해서 자동으로 객체로 전환(MemberDTO.java 생성)
	@RequestMapping("testdto")
	public String testMemberDTO(@ModelAttribute("dto") MemberDTO dto) {
		System.out.println(dto);
		return "testVoid";
	}
//	5. redirect 방식으로 포워딩 반환값에 "redirect:/넘어갈 곳"
	@RequestMapping("testredir")
	public String testRedirect() {
		return "redirect:/testVoid";
	}
//	6. redirect방식으로 포워딩 할 때 model객체에 데이터를 바인딩하면 query string으로 데이터가 작성됨(주소창에 데이터를 가지고 이동). mvc핸들러의 파라미터 선언하면 바인딩된 데이터(주소창의 데이터)를 쉽게 받아 올 수 있다.  
	@RequestMapping("testredirbind")
	public String testRedirectBinding(Model model) {
		model.addAttribute("msg", "test Redirect Binding");
		return "redirect:/testparam";
	}
	// 뭔가 우선순위가 있나보다 바로 testVoid로 포워딩하면 msg가 밀리고 testma, testparam으로 거치면 msg를 밀어낸다
	@RequestMapping("testredirbind2")
	public String testRedirectBinding2(Model model) {
		model.addAttribute("msg", "test Redirect Binding2");
		return "redirect:/testVoid";
	}
//	7. redirect방식으로 포워딩할 하고 바인딩된 데이터를 숨겨서 보낼 때 
//	- RedirectAttributes 객체의 addFlashAttribute( )를 이용
	@RequestMapping("testredirA")
	public String testRedirectAttributes(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("msg", "test redirectAttributes");
		return "redirect:/testma";
	}
//	7-1. 바인딩된 데이터를 받을 때
	//2,3
//	8. JSON 구현
	@RequestMapping("json")
	public @ResponseBody MemberDTO testjson() {
		MemberDTO dto = new MemberDTO("gogo", "json", 14);
		return dto;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/good", method = RequestMethod.GET)
	public String good(Model model) {
				
		model.addAttribute("msg", "moning" );
		
		return "good";
	}
	
	@RequestMapping(value = "/pjg", method = RequestMethod.GET )
	public String pjg1(Model model) {
		model.addAttribute("name", "park jin goon");
		return "pjg2";
	}
	
}
