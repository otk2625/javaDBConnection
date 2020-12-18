import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.DBConnection;
import dao.DeptDAO;
import model.Dept;

public class MainApp {

	//DB는 비동기처리가 안된다.NoSQL., MongoDB는 비동기가능 
	public static void main(String[] args) {
		DeptDAO deptDAO = new DeptDAO();
		List<Dept> 전체list = new ArrayList<>();
		전체list = deptDAO.전체찾기();
		
		for (int i = 0; i < 전체list.size(); i++) {
			System.out.println(전체list.get(i));
		}
	}

}
