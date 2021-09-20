<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Add News</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="addnews.heading.text" var="heading_text" />
<fmt:message bundle="${loc}" key="news.title" var="title_text" />
<fmt:message bundle="${loc}" key="news.brief" var="brief_text" />
<fmt:message bundle="${loc}" key="news.content" var="content_text" />
<fmt:message bundle="${loc}" key="news.btn.add" var="add_btn" />
<fmt:message bundle="${loc}" key="addnews.cat.sport" var="cat_sport" />
<fmt:message bundle="${loc}" key="addnews.cat.economy" var="cat_economy" />
<fmt:message bundle="${loc}" key="addnews.cat.world" var="cat_world" />
<fmt:message bundle="${loc}" key="addnews.cat.text" var="cat_text" />	
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
		<input type="hidden" name="command" value="addnews" />
		<div class="container">
		    <h2><c:out value="${param.message}"/></h2>
			<h1>
				<c:out value="${heading_text}" />
			</h1>
			<c:set var="user_id" value="${sessionScope.user.id}"/>
			<input type="hidden" name="id_user" value="${user_id}"/>
			<label for="category"><c:out value="${cat_text}" /></label>
			 <select id="category" name="category">
				<option value="economy">${cat_economy}</option>
				<option value="sport">${cat_sport}</option>
				<option value="world">${cat_world}</option>
			</select>
			<p>${title_text}<br>
				<textarea name="title" cols="40" rows="1"></textarea>
			</p>

			<p>${brief_text}<br>
				<textarea name="brief" cols="40" rows="3"></textarea>
			</p>
            
			<p>${content_text}<br>
				<textarea name="content" cols="50" rows="10"></textarea>
			</p>
			<hr>
			<button type="submit">${add_btn}</button>
		</div>
	</form>
</body>
</html>