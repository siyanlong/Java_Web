package siyl.cit.jstl.mvc.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BaseServlet
 */
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String method = request.getParameter("method");
		try {
			Method currMethod = this.getClass().getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
			String rel = (String) currMethod.invoke(this, request, response);
			String redirect = "redirect:";
			if (rel.startsWith(redirect)) {
				response.sendRedirect(rel.substring(redirect.length()));
			} else {
				// 使用Dispatcher跳转回自动增加上下文路径
				request.getRequestDispatcher("/WEB-INF" + rel).forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
