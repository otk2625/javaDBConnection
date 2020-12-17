import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.DBConnection;
import model.Dept;

public class MainApp {

	public static void 추가(int id) {
		// 인젝션 공격에 취약한 위험한 문법
		// String sql = "INSERT INTO test1(id) VALUES("+ id +")";
		String sql = "INSERT INTO test1(id) VALUES(?)"; // ?를 사용하도록 한다
		Connection conn = DBConnection.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id); //인젝션 공격 예방
			int result = pstmt.executeUpdate(); //변경된 row count를 리턴, -값은 오류에만 리턴
			System.out.println("result : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void 삭제(int id) {
		String sql = "DELETE FROM test1 WHERE id = ?"; // ?를 사용하도록 한다
		Connection conn = DBConnection.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id); //인젝션 공격 예방
			int result = pstmt.executeUpdate(); //변경된 row count를 리턴, -값은 오류에만 리턴
			System.out.println("result : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void 수정(int id) {
		String sql = "UPDATE test1 SET id = ?"; // ?를 사용하도록 한다
		Connection conn = DBConnection.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id); //인젝션 공격 예방
			int result = pstmt.executeUpdate(); //변경된 row count를 리턴, -값은 오류에만 리턴
			System.out.println("result : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Dept 찾기(int deptno) {
		String sql = "SELECT deptno, dname, loc FROM dept WHERE deptno = ?"; // ?를 사용하도록 한다
		Connection conn = DBConnection.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, deptno); //인젝션 공격 예방
			//커서를 한칸씩 내리면서 찾음
			ResultSet rs = pstmt.executeQuery(); //결과집합
			if(rs.next()) {
				Dept dept = Dept.builder()
						.deptno(rs.getInt("deptno"))
						.dname(rs.getString("dname"))
						.loc(rs.getString("loc"))
						.build();
				
				System.out.println(dept);
//				int deptno2 = rs.getInt("deptno"); //컬럼명
//				String dname = rs.getString("dname");
//				String loc = rs.getString("loc");
//				System.out.println(deptno2);
//				System.out.println(dname);
//				System.out.println(loc);
				return dept;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static List<Dept> 전체찾기() {
		List<Dept> 전체list = new ArrayList<>();
		String sql = "SELECT deptno, dname, loc FROM dept"; // ?를 사용하도록 한다
		Connection conn = DBConnection.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//커서를 한칸씩 내리면서 찾음
			ResultSet rs = pstmt.executeQuery(); //결과집합
			while(rs.next()) {
				Dept dept = Dept.builder()
						.deptno(rs.getInt("deptno"))
						.dname(rs.getString("dname"))
						.loc(rs.getString("loc"))
						.build();
				전체list.add(dept);
				
			}
			return 전체list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		List<Dept> 전체list = new ArrayList<>();
		전체list = 전체찾기();
		
		for (int i = 0; i < 전체list.size(); i++) {
			System.out.println(전체list.get(i));
		}
	}

}
