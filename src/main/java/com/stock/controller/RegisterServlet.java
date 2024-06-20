package com.stock.controller;

import com.stock.model.UserModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		String name = request.getParameter("name");

		UserModel ua = new UserModel();
		int n = 0;
		try {
			n = ua.appendUser(userid, password, name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (n == 0)
			out.print("<script>alert('账号已存在!');location.href='register.jsp';</script>");
		else
			out.print("<script>alert('注册成功!');location.href='login.jsp';</script>");
	}


}