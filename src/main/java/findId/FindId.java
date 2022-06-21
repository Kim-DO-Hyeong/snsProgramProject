package findId;

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

import org.json.JSONObject;

import Util.DatabaseManager;
import signUp.Join;
import signUp.Member;

@WebServlet("/findid")
public class FindId extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = (String) request.getParameter("name");
		
		// DB 연동 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DatabaseManager.getConnection();

			String sql = "SELECT * FROM membertbl WHERE name = ?";
			pstmt = DatabaseManager.getPstmt(conn, sql);
			pstmt.setString(1, name);
			
			rs = pstmt.executeQuery();
			
			// DB 에 일치하는 아이디가 있을시에 
			if(rs.next()) {
				response.setContentType("application/json;charset=UTF-8");
				PrintWriter output = response.getWriter();
				JSONObject jsonObject = new JSONObject();
				
				jsonObject.put("id",rs.getString("memberID"));

				output.print(jsonObject);
				output.close();
			}else {
				// 아이디가 없다면 
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
			
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.closeResultSet(rs);
			DatabaseManager.closePstmt(pstmt);
			DatabaseManager.closeConn(conn);
		}
				
				
				// DB 연동 - end 
		
	}

}
