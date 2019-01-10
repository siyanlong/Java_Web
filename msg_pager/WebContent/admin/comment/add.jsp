<%@page import="siyl.cit.msg.model.User"%>
<%@page import="siyl.cit.msg.model.Comment"%>
<%@page import="siyl.cit.msg.dao.DAOFactory"%>
<%@page import="siyl.cit.msg.dao.ICommentDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	int msgId = Integer.parseInt(request.getParameter("msgId"));
	String content = request.getParameter("content");
	ICommentDao commentDao = DAOFactory.getCommentDao();
	Comment comment = new Comment();
	comment.setContent(content);
	int userId = ((User)session.getAttribute("loginUser")).getId();
	commentDao.add(comment, userId, msgId);
	response.sendRedirect(request.getContextPath()+"/msg/show.jsp?id="+msgId);
%>