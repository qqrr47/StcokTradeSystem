<%@page import="java.util.List"%>
<%@page import="com.stock.entity.Stock"%>
<%@page import="com.stock.entity.User"%>
<%@ page import="com.stock.entity.Inventory" %>
<%@ page import="com.stock.entity.Inventory" %>
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
<title>业务菜单--股票交易系统</title>
	<title>业务菜单--股票交易系统</title>
	<link rel="stylesheet" href="<%=path%>/assets/css/styles.css" />
	<script type="text/javascript">
		function confirmLogout() {
			// 确认注销
			if (confirm('您确定要注销吗？')) {
				var userId = '<%= uib.getUserid() %>'; // 获取用户ID
				// 如果用户确认注销，重定向到注销Servlet，并传递用户ID
				window.location.href = "/LogoutServlet?type=1&userId=" + userId;
			}
		}
	</script>
</head>
<body>
	<form method="post" action="">
		<div id="mainlist">
			<h1>股票交易系统</h1>
			<div class="mainlist">
				<h1>
					<span id="sp">
						<span id="sp">
							欢迎您：<%=uib.getName()%>&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="/LogoutServlet?type=0">退出系统</a>
							 <input type="button" value="注销" onclick="confirmLogout();"/>
						</span>
					</span>
				</h1>
				<div>
					<table width="250" border="0" id="con1">
						<tr>
							<td width="117" nowrap class="tdr"></td>
							<td width="117" nowrap class="tdl"></td>
						</tr>
						<tr>
							<td colspan="2" nowrap class="tdc">您的账户资金</td>
						</tr>
						<tr>
							<td colspan="2" nowrap class="tdc"><%=uib.getAccountBalance()%></td>
						</tr>
						<tr>
							<td colspan="2" nowrap class="tdc">&nbsp;</td>
						</tr>
						<tr>
							<td colspan="2" nowrap class="tdc">
								<span id="sp">请选择业务类型</span>
							</td>
						</tr>
						<tr>
							<td colspan="2" nowrap class="tdc">&nbsp;</td>
						</tr>
						<tr>
							<td colspan="2" nowrap class="tdc">
								<a href="/PricePageQueryServlet">实时股价查询</a>
							</td>
						</tr>
						<tr>
							<td colspan="2" nowrap class="tdc">
								<a href="<%=path%>/chargeAccount.jsp">帐户充值</a>
							</td>
						</tr>
						<tr>
							<td colspan="2" nowrap class="tdc">
								<a href="/TradeRecordServlet">股票交易记录查询</a>
							</td>
						</tr>
						<tr>
							<td colspan="2" nowrap class="tdc">
								<a href="/ManageStockServlet">股票信息管理</a>
							</td>
						</tr>
						<tr>
							<td colspan="2" nowrap class="tdc">&nbsp;</td>
						</tr>

					</table>
					<table width="550" border="0" id="con2">
						<tr>
							<td colspan="6" nowrap class="tdc">
								<span id="sp">您持有的股票信息</span>
							</td>
						</tr>
						<tr>
							<td width="86" nowrap class="tdc">股票编码</td>
							<td width="86" nowrap class="tdc">股票名称</td>
							<td width="86" nowrap class="tdc">持有数量</td>
							<td width="86" nowrap class="tdc">最新单价</td>
							<td width="86" nowrap class="tdc">市值金额</td>
							<td width="86" nowrap class="tdc">相关操作</td>
						</tr>
						<%
						List<Inventory> list = (List<Inventory>) request
								.getAttribute("ownlist"); //查询的记录信息
						if (list != null) {
							for (int i = 0; i < list.size(); i++) { // 对记录信息做循环显示
								Inventory si = (Inventory) list.get(i);
								String salelink = path + "/saleStock.jsp?stockid="
										+ si.getStock_id()+ "&stockName=" + si.getStock_name();
					%>
						<tr>
						<td width="86" nowrap class="tdc"><%=si.getStock_id()%></td>
						<td width="86" nowrap class="tdc"><%=si.getStock_name()%></td>
						<td width="86" nowrap class="tdc"><%=si.getStock_num()%></td>
						<td width="86" nowrap class="tdc"><%=si.getStock_price()%></td>
						<td width="86" nowrap class="tdc"><%=si.getStock_price()*si.getStock_num()%></td>
						<td width="86" nowrap class="tdc"><a href="<%=salelink%>">卖出</a></td>
					</tr>
					<%
						}
						}
					%>
					</table>
<%--					<br /> <br />--%>
<%--					<%--%>
<%--						// 显示页码--%>
<%--						if (pageTotal != null) {--%>
<%--							out.print("<span id=\"sp\">页数：");--%>
<%--							for (int i = 1; i <= pageTotal; i++) { //根据总的页数做循环显示--%>
<%--								out.println("<a href='" + path--%>
<%--										+ "/PricePageQueryServlet?page=" + i + "'>"--%>
<%--										+ i + "</a>&nbsp;");--%>
<%--							}--%>
<%--							out.print("</span>");--%>
<%--						}--%>
<%--					%>--%>
				</div>
			</div>
		</div>
	</form>
</body>
</html>