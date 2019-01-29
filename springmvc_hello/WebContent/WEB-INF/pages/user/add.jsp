<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 此时没有写action,直接提交会提交给/add -->
<sf:form method="post" modelAttribute="user" enctype="multipart/form-data">
	<table>
		<tr>
			<td>用户名：</td><td><sf:input path="username"/>&nbsp;<sf:errors path="username"/></td>
		</tr>
		<tr>
			<td>密码：</td><td><sf:input path="password"/>&nbsp;<sf:errors path="password"/></td>
		</tr>
		<tr>
			<td>用户昵称：</td><td><sf:input path="nickname"/></td>
		</tr>
		<tr>
			<td>邮箱：</td><td><sf:input path="email"/>&nbsp;<sf:errors path="email"/></td>
		</tr>
		<tr>
			<td>文件：</td>
			<td>
				<input type="file" name="attachs"/><br/><br/>
				<input type="file" name="attachs"/><br/><br/>
				<input type="file" name="attachs"/><br/><br/>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="添加用户"/>
			</td>
		</tr>
	</table>
</sf:form>
</body>
</html>