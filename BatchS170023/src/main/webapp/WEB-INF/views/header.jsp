<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@page import="java.security.Principal"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#"><span
					class="glyphicon glyphicon-search"></span>search by category</a>
			</div>
			<ul class="nav navbar-nav">
				
					<li><a href="productform"><span
							class="glyphicon glyphicon-product"></span> product</a></li>
					<security:authorize access="hasRole('ROLE_ADMIN')">
					<li><a href="User"><span class="glyphicon glyphicon-user"></span>
							User</a></li></security:authorize>
							<security:authorize access="hasRole('ROLE_ADMIN')">
					<li><a href="category"><span
							class="glyphicon glyphicon-category"></span> Category</a></li>
				</security:authorize>
				<security:authorize access="hasRole('ROLE_USER')">
					<li><a href="cart"><span class="glyphicon glyphicon-cart"></span>
							Cart</a></li></security:authorize>
					

			</ul>
			<ul class="nav navbar-nav navbar-right">
				<security:authorize access="isAnonymous()">
					<li><a href="${pageContext.request.contextPath}/signin"><span
							class="glyphicon glyphicon-user"></span> Sign Up</a></li></security:authorize>
					<security:authorize access="isAnonymous()">
					<li><a href="${pageContext.request.contextPath}/login"><span
							class="glyphicon glyphicon-log-in"></span> Login</a></li>
				</security:authorize>
			</ul>


			<ul class="nav navbar-nav navbar-right">
				<security:authorize access="isAuthenticated()">
					<li><a href="" style="text-align: center; color: white;">WELCOME
							<%
						String user = new String();
							try {
								Principal principal = request.getUserPrincipal();
								user = principal.getName();
							} catch (Exception e) {
								user = new String(" :) ");
							}
							out.print(user);
					%>
					</a></li>
					<li><a href="logout" style="text-align: center; color: white;"><span
							class="glyphicon glyphicon-log-in"></span>logout</a></li>
				</security:authorize>
			</ul>
		</div>

	</nav>



</body>
</html>