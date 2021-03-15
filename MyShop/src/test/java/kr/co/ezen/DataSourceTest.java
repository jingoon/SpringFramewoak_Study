package kr.co.ezen;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class DataSourceTest {
	
	@Inject //@Autowired  == @Inject와 같다.
	private DataSource ds; // root-context.xml의 bean 객체를 받아온다
	
	@Test
	public void testConnection() {
		Connection conn = null;
		try {
			conn = ds.getConnection();
			System.out.println("생성 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
