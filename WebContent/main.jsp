<%@ page contentType = "text/html; charset=UTF-8" %>
<%@ taglib prefix = "s" uri = "/struts-tags" %>

<html>
   <head>  	  
      <title>main page</title>
      
      <script src="scripts/jquery-3.3.1.min.js"></script>
   	  <script src="scripts/bootstrap.js"></script>
   	
   	<script>  
   	  $(document).ready(function() {
		var msg = "${requestScope.msg}";
		if (!(msg === "")) {			
			alert(msg);
			$('body').append(msg);
		}		
		console.log("Debug Info:${requestScope.sql}");
	  });
   	 </script>
            
   </head>
   
   <body>
   
	   	<form action="edit.jsp" method="get">
	  		帳號 : ${sessionScope.account} <br/>
	  		姓名 : ${sessionScope.name} <br/>	  		
	   		<input type="submit" value="修改"/> 
	   		<input type ="button" onclick="location.href='login.do'" value="登出"></input>
		</form>
		
					
		 
   </body>
   
   
   
    
</html>