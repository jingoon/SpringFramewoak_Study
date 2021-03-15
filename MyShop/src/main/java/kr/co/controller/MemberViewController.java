package kr.co.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.domain.LoginDTO;
import kr.co.domain.MemberVO;
import kr.co.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberViewController {
	
	@Autowired
	private MemberService memberService;

	//회원가입
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public void insert() {
		
	}
	
	//회원가입
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(MemberVO memberVO) {
		System.out.println(memberVO);// test
		int success = memberService.insert(memberVO);
		if(success==1) {
			return "/member/login";
		}else {
			return "/member/insert";
		}
	}
	
	//로그인
	@RequestMapping(value = "/loginUI", method = RequestMethod.GET)
	public String loginUI() {
		return "/member/login";
	}
	//로그인
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login(LoginDTO loginDTO, Model model) {
		MemberVO login= memberService.login(loginDTO);
		model.addAttribute("login", login);
	}
	
	
}
