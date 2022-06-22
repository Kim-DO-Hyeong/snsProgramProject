package member;

public class MemberPw {
	public String turnHalfIntoStars(String memberPw) {
		int MidPwLength = 0;
		MidPwLength= memberPw.length() / 2;
		
		if(memberPw.length() % 2 != 0) {
			memberPw = memberPw.substring(0, MidPwLength+1);
		}else {
			memberPw = memberPw.substring(0, MidPwLength);	
		}
		
		String star = "";
		for(int i =0; i<MidPwLength; i++) {
			star=star+"*";
		}
		
		memberPw = memberPw+star;
	
	
		return memberPw;
	}
}
