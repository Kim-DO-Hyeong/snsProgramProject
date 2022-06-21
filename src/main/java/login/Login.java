package login;

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
import javax.servlet.http.HttpSession;

import Util.DatabaseManager;
import signUp.Member;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		Member memberInfo = new Member(id,pw);
		

		// DB 연동
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DatabaseManager.getConnection();

			String sql = "SELECT * FROM membertbl WHERE memberID = ? AND memberPW = ?";
			
			pstmt = DatabaseManager.getPstmt(conn, sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			rs = pstmt.executeQuery();
			
			// 로그인에 아이디와 비밀번호가 일치할때 ( sql )
			if(rs.next()) {
				HttpSession session = request.getSession();
				session.setAttribute("isLogin", true);
				session.setAttribute("memberInfo", memberInfo);
				session.setMaxInactiveInterval(3600);
				// 상태코드 200 생략 
			}else {
				// 로그인에 실패했을시 
				// 상태코드 400 전송
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DatabaseManager.closeResultSet(rs);
			DatabaseManager.closePstmt(pstmt);
			DatabaseManager.closeConn(conn);
		}
		
		
		// DB 연동 -end 
		
		
//		if(isLogin) {
//			HttpSession session = request.getSession();
//			session.setAttribute("isLogin", true);
//			session.setAttribute("memberInfo", memberInfo);
//			session.setMaxInactiveInterval(3600);
//			
//		}else {
//			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//		}
	}

}
