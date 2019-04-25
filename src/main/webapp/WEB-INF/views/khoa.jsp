<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
    <title>Khoa</title>
    <link rel="icon" type="image/jpeg" href='<spring:url value="/resources/images/giadinh.png"></spring:url>'>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href='<spring:url value="/resources/css/bootstrap.min.css"></spring:url>' id="bootstrap-css">
    <link rel="stylesheet" href='<spring:url value="/resources/css/jquery-ui.min.css"></spring:url>' id="bootstrap-css">
    <script src='<spring:url value="/resources/js/jquery-3.3.1.min.js"></spring:url>'></script>
    <script src='<spring:url value="/resources/js/jquery-ui.min.js"></spring:url>'></script>
    <script src='<spring:url value="/resources/js/bootstrap.min.js"></spring:url>'></script>
    <link rel="stylesheet" href='<spring:url value="/resources/fontawesome/css/fontawesome.min.css"></spring:url>'>
    <link rel="stylesheet" href='<spring:url value="/resources/fontawesome/css/solid.min.css"></spring:url>'>
    <link rel="stylesheet" href='<spring:url value="/resources/css/listAdmin.css"></spring:url>'>
    
    <script type="text/javascript">
	    $(document).ready(function() {
			$('#khoaName').autocomplete({
				source : '${pageContext.request.contextPath}/admin/searchAuto'
			});
		});
    </script>
</head>
<body>
    <nav class="navbar navbar-expand-sm navbar-dark" style="background-color: #003262;">
        <a class="navbar-brand" href="#">Gia Định</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#"><i class="fas fa-university"></i> Trang Chủ <span class="sr-only">(current)</span></a>
                </li>
            </ul>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="#"><i class="fas fa-sign-out-alt"></i> Đăng xuất</a>
                </li>
            </ul>
        </div>
    </nav>
    <div class="container-fluid">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="#">Trang chủ</a></li>
                <li class="breadcrumb-item"><a href="#">Admin</a></li>
                <li class="breadcrumb-item active" aria-current="page">Khoa</li>
            </ol>
        </nav>
        <div class="row ">
            <div class="col-sm-2 ">
                <div class="vertical-menu ">
                    <a href="#"><i class="fas fa-user-graduate"></i> Sinh Viên</a>
                    <a href="#"><i class="fas fa-user-tie"></i> Giảng Viên</a>
                    <a href="khoa" class="active"><i class="fas fa-graduation-cap"></i> Khoa</a>
                    <a href="#"><i class="fas fa-layer-group"></i> Chuyên Ngành</a>
                    <a href="#"><i class="fas fa-book"></i> Môn Học</a>
                    <a href="#"><i class="fas fa-building"></i> Phòng Học</a>
                    <a href="#"><i class="fas fa-chalkboard-teacher"></i> Lớp Học</a>
                    <a href="#"><i class="fas fa-clipboard-list"></i> Phân Công</a>
                    <a href="#"><i class="fas fa-calendar-alt"></i> Thời Khóa Biểu</a>
                    <a href="#"><i class="fas fa-user-cog"></i> Tài Khoản</a>
                </div>
            </div>
            <div class="col-sm-10 ">
            	<nav class="navbar navbar-expand-lg">
                	<ul class="navbar-nav mr-auto">
                		<li>
	                   		<form class="form-inline my-2 my-lg-0" method="get" action="search-khoa" role="search">
	                       		<input id="khoaName" name="k" class="form-control mr-sm-2" type="search" placeholder="Tìm kiếm khoa" aria-label="Search">
	                          	<button class="btn btn-outline-success my-2 my-sm-0" title="Tìm kiếm" type="submit">
	                          		<i class="fas fa-search"></i>
	                          	</button>
	                        </form>
                        </li>
                  	</ul>
                   	<ul class="navbar-nav">
                   		<li>
                    		<button class="btn btn-outline-primary" onclick="location.href='add-khoa'">
                    			<i class="fas fa-plus-square"></i> Thêm mới
                    		</button>
                  		</li>
                  	</ul>
               	</nav>
                <table class="table table-striped table-hover">
                    <c:choose>
	                    <c:when test="${!empty dsKhoa}">
	                    	<c:if test="${search}">
	                    		<div class="form-group col-lg-12">
				                	<div class="alert alert-success text-center text-uppercase font-weight-bold" role="alert">
										Tìm thấy ${result} kết quả
										<button class="close" data-dismiss="alert" aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
				                </div>
	                    	</c:if>
	                    	<thead class="thead-bg ">
		                        <tr>
		                        	<th scope="col">#</th>
		                            <th scope="col">Mã Khoa</th>
		                            <th scope="col">Khoa</th>
		                            <th scope="col">Thao Tác</th>
		                        </tr>
		                    </thead>
		                    <tbody>
		                    	<c:forEach end="${result}" items="${dsKhoa}" var="khoa" varStatus="loop">
		                    		<c:url var="updateLink" value="edit-khoa">
		                    			<c:param name="maKhoa" value="${khoa.maKhoa}"></c:param>
		                    		</c:url>		                    		
		                    		<c:url var="deleteLink" value="delete-khoa">
		                    			<c:param name="maKhoa" value="${khoa.maKhoa}"></c:param>
		                    		</c:url>
		                    		
		                    		<tr>
		                    			<th>${loop.index+1}</th>
		                    			<td>${khoa.maKhoa}</td>
		                    			<td>${khoa.tenKhoa}</td>
		                    			<td>
		                    				<a href="${updateLink}" class="btn btn-outline-info"><i class="fas fa-edit"></i> Sửa</a>
		                    				<a href="${deleteLink}" class="btn btn-outline-danger"><i class="fas fa-trash-alt"></i> Xóa</a>
		                    			</td>
		                    		</tr>
		                    	</c:forEach>
		                    </tbody>
	                    </c:when>
	                    <c:otherwise>
	                    	<div class="form-group col-lg-12">
			                	<div class="alert alert-danger text-center text-uppercase font-weight-bold" role="alert">
									Không tìm thấy kết quả
								</div>
			                </div>
	                    </c:otherwise>
                    </c:choose>
                </table>
                <div class="text-center">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-center">
                            <li class="page-item">
                                <a class="page-link" href="#" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                    <span class="sr-only">Previous</span>
                                </a>
                            </li>
                            <li class="page-item active"><a class="page-link" href="#">1</a></li>
                            <li class="page-item"><a class="page-link" href="#">2</a></li>
                            <li class="page-item"><a class="page-link" href="#">3</a></li>
                            <li class="page-item">
                                <a class="page-link" href="#" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                    <span class="sr-only">Next</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</body>
</html>