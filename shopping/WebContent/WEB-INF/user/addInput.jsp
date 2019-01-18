<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/main.css">
</head>
<body>
<jsp:include page="inc.jsp"/>
<form action="user.do?method=add" method="post">
<table width="600" class="thin-border" align="center">
	<tr>
		<td>用户名：</td><td><input type="text" name="username"/><span class="errorContainer">${errors.username}</span></td>
	</tr>
	<tr>
		<td>用户密码：</td><td><input type="password" name="password"/><span class="errorContainer">${errors.password}</span></td>
	</tr>
	<tr>
		<td>用户昵称：</td><td><input type="text" name="nickname"/></td>
	</tr>
	<!-- <tr> -->
	<!-- <td>用户兴趣：</td><td><input type="checkbox" name="interes" value="足球"/>足球<input type="checkbox" value="篮球" name="interes"/></td> -->
	<!-- </tr> -->
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="用户注册"/>&nbsp;<input type="reset"/>
		</td>
	</tr>
</table>
</form>
</body>
</html>