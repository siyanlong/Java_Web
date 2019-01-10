<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><decorator:title default="欢迎使用网上商城"/></title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/main.css">
<decorator:head/>
</head>
<body>
<a href="">用户管理</a>
<h3 align="center"><decorator:title default="商城管理"/></h3>
<decorator:body/>
<hr/>
<div align="center">
	CopyRight@2012-2019<br/>
	开发培训教学项目
</div>
</body>
</html>