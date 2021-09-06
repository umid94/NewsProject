<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Disapproved News</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="resources.localization.local" var="loc" />
<fmt:message bundle="${loc}" key="news.status" var="status_text" />
<fmt:message bundle="${loc}" key="news.btn.update" var="update_btn" />
<fmt:message bundle="${loc}" key="news.title" var="title_text" />
<fmt:message bundle="${loc}" key="news.brief" var="brief_text" />
<fmt:message bundle="${loc}" key="news.content" var="content_text" />
<fmt:message bundle="${loc}" key="news.date" var="date_text" />
<fmt:message bundle="${loc}" key="aproved.news" var="approved_btn" />
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
	<br>
	<form style="margin: 10px; display: inline; " action="Controller" method="post">
		<input type="hidden" name="command" value="go_to_admin_page" /> <input
			type="submit" value="Admin Page" />
	</form>
	<hr>
	<font color=green size=15> <c:out value="${param.message}" /></font>
	<form action="Controller" method="post">
		<input type="hidden" name="command" value="approved_of_news" />
		<input type="submit" value="${approved_btn}" />
		<br>
		<table border="1" bgcolor="#E6E6FA" cellspacing="0" cellpadding="2"
			width="100%">
			<tr>
				<td>check</td>
				<td>${title_text}</td>
				<td>${brief_text}</td>
				<td>${content_text}</td>
				<td>${status_text}</td>
				<td>${date_text}</td>
			</tr>

			<c:forEach var="news1" items="${news}">
				<tr>
					<td><input type="checkbox" name="id_news" value="${news1.id}" /></td>
					<td>${news1.title}</td>
					<td>${news1.brief}</td>
					<td>${news1.content}</td>
					<td>${news1.status}</td>
					<td>${news1.newsDate}</td>
				</tr>
			</c:forEach>
		</table>
	</form>

</body>
</html>
