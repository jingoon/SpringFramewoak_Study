package kr.co.ezen;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.domain.BoardVO;
import kr.co.domain.PageTO;
import kr.co.repository.BoardDAO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class TestPageTO {

	@Inject
	SqlSession sql;
	
	@Inject
	BoardDAO dao;
	
	@Test
	public void testGetAmount() {
				
		int amount= dao.getAmount();
		System.out.println(amount);
		
		PageTO to = new PageTO(1);
		to.setAmount(amount);
		List<BoardVO> list= dao.list(to);
		for (BoardVO boardVO : list) {
			System.out.println(boardVO);
		}
		
	}
}
