<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>Registration</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="resources.localization.local" var="loc" />
<fmt:message bundle="${loc}" key="registration.label.login" var="label_login" />
<fmt:message bundle="${loc}" key="registration.label.password" var="label_password" />
<fmt:message bundle="${loc}" key="registration.label.firstname"	var="label_firstname" />
<fmt:message bundle="${loc}" key="registration.label.lastname"	var="label_lastname" />
<fmt:message bundle="${loc}" key="registration.label.email"	var="label_email" />
<fmt:message bundle="${loc}" key="registration.placeholder.login" var="placeholder_login" />
<fmt:message bundle="${loc}" key="registration.placeholder.password" var="placeholder_password" />
<fmt:message bundle="${loc}" key="registration.placeholder.lastname" var="placeholder_lastname" />
<fmt:message bundle="${loc}" key="registration.placeholder.firstname" var="placeholder_firstname" />
<fmt:message bundle="${loc}" key="registration.placeholder.email" var="placeholder_email" />
<fmt:message bundle="${loc}" key="registration.heading_text" var="heading_text" />	
<fmt:message bundle="${loc}" key="registration.btn" var="btn_signin" />	
<style>
body {
    font-family: Arial, Helvetica, sans-serif;
    background-color: black;
}

* {
    box-sizing: border-box;
}

.container {
    padding: 16px;
    background-color: white;
}

input[type=text], input[type=password] {
    width: 100%;
    padding: 15px;
    margin: 5px 0 22px 0;
    display: inline-block;
    border: none;
    background: #f1f1f1;
}

input[type=text]:focus, input[type=password]:focus {
    background-color: #ddd;
    outline: none;
}

hr {
    border: 1px solid #f1f1f1;
    margin-bottom: 25px;
}

.registerbtn {
    background-color: #4CAF50;
    color: white;
    padding: 16px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 100%;
    opacity: 0.9;
}

.registerbtn:hover {
    opacity: 1;
}

a {
    color: dodgerblue;
}

.signin {
    background-color: #f1f1f1;
    text-align: center;
}
</style>
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
	<font color="red" size="20">
	<%
	   String mes = (String)request.getParameter("message");
	   if(mes != null){
		   out.print(mes);
	   }
	%>
	</font>
    <font color=red >
    <%
       String message =(String)request.getAttribute("validInfo");
       if(message != null){
    	   out.print(message);
       }
    %>
    </font>
	<form action="Controller" method="post">
	<input type="hidden" name="command" value="registration_new_user" /> >
    <div class="container">
    <h1><c:out value="${heading_text}"></c:out></h1>
    
    <label for="firstname"><b>${label_firstname}</b></label>
    <input type="text" placeholder="${placeholder_firstname}" name="firstname" value="" required>
    
    <label for="lastname"><b>${label_lastname}</b></label>
    <input type="text" placeholder="${placeholder_lastname}" name="lastname" value="" required>
    
    <label for="email"><b>${label_email}</b></label>
    <input type="text" placeholder="${placeholder_email}" name="email" value="" required>
    
    <label for="login"><b>${label_login}</b></label>
    <input type="text" placeholder="${placeholder_login}" name="login" value="" required>
    
    <label for="password"><b>${label_password}</b></label>
    <input type="password" placeholder="${placeholder_password}" name="password" value="" required>
    <hr>

    <button type="submit" class="registerbtn">${btn_signin}</button>
  </div>
</form>
</body>
</html>