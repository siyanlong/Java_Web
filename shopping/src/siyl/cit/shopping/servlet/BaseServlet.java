package siyl.cit.shopping.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import siyl.cit.shopping.model.Auth;
import siyl.cit.shopping.model.User;
import siyl.cit.shopping.util.DaoUtil;

/**
 * Servlet implementation class BaseServlet
 */
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, String> errors = new HashMap<String, String>();
	public static final String redirectPath = "redirect:";

	protected String redirPath(String path) {
		return redirectPath + path;
	}

	protected Map<String, String> getErrors() {
		return errors;
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		errors.clear();
		request.setAttribute("errors", errors);
		DaoUtil.diDao(this);
		String method = request.getParameter("method");
		try {
			Method currMethod = this.getClass().getMethod(method, HttpServletRequest.class, HttpServletResponse.class);

			// 在这里进行权限控制
			int flag = checkAuth(request, currMethod, response);
			if (flag == 1) {
				response.sendRedirect("user.do?method=loginInput");
				return;
			} else if (flag == 2) {
				request.setAttribute("errorMsg", "你没有权限访问该功能");
				request.getRequestDispatcher("/WEB-INF/inc/error.jsp").forward(request, response);
				return;
			}

			String path = (String) currMethod.invoke(this, request, response);
			if (path.startsWith(redirectPath)) {
				response.sendRedirect(path.substring(redirectPath.length()));
			} else {
				// 使用Dispatcher跳转回自动增加上下文路径
				request.getRequestDispatcher("/WEB-INF/" + path).forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 返回一个int类型的值，如果是0表示可以成功访问，如果是1表示到登录页面，如果是2表示显示没有权限
	 */
	private int checkAuth(HttpServletRequest req, Method m, HttpServletResponse resp) {
		User lu = (User) req.getSession().getAttribute("loginUser");

		if (lu != null && lu.getType() == 1) {
			// 如果是管理员说明所有的功能都可以访问
			return 0;
		}

		// 没有Auth的Annotation说明该方法必须由超级管理员访问
		if (!m.isAnnotationPresent(Auth.class)) {
			if (lu == null) {
				return 1;
			}
			if (lu.getType() != 1) {
				return 2;
			}
		} else {
			Auth au = m.getAnnotation(Auth.class);
			String v = au.value();
			if (v.equals("any")) {
				return 0;
			} else if (v.equals("user")) {
				if (lu == null) {
					return 1;
				} else {
					return 0;
				}
			}
		}

		return 0;
	}
}
