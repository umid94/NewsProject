<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>News Page</title>
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
	<form style="margin: 10px; display: inline; " action="Controller" method="post">
		<input type="hidden" name="command" value="go_to_profile_user_page" /> <input
			type="submit" value="profile Page" />
	    </form>
	<hr>
 <div style=" background-color: #92a8d1; text-align:center; ">
  <c:set var="news1" value="${news}"/>
			<h2>${news1.title}</h2>
			<br>
			<p>${news1.content}</p>
			<h6>${news1.newsDate}</h6>
</div>
</body>
</html>