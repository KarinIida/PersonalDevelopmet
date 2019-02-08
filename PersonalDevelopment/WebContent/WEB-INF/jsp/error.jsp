<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>システムエラー</title>
<jsp:include page="head.html" />
</head>
<body>
<jsp:include page="header.jsp" />
<div id="container">

	<div id="main" class="sample6Wrap content font">
		<div class="sample6 center">
			<h4 class="red-text">システムエラーが発生しました</h4>

			<p class="center">
				<a href = "Index">
					<input type = "submit" name = "TOPページへ" value = "TOPページへ" class ="font"></a>
			</p>
		</div>
	</div>

	<footer class="site-footer font">
	    <p class="copyright">@1 Soup 3 Veggies</p>
	</footer>
</div>
</body>
</html>