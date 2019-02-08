<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>ログイン</title>
<jsp:include page="head.html" />
</head>
<body>
<jsp:include page="header.jsp" />
<div id="container">

	<div id="main" class="content font">
			<h5 class="center fs">ログイン画面</h5>
			<div class="card card-content md1">
				<form action="LoginResult" method="POST">
					<div class="center md1">ログインID<br>
						<input name="login_id" type="text" required>
					</div>
					<div class="center md1">パスワード<br>
						<input  name="password" type="password" required>
					</div>
					<p class="center-align center">
					<button class="btn font" type="submit" name="action">ログイン</button>
					</p>
				</form>
			</div>
			<div class = "center"><br>新規登録はこちらからお願いします。<br>
				<a href="Regist">新規登録</a>
			</div>
	</div>

	<footer class="site-footer font bottom">
	    <p class="copyright">@1 Soup 3 Veggies</p>
	</footer>
</div>
</body>
</html>