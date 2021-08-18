<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>Profile User</title>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
</head>
<body>
  <form action="Controller" method="post">
		<input type="hidden"
			name="local" value="ru" />
		<input type="hidden"
			name="command" value="CHANGE_LOCALE" /> <input type="submit"
			value="RU" /><br />
	</form>

	<form action="Controller" method="post">
	<input type="hidden"
			name="local" value="en" />
		 <input type="hidden"
			name="command" value="CHANGE_LOCALE" /> <input type="submit"
			value="EN" /><br />
	</form>
	<hr>
	<c:if test="${sessionScope.user != null}">
				<form action="Controller" method="post">
					<input type="hidden" name="command" value="logout" /> <input
						type="submit"
						value="LOgOUT" />
				</form>
	</c:if>
	<font color=green size=20 >
    <%
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