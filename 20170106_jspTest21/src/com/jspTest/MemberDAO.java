package com.jspTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {

	private static final int MEMBER_NONEXISTENT= 0;
	private static final int MEMBER_EXISTENET= 1;
	private static final int MEMBER_JOIN_FAIL= 0;
	private static final int MEMBER_JOIN_SUCCESS= 1;
	private static final int MEMBER_LOGIN_PW_NO_GOOD= 0;
	private static final int MEMBER_LOGIN_SUCCESS= 1;
	private static final int MEMBER_LOGIN_IS_NOT= -1;
	
	private static MemberDAO instance = new MemberDAO(); //자신이 자신을 선언하는 선언문이 있고,
	
	
	private MemberDAO() {
		// 특이하게도 생성자는private이다.
	}
	
	public static MemberDAO getInstance(){
		return instance;
		//싱글턴 패턴.. 자기자신의 객체를 단 한개만 만들어서 여러 공간에서 사용가능.
	}
	
	public int confirmId(String id){
		int ri=0;
		
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query ="select id from members where id =?";
		
		try{
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				ri = MemberDAO.MEMBER_EXISTENET;
			}else{
				ri = MemberDAO.MEMBER_NONEXISTENT;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
	}
	
	private Connection getConnection() {
		
		Context context =null;
		DataSource dataSource = null;
		Connection con = null;
		
		try {
			context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
			con = dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public int insertMember(MemberDTO dto){
		
		int ri=0;
		
		Connection con =null;
		PreparedStatement pstmt = null;
		String query = "insert into members values(?,?,?,?,?,?)";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.geteMail());
			pstmt.setTimestamp(5, dto.getrDate());
			pstmt.setString(6, dto.getAddress());
			pstmt.executeUpdate();
			ri= MemberDAO.MEMBER_JOIN_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
	}
}
