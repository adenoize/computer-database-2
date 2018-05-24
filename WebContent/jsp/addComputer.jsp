<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
<title>Add computer</title>
<jsp:include page="head.jsp" flush="true" />
</head>
<body>
	<jsp:include page="header.jsp" flush="true" />
	<section id="main">
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-xs-offset-2 box">
				<h1>Add Computer</h1>
				<c:if test="${error!=null}">
					<div class="alert alert-danger">
						<c:out value="${error}"></c:out>
					</div>
				</c:if>
				<form:form modelAttribute="computer" method="POST">
					<fieldset>
						
						<div class="form-group">
							<label for="computerName">Computer name</label> <form:input path="name"
								type="text" class="form-control" id="name"
								placeholder="Computer name"/>
						</div>
						<div class="form-group">
							<label for="introduced">Introduced date</label> <form:input path="introduced"
								type="date" class="form-control" id="introduced"
								placeholder="Introduced date"/>
						</div>
						<div class="form-group">
							<label for="discontinued">Discontinued date</label> <form:input path="discontinued"
								type="date" class="form-control" id="discontinued"
								placeholder="Discontinued date"/>
						</div>
						<div class="form-group">
							<form:label path="company">Company</form:label> 
							<form:select path="company"
								class="form-control" id="companyId">						
								<form:option value="0" label="--Please Select"/>
            					<form:options items="${companies}" itemValue="id" itemLabel="name"/>
							</form:select>
						</div>
					</fieldset>
					<div class="actions pull-right">
						<button type="submit" class="btn btn-primary">Add</button>
						or <a href="${contextPath}/dashboard" class="btn btn-default">Cancel</a>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	</section>
	
<jsp:include page="script.jsp" flush="true" />
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/js/form-validation.js"></script>
</body>
</html>