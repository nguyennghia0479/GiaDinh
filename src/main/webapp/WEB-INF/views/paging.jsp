<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
</head>
<body>	
	<!-- 	Previous Page -->
	<c:url value="${pageURL}" var="previousPage">
		<c:if test="${search}">
			<c:param name="k" value="${k}"></c:param>
		</c:if>
		<c:param name="p" value="${pagedListHolder.page - 1}"></c:param>
	</c:url>
	<!-- 	Next Page -->
	<c:url value="${pageURL}" var="nextPage">
		<c:if test="${search}">
			<c:param name="k" value="${k}"></c:param>
		</c:if>
		<c:param name="p" value="${pagedListHolder.page + 1}"></c:param>
	</c:url>
	<!-- 	First Page -->
	<c:url value="${pageURL}" var="firstPage">
		<c:if test="${search}">
			<c:param name="k" value="${k}"></c:param>
		</c:if>
		<c:param name="p" value="0"></c:param>
	</c:url>
	<!-- 	Last Page -->
	<c:url value="${pageURL}" var="lastPage">
		<c:if test="${search}">
			<c:param name="k" value="${k}"></c:param>
		</c:if>
		<c:param name="p" value="${pagedListHolder.pageCount}"></c:param>
	</c:url>
						
	<c:if test="${pagedListHolder.pageCount > 1}">
		<div class="text-center">
			<nav aria-label="Page navigation example">
				 <ul class="pagination justify-content-center">
				 	<c:if test="${!pagedListHolder.firstPage}">
				 		<li class="page-item">
				 			<a class="page-link" href="${previousPage}" aria-lable="Previous">
				 				<span aria-hidden="true">&laquo;</span>
				 				<span class="sr-only">Previous</span>
				 			</a>
				 		</li>
				 	</c:if>
				 	<c:if test="${pagedListHolder.firstLinkedPage > 0}">
						<li class="page-item">
							<a class="page-link" href="${firstPage}">1</a>
						</li>
					</c:if>
					<c:if test="${pagedListHolder.firstLinkedPage > 1}">
						<li class="page-item"><span class="page-link">...</span><li>
					</c:if>
					<c:forEach begin="${pagedListHolder.firstLinkedPage}" end="${pagedListHolder.lastLinkedPage}" var="p">
						<!-- Page Link -->
						<c:url value="${pageURL}" var="pageLink">
							<c:if test="${search}">
								<c:param name="k" value="${k}"></c:param>
							</c:if>
							<c:param name="p" value="${p}"></c:param>
						</c:url>
						<c:choose>
							<c:when test="${pagedListHolder.page == p}">
								<li class="page-item active"><a class="page-link" href="">${p+1}</a></li>
							</c:when>
							<c:otherwise>
								 <li class="page-item">
								 	<a class="page-link" href="${pageLink}">${p+1}</a>
								 </li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${pagedListHolder.lastLinkedPage < pagedListHolder.pageCount - 2}">
						<li class="page-item"><span class="page-link">...</span><li>
					</c:if>
					<c:if test="${pagedListHolder.lastLinkedPage < pagedListHolder.pageCount - 1}">
						<li class="page-item">
							<a class="page-link" href="${lastPage}">${pagedListHolder.pageCount}</a>
						</li>
					</c:if>
					<c:if test="${!pagedListHolder.lastPage}">
						<li class="page-item">
		             		<a class="page-link" href="${nextPage}" aria-label="Next">
		                        <span aria-hidden="true">&raquo;</span>
		                        <span class="sr-only">Next</span>
		                    </a>
		                </li>
					</c:if>
				 </ul>
			</nav>
		</div>
	</c:if>
</body>
</html>