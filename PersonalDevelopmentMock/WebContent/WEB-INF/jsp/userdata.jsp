<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>ユーザ情報</title>
<jsp:include page="head.html" />
</head>
<body>
<jsp:include page="header.jsp" />
<div id="container">

	<div id="main">
		<div class="content center font">
			<h5 class="center font fs">ユーザ情報</h5>
		</div>

		<div class="card">
			<div class="card-content font md1">
				<form action="UserDataUpdateConfirm" method="POST">
					<c:if test="${validationMessage != null}">
						<p class="red-text center-align">${validationMessage}</p>
					</c:if>

					<div class="center md1 font">ログインID<br>
						<input type="text" name="login_id" value="${udb.loginId}">
					</div>
					<div class="center md1 font">名前<br>
						<input type="text" name="user_name" value="${udb.name}">
					</div>
					<div class="center md1 font">メールアドレス<br>
						<input type="text" name="email" value="${udb.email}">
					</div>
					<div class="center md1 font">住所<br>
						<input type="text" name="user_address" value="${udb.address}">
					</div>
					<div class="center md1 font">電話番号<br>
						<input type="text" name="tel" value="${udb.tel}">
					</div>
					<div class="center md1 font">生年月日<br>
						<input type="text" name="sBirthday" value="${udb.getSBirthday()}">
					</div>

					<div class="center md1">
						<button class="btn font" type="submit" name="action">更新</button>
					</div>
				</form>
			</div>
		</div>

		<!--  購入履歴 -->
			<div class="card-buyhistory font">
				<div class="card-content">
					<table border="1" cellpadding="5" class="center">
						<thead>
							<tr>
								<th>詳細</th>
								<th>購入日時</th>
								<th>配送方法</th>
								<th>購入金額</th>
							</tr>
						</thead>
							<tbody>
<c:forEach var = "resultBDB" items = "${bdbList}">
								<tr>
									<td><a href="UserBuyHistory?buy_id=${resultBDB.id}">詳しくはこちら</a></td>
									<td>${resultBDB.formatDate}</td>
									<td>${resultBDB.deliveryMethodName}</td>
									<td>${resultBDB.totalPrice}円</td>
								</tr>
</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
	</div>

	<footer class="site-footer font">
	    <p class="copyright">@1 Soup 3 Veggies</p>
	</footer>
</div>
</body>
</html>