<%@page import="java.security.Principal"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>gf boutique</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style>
h1 {
	text-align: center;
	width: 500px;
	height: 500px;
	text-align: center;
	
	font-size: 80px;
	font-weight: bold;
	color: #660033;
	
}

#cursive{
  font-family:'Monotype Corsiva','Apple Chancery','ITC Zapf Chancery','URW Chancery L',cursive;
}


h1.thick {
	font-weight: bold;
}
</style>
<style>
.nav a {
	color: DarkRed;
	font-size: 40px;
	font-weight: bold;
	padding: 14px 10px;
}

@media ( min-width : 768px) {
	.navbar-nav.navbar-center {
		background-color: transparent;
		position: absolute;
		left: 50%;
		transform: translatex(-50%);
	}
}
</style>
<style>
body {
	background-image: url("resources/images/baby.jpg");
	background-repeat: no-repeat;
	background-size: 1400px 500px;
	background-position: center;
}
</style>
<style>
div.relative {
	position: relative;
	left: 50px;
	top: 50px;
}
</style>

</head>
<c:set value="${pageContext.request.contextPath}" var="context"></c:set>
<body>
<div class="container">
	
	<div class="relative">
		<h1 id="cursive">Jaan Boutique</h1>
	</div>
</div>

	<nav class="navbar navbar">
	<div class="container-fluid">
			<div class="navbar-header">
				<ul class="nav navbar-nav navbar-center">
					<li><a href="landing"><span
							class="glyphicon glyphicon-home"></span> Home</a></li>
				</ul>
			</div>
</div>
	</nav>

	
</body>

</html>