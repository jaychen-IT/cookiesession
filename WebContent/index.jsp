<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<% System.out.println("Hello,JAVA");
 String path= request.getContextPath();
 String allpath= request.getRequestURI();
 StringBuffer allpath2= request.getRequestURL();
 out.print(path);
 out.print(allpath);
 out.print(allpath2);
%>
<% response.getWriter().write("你好！"); %>>
<%! int i=100; %>
<%= "Goodbye,Gemtek!" %>
<h1>Hello,JSP!</h1>

</body>
</html>