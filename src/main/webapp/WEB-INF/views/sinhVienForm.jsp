<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<title>Mẫu Đơn Sinh Viên</title>
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
        <h2 class="text-center text-uppercase">Mẫu Đơn Sinh Viên
        </h2>
        <div class="card">
            <div class="card-header">
               	Sinh Viên
            </div>
            <div class="card-body">   	
	            <form:form method="post" enctype="multipart/form-data" modelAttribute="sinhVien">  
	            	<c:if test="${success}">
            			<div class="form-group col-lg-12">
		                	<div class="alert alert-success text-center" role="alert">
		                		<c:choose>
		                			<c:when test="${remove}">
		                				Xóa sinh viên <b>${hoTen}</b> thành công
		                			</c:when>
		                			<c:when test="${edit}">
		                				Cập nhật sinh viên <b>${hoTen}</b> thành công
		                			</c:when>
		                			<c:otherwise>
		                				Thêm sinh viên <b>${hoTen}</b> thành công
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
	              
	              	<form:input path="mode" type="hidden" value="${mode}"/>
	                <div class="row">
	                	<div class="col-sm-4">
	                		<c:choose>
	                			<c:when test="${empty img}">
	                				<div class="form-group required col-lg-12">	
			                       		<img id="img-upload" width="240px" height="240px"/>               			
			                		</div>
	                			</c:when>
	                			<c:otherwise>
	                				<div class="form-group required col-lg-12">	
			                       		<img id="img-upload" src="data:image/jpeg;base64,${sinhVien.base64}" width="240px" height="240px"/>               			
			                		</div>
	                			</c:otherwise>
	                		</c:choose>      		
	                		<div class="form-group required col-lg-12">	
	                			<label for="hinhAnh"><b>Hình ảnh</b></label>
	                			<div class="custom-file mb-3">
	                				<c:if test="${remove}">
	                					<input type="file" class="custom-file-input btn-file" id="image" name="image" disabled="disabled"/>
	                				</c:if>
	                                <c:if test="${!remove }">
	                                	<input type="file" class="custom-file-input btn-file" id="image" name="image" />
	                                </c:if>
	                                <label class="custom-file-label" for="hinhAnh">Chọn hình ảnh</label>
	                                <c:if test="${error}">
	                                	<span class="error">${message}</span>
	                                </c:if>
	                            </div>
	                		</div>
	                	</div>
	                	<div class="col-sm-8">
	                		<div class="row">
	                			<div class="col-sm-5">
	                				<div class="form-group required col-lg-12">
					                	<label for="maSV"><b>Mã sinh viên</b></label>
			                    		<form:input path="maSV" class="form-control" placeholder="Nhập mã sinh viên" maxlength="10"
			                    			disabled="${!add}"/>
			                    		<form:errors path="maSV" cssClass="error"/>
					                </div>
					        	</div>
	                			<div class="col-sm-7">
	                				<div class="form-group required col-lg-12">
					                	<label for="hoTen"><b>Họ tên</b></label>
					                	<form:input path="hoTen" class="form-control" placeholder="Nhập họ tên" maxlength="100"
					                		readonly="${remove}"/>
					                	<form:errors path="hoTen" cssClass="error"/>
					                </div>
	                			</div>
	                		</div>
	                		
	                		<div class="row">
	                			<div class="col-sm-5">
	                				<div class="form-group required col-lg-12">
					                	<label for="SDT"><b>Số điện thoại</b></label>
					                	<form:input path="SDT" class="form-control" placeholder="Nhập số điện thoại" maxlength="10" 
					                		readonly="${remove}"/>
					                	<form:errors path="SDT" cssClass="error"/>
					                </div>
	                			</div>
	                			<div class="col-sm-7">
	                				<div class="form-group required col-lg-12">
					                	<label for="email"><b>Email</b></label>
					                	<form:input path="email" class="form-control" placeholder="Nhập email" maxlength="255"
					                		readonly="${remove}"/>
					                	<form:errors path="email" cssClass="error"/>
					                </div>
	                			</div>
	                		</div> 	
	                		
	                		<div class="row">
	                			<div class="col-sm-5">
	                				<div class="form-group required col-lg-12">
			                			<label for="gioiTinh"><b>Giới tính</b></label><br>
			                			<div class="custom-control custom-radio custom-control-inline">
			                				<form:radiobutton path="gioiTinh" class="custom-control-input" value="Nam" id="nam" 
			                					name="gioiTinh" disabled="${remove}"/>
			                				<label class="custom-control-label" for="nam">Nam</label>
			                			</div>
			                			<div class="custom-control custom-radio custom-control-inline">
			                				<form:radiobutton path="gioiTinh" class="custom-control-input" value="Nữ" id="nu" 
			                					name="gioiTinh" disabled="${remove}"/>
			                				<label class="custom-control-label" for="nu">Nữ</label>
			                			</div>
			                			<br><form:errors path="gioiTinh" cssClass="error"/>
			                		</div>
	                			</div>
	                			<div class="col-sm-7">
	                				<div class="form-group required col-lg-12">
					                	<label for="diaChi"><b>Địa chỉ</b></label>
					                	<form:input path="diaChi" class="form-control" placeholder="Nhập địa chỉ" maxlength="255"
					                		readonly="${remove}"/>
					                	<form:errors path="diaChi" cssClass="error"/>
					                </div>
	                			</div>
	                		</div> 	
	                		
	                		<div class="row">
	                			<div class="col-sm-5">
	                				<div class="form-group required col-lg-12">
					                	<label for="ngaySinh"><b>Ngày sinh</b></label>
					                	<form:input path="ngaySinh" type="date" class="form-control" readonly="${remove}"/>
					                	<form:errors path="ngaySinh" cssClass="error"/>
					                </div>
	                			</div>
	                			<div class="col-sm-7">
	                				<div class="form-group required col-lg-12">
					                	<label for="noiSinh"><b>Nơi sinh</b></label>
					                	<form:select path="noiSinh" class="form-control" disabled="${remove}">
					                		<form:option value="NONE" label="Chọn nơi sinh" cssClass="option"/>
					                		<form:options items="${noiSinhList}"/>
					                	</form:select>
					                	<form:errors path="noiSinh" cssClass="error"/>
					                </div>
	                			</div>
	                		</div> 	
	                	</div>
	                </div>
	                <div class="row">
	                	<div class="col-sm-4">
	                		<div class="form-group required col-lg-12">
			                	<label for="khoa"><b>Khóa</b></label>
			                	<form:input path="khoa" type="number" class="form-control" placeholder="Nhập khóa" 
			                		readonly="${remove}"/>
								<form:errors path="khoa" cssClass="error"/>
			                </div>
	                	</div>
	                	<div class="col-sm-4">
	                		<div class="form-group required col-lg-12">
			                	<label for="loppHoc"><b>Lớp học</b></label>
			                	<form:select path="lopHoc.maLop" class="form-control"  disabled="${remove}">
			                		<form:option value="NONE" label="Chọn lớp học" cssClass="option"/>
									<form:options items="${lopHocList}"/>
								</form:select>
								<form:errors path="lopHoc" cssClass="error"/>
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
    <script src='<spring:url value="/resources/js/chooseImg.js"></spring:url>'></script>
</body>
</html>