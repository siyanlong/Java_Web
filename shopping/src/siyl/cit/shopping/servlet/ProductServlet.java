package siyl.cit.shopping.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import siyl.cit.shopping.dao.ICategoryDao;
import siyl.cit.shopping.dao.IProductDao;
import siyl.cit.shopping.model.Auth;
import siyl.cit.shopping.model.Category;
import siyl.cit.shopping.model.Product;
import siyl.cit.shopping.model.ShopDi;
import siyl.cit.shopping.util.RequestUtil;

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

	public String addInput(HttpServletRequest req, HttpServletResponse resp) {
		List<Category> category = categoryDao.list();
		req.setAttribute("cs", category);
		return "product/addInput.jsp";
	}

	public String add(HttpServletRequest req, HttpServletResponse resp) {
		Product product = (Product) RequestUtil.setParam(Product.class, req);
		if (!RequestUtil.validate(Product.class, req)) {
			addInput(req, resp);
			return "product/addInput.jsp";
		}
		int cid = 0;
		try {
			cid = Integer.parseInt(req.getParameter("cid"));
		} catch (NumberFormatException e) {
		}
		if (cid == 0) {
			this.getErrors().put("cid", "商品类别必须选择！");
			addInput(req, resp);
			return "product/addInput.jsp";
		}
		return redirPath("product.do?method=list");
	}
}
