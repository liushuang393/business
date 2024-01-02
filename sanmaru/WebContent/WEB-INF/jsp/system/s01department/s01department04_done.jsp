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
    <form id="submitForm" name="submitForm" action="" method="post" class="form-inline">
      <div class="row clearfix">
		<div class="col-md-12 column">
			<div class="list-group">
				 <a href="#" class="list-group-item active"><b>部署更新完了</b></a>
			</div>
		</div>
	  </div>
      <div class="row clearfix">
		<div class="col-md-12 column">
			<h3>
				部署更新完了。
			</h3>
		</div>
	</div>
    </form>
  </div>
</body>
</html>
