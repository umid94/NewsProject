<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <form style="margin: 10px; display: inline; " action="Controller" method="post">
		<input type="hidden" name="command" value="go_to_main_page" /> <input
			type="submit" value="Home Page" />
	</form>
   <h1 style="color: red; text-align: center; " >You have introduced a non-existent Page !</h1>
   <h3 style="color: red; text-align: center; " >Please try again !!</h3>
</body>
</html>