<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="siyl.cit.shop.dao.DAOFactory"%>
<%@ page import="siyl.cit.shop.dao.IUserDao"%>
<%@ page import="siyl.cit.shop.model.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	session.invalidate();
	response.sendRedirect(request.getContextPath() + "/loginInput.jsp");
%>
</body>
</html>