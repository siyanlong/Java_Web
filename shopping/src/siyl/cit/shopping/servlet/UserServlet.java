package siyl.cit.shopping.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import siyl.cit.shopping.dao.IUserDao;
import siyl.cit.shopping.model.Pager;
import siyl.cit.shopping.model.ShopDi;
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

	@SuppressWarnings("unchecked")
	public String list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Pager<User> users = userDao.find("");
		request.setAttribute("users", users);

		return "/user/list.jsp";
	}

	public String addInput(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "/user/addInput.jsp";
	}

	public String register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "/user/register.jsp";
	}

	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isValidate = RequestUtil.validate(User.class, request);
		if (!isValidate) {
			return "/user/addInput.jsp";
		}
		User user = (User) RequestUtil.setParam(User.class, request);
		try {
			userDao.add(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return redirectPath + "user.do?method=list";
	}
}
