package siyl.cit.shopping.servlet;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import siyl.cit.shopping.dao.IOrdersDao;
import siyl.cit.shopping.dao.IProductDao;
import siyl.cit.shopping.dao.IUserDao;
import siyl.cit.shopping.model.Auth;
import siyl.cit.shopping.model.Orders;
import siyl.cit.shopping.model.Product;
import siyl.cit.shopping.model.ShopCart;
import siyl.cit.shopping.model.ShopDi;
import siyl.cit.shopping.model.ShopException;
import siyl.cit.shopping.model.SystemContext;
import siyl.cit.shopping.model.User;

/**
 * Servlet implementation class UserServlet
 */
public class OrdersServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private IProductDao productDao;
	private IUserDao userDao;
	private IOrdersDao ordersDao;

	public IOrdersDao getOrdersDao() {
		return ordersDao;
	}

	@ShopDi
	public void setOrdersDao(IOrdersDao ordersDao) {
		this.ordersDao = ordersDao;
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	@ShopDi
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public IProductDao getProductDao() {
		return productDao;
	}

	@ShopDi
	public void setProductDao(IProductDao productDao) {
		this.productDao = productDao;
	}

	@Auth
	public String addToCart(HttpServletRequest req, HttpServletResponse resp) {
		try {
			ShopCart shopCart = (ShopCart) req.getSession().getAttribute("shopCart");
			if (shopCart == null) {
				shopCart = new ShopCart();
				req.getSession().setAttribute("shopCart", shopCart);
			}
			Product p = productDao.load(Integer.parseInt(req.getParameter("id")));
			shopCart.add(p);
		} catch (ShopException e) {
			return this.handleException(e, req);
		}
		return redirPath("product.do?method=list");
	}

	@Auth
	public String showCart(HttpServletRequest req, HttpServletResponse resp) {
		User u = (User) req.getSession().getAttribute("loginUser");
		req.setAttribute("addresses", userDao.load(u.getId()).getAddresses());
		return "orders/showCart.jsp";
	}

	@Auth
	public String clearProduct(HttpServletRequest req, HttpServletResponse resp) {
		int pid = Integer.parseInt(req.getParameter("pid"));
		ShopCart shopCart = (ShopCart) req.getSession().getAttribute("shopCart");
		if (shopCart != null) {
			shopCart.clearProduct(pid);
		}
		return redirPath("orders.do?method=showCart");
	}

	@Auth
	public String clearShopCart(HttpServletRequest req, HttpServletResponse resp) {
		ShopCart shopCart = (ShopCart) req.getSession().getAttribute("shopCart");
		if (shopCart != null) {
			shopCart.clearShopCart();
		}
		return redirPath("orders.do?method=showCart");
	}

	@Auth
	public String productAddNumberInput(HttpServletRequest req, HttpServletResponse resp) {
		req.setAttribute("pid", Integer.parseInt(req.getParameter("pid")));
		return "orders/productAddNumberInput.jsp";
	}

	@Auth
	public String productAddNumber(HttpServletRequest req, HttpServletResponse resp) {
		int pid = Integer.parseInt(req.getParameter("pid"));
		try {
			int number = Integer.parseInt(req.getParameter("number"));
			ShopCart shopCart = (ShopCart) req.getSession().getAttribute("shopCart");
			if (shopCart != null) {
				shopCart.addProductNumber(pid, number);
			}
		} catch (NumberFormatException e) {
			this.getErrors().put("number", "数量必须为整数！");
			req.setAttribute("pid", pid);
			return "orders/productAddNumberInput.jsp";
		} catch (ShopException e) {
			return this.handleException(e, req);
		}
		return redirPath("orders.do?method=showCart");
	}

	@Auth
	public String addOrders(HttpServletRequest req, HttpServletResponse resp) {
		int aid = Integer.parseInt(req.getParameter("address"));
		double price = Double.parseDouble(req.getParameter("price"));
		Orders order = new Orders();
		order.setBuyDate(new Date());
		order.setStatus(1);
		order.setPrice(price);
		User u = (User) req.getSession().getAttribute("loginUser");
		ordersDao.add(order, u, aid, ((ShopCart) req.getSession().getAttribute("shopCart")).getProducts());
		return redirPath("user.do?method=show&id=" + u.getId());
	}

	public String list(HttpServletRequest req, HttpServletResponse resp) {
		SystemContext.setOrder("desc");
		SystemContext.setSort("buy_date");
		int status = 0;
		try {
			status = Integer.parseInt(req.getParameter("status"));
		} catch (NumberFormatException e) {
		}
		req.setAttribute("orders", ordersDao.findByStatus(status));
		return "orders/list.jsp";
	}

	@Auth
	public String userList(HttpServletRequest req, HttpServletResponse resp) {
		SystemContext.setOrder("desc");
		SystemContext.setSort("buy_date");
		int status = 0;
		int userId = 0;
		try {
			userId = Integer.parseInt(req.getParameter("id"));
			status = Integer.parseInt(req.getParameter("status"));
		} catch (NumberFormatException e) {
		}
		req.setAttribute("orders", ordersDao.findByUser(userId, status));
		return "orders/userList.jsp";
	}

	@Auth
	public String delete(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		ordersDao.delete(id);
		return redirPath("product.do?method=list");
	}

	@Auth
	public String show(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		req.setAttribute("o", ordersDao.load(id));
		return "orders/show.jsp";
	}

	@Auth
	public String pay(HttpServletRequest req, HttpServletResponse resp) {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			ordersDao.updatePayStatus(id);
		} catch (ShopException e) {
			return handleException(e, req);
		}
		return redirPath("orders.do?method=userList&id=" + ((User) req.getSession().getAttribute("loginUser")).getId());
	}

	public String sendProduct(HttpServletRequest req, HttpServletResponse resp) {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			ordersDao.updateSendStatus(id);
		} catch (ShopException e) {
			return handleException(e, req);
		}
		return redirPath("orders.do?method=list");
	}

	@Auth
	public String confirmProduct(HttpServletRequest req, HttpServletResponse resp) {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			ordersDao.updateConfirmStatus(id);
		} catch (ShopException e) {
			return handleException(e, req);
		}
		return redirPath("orders.do?method=userList&id=" + ((User) req.getSession().getAttribute("loginUser")).getId());
	}
}
