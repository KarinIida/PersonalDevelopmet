<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>ユーザ情報更新完了</title>
<jsp:include page="head.html" />
</head>
<body>
<jsp:include page="header.jsp" />
<div id="container">

	<div id="main" class="content font center">
		<h5 class="center font fs">更新完了</h5>

		<div class="section"></div>
			<div class="card">
				<div class="card-content md1">

					<div class="center md1 font">ログインID<br>
						<input type="text" value="${udb.loginId}" readonly>
					</div>
					<div class="center md1 font">名前<br>
						<input type="text" value="${udb.name}" readonly>
					</div>
					<div class="center md1 font">メールアドレス<br>
						<input type="text" value="${udb.email}" readonly>
					</div>
					<div class="center md1 font">電話番号<br>
						<input type="text" value="${udb.address}" readonly>
					</div>
					<div class="center md1 font">住所<br>
						<input type="text" value="${udb.tel}" readonly>
					</div>
					<div class="center md1 font">生年月日<br>
						<input type="text" value="${udb.getSBirthday()}" readonly>
					</div>
						<p class="center">上記内容で更新しました</p>
					<div class="center md1">
						<a href="UserData"><button class="btn font" type="submit" name="action">ユーザー情報へ</button></a>
					</div>
				</div>
			</div>
	</div>

	<footer class="site-footer font">
	    <p class="copyright">@1 Soup 3 Veggies</p>
	</footer>
</div>
</body>