package com.stock.controller;

import com.stock.entity.User;
import com.stock.model.StockTradeModel;
import com.stock.model.UserModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/BuyStockServlet")
public class BuyStockServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String pathadd = request.getContextPath();
		User user = (User) session.getAttribute("user");
		String stockId = request.getParameter("stockId");
		String stockName = request.getParameter("stockName");
		String tradeNumberstr = request.getParameter("tradeNumber");
		String tradePricestr = request.getParameter("stockPrice");

		try {
			Integer tradeNumber = Integer.parseInt(tradeNumberstr);
			Float tradePrice = Float.parseFloat(tradePricestr);

			StockTradeModel stm = new StockTradeModel();
			int n = stm.buyStock(user, stockId, stockName, tradePrice, tradeNumber);

			UserModel uim = new UserModel();
			session.removeAttribute("user");
			User uib = (User) uim.getUser(user.getUserid());
			session.setAttribute("user", uib);

			if (n == 0)
				out.print("<script>alert('交易失败!');location.href='" + pathadd + "/MainServlet';</script>");
			else if (n == 2)
				out.print("<script>alert('交易成功!');location.href='" + pathadd + "/MainServlet';</script>");
			else if (n == -1)
				out.print("<script>alert('价格不合理!');location.href='" + pathadd + "/MainServlet';</script>");
			else if (n == -2)
				out.print("<script>alert('余额不足!');location.href='" + pathadd + "/MainServlet';</script>");
			else
				out.print("<script>alert('委托成功!');location.href='" + pathadd + "/MainServlet';</script>");
		} catch (NumberFormatException e) {
			out.print("<script>alert('Invalid number format!');location.href='" + pathadd + "/MainServlet';</script>");
		} catch (SQLException e) {
			e.printStackTrace();
			out.print("<script>alert('Database error!');location.href='" + pathadd + "/MainServlet';</script>");
		}
	}

}