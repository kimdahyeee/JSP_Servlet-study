<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

	<c:set var="varName" value="varValue"/>
	varName: <c:out value="${varName }"/><br>
	
	<c:remove var="varName"/>
	varName: <c:out value="${varName }"/><br>
	
	<c:forEach var="fe" begin="0" end="100" step="5">
		${fe }&nbsp;
	</c:forEach>
	<br>
	<c:catch var="error">
		<%=2/0 %>
	</c:catch>
	<c:out value="${error }"/>
	
	<c:if test="${1+2==3 }">
	1+2=3
	</c:if>
	<c:if test="${1+2!=3 }">
	1+2!=3
	</c:if>
</body>
</html>