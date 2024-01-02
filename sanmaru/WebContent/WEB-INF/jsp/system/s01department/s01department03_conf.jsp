<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
$(document).ready(function(){

  $("#updatBtn").click(function(){
  // 次へ
    $('#submitForm').attr("action", "${pageContext.request.contextPath}/department/update.do").submit();
  });

    // 戻る
    $("#backbtn").click(function(){
    $('#submitForm').attr("action", "${pageContext.request.contextPath}/department/addInit.do").submit();
  });

});
</script>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="list-group">
                 <a href="#" class="list-group-item active"><b>部署追加・編集</b></a>
            </div>
        </div>
    </div>
 <form:form id="submitForm" name="submitForm" method="post"
            commandName="departmentForm" class="form-horizontal">
            <div class="row clearfix">
                <form:errors path="*" cssStyle="color:red" />
            </div>

    <div class="form-group">
    <div class="col-sm-1">
        <label class="control-label">部署ID:</label>
        </div>
            <div class="col-sm-6">
            <label class="control-label"><c:out value="${departmentForm.departmentId}"/></label>
            </div>
   </div>
   <div class="form-group">
   <div class="col-sm-1">
        <label  class="control-label">部署名:</label>
        </div>
            <div class="col-sm-6">
            <label class="control-label"><c:out value="${departmentForm.departmentName}"/></label>
           </div>
    </div>
    <div class="form-group">
    <div class="col-sm-1">
        <label class="control-label">責任者:</label>
        </div>
        <div class="col-sm-6">
         <label class="control-label"><c:out value="${departmentForm.responsiblePerson}"/></label>
        </div>
   </div>
  <div class="form-group">
  <div class="col-sm-1">
        <label for="establishmentDate" class="control-label">成立日:</label>
        </div>
        <div class="col-sm-6">
                        <label class="control-label">
                        <c:out value="${departmentForm.establishmentDate}"/></label>
        </div>
   </div>
     <div class="col-sm-1">
        <label for="updatetime" class="control-label">更新日:</label>
        </div>
        <div class="col-sm-6">
                        <label class="control-label">

        <fmt:formatDate value="${departmentForm.updatetime}"
            type="both" pattern="yyyy-MM-dd"
                    dateStyle="full" timeStyle="full"/>
        </div>
   </div>
    <br/>
    <div class="row clearfix">
      <div class="col-md-12 column">
        <div class="col-md-4 column">
        </div>
        <div class="col-md-2 column">
        <button type="button" class="btn btn-default btn-primary btn-block" id="updatBtn">更新</button>
        </div>
        <div class="col-md-2 column">
        <button type="button" class="btn btn-default btn-primary btn-block" id="backbtn">戻る</button>
        </div>
        <div class="col-md-6 column">
        </div>
      </div>
    </div>
 </form:form>
</div>

</body>
</html>