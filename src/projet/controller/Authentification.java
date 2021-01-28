package projet.controller;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import projet.visu.*;


import javax.servlet.http.HttpServletRequest;

public class Authentification implements Serializable {


	private static final long serialVersionUID = 1L;
	private boolean connexion = false;
	private boolean inscription = false;
	Teams t = new Teams();
	Connection connection = t.getConnection();

	public Authentification() {
		super();
	}

	public Authentification(boolean connexion) {
		super();
		this.connexion = connexion;
	}

	public boolean isConnexion() {
		return connexion;
	}

	public void setConnexion(boolean connexion) {
		this.connexion = connexion;
	}
	
	/**
	 * @return the inscription
	 */
	public boolean isInscription() {
		return inscription;
	}

	/**
	 * @param inscription the inscription to set
	 */
	public void setInscription(boolean inscription) {
		this.inscription = inscription;
	}

	/**
	 * Permet de crypter le mot de passe
	 * @param password : le mot de passe saisi par l'utilisateur
	 * @return le mot de passe crypté via SHA-256
	 */
	public String hashCode(String password) {
		MessageDigest md; StringBuilder sb = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
			byte[]hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

		       //bytes to hex
		        sb = new StringBuilder();
		        for (byte b : hashInBytes) {
		            sb.append(String.format("%02x", b));
		        }
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
        
	}
	
	/**
	 * Permet de s'inscrire sur le site via une requête SQL. Pose également le booléen inscription à true.
	 * @param request : la requête HTTP du servlet
	 */
	public void inscription(HttpServletRequest request) {
		String login_insc = request.getParameter("login_insc");
		String password_insc = request.getParameter("password_insc");
		
		t.connexion();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet_teams?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
			/*statement = connection.createStatement();

			//exécuter une requête et récup son contenu dans resultSet
			resultSet = statement.executeQuery("SELECT * FROM login WHERE login = \"" + login_insc + "\"");
			
			while(resultSet.next())
			if(resultSet.getString("login").isEmpty()) {*/
				PreparedStatement prep = this.connection.prepareStatement("INSERT INTO `login`(`login`, `password`) VALUES (?,?);");
				prep.setString(1, login_insc);
				prep.setString(2, hashCode(password_insc));
				prep.executeUpdate();
				
				this.inscription = true;
			//}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Permet de comparer les identifiants et mots de passes cryptés avec ceux présents sur la base de données. Définit le booléen connexion en fonction.
	 * @param request : la requête HTTP du servlet
	 */
	public void acces(HttpServletRequest request) {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		t.connexion();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet_teams?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
			statement = connection.createStatement();

			//exécuter une requête et récup son contenu dans resultSet
			resultSet = statement.executeQuery("SELECT * FROM login WHERE login = \"" + login + "\"");
			
			while(resultSet.next()) {
				if(hashCode(password).equals(resultSet.getString("password")) && login.equals(resultSet.getString("login").toString())) {
					this.connexion = true;
				}
				else {
					this.connexion = false;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {//Pour tout fermer à la fin de l'utilisation
			
			try {
				if(connection != null) connection.close();
				if(statement != null) statement.close();
				if(resultSet != null) resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
