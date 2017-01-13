package com.board.ex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.board.ex.dto.BDto;

public class BDao {

	DataSource dataSource;
	
	public BDao() {
		try {
			Context context = new InitialContext(); //context.xml 불러오는 부분
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<BDto> list(){
		
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection con = null;
		PreparedStatement prStmt = null;
		ResultSet resultSet = null;
		
		try {
			con = dataSource.getConnection();
			String query = "select bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent from mvc_board order by bGroup desc, bStep asc";
			prStmt = con.prepareStatement(query);
			resultSet = prStmt.executeQuery();
			
			while(resultSet.next()){
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				
				BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
				dtos.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if(resultSet != null) resultSet.close();
				if(prStmt != null) prStmt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return dtos;
	}
	
	public BDto contentView(String strID){
		upHit(strID);
		
		BDto dto =null;
		Connection con = null;
		PreparedStatement preStmt = null;
		ResultSet resultSet = null;
		
		try {
			con = dataSource.getConnection();
			
			String query = "select * from mvc_board where bId=?";
			preStmt = con.prepareStatement(query);
			preStmt.setInt(1, Integer.parseInt(strID));
			resultSet = preStmt.executeQuery();
			
			if(resultSet.next()){
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				
				dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preStmt != null) preStmt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return dto;
	}
	
	public void modify(String bId, String bName, String bTitle, String bContent){
		
		Connection con = null;
		PreparedStatement preStmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "update mvc_board set bName=?, bTitle=?, bContent=? where bId=?";
			preStmt = con.prepareStatement(query);
			preStmt.setString(1, bName);
			preStmt.setString(2, bTitle);
			preStmt.setString(3, bContent);
			preStmt.setInt(4, Integer.parseInt(bId));
			
			int rn = preStmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(preStmt !=  null) preStmt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	
	public void delete(String bId){
		Connection con = null;
		PreparedStatement preStmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "delete from mvc_board where bId=?";
			preStmt = con.prepareStatement(query);
			preStmt.setInt(1, Integer.parseInt(bId));
			
			int rn = preStmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(preStmt !=  null) preStmt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	
	public void write(String bName, String bTitle, String bContent){
		
		Connection con = null;
		PreparedStatement preStmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "insert into mvc_board(bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) values(mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0, 0)";
			preStmt = con.prepareStatement(query);
			preStmt.setString(1, bName);
			preStmt.setString(2, bTitle);
			preStmt.setString(3, bContent);
			
			int rn = preStmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(preStmt != null) preStmt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	private void upHit(String bId){
		
		Connection con = null;
		PreparedStatement preStmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "update mvc_board set bHit = bHit+1 where bId =?";
			preStmt = con.prepareStatement(query);
			preStmt.setString(1, bId);
			
			int rn = preStmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(preStmt != null) preStmt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void reply(String bId, String bName, String bTitle, String bContent, String bGroup, String bStep,
			String bIndent) {
		
		replyShape(bGroup, bStep);
		
		Connection con = null;
		PreparedStatement preStmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "insert into mvc_board(bId, bName, bTitle, bContent, bGroup, bStep, bIndent) values(mvc_board_seq.nextval, ?, ?, ?, ?,?,?)";
			preStmt = con.prepareStatement(query);
			preStmt.setString(1, bName);
			preStmt.setString(2, bTitle);
			preStmt.setString(3, bContent);
			preStmt.setInt(4, Integer.parseInt(bGroup));
			preStmt.setInt(5, Integer.parseInt(bStep)+1);
			preStmt.setInt(6, Integer.parseInt(bIndent)+1);
			
			int rn = preStmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(preStmt != null) preStmt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	private void replyShape(String strGroup, String strStep){
		Connection con = null;
		PreparedStatement preStmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "update mvc_board set bStep = bStep+1 where bGroup =? and bStep >?";
			preStmt = con.prepareStatement(query);
			preStmt.setString(1, strGroup);
			preStmt.setString(2, strStep);
			
			int rn = preStmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(preStmt != null) preStmt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public BDto replyView(String str) {

		BDto dto = null;
		Connection con = null;
		PreparedStatement preStmt = null;
		ResultSet resultSet = null;
		
		try {
			con = dataSource.getConnection();
			String query = "select * from mvc_board where bId =?";
			preStmt = con.prepareStatement(query);
			preStmt.setInt(1, Integer.parseInt(str));
			resultSet = preStmt.executeQuery();
			
			if(resultSet.next()){
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				
				dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				//if(resultSet != null) resultSet.close();
				if(preStmt != null) preStmt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
	}

}
