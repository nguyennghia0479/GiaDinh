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
	                <div class="form-group required col-lg-12">
	                	<label for="maNganh"><b>Mã ngành</b></label>
	                    <c:choose>
	                    	<c:when test="${edit}">
	                    		<form:input path="maNganh" type="text" class="form-control" placeholder="Nhập mã ngành học" disabled="${edit}"/>
	                    	</c:when>
	                    	<c:when test="${remove}">
	                    		<form:input path="maNganh" type="text" class="form-control" placeholder="Nhập mã ngành học" disabled="${remove}"/>
	                    	</c:when>
	                    	<c:otherwise>
	                    		<form:input path="maNganh" type="text" class="form-control" placeholder="Nhập mã ngành học"/>
	                    		<c:if test="${existKey}">
	                    			<div style="color:red">Mã ngành vừa nhập đã tồn tại</div>
	                    		</c:if>
	                    		<form:errors path="maNganh" cssStyle="color:red"></form:errors>
	                    	</c:otherwise>
	                    </c:choose>
	                </div>
					
					<div class="form-group required col-lg-12">
						<label for="tenNganh"><b>Tên ngành học</b></label>
						<form:input path="tenNganh" type="text" class="form-control" placeholder="Nhập tên ngành học" readonly="${remove}"/>
				    	<form:errors path="tenNganh" cssStyle="color:red"></form:errors>
					</div>
					
					<div class="form-group required col-lg-12">
						<label for="khoa"><b>Khoa</b></label>
						<form:select path="khoa.maKhoa" id="khoa" name="khoa" class="form-control" disabled="${remove}">
							<c:forEach items="${khoa}" var="khoa">
								<form:option value="${khoa.maKhoa}">${khoa.tenKhoa}</form:option>
							</c:forEach>
						</form:select>
					</div>
	                
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
	                
	                <c:choose>
	                	<c:when test="${success}">
							<div class="form-group col-lg-12">
			                	<div class="alert alert-success text-center" role="alert">
			                		<c:choose>
			                			<c:when test="${edit}">
			                				Cập nhật ngành <b>${tenNganh}</b> thành công
			                			</c:when>
			                			<c:when test="${remove}">
											Xóa ngành <b>${tenNganh}</b> thành công
			                			</c:when>
			                			<c:otherwise>
			                				Thêm ngành <b>${tenNganh}</b> thành công
			                			</c:otherwise>	
			                		</c:choose>
									<button onclick="location.href='${pageURL}'" class="close" data-dismiss="alert" aria-label="Close">
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
			                    		<c:when test="${announceReference}">
			                    		</c:when>
			                    		<c:otherwise>
			                    			<button type="submit" class="btn btn-outline-success"><i class="fas fa-check-square"></i> Lưu</button>
			                    		</c:otherwise>
			                    	</c:choose>
			                    	<button type="button" onclick="location.href='${pageURL}'" class="btn btn-outline-secondary"><i class="fas fa-times"></i> Hủy</button>
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