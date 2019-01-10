<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form>
		请输入数字：
		<input type="text" name="number"/>
		<input type="submit"/>
		<%!
			//<%!这样穿件的是全局变量，不使用<%!的都是定义到Service方法中的，可以去RealPath
			//的Tomcat目录下work目录去查看，定义方法也要使用带有"!"的方式定义
			int a = 10;
		%>
		<%=a %>
		<%=application.getRealPath("/") %>
		<%!
			public int sum(int a, int b) {
				return a + b;
			}
		%>
		<%=sum(12, 13) %>
	</form>
	<%
		try {
			int number = Integer.parseInt(request.getParameter("number"));
	%>
	<table border="1" width=2500px>
		<%
			for (int i = 1; i <= number; i++) {
		%>
		<tr>
			<%
				for (int j = 1; j <= number; j++) {
			%>
			<td><%=i%> * <%=j%> = <%=i*j%></td>
			<%
				}
			%>
		</tr>
		<%
			}
		%>
	</table>

	<%
		} catch (NumberFormatException ex) {
	%>
	<h1>请输入一个正确的格式</h1>
	<%
		}
	%>
</body>
</html>