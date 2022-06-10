package etc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/StudyDB")
public class StudyDB extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn= null;
		PreparedStatement pstmt = null;
		try {
			
			// JDBC 드라이버 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			// DBMS 접속
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/snsdb?user=root&password=1234");
			// SQL 생성 
			String sql = "SELCET * FROM snsdb";
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);

			int count = pstmt.executeUpdate();
			
			// 자원해제
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt !=null) {
				pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn!=null) {
				conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		 
	
	
	}

}
