<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
	<title>Lớp Học</title>
	<link rel="icon" type="image/jpeg" href='<spring:url value="/resources/images/giadinh.png"></spring:url>'>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href='<spring:url value="/resources/css/bootstrap.min.css"></spring:url>'>
    <link rel="stylesheet" href='<spring:url value="/resources/css/jquery-ui.min.css"></spring:url>'>
    <link rel="stylesheet" href='<spring:url value="/resources/fontawesome/css/all.min.css"></spring:url>'>
    <link rel="stylesheet" href='<spring:url value="/resources/css/listAdmin.css"></spring:url>'>
    <script src='<spring:url value="/resources/js/jquery-3.3.1.min.js"></spring:url>'></script>
    <script src='<spring:url value="/resources/js/jquery-ui.min.js"></spring:url>'></script>
    <script src='<spring:url value="/resources/js/bootstrap.min.js"></spring:url>'></script>
    
    <script type="text/javascript">
    	$(document).ready(function(){
    		$('#lopHoc').autocomplete({
    			source: '${pageContext.request.contextPath}/admin/searchAuto-lop-hoc'
    		});
    	});
    </script>
</head>
<body>
	<jsp:include page="menu.jsp"/>
	<div class="container-fluid">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="#">Trang chủ</a></li>
                <li class="breadcrumb-item"><a href="#">Admin</a></li>
                <li class="breadcrumb-item active" aria-current="page">Lớp Học</li>
            </ol>
        </nav>
        <div class="row ">
            <div class="col-sm-2 ">
                <div class="vertical-menu ">
                    <a href="sinh-vien"><i class="fas fa-user-graduate"></i> Sinh Viên</a>
                    <a href="giang-vien"><i class="fas fa-user-tie"></i> Giảng Viên</a>
                    <a href="khoa"><i class="fas fa-graduation-cap"></i> Khoa</a>
                    <a href="nganh-hoc"><i class="fas fa-layer-group"></i> Ngành Học</a>
                    <a href="mon-hoc"><i class="fas fa-book"></i> Môn Học</a>
                    <a href="phong-hoc"><i class="fas fa-building"></i> Phòng Học</a>
                    <a href="lop-hoc" class="active"><i class="fas fa-chalkboard-teacher"></i> Lớp Học</a>
                    <a href="phan-cong"><i class="fas fa-clipboard-list"></i> Phân Công</a>
                    <a href="thoi-khoa-bieu"><i class="fas fa-calendar-alt"></i> Thời Khóa Biểu</a>
                    <a href="tai-khoan"><i class="fas fa-user-cog"></i> Tài Khoản</a>
                </div>
            </div>
            <div class="col-sm-10 ">
            	<c:set var="pagedListHolder" value="${pagedListHolder}" scope="session" /> 
            	<nav class="navbar navbar-expand-lg">
                	<ul class="navbar-nav mr-auto">
                		<li>
	                   		<form class="form-inline my-2 my-lg-0" method="get" action="lop-hoc" role="search">
	                       		<input id="lopHoc" name="k" class="form-control mr-sm-2" type="search" placeholder="Tìm kiếm lớp học" aria-label="Search">
	                          	<button class="btn btn-outline-success my-2 my-sm-0" title="Tìm kiếm" type="submit">
	                          		<i class="fas fa-search"></i>
	                          	</button>
	                        </form>
                        </li>
                  	</ul>
                   	<ul class="navbar-nav">
                   		<li>   
                   			<c:url value="add-lop-hoc" var="addLink">
                   				<c:param name="p" value="${pagedListHolder.page}"></c:param>
                   				<c:if test="${search}">
                    				<c:param name="k" value="${k}"></c:param>
                    			</c:if>
                   			</c:url>                 		
                   			<a title="Thêm" class="btn btn-outline-primary" href="${addLink}">
                    			<i class="fas fa-plus-square"></i> Thêm mới
                    		</a>
                  		</li>
                  	</ul>
               	</nav>	
                <table class="table table-striped table-hover">
                    <c:choose>
	                    <c:when test="${!empty pagedListHolder.pageList}">
	                    	<c:if test="${search}">
	                    		<div class="form-group col-lg-12">
				                	<div class="alert alert-success text-center" role="alert">
										Tìm thấy <b>${result}</b> kết quả
									</div>
				                </div>
	                    	</c:if>
	                    	<thead class="thead-bg ">
		                        <tr>
		                        	<th scope="col">#</th>
		                            <th scope="col">Mã Lớp</th>
		                            <th scope="col">Chuyên Ngành</th>
		                            <th scope="col">Thao Tác</th>
		                        </tr>
		                    </thead>
		                    <tbody>
		                    	<c:forEach end="${result}" items="${pagedListHolder.pageList}" var="lopHoc" varStatus="loop">
		                    		<c:url var="updateLink" value="edit-lop-hoc">
		                    			<c:param name="maLop" value="${lopHoc.maLop}"></c:param>
		                    			<c:param name="p" value="${pagedListHolder.page}"></c:param>
		                    			<c:if test="${search}">
		                    				<c:param name="k" value="${k}"></c:param>
		                    			</c:if>
		                    		</c:url>		                    		
		                    		<c:url var="deleteLink" value="delete-lop-hoc">
		                    			<c:param name="maLop" value="${lopHoc.maLop}"></c:param>
		                    			<c:param name="p" value="${pagedListHolder.page}"></c:param>
		                    			<c:if test="${search}">
		                    				<c:param name="k" value="${k}"></c:param>
		                    			</c:if>
		                    		</c:url>
		                    		
		                    		<tr>
		                    			<th>${loop.index+1}</th>
		                    			<td>${lopHoc.maLop}</td>
		                    			<td>${lopHoc.nganhHoc.tenNganh}</td>
		                    			<td>
		                    				<a href="${updateLink}" title="Sửa" class="btn btn-outline-info"><i class="fas fa-edit"></i></a>
		                    				<a href="${deleteLink}" title="Xóa" class="btn btn-outline-danger"><i class="fas fa-trash-alt"></i></a>
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
             	<jsp:include page="paging.jsp"/>
            </div>
        </div>
    </div>
</body>
</html>