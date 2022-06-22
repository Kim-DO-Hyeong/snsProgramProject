package chat.controller;

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

import member.controller.Join;
import member.dto.MemberInfo;
import util.DatabaseManager;

@WebServlet("/chat")
public class Chat extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		MemberInfo memberInfo= (MemberInfo)session.getAttribute("memberInfo");

		response.setContentType("application/json;charset=UTF-8");
		PrintWriter output = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("name",memberInfo.getName());

		output.print(jsonObject);
		output.close();
		
		
	}

}
