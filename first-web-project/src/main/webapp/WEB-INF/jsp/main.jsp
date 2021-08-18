<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Breaking News</title>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="main.authorization.button.name" var="authorization_btn" />
<fmt:message bundle="${loc}" key="main.registration.button.name" var="registration_btn" />
<fmt:message bundle="${loc}" key="main.logout.button.name" var="logout_btn" />
<fmt:message bundle="${loc}" key="main.heading_text" var="heading_text" />	 	
<style type="text/css">
body{
    font-family: Arial, Helvetica, sans-serif;
    margin: 0;
}
.headbar{
   background-color: powderblue; 
   
}
.headbar h1{
   color: white;
   
}
</style>
</head>
<body>
<div class="headbar">
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
	<h1><c:out value="${heading_text}"></c:out></h1>
	<form action="Controller" method="post">
		<input type="hidden" name="command" value="go_to_registration_page"/>
		<input type="submit" value="${registration_btn}" />
	</form>
	<br />
	<form action="Controller" method="post">
	    <input type="hidden" name="command" value="go_to_authorization_page"/>
		<input type="submit" value="${authorization_btn}" />
	</form>
	<c:if test="${sessionScope.user != null}">
				<form action="Controller" method="post">
					<input type="hidden" name="command" value="logout" /> <input
						type="submit"
						value="${logout_btn}" />
				</form>
	</c:if>
	</div>
	<br/>
	
		<table>
		<c:forEach var="oneNews" items="${newses}">
				<h3>${oneNews.title}</h3>
				<p>${oneNews.brief}</p>
				<br>
			
		</c:forEach>
	</table>
	
</body>
</html>