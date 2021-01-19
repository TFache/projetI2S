package projet.controller;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import projet.visu.*;


import javax.servlet.http.HttpServletRequest;

public class Authentification implements Serializable {


	private static final long serialVersionUID = 1L;
	private boolean connexion;
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
	
	public void acces(HttpServletRequest request) {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		t.connexion();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet_teams?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
			statement = connection.createStatement();

			//ex�cuter une requ�te et r�cup son contenu dans resultSet
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

	}
}
