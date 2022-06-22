package member.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import member.dto.MemberInfo;
import util.DatabaseManager;

public class MemberDao {
	
	public boolean insertMemberInfo(MemberInfo memberInfo){
			Connection conn = null;
			PreparedStatement pstmt = null;
	
			try {
				conn = DatabaseManager.getConnection();
	
				String sql = "INSERT INTO member_info(memberID,memberPW,name,birth) VALUES(?,?,?,?)";
				pstmt = DatabaseManager.getPstmt(conn, sql);
				pstmt.setString(1, memberInfo.getId());
				pstmt.setString(2, memberInfo.getPw());
				pstmt.setString(3, memberInfo.getName());
				pstmt.setDate(4, Date.valueOf(memberInfo.getBirth()));
	
				pstmt.executeUpdate();
	
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally {
				DatabaseManager.closePstmt(pstmt);
				DatabaseManager.closeConn(conn);
			}
			
	}

	public MemberInfo getMemberInfoByIdAndPw(MemberInfo memberInfo){
		// DB 연동
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				MemberInfo selectMemberInfo = null;
				
				try {
					conn = DatabaseManager.getConnection();

					String sql = "SELECT * FROM member_info WHERE memberID = ? AND memberPW = ?";
					
					pstmt = DatabaseManager.getPstmt(conn, sql);
					pstmt.setString(1, memberInfo.getId());
					pstmt.setString(2, memberInfo.getPw());
					
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						
						String memberID = rs.getString("memberID");
						String memberPW = rs.getString("memberPW");
						String name = rs.getString("name");
						String date  = rs.getString("birth");

						LocalDate birth = LocalDate.parse(date);
						
						selectMemberInfo = new MemberInfo(memberID, memberPW,name,birth);
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					DatabaseManager.closeResultSet(rs);
					DatabaseManager.closePstmt(pstmt);
					DatabaseManager.closeConn(conn);
				}
				
				return selectMemberInfo;
				
	}
	
	public String getMemberIDByName(String name){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String memberID = null;
		
		try {
			conn = DatabaseManager.getConnection();

			String sql = "SELECT * FROM member_info WHERE name = ?";
			pstmt = DatabaseManager.getPstmt(conn, sql);
			pstmt.setString(1, name);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				memberID = rs.getString("memberID");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.closeResultSet(rs);
			DatabaseManager.closePstmt(pstmt);
			DatabaseManager.closeConn(conn);
		}
		
		return memberID;
				
	}
	
	public String getMemberPWByMemberID(String memberId){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String memberPw = null;
		try {
			conn = DatabaseManager.getConnection();

			String sql = "SELECT * FROM member_info WHERE memberID = ?";
			pstmt = DatabaseManager.getPstmt(conn, sql);
			pstmt.setString(1, memberId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				memberPw = rs.getString("memberPW");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.closeResultSet(rs);
			DatabaseManager.closePstmt(pstmt);
			DatabaseManager.closeConn(conn);
		}
		return memberPw;
	}
	
	public String getMemberPWByNameAndBirth(String name, LocalDate birth){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String memberPw = null;
		try {
			conn = DatabaseManager.getConnection();

			String sql = "SELECT * FROM member_info WHERE name = ? AND birth = ?";
			pstmt = DatabaseManager.getPstmt(conn, sql);
			pstmt.setString(1, name);
			pstmt.setDate(2, Date.valueOf(birth));
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				memberPw = rs.getString("memberPW");
			}
			
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.closeResultSet(rs);
			DatabaseManager.closePstmt(pstmt);
			DatabaseManager.closeConn(conn);
		}
				
		return memberPw;		
	}
}
