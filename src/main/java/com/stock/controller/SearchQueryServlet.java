package com.stock.controller;

import com.stock.entity.Stock;
import com.stock.model.StockQueryModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/SearchQueryServlet")
public class SearchQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		StockQueryModel sqm = new StockQueryModel();
		String searchContent = request.getParameter("queryKey").toUpperCase();
		List<Stock> list = null;
		try {
			list = sqm.searchQuery(searchContent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("list", list);
		request.getRequestDispatcher("/searchQuery.jsp").forward(request,
				response);
	}
}