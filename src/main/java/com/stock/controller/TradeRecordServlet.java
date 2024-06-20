package com.stock.controller;

import com.stock.entity.TradeRecord;
import com.stock.entity.User;
import com.stock.model.StockQueryModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/TradeRecordServlet")
public class TradeRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		String userid = ((User)session.getAttribute("user")).getUserid();
		StockQueryModel sqm = new StockQueryModel();
		List<TradeRecord> list = null;
		try {
			list = sqm.getStockRecord(userid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("list", list);
		request.getRequestDispatcher("/tradeRecord.jsp").forward(
				request, response);
	}
}