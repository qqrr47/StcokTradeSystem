<%@page import="com.stock.entity.User"%>
<%@ page import="com.stock.entity.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	User uib = (User) session.getAttribute("user");
	String stockid = request.getParameter("stockid");
	String stockName = request.getParameter("stockName");
%>
<!DOCTYPE html>
<html>
<head>
<title>出售股票--股票交易系统</title>
<link rel="stylesheet" href="<%=path%>/assets/css/styles.css" />
</head>
<body>
	<form method="post" action="/SaleStockServlet">
		<div id="mainlist">
			<h1>股票交易系统</h1>
			<div class="mainlist">
				<h1>
					<span id="sp"> <a href="/MainServlet">返回主页</a>
						&nbsp;&nbsp;&nbsp;&nbsp;欢迎您：<%=uib.getName()%>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="/LogoutServlet">退出系统</a>
					</span>
				</h1>
				<table border="0" id="con">
					<tr>
						<td colspan="2" nowrap class="tdc"><span id="sp">交易股票</span>
						</td>

					</tr>
					<tr>
						<td colspan="2" nowrap class="tdc">&nbsp;</td>

					</tr>
					<tr>
						<td nowrap class="tdr">交易数量：</td>
						<td nowrap class="tdl"><input type="text" class="nt"
							id="tradeNumber" name="tradeNumber"></td>
					</tr>
					<tr>
						<td nowrap class="tdr">交易价格：</td>
						<td nowrap class="tdl"><input type="text" class="nt"
							id="tradePrice" name="tradePrice"></td>
					</tr>
					<tr hidden="hidden">
						<td colspan="2" nowrap class="tdc"><input type="text" class="nt"
							id="stockid" name="stockid" value="<%=stockid%>"></td>
					</tr>
					<tr hidden="hidden">
						<td colspan="2" nowrap class="tdc">
							<input type="text" id="stockName" name="stockName" value="<%=stockName%>">
						</td>
					</tr>
					<tr>
						<td colspan="2" nowrap class="tdc">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="2" nowrap class="tdc"><input type="submit"
							class="sm" value="确认交易" /></td>
					</tr>
				</table>
				<br /> <br />
			</div>
		</div>
	</form>
</body>
</html>