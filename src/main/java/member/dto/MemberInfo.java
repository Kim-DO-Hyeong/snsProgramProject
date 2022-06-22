package member.dto;

import java.time.LocalDate;

public class MemberInfo {
	private String id;
	private String pw;
	private String name;
	private LocalDate birth;
	
	public MemberInfo(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}
	
	public MemberInfo(String id, String pw, String name, LocalDate birth) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.birth = birth;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirth() {
		return birth;
	}

	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}
	
	
}
