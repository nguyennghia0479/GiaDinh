<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<title>Đăng nhập</title>
    <link rel="icon" type="image/jpeg" href='<spring:url value="/resources/images/giadinh.png"></spring:url>'>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href='<spring:url value="/resources/css/bootstrap.min.css"></spring:url>'>
    <link rel="stylesheet" href='<spring:url value="/resources/fontawesome/css/all.min.css"></spring:url>'>
    <link rel="stylesheet" href='<spring:url value="/resources/css/login.css"></spring:url>'>
    <script src='<spring:url value="/resources/js/jquery-3.3.1.min.js"></spring:url>'></script>
    <script src='<spring:url value="/resources/js/bootstrap.min.js"></spring:url>'></script>
</head>
<body>
	<div class="container login-container">
		<div class="row">
			<div class="col-sm-12 login-form">
				<h3>Đăng nhập</h3>
				<form:form method="post" modelAttribute="taiKhoan">
					<div class="form-group">
						<form:input path="maTK" type="text" class="form-control" placeholder="Nhập tên tài khoản" name="maTK"/>
						<form:errors path="maTK" cssStyle="color: red"/>
						<c:if test="${errorUsername}"><span style="color :red">${msgUsername}</span></c:if>
					</div>
					<div class="form-group">
						<form:input path="matKhau" type="password" class="form-control" placeholder="********" name="matKhau"/>
						<form:errors path="matKhau" cssStyle="color: red"/>
						<c:if test="${errorPassword}"><span style="color :red">${msgPassword}</span></c:if>
					</div>
					<div class="form-group">
						<input type="submit" class="btnSubmit" value="Đăng nhập" />
					</div>
					<div class="form-group">
						<a href="#" class="ForgetPwd">Quên mật khẩu?</a>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>