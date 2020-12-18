package test.config;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;

import org.junit.Test;

import config.DBConnection;
import config.DBConnection2;

public class DBConnectionTest {
	
	@Test
	public void 데이터베이스연결_테스트() {
		Connection conn = DBConnection2.getInstance();
		Connection conn2 = null;
		assertNotNull(conn);
	}
	
}
