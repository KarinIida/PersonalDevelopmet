<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>ユーザー情報/更新確認</title>
<jsp:include page="head.html" />
</head>
<body>
<jsp:include page="header.jsp" />
<div id="container">

	<div id= "main" class="content font center">
		<h5 class="center font fs">入力内容確認</h5>

			<div class="card">
				<div class="card-content md1 font">
				<form action="UserDataUpdateResult" method="POST">

					<div class="center md1">ログインID<br>
						<input type="text" name="login_id_update" value="${udb.loginId}" readonly>
					</div>
					<div class="center md1">名前<br>
						<input type="text" name="user_name_update" value="${udb.name}" readonly>
					</div>
					<div class="center md1">メールアドレス<br>
						<input type="text" name="email_update" value="${udb.email}" readonly>
					</div>
					<div class="center md1">住所<br>
						<input type="text" name="user_address_update" value="${udb.address}" readonly>
					</div>
					<div class="center md1">電話番号<br>
						<input type="text" name="tel_update" value="${udb.tel}" readonly>
					</div>
					<div class="center md1">生年月日<br>
						<input type="text" name="sBirthday_update" value="${udb.getSBirthday()}" readonly>
					</div>

					<p class="center">上記内容で更新して<br>
						よろしいでしょうか?</p>

					<div class="center md1">
						<button class="btn font" type="submit" name="confirmButton" value="cancel">戻る</button>

					<div class="center md1">
						<button class="btn font" type="submit" name="confirmButton" value="update">登録</button>
					</div>
					</div>

				</form>
				</div>
			</div>
	</div>

	<footer class="site-footer font">
	    <p class="copyright">@1 Soup 3 Veggies</p>
	</footer>
</div>
</body>
</html>