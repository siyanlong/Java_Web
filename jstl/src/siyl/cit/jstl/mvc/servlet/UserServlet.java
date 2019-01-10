package siyl.cit.jstl.mvc.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import siyl.cit.jstl.mvc.model.User;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public String list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> users = (List<User>) request.getSession().getServletContext().getAttribute("storeUsers");
		request.setAttribute("users", users);

		return "/user/list.jsp";
	}

	public String register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "/user/register.jsp";
	}

	@SuppressWarnings("unchecked")
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String nickname = request.getParameter("nickname");
		int age = Integer.parseInt(request.getParameter("age"));

		/**
		 * 此处将用户存储到application中
		 */
		List<User> users = (List<User>) request.getSession().getServletContext().getAttribute("storeUsers");
		if (users == null) {
			users = new ArrayList<User>();
		}
		User user = new User(username, nickname, age);
		users.add(user);
		request.getSession().getServletContext().setAttribute("storeUsers", users);
		return "redirect:user.do?method=list";
	}
}
