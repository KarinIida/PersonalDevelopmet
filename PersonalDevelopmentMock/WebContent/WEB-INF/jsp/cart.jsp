<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>カート確認</title>
<jsp:include page="head.html" />
</head>
<body>
<jsp:include page="header.jsp" />
<div id="container">

	<div id="main" class="content font">
		<h5 class="center fs">買い物かご</h5>
		<div class = "center">${cartActionMessage}</div>

		<div class = "section">
			<form action="ItemDelete" method="POST">
				<ul class = "inline md1">
					<li><button type="submit" name="action" class = "center-align content-botton font md1 center">削除
						</button></li>
					<li>　　　　　　　　</li>
					<li><a href = "Buy">
						<button class = "center-align content-botton font md1 center" type="button">レジに進む</button></a></li>
				</ul>

  				<div class="boxA center">
					<div class="box1 md1">
						<c:forEach var="meal" items="${cart}" varStatus="status">
						<br>
						<br>
						<section  class="card">
							<div class="card-image">
 								<a href="Meal?meal_id=${meal.id}">
 								<img src ="images/${meal.fileName}" width="350" height="220"> </a>
							</div>
							<div class="card-content">
							<div class="card-title">${meal.name}</div>
								<p>${meal.price}円</p>
								<p><input type="checkbox" id="${status.index}" name="delete_meal_id_list" value="${meal.id}"/>
									<label for="${status.index}">削除</label>
								</p>
							</div>
  						</section>
						<c:if test="${(status.index+1) % 4 == 0 }">
						</c:if>
						</c:forEach>
					</div>
  	  				<div class="box1 md1">
						<c:forEach var="mdb" items="${itemCart}" varStatus="status">
						<br>
						<br>
						<section  class="card">
							<div class="card-image">
 								<a href="Item?meal_id=${mdb.id}">
 								<img src ="images/${mdb.fileName}" width="350" height="220"></a>
							</div>
							<div class="card-content">
							<div class="card-title">「${mdb.name}」<br>の材料</div>
							<p>（食材名）${mdb.itemName}</p>
							<p>（一食分×数字）${mdb.itemNum}</p>
							<p>（合計金額）${mdb.itemPrice}円</p>
							<p><input type="checkbox" id="${status.index}" name="delete_item_id_list" value="${mdb.itemId}"/>
								<label for="${status.index}">削除</label></p>
							</div>
  						</section>
						<c:if test="${(status.index+1) % 4 == 0 }">
						</c:if>
						</c:forEach>
					</div>
				</div>
			</form>
		</div>
	</div>

	<footer class="site-footer font bottom">
	    <p class="copyright">@1 Soup 3 Veggies</p>
	</footer>
</div>
</body>
</html>