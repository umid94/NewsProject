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
		<form action="Controller" method="post">
			<input type="hidden" name="command" value="go_to_main_page" /> <input
				type="submit" value="Home Page" />
		</form>
		<form action="Controller" method="post">
			<input type="hidden" name="command" value="go_to_category_page" />
			<input type="hidden" name="category" value="economy" />
			<input type="submit" value="Economy" />
		</form>
		<form action="Controller" method="post">
			<input type="hidden" name="command" value="go_to_category_page" />
			<input type="hidden" name="category" value="sport" /> 
			 <input	type="submit" value="Sport" />
		</form>
		<form action="Controller" method="post">
			<input type="hidden" name="command" value="go_to_category_page" />
			 <input type="hidden" name="category" value="world" /> 
			 <input	type="submit" value="World" />
		</form>
	<font color=green size=20> 
	<c:out value="${param.message}" />
	</font>
	<c:set var="newses" value="${sessionScope.newse}"/>
	<table>
		<c:forEach var="news1" items="${newse}">
		<a href="Controller?command=go_to_one_news_page&id_news=${news1.id}">
			<h3>${news1.title}</h3>
			<p>${news1.content}</p>
		</a>
		</c:forEach>
	</table>
</body>
</html>
