package member.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.dto.MemberInfo;
import member.service.MemberService;

@WebServlet("/join")


public class Join extends HttpServlet {

	public static List<MemberInfo> memberInfoList = new ArrayList<>();	
	
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
		
		MemberInfo memberInfo = new MemberInfo(id, pw,name,date);
	
		MemberService service = new MemberService();
		
		int statusCode = service.insertMemberInfo(memberInfo);
		
		response.setStatus(statusCode);
		
	}

}
