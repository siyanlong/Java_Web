<%@page import="siyl.cit.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="HisJSP_2.jsp?username=好不好使">HisJSP_2</a>
<%
	User user = new User(1, "zhubajie", "八戒");
	request.setAttribute("user", user);
%>
</body>
</html>