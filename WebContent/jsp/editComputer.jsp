<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Edit computer</title>
<jsp:include page="head.jsp" flush="true" />
</head>
<body>
	<jsp:include page="header.jsp" flush="true" />
	<section id="main">
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-xs-offset-2 box">
				<div class="label label-default pull-right">
					id:
					<c:out value="${id}">0</c:out>
				</div>
				<h1>Edit Computer</h1>

				<c:if test="${error!=null}">
					<div class="alert alert-danger">
						<c:out value="${error}"></c:out>
					</div>
				</c:if>
				
				<form action="editComputer?id=${id}" method="POST" name="editComputer">
					<fieldset>
						<div class="form-group">
							<label for="computerName">Computer name</label> <input
								type="text" class="form-control" id="computerName"
								name="computerName" placeholder="Computer name"
								value="${computerName}">
						</div>
						<div class="form-group">
							<label for="introduced">Introduced date</label> <input
								type="date" class="form-control" id="introduced"
								name="introduced" placeholder="Introduced date"
								value="${introduced}">
						</div>
						<div class="form-group">
							<label for="discontinued">Discontinued date</label> <input
								type="date" class="form-control" id="discontinued"
								name="discontinued" placeholder="Discontinued date"
								value="${discontinued}">
						</div>
						<div class="form-group">
							<label for="companyId">Company</label> <select
								class="form-control" id="companyId" name="companyId">
								<option value="0">--</option>
								<c:forEach items="${companies}" var="companiesValue">
									
									<c:if test="${companiesValue.id==companyId}">
										<option selected="selected" value="${companiesValue.id}"><c:out
												value="${companiesValue.name}"></c:out></option>
									</c:if>
									<c:if test="${companiesValue.id!=companyId}">
										<option value="${companiesValue.id}"><c:out
												value="${companiesValue.name}"></c:out></option>
									</c:if>
								</c:forEach>
							</select>
						</div>
					</fieldset>
					<div class="actions pull-right">
						<input type="submit" value="Edit" class="btn btn-primary">
						or <a href="dashboard" class="btn btn-default">Cancel</a>
					</div>
				</form>
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