<%@ page contentType = "text/html; charset=UTF-8" %>
<%@ taglib prefix = "s" uri = "/struts-tags" %>

<html>
   <head>   	  
      <title>main page</title>      
   </head>
   
   <body>
   
	   	<form action="edit.jsp" method="get">
	  		帳號 : ${sessionScope.account} <br/>
	  		姓名 : ${sessionScope.name} <br/>	  		
	   		<input type="submit" value="修改"/> 
	   		<input type ="button" onclick="location.href='login.do'" value="登出"></input>
		</form>
		
					
		 
   </body>
   
   <script src="scripts/jquery-3.3.1.min.js"></script>
   <script src="scripts/bootstrap.js"></script>
   
    
</html>