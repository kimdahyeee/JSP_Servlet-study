<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
    <%!
    	Connection con;
    	PreparedStatement ps;
    	ResultSet rs;
    	
    	String driver = "oracle.jdbc.driver.OracleDriver";
    	String url = "jdbc:oracle:thin:@localhost:1522:orcl";
    	String uid = "dahye";
    	String upw = "dahye";
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%
		try{
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, uid, upw);
			int n;
			String query = "insert into member(id, pw, name, phone1) values(?,?,?,?)";
			ps = con.prepareStatement(query);
			
			ps.setString(1,"abc");
			ps.setString(2, "123");
			ps.setString(3, "홍길동");
			ps.setString(4, "010");
			n = ps.executeUpdate();
			
			if(n ==1){
				out.println("insert success");
			}else{
				out.println("insert fail");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs !=null) rs.close();
				if(ps != null) ps.close();
				if(con != null) con.close();
			}catch(Exception e){}
		}
	%>
	
	<br>
	<a href="memberDateVies.jsp"> 회원정보 보기</a>
</body>
</html>