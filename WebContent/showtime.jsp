<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.Date"%>
<%@page import="java.net.URLDecoder"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>itcast</title>
</head>
<body>
<%
response.setContentType("text/html;charset=utf-8");
//1.獲取所有Cookie
Cookie[] cookies=request.getCookies();
boolean flag=false;
//2.遍歷所有數組
if(cookies!=null && cookies.length>0 )
{
	for(Cookie cookie:cookies) {
		//3.獲取Cookie名稱
		String name=cookie.getName();
		//判斷是否為lasttime
		if("lasttime".equals(name))
		{
			flag=true;
			Date date=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
			String value=cookie.getValue();
			System.out.println(value);
			value= URLDecoder.decode(value, "utf-8");
			System.out.println(value);
			%>
			
			<h1>歡迎回來，您上次訪問時間為: <%= value %>!</h1>
			<% 
			//設定訪問時間
			String str_date=sdf.format(date);
			System.out.println(str_date);
			str_date=URLEncoder.encode(str_date, "utf-8");
			System.out.println(str_date);
			cookie.setValue(str_date);
			cookie.setMaxAge(60*60*24*30);
			response.addCookie(cookie);
		}
	}	
}
if(cookies==null || cookies.length==0||flag==false )
{
				Date date=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
	String str_date=sdf.format(date);
	str_date= URLEncoder.encode(str_date, "utf-8");
	Cookie cookie=new Cookie("lasttime",str_date);
	cookie.setMaxAge(60*60*24*30);
	response.addCookie(cookie);
	%>

	<h1>歡迎您首次訪問!</h1>
<% 		
}
%>
<input type="text" name="userid" size="40" maxlength="50">
<input type="password"> 

</body>
</html>