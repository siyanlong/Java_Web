package siyl.cit.shopping.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import siyl.cit.shopping.dao.ICategoryDao;
import siyl.cit.shopping.dao.IProductDao;
import siyl.cit.shopping.model.Auth;
import siyl.cit.shopping.model.ShopDi;

/**
 * Servlet implementation class ProductServlet
 */
public class ProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private ICategoryDao categoryDao;
	private IProductDao productDao;

	public ICategoryDao getCategoryDao() {
		return categoryDao;
	}

	@ShopDi
	public void setCategoryDao(ICategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public IProductDao getProductDao() {
		return productDao;
	}

	@ShopDi
	public void setProductDao(IProductDao productDao) {
		this.productDao = productDao;
	}

	@Auth("any")
	public String list(HttpServletRequest req, HttpServletResponse resp) {
		req.setAttribute("products", productDao.find(0, null, 0));
		return "product/list.jsp";
	}
}
