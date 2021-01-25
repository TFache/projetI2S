<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Accueil</title>
<style type="text/css">
    	<%@include file="/bootstrap-4.5.2-dist/css/bootstrap.min.css" %>
</style>
</head>
<body>

<h1>Accueil</h1>


<form method="post" action="login">
<!-- Le but : Pouvoir entrer son login et password et pouvoir voir son nom sur la vue resultat -->
  <div class="form-group">
    <label for="login">Identifiant</label>
    <input type="text" class="form-control" id="login" name="login" placeholder="Votre identifiant"/>
  </div>
  
  <div class="form-group">
    <label for="password">Mot de passe</label>
    <input type="password" class="form-control" id="password" name="password" placeholder="Votre mot de passe"/>
  </div>
 
  <button type="submit" class="btn btn-default">Envoyer</button>

</form>
<c:if test="${authentification.isInscription()}"><c:out value="Inscription effectuée avec succès !"></c:out></c:if>
<a href="signup">Inscrivez-vous ici !</a>
</body>
</html>