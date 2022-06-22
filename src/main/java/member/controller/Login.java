package member.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.dto.MemberInfo;
import member.service.MemberService;
import util.DatabaseManager;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MemberInfo memberInfo = new MemberInfo(id,pw);
		
		MemberService service = new MemberService();
		
		MemberInfo selectMemberInfo = service.getMemberInfoByIdAndPw(memberInfo);
				
		// 로그인에 아이디와 비밀번호가 일치할때 ( sql )
		if(selectMemberInfo != null ) {
			HttpSession session = request.getSession();
			session.setAttribute("isLogin", true);
			session.setAttribute("memberInfo", selectMemberInfo);
			session.setMaxInactiveInterval(3600);
			// 상태코드 200 생략 
		}else {
			// 로그인에 실패했을시 
			// 상태코드 400 전송
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		
	}

}
