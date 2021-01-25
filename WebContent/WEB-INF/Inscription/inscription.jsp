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
    	<%@include file="/bootstrap-4.5.2-dist/css/bootstrap.min.css" %>
	</style>
	
</head>
<body>
<h1>Inscription</h1>

<form method="post" action="signup">
<!-- Le but : S'inscrire -->
  <div class="form-group">
    <label for="login_insc">Identifiant</label>
    <input type="text" class="form-control" id="login_insc" name="login_insc" placeholder="Votre identifiant"/>
  </div>
  
  <div class="form-group">
    <label for="password_insc">Mot de passe</label>
    <input type="password" class="form-control" id="password_insc" name="password_insc" placeholder="Votre mot de passe"/>
  </div>
 
  <button type="submit" class="btn btn-default">Envoyer</button>

</form>
</body>
</html>