package siyl.cit.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ScopeServlet
 */
public class ScopeServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {
		System.out.println("init servlet!");
	}
	
	/*
	 * 如果有两个init方法，系统会默认调用带有ServletConfig的方法
	 * */
	@Override
	public void init(ServletConfig config) throws ServletException {
		String userName = config.getInitParameter("username");
		System.out.println(userName);
	}

	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
		System.out.println("service is invoking!");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet!");
	}

	@Override
	public void destroy() {
		System.out.println("destroy!");
	}
}
