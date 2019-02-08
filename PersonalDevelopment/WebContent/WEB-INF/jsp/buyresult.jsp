<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>>購入完了</title>
<jsp:include page="head.html" />
</head>
<body>
<jsp:include page="header.jsp" />
<div id="container">

	<div id="main" class="content font">
		<div class="container">
			<h5 class="center fs">購入が完了しました</h5>
				<ul class="inline">
					<li>
						<a href = "Index">
							<input class="center-align content-botton font md1 center"
								type="submit" name="indexButton" value="TOPページへ"></a></li>
					<li>　　　　　　</li>
					<li>
						<a href = "UserData">
							<input class="center-align content-botton font md1 center"
								type="submit" name="userdataButton" value="ユーザ情報へ"></a></li>
				</ul>
		<div class="center">
			<h5 class="center fs">購入詳細</h5>
		</div>
		<!--  購入 -->
			<div class="section"></div>
				<div class="card-buyhistory">
					<div class="card-content md1">
						<table border="1" cellpadding="5" class="center">
							<thead>
								<tr>
									<th>購入日時</th>
									<th>配送方法</th>
									<th>合計金額</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>${resultBDB.formatDate}</td>
									<td>${resultBDB.deliveryMethodName}</td>
									<td>${resultBDB.totalPrice}円</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
		<!-- 詳細 -->
				<div class="card-buyhistory">
					<div class="card-content md1">
						<table border="1" cellpadding="5" class="center">
							<thead>
								<tr>
									<th>商品名</th>
									<th>個数</th>
									<th>単価</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="buyMDB" items="${buyMDBList}">
									<tr>
										<td>${buyMDB.name}</td>
										<td>${buyMDB.num}</td>
										<td>${buyMDB.price}円</td>
									</tr>
								</c:forEach>
								<c:forEach var="buyIDB" items="${buyIDBList}">
									<tr>
										<td>${buyIDB.itemName}</td>
										<td>${buyIDB.num}</td>
										<td>${buyIDB.itemPrice}円</td>
									</tr>
								</c:forEach>
								<tr>
									<td>${resultBDB.deliveryMethodName}</td>
									<td></td>
									<td>${resultBDB.deliveryMethodPrice}円</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
	</div>

	</div>
	<footer class="site-footer font bottom">
	    <p class="copyright">@1 Soup 3 Veggies</p>
	</footer>
</div>
</body>
</html>