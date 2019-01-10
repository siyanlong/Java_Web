package siyl.cit.msg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import siyl.cit.msg.model.MessageException;
import siyl.cit.msg.model.Pager;
import siyl.cit.msg.model.SystemContext;
import siyl.cit.msg.model.User;
import siyl.cit.msg.util.DBUtil;

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
					throw new MessageException("添加的用户已经存在，不能继续添加！");
				}
			}
			sql = "insert into t_user (username,password,nickname,status,type) values (?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getNickname());
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
				throw new MessageException("超级管理员admin不能被删除！");
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
			ps.setString(2, user.getNickname());
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
	public Pager<User> list(String condition) {
		int pageOffset = SystemContext.getPageOffset();
		int pageSize = SystemContext.getPageSize();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> users = new ArrayList<User>();
		Pager<User> pages = new Pager<User>();
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from t_user";
			String sqlCount = "select count(*) from t_user";
			if (condition != null && !"".equals(condition)) {
				sql += " where username like " + "'%" + condition + "%'" + "or nickname like " + "'%" + condition
						+ "%'";
				sqlCount += " where username like " + "'%" + condition + "%'" + "or nickname like " + "'%" + condition
						+ "%'";
			}
			sql += " limit ?,?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pageOffset);
			ps.setInt(2, pageSize);
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
			ps = conn.prepareStatement(sqlCount);
			rs = ps.executeQuery();
			int totalRecord = 0;
			while (rs.next()) {
				totalRecord = rs.getInt(1);
			}
			int totalPage = (totalRecord - 1) / pageSize + 1;
			pages.setPageOffset(pageOffset);
			pages.setPageSize(pageSize);
			pages.setTotalPage(totalPage);
			pages.setTotalRecord(totalRecord);
			pages.setDatas(users);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(conn);
		}
		return pages;
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
				throw new MessageException("用户名不存在！");
			}
			if (!user.getPassword().equals(password)) {
				throw new MessageException("用户密码不存在！");
			}
			if (user.getStatus() == 0) {
				throw new MessageException("该用户处于停用状态，不能登录！");
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
