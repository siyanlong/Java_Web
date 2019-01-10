package siyl.cit.msg.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBUtil {
	// 方法一：通过配置文件配置数据库连接
	// 优点是连接好管理，容易修改，没有在代码里写死
	// 缺点是每次都需要重新连接数据库，性能慢，没有使用量连接池
	/*
	 * public static Connection getConnection() { Properties prop =
	 * PropertiesUtil.getJdbcProp(); String username = prop.getProperty("username");
	 * String password = prop.getProperty("password"); String url =
	 * prop.getProperty("url"); Connection connection = null; try { connection =
	 * DriverManager.getConnection(url, username, password); } catch (SQLException
	 * e) { e.printStackTrace(); }
	 * 
	 * return connection; }
	 */

	// 方法一：通过Tomcat配置数据源的方法
	// 参考：Tomcat的帮助文档，file:///D:/Program%20Files/apache-tomcat-6.0.33/webapps/docs/jndi-resources-howto.html
	// 优点是使用连接池，容易修改，并且性能有提高
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) envCtx.lookup("jdbc/msg");
			connection = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return connection;
	}

	public static void close(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(PreparedStatement ps) {
		try {
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
