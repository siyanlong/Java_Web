<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
</head>
<body>
<hr/>
<jsp:include page="inc.jsp"></jsp:include>
<table align="center" width="1000" cellpadding="0" cellspacing="0" class="thin-border">
	<tr>
		<td>用户标识</td><td>用户名称</td><td>用户密码</td><td>用户昵称</td><td>用户类型</td><td>操作</td>
	</tr>
	<c:forEach var="user" items="${users.datas }" varStatus="vs">
		<tr <c:if test="${vs.index % 2 == 0 }">style="background:#ff0;color:#45e"</c:if>>
			<td>${user.id }</td><td>${user.nickname }</td><td>${user.password }</td><td>${user.nickname }</td>
			<td>
				<c:if test="${user.type eq 0 }">普通用户</c:if>
				<c:if test="${user.type eq 1 }">管理员</c:if>
			</td>
			<td>
				<a href="">修改</a><a href="">删除</a>
			</td>
		</tr>
	</c:forEach>
	<tr>
		<td colspan="6">
			<jsp:include page="/inc/pager.jsp">
				<jsp:param value="user.do" name="url"/>
				<jsp:param value="${users.totalRecord }" name="items"/>
				<jsp:param value="method" name="params"/>
			</jsp:include>
		</td>
	</tr>
</table>
</body>
</html>