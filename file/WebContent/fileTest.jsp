<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="file.do" enctype="multipart/form-data">
<!-- <form method="post" action="file.do"> -->
	Username：<input type="text" name="username"/><br/>
	Photo：<input type="file" name="photo"/><br/>
	Interests:<input type="checkbox" name="interest" value="足球"/>足球
	<input type="checkbox" name="interest" value="蓝球"/>蓝球
	<input type="checkbox" name="interest" value="羽毛球"/>羽毛球<br/>
	<input type="submit">
</form>
</body>
</html>