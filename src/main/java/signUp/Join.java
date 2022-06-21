package signUp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.DatabaseManager;

@WebServlet("/join")


public class Join extends HttpServlet {

	public static List<Member> memberInfoList = new ArrayList<>();	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 회원가입 정보 가져오기
		request.setCharacterEncoding("UTF-8");

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");

		String birth = request.getParameter("birth");
		LocalDate date = LocalDate.parse(birth);
		// 문자열인 birth를 Date 타입으로 형변환

		// DB 연동
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseManager.getConnection();

			String sql = "INSERT INTO membertbl VALUES(?,?,?,?)";
			pstmt = DatabaseManager.getPstmt(conn, sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setDate(4, Date.valueOf(date));

			int count = pstmt.executeUpdate();

			if (count == 1) {
				response.setStatus(HttpServletResponse.SC_OK);
			} else {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.closePstmt(pstmt);
			DatabaseManager.closeConn(conn);
		}
		// DB 연동 -end

	}

}
