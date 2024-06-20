package com.stock.controller;

import com.stock.model.UserModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");
		if (type == null) {
			type = "0"; // 如果没有收到类型参数，则将其设置为 "0"
		}
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String pathadd = request.getContextPath();
		HttpSession session = request.getSession();
		session.invalidate();//删除会话信息
		if(type.equals("1")){
			int num =0;
			UserModel userInfoModel = new UserModel();
			String userId = request.getParameter("userId");
			try {
				 num = userInfoModel.deleteUser(userId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (num== 1)
				out.print("<script>alert('注销成功!');location.href='" + pathadd
						+ "/login.jsp';</script>");
			else
				out.print("<script>alert('注销失败');location.href='" + pathadd
						+ "/login.jsp';</script>");
		}
		else{
			response.sendRedirect(pathadd+"/login.jsp");
		}
	}
}