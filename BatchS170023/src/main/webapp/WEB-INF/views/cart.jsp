<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
    <%@ include file="header.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cart</title>


<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
	

	<div class = "container">
		<table class="table table-hover">
			<thead>
				<tr>
					<td>Image</td>
					<td>Name</td>
					<td>Cost</td>
					<td>Quantity</td>
					<td>Action</td>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${cart.cartItems}" var="crt">
					<tr>
						<td><img alt="${crt.product.productname}"
							src="${pageContext.request.contextPath}/resources/images/${crt.product.productname}.jpg"
							width="260px" height="300px" class="img-thumbnail"></td>
						<td>${crt.product.productname}</td>
						<td>${crt.subTotal}</td>
						<td>${crt.quantity}</td>
						<td><a href="<c:url value='${pageContext.request.contextPath}/user/delete/cartItem/${crt.id}'/>" class="btn btn-danger">No Need</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="row">
			<div class="col-sm-6">
				<h3>Grand Total : ${cart.grandTotal}</h3>
			</div>
			<div class="col-sm-6">
				<a href="checkout" class="btn btn-primary" style="right: 50px">CHECK OUT</a>	
			</div>
		</div>
		
	</div>
</body>
</html>