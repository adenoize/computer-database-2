<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
<title><spring:message code="addComputer"/></title>
<jsp:include page="head.jsp" flush="true" />
</head>
<body>
	<jsp:include page="header.jsp" flush="true" />
	<section id="main">
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-xs-offset-2 box">
				<h1><spring:message code="addComputer"/></h1>
				<c:if test="${error!=null}">
					<div class="alert alert-danger">
						<c:out value="${error}"></c:out>
					</div>
				</c:if>
				<form:form modelAttribute="computer" method="POST">
					<fieldset>
						
						<div class="form-group">
							<label for="computerName"><spring:message code="computerName"/></label><form:input path="name"
								type="text" class="form-control" id="name"
								placeholder="Computer name"/> 
							<form:errors path="name" />
						</div>                        
                    
						<div class="form-group">
							<label for="introduced"><spring:message code="introduced"/></label> <form:input path="introduced"
								type="date" class="form-control" id="introduced"
								/>
								<form:errors path="" />
						</div>
						<div class="form-group">
							<label for="discontinued"><spring:message code="discontinued"/></label> <form:input path="discontinued"
								type="date" class="form-control" id="discontinued"
								/>
						</div>
						<div class="form-group">
							<form:label path="company"><spring:message code="company"/></form:label> 
							<form:select path="company"
								class="form-control" id="companyId">						
								<form:option value="0"><spring:message code="select"/></form:option>
            					<form:options items="${companies}" itemValue="id" itemLabel="name"/>
							</form:select>
						</div>
					</fieldset>
					<div class="actions pull-right">
						<button type="submit" class="btn btn-primary"><spring:message code="add"/></button>
						<spring:message code="or"/> <a href="${contextPath}/dashboard" class="btn btn-default"><spring:message code="cancel"/></a>
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