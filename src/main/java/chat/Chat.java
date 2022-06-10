package chat;

import java.io.IOException;
import java.io.PrintWriter;
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

import org.json.JSONObject;

import signUp.Join;
import signUp.Member;

/**
 * Servlet implementation class Chat
 */
@WebServlet("/chat")
public class Chat extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		HttpSession session = request.getSession();
		
		Member memberInfo= (Member)session.getAttribute("memberInfo");
		
		
		
		String id = memberInfo.getId();
		String pw= memberInfo.getPw();
		String name = "";
		
		// DB 연동 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/snsdb?user=root&password=1234");

			String sql = "SELECT * FROM membertbl WHERE memberID = ? AND memberPW = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			rs = pstmt.executeQuery();
			
			// 회원 정보 조회 성공시 
			if(rs.next()) {
				response.setContentType("application/json;charset=UTF-8");
				PrintWriter output = response.getWriter();
				JSONObject jsonObject = new JSONObject();
				
				jsonObject.put("name",rs.getString("name"));

				output.print(jsonObject);
				output.close();
			}else {
				// 회원 정보 조회 실패시 
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
			
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs !=null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(pstmt != null) {
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
		
		
		// DB 연동 - end 
	}

}
