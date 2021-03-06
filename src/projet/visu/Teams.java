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
	
	/**
	 * Permet d'acc�der � la base de donn�es
	 */
	public void connexion() {
		//driver MySQL va �tre charg�
				try {
					Class.forName("com.mysql.cj.jdbc.Driver"); //exception surveill�e ici
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println("Driver not loaded");
				}
				
				try {
					connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet_teams?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Connection failed.");
				}
	}
	
	/**
	 * Permet d'afficher toutes les �quipes
	 * @return resultat : toutes les �quipes pr�sentes dans la base de donn�es
	 */
	public List<Team> affichage() {
		
		List<Team> resultat = new ArrayList<Team>();
		//connexion � la BDD
		this.connexion();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet_teams?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
			statement = connection.createStatement();

			//ex�cuter une requ�te et r�cup son contenu dans resultSet
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
	
	/**
	 * Permet de supprimer une �quipe de la base de donn�es
	 * @param id : l'indentifiant de l'�quipe
	 */
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
	
	/**
	 * Permet d'ajouter une �quipe � la base de donn�es
	 * @param t : l'�quipe � ajouter avec tous ses param�tres
	 */
	public void ajoutTeam(Team t) {
		this.connexion();
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement("INSERT INTO `teams`(`id`, `link`, `pseudo`) VALUES (?,?,?);");
			preparedStatement.setInt(1, t.getId());//Remplace le 1er "?" de la requ�tes
			preparedStatement.setString(2, t.getLink());//Remplace le 2er "?" de la requ�te
			preparedStatement.setString(3, t.getPseudo());//Remplace le 3er "?" de la requ�te
			preparedStatement.executeUpdate(); //Mise � jour du tableau
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	}
}
