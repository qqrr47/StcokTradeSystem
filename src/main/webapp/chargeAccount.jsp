<%@page import="com.stock.entity.User"%>
<%@page import="java.util.List"%>
<%@page import="com.stock.entity.Stock"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<title>实时股价查询--股票交易系统</title>
<link rel="stylesheet" href="<%=path %>/assets/css/styles.css" />
</head>
<body>
	<form method="post" action="/ChargeAccountServlet">
		<div id="mainlist">
			<h1>股票交易系统</h1>
			<div class="mainlist">
				<h1>
					<span id="sp"> <a href="/MainServlet">返回主页</a>
						&nbsp;&nbsp;&nbsp;&nbsp;欢迎您：&nbsp;&nbsp;&nbsp;&nbsp; <a href="/LogoutServlet">退出系统</a>
					</span>
				</h1>
				<table border="0" id="con">
					<tr>
						<td colspan="3" nowrap class="tdc"><span id="sp">账户充值</span>
						</td>

					</tr>
					<tr>
						<td colspan="3" nowrap class="tdc">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="3" nowrap class="tdc">请输入要充值的金额</td>
					</tr>

					<tr>
						<td colspan="3" nowrap class="tdc"><input type="text"
							class="maintext" name="charge" id="charge" /></td>
					</tr>
					<tr>
						<td colspan="3" nowrap class="tdc"><input type="submit"
							class="sm" value="充值" /></td>
					</tr>
				</table>
				<br /> <br />
			</div>
		</div>
	</form>
</body>
</html>