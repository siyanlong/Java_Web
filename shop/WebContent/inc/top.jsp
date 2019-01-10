<%@ page import="siyl.cit.shop.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	User user = (User)session.getAttribute("loginUser");
%>
<div style="text-align:right;border-bottom:1px solid #000;">
	欢迎[<%=user.getNicknanme() %>]使用我们的系统！<a href="<%=request.getContextPath() %>/admin/user/list.jsp">用户管理</a>
	&nbsp;<a href="<%=request.getContextPath() %>/logout.jsp">修改个人信息</a>
	&nbsp;<a href="<%=request.getContextPath() %>/logout.jsp">退出系统</a>
</div>
