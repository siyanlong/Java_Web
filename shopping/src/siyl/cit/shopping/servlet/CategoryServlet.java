package siyl.cit.shopping.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import siyl.cit.shopping.dao.ICategoryDao;
import siyl.cit.shopping.model.Category;
import siyl.cit.shopping.model.ShopDi;
import siyl.cit.shopping.model.ShopException;
import siyl.cit.shopping.util.RequestUtil;

public class CategoryServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	private ICategoryDao categoryDao;

	public ICategoryDao getCategoryDao() {
		return categoryDao;
	}

	@ShopDi
	public void setCategoryDao(ICategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public String list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		List<Category> category = categoryDao.list(name);
		request.setAttribute("cs", category);

		return "category/list.jsp";
	}

	public String addInput(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "category/addInput.jsp";
	}

	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isValidate = RequestUtil.validate(Category.class, request);
		if (!isValidate) {
			return "category/addInput.jsp";
		}
		Category category = (Category) RequestUtil.setParam(Category.class, request);
		try {
			categoryDao.add(category);
		} catch (ShopException e) {
			request.setAttribute("errorMsg", e.getMessage());
			return "inc/error.jsp";
		}
		return redirPath("category.do?method=list");
	}

	public String updateInput(HttpServletRequest req, HttpServletResponse resp) {
		req.setAttribute("category", categoryDao.load(Integer.parseInt(req.getParameter("id"))));
		return "category/updateInput.jsp";
	}

	public String update(HttpServletRequest req, HttpServletResponse resp) {
		Category category = categoryDao.load(Integer.parseInt(req.getParameter("id")));
		Category tc = (Category) RequestUtil.setParam(Category.class, req);
		category.setName(tc.getName());
		if (!RequestUtil.validate(Category.class, req)) {
			return "category/updateInput.jsp";
		}
		categoryDao.update(category);
		return redirPath("category.do?method=show&id=" + category.getId());
	}

	public String show(HttpServletRequest req, HttpServletResponse resp) {
		Category category = categoryDao.load(Integer.parseInt(req.getParameter("id")));
		req.setAttribute("category", category);
		return "category/show.jsp";
	}

	public String delete(HttpServletRequest req, HttpServletResponse resp) {
		try {
			categoryDao.delete(Integer.parseInt(req.getParameter("id")));
		} catch (ShopException e) {
			req.setAttribute("errorMsg", e.getMessage());
			return "inc/error.jsp";
		}
		return redirPath("category.do?method=list");
	}
}
