package projet.controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

public class Authentification implements Serializable {


	private static final long serialVersionUID = 1L;
	private boolean connexion;

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
		
		if(password.equals(login+"0000")) {
			this.connexion = true;
		}
		else {
			this.connexion = false;
		}
	}
}
