function check() {
	var userid = document.getElementById("userid").value;
	var password = document.getElementById("password").value;
	var passwordag = document.getElementById("passwordag").value;
	var name = document.getElementById("name").value;
	if (userid.length == 0) {
		alert("请输入账号！");
		return false;
	}
	if (password.length == 0) {
		alert("请输入密码！");
		return false;
	}
	if (passwordag.length == 0) {
		alert("请确认密码！");
		return false;
	}
	if (name.length == 0) {
		alert("请输入姓名！");
		return false;
	}
	if (password != passwordag) {
		alert("两次密码输入不一致！");
		return false;
	}

}
