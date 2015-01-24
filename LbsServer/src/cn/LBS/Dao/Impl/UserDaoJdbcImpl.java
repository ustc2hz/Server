package cn.LBS.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.LBS.Model.User;
import cn.LBS.exception.DaoException;
import cn.LBS.utils.JdbcUtils;

public class UserDaoJdbcImpl implements cn.LBS.Dao.UserDao {
	public void add(User user) {

		System.out.println(user.getUsername());
		System.out.println(user.getUserpassword());

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "insert into admin(admin_name,admin_password) values(?,?)";
			st = conn.prepareStatement(sql);
			st.setString(1, user.getUsername());
			st.setString(2, user.getUserpassword());

			st.executeUpdate();

		} catch (Exception e) {
			throw new DaoException(e);
		} finally {
			JdbcUtils.release(conn, st, rs);
		}

	}

	// username = ' or 1=1 or username=' java--class
	public User find(User user) {

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();

			String sql = "select * from admin where admin_name=? and admin_password=?";
			st = conn.prepareStatement(sql);
			st.setString(1, user.getUsername());
			st.setString(2, user.getUserpassword());

			rs = st.executeQuery(); //
			if (rs.next()) {
				User user1 = new User();
				user1.setUsername(rs.getString("admin_name"));
				user1.setUserpassword(rs.getString("admin_password"));
				return user1;
			}
			return null;
		} catch (Exception e) {
			throw new DaoException(e);
		} finally {
			JdbcUtils.release(conn, st, rs);
		}
	}

	public void update(User user) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "update admin set admin_password=? where admin_name=?";
			st = conn.prepareStatement(sql);
			st.setString(1, user.getUserpassword());
			st.setString(2, user.getUsername());
			st.executeUpdate();
		} catch (Exception e) {
			throw new DaoException(e);
		} finally {
			JdbcUtils.release(conn, st, rs);
		}
	}

}
