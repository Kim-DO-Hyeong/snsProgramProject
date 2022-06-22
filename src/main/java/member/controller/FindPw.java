package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import member.service.MemberService;

@WebServlet("/findpw")
public class FindPw extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = (String) request.getParameter("id");

		MemberService service = new MemberService();
		
		String memberPw = service.getMemberPWByMemberID(id);
		
		
		// DB 에 일치하는 아이디가 있을시에 
		if(memberPw != null) {
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter output = response.getWriter();
			JSONObject jsonObject = new JSONObject();
			
			jsonObject.put("pw",memberPw);

			output.print(jsonObject);
			output.close();
		}else {
			// 아이디가 없다면 
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		
	}


}
