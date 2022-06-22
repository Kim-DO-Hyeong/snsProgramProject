package member.controller;

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

import member.dto.MemberInfo;
import member.service.MemberService;
import util.DatabaseManager;

@WebServlet("/findid")
public class FindId extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = (String) request.getParameter("name");
		
		MemberService service = new MemberService();
		
		String memberID = service.getMemberIDByName(name);
		
		// DB 에 일치하는 아이디가 있을시에 
		if(memberID !=null) {
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter output = response.getWriter();
			JSONObject jsonObject = new JSONObject();
			
			jsonObject.put("id",memberID);

			output.print(jsonObject);
			output.close();
		}else {
			// 아이디가 없다면 
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		
	}

}
