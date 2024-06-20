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
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {


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
		String pathadd = request.getContextPath();
		PrintWriter out = response.getWriter();
		String rand = (String) session.getAttribute("rand");
		String input = request.getParameter("rand");
		if (rand.equals(input)) {
			String userid = request.getParameter("userid");
			String password = request.getParameter("password");
			UserModel ua = new UserModel();
			User uib = null;
			try {
				uib = ua.checkLogin(userid, password);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (uib == null)
				out.print("<script>alert('账号或密码错误!');location.href='login.jsp';</script>");
			else {
				Socket socket = null;
				OutputStream outputStream = null;

				try {
					// 等待一段时间，确保服务器先运行
					Thread.sleep(500);

					// 创建客户端套接字，连接到服务器
					socket = new Socket("localhost", 12345);
					System.out.println("Connected to server.");

					// 获取输出流
					outputStream = socket.getOutputStream();

					// 向服务器发送数据
					String message = "Hello from client!";
					outputStream.write(message.getBytes());
					System.out.println("Sent: " + message);
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
				} finally {
					try {
						// 关闭连接
						if (outputStream != null) {
							outputStream.close();
						}
						if (socket != null) {
							socket.close();
							session.setAttribute("user", uib);
							response.sendRedirect(pathadd + "/MainServlet");
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} else {
			out.print("<script>alert('请输入正确的验证码！');location.href='login.jsp';</script>");
		}
	}

}