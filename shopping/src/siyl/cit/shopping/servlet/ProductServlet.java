package siyl.cit.shopping.servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;

import siyl.cit.shopping.dao.ICategoryDao;
import siyl.cit.shopping.dao.IProductDao;
import siyl.cit.shopping.model.Auth;
import siyl.cit.shopping.model.Category;
import siyl.cit.shopping.model.Pager;
import siyl.cit.shopping.model.Product;
import siyl.cit.shopping.model.ShopDi;
import siyl.cit.shopping.model.ShopException;
import siyl.cit.shopping.model.SystemContext;
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
		Pager<Product> products = productDao.find(0, null, 0);
		req.setAttribute("products", products);
		return "product/list.jsp";
	}

	@Auth("any")
	public String show(HttpServletRequest req, HttpServletResponse resp) {
		req.setAttribute("product", productDao.load(Integer.parseInt(req.getParameter("id"))));
		return "product/show.jsp";
	}

	public String addInput(HttpServletRequest req, HttpServletResponse resp) {
		List<Category> category = categoryDao.list();
		req.setAttribute("cs", category);
		return "product/addInput.jsp";
	}

	public String addStockInput(HttpServletRequest req, HttpServletResponse resp) {
		req.setAttribute("c", productDao.load(Integer.parseInt(req.getParameter("id"))));
		return "product/addStockInput.jsp";
	}

	public String addStock(HttpServletRequest req, HttpServletResponse resp) {
		try {
			int number = Integer.parseInt(req.getParameter("number"));
			int id = Integer.parseInt(req.getParameter("id"));
			productDao.addStock(id, number);
		} catch (NumberFormatException e) {
			this.getErrors().put("number", "库存的类型必须为整数！");
			req.setAttribute("c", productDao.load(Integer.parseInt(req.getParameter("id"))));
			return "product/addStockInput.jsp";
		}
		return redirPath("product.do?method=list");
	}

	public String delete(HttpServletRequest req, HttpServletResponse resp) {
		try {
			// TODO 如果用户购买了该商品就不能删除，该商品存在订单也不能删除
			// 如果可以删除商品的话需要删除商品的图片
			int id = Integer.parseInt(req.getParameter("id"));
			Product p = productDao.load(id);
			String img = p.getImg();
			productDao.delete(id);

			// 删除图片
			String path = SystemContext.getRealpath() + "/img/";
			File f = new File(path + img);
			f.delete();
		} catch (ShopException e) {
			return this.handleException(e, req);
		}
		return redirPath("product.do?method=list");
	}

	public String changeStatus(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		productDao.changeStatus(id);
		return redirPath("product.do?method=list");
	}

	public String add(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Product product = (Product) RequestUtil.setParam(Product.class, req);
		product.setStatus(1);
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
		}
		if (!this.hasErrors()) {
			// 文件上传
			byte[] fs = (byte[]) req.getAttribute("fs");
			String fileName = req.getParameter("img");
			fileName = FilenameUtils.getName(fileName);
			RequestUtil.uploadFile(fileName, "img", fs, req);
		}
		if (this.hasErrors()) {
			addInput(req, resp);
			return "product/addInput.jsp";
		}
		productDao.add(cid, product);
		return redirPath("product.do?method=list");
	}

	public String updateInput(HttpServletRequest req, HttpServletResponse resp) {
		req.setAttribute("cs", categoryDao.list());
		req.setAttribute("p", productDao.load(Integer.parseInt(req.getParameter("id"))));
		return "product/updateInput.jsp";
	}

	public String update(HttpServletRequest req, HttpServletResponse resp) throws FileNotFoundException, IOException {
		Product p = (Product) RequestUtil.setParam(Product.class, req);
		RequestUtil.validate(Product.class, req);
		String img = req.getParameter("img");
		int cid = 0;
		try {
			cid = Integer.parseInt(req.getParameter("cid"));
		} catch (NumberFormatException e) {
		}
		if (cid == 0) {
			this.getErrors().put("cid", "商品类别必须选择");
		}
		Product tp = productDao.load(Integer.parseInt(req.getParameter("id")));
		tp.setIntro(p.getIntro());
		tp.setName(p.getName());
		tp.setPrice(p.getPrice());
		tp.setStock(p.getStock());
		boolean updateImg = false;
		if (img == null || img.trim().equals("")) {
			// 此时说明不修改图片
		} else {
			// 此时说明需要修改图片
			if (!this.hasErrors()) {
				// 是否要修改文件
				byte[] fs = (byte[]) req.getAttribute("fs");
				String fname = req.getParameter("img");
				fname = FilenameUtils.getName(fname);
				try {
					RequestUtil.uploadFile(fname, "img", fs, req);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			updateImg = true;
		}
		if (this.hasErrors()) {
			req.setAttribute("p", tp);
			req.setAttribute("cs", categoryDao.list());
			return "product/updateInput.jsp";
		}
		if (updateImg) {
			// 先删除原有的图片
			String oimg = tp.getImg();
			File f = new File(SystemContext.getRealpath() + "/img/" + oimg);
			f.delete();
			tp.setImg(p.getImg());
		}
		productDao.update(cid, tp);
		return redirPath("product.do?method=list");
	}
}
