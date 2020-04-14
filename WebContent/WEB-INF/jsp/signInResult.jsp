<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="entity.SignIn" %>
 <%
 	String em = (String) request.getAttribute("em");
 	SignIn signInUser = (SignIn) request.getAttribute("signInUser");
 %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>家計簿アプリケーション</title>
<link rel="stylesheet" href="./css/bootstrap.min.css">
<link rel="stylesheet" href="./css/bootstrap.css">
<link rel="stylesheet" href="./css/fixed.css">
</head>
<body>
	<% if(em != null) {%>
	<p><%= em %></p><br>
	<a href="/householdAccountsSystem/SignInServlet" >入力画面へ戻る</a>


	<% } else if(signInUser == null) {%>
	<p>入力されたユーザー名はすでに利用されています</p>
	<a href="/householdAccountsSystem/SignInServlet" >入力画面へ戻る</a>


	<% } else { %>
	<p><%= signInUser.getUserName() %>さん、登録完了しました。</p>
	<h3><a href="/householdAccountsSystem">ログイン画面へ戻る</a></h3>
	<% } %>

<script type="text/javascript" src="/householdAccountsSystem/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="/householdAccountsSystem/js/bootstrap.min.js"></script>
</body>
</html>