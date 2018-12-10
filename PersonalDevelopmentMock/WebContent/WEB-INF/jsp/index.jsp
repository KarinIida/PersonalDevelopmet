<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>TOPページ</title>
<jsp:include page="head.html" />
</head>
<body>
<jsp:include page="header.jsp" />
<div id="container">

	<div id= "main" class="content font">
		<img src = "images/champagne-2264811_1920.jpg" width = 100% alt = "DiningTable"/>
		<h5 class="center fs md1">オススメ</h5>

	<div class="boxA center">
		<div class="box1 md1">
			<c:forEach var ="meal" items = "${mealList}">
				<section class="card">
					<div class="card-img">
						<a href ="Meal?meal_id=${meal.id}">
							<img src ="images/${meal.fileName}" width="350" height="220"></a>
					</div>
					<div class="card-content">
						<h1 class="card-title">${meal.name}</h1>
    					<p class="card-text">${meal.price}円</p>
					</div>
				</section>
			</c:forEach>
		</div>
	</div>

	</div>
	<footer class="site-footer font">
	    <p class="copyright">@1 Soup 3 Veggies</p>
	</footer>
</div>
</body>
</html>