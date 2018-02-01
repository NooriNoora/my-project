<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="spring-form"
	uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>


<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>


<div class="container">
	<div class="row">
		<div class="col-md-6">
			<c:set value="${pageContext.request.contextPath }" var="context"></c:set>
			<security:authorize access="hasRole('ROLE_ADMIN')">
				<spring-form:form class="form-horizontal"
					action="${context}/addproduct" method="POST"
					modelAttribute="product" enctype="multipart/form-data">

					<spring-form:hidden path="productId"/>

					<fieldset>
						<div id="legend">
							<legend>Add</legend>
						</div>
						<div class="control-group">

							<spring-form:label class="control-label" path="productname"
								for="productname">productname:</spring-form:label>

							<div class="controls">
								<spring-form:input path="productname"
									class="form-control input-lg" id="productname" type="text"
									placeholder="Please enter the productname you need" />
								<spring-form:errors path="productname" id="message" />
							</div>
						</div>

						<div class="control-group">
							<spring-form:label class="control-label" path="category"
								for="category">category</spring-form:label>
							<br />
							<spring-form:select path="category.id">
								<spring-form:option value="0" label="Select" />
								<spring-form:options items="${categories}" itemLabel="name"
									itemValue="id" />
							</spring-form:select>
							<br />
						</div>

						<div class="control-group">
							<spring-form:label class="control-label" path="description"
								for="description">description</spring-form:label>
							<div class="controls">
								<spring-form:input path="description"
									class="form-control input-lg" id="description"
									type="description" placeholder="Please enter the description" />
							</div>
						</div>

						<div class="control-group">
							<spring-form:label class="control-label" path="discount"
								for="discount">discount</spring-form:label>
							<div class="controls">
								<spring-form:input name="discount" class="form-control input-lg"
									path="discount" id="discount" type="discount"
									placeholder="Please enter the discount" />
							</div>
						</div>
						<spring-form:label class="control-label" path="cost" for="cost">cost</spring-form:label>

						<div class="controls">
							<spring-form:input path="cost" class="form-control input-lg"
								id="cost" type="cost" placeholder="Please enter the cost" />

						</div>

						<div class="control-group">
							<!-- Button -->
							<div class="controls">
								<button class="btn btn-success" type="submit">Add</button>
							</div>
						</div>

					</fieldset>
				</spring-form:form>
			</security:authorize>
		</div>

		<table class="table table-hover">
			<tr>
				<th>productId</th>
				<th>file</th>
				<th>productname</th>
				<th>category</th>
				<th>description</th>
				<th>cost</th>
				<th>discount</th>
				<security:authorize access="hasRole('ROLE_ADMIN')">
				<th>edit</th>
				<th>delete</th>
				</security:authorize>
				<security:authorize access="hasRole('ROLE_USER')">
				<th>cart</th>
				</security:authorize>
			</tr>

			<c:forEach items="${products}" var="product">
				<tr>
					<c:url value="/resources/images/${product.productId}.jpg" var="url" />
					<td>${product.productId}</td>
					<td><img src="${url}"
						value="${pageContext.request.contextPath}" width="150"
						align="middle" height="150"></td>
					<td>${product.productname}</td>
					<td>${product.category.name}</td>
					<td>${product.description}</td>
					<td>${product.cost}</td>
					<td>${product.discount}</td>
					
					<security:authorize access="hasRole('ROLE_ADMIN')">
						<td><a
							href="<c:url value='/editproduct/${product.productId}'/>">
								<button type="submit" class="btn btn-info">edit</button>
						</a></td>
						<td><a href="deleteproduct/${product.productId}">
								<button type="submit" class="btn btn-danger">Delete</button>
						</a></td>
					</security:authorize>
					<security:authorize access="hasRole('ROLE_USER')">
						<td><a
							href="<c:url value='/user/addtocart?id=${product.productId}'/>">
								<button type="submit" class="btn btn-info">add to cart</button>
						</a></td>
						</security:authorize>
					
				</tr>
			</c:forEach>
		</table>
	</div>
</div>

</html>