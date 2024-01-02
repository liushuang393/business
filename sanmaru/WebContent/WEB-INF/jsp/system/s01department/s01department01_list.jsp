<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resourceslocales/bootstrap-datepicker.ja.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap-datepicker.min.css" />
<title>三圓株式会社管理システム</title>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$("#addBtn")
								.click(
										function() {
											$("#submitForm")
													.attr("action",
															"${pageContext.request.contextPath}/department/addInit.do")
													.submit();
										});

					});
</script>
</head>
<body>
	<div class="container">
		<form:form id="submitForm" name="submitForm" method="post"
			commandName="departmentForm" class="form-inline">
			<div class="row clearfix">
				<form:errors path="*" cssStyle="color:red" />
			</div>
			<div class="row clearfix">
				<div class="col-md-12 column">
					<div class="list-group">
						<a href="#" class="list-group-item active"><b>部署リスト</b></a>
					</div>
				</div>
			</div>

			<div class="row clearfix">
				<div class="col-md-10 column"></div>
				<div class="col-md-2 column">
					<button type="button" class="btn btn-default btn-primary btn-block"
						id="addBtn">新規</button>
				</div>
			</div>
			<br/>
			<div class="row clearfix">
				<div class="col-md-12 column">
					<table class="table table-hover table-condensed table-bordered">
						<thead>
							<tr class="info">
								<th class="text-center">No.</th>
								<th class="text-center">部署ID</th>
								<th class="text-center">部署名</th>
								<th class="text-center">責任者</th>
							</tr>
						</thead>
						<tbody>
                        <c:forEach var="department" items="${departmentForm.departmentList}" varStatus="status">
						  <c:choose>
						  <c:when test="${(status.index+1)%2 == '0'}"><tr class="info"></c:when>
						  <c:otherwise><tr></c:otherwise>
						  </c:choose>
						    <td><a href="${pageContext.request.contextPath}/department/editInit.do?departmentId=${department.departmentId}">${status.index+1}</a></td>
						    <td><c:out value="${department.departmentId}" /></td>
						    <td><c:out value="${department.departmentName}" /></td>
						    <td><c:out value="${department.responsiblePerson}" /></td>
						   </tr>
                        </c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>
