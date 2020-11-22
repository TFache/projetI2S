package fr.polytech;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.mysql.cj.jdbc.*;
import com.mysql.cj.xdevapi.PreparableStatement;


//vue couche m�tier
public class Etudiants {

	private Connection connection;
	
	
	
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public void connexion() {
		//driver MySQL va �tre charg�
				try {
					Class.forName("com.mysql.cj.jdbc.Driver"); //exception surveill�e ici
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println("Driver not loaded");
				}
				
				try {
					connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdd_13_11?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Connection failed.");
				}
	}
	
	public List<Etudiant> afficherTous() { //Va permettre l'affichage de la Base De Donn�es
		List<Etudiant> resultat = new ArrayList<Etudiant>();
		
		
		//connexion � la BDD
		this.connexion();
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdd_13_11?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
			statement = connection.createStatement();
			
			//ex�cuter une requ�te et r�cup son contenu dans resultSet
			resultSet = statement.executeQuery("SELECT * FROM etudiants WHERE 1");
			//r�cup�ration des donn�es
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String nom = resultSet.getString("nom");
				String prenom = resultSet.getString("prenom");
				
				resultat.add(new Etudiant(id, nom, prenom));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		finally {//Pour tout fermer � la fin de l'utilisation
			
				try {
					if(connection != null) connection.close();
					if(statement != null) statement.close();
					if(resultSet != null) resultSet.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		return resultat;
	}

	public void ajoutEtudiant(Etudiant e) {//Permet l'ajout d'un �tudiant gr�ce � un PreparedStatement
		this.connexion();
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement("INSERT INTO `etudiants`(`id`, `nom`, `prenom`) VALUES (?,?,?);");
			preparedStatement.setInt(1, e.getId());//Remplace le 1er "?" de la requ�te
			preparedStatement.setString(2, e.getNom());//Remplace le 2er "?" de la requ�te
			preparedStatement.setString(3, e.getPrenom());//Remplace le 3er "?" de la requ�te
			preparedStatement.executeUpdate(); //Mise � jour du tableau
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("Ajout : " + e1.getMessage());
		} 
	}
	
	public void modifEtudiant(Etudiant etu) {//Modifie les caract�ristiques d'un �tudiant
		this.connexion();
		try {
			PreparedStatement prep = this.connection.prepareStatement("UPDATE etudiants SET nom = ?, prenom = ? WHERE id = ?;");
			prep.setString(1, etu.getNom());
			prep.setString(2, etu.getPrenom());
			prep.setInt(3, etu.getId());
			prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Modification : " + e.getMessage());
		}
	}
	
	public void suppEtudiant(int id) { //Suppression d'un �tudiant. Ici, seul son ID est n�cessaire.
		this.connexion();
		PreparedStatement prep;
		try {
			prep = this.connection.prepareStatement("DELETE FROM `etudiants` WHERE `id` = ?;");
			prep.setInt(1, id);
			prep.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
