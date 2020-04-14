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
<link rel="stylesheet" href="/householdAccountsSystem/css/signIn_style.css">
<script type="text/javascript">
	function check() {
		if (signInForm.name.value.match(/^\w{3,10}$/) == null){
			alert("ユーザー名は３～１０文字の英数字で入力してください");
			return false;
		} else if (signInForm.pass.value.match(/^\w{5,10}$/) == null){
			alert("パスワードは５～１０文字の英数字で入力してください");
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
		<h3 class="text-center" style="270px; width: 270px;">サインアップ</h3>
		<form action="/householdAccountsSystem/SignInServlet" method="post" name="signInForm">
			NAME:<br>
			<input type="text" class="form-control" name="name" placeholder="user"><br>
			PASS:<br>
			<input type="password" class="form-control" name="pass"  placeholder="password"><br>
			<input type="submit" class="btn" value="登録" onclick="return check()">
		</form>
	</div>
		<p><a href="/householdAccountsSystem">ログインはこちらへ</a></p>
	</div>
	</div>

<script type="text/javascript" src="/householdAccountsSystem/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="/householdAccountsSystem/js/bootstrap.min.js"></script>
</body>
</html>