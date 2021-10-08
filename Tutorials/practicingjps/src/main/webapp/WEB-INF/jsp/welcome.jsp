<%@page import="com.practicing.practicingjps.funutils.FunUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
	<h1>Passing data to a JSP using request</h1>
	<p>This page receives data through request, it obtains the URI: <b><%= request.getRequestURI() %></b> by using JSP expression</p>
	<h2>Expressions</h2>
	Expressions are defined using &lt;%= Expression &gt; for example <br>&lt;%= new java.util.Date() &gt; = <%= new java.util.Date() %><br>
	They are basically java expressions of one line, you can even make mathematics operations using them <br>
	&lt;%= 2+4 &gt; = <%= 2+4 %>  and it will return its string value/representation.
	<h2>Scriptlets</h2>
	They are multiple line java code that are rendered, they return the out.print("Something") that you put inside them <b>you must avoid them, since they are expensive</b><br>
	They are implemented through &lt;% Whatever %&gt;
	<%
		out.print("Example using loop <br>");
		for(int i=0;i<4;i++){
			out.print(String.format("%d <b>tags</b>", i));
		}
	%>
	<br>In the previous example, the expressions didn't have &lt;br&gt;, here we have another example:<br>
	<%
		for(int i=0;i<4;i++){
			out.print(String.format("%d i member you can also add <b>tags</b><br>", i));
		}
	%>
	<h2>Declarations</h2>
	You can declare functions through &lt;%! Type methodName(parameters){ Your function} %&gt;<br>
	<%!
		String makeItLower(String data){
			return data.toLowerCase();
		}
	%>
	You can call them using <b>expressions</b> <br>Example :<br>
	HI EVERYONE to lower case = <%= makeItLower("HI EVERYONE") %>
	<h2>Calling classes</h2>
	<%= FunUtils.makeFunItLower("ToLower") %>
	<h2>Importing classes</h2>
	You can import things using &lt;%@ page import="package,package 2" %>
	</body>
</html>