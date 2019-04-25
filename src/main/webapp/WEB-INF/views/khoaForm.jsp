<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<title>Khoa Form</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href='<spring:url value="/resources/css/bootstrap.min.css"></spring:url>' id="bootstrap-css">
    <script src='<spring:url value="/resources/js/jquery-3.3.1.min.js"></spring:url>'></script>
    <script src='<spring:url value="/resources/js/bootstrap.min.js"></spring:url>'></script>
    <link rel="stylesheet" href='<spring:url value="/resources/fontawesome/css/fontawesome.min.css"></spring:url>'>
    <link rel="stylesheet" href='<spring:url value="/resources/fontawesome/css/solid.min.css"></spring:url>'>
    <link rel="stylesheet" href='<spring:url value="/resources/css/formAdmin.css"></spring:url>'>
<body>
    <div class="container">
        <h2 class="text-center text-uppercase">Khoa Form
        </h2>
        <div class="card">
            <div class="card-header">
                Khoa Form
            </div>
            <div class="card-body">
	            <form:form method="post" modelAttribute="khoa">
	                <div class="form-group required col-lg-12">
	                	<label for="maKhoa"><b>Mã khoa</b></label>
	                    <c:choose>
	                    	<c:when test="${edit}">
	                    		<form:input path="maKhoa" type="text" class="form-control" placeholder="Nhập mã khoa" disabled="${edit}"/>
	                    	</c:when>
	                    	<c:when test="${delete}">
	                    		<form:input path="maKhoa" type="text" class="form-control" placeholder="Nhập mã khoa" disabled="${delete}"/>
	                    	</c:when>
	                    	<c:otherwise>
	                    		<form:input path="maKhoa" type="text" name="maKhoa" class="form-control" placeholder="Nhập mã khoa"/>
	                    		<c:if test="${key == true}">
	                    			<div style="color:red">Mã khoa vừa nhập đã tồn tại</div>
	                    		</c:if>
	                    		<form:errors path="maKhoa" cssStyle="color:red"></form:errors>
	                    	</c:otherwise>
	                    </c:choose>
	                </div>
	
					<c:choose>
						<c:when test="${delete}">
							<div class="form-group required col-lg-12">
			                    <label for="tenKhoa"><b>Tên khoa</b></label>
			                    <form:input path="tenKhoa" type="text" name="tenKhoa" class="form-control" placeholder="Nhập tên khoa" readonly="${delete}"/>
			                </div>
						</c:when>
						<c:otherwise>
							<div class="form-group required col-lg-12">
			                    <label for="tenKhoa"><b>Tên khoa</b></label>
			                    <form:input path="tenKhoa" type="text" name="tenKhoa" class="form-control" placeholder="Nhập tên khoa" />
			                	<form:errors path="tenKhoa" cssStyle="color:red"></form:errors>
			                </div>
						</c:otherwise>
					</c:choose>
	                
	                <c:if test="${delete}">
	                	<div class="form-group col-lg-12">
		                	<div class="alert alert-danger text-center text-uppercase font-weight-bold" role="alert">
								Bạn có chắc chắn muốn xóa ?
							</div>
		                </div>
	                </c:if>
	
	                <div class="form-group col-lg-12 text-center">
	                    <div class="btn-group" role="group" aria-label="Basic example">
	                    	<c:choose>
	                    		<c:when test="${edit}">
	                    			 <button type="submit" class="btn btn-outline-success"><i class="fas fa-check-square"></i> Cập nhật</button>
	                    		</c:when>
	                    		<c:when test="${delete}">
	                    			 <button type="submit" class="btn btn-outline-success"><i class="fas fa-check-square"></i> Xóa</button>
	                    		</c:when>
	                    		<c:otherwise>
	                    			 <button type="submit" class="btn btn-outline-success"><i class="fas fa-check-square"></i> Lưu</button>
	                    		</c:otherwise>
	                    	</c:choose>
	                        <button type="button" onclick="location.href='khoa'" class="btn btn-outline-secondary"><i class="fas fa-times"></i> Hủy</button>
	                    </div>
	                </div>
	            </form:form>
            </div>
        </div>
    </div>
</body>
</html>