<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>登録完了</title>
<jsp:include page="head.html" />
</head>
<body>
<jsp:include page="header.jsp" />
<div id="container">

	<div id= "main" class = "content font">
		<h5 class="center fs">登録完了</h5>
		<div class="section"></div>
			<div class="card">
				<div class="card-content font">

					<div class="center md1">ログインID<br>
						<input value="${udb.loginId}" name="login_id" type="text" readonly>
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

					<p class="center">上記内容で登録しました。</p>
					<p class="center">
						<a href = "Login">
							<button class="btn font" type="submit" name="confirm_button" value="regist">ログイン画面へ
							</button>
						</a>
					</p>
				</div>
			</div>
	</div>

	<footer class="site-footer font">
	    <p class="copyright">@1 Soup 3 Veggies</p>
	</footer>
</div>
</body>
</html>