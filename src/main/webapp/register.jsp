<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<title>注册页面--股票交易</title>
<link rel="stylesheet" href="assets/css/styles.css" />
<script type="text/javascript" src="<%=path%>/assets/js/register.js"></script>
</head>
<body>
	<form method="post" action="/RegisterServlet">
		<div id="umain">
			<h1>股票交易系统</h1>
			<div class="register">
				<h1>欢迎您注册账户</h1>
				<table width="100%" border="0">
					<tr>
						<td nowrap class="tdr">*用户账户：</td>
						<td class="tdl" nowrap><input type="text" id="userid"
							name="userid"></td>
						<td nowrap>（必填）</td>
					</tr>
					<tr>
						<td nowrap class="tdr">*密码：</td>
						<td class="tdl" nowrap><input type="password" id="password"
							name="password"></td>
						<td nowrap>（必填）</td>
					</tr>
					<tr>
						<td nowrap class="tdr">*确认密码：</td>
						<td class="tdl" nowrap><input type="password" id="passwordag"
							name="passwordag"></td>
						<td nowrap>（必填）</td>
					</tr>
					<tr>
						<td width="36%" nowrap class="tdr">*姓名：</td>
						<td class="tdl" width="40%" nowrap><input type="text"
							id="name" name="name"></td>
						<td width="24%" nowrap>（必填）</td>
					</tr>
					<tr>
						<td colspan="3"><input type="submit" value="注 册" onclick="return check()"></td>
					</tr>
					<tr>
						<td colspan="3"><input type="button" value="返 回"
											   onclick="javascrtpt:window.location.href='login.jsp'"></td>
					</tr>

				</table>

			</div>
		</div>
	</form>
</body>
</html>