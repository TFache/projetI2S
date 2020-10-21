<!DOCTYPE unspecified PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<title>Gestion Etudiants</title>
<body>
<?php 
$action = "";//Nous sert pour l'affichage après une action
?>
<h1>Cr&eacute;er un &eacute;tudiant</h1>
	<form action="Gestion.php" method="POST">
		Nom : <input type="text" name ="nom"/><br>
		Prenom : <input type="text" name ="prenom"/><br>
		Email : <input type="text" name ="email"/><br>
		<input type = "submit" name = "action" value = "ajouter"/>
		<?php 
		if(isset($_POST['action']) && ($_POST['action']=='ajouter')){
		    $clientSoap = new SoapClient("http://localhost:9594/?wsdl");
		    if(isset($_POST["nom"]) && isset($_POST["prenom"]) && isset($_POST["email"])) {
		        $etudiant = array("arg0"=>$_POST["nom"], "arg1"=>$_POST["prenom"], "arg2"=>$_POST["email"]);
		        $clientSoap->ajouter($etudiant);
		        $action = "ajouter";
		
		    }
		}
        ?>
    </form>
    <?php if($action == "ajouter") echo "Ajout effectu&eacute; !"?>
    
<!-- Supprimer un élève -->    
<h1>Supprimer un &eacute;tudiant</h1>
	<form action="Gestion.php" method="POST">
		ID : <input type ="number" name="id_supp" min="0"/><br>
		<input type="submit" name="action" value="supprimer"/>
		<?php 
		if(isset($_POST['action']) && ($_POST['action']=='supprimer')){
		    $clientSoap = new SoapClient("http://localhost:9594/?wsdl");
		    $param = array("arg0"=>$_POST["id_supp"]);
		    $clientSoap->suppression($param);
		    $action="supprimer";
		}
		?>
	</form>
	<?php if($action == "supprimer") echo "Suppression effectu&eacute; !"?>
	
<!-- Consulter un élève -->
<h1>Consulter un &eacute;tudiant</h1>
	<form action="Gestion.php" method="POST">
			ID : <input type ="number" name="id_cons" min="0"/><br>
			<input type="submit" name="action" value="consulter"/>
			<?php 
			if(isset($_POST['action']) && ($_POST['action']=='consulter')){
			    $clientSoap = new SoapClient("http://localhost:9594/?wsdl");
			    $param = array("arg0"=>$_POST["id_cons"]);
			    $result = $clientSoap->consulter($param);
			    $action = "consulter";
			    
			}
			?>
			
	</form>
	<?php if($action == "consulter") echo "Etudiant : ",print_r((array)$result->return); ?>
	
<!-- Modifier un élève -->
<h1>Modifier un &eacute;tudiant</h1>
	<form action="Gestion.php" method="post">
		ID : <input type ="number" name="id_modi" min="0"/><br>
		Modifier : <input type="radio" id="param" name="param" value="nom">
				<label for="nom">Nom</label>
				<input type="radio" id="param" name="param" value="prenom">
				<label for="prenom">Prenom</label>
				<input type="radio" id="param" name="param" value="email">
				<label for="email">Email</label><br>
		Nouvelle valeur : <input type="text" name="newVal"/><br>
		<input type="submit" name="action" value="modifier" />
		
		<?php 
		if(isset($_POST['action']) && ($_POST['action']=='modifier')){
		    $clientSoap = new SoapClient("http://localhost:9594/?wsdl");
		    $param = array("arg0"=>$_POST["id_modi"], "arg1"=>$_POST["param"], "arg2"=>$_POST["newVal"]);
		    $clientSoap->modifier($param);
		    $action="modifier";
		   
		}
		?>
		</form>
		<?php if($action == "modifier") echo "Param&egrave;tre modifi&eacute; !"?>
</body>
</html>
