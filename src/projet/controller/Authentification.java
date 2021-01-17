package projet.controller;

import java.io.Serializable;
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
			resultSet = statement.executeQuery("SELECT * FROM login WHERE login =" + login);
			

			if(password.equals(resultSet.getString("password")) && login.equals(resultSet.getString("login"))) {
				this.connexion = true;
			}
			else {
				this.connexion = false;
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
