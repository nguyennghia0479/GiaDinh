<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="login.title"/></title>
</head>
<body>
	<label><spring:message code="login.user"/></label>
	<label><spring:message code="login.pass"/></label>
	
	 <div style="text-align: right;padding:5px;margin:5px 0px;background:#ccc;">
       <a href="${pageContext.request.contextPath}/en/login">Login (English)</a>
       &nbsp;&nbsp;
       <a href="${pageContext.request.contextPath}/vi/login">Login (Vietnamese)</a>
      </div>
      
      <p id="demo"></p>
     

<script>
  var str = "/GiaDinh/en/login";
  var res = str.substring(9, 11);
  document.getElementById("demo").innerHTML = res;
</script>
</body>
</html>