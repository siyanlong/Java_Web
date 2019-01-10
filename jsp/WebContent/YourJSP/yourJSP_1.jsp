<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="../MyJSP/userJSP.jsp">访问MyJSP页面（相对路径）</a>
<img alt="图片获取失败！" src="../image/Snap1.jpg">
<a href="<%=request.getContextPath() %> %>../MyJSP/userJSP.jsp">访问MyJSP页面（绝对路径）</a>
</body>
</html>