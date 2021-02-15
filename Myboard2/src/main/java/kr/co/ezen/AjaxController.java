package kr.co.ezen;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.domain.SearchPageTO;

@Controller
@RequestMapping(value = "/ajax")
public class AjaxController {
	
	
	@ResponseBody
	@RequestMapping(value = "/soapList", method = RequestMethod.POST)
	public List<SearchPageTO> soapList(@RequestParam Map<String, String>map) throws Exception {//@RequestParam, map으로 데이터 받음
		String strList = map.get("list").toString();
		System.out.println(strList);
		ObjectMapper mapper = new ObjectMapper();
		List<SearchPageTO> list= mapper.readValue(strList, new TypeReference<List<SearchPageTO>>() {
		});
		System.out.println(list.get(1));
		
		return list;
	}
	
	
	// ajax 객체, JSON 타입 데이터 전송
	@ResponseBody
	@RequestMapping(value = "/soapJson", method = RequestMethod.POST)
	public SearchPageTO soapJson(SearchPageTO to) {
		System.out.println(to.getSearchType());
		System.out.println(to.getKeyword());
		System.out.println(to.getCurPage());
		return to;
	}
	
	//ajax 배열 데이터 전송
	@ResponseBody //리턴값이 view가 아니라 data임을 알려준다
	@RequestMapping(value = "/soapArr", method = RequestMethod.POST)
	public String[] soapArr(String[] arr) {
		for(String a: arr) {
			System.out.print(a+" ");
		}
		System.out.println();
		return arr;
	}
	
	// ajax 단일 데이터 전송
	@ResponseBody //리턴값이 view가 아니라 data임을 알려준다
	@RequestMapping(value = "/soapOne", method = RequestMethod.POST,
				produces = "application/text; charset=utf-8")// 한글이 깨질경우 작성
	public String soapOne(String str) {
		System.out.println("soapOne--> "+str);
		return str;
	}
	
	
	
	
	// 페이지 연결
	@RequestMapping(value = "/soap", method = RequestMethod.GET)
	public String soapOne() {
		return "/ajax/soap";
	}

}
