package member.service;

import java.time.LocalDate;

import member.MemberPw;
import member.dao.MemberDao;
import member.dto.MemberInfo;

public class MemberService {
	
	public int insertMemberInfo(MemberInfo memberInfo){
		
		MemberDao dao = new MemberDao();
		
		boolean result = dao.insertMemberInfo(memberInfo);
		
		if (result) {
			return 200;
		} else {
			return 400;
		}
		
	}

	public MemberInfo getMemberInfoByIdAndPw(MemberInfo loginmemberInfo) {
		MemberDao dao = new MemberDao();
		
		MemberInfo memberInfo = dao.getMemberInfoByIdAndPw(loginmemberInfo);
		
		if(memberInfo !=null) {
			return memberInfo;
		}else {
			return null;
		}
		
	}

	public String getMemberIDByName(String name){
		MemberDao dao = new MemberDao();
		
		String memberID = dao.getMemberIDByName(name);
		
		return memberID;
	}
	
	public String getMemberPWByMemberID(String memberId){
		
		MemberDao dao = new MemberDao();
		
		String memberPw = dao.getMemberPWByMemberID(memberId);
		
		MemberPw pw = new MemberPw();

		
		if(memberPw != null) {
			memberPw = pw.turnHalfIntoStars(memberPw);
		}
		
		return memberPw;
		
	}
	
public String getMemberPWByNameAndBirth(String name, LocalDate birth){
		
		MemberDao dao = new MemberDao();
		
		String memberPw = dao.getMemberPWByNameAndBirth(name, birth);
		
		MemberPw pw = new MemberPw();
		if(memberPw != null) {
			memberPw = pw.turnHalfIntoStars(memberPw);
		}
		return memberPw;
		
	}
}
