package siyl.cit.shopping.dao;

import java.util.List;

import siyl.cit.shopping.model.CartProduct;
import siyl.cit.shopping.model.Orders;
import siyl.cit.shopping.model.Pager;
import siyl.cit.shopping.model.Product;
import siyl.cit.shopping.model.User;

public interface IOrdersDao {
	public void add(Orders orders, User user, int aid, List<CartProduct> cps);

	public void delete(int id);

	public void update(Orders orders);

	public void updatePrice(int id, double price);

	public void updatePayStatus(int id);

	public void updateSendStatus(int id);

	public void updateConfirmStatus(int id);

	public Orders load(int id);

	public Pager<Orders> findByUser(int userId, int status);

	public Pager<Orders> findByStatus(int status);

	public void addCartProduct(CartProduct cp, Orders o, Product p);

	public void deleteCartProduct(int orderID);
}
