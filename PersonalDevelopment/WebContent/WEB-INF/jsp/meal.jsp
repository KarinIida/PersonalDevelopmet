<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>商品詳細</title>
<jsp:include page="head.html" />
</head>
<body>
<jsp:include page="header.jsp" />
<div id="container">

	<div id="main" class="content font">
		<div class="section">
			<h5 class="center fs">商品詳細</h5>
			<img src="images/${meal.fileName}" width="800" height="500" class = "md1">

			<div class="boxA center">
				<div class="box1 md1">
					<section class="card">
						<h1 class="card-title">${meal.name}</h1>
						<form action="MealAdd" method="POST">
							<label>${meal.name}</label>
							<div class="card-text md1">${meal.price}円</div>
							<div class="center-align">
								<button type="submit" class="center-align content-botton font md1 center"
									name = "meal_id" value="${meal.id}">カートに追加</button>
							</div>
						</form>
					</section>
				</div>
				<div class="box1 md1">
					<section class="card">
						<h1 class="card-title">「${meal.name}」<br>の材料</h1>
							<form action="Item" method="POST">
								<c:forEach var ="mdb" items = "${mdbList}">
									<div class="card-text md1">${mdb.itemName}</div>
								</c:forEach>
  								<div class="center-align">
									<button class="center-align content-botton font md1 center" type="submit"
										value = "${meal.id}" name ="meal_id">詳細</button>
								</div>
							</form>
					</section>
				</div>
			</div>
		</div>
	</div>

	<footer class="site-footer font">
	    <p class="copyright">@1 Soup 3 Veggies</p>
	</footer>
</div>
</body>
</html>
