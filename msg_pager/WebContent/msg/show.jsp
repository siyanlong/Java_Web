<%@page import="siyl.cit.msg.model.User"%>
<%@page import="siyl.cit.msg.model.Comment"%>
<%@page import="siyl.cit.msg.model.Pager"%>
<%@page import="siyl.cit.msg.dao.ICommentDao"%>
<%@page import="siyl.cit.msg.util.ValidateUtil"%>
<%@page import="siyl.cit.msg.dao.IUserDao"%>
<%@page import="siyl.cit.msg.util.MsgUtil"%>
<%@page import="siyl.cit.msg.model.Message"%>
<%@page import="siyl.cit.msg.dao.DAOFactory"%>
<%@page import="siyl.cit.msg.dao.IMessageDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>留言显示</title>
<%
	IMessageDao msgDao = DAOFactory.getMessageDao();
	ICommentDao commentDao = DAOFactory.getCommentDao();
	IUserDao userDao = DAOFactory.getUserDao();
	int id = Integer.parseInt(request.getParameter("id"));
	Pager<Comment> comments = commentDao.list(id);
	Message msg = msgDao.load(id);
	User lu = (User)session.getAttribute("loginUser");
%>
<script type="text/javascript" src="<%=request.getContextPath() %>/xhEditor/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/xhEditor/xheditor-1.1.14-zh-cn.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#content").xheditor({
			tools:'simple'
		});
	});
</script>
</head>
<body>
<jsp:include page="/msg/inc.jsp">
	<jsp:param value="显示" name="op"/>
</jsp:include>
<table width="900" align="center" border="1">
	<tr>
	<td><h3><%=msg.getTitle() %></h3></td>
	</tr>
	<tr>
	<td>
		发表日期:<%=MsgUtil.formatDate(msg.getPostDate()) %>
		&nbsp;&nbsp;&nbsp;&nbsp;
		发布人员:<%=(userDao.load(msg.getUserId())).getNickname() %>
		<%
		if(ValidateUtil.checkAdminOrSelf(session, msg.getUserId())) {
		%>
		<a href="<%=request.getContextPath()%>/admin/msg/updateInput.jsp?id=<%=msg.getId()%>">更新</a>
		&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/admin/msg/delete.jsp?id=<%=msg.getId()%>">删除</a>
		<%	
		}
		%>
	</td>
	</tr>
	<tr>
	<td>
		<%=msg.getContent() %>
	</td>
	</tr>
</table>
<table width="900" align="center" border="1">
<%
for (Comment com : comments.getDatas()) {
%>
<tr>
<td width="600px"><%=com.getContent() %></td>
<td>
	<%=MsgUtil.formatDate(com.getPostDate()) %>&nbsp;
	<%=userDao.load(com.getUserId()).getNickname() %>&nbsp;
	<%
	if(ValidateUtil.checkAdminOrSelf(session, com.getUserId())) {
	%>
	<a href="<%=request.getContextPath()%>/admin/comment/delete.jsp?id=<%=com.getId()%>&msgId=<%=msg.getId()%>">删除</a>
	<%	
	}
	%>
</td>
</tr>
<%	
}
%>
<tr>
<td colspan="2">
<jsp:include page="/inc/pager.jsp">
	<jsp:param value="<%=comments.getTotalRecord() %>" name="items"/>
	<jsp:param value="id" name="params"/>
</jsp:include>
</td>
</tr>
</table>
<%
if(lu!=null) {
%>
<form action="<%=request.getContextPath()%>/admin/comment/add.jsp" method="post">
<input type="hidden" name="msgId" value="<%=msg.getId()%>">
<table width="900" align="center" border="1">
<tr><td>添加回复</td></tr>
<tr>
	<td><textarea id="content" cols="100" rows="10" name="content"></textarea></td>
</tr>
<tr><td><input type="submit" value="添加回复"/></td></tr>
</table>
</form>
<%	
}
%>

</body>
</html>