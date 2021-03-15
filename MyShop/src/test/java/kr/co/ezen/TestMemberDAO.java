package kr.co.ezen;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.domain.MemberVO;
import kr.co.repository.MemberDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class TestMemberDAO {

	@Inject
	private MemberDAO mDAO;
	
	@Test
	public void testMemberRead() {
		MemberVO vo = mDAO.read("park001");
		System.out.println(vo);
	}
}
