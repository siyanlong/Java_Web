package siyl.cit.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import siyl.cit.shop.model.ShopException;
import siyl.cit.shop.model.User;
import siyl.cit.shop.util.DBUtil;

public class UserDao implements IUserDao {

	@Override
	public void add(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select count(*) from t_user where username=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getInt(1) > 0) {
					throw new ShopException("添加的用户已经存在，不能继续添加！");
				}
			}
			sql = "insert into t_user (username,password,nickname,status,type) values (?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getNicknanme());
			ps.setInt(4, user.getStatus());
			ps.setInt(5, user.getType());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(conn);
		}
	}

	@Override
	public void delete(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			User user = load(id);
			if (user.getUsername().equals("admin")) {
				throw new ShopException("超级管理员admin不能被删除！");
			}
			String sql = "delete from t_user where id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(ps);
			DBUtil.close(conn);
		}
	}

	@Override
	public void update(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "update t_user set password=?,nickname=?,status=?,type=? where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getPassword());
			ps.setString(2, user.getNicknanme());
			ps.setInt(3, user.getStatus());
			ps.setInt(4, user.getType());
			ps.setInt(5, user.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(ps);
			DBUtil.close(conn);
		}
	}

	@Override
	public User load(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from t_user where id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId(id);
				user.setNicknanme(rs.getString("nickname"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setStatus(rs.getInt("status"));
				user.setType(rs.getInt("type"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(conn);
		}
		return user;
	}

	@Override
	public List<User> list() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> users = new ArrayList<User>();
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from t_user";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setNicknanme(rs.getString("nickname"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setStatus(rs.getInt("status"));
				user.setType(rs.getInt("type"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(conn);
		}
		return users;
	}

	@Override
	public User login(String username, String password) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from t_user where username=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setNicknanme(rs.getString("nickname"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setStatus(rs.getInt("status"));
				user.setType(rs.getInt("type"));
			}
			if (user == null) {
				throw new ShopException("用户名不存在！");
			}
			if (!user.getPassword().equals(password)) {
				throw new ShopException("用户密码不存在！");
			}
			if (user.getStatus() == 0) {
				throw new ShopException("该用户处于停用状态，不能登录！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(conn);
		}
		
		return user;
	}

}
