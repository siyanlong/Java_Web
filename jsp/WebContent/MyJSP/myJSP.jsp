<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>这个是我的第一个JSP程序</title>
</head>
<body>
<h1>Hello JSP</h1>
<%-- JSP有一些重要的内置对象，常用的有：
	out(PrintWriter),request(HttpServletRequest),
	response(HttpServletResponse),session(HttpSession),application(ServletContext)
--%>
<%
	String str = "Hello";
	out.println(str + "<br/>");
	String username = request.getParameter("username");
	out.println(username);
%>
<br/>
<%=username%>
</body>
</html>