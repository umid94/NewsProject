<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Category News</title>
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
	<h1>CATEGORY NEWS PAGE</h1>
	<hr>
	<c:out value="${param.errMessage}"/>
	<table>
		<c:forEach var="news1" items="${categNews}">
		<a href="Controller?command=go_to_one_news_page&id_news=${news1.id}">
			<h3>${news1.title}</h3>
			<p>${news1.content}</p>
		</a>
		</c:forEach>
	</table>
</body>
</html>