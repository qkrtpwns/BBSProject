package user_model1;

import user_model1.UserTO;
import user_model1.UserDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement pstmt;
	
	public UserDAO() {
		try {
			String url = "jdbc:mysql://localhost:3306/project?useSSL=false";
			String user = "root";
			String password = "dlrlFgh!@#$1234";
						
			Class.forName("com.mysql.jdbc.Driver");
			
			conn = DriverManager.getConnection(url, user, password);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("[에러] : " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("[에러] : " + e.getMessage());
		}
	}
	
	public int login(String userID, String userPassword) {
		String sql = "select count(*) as count from user where userID=? and userPassword=md5(?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			pstmt.setString(2, userPassword);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// 카운트가 1 이면 해당 아이디와 패스워드가 존재한다는 뜻
				if(rs.getInt("count")==1 ) { 
					return 1; // 로그인 성공
				} else {
					return 0; // 비밀번호 불일치
				}
			}
			return -1; // 아이디가	없음
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2; // 데이터베이스 오류
	}
	
	public int join(UserTO userTO) {
		String sql = "insert into user values(0, ?, md5(?), ?, ?, ?, 0)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userTO.getUserID());
			pstmt.setString(2, userTO.getUserPassword());
			pstmt.setString(3, userTO.getUserName());
			pstmt.setString(4, userTO.getUserGender());
			pstmt.setString(5, userTO.getUserEmail());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류
	}
}
