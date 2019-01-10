<%@page import="siyl.cit.msg.util.ValidateUtil"%>
<%@page import="siyl.cit.msg.dao.DAOFactory"%>
<%@page import="siyl.cit.msg.dao.IMessageDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int id = Integer.parseInt(request.getParameter("id"));
	IMessageDao msgDao = DAOFactory.getMessageDao();
	boolean flag = ValidateUtil.checkAdminOrSelf(session, msgDao.load(id).getUserId());
	if(!flag) {
%>
	<h2 style="color:red">你没有权限删除该留言！</h2>
<%		
	} else {
		msgDao.delete(id);
		response.sendRedirect(request.getContextPath()+"/msg/list.jsp");
	}
%>
