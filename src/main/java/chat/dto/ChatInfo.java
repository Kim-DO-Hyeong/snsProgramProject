package chat.dto;

import java.time.LocalDateTime;

public class ChatInfo {
	private String memberID;
	private String anotherMemberID;
	private String text;
	private LocalDateTime date;
	
	public ChatInfo(String memberID, String anotherMemberID, String text, LocalDateTime date) {
		this.memberID = memberID;
		this.anotherMemberID = anotherMemberID;
		this.text = text;
		this.date = date;
	}

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public String getAnotherMemberID() {
		return anotherMemberID;
	}

	public void setAnotherMemberID(String anotherMemberID) {
		this.anotherMemberID = anotherMemberID;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	
	
}
