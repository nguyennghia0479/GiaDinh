<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<title>Khoa Form</title>
	<link rel="icon" type="image/jpeg" href='<spring:url value="/resources/images/giadinh.png"></spring:url>'>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href='<spring:url value="/resources/css/bootstrap.min.css"></spring:url>' id="bootstrap-css">
    <link rel="stylesheet" href='<spring:url value="/resources/fontawesome/css/all.min.css"></spring:url>'>
    <link rel="stylesheet" href='<spring:url value="/resources/css/formAdmin.css"></spring:url>'>
    <script src='<spring:url value="/resources/js/jquery-3.3.1.min.js"></spring:url>'></script>
    <script src='<spring:url value="/resources/js/bootstrap.min.js"></spring:url>'></script>
</head>
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
	                    	<c:when test="${remove}">
	                    		<form:input path="maKhoa" type="text" class="form-control" placeholder="Nhập mã khoa" disabled="${remove}"/>
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
						<c:when test="${remove}">
							<div class="form-group required col-lg-12">
			                    <label for="tenKhoa"><b>Tên khoa</b></label>
			                    <form:input path="tenKhoa" type="text" name="tenKhoa" class="form-control" placeholder="Nhập tên khoa" readonly="${remove}"/>
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
	                
	                <c:if test="${remove}">
	                	<div class="form-group col-lg-12">
		                	<div class="alert alert-danger text-center text-uppercase font-weight-bold" role="alert">
								Bạn có chắc chắn muốn xóa ?
							</div>
		                </div>
	                </c:if>
	                <c:choose>
	                	<c:when test="${success}">
							<div class="form-group col-lg-12">
			                	<div class="alert alert-success text-center" role="alert">
									<c:if test="${insert}">
										Thêm khoa <b>${tenKhoa}</b> thành công
									</c:if>
									<c:if test="${update}">
										Cập nhật khoa <b>${tenKhoa}</b> thành công
									</c:if>
									<c:if test="${delete}">
										Xóa khoa <b>${tenKhoa}</b> thành công
									</c:if>
									<button onclick="location.href='khoa'" class="close" data-dismiss="alert" aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
			                </div>
						</c:when>
						<c:otherwise>
							 <div class="form-group col-lg-12 text-center">
			                    <div class="btn-group" role="group" aria-label="Basic example">
			                    	<c:choose>
			                    		<c:when test="${edit}">
			                    			 <button type="submit" class="btn btn-outline-success"><i class="fas fa-check-square"></i> Cập nhật</button>
			                    		</c:when>
			                    		<c:when test="${remove}">
			                    			 <button type="submit" class="btn btn-outline-success"><i class="fas fa-check-square"></i> Xóa</button>
			                    		</c:when>
			                    		<c:otherwise>
			                    			 <button type="submit" class="btn btn-outline-success"><i class="fas fa-check-square"></i> Lưu</button>
			                    		</c:otherwise>
			                    	</c:choose>
			                        <button type="button" onclick="location.href='khoa'" class="btn btn-outline-secondary"><i class="fas fa-times"></i> Hủy</button>
			                    </div>
			                </div>
						</c:otherwise>
	                </c:choose>
	            </form:form>
            </div>
        </div>
    </div>
</body>
</html>