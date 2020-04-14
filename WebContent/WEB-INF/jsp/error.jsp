<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String addItemSys = (String) request.getAttribute("addItemSys") ;
String addExpenceSys = (String) request.getAttribute("addExpenceSys") ;
String addBudgetSys = (String) request.getAttribute("addBudgetSys") ;
String updateBudgetSys = (String) request.getAttribute("updateBudgetSys") ;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>家計簿アプリケーション</title>
</head>
<body>
	<% if(addItemSys != null) { %>
		<h1>errorがおきてんで！？</h1>
	<% } else if(addExpenceSys != null) { %>
		<h1>すでに存在する費目です</h1>
	<% } else if (addBudgetSys != null) { %>
		<h1>error</h1>
	<% } else if (updateBudgetSys != null) { %>
		<h1>errorがおきてんで！？</h1>
	<% } %>
</body>
</html>