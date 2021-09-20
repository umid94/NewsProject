<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Page</title>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="news.category" var="category_text" />
<fmt:message bundle="${loc}" key="news.title" var="title_text" />
<fmt:message bundle="${loc}" key="news.brief" var="brief_text" />
<fmt:message bundle="${loc}" key="news.content" var="content_text" />
<fmt:message bundle="${loc}" key="news.date" var="date_text" />
<fmt:message bundle="${loc}" key="logout.btn" var="logout_btn" />
<fmt:message bundle="${loc}" key="news.btn.add" var="add_btn" />
<fmt:message bundle="${loc}" key="myNews.btn" var="myNews_btn" />
<fmt:message bundle="${loc}" key="addnews.cat.sport" var="cat_sport" />
<fmt:message bundle="${loc}" key="addnews.cat.economy" var="cat_economy" />
<fmt:message bundle="${loc}" key="addnews.cat.world" var="cat_world" />



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
		<input type="hidden" name="command" value="go_to_addnews_page" /> <input
			type="submit" value="${add_btn}" />
	</form>
	<form style="margin: 10px; display: inline; " action="Controller" method="post">
		<input type="hidden" name="command" value="get_user_news" /> <input
			type="submit" value="${myNews_btn}" />
	</form>
	<form style="margin: 10px; display: inline; " action="Controller" method="post">
		<input type="hidden" name="command" value="go_to_category_page" /> <input
			type="hidden" name="category" value="economy" /> <input
			type="submit" value="${cat_economy}" />
	</form>
	<form style="margin: 10px; display: inline; " action="Controller" method="post">
		<input type="hidden" name="command" value="go_to_category_page" /> <input
			type="hidden" name="category" value="sport" /> <input type="submit"
			value="${cat_sport}" />
	</form>
	<form style="margin: 10px; display: inline; " action="Controller" method="post">
		<input type="hidden" name="command" value="go_to_category_page" /> <input
			type="hidden" name="category" value="world" /> <input type="submit"
			value="${cat_world}" />
	</form>
	<form style="margin: 10px; display: inline; position: right; " action="Controller" method="post">
		<input type="hidden" name="command" value="logout" /> <input
			type="submit" value="${logout_btn}" />
	</form>
	<br/>
	<font color=green size=10> <c:out value="${param.message}" /></font>	
	<font color=red size=10> <c:out value="${param.errMessage}" /></font>
	<font color=blue size=10> <c:out value="${param.userMess}" /></font>
	<table border="1" bgcolor="#E6E6FA" cellspacing="0" cellpadding="2" width="100%" >
          <tr>
              
                <td>${title_text}</td>
               <td>${brief_text}</td>
               <td>${content_text}</td>
               <td>${category_text}</td>
               <td>${date_text}</td>
          </tr>

         <c:forEach var="news1" items="${newses}">          
         <tr>
               <td><a href="Controller?command=go_to_one_news_page&id_news=${news1.id}">
				${news1.title}
			      </a>
			   </td>
               <td>${news1.brief}</td>
               <td>${news1.content}</td>
               <td>${news1.category}</td>
               <td>${news1.newsDate}</td>
          </tr>
          </c:forEach>
     </table>
	<c:set var="count" value="${countNews}" />
    <c:forEach begin="1" end="${count}" var="i">
       <a href="Controller?command=all_news&page=${i}">${i}</a>
    </c:forEach>
</body>
</html>
