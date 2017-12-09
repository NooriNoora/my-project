<%@page language="java" contentType="text/html; charset=ISO-8859-1"
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
<script type="text/javascript">
	function Validate() {
		var password = document.getElementById("password").value;
		var confirmpassword = document.getElementById("confirmpassword").value;
		if (password != confirmpassword) {
			alert("Passwords do not match.");
			return false;
		}
		return true;
	}
</script>
</head>


<div class="container">
	<div class="row">
		<div class="col-md-6">
			<c:set value="${pageContext.request.contextPath }" var="context"></c:set>
			<spring-form:form class="form-horizontal" action="${context}/add"
				method="POST" modelAttribute="User">
				<fieldset>
					<div id="legend">
						<legend>Register</legend>
					</div>
					<div class="control-group">


						<spring-form:label class="control-label" path="username"
							for="username">username</spring-form:label>

						<div class="controls">
							<spring-form:input path="username" class="form-control input-lg"
								id="username" type="text"
								placeholder="username can contain any letters or numbers, without spaces" />
							<spring-form:errors path="username" id="message" />
							<span></span>
						</div>
					</div>

					<div class="control-group">



						<spring-form:label class="control-label" path="email" for="email">E-mail</spring-form:label>

						<div class="controls">
							<spring-form:input path="email" class="form-control input-lg"
								id="email" type="email" placeholder=""></spring-form:input>
							<p class="help-block">Please provide your E-mail</p>

						</div>
					</div>

					<div class="control-group">



						<spring-form:label class="control-label" path="password"
							for="password">Password</spring-form:label>

						<div class="controls">
							<spring-form:input path="password" class="form-control input-lg"
								id="password" type="password" placeholder=""></spring-form:input>
							<p class="help-block">Password should be at least 6
								characters</p>
						</div>
					</div>

					<div class="control-group">
						<spring-form:label class="control-label" path="confirmpassword"
							for="confirmpassword">Password (Confirm)</spring-form:label>
						<div class="controls">
							<spring-form:input name="confirmpassword"
								class="form-control input-lg" path="confirmpassword"
								id="confirmpassword" type="password" placeholder=""></spring-form:input>
							<p class="help-block">Please confirm password</p>
						</div>
					</div>

					<div class="control-group">
						<!-- Button -->
						<div class="controls">
							<button class="btn btn-success" onclick="Validate()">Register</button>
							
							</div>
					</div>
				</fieldset>
			</spring-form:form>

		</div>
	</div>
</div>
</html>