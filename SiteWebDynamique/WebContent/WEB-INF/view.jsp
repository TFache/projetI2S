<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> <!-- ça c'est pour que l'affichage des accents se passe bien, parce que même
    avec le meta j'ai des erreurs -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Accueil</title>
<link href=”bootstrap-4.5.2-dist/css/bootstrap.min.css” rel=”stylesheet” type=”text/css” />
</head>
<body>
<% String n = (String)request.getAttribute("nom"); String p = (String)request.getAttribute("prenom"); %>
<h1>Accueil</h1>
<!--  Tout le code en dessous vient de bootstrap -->

<form method="get" action="/projetI2S/resultat?nom=${nom}&prenom=${prenom}">
<!-- Le but : Pouvoir entrer son nom et son prénom et pouvoir les retrouver sur la vue resultat -->
  <div class="form-group">
    <label for="nom">Nom</label>
    <input type="text" class="form-control" id="nom" placeholder="Votre nom">
  </div>
  
  <div class="form-group">
    <label for="prenom">Pr&eacute;nom</label>
    <input type="text" class="form-control" id="prenom" placeholder="Votre prénom">
  </div>
 
  <button type="submit" class="btn btn-default">Envoyer</button>
</form>

<!-- Fin du bootstrap -->
</body>
</html>