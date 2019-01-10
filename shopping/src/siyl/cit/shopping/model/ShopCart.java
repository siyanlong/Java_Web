package siyl.cit.shopping.model;

import java.util.List;

/*
 * 购物车
 */
public class ShopCart {
	private List<Goods> goods;

	public List<Goods> getGoods() {
		return goods;
	}

	public void setGoods(List<Goods> goods) {
		this.goods = goods;
	}
}
