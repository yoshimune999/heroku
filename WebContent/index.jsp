<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>家計簿アプリケーション</title>
<link rel="stylesheet" href="./css/bootstrap.min.css">
<link rel="stylesheet" href="./css/bootstrap.css">
<link rel="stylesheet" href="./css/fixed.css">
<link rel="stylesheet" href="/householdAccountsSystem/css/login_style.css">
<script type="text/javascript">
	function check() {
		if (loginForm.name.value == ""){
			alert("ユーザー名を入力してください");
			return false;
		} else if (loginForm.pass.value == ""){
			alert("パスワードを入力してください");
			return false;
		} else {
			return true;
		}
	}
</script>
</head>

<body>

	<div class="landing">
			<div class="home-wrap">
				<div class="home-inner">

				</div>
			</div>
	</div>

	<div class="caption text-center">
	<h1 class="pb-5">ACCOUNT BOOK</h1>
	<div class="text">
	<div class="boxline">
		<h3 class="text-center">ログイン</h3>
		<form  action="/householdAccountsSystem/LoginServlet" method="post" name="loginForm">
			NAME:<br>
			<input type="text" class="form-control" name="name" placeholder="user"><br>
			PASS:<br>
			<input type="password" class="form-control" name="pass"  placeholder="password"><br>
			<input type="submit" class="btn" value="ログイン" onclick="return check()">
		</form>
	</div>
			<p>未登録の場合は<a href="/householdAccountsSystem/SignInServlet">サインアップ</a>してください</p>
	</div>
	</div>

<script type="text/javascript" src="/householdAccountsSystem/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="/householdAccountsSystem/js/bootstrap.min.js"></script>
</body>
</html>