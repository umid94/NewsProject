<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Admin Page</title>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="resources.localization.local" var="loc" />
<fmt:message bundle="${loc}" key="logout.btn" var="logout_btn" />
<fmt:message bundle="${loc}" key="news.btn.add" var="add_btn" />
<fmt:message bundle="${loc}" key="admin.unapproved.btn" var="unapproved_btn" />
<fmt:message bundle="${loc}" key="admin.deleted.btn" var="deleted_btn" />
<fmt:message bundle="${loc}" key="admin.delete.news.btn" var="delete_news_btn" />
<fmt:message bundle="${loc}" key="news.category" var="category_text" />
<fmt:message bundle="${loc}" key="news.btn.update" var="update_btn" />	
<fmt:message bundle="${loc}" key="news.title" var="title_text" />
<fmt:message bundle="${loc}" key="news.brief" var="brief_text" />
<fmt:message bundle="${loc}" key="news.content" var="content_text" />
<fmt:message bundle="${loc}" key="news.date" var="date_text" />
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
	
	<form style="margin: 10px; display: inline;" action="Controller" method="post">
		<input type="hidden" name="command" value="go_to_addnews_page" /> <input
			type="submit" value="${add_btn}" />
	</form>
	<form style="margin: 10px; display: inline;" action="Controller" method="post">
		<input type="hidden" name="command" value="get_disapproved_news" /> <input
			type="submit" value="${unapproved_btn}" />
	</form>
	<form style="margin: 10px; display: inline;" action="Controller" method="post">
		<input type="hidden" name="command" value="get_deleted_news" /> <input
			type="submit" value="${deleted_btn}" />
	</form>
	<form style="margin: 10px; display: inline;" action="Controller" method="post">
		<input type="hidden" name="command" value="logout" /> <input
			type="submit" value="${logout_btn}" />
	</form>
	<br>
	<font color=green size=20> <c:out value="${param.message}" /></font>
	 <form action="Controller" method="post">
	 <input type="hidden" name="command" value="delete_news" />
	 <input style="margin: 10px; display: inline;" type="submit" value="${delete_news_btn}" />
	<table border="1" bgcolor="#E6E6FA" cellspacing="0" cellpadding="2" width="100%">
		<tr>
		    <td>check</td>
            <td>${update_btn}</td>
			<td>${title_text}</td>
			<td>${brief_text}</td>
			<td>${content_text}</td>
			<td>${category_text}</td>
			<td>${date_text}</td>
		</tr>

		<c:forEach var="news1" items="${newses}">
			<tr>
			    <td><input type="checkbox" name="id_news" value="${news1.id}"/></td>
			    <td><a href="Controller?command=go_to_update_news_page&id_news=${news1.id}">${update_btn}</a></td>
				<td>${news1.title}</td>
				<td>${news1.brief}</td>
				<td>${news1.content}</td>
				<td>${news1.category}</td>
				<td>${news1.newsDate}</td>
			</tr>
		</c:forEach>
	</table>
    </form>
	<c:set var="count" value="${countNews}" />
	<c:forEach begin="1" end="${count}" var="i">
		<a href="Controller?command=all_news&page=${i}">${i}</a>
	</c:forEach>
</body>
</html>