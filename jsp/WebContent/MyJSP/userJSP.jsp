<%@ page import="siyl.cit.model.User"%>
<%@ page import="siyl.cit.model.UserInit"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%
	List<User> users = UserInit.initUser();
%>
</head>
<body>
<%=request.getParameter("userName") %>
<table width="700px" align="center" border="1">
<tr align="center">
	<td>ID</td><td>用户名</td><td>用户昵称</td>
</tr>
<%
	for (User currUser : users) {
%>
<tr align="center">
	<td><%=currUser.getId() %></td>
	<td><%=currUser.getUserName() %></td>
	<td><%=currUser.getNickName() %></td>
</tr>
<%
	}
%>
</table>
</body>
</html>