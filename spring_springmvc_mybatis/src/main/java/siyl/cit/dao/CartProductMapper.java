package siyl.cit.dao;

import siyl.cit.model.CartProduct;

public interface CartProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CartProduct record);

    int insertSelective(CartProduct record);

    CartProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CartProduct record);

    int updateByPrimaryKey(CartProduct record);
}