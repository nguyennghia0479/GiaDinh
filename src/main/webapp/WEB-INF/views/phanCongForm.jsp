<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<title>Mẫu Đơn Phân Công</title>
	<link rel="icon" type="image/jpeg" href='<spring:url value="/resources/images/giadinh.png"></spring:url>'>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href='<spring:url value="/resources/css/bootstrap.min.css"></spring:url>' id="bootstrap-css">
    <link rel="stylesheet" href='<spring:url value="/resources/fontawesome/css/all.min.css"></spring:url>'>
    <link rel="stylesheet" href='<spring:url value="/resources/css/formAdmin.css"></spring:url>'>
    <script src='<spring:url value="/resources/js/jquery-3.3.1.min.js"></spring:url>'></script>
    <script src='<spring:url value="/resources/js/bootstrap.min.js"></spring:url>'></script>
</head>
<body>
    <div class="container">
        <h2 class="text-center text-uppercase">Mẫu Đơn Phân Công
        </h2>
        <div class="card">
            <div class="card-header">
                Phân Công
            </div>
            <div class="card-body">
	            <form:form method="post" modelAttribute="phanCong">
	            	<c:if test="${success}">
	            		<div class="form-group col-lg-12">
		                	<div class="alert alert-success text-center" role="alert">
								<c:choose>
		                			<c:when test="${add}">
										Thêm phân công thành công
		                			</c:when>
		                			<c:when test="${edit}">
		                				Cập nhật phân công thành công
		                			</c:when>
		                			<c:otherwise>
		                				Xóa khoa phân công thành công
		                			</c:otherwise>	
		                		</c:choose>
								<button onclick="location.href='${pageURL}'" class="close" data-dismiss="alert" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
		                </div>
	            	</c:if>
	            	<c:if test="${announceRemove}">
	                	<div class="form-group col-lg-12">
		                	<div class="alert alert-danger text-center text-uppercase font-weight-bold" role="alert">
								Bạn có chắc chắn muốn xóa ?
							</div>
		                </div>
	                </c:if>                
	                <c:if test="${announceReference}">
	                	<div class="form-group col-lg-12">
		                	<div class="alert alert-warning text-center" role="alert">
								Khoa bạn chọn xóa đang được tham chiếu.
								<br>
								Để xóa Khoa, bạn hãy xóa hoặc cập nhật <a href="nganh-hoc" class="link-warning">Ngành Học</a> hoặc 
								<a href="giang-vien" class="link-warning">Giảng Viên</a> tham chiếu tới Khoa khác.
							</div>
		                </div>
	                </c:if>
	            	
	            	<form:input path="maPC" type="hidden"/>
	
					<div class="form-group required col-lg-12">
			        	<label for="lopHoc"><b>Lớp học</b></label>
						<form:select path="lopHoc.maLop" class="form-control" disabled="${remove}">
							<form:option value="NONE" label="Chọn lớp học"/>
							<form:options items="${lopHocList}"/>
						</form:select>
				        <form:errors path="lopHoc" cssClass="error"/>
					</div>
					
					<div class="form-group required col-lg-12">
			        	<label for="monHoc"><b>Môn học</b></label>
						<form:select path="monHoc.maMH" class="form-control" disabled="${remove}">
							<form:option value="NONE" label="Chọn môn học"/>
							<form:options items="${monHocList}"/>
						</form:select>
				        <form:errors path="monHoc" cssClass="error"/>
					</div>
					
					<div class="form-group required col-lg-12">
			        	<label for="giangVien"><b>Giảng viên</b></label>
						<form:select path="giangVien.maGV" class="form-control" disabled="${remove}">
							<form:option value="NONE" label="Chọn giảng viên"/>
							<form:options items="${giangVienList}"/>
						</form:select>
				        <form:errors path="giangVien" cssClass="error"/>
					</div>
					
					<div class="row">
						<div class="col-sm-3">
							<div class="form-group required col-lg-12">
								<label for="ngayBD"><b>Ngày bắt đầu</b></label>
								<form:input path="ngayBD" type="date" class="form-control" readonly="${remove}"/>
								<form:errors path="ngayBD" cssClass="error"/>
							</div>
						</div>
						
						<div class="col-sm-3">
							<div class="form-group required col-lg-12">
								<label for="ngayKT"><b>Ngày kết thúc</b></label>
								<form:input path="ngayKT" type="date" class="form-control" readonly="${remove}"/>
								<form:errors path="ngayKT" cssClass="error"/>
							</div>
						</div>
						
						<div class="col-sm-3">
							<div class="form-group required col-lg-12">
	                			<label for="hocKy"><b>Học kỳ</b></label><br>
	                			<div class="custom-control custom-radio custom-control-inline">
	                				<form:radiobutton path="hocKy" class="custom-control-input" value="1" id="mot" 
	                					name="hocKy" disabled="${remove}"/>
	                				<label class="custom-control-label" for="mot">Một</label>
	                			</div>
	                			<div class="custom-control custom-radio custom-control-inline">
	                				<form:radiobutton path="hocKy" class="custom-control-input" value="2" id="hai" 
	                					name="hocKy" disabled="${remove}"/>
	                				<label class="custom-control-label" for="hai">Hai</label>
	                			</div>
	                			<br><form:errors path="hocKy" cssClass="error"/>
	                		</div>
                		</div>
						
						<div class="col-sm-3">
							<div class="form-group required col-lg-12">
								<label for="ngayKT"><b>Năm học</b></label>
								<form:input path="namHoc" type="number" class="form-control" placeholder="Nhập năm học" readonly="${remove}"/>
								<form:errors path="namHoc" cssClass="error"/>
							</div>
						</div>
					</div>
	                
	                <c:if test="${!success}">
	                	<div class="form-group col-lg-12 text-center">
		                    <div class="btn-group" role="group" aria-label="Basic example">
		                    	<c:choose>
		                    		<c:when test="${add}">
		                    			<button type="submit" class="btn btn-outline-success"><i class="fas fa-check-square"></i> Lưu</button>
		                    		</c:when>
		                    		<c:when test="${edit}">
		                    			<button type="submit" class="btn btn-outline-success"><i class="fas fa-check-square"></i> Cập nhật</button>
		                    		</c:when>
		                    		<c:when test="${remove}">
		                    			<button type="submit" class="btn btn-outline-success"><i class="fas fa-check-square"></i> Xóa</button>
		                    		</c:when>
		                    	</c:choose>
		                    	<button type="button" onclick="location.href='${pageURL}'" class="btn btn-outline-secondary"><i class="fas fa-times"></i> Hủy</button>
		                    </div>
		         		</div>
	                </c:if>	 
	            </form:form>
            </div>
        </div>
    </div>
</body>
</html>