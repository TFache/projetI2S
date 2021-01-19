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
	<h1>Bienvenue <c:if test="${authentification.isConnexion()}"><c:out value="${login }"></c:out></c:if> !</h1>
	<h5>Ici, tu vas pouvoir stocker tes teams Showdown tranquillement sans risques de les voir disparaître à cause d'un malencontreux nettoyage des cookies du navigateur !</h5>


<form action="" method="post">
<label id="team">Déposez votre team ici : </label>
<input type="text" id="link" name="link">
<br/>
<label id="id">Entrez le numéro de la team à supprimer ici : </label>
<input type="text" id="id" name="id">
<br/>
<input type="submit" name="action" value="Ajouter" class="btn btn-primary">
<input type="submit" name="action" value="Supprimer" class="btn btn-primary">
</form>



<table class="table" data-pagination="true" data-page-list="[4, 8, 12, ALL]" data-page-size="4">
  	<thead class="thead-dark">
  	<tr class = "header">
      <th scope="col">N°</th>
      <th scope="col">Nom de la team</th>
      <th scope="col">Lien de la team</th>
    </tr>
	</thead>
	<tbody id="res">
<c:forEach items="${resultat}" var="teams">
<tr>
	<th scope="row"><c:out value="${teams.id}"></c:out></th>
	<th><c:out value="${teams.pseudo}"></c:out></th>
	<th><a href = "<c:out value="${teams.link}"></c:out>"><c:out value="${teams.link}"></c:out></a></th>
</tr>
</c:forEach>

	</tbody>
</table>
</body>
</html>