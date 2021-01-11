<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>Bienvenue !</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<style type="text/css">
    	<%@include file="../bootstrap-4.5.2-dist/css/bootstrap.min.css" %>
	</style>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script type="text/javascript">
		<%@ include file="../bootstrap-4.5.2-dist/js/bootstrap.min.js" %>
	</script>
</head>
<body>
	<%@ include file="nav.jsp" %>
	<h1>Bienvenue !</h1>
<c:if test="${authentification.isConnexion()}">
<c:out value="${login }"></c:out>
</c:if>
</body>
</html>