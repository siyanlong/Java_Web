package siyl.cit.shopping.dao;

import siyl.cit.shopping.model.Pager;
import siyl.cit.shopping.model.Product;

public interface IProductDao {
	public void add(int categoryID, Product product);

	public void update(int categoryID, Product product);

	public void delete(int id);

	public Product load(int id);

	/**
	 * 可以通过商品类别和名称进行搜索 此时可以进行灵活的排序
	 */
	public Pager<Product> find(int categoryID, String name, int status);

	/**
	 * 增加库存
	 */
	public void addStock(int id, int num);

	/**
	 * 减少库存
	 */
	public void decreaseStock(int id, int num);

	/**
	 * 变更状态
	 */
	public void changeStatus(int id);
}
