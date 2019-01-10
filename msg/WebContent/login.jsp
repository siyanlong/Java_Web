<%@ page import="siyl.cit.msg.model.MessageException"%>
<%@ page import="siyl.cit.msg.dao.DAOFactory"%>
<%@ page import="siyl.cit.msg.dao.IUserDao"%>
<%@ page import="siyl.cit.msg.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	try {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		IUserDao userDao = DAOFactory.getUserDao();
		User user = userDao.login(username, password);
		session.setAttribute("loginUser", user);
		response.sendRedirect(request.getContextPath() + "/admin/user/list.jsp");
		return;
	} catch (MessageException e) {
%>
	<h2 style="color:red">发生错误：<%=e.getMessage() %></h2>
<%
	}
%>
