<%@ page import="siyl.cit.msg.model.Message"%>
<%@ page import="siyl.cit.msg.dao.DAOFactory"%>
<%@ page import="siyl.cit.msg.util.ValidateUtil"%>
<%@ page import="siyl.cit.msg.dao.IMessageDao"%>
<%@ page import="siyl.cit.msg.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>留言更新</title>
<%
int id = Integer.parseInt(request.getParameter("id"));
IMessageDao msgDao = DAOFactory.getMessageDao();
Message msg = msgDao.load(id);
boolean flag = ValidateUtil.checkAdminOrSelf(session, msg.getUserId());
if(!flag) {
	response.sendRedirect(request.getContextPath()+"/msg/list.jsp");
	return;
}
String title = request.getParameter("title");
String content = request.getParameter("content");
msg.setContent(content);
msg.setTitle(title);
msgDao.update(msg);
response.sendRedirect(request.getContextPath()+"/msg/show.jsp?id="+msg.getId());
%>
</head>
</html>