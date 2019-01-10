<%@ page import="siyl.cit.shop.dao.DAOFactory"%>
<%@ page import="siyl.cit.shop.dao.IUserDao"%>
<%@ page import="siyl.cit.shop.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	int id = Integer.parseInt(request.getParameter("id"));
	IUserDao userDao = DAOFactory.getUserDao();
	User user = userDao.load(id);
	if (user.getStatus() == 0) {
		user.setStatus(1);
	} else {
		user.setStatus(0);
	}
	userDao.update(user);
	response.sendRedirect("list.jsp");
%>
</body>
</html>