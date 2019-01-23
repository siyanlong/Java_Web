package siyl.cit.shopping.model;

import java.util.List;

/*
 * 购物车
 */
public class ShopCart {
	private List<Product> products;

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
