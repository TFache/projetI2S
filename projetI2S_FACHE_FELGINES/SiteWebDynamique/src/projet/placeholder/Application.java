package projet.placeholder;

import java.io.IOException;
import java.io.PrintWriter;

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

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = request.getParameter("nom"); String prenom = request.getParameter("prenom");
		request.setAttribute("nom", nom); request.setAttribute("prenom", prenom);
		this.getServletContext().getRequestDispatcher("/WEB-INF/view.jsp").forward(request, response);
		//Si j'ai bien compris, la commande au dessus va te permettre de savoir quelle vue doit être 
		//exécutée
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}