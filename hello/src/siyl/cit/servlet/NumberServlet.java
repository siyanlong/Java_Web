package siyl.cit.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NumberServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取次数
		int times = Integer.parseInt(req.getParameter("times"));
		
		//设置文档类型及编码
		resp.setContentType("text/html;charset=UTF-8");
		
		//获取输出对象并输出内容
		PrintWriter out = resp.getWriter();
		out.println("<html><body>");
		
		//输出指定次数个Hello
		for (int i = 0; i < times; i++) {
			out.println("<h1>" + "Hello" + "</h1>");
		}
		
		out.println("</html></body>");
	}
}
