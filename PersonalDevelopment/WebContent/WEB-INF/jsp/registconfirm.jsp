<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>ユーザー登録/入力内容確認</title>
<jsp:include page="head.html" />
</head>
<body>
<jsp:include page="header.jsp" />
<div id="container">

	<div id= "main" class = "content font">
		<h5 class = "center fs">入力内容確認</h5>

		<div class="card">
			<div class="card-content font">
				<form action="RegistResult" method="POST">

					<div class="center md1">ログインID<br>
						<input value="${udb.loginId}" name="login_id" type="text" readonly>
					</div>
					<div class="center md1">パスワード<br>
						<input  value="${udb.password}" name="password" type="password" readonly>
					</div>
					<div class="center md1">名前<br>
						<input value="${udb.name}" name="user_name" type="text" readonly>
					</div>
					<div class="center md1">メールアドレス<br>
						<input value="${udb.email}" name="user_email" type="text" readonly>
					</div>
					<div class="center md1">電話番号<br>
						<input value="${udb.tel}" name="user_tel" type="text" readonly>
					</div>
					<div class="center md1">住所<br>
						<input value="${udb.address}" name="user_address" type="text" readonly>
					</div>
					<div class="center md1">生年月日<br>
						<input value="${udb.getSBirthday()}" name="user_birthday" type="text" readonly>
					</div>

					<div class="center md1">上記内容で登録して<br>よろしいでしょうか?</div>

					<div class="center md1">
						<button class="btn font" type="submit" name="confirm_button" value="cancel">戻る</button>
					</div>
					<div class="center md1">
						<button class="btn font" type="submit" name="confirm_button" value="regist">登録</button>
					</div>
				</form>
			</div>
		</div>
<!--		</form>-->
	</div>

	<footer class="site-footer font">
	    <p class="copyright">@1 Soup 3 Veggies</p>
	</footer>
</div>
</body>