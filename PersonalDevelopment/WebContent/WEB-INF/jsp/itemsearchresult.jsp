<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>検索結果</title>
<jsp:include page="head.html" />
</head>
<body>
<jsp:include page="header.jsp" />
<div id="container">

	<div id="main" class="content font center">
		<h5 class="fs">検索結果</h5>
		<p>検索結果${mealCount}件</p>

		<div class="section">
			<!--   商品情報   -->

			<div class="boxA center">
				<div class="box1 md1">
					<c:forEach var ="meal" items = "${mealList}" varStatus="status">
						<section class="card">
							<div class="card-img">
								<a href ="Meal?meal_id=${meal.id}&page_num=${pageNum}">
									<img src ="images/${meal.fileName}" width="350" height="220">
								</a>
							</div>
							<div class="card-content">
								<h1 class="card-title">${meal.name}</h1>
    							<p class="card-text">${meal.price}円</p>
							</div>
						</section>
						<c:if test="${(status.index + 1) % 4 == 0}">
						</c:if>
					</c:forEach>
				</div>
			</div>
		</div>

		<ul class="inline">
			<!-- １ページ戻るボタン  -->
			<c:choose>
				<c:when test="${pageNum == 1}">
					<li>
						<img src= "images/206.png" class="content1 icon" alt="戻る">
					</li>
				</c:when>
				<c:otherwise>
					<li class="center">
						<a href="ItemSearchResult?search_word=${searchWord}&page_num=${pageNum - 1}">
							<img src= "images/206.png" class="content1 icon" alt="戻る"></a></li>
				</c:otherwise>
			</c:choose>

			<!-- ページインデックス -->
			<c:forEach begin="${(pageNum - 5) > 0 ? pageNum - 5 : 1}"
						end="${(pageNum + 5) > pageMax ? pageMax : pageNum + 5}"
						step="1" varStatus="status">
				<li <c:if test="${pageNum == status.index }"></c:if>>
					<a href="ItemSearchResult?search_word=${searchWord}&page_num=${status.index}">${status.index}
					</a></li>
			</c:forEach>

			<!-- 1ページ送るボタン -->
			<c:choose>
				<c:when test="${pageNum == pageMax || pageMax == 0}">
					<li class="right2">
						<img src= "images/205.png" class="content1 icon" alt="進む">
					</li>
				</c:when>
				<c:otherwise>
					<li>
						<a href="ItemSearchResult?search_word=${searchWord}&page_num=${pageNum + 1}">
							<img src= "images/205.png" class="content1 icon" alt="進む"></a></li>
				</c:otherwise>
			</c:choose>
		</ul>

	</div>
	<footer class="site-footer font bottom">
	    <p class="copyright">@1 Soup 3 Veggies</p>
	</footer>
</div>
</body>
</html>