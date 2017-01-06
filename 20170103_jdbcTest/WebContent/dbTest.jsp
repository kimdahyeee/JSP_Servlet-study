<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%!
	Connection conn;
	Statement stmt;
	ResultSet resultS;
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1522:orcl";
	String uid = "dahye";
	String upw = "dahye";
	String query = "select * from member";
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
			conn = DriverManager.getConnection(url, uid, upw);
			stmt = conn.createStatement();
			resultS = stmt.executeQuery(query);
			
			while(resultS.next()){ //resultset 데이터 있으면
				String id = resultS.getString("id");
				String pw = resultS.getString("pw");
				String name = resultS.getString("name");
				String phone = resultS.getString("phone");
				
				out.println("아이디 : " +id + ",비밀번호" + pw + ",이름 : "+name + ",전화번호 : "+ phone + "<br>");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//이상이 있건 없건 무조건 실행!
			try{
				//생성했던 것 역순으로 자원해제
				if(resultS !=null)resultS.close();
				if(stmt != null)stmt.close();
				if(conn !=null) conn.close();
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
		
	%>
</body>
</html>