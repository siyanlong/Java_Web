<%@ page import="siyl.cit.shop.model.ShopException"%>
<%@ page import="siyl.cit.shop.dao.DAOFactory"%>
<%@ page import="siyl.cit.shop.dao.IUserDao"%>
<%@ page import="siyl.cit.shop.model.User"%>
<%@ page import="siyl.cit.shop.util.ValidateUtil"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	int id = Integer.parseInt(request.getParameter("id"));
	String password = request.getParameter("password");
	String nickname = request.getParameter("nickname");
	boolean validate = ValidateUtil.validateNull(request, new String[] {"password", "nickname"});
	if (!validate) {
%>
	<jsp:forward page="updateInput.jsp"></jsp:forward>
<%
	}
	IUserDao userDao = DAOFactory.getUserDao();
	User user = userDao.load(id);
	user.setPassword(password);
	user.setNicknanme(nickname);
	
	try {
		userDao.update(user);
		response.sendRedirect("list.jsp");
		return;
	} catch (ShopException e) {
%>
	<h2 style="color:red">发生错误：<%=e.getMessage() %></h2>
<%
	}
%>