package siyl.cit.shopping.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import siyl.cit.shopping.dao.IUserDao;
import siyl.cit.shopping.model.Pager;
import siyl.cit.shopping.model.ShopDi;
import siyl.cit.shopping.model.User;

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
		try {
			Map<String, String[]> params = request.getParameterMap();
			Set<String> keys = params.keySet();
			User currUser = new User();
			for (String currKey : keys) {
				String methodName = "set" + currKey.substring(0, 1).toUpperCase() + currKey.substring(1);
				Method method = currUser.getClass().getMethod(methodName, String.class);
				method.invoke(currUser, params.get(currKey)[0]);
			}
			String username = request.getParameter("username");
			String nickname = request.getParameter("nickname");
			String password = request.getParameter("password");
			User user = new User();
			user.setUsername(username);
			user.setNickname(nickname);
			user.setPassword(password);
			userDao.add(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return redirectPath + "user.do?method=list";
	}
}
