<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>購入確認</title>
<jsp:include page="head.html" />
</head>
<body>
<jsp:include page="header.jsp" />
<div id="container">

	<div id="main" class="content font">
		<div class="container">
			<h5 class="center fs">購入</h5>

			<div class="section"></div>
				<div class="card-buyhistory">
					<div class="card-content md1">
						<table border="1" cellpadding="5" class="center">
							<thead>
								<tr>
									<th>商品名</th>
									<th>個数</th>
									<th>小計</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="cartInItem" items="${cart}" >
									<tr>
										<td>${cartInItem.name}</td>
										<td>${cartInItem.num}</td>
										<td>${cartInItem.price}円</td>
									</tr>
								</c:forEach>
								<c:forEach var="cartInItem" items="${itemCart}" >
									<tr>
										<td>${cartInItem.itemName}</td>
										<td>${cartInItem.num}</td>
										<td>${cartInItem.itemPrice}円</td>
									</tr>
								</c:forEach>
									<tr>
										<td>${bdb.deliveryMethodName}</td>
										<td></td>
										<td>${bdb.deliveryMethodPrice}円</td>
									</tr>
									<tr>
										<td>合計</td>
										<td></td>
										<td>${bdb.totalPrice}円</td>
									</tr>
								</tbody>
							</table>
							<br>
							<div class="center md1">
								<form action="BuyResult" method="post">
									<button class="center-align content-botton font md1 center" type="submit">購入</button>
								</form>
							</div>
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