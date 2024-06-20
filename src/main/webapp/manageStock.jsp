<%@page import="com.stock.entity.User"%>
<%@page import="java.util.List"%>
<%@page import="com.stock.entity.Stock"%>
<%@ page import="com.stock.entity.Stock" %>
<%@ page import="com.stock.entity.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	User uib = (User) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<title>股票信息管理--股票交易系统</title>
<link rel="stylesheet" href="<%=path%>/assets/css/styles.css" />
</head>
<body>
	<form method="post" action="">
		<div id="mainlist">
			<h1>股票交易系统</h1>
			<div class="mainlist">
				<h1>
					<span id="sp"> <a href="/MainServlet">返回主页</a>
						&nbsp;&nbsp;&nbsp;&nbsp;欢迎您：<%=uib.getName()%>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="/LogoutServlet">退出系统</a>
					</span>
				</h1>
				<table width="600" border="0" id="con">
					<tr>
						<td colspan="4" nowrap class="tdc"><span id="sp">股票信息管理</span>
						</td>
					</tr>
					<tr>
						<td width="145" nowrap class="tdc"><span id="sp">股票编码</span>
						</td>
						<td width="145" nowrap class="tdc"><span id="sp">股票名称</span>
						</td>
						<td width="147" nowrap class="tdc"><span id="sp">最新单价</span>
						</td>
					</tr>
					<%
						List<Stock> list = (List<Stock>) request
								.getAttribute("list"); //查询的记录信息
						if (list != null) {
							for (int i = 0; i < list.size(); i++) { // 对记录信息做循环显示
								Stock si = (Stock) list.get(i);
					%>
					<tr>
						<td nowrap class="tdc"><%=si.getStock_id()%></td>
						<td nowrap class="tdc"><%=si.getStock_name()%></td>
						<td nowrap class="tdc"><%=si.getStock_price()%></td>
						</td>
					</tr>
					<%
						}
						}
					%>
				</table>
				<br /> <br />
			</div>
		</div>
	</form>
</body>
</html>