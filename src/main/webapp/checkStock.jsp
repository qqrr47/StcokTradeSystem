<%@page import="com.stock.entity.User"%>
<%@page import="java.util.List"%>
<%@page import="com.stock.entity.Stock"%>
<%@ page import="com.stock.entity.HistoryPrice" %>
<%@ page import="com.stock.entity.HistoryPrice" %>
<%@ page import="com.stock.entity.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String stockid = request.getParameter("stockid");
    User uib = (User) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
    <title>股价详情查询--股票交易系统</title>
    <link rel="stylesheet" href="<%=path%>/assets/css/styles.css" />
</head>
<body>
<form method="post" action="/HistoryPriceServlet">
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
                    <td colspan="4" nowrap class="tdc"><span id="sp">股价详情</span>
                    </td>
                </tr>
                <tr>
                    <td nowrap class="tdc">&nbsp;</td>
                    <td nowrap class="tdc">&nbsp;</td>
                </tr>
                <tr>
                    <td width="200" nowrap class="tdc"><span id="sp">股票周期</span>
                    </td>
                    <td width="200" nowrap class="tdc"><span id="sp">股票价格</span>
                    </td>
                </tr>
                <%
                    List<HistoryPrice> list = (List<HistoryPrice>) request
                            .getAttribute("list"); //查询的记录信息
                    if (list != null) {
                        for (int i = 0; i < list.size(); i++) { // 对记录信息做循环显示
                            HistoryPrice si = (HistoryPrice) list.get(i);
                %>
                <tr>
                    <td nowrap class="tdc"><%=si.getStockSequance()%></td>
                    <td nowrap class="tdc"><%=si.getStockPrice()%></td>
                </tr>
                <%
                        }
                    }
                %>
            </table>

        </div>
    </div>
</form>
</body>
</html>