<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<title>Mẫu Đơn Ngành Học</title>
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
        <h2 class="text-center text-uppercase">Mẫu Đơn Ngành Học
        </h2>
        <div class="card">
            <div class="card-header">
               	Ngành Học
            </div>
            <div class="card-body">
	            <form:form method="post" modelAttribute="nganhHoc">
	            	<c:if test="${success}">
	            		<div class="form-group col-lg-12">
		                	<div class="alert alert-success text-center" role="alert">
		                		<c:choose>
		                			<c:when test="${add}">
										Thêm ngành <b>${tenNganh}</b> thành công
		                			</c:when>
		                			<c:when test="${edit}">
		                				Cập nhật ngành <b>${tenNganh}</b> thành công
		                			</c:when>
		                			<c:otherwise>
		                				Xóa ngành <b>${tenNganh}</b> thành công
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
								Ngành học bạn chọn xóa đang được tham chiếu.
								<br>
								Để xóa Ngành học, bạn hãy xóa hoặc cập nhật <a href="lop-hoc" class="link-warning">Lớp Học</a> 
								tham chiếu tới Ngành học khác.
							</div>
		                </div>
	                </c:if>
	            	
	            	<form:input path="mode" type="hidden" value="${mode}"/>
	                <div class="form-group required col-lg-12">
	                	<label for="maNganh"><b>Mã ngành</b></label>
                   		<form:input path="maNganh" class="form-control" placeholder="Nhập mã ngành học" maxlength="10" disabled="${!add}"/>
                   		<form:errors path="maNganh" cssClass="error"/>
	                </div>
					
					<div class="form-group required col-lg-12">
						<label for="tenNganh"><b>Tên ngành học</b></label>
						<form:input path="tenNganh" class="form-control" placeholder="Nhập tên ngành học" maxlength="100" readonly="${remove}"/>
				    	<form:errors path="tenNganh" cssClass="error"/>
					</div>
					
					<div class="form-group required col-lg-12">
						<label for="khoa"><b>Khoa</b></label>
						<form:select path="khoa.maKhoa" class="form-control" disabled="${remove}">
							<form:option value="NONE" label="Chọn khoa" cssClass="option"/>
							<form:options items="${khoaList}"/>
						</form:select>
						<form:errors path="khoa" cssClass="error"/>
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