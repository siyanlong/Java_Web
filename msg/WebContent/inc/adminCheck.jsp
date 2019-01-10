<%@ page import="siyl.cit.msg.model.User"%>
<%@ page import="siyl.cit.msg.model.MessageException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	User u = (User)session.getAttribute("loginUser");
	if (u.getType() != 1) {
		throw new MessageException("没有权限操作该功能！");
	}
%>