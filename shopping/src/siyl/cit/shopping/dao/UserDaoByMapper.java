package siyl.cit.shopping.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import siyl.cit.shopping.mapper.UserMapper;
import siyl.cit.shopping.model.Pager;
import siyl.cit.shopping.model.ShopException;
import siyl.cit.shopping.model.SystemContext;
import siyl.cit.shopping.model.User;
import siyl.cit.shopping.util.MyBatisUtil;

public class UserDaoByMapper extends BaseDao<User> implements IUserDao {

	@Override
	public void add(User user) {
		User currUser = this.loadByUsername(user.getUsername());
		if (currUser != null) {
			throw new ShopException("要添加的用户已经存在！");
		}
		super.add(user);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public User loadByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User load(int id) {
		SqlSession session = null;
		User user = null;
		try {
			session = MyBatisUtil.createSession();
			user = session.getMapper(UserMapper.class).load(id);
		} finally {
			MyBatisUtil.closeSession(session);
		}
		return user;
	}

	@Override
	public Pager<User> find(String name) {
		int pageOffset = SystemContext.getPageOffset();
		int pageSize = SystemContext.getPageSize();
		String sort = SystemContext.getSort();
		String order = SystemContext.getOrder();
		Pager<User> pages = new Pager<User>();
		SqlSession session = null;
		try {
			session = MyBatisUtil.createSession();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("name", "%" + name + "%");
			params.put("pageSize", pageSize);
			params.put("pageOffset", pageOffset);
			params.put("order", order);
			params.put("sort", sort);
			List<User> users = session.getMapper(UserMapper.class).find(params);
			pages.setDatas(users);
			pages.setPageOffset(pageOffset);
			pages.setPageSize(pageSize);
			int totalRecord = session.getMapper(UserMapper.class).find_count(params);
			pages.setTotalRecord(totalRecord);
		} finally {
			MyBatisUtil.closeSession(session);
		}
		return pages;
	}

	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
