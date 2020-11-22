<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
    <%@include file="../bootstrap-4.5.2-dist/css/bootstrap.min.css" %>
</style>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script type="text/javascript">
	<%@ include file="../bootstrap-4.5.2-dist/js/bootstrap.min.js" %>
</script>
<title>DAO</title>
</head>
<body>
<!-- Juste un bel accueil -->
<h1> Apéro !</h1>
<c:out value="Volontiers !"></c:out>

<!-- Formulaire d'ajout dans la BDD -->
<form action="dao" method="post">
<label id="id">ID</label>
<input type="text" id="id" name="id">
<br/>
<label id="nom">Nom</label>
<input type="text" id="nom" name="nom">
<br/>
<label id="prenom">Prénom</label>
<input type="text" id="prenom" name="prenom">
<br/>
<input type="submit" name="action" value="Ajouter" class="btn btn-primary">
<input type="submit" name="action" value="Supprimer" class="btn btn-primary">
<input type="submit" name="action" value="Modifier" class="btn btn-primary">
</form>

<p>
<div class="input-group">
  <select class="form-control" id="select">
    <option>Select option..</option>
    <option id="nom_op">Nom</option>
    <option id="prenom_op">Prénom</option>
  </select>
<input type="text" id="search" onkeyup="rechercher()" placeholder="Rechercher...">
</div>
</p>


<p>
<table class="table" data-pagination="true" data-page-list="[4, 8, 12, ALL]" data-page-size="4">
  	<thead class="thead-dark">
  	<tr class = "header">
      <th scope="col">N°</th>
      <th scope="col">Nom</th>
      <th scope="col">Prénom</th>
    </tr>
    </thead>
    <tbody id="res">
<c:forEach items="${resultat}" var="etud">
<tr>
	<th scope="row"><c:out value="${etud.id}"></c:out></th>
	<th><c:out value="${etud.nom}"></c:out></th>
	<th><c:out value="${etud.prenom}"></c:out></th>
</tr>
</c:forEach>

	</tbody>
</table>



<script>
function rechercher() {
  // Déclaration des variables utilisées
  var input, filter, table, tr, td, i, select, option, txtValue;
  input = document.getElementById("search");
  filter = input.value.toUpperCase();
  table = document.getElementById("res");
  tr = table.getElementsByTagName("tr");
  select = document.getElementById("select");
  option = select.options[document.getElementById('select').selectedIndex].text;
  
  // Fait la recherche en fonction de l'option choisie et cache les options ne concernant pas la recherche
  if(option == "Prénom") {
	  for (i = 0; i < tr.length; i++) {
		  th = tr[i].getElementsByTagName("th")[2];
	   	  if (th) {
	      txtValue = th.textContent || th.innerText;
	      if (txtValue.toUpperCase().indexOf(filter) > -1) {
	        tr[i].style.display = "";
	      } else {
	        tr[i].style.display = "none";
	      }
	    }
	  }
  }
  else if(option == "Nom") {
	  for (i = 0; i < tr.length; i++) {
		  th = tr[i].getElementsByTagName("th")[1];
	   	  if (th) {
	      txtValue = th.textContent || th.innerText;
	      if (txtValue.toUpperCase().indexOf(filter) > -1) {
	        tr[i].style.display = "";
	      } else {
	        tr[i].style.display = "none";
	      }
	    }
	  }
  }
  
}
</script>

</body>
</html>