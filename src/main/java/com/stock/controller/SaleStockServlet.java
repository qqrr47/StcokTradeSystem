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

@WebServlet("/SaleStockServlet")
public class SaleStockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		User user = ((User) session.getAttribute("user"));
		String stockId = request.getParameter("stockid");
		String stockName = request.getParameter("stockName");
		String tradeNumberStr = request.getParameter("tradeNumber");
		String tradePriceStr = request.getParameter("tradePrice");

		try {
			Integer tradeNumber = Integer.parseInt(tradeNumberStr);
			Float tradePrice = Float.parseFloat(tradePriceStr);

			StockTradeModel stm = new StockTradeModel();
			int tradeResult = stm.saleStock(user, stockId, stockName, tradePrice, tradeNumber);

			switch (tradeResult) {
				case 0:
					out.print("<script>alert('交易失败！');location.href='" + pathadd + "/MainServlet';</script>");
					break;
				case 1:
					out.print("<script>alert('委托成功！');location.href='" + pathadd + "/MainServlet';</script>");
					break;
				case 2:
					out.print("<script>alert('交易成功！');location.href='" + pathadd + "/MainServlet';</script>");
					break;
				case -2:
					out.print("<script>alert('库存不足！');location.href='" + pathadd + "/MainServlet';</script>");
					break;
				default:
					out.print("<script>alert('价格超出范围！');location.href='" + pathadd + "/MainServlet';</script>");
			}

			UserModel uim = new UserModel();
			User updatedUser = (User) uim.getUser(user.getUserid());
			session.setAttribute("user", updatedUser);
		} catch (NumberFormatException e) {
			out.print("<script>alert('请输入有效的数字！');location.href='" + pathadd + "/MainServlet';</script>");
		} catch (SQLException e) {
			out.print("<script>alert('交易失败，请稍后重试！');location.href='" + pathadd + "/MainServlet';</script>");
		}
	}


}