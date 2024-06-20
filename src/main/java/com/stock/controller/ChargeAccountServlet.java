package com.stock.controller;

import com.stock.entity.User;
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
@WebServlet("/ChargeAccountServlet")
public class ChargeAccountServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8"); // 设置字符编码为UTF-8
		HttpSession session = request.getSession();
		String pathadd = request.getContextPath();
		PrintWriter out = response.getWriter();

		User user = (User) session.getAttribute("user");
		if(user == null) {
			out.print("<script>alert('用户未登录！');location.href='" + pathadd
					+ "/login.jsp';</script>");
			return;
		}
		String userid = user.getUserid();

		String chargeStr = request.getParameter("charge");
		if(chargeStr == null || chargeStr.isEmpty()) {
			out.print("<script>alert('请输入充值金额！');location.href='" + pathadd
					+ "/chargeAccount.jsp';</script>");
			return;
		}
		Float charge = null;
		try {
			charge = Float.parseFloat(chargeStr);
			if (charge < 0) {
				out.print("<script>alert('充值金额不能负值！');location.href='" + pathadd
						+ "/chargeAccount.jsp';</script>");
				return;
			}
		} catch (NumberFormatException e) {
			out.print("<script>alert('请输入有效的充值金额！');location.href='" + pathadd
					+ "/chargeAccount.jsp';</script>");
			return;
		}

		UserModel uam = new UserModel();
		try {
			uam.chargeAccount(userid, charge);
		} catch (SQLException e) {
			e.printStackTrace();
			out.print("<script>alert('充值失败，请稍后重试！');location.href='" + pathadd
					+ "/chargeAccount.jsp';</script>");
			return;
		} catch (Exception ex) {
			ex.printStackTrace();
			out.print("<script>alert('系统出现异常，请稍后重试！');location.href='" + pathadd
					+ "/chargeAccount.jsp';</script>");
			return;
		}

		UserModel uim = new UserModel();
		User uib = null;
		uib = (User) uim.getUser(userid);

		session.setAttribute("user", uib);

		response.sendRedirect(pathadd + "/MainServlet");
	}

}