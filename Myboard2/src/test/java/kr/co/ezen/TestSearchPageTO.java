package kr.co.ezen;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.domain.BoardVO;
import kr.co.domain.SearchPageTO;
import kr.co.repository.SBoardDAO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class TestSearchPageTO {

	@Autowired
	private SBoardDAO dao;
	
	@Test
	public void testGetAmount() {
				
		SearchPageTO spt = new SearchPageTO("title", "test", 1);
		Integer amount= dao.getAmount(spt);
		System.out.println(amount);
		
	}
	
	@Test
	public void testList() {
		SearchPageTO spt = new SearchPageTO("writer", "kim", 1);
		Integer amount= dao.getAmount(spt);
		spt.setAmount(amount);
		List<BoardVO> list =dao.list(spt);
		spt.setList(list);
		
		for(BoardVO vo : spt.getList()) {
			System.out.println(vo);
		}
	}
}
