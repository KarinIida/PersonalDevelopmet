<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>購入履歴詳細</title>
<jsp:include page="head.html" />
</head>
<body>
<jsp:include page="header.jsp" />
<div id="container">

	<div id="main">
		<div class="content font center">
			<div class="container">
				<h4 class="center fs">購入詳細</h4>

				<!--  購入 -->
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
					<div class="card-content">
						<table border="1" cellpadding="5" class="center">
							<thead>
								<tr>
									<th>商品名</th>
									<th>単価</th>
									<th>個数</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var ="BM" items ="${buyMDB}">
									<tr>
										<td>${BM.name}</td>
										<td>${BM.price}円</td>
										<td>${BM.num}</td>
									</tr>
								</c:forEach>
								<c:forEach var ="BI" items ="${buyIDB}">
									<tr>
										<td>${BI.itemName}</td>
										<td>${BI.itemPrice}円</td>
										<td>${BI.num}</td>
									</tr>
								</c:forEach>
									<tr>
										<td>${resultBDB.deliveryMethodName}</td>
										<td>${resultBDB.deliveryMethodPrice}円</td>
										<td>1</td>
									</tr>
							</tbody>
						</table>
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