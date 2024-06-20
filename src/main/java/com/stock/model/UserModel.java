package com.stock.model;

import com.stock.entity.User;
import com.stock.util.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {

	// 注册
	public int appendUser(String userid, String password, String name) {
		int num = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			String checkSql = "SELECT user_id FROM UserInfo WHERE user_id = ?";
			connection = JDBCTools.getConnection();
			preparedStatement = connection.prepareStatement(checkSql);
			preparedStatement.setString(1, userid);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				// 用户已存在，返回0表示添加失败
				return 0;
			}

			// 用户不存在，执行插入操作
			String insertSql = "INSERT INTO UserInfo (user_id, password, name, accountBalance) VALUES (?, ?, ?, 1000000.0)";
			preparedStatement = connection.prepareStatement(insertSql);
			preparedStatement.setString(1, userid);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, name);

			// 执行插入操作并返回受影响的行数
			num = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭资源
			JDBCTools.release(connection, preparedStatement, null);
		}
		return num;
	}


	// 获取用户信息
	public User getUser(String userid) {
		User user = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			String sql = "SELECT * FROM UserInfo WHERE user_id=?";
			connection = JDBCTools.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userid);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String password = resultSet.getString("password");
				String name = resultSet.getString("name");
				Float accountBalance = resultSet.getFloat("accountBalance");
				user = new User(userid, password, name, accountBalance);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭资源
			JDBCTools.release(connection, preparedStatement, null);
		}
		return user;
	}


	// 检查登录
	public User checkLogin(String userid, String password) {
		User user = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			String sql = "SELECT * FROM UserInfo WHERE user_id=? AND password=?";
			connection = JDBCTools.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userid);
			preparedStatement.setString(2, password);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String name = resultSet.getString("name");
				Float accountBalance = resultSet.getFloat("accountBalance");
				user = new User(userid, password, name, accountBalance);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭资源
			JDBCTools.release(connection, preparedStatement, null);
		}
		return user;
	}


	public int deleteUser(String userId) {
		int num = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			String sql = "DELETE FROM UserInfo WHERE user_id = ?";
			connection = JDBCTools.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userId);
			num = preparedStatement.executeUpdate();

			// 删除Inventory表中对应用户的记录
			sql = "DELETE FROM Inventory WHERE user_id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userId);
			preparedStatement.executeUpdate();

			// 删除TradeRecord表中对应用户的记录
			sql = "DELETE FROM TradeRecord WHERE user_id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace(); // 或者记录日志
		} finally {
			// 关闭资源
			JDBCTools.release(connection, preparedStatement, null);
		}

		return num;
	}

	public int chargeAccount(String userid, Float charge) throws SQLException {
		int num = 0;
		String sql = "update UserInfo set accountBalance=accountBalance+? where user_id=?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = JDBCTools.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setFloat(1, charge);
			preparedStatement.setString(2, userid);
			num = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭资源
			JDBCTools.release(connection, preparedStatement,null);
		}
		return num;
	}


}