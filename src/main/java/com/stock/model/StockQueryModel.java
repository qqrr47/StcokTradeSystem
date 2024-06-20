package com.stock.model;

import com.stock.entity.HistoryPrice;
import com.stock.entity.Inventory;
import com.stock.entity.Stock;
import com.stock.entity.TradeRecord;
import com.stock.util.JDBCTools;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockQueryModel {
	int sum=2;
	/**
	 * 查询所有股票信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Stock> getAllStock() throws Exception {
		String sql = "select * from StockInfo";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Stock> stockList = new ArrayList<>();

		try {
			connection = JDBCTools.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String stockId = resultSet.getString("stock_id");
				String stockName = resultSet.getString("stock_name");
				Float stockPrice = resultSet.getFloat("stock_price");
				stockList.add(new Stock(stockId, stockName, stockPrice));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭资源
			JDBCTools.release(connection, preparedStatement, resultSet);
		}

		return stockList;
	}

	/**
	 * 查询记录总数
	 * 
	 * @return
	 * @throws Exception
	 */
	public int count() {
		int ret = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = JDBCTools.getConnection();
			String sql = "SELECT COUNT(*) FROM StockInfo";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				ret = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭资源
			JDBCTools.release(connection, preparedStatement, resultSet);
		}

		return ret;
	}


	/**
	 * 分页查询
	 * 
	 * @param page
	 *            显示的页码
	 * @param pageContent
	 *            每页的容量
	 * @return
	 * @throws Exception
	 */
	public List<Stock> pageListStockPrice(int page, int pageContent)
			throws Exception {
		int startNum = (page - 1) * pageContent + 1; // 计算起始记录点
		int endNum = page * pageContent; // 计算终止的记录点
		String sql = "SELECT stock_id,stock_name,stock_price\n" +
				"FROM  \n" +
				"(\n" +
				"    SELECT *, ROW_NUMBER() OVER (ORDER BY stock_id) AS RowNum \n" +
				"    FROM StockInfo\n" +
				") AS SubQuery \n" +
				"WHERE RowNum BETWEEN ? AND ?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Stock> stockList = new ArrayList<Stock>();

		try {
			connection = JDBCTools.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, startNum);
			preparedStatement.setInt(2, endNum);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String stockId = resultSet.getString("stock_id");
				String stockName = resultSet.getString("stock_name");
				Float stockPrice = resultSet.getFloat("stock_price");
				stockList.add(new Stock(stockId, stockName, stockPrice));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭资源
			JDBCTools.release(connection, preparedStatement, resultSet);
		}

		return stockList;
	}


	public List<HistoryPrice> searchHistoryPrice(String stockId)
			throws Exception {
		String sql = "select stock_id,stock_sequance,stock_price from StockPriceRecord where stock_id=? order by stock_sequance;";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<HistoryPrice> stockList = new ArrayList<HistoryPrice>();

		try {
			connection = JDBCTools.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, stockId);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String stockid = resultSet.getString("stock_id");
				Integer stockSequance = resultSet.getInt("stock_sequance");
				Float stockPrice = resultSet.getFloat("stock_price");
				stockList.add(new HistoryPrice(stockid, stockPrice, stockSequance));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭资源
			JDBCTools.release(connection, preparedStatement, resultSet);
		}

		return stockList;
	}
	/**
	 * 通过搜索内容查找股票信息
	 *
	 * @return
	 * @throws Exception
	 */
	public List<Stock> searchQuery(String searchContent)
			throws Exception {
		String sql = "SELECT * FROM StockInfo \n" +
				"WHERE stock_id LIKE ? OR stock_name LIKE ?\n" +
				"ORDER BY stock_id";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Stock> stockList = new ArrayList<Stock>();

		try {
			connection = JDBCTools.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "%" + searchContent + "%");
			preparedStatement.setString(2, "%" + searchContent + "%");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String stockId = resultSet.getString("stock_id");
				String stockName = resultSet.getString("stock_name");
				Float stockPrice = resultSet.getFloat("stock_price");
				stockList.add(new Stock(stockId, stockName, stockPrice));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭资源
			JDBCTools.release(connection, preparedStatement, resultSet);
		}

		return stockList;
	}

	/**
	 * 查询用户现有股票
	 * 
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public List<Inventory> getInventory(String userid) throws Exception {
		String sql = "SELECT A.stock_id,A.stock_num, B.stock_name, B.stock_price\n" +
				"FROM Inventory A\n" +
				"JOIN StockInfo B ON A.stock_id = B.stock_id where A.user_id = ?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Inventory> stockList = new ArrayList<Inventory>();

		try {
			connection = JDBCTools.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userid);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String stockId = resultSet.getString("stock_id");
				String stockName = resultSet.getString("stock_name");
				Integer stockNum = resultSet.getInt("stock_num");
				Float stockPrice = resultSet.getFloat("stock_price");
				stockList.add(new Inventory(userid, stockId, stockName,
						stockNum, stockPrice));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭资源
			JDBCTools.release(connection, preparedStatement, resultSet);
		}

		return stockList;
	}
	
	/**
	 * 查询用户股票交易记录
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public List<TradeRecord> getStockRecord(String userid) throws Exception {
		String sql = "select * from TradeRecord where user_id = ?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<TradeRecord> list = new ArrayList<TradeRecord>();

		try {
			connection = JDBCTools.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userid);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String userId = resultSet.getString("user_id");
				String stockId = resultSet.getString("stock_id");
				String stockName = resultSet.getString("stock_name");
				Integer tradeNum = resultSet.getInt("trade_num");
				Float tradePrice = resultSet.getFloat("trade_price");
				String tradeTime = resultSet.getString("trade_time");
				Integer tradeType = resultSet.getInt("trade_type");
				Integer status = resultSet.getInt("status");
				list.add(new TradeRecord(userId, stockId, stockName, tradeNum, tradePrice, tradeTime, tradeType, status));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭资源
			JDBCTools.release(connection, preparedStatement, resultSet);
		}
		return list;
	}

}