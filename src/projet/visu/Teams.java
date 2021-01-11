package projet.visu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Teams {

	private Connection connection;

	/**
	 * @return the connection
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * @param connection the connection to set
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public void connexion() {
		//driver MySQL va être chargé
				try {
					Class.forName("com.mysql.cj.jdbc.Driver"); //exception surveillée ici
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
	
	public List<Team> affichage() {
		
		List<Team> resultat = new ArrayList<Team>();
		//connexion à la BDD
		this.connexion();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet_teams?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
			statement = connection.createStatement();

			//exécuter une requête et récup son contenu dans resultSet
			resultSet = statement.executeQuery("SELECT * FROM teams WHERE 1");
			
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String link = resultSet.getString("link");
				String pseudo = resultSet.getString("pseudo");
				
				resultat.add(new Team(id, link, pseudo));
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
	
		return resultat;

	}
	
	public void suppTeam(int id) {
		
		this.connexion();
		PreparedStatement prep;
		try {
			prep = this.connection.prepareStatement("DELETE FROM `teams` WHERE `id` = ?;");
			prep.setInt(1, id);
			prep.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ajoutTeam(Team t) {
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement("INSERT INTO `teams`(`id`, `link`, `login`) VALUES (?,?,?);");
			preparedStatement.setInt(1, t.getId());//Remplace le 1er "?" de la requête
			preparedStatement.setString(2, t.getLink());//Remplace le 2er "?" de la requête
			preparedStatement.setString(3, t.getLogin());//Remplace le 3er "?" de la requête
			preparedStatement.executeUpdate(); //Mise à jour du tableau
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("Ajout : " + e1.getMessage());
		} 
	}
}
