<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My NEws</title>
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
		<br>
		<hr>
 <table border="1" bgcolor="#E6E6FA" cellspacing="0" cellpadding="2" width="100%">
          <tr>
               <td>Update</td>
               <td>title</td>
               <td>brief</td>
               <td>content</td>
               <td>category</td>
               <td>status</td>
               <td>date</td>
          </tr>

         <c:forEach var="news1" items="${news}">          
         <tr>
               
               <td>${news1.title}</td>
               <td>${news1.brief}</td>
               <td>${news1.content}</td>
               <td>${news1.category}</td>
               <td>${news1.status}</td>
               <td>${news1.newsDate}</td>
          </tr>
          </c:forEach>
     </table>
</body>
</html>