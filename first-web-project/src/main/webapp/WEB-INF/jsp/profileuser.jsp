<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Page</title>
</head>
<body>
	<form action="Controller" method="post">
		<input type="hidden" name="local" value="ru" /> <input type="hidden"
			name="command" value="CHANGE_LOCALE" /> <input type="submit"
			value="RU" /><br />
	</form>

	<form action="Controller" method="post">
		<input type="hidden" name="local" value="en" /> <input type="hidden"
			name="command" value="CHANGE_LOCALE" /> <input type="submit"
			value="EN" /><br />
	</form>
	<hr>
		<form action="Controller" method="post">
			<input type="hidden" name="command" value="logout" /> <input
				type="submit" value="logout" />
		</form>
	<font color=green size=20> <%
      String mess = (String)request.getParameter("message");
      if(mess != null){
    	  out.print(mess);
      }
      
    %>
	</font>
	<table>
		<c:forEach var="news1" items="${newse}">
			<h3>${news1.title}</h3>
			<p>${news1.content}</p>
		</c:forEach>
	</table>
</body>
</html>
