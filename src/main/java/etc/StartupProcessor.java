package etc;

import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import signUp.Join;
import signUp.Member;

// 서버가 시작할 때 필요한 것을 준비해주는 서블릿 
// 1. 몇명의 회원 정보를 저장 
// 2. ~~~
@WebServlet(urlPatterns = {"/StartupProcessor"}, loadOnStartup = 1)
	// loadOnStartUp 
public class StartupProcessor extends HttpServlet {

	@Override
	public void init() throws ServletException {
		LocalDate date = LocalDate.parse("2022-05-26");
		
		Member m1 = new Member("id@asd","asdasd21","도",date);
		Member m2 = new Member("asd@asd","asd","김도형",date);
		Member m3 = new Member("as@asd","asd","a",date);
		
		Join.memberInfoList.add(m1);
		Join.memberInfoList.add(m2);
		Join.memberInfoList.add(m3);
		
	}

	
}
