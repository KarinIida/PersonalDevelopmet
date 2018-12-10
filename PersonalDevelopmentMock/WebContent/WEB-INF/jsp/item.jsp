<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>食材一覧</title>
<jsp:include page="head.html" />
</head>
<body>
<jsp:include page="header.jsp" />
<div id="container">

	<div id="main" class="content font">
		<c:if test="${validationMessage != null}">
				<P class="red-text">${validationMessage}</P>
		</c:if>
			<table border="1" class="center">
				<thead>
 					<tr>
 						<th></th>
						<th>食材名</th>
						<th>一食分（値段）</th>
						<th>購入量</th>
					</tr>
				</thead>
				<tbody>
					<form action="ItemAdd" method="POST">
						<input type="hidden" value="${meal_id}" name = "meal_id">
  						<c:forEach var ="mdb" items = "${mdbList}">
							<tr>
								<th>
									<input type="checkbox" value="${mdb.itemId}" name ="item_id"></th>
								<td>${mdb.itemName}</td>
								<td>${mdb.itemNum}（${mdb.itemPrice}円）</td>
								<td>${mdb.itemNum}×<input type="text" name="num${mdb.itemId}" size="10"></td>
							</tr>
						</c:forEach>
						<div class="right2">
							<button class="center-align content-botton font md1 center" type="submit" name="action">
								カートに追加</button>
						</div>
					</form>
				</tbody>
			</table>
	</div>

	<footer class="site-footer font">
	    <p class="copyright">@1 Soup 3 Veggies</p>
	</footer>
</div>
</body>
</html>