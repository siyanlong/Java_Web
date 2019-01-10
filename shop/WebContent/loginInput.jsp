<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="siyl.cit.shop.dao.DAOFactory"%>
<%@ page import="siyl.cit.shop.dao.IUserDao"%>
<%@ page import="siyl.cit.shop.model.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<body>
<h2 align="center">用户登录</h2>
<hr/>
<form action="login.jsp" method="post">
<table align="center" width="500" border="1">
	<tr>
		<td>用户名称：</td>
		<td>
			<input type="text" name="username"/>
		</td>
	</tr>
	<tr>
		<td>用户密码：</td>
		<td>
			<input type="password" name="password"/>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="登录"/>
		</td>
	</tr>
</table>
</form>
</body>
</html>