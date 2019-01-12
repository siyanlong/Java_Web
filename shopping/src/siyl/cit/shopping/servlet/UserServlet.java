package siyl.cit.shopping.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import siyl.cit.shopping.dao.IUserDao;
import siyl.cit.shopping.model.Auth;
import siyl.cit.shopping.model.Pager;
import siyl.cit.shopping.model.ShopDi;
import siyl.cit.shopping.model.ShopException;
import siyl.cit.shopping.model.User;
import siyl.cit.shopping.util.RequestUtil;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private IUserDao userDao;

	public IUserDao getUserDao() {
		return userDao;
	}

	@ShopDi
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public String list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Pager<User> users = userDao.find("");
		request.setAttribute("users", users);

		return "user/list.jsp";
	}

	@Auth("any")
	public String addInput(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "user/addInput.jsp";
	}

	@Auth("any")
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isValidate = RequestUtil.validate(User.class, request);
		if (!isValidate) {
			return "user/addInput.jsp";
		}
		User user = (User) RequestUtil.setParam(User.class, request);
		try {
			userDao.add(user);
		} catch (ShopException e) {
			request.setAttribute("errorMsg", e.getMessage());
			return "inc/error.jsp";
		}
		return redirPath("user.do?method=list");
	}

	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			userDao.delete(id);
		} catch (ShopException e) {
			request.setAttribute("errorMsg", e.getMessage());
			return "inc/error.jsp";
		}
		return redirPath("user.do?method=list");
	}

	public String updateInput(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			User user = userDao.load(id);
			request.setAttribute("user", user);
		} catch (ShopException e) {
			request.setAttribute("e", e);
			return "inc/error.jsp";
		}
		return "user/updateInput.jsp";
	}

	public String update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			User tu = (User) RequestUtil.setParam(User.class, request);
			User user = userDao.load(id);
			user.setNickname(tu.getNickname());
			boolean isValidate = RequestUtil.validate(User.class, request);
			if (!isValidate) {
				request.setAttribute("user", user);
				return "user/updateInput.jsp";
			}

			user.setPassword(tu.getPassword());
			user.setNickname(tu.getNickname());
			userDao.update(user);
		} catch (ShopException e) {
			request.setAttribute("errorMsg", e.getMessage());
			return "inc/error.jsp";
		}
		return redirPath("user.do?method=list");
	}

	public String changeType(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			User user = userDao.load(id);
			if (user.getType() == 0) {
				user.setType(1);
			} else {
				user.setType(0);
			}

			userDao.update(user);
		} catch (ShopException e) {
			request.setAttribute("errorMsg", e.getMessage());
			return "inc/error.jsp";
		}
		return redirPath("user.do?method=list");
	}

	@Auth("any")
	public String loginInput(HttpServletRequest req, HttpServletResponse resp) {
		return "user/loginInput.jsp";
	}

	@Auth("any")
	public String login(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			User u = userDao.login(username, password);
			req.getSession().setAttribute("loginUser", u);
		} catch (ShopException e) {
			req.setAttribute("errorMsg", e.getMessage());
			return "inc/error.jsp";
		}
		return redirPath("product.do?method=list");
	}

	@Auth("any")
	public String logout(HttpServletRequest req, HttpServletResponse resp) {
		req.getSession().invalidate();
		return redirPath("product.do?method=list");
	}

	@Auth
	public String updateSelfInput(HttpServletRequest req, HttpServletResponse resp) {
		req.setAttribute("user", (User) req.getSession().getAttribute("loginUser"));
		return "user/updateSelfInput.jsp";
	}

	@Auth
	public String updateSelf(HttpServletRequest req, HttpServletResponse resp) {
		User tu = (User) RequestUtil.setParam(User.class, req);
		boolean isValidate = RequestUtil.validate(User.class, req);
		User user = (User) req.getSession().getAttribute("loginUser");
		user.setPassword(tu.getPassword());
		user.setNickname(tu.getNickname());
		if (!isValidate) {
			req.setAttribute("user", user);
			return "user/updateSelfInput.jsp";
		}
		userDao.update(user);
		return redirPath("goods.do?method=list");
	}

	@Auth
	public String show(HttpServletRequest req, HttpServletResponse resp) {
		User user = userDao.load(Integer.parseInt(req.getParameter("id")));
		req.setAttribute("user", user);
		return "user/show.jsp";
	}
}
