package kr.co.ezen;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ajax")
public class TestRestController {
	
	// Rest ajax String 데이터
	// 입력받은 데이터 출력
	@RequestMapping(value = "/rest1", method = RequestMethod.POST )
	public String rest1(@RequestBody Map<String, Object> map) {
		String str=(String) map.get("test1");
		return str;
	}
	
	// Rest ajax ARRAY 데이터
	// 입력받은 데이터중 hello, world 출력
	@RequestMapping(value = "/rest2", method = RequestMethod.POST )
	public Map<String, Object> rest2(@RequestBody Map<String, Object> map) {
		System.out.println(map);	//{arr={test1=hello, test2=world, test3=good}} 맵속 맵
		@SuppressWarnings("unchecked")
		Map<String, Object> sMap= (Map<String, Object>) map.get("arr");
		System.out.println(sMap.get("test2"));
		return map;
	}
	
	// Rest ajax JSON 데이터
	// 입력받은 데이터중 p002 출력
	@RequestMapping(value = "/rest3", method = RequestMethod.POST )
	public Map<String, Object> rest3(@RequestBody Map<String, Object> map) {
		System.out.println(map);	// map{ key=value, key=value, key=value(List(sMap, sMap, sMap))
		System.out.println(map.get("test1"));
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> list=(List<Map<String, Object>>) map.get("test3");	//[map, map, ... ] map의 List
		System.out.println(list.get(1).get("mpw")); // List 속 map
		
		return map;
	}
	
	// Rest ajax LIST 데이터
	// 입력받은 데이터중 m001출력
	@RequestMapping(value = "/rest4", method = RequestMethod.POST)
	public Map<String, Object> rest4(@RequestBody Map<String, Object> map){
		System.out.println(map);
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> list= (List<Map<String, Object>>) map.get("list");
		System.out.println(list.get(0).get("searchType"));
		
		return map;
	}
	
	
	
	
	
}
