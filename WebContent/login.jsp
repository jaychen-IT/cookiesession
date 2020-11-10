<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>login</title>
</head>
<script type="text/javascript">
window.onload=function()
{
	var img=document.getElementById("img");
	img.onclick=function()
	{
		var date=new Date().getTime();
		img.src="/cookiesession/checkCodeServlet?"+date;
		}
	
}
</script>
<style>
  div {
	color: red;
}
</style>
<body>
<form action="/cookiesession/loginServlet" method="post">
	<table>
	  <tr>
	     <td>用戶名</td>
	     <td><input type="text" name="username"></td>
	   </tr>
	   <tr>  
	     <td>密碼</td>
	     <td><input type="text" name="password"></td>
	   </tr>
	   <tr>   
	     <td>驗證碼</td>
	     <td><input type="text" name="checkCode"></td>
	   </tr>
	   <tr>    
	     <td colspan="2"><img id="img" src="/cookiesession/checkCodeServlet"></td>
	   </tr>
	   <tr>    
	     <td colspan="2"><input type="submit" value="登錄"></td>
	  </tr>
	
	</table>
</form>	
	<div><%= request.getAttribute("cc_error")==null?"":request.getAttribute("cc_error") %></div>
	<div><%= request.getAttribute("login_error")==null?"":request.getAttribute("login_error") %></div>

</body>
</html>