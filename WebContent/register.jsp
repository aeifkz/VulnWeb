<%@ page contentType = "text/html; charset=UTF-8" %>
<%@ taglib prefix = "s" uri = "/struts-tags" %>

<html>


   <head>
   
      <title>Hello World</title>
      
      <script src="scripts/jquery-3.3.1.min.js"></script>
   	  <script src="scripts/bootstrap.js"></script>
   	  
   	  <script>
   	  
   			$( document ).ready(function() {   				
   				var msg = "${requestScope.msg}";   				
   				if( !(msg === "") ) {
   					console.log(msg);
   					$('body').append(msg);
   				}
   				
   				console.log("Debug Info:${requestScope.sql}");
   				
   			});
   			
   			function check_register() {
   				
   				var account = $("#account").val();			
   				console.log("account:"+account);
   				
   				var re = new RegExp("^[A-Za-z]{5,30}$");
   				console.log("test:"+re.test(account));
   				
   				//$("#register").submit();
   				
   				if(re.test(account)) {
   					$("#register").submit();
   				}
   				else {
   					alert("帳號格式錯誤");
   				}
   				
   				
   			}
   			
   	  </script>
            
   </head>
   
   <body>
      
   	<form id="register" action="register.do" method="get">
   		帳號 : <input id="account" type="text" name="account"  /> <br/>
  		密碼 : <input id="password" type="password" name="password"  /> <br/>
  		暱稱 : <input id="name" type="text" name="name"   /> <br/>
   		<input type="button" onclick="check_register()" value="註冊"/>
	</form>
	
	
		 
   </body>
      
   
    
</html>