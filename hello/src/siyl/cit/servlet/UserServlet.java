package siyl.cit.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println("<html><body>");
		
		//获取上下文路径
		out.println("<h1>" + req.getContextPath() + "</h1>");
		
		//获取绝对路径
		out.println("<h5>" + req.getSession().getServletContext().getRealPath("/") + "</h5>");
		
		//获取请求参数：方式一
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		out.println("<h1>" + "Hello：" + userName + "    Password：" + password + "</h1>");
		
		//获取请求参数：方式二
		Enumeration<String> enumeration = req.getParameterNames();
		while (enumeration.hasMoreElements()) {
			out.println(enumeration.nextElement() + "<br/>");
		}
		
		out.println("</html></body>");
	}
}
