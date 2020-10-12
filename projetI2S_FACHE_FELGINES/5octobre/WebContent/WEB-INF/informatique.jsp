<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href=”bootstrap/css/bootstrap.min.css” rel=”stylesheet” type=”text/css” />
<meta charset="UTF-8">
<title>Informatique</title>
</head>
<body>
<%@ include file="menu.jsp" %>
<h1>Cours Informatique</h1>
${empty nom ? nom ; "T'as pas de nom" }
</body>
</html>