<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

			<spring-form:form class="form-horizontal"
				action="${context}/uploadfile" method="POST" modelAttribute="product"
				 enctype="multipart/form-data">



				<fieldset>
					<div id="legend">
						<legend>Add</legend>
					</div>
					<div class="control-group">
						<spring-form:label class="control-label" path="file"
							for="productname">upload the image</spring-form:label>
						<div class="controls">
							<spring-form:input class="form-control input-lg" path="file"
								name="file" type="file"/>
						</div>
					</div>
					<div class="control-group">
						<!-- Button -->
						<div class="controls">
							<button class="btn btn-success" type="submit">Add</button>
						</div>
					</div>
					
				</fieldset>

			</spring-form:form>
		</div>
	</div>
</div>