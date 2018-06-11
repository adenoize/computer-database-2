<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<title>Computer Database</title>
<jsp:include page="head.jsp" flush="true" />
</head>
<body>
	<jsp:include page="header.jsp" flush="true" />

	<section id="main">
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-xs-offset-2 box">
				
				<h1>Login</h1>

				<c:if test="${param.error != null}">
					<div class="alert alert-danger">
						Invalid username and password. <br/>
					</div>
				</c:if>
				<c:if test="${param.logout != null}">
					<div class="alert alert-success">
						You have been logged out. <br/>
					</div>
				</c:if>
				<form action="login" method="POST">
					<fieldset>
						<div class="form-group">
							 <label for="username">username</label>
                             <input type="text" class="form-control" id="username" name="username" placeholder="Enter Username" required/>
						</div>
						<div class="form-group">
							<label for="password">password</label> 
                            <input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" required/>
						</div>
					</fieldset>
					<div class="actions pull-right">
						<input type="submit" name="submit" value="Submit"
							class="btn btn-primary">
					</div>
				</form>
			</div>
		</div>
	</div>
	</section>
</body>
</html>