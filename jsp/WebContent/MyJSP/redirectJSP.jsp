<%@page import="siyl.cit.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- 对于response.sendRedirect的跳转，跳转之后依然会执行完剩下的代码才进行跳转 --%>
<%
	User user = null;
	int a = 10;
	if (a == 10) {
		//浏览器的地址变了就是客户端跳转，浏览器地址不变就是服务器端跳转
		//所以一定注意，在response.sendRedirect之后增加return
		//response.sendRedirect("userJSP.jsp");
		//response.sendRedirect("userJSP.jsp?userName=aaa");
		response.sendRedirect("userJSP.jsp?userName=张三");
		return;
	}
	user.setId(1);
%>
</body>
</html>