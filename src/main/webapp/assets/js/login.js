function check() {
	var userid = document.getElementById("userid").value;
	var password = document.getElementById("password").value;
	var rand = document.getElementById("rand").value;
	if (userid.length == 0) {
		alert("请输入账号！");
		return false;
	}
	if (password.length == 0) {
		alert("请输入密码！");
		return false;
	}
	if (rand.length == 0) {
		alert("请输入验证码！");
		return false;
	}
}
function loadImage() {
	document.getElementById("randImage").src = "image.jsp?" + Math.random();
}