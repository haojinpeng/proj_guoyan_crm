$(function() {
	
	$("#d99").click(function(){
		window.open("修改密码.html");
	});
	
	$("#d1").click(function() {
		var zh1 = $("#zh1").val();
		var mzh1 = $("#mzh1").val();
		var zh2 = $("#zh2").val();
		var mzh2 = $("#mzh2").val();
		var name = $("#p1").val();
		var pass = $("#cemail").val();

		if(name == zh1 && pass == mzh1) {
			alert("找回成功 密码：admin1");
			window.open("login.html");
		} else if(name == zh2 && pass == mzh2) {
			alert("找回成功 密码：55555");
			window.open("login.html");
		} else {
			alert("找回密码失败!!!");			
		}

	});

});
