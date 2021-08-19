<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Admin Page</title>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
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
	<h1>ADMIN PAGE YOU YOU</h1>
	<form action="Controller" method="post">
		<input type="hidden" name="command" value="logout" /> <input
			type="submit" value="logout" />
	</form>
	<form action="Controller" method="post">
		<input type="hidden" name="command" value="go_to_addnews_page" />
		<input type="submit" value="addnews" />
	</form>
	<font color=green size=20> <%
      String mess = (String)request.getParameter("message");
      if(mess != null){
    	  out.print(mess);
      }
    %>
	</font>
	<table>
		<c:forEach var="oneNews" items="${newsess}">
			<h3>${oneNews.title}</h3>
			<p>${oneNews.content}</p>
		</c:forEach>
	</table>
</body>
</html>