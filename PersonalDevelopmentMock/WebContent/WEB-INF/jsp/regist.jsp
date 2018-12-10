<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>ユーザ登録</title>
<jsp:include page="head.html" />
</head>
<body>
<jsp:include page="header.jsp" />
<div id="container">

	<div id= "main" class="content font center container">
		<h5 class = "center fs">新規登録</h5>
			<c:if test="${validationMessage != null}">
				<P class="red-text">${validationMessage}</P>
			</c:if>
		<div class="card">
			<div class="card-content font">
				<form action="RegistConfirm" method="POST">
					<div class="center md1">ログインID<br>
						<input value="${udb.loginId}" name="login_id" type="text" required>
					</div>
					<div class="center md1">パスワード<br>
						<input  name="password" type="password" required>
					</div>
					<div class="center md1">パスワード（確認用）<br>
						<input name="confirm_password" type="password" required>
					</div>
					<div class="center md1">名前<br>
						<input value="${udb.name}" name="user_name" type="text" required>
					</div>
					<div class="center md1">メールアドレス<br>
						<input value="${udb.email}" name="user_email" type="text" required>
					</div>
					<div class="center md1">電話番号<br>
						<input value="${udb.tel}" name="user_tel" type="text" required>
					</div>
					<div class="center md1">住所<br>
						<input value="${udb.address}" name="user_address" type="text" required>
					</div>
					<div class="center md1">生年月日<br>
						<input value="${udb.getSBirthday()}" name="user_birthday" type="text"
							placeholder="20180101" required>
					</div>
					<p class="center-align center">
						<button class="btn font" type="submit" name="action">確認</button>
					</p>
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