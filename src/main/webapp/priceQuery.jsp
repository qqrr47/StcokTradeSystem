<%@page import="com.stock.entity.User"%>
<%@page import="java.util.List"%>
<%@page import="com.stock.entity.Stock"%>
<%@ page import="com.stock.entity.Stock" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	User uib = (User) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<title>实时股价查询--股票交易系统</title>
<link rel="stylesheet" href="<%=path%>/assets/css/styles.css" />
</head>
<body>
	<form method="post" action="/SearchQueryServlet">
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
						<td colspan="4" nowrap class="tdc"><span id="sp">实时股价查询</span>
						</td>
					</tr>
					<tr>
						<td colspan="4" nowrap class="tdc"><input type="text"
							class="maintext" name="queryKey" id="queryKey"
							placeholder="请输入股票名称或编码查询" /> <input type="submit"
							class="sm" value="查询" /></td>
					</tr>
					<tr>
						<td nowrap class="tdc">&nbsp;</td>
						<td nowrap class="tdc">&nbsp;</td>
						<td nowrap class="tdc">&nbsp;</td>
						<td nowrap class="tdc">&nbsp;</td>
					</tr>
					<tr>
						<td width="146" nowrap class="tdc"><span id="sp">股票编码</span>
						</td>
						<td width="146" nowrap class="tdc"><span id="sp">股票名称</span>
						</td>
						<td width="146" nowrap class="tdc"><span id="sp">最新单价</span>
						</td>
						<td width="146" nowrap class="tdc"><span id="sp">历史价格</span>
						</td>
						<td width="147" nowrap class="tdc"><span id="sp">相关操作</span>
						</td>
					</tr>
					<%
						Integer pageTotal = (Integer) request.getAttribute("pageTotal"); // 总的页数
						List<Stock> list = (List<Stock>) request
								.getAttribute("list"); //查询的记录信息
						if (list != null) {
							for (int i = 0; i < list.size(); i++) { // 对记录信息做循环显示
								Stock si = (Stock) list.get(i);
								String buylink = path + "/buyStock.jsp?stockId="
										+ si.getStock_id()+"&ownerId="+uib.getUserid()
										+"&stockPrice="+si.getStock_price()
										+"&stockName="+si.getStock_name();
								String checklink = path + "/HistoryPriceServlet?stockid="
										+ si.getStock_id();

					%>
					<tr>
						<td nowrap class="tdc"><%=si.getStock_id()%></td>
						<td nowrap class="tdc"><%=si.getStock_name()%></td>
						<td nowrap class="tdc"><%=si.getStock_price()%></td>
						<td nowrap class="tdc"><a href="<%=checklink%>">查询</a></td>
						<td nowrap class="tdc"><a href="<%=buylink%>">买入</a></td>
					</tr>
					<%
						}
						}
					%>
				</table>
				<br /> <br />
				<%
					// 显示页码
					if (pageTotal != null) {
						out.print("<span id=\"sp\">页数：");
						for (int i = 1; i <= pageTotal; i++) { //根据总的页数做循环显示
							out.println("<a href='" + path
									+ "/PricePageQueryServlet?page=" + i + "'>"
									+ i + "</a>&nbsp;");
						}
						out.print("</span>");
					}
				%>
			</div>
		</div>
	</form>
</body>
</html>