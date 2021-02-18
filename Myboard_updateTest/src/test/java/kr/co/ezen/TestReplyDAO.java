package kr.co.ezen;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.domain.ReplyVO;
import kr.co.repository.ReplyDAO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class TestReplyDAO {

	@Autowired
	private ReplyDAO replyDAO;
	
	@Test
	public void testReplyInsert() {
		replyDAO.insert(new ReplyVO(10, "number ten reply", "tester"));
	}
	
}
