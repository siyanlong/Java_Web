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
<%=request.getParameter("username") %>
<%
	//User user = (User)request.getAttribute("user");
	//User user = (User)session.getAttribute("user");
	User user = (User)application.getAttribute("user");
%>
<%=user %>
</body>
</html>