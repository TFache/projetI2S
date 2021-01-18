package projet.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Exécute ce fichier quand tu veux voir le résultat sur une page Web, ou juste 
//localhost:8080/[nom du dossier]/[ce que t'as placé dans <url-pattern> sur le fichier xml]
//juste à run ce fichier et tomcat démarrera tout seul

@WebServlet("/Application")
public class Application extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public Application() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected Authentification authentification = new Authentification();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		if(authentification.isConnexion())  response.sendRedirect("index");
			//this.getServletContext().getRequestDispatcher("/WEB-INF/resultat.jsp").forward(request, response);

		else this.getServletContext().getRequestDispatcher("/WEB-INF/Connexion/view.jsp").forward(request, response);
		
		//Si j'ai bien compris, la commande au dessus va te permettre de savoir quelle vue doit être 
		//exécutée
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String login = request.getParameter("login");
		request.setAttribute("login", login);

		String password = request.getParameter("password");
		request.setAttribute("password", password);

		authentification.acces(request); 
		request.setAttribute("authentification", authentification);

		
		
		
		doGet(request, response);
		
		
		
	}

	
	
}