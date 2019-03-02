<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>


<head>
<title>登入頁面</title>

<script src="scripts/jquery-3.3.1.min.js"></script>
<script src="scripts/bootstrap.js"></script>

<script>

	$(document).ready(function() {
		var msg = "${requestScope.msg}";
		if (!(msg === "")) {			
			alert(msg);
			$('body').append(msg);
		}
	});

	function check_login() {
		var account = $("#account").val();
		console.log("account:" + account);
		var re = new RegExp("^[A-Za-z]{4,30}$");
		console.log("test:" + re.test(account));
		$("#login").submit();
		
		/*
		if(re.test(account)) {
			$("#login").submit();
		}
		lse {
			alert("帳號格式錯誤");
		}
		 */
	}
	
</script>

</head>

<body>

	<form id="login" action="login.do" method="post">
		帳號 : <input id="account" type="text" name="account" /> <br /> 
		密碼 : <input id="password" type="password" name="password" /> <br /> 
		<input type="button" onclick="check_login()" value="登入" /> 
		<input type="button" onclick="location.href='register.jsp'" value="註冊"/>
	</form>
		
	SQL Debug Info:${requestScope.sql} <br/>	

</body>


</html>