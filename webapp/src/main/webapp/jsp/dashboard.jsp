<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="p" uri="/WEB-INF/paginator/Paginator.tld" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
<title><spring:message code="dashboard"/></title>
<jsp:include page="head.jsp" flush="true" />
</head>

<body>
	<jsp:include page="header.jsp" flush="true" />

	<section id="main">
	<div class="container">
		<c:if test="${info!=null}">
			<div class="alert alert-success">
				<c:out value="${info}"></c:out>
			</div>
		</c:if>
		<h1 id="homeTitle"><spring:message code="computersFound" arguments="${numberComputers}"/></h1>
		<div id="actions" class="form-horizontal">
			<div class="pull-left">
				 <form id="searchForm" action="dashboard" method="GET" class="form-inline">
					<input type="text" id="searchbox" name="search"
						 placeholder=<spring:message code="filterByName"/> class="form-control"/>
						<input
						type="submit" id="searchsubmit" value=<spring:message code="filterByName"/>
						class="btn btn-primary" />
				</form> 
			</div>
			<div class="pull-right">
				<a class="btn btn-success" id="addComputer" href="${contextPath}/computer/add"><spring:message code="addComputer"/></a> <a class="btn btn-default" id="editComputer" href="#"
					onclick="$.fn.toggleEditMode();"><spring:message code="edit"/> <i class="fa fa-edit"></i></a>
			</div>
		</div>
	</div>
	
	<form id="deleteForm" action="dashboard" method="POST">
		<input type="hidden" name="selection" value="">
	</form>

	<div class="container" style="margin-top: 10px;">
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<!-- Variable declarations for passing labels as parameters -->
					<!-- Table header for Computer Name -->

					<th class="editMode" style="width: 60px; height: 22px;"><input
						type="checkbox" id="selectall" /> <span
						style="vertical-align: top;"> - <a href="#"
							id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
								class="fa fa-trash-o fa-lg"></i>
						</a>
					</span></th>
					<th><spring:message code="computerName"/></th>
					<th><spring:message code="introduced"/></th>
					<th><spring:message code="discontinued"/></th>
					<th><spring:message code="company"/></th>

				</tr>
			</thead>

			<!-- Browse attribute computers -->
			<tbody id="results">
				<c:forEach items="${page.page}" var="item">
					<tr>
						<td class="editMode"><input type="checkbox" name="cb"
							class="cb" value="${item.id}"></td>
						<td><a href="${contextPath}/computer/edit?id=${item.id}" onclick=""><c:out value="${item.name}"/></a></td>
						<td><c:out value="${item.introduced}"/></td>
						<td><c:out value="${item.discontinued}"/></td>
						<td><c:out value="${item.company}"/></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	</section>

	<footer class="navbar-fixed-bottom">
	<div class="container text-center">
		
		<c:if test="${search==null}">
			<c:url var="searchUri" value="/dashboard?page=##" />
		</c:if>
		<c:if test="${search!=null}">
			<c:url var="searchUri" value="/dashboard?search=${search}&page=##" />
		</c:if>
		
		<p:display maxLinks="5" currPage="${currPage}" totalPages="${totalPages}" uri="${searchUri}" />

		<div class="btn-group btn-group-sm pull-right" role="group">
			<button type="button" class="btn btn-default">10</button>
			<button type="button" class="btn btn-default">50</button>
			<button type="button" class="btn btn-default">100</button>
		</div>
	</div>
	</footer>

	<jsp:include page="script.jsp" flush="true" />
	<script src="${contextPath}/js/dashboard.js"></script>

</body>
</html>