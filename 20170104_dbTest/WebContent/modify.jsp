<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

	<%!
		Connection conn;
		Statement stmt;
		ResultSet resultS;
		
		String name, id, pw, phone1, phone2, phone3, gender;
	%>
	
	<%
		id = (String)session.getAttribute("id");
	
		String query = "select * from member where id='" +id + "'";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:orcl", "dahye", "dahye");
		stmt = conn.createStatement();
		resultS = stmt.executeQuery(query);
		
		while(resultS.next()){
			name = resultS.getString("name");
			id = resultS.getString("id");
			pw = resultS.getString("pw");
			phone1 = resultS.getString("phone1");
			phone2 = resultS.getString("phone2");
			phone3 = resultS.getString("phone3");
			gender = resultS.getString("gender");
		}
	%>
	
	<form action="ModifyOk" method="post">
		�̸� : <input type="text" name="name" size="10" value=<%=name %>><br>
		���̵� : <%=id %><br>
		��й�ȣ: <input type="password" name="pw" size="10"><br>
		��ȭ��ȣ: <select name="phone1">
			<option value="010">010</option>
			<option value="016">016</option>
			<option value="011">011</optioin>
		</select> - 
		<input type="text" name="phone2" size="5" value=<%=phone2 %>> - <input type="text" name="phone3" size="5" value=<%=phone3 %>>
		��������: <%if(gender.equals("man")){ %><input type="radio" name="gender" value="man" checked="checked">�� &nbsp;<input type="radio" name="gender" value="woman">��<br>
		<%}else{ %><input type="radio" name="gender" value="man" >�� &nbsp;<input type="radio" name="gender" value="woman" checked="checked">��<br>
		<%} %>
		<input type="submit" value="��������"><input type="reset" value="���">
	
	</form>
</body>
</html>