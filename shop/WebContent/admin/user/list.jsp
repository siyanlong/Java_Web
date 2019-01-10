<%@ page import="siyl.cit.shop.dao.DAOFactory"%>
<%@ page import="siyl.cit.shop.dao.IUserDao"%>
<%@ page import="siyl.cit.shop.model.User"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="inc.jsp">
	<jsp:param value="列表" name="op"/>
</jsp:include>
<%
	IUserDao userDao = DAOFactory.getUserDao();
	List<User> users = userDao.list();
	User loginUser = (User)session.getAttribute("loginUser");
%>
</head>
<body>
<table align="center" border="1" width="800">
	<tr>
		<td>用户标识</td><td>用户名</td><td>用户密码</td><td>用户昵称</td><td>操作</td>
		<td>用户类型</td><td>用户状态</td>
	</tr>
	<%
		for (User user : users) {
	%>
		<tr>
			<td><%=user.getId() %></td>
			<td><%=user.getUsername() %></td>
			<td><%=user.getPassword() %></td>
			<td><%=user.getNicknanme() %></td>
			<td>
			<%
				if (user.getType() == 0) {
					//out.print("普通用户");
			%>
				普通用户&nbsp;&nbsp;
				<% 
					if (loginUser.getType() == 1) {
				%>
				<a href="setType.jsp?id=<%=user.getId() %>">设置管理员</a>
				<%
					}
				%>
				
			<%
				}
				else {
			%>
				管理员&nbsp;&nbsp;
				<% 
					if (loginUser.getType() == 1) {
				%>
				<a href="setType.jsp?id=<%=user.getId() %>">取消管理员</a>
				<%
					}
				%>
			<%
					//out.print("管理员");
				}
			%>
			</td>
			<td>
			<%
				if (user.getStatus() == 0) {
					//out.print("启用");
			%>
				启用&nbsp;&nbsp;
				<% 
					if (loginUser.getType() == 1) {
				%>
				<a href="setStatus.jsp?id=<%=user.getId() %>">停用</a>
				<%
					}
				%>
			<%
				}
				else {
			%>
				<span style='color:red'>停用</span>&nbsp;&nbsp;
				<% 
					if (loginUser.getType() == 1) {
				%>
				<a href="setStatus.jsp?id=<%=user.getId() %>">启用</a>
				<%
					}
				%>
			<%
					//out.print("<span style='color:red'>停用</span>");
				}
			%>
			</td>
			<td>
			<% 
				if (loginUser.getType() == 1) {
			%>
			<a href="delete.jsp?id=<%=user.getId() %>">删除</a><a href="update.jsp?id=<%=user.getId() %>">更新</a></td>
			<%
				}
			%>
		</tr>
	<%
		}
	%>
</table>
</body>
</html>