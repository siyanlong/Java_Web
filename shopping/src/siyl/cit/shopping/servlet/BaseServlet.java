package siyl.cit.shopping.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import siyl.cit.shopping.util.DaoUtil;

/**
 * Servlet implementation class BaseServlet
 */
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, String> errors = new HashMap<String, String>();
	public static final String redirectPath = "redirect:";

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		errors.clear();
		request.setAttribute("errors", errors);
		DaoUtil.diDao(this);
		String method = request.getParameter("method");
		try {
			Method currMethod = this.getClass().getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
			String path = (String) currMethod.invoke(this, request, response);
			if (path.startsWith(redirectPath)) {
				response.sendRedirect(path.substring(redirectPath.length()));
			} else {
				// 使用Dispatcher跳转回自动增加上下文路径
				request.getRequestDispatcher("/WEB-INF" + path).forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
