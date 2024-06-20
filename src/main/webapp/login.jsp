<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<title>登陆页面--股票交易系统</title>
<link rel="stylesheet" href="<%=path%>/assets/css/styles.css">
	<script type="text/javascript" src="<%=path%>/assets/js/login.js"></script>
</head>
<body>
	<form method="post" action="/LoginServlet">
		<div id="umain">
			<h1>股票交易系统</h1>
			<div class="login">
				<h1>欢迎登陆</h1>
				<p>
					<input type="text" name="userid" placeholder="用户帐号">
				</p>
				<p>
					<input type="password" name="password" placeholder="密码">
				</p>
				<p>
					<input type="text" name="rand" placeholder="验证码">
				</p>
				<p>
					<img alt="验证码" name="randImage" id="randImage" src="image.jsp"
						width="60" height="20" border="1" align="absmiddle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="javascript:loadImage();">看不清点我</a>
				</p>
				<p>
					<input type="submit" value="登 陆" onclick="return check()">
				</p>
				<p>
					<input type="button" value="注 册"
						onclick="javascrtpt:window.location.href='register.jsp'">
				</p>
			</div>
		</div>
	</form>
</body>
</html>