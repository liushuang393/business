<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>会社管理システム</title>
<link
	href="${pageContext.request.contextPath}/resources/css/authority/login_css.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$("#login_sub")
								.click(
										function() {
											$("#submitForm")
													.attr("action",
															"${pageContext.request.contextPath}/user/login.do")
													.submit();
										});

					});

	function EnterPress(e) {
		var e = e || window.event;
		if (e.keyCode == 13) {
			$("#submitForm").attr("action",
					"${pageContext.request.contextPath}/user/login.do")
					.submit();
		}
	}
	function changeLocale() {
		$("#submitForm").attr("action",
				"${pageContext.request.contextPath}/user/changeLocale.do")
				.submit();
	}
</script>
</head>
<body>
	<div id="login_center">
		<div id="login_area">
			<div id="login_box">
				<div id="login_form">
					<form:form id="submitForm" method="post" commandName="logInUser">
						<form:radiobuttons path="locale" items="${locales}"
							onchange="changeLocale();" />
						<div id="login_tip">
							<span id="login_err" class="sty_txt2"> <form:errors
									path="*" cssStyle="color:red" />
							</span>
						</div>
						<div>
							<table>
								<tr>
									<td align="right"><spring:message code="userId" />：</td>
									<td><input type="text" name="userId" class="username"
										id="userId" value=""></td>
								</tr>
								<tr>
									<td align="right"><spring:message code="password" />：</td>
									<td><input type="password" name="password" class="pwd"
										id="password" onkeypress="EnterPress(event)"
										onkeydown="EnterPress()"></td>
								</tr>
							</table>
						</div>
						<div id="btn_area">
							<input type="button" class="login_btn" id="login_sub"
								value="<spring:message code="bnt.login"/>"> <input
								type="reset" class="login_btn" id="login_ret"
								value="<spring:message code="bnt.rest"/>">
						</div>
					</form:form>
				</div>
			</div>
			<div class="copyright" align="center">
				<h3>
					<b>Copyright &copy; 2019.ABC ㈱ All rights reserved.</b>
				</h3>
			</div>
		</div>

	</div>
</body>
</html>