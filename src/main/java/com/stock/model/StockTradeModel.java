package com.stock.model;

import com.stock.entity.User;
import com.stock.util.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StockTradeModel {

	/**
	 * 买家下单
	 *
	 * @param stockPrice
	 * @throws SQLException
	 */
	public int buyStock(User user, String stockId, String stockName, Float stockPrice, Integer stockNum) throws SQLException {
		String selectStockPriceSQL = "SELECT stock_price FROM StockInfo WHERE stock_id = ?";
		String insertTradeRecordSQL = "INSERT INTO TradeRecord(user_id, stock_id, stock_name, trade_num, trade_price, trade_time, trade_type, status) VALUES (?, ?, ?, ?, ?, ?, 1, ?)";
		String updateUserInfoSQL = "UPDATE UserInfo SET accountBalance = ? WHERE user_id = ?";
		String selectInventorySQL = "SELECT stock_num FROM Inventory WHERE user_id = ? AND stock_id = ?";
		String updateInventorySQL = "UPDATE Inventory SET stock_num = stock_num + ? WHERE user_id = ? AND stock_id = ?";
		String insertInventorySQL = "INSERT INTO Inventory(user_id, stock_id, stock_num) VALUES (?, ?, ?)";

		int result = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = JDBCTools.getConnection();
			preparedStatement = connection.prepareStatement(selectStockPriceSQL);
			preparedStatement.setString(1, stockId);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				//计算价格的合理范围
				Float currentPrice = resultSet.getFloat("stock_price");
				Float lowerBound = 0.5f * currentPrice;
				Float upperBound = 1.5f * currentPrice;
				Float totalPrice = stockPrice * stockNum;

				if (stockPrice < lowerBound || stockPrice > upperBound) {
					//废单，插入交易记录
					preparedStatement = connection.prepareStatement(insertTradeRecordSQL);
					preparedStatement.setString(1, user.getUserid());
					preparedStatement.setString(2, stockId);
					preparedStatement.setString(3, stockName);
					preparedStatement.setInt(4, stockNum);
					preparedStatement.setFloat(5, stockPrice);
					preparedStatement.setString(6, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
					preparedStatement.setInt(7, 0);
					preparedStatement.executeUpdate();
					result = -1; // 返回 -1 代表股票价格不在合理范围内
				} else if (totalPrice > user.getAccountBalance()) {
					result = -2; // 余额不足
				} else {//价格合理
					//买入价格小于市场价，委托交易，返回1
					if(stockPrice<currentPrice){
						preparedStatement = connection.prepareStatement(insertTradeRecordSQL);
						preparedStatement.setString(1, user.getUserid());
						preparedStatement.setString(2, stockId);
						preparedStatement.setString(3, stockName);
						preparedStatement.setInt(4, stockNum);
						preparedStatement.setFloat(5, stockPrice);
						preparedStatement.setString(6, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
						preparedStatement.setInt(7, 1);
						preparedStatement.executeUpdate();

						result = 1;
					}else{
						//插入成功交易记录
						preparedStatement = connection.prepareStatement(insertTradeRecordSQL);
						preparedStatement.setString(1, user.getUserid());
						preparedStatement.setString(2, stockId);
						preparedStatement.setString(3, stockName);
						preparedStatement.setInt(4, stockNum);
						preparedStatement.setFloat(5, stockPrice);
						preparedStatement.setString(6, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
						preparedStatement.setInt(7, 2);
						preparedStatement.executeUpdate();

						//修改账户资金
						Float subPrice = user.getAccountBalance() - totalPrice;
						preparedStatement = connection.prepareStatement(updateUserInfoSQL);
						preparedStatement.setFloat(1, subPrice);
						preparedStatement.setString(2, user.getUserid());
						preparedStatement.executeUpdate();

						preparedStatement = connection.prepareStatement(selectInventorySQL);
						preparedStatement.setString(1, user.getUserid());
						preparedStatement.setString(2, stockId);
						resultSet = preparedStatement.executeQuery();

						if (resultSet.next()) {//有库存，则加数量
							preparedStatement = connection.prepareStatement(updateInventorySQL);
							preparedStatement.setInt(1, stockNum);
							preparedStatement.setString(2, user.getUserid());
							preparedStatement.setString(3, stockId);
						} else {//无库存，则添记录
							preparedStatement = connection.prepareStatement(insertInventorySQL);
							preparedStatement.setString(1, user.getUserid());
							preparedStatement.setString(2, stockId);
							preparedStatement.setInt(3, stockNum);
						}
						preparedStatement.executeUpdate();
						result = 2; // 交易成功
					}
				}
			}
		} finally {
			// 释放资源
			JDBCTools.release(connection, preparedStatement, resultSet);
		}

		return result;
	}



	/**
	 * 卖家下单
	 * @param stockPrice
	 * @return
	 * @throws SQLException
	 */
	public int saleStock(User user, String stockId, String stockName, Float stockPrice, Integer stockNum) throws SQLException {
		int result = 0;
		String selectStockPriceSQL = "SELECT stock_price FROM StockInfo WHERE stock_id = ?";
		String insertTradeRecordSQL = "INSERT INTO TradeRecord(user_id, stock_id, stock_name, trade_num, trade_price, trade_time, trade_type, status) VALUES (?, ?, ?, ?, ?, ?, 2, ?)";
		String selectInventorySQL = "SELECT stock_num FROM Inventory WHERE user_id = ? AND stock_id = ?";
		String updateInventorySQL = "UPDATE Inventory SET stock_num = ? WHERE user_id = ? AND stock_id = ?";
		String deleteInventorySQL = "DELETE FROM Inventory WHERE user_id = ? AND stock_id = ?";
		String updateUserInfoSQL = "UPDATE UserInfo SET accountBalance = ? WHERE user_id = ?";
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		try {
			connection = JDBCTools.getConnection();
			preparedStatement = connection.prepareStatement(selectStockPriceSQL);
			preparedStatement.setString(1,stockId);
			resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				Float currentPrice = resultSet.getFloat("stock_price");
				Float lowerBound = 0.5f * currentPrice;
				Float upperBound = 1.5f * currentPrice;
				Float totalPrice = stockPrice * stockNum;

				// 超出价格范围，废单，插入交易记录
				if (stockPrice < lowerBound || stockPrice > upperBound){
					preparedStatement = connection.prepareStatement(insertTradeRecordSQL);
					preparedStatement.setString(1, user.getUserid());
					preparedStatement.setString(2, stockId);
					preparedStatement.setString(3, stockName);
					preparedStatement.setInt(4, stockNum);
					preparedStatement.setFloat(5, stockPrice);
					preparedStatement.setString(6, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
					preparedStatement.setInt(7, 0);
					preparedStatement.executeUpdate();

					result = -1;
				}
				else {
					//获取库存数量
					preparedStatement = connection.prepareStatement(selectInventorySQL);
					preparedStatement.setString(1, user.getUserid());
					preparedStatement.setString(2, stockId);
					resultSet = preparedStatement.executeQuery();

					//有库存，肯定有库存
					if (resultSet.next()){
						Integer availableStockNum = resultSet.getInt("stock_num");

						//库存不足
						if (availableStockNum < stockNum) {
							result = -2;
						}
						else {
							//委托交易，返回1
							if (stockPrice > currentPrice){
								//插入委托交易记录
								preparedStatement = connection.prepareStatement(insertTradeRecordSQL);
								preparedStatement.setString(1, user.getUserid());
								preparedStatement.setString(2, stockId);
								preparedStatement.setString(3, stockName);
								preparedStatement.setInt(4, stockNum);
								preparedStatement.setFloat(5, stockPrice);
								preparedStatement.setString(6, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
								preparedStatement.setInt(7,1);
								preparedStatement.executeUpdate();
								result = 1;
							}
							//交易成功，返回2
							else{
								//插入成功交易记录
								preparedStatement = connection.prepareStatement(insertTradeRecordSQL);

								// 设置参数值
								preparedStatement.setString(1, user.getUserid());
								preparedStatement.setString(2, stockId);
								preparedStatement.setString(3, stockName);
								preparedStatement.setInt(4, stockNum);
								preparedStatement.setFloat(5, stockPrice);
								preparedStatement.setString(6, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
								preparedStatement.setInt(7,2);
								preparedStatement.executeUpdate();

								//修改账户资金
								Float sumPrice = user.getAccountBalance()+totalPrice;
								preparedStatement = connection.prepareStatement(updateUserInfoSQL);
								preparedStatement.setFloat(1,sumPrice);
								preparedStatement.setString(2,user.getUserid());

								preparedStatement.executeUpdate();

								//stock_num等于stockNum
								if(availableStockNum == stockNum){

									//删除股票库存
									preparedStatement = connection.prepareStatement(deleteInventorySQL);
									preparedStatement.setString(1,user.getUserid());
									preparedStatement.setString(2,stockId);
								}
								//stock_num大于stockNum
								else{
									//更新持仓
									availableStockNum = availableStockNum - stockNum;
									preparedStatement = connection.prepareStatement(updateInventorySQL);
									preparedStatement.setInt(1,availableStockNum);
									preparedStatement.setString(2,user.getUserid());
									preparedStatement.setString(3,stockId);
								}
								preparedStatement.executeUpdate();

								result = 2;
							}
						}
					}else
						result = -2;//没有库存
				}
			}
		}finally {
			// 释放资源
			JDBCTools.release(connection, preparedStatement, resultSet);
		}
		return result;
	}
}