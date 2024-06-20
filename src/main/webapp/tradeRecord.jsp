<%@page import="com.stock.entity.TradeRecord"%>
<%@page import="com.stock.entity.User"%>
<%@page import="java.util.List"%>
<%@page import="com.stock.entity.Stock"%>
<%@ page import="com.stock.entity.TradeRecord" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	User uib = (User) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<title>交易记录查询--股票交易系统</title>
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
				<table width="600" border="1" id="con">
					<tr>
						<td colspan="7" nowrap class="tdc"><span id="sp">交易记录查询</span>
						</td>
					</tr>
					<tr>
						<td width="81" nowrap class="tdc"><span id="sp">股票编码</span></td>
						<td width="81" nowrap class="tdc"><span id="sp">股票名称</span></td>
						<td width="81" nowrap class="tdc"><span id="sp">交易数量</span></td>
						<td width="81" nowrap class="tdc"><span id="sp">交易单价</span></td>
						<td width="81" nowrap class="tdc"><span id="sp">交易时间</span></td>
						<td width="81" nowrap class="tdc"><span id="sp">交易类型</span></td>
						<td width="84" nowrap class="tdc"><span id="sp">状态</span></td>
					</tr>
					<%
						List<TradeRecord> list = (List<TradeRecord>) request
								.getAttribute("list"); //查询的记录信息
						if (list != null) {
							for (int i = 0; i < list.size(); i++) { // 对记录信息做循环显示
								TradeRecord trb = (TradeRecord) list.get(i);
					%>
					<tr>
						<td nowrap class="tdc"><%=trb.getStock_id()%></td>
						<td nowrap class="tdc"><%=trb.getStock_name()%></td>
						<td nowrap class="tdc"><%=trb.getTrade_num()%></td>
						<td nowrap class="tdc"><%=trb.getTrade_price()%></td>
						<td nowrap class="tdc"><%=trb.getTrade_time()%></td>
						<td nowrap class="tdc">
							<%
								int type = trb.getTrade_type();
								String typeText = "";
								switch(type) {
									case 1:
										typeText = "买入";
										break;
									case 2:
										typeText = "卖出";
										break;
									default:
										typeText = "未知状态";
								}
								out.print(typeText);
							%>
						</td>
						<td nowrap class="tdc">
							<%
								int status = trb.getStatus();
								String statusText = "";
								switch(status) {
									case 0:
										statusText = "废单";
										break;
									case 1:
										statusText = "委托中";
										break;
									case 2:
										statusText = "完成";
										break;
									default:
										statusText = "未知状态";
								}
								out.print(statusText);
							%>
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