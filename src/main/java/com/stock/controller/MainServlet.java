package com.stock.controller;

import com.stock.entity.Inventory;
import com.stock.entity.User;
import com.stock.model.StockQueryModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
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
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		User user = ((User) session.getAttribute("user"));
		if(user == null) {
			out.print("<script>alert('用户未登录！');location.href='"
					+ "/login.jsp';</script>");
			return;
		}
		StockQueryModel sqm = new StockQueryModel();
		List<Inventory> list = new ArrayList<Inventory>();
		try {
			list = sqm.getInventory(user.getUserid());
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("ownlist", list);
		request.getRequestDispatcher("/main.jsp").forward(request,
				response);
	}
}