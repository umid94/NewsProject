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
<fmt:setBundle basename="resources.localization.local" var="loc" />
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
		<input type="hidden" name="command" value="go_to_addnews_page" /> <input
			type="submit" value="addnews" />
	</form>
	<form action="Controller" method="post">
		<input type="hidden" name="command" value="get_disapproved_news" /> <input
			type="submit" value="for approval" />
	</form>
	<form action="Controller" method="post">
		<input type="hidden" name="command" value="get_deleted_news" /> <input
			type="submit" value="Deleted news" />
	</form>
	<font color=green size=20> <c:out value="${param.message}" />
	</font>
	 <form action="Controller" method="post">
	 <input type="hidden" name="command" value="delete_news" />
	<table border="1" bgcolor="#E6E6FA" cellspacing="0" cellpadding="2"
		width="100%">
		<tr>
		    <td>check</td>
            <td>update news</td>
			<td>title</td>
			<td>brief</td>
			<td>content</td>
			<td>category</td>
			<td>date</td>
		</tr>

		<c:forEach var="news1" items="${newses}">
			<tr>
			    <td><input type="checkbox" name="id_news" value="${news1.id}"/></td>
			    <td><a href="Controller?command=go_to_update_news_page&id_news=${news1.id}">Update News</a></td>
				<td>${news1.title}</td>
				<td>${news1.brief}</td>
				<td>${news1.content}</td>
				<td>${news1.category}</td>
				<td>${news1.newsDate}</td>
			</tr>
		</c:forEach>
	</table>
	<input type="submit" value="Delete News" />
      </form>
	<c:set var="count" value="${countNews}" />
	<c:forEach begin="1" end="${count}" var="i">
		<a href="Controller?command=all_news&page=${i}">${i}</a>
	</c:forEach>
</body>
</html>