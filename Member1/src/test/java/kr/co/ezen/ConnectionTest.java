package kr.co.ezen;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class ConnectionTest {
	
	public final String DRIVER = "oracle.jdbc.OracleDriver";
	public final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	public final String USER = "spring_study";
	public final String PASSWORD = "spring_study";
		
	@Test
	public void testConnection() throws Exception {
	
		Class.forName(DRIVER);
		System.out.println("로딩 성공");
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("연결성공");
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
