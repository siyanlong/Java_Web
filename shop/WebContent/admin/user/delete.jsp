<%@ page import="siyl.cit.shop.dao.DAOFactory"%>
<%@ page import="siyl.cit.shop.dao.IUserDao"%>
<%@ page import="siyl.cit.shop.model.User"%>
<%@ page import="siyl.cit.shop.model.ShopException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	try {
		int id = Integer.parseInt(request.getParameter("id"));
		IUserDao userDao = DAOFactory.getUserDao();
		userDao.delete(id);
		response.sendRedirect("list.jsp");
	} catch (ShopException e) {
%>
	<h2 style="color:red">发生错误：<%=e.getMessage() %></h2>
<%
	}
%>