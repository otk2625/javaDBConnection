package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.DBConnection;
import config.DBConnection2;
import model.User;

public class UserDAO {
	public void 삭제하기(int id) {
		// stream에 연결된 객체
		Connection conn = DBConnection2.getInstance();

		// 버퍼에 담을 메시지
		String query = "delete from user where id = ?";

		try {
			// 버퍼연결해서 쿼리 전송
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			// commit
			pstmt.executeUpdate();
			System.out.println("DELETE가 완료되었습니다.");
		} catch (SQLException e) {
			System.out.println("Query 전송시 오류가 발생했습니다.");
		}
	}

	
	public void 수정하기(int id, String name, String phone, String address, String relation) {
		// stream에 연결된 객체
		Connection conn = DBConnection2.getInstance();

		// 버퍼에 담을 메시지
		String query = "update user set name = ?, phone = ?, address = ?, relation = ? where id = ?";

		try {
			// 버퍼연결해서 쿼리 전송
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			pstmt.setString(3, address);
			pstmt.setString(4, relation);
			pstmt.setInt(5, id);
			
			// commit
			pstmt.executeUpdate();
			System.out.println("UPDATE가 완료되었습니다.");
		} catch (SQLException e) {
			System.out.println("Query 전송시 오류가 발생했습니다."+e.getMessage());
		}
	}
	
	public ArrayList<User> 찾기() {
		// 컬렉션 만들기
		ArrayList<User> list = new ArrayList<User>();
		
		// stream에 연결된 객체
		Connection conn = DBConnection2.getInstance();

		// 버퍼에 담을 메시지
		String query = "select * from user";

		try {
			// 버퍼연결해서 쿼리 전송
			PreparedStatement pstmt = conn.prepareStatement(query);
			// 결과 받기
			ResultSet rs = pstmt.executeQuery();
			System.out.println("SELECT가 완료되었습니다.");
			
			while(rs.next()) { // 검색 결과가 한건이 아니면  while로 변경한다.
				User user = User.builder()
						.id(rs.getInt("id"))
						.name(rs.getString("name"))
						.phone(rs.getString("phone"))
						.address(rs.getString("address"))
						.relation(rs.getString("relation")).build();
				list.add(user);
			}	
			
			return list;
			
		} catch (SQLException e) {
			System.out.println("Query 전송시 오류가 발생했습니다."+e.getMessage());
		}
		return null;
	}
	
	
	public ArrayList<User> 검색(String relation) {
		// 컬렉션 만들기
		ArrayList<User> list = new ArrayList<User>();
		
		// stream에 연결된 객체
		Connection conn = DBConnection2.getInstance();

		// 버퍼에 담을 메시지
		String query = "select * from user where relation = ?";

		try {
			// 버퍼연결해서 쿼리 전송
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, relation);
			
			// 결과 받기
			ResultSet rs = pstmt.executeQuery();
			System.out.println("SELECT가 완료되었습니다.");
			
			while(rs.next()) { // 검색 결과가 한건이 아니면  while로 변경한다.
				User user = User.builder()
						.id(rs.getInt("id"))
						.name(rs.getString("name"))
						.phone(rs.getString("phone"))
						.address(rs.getString("address"))
						.relation(rs.getString("relation")).build();
				
				list.add(user);
			}	
			return list;
			
		} catch (SQLException e) {
			System.out.println("Query 전송시 오류가 발생했습니다."+e.getMessage());
		}
		return null;
	}
	
	public User 찾기(int id) {
		// stream에 연결된 객체
		Connection conn = DBConnection2.getInstance();

		// 버퍼에 담을 메시지
		String query = "select id, name, phone, address, relation from user where id = ?";

		try {
			// 버퍼연결해서 쿼리 전송
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			// 결과 받기
			ResultSet rs = pstmt.executeQuery();
			System.out.println("SELECT가 완료되었습니다.");
			
			if(rs.next()) { // 검색 결과가 한건이 아니면  while로 변경한다.
				User user = User.builder()
						.name(rs.getString("name"))
						.phone(rs.getString("phone"))
						.address(rs.getString("address"))
						.relation(rs.getString("relation")).build();
				
				return user;
			}			
			
		} catch (SQLException e) {
			System.out.println("Query 전송시 오류가 발생했습니다."+e.getMessage());
		}
		return null;
	}
	
	public void 추가하기(String name, String phone, String address, String relation) {
		// stream에 연결된 객체
		Connection conn = DBConnection2.getInstance();

		// 버퍼에 담을 메시지
		String query = "INSERT INTO user(name, phone, address, relation) VALUES(?, ?, ?, ?)";

		try {
			// 버퍼연결해서 쿼리 전송
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			pstmt.setString(3, address);
			pstmt.setString(4, relation);
			// commit
			pstmt.executeUpdate();
			System.out.println("INSERT가 완료되었습니다.");
		} catch (SQLException e) {
			System.out.println("Query 전송시 오류가 발생했습니다.");
		}
	}
}
