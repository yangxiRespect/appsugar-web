<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="<c:url value='/webjars/bootstrap/3.3.6/css/bootstrap.min.css'/>" rel="stylesheet">
	<!-- scripts -->
	<script type="text/javascript" src="<c:url value='/webjars/jquery/2.2.1/jquery.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/webjars/bootstrap/3.3.6/js/bootstrap.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/static/js/formutil.js'/>"></script>
</head>
<body>
	<div class="row">
		<div class="col-md-4 col-md-offset-7">
			<form action="#" method="POST" class="form-inline">
				<input type="text" name="name" class="form-control input-sm"
					value="${param.name}">
				<button type="submit" class="btn btn-primary btn-sm" id="search_btn">
					<spring:message code="search" />
				</button>
			</form>
		</div>
	</div>

	<table class="table">
		<thead>
			<tr>
				<th><spring:message code="id" /></th>
				<th><spring:message code="createdAt" /></th>
				<th><spring:message code="name" /></th>
				<th><spring:message code="loginName" /></th>
			</tr>
		</thead>

		<tbody>
			<c:forEach var="user" items="${page.content}">
				<tr>
					<td>${user.id}</td>
					<td><fmt:formatDate value="${user.createdAt}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${user.name}</td>
					<td>${user.loginName}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<tags:pagination page="${page}" />
</body>

</html>
