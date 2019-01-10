<%@ page import="siyl.cit.msg.dao.DAOFactory"%>
<%@ page import="siyl.cit.msg.dao.IUserDao"%>
<%@ page import="siyl.cit.msg.model.User"%>
<%@ page import="siyl.cit.msg.model.MessageException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/adminCheck.jsp" %>
<%
	try {
		int id = Integer.parseInt(request.getParameter("id"));
		IUserDao userDao = DAOFactory.getUserDao();
		userDao.delete(id);
		response.sendRedirect("list.jsp");
	} catch (MessageException e) {
%>
	<h2 style="color:red">发生错误：<%=e.getMessage() %></h2>
<%
	}
%>