package siyl.cit.shopping.dao;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import siyl.cit.shopping.model.Category;
import siyl.cit.shopping.model.Pager;
import siyl.cit.shopping.model.Product;
import siyl.cit.shopping.model.ShopDi;
import siyl.cit.shopping.model.ShopException;
import siyl.cit.shopping.model.SystemContext;

public class ProductDao extends BaseDao<Product> implements IProductDao {
	private ICategoryDao categoryDao;

	public ICategoryDao getCategoryDao() {
		return categoryDao;
	}

	@ShopDi
	public void setCategoryDao(ICategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	public void add(int categoryID, Product product) {
		Category category = categoryDao.load(categoryID);
		if (category == null) {
			throw new ShopException("要添加商品的类别不存在！");
		}
		product.setCategory(category);
		super.add(product);
	}

	@Override
	public void update(int categoryID, Product product) {
		Category category = categoryDao.load(categoryID);
		if (category == null) {
			throw new ShopException("要添加商品的类别不存在！");
		}
		product.setCategory(category);
		super.update(product);
	}

	@Override
	public void delete(int id) {
		// TODO 如果用户购买了该商品就不能删除，该商品存在订单也不能删除，
		// 如果可以删除商品的话需要删除商品的图片
		Product product = this.load(id);
		String img = product.getImg();
		super.delete(Product.class, id);
		// 删除图片
		String path = SystemContext.getRealpath() + "/img/";
		File f = new File(path + img);
		f.delete();
	}

	@Override
	public Product load(int id) {
		return super.load(Product.class, id);
	}

	@Override
	public Pager<Product> find(int categoryID, String name, int status) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (categoryID > 0) {
			params.put("cid", categoryID);
		}
		if (name != null && !"".equals(name.trim())) {
			params.put("name", "%" + name + "%");
		}
		if (status == 1 || status == -1) {
			params.put("status", status);
		}
		return super.find(Product.class, params);
	}

	@Override
	public void addStock(int id, int num) {
		Product product = this.load(id);
		product.setStock(product.getStock() + num);
		this.update(product);
	}

	@Override
	public void decreaseStock(int id, int num) {
		Product product = this.load(id);
		product.setStock(product.getStock() - num);
		this.update(product);
	}

	@Override
	public void changeStatus(int id) {
		Product product = this.load(id);
		if (product.getStatus() == -1) {
			product.setStatus(1);
		} else {
			product.setStatus(-1);
		}
		this.update(product);
	}
}
