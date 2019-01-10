<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body >
<form action="user.do?method=add" method="post">
	<table width="500">
		<tr><td width="100">用户名：</td><td><input type="text" name="username" /></td></tr>
		<tr><td width="100">用户昵称：</td><td><input type="text" name="nickname" /><br/></td></tr>
		<tr><td width="100">用户年龄：</td><td><input type="text" name="age" /></td></tr>
		<tr><td colspan="2" align="center"><input type="submit" value="用户注册" /></td></tr>
	</table>
</form>
</body>
</html>