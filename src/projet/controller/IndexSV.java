package projet.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projet.visu.Team;
import projet.visu.Teams;

/**
 * Servlet implementation class Index
 */
@WebServlet("/Index")
public class IndexSV extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexSV() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Teams t = new Teams();
		request.setAttribute("resultat", t.affichage());
		this.getServletContext().getRequestDispatcher("/WEB-INF/resultat.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		if(request.getParameter("action").equals("Ajouter")) {
			Team t = new Team();
			
			URL u = new URL(request.getParameter("link"));
			InputStream in = u.openStream();
			Scanner scanner = new Scanner(in);
	        String responseBody = scanner.useDelimiter("\\A").next();
	        String title = responseBody.substring(responseBody.indexOf("<title>") + 7, responseBody.indexOf("</title>"));
			
			//l'id s'auto-incrémente
			t.setLink(request.getParameter("link"));
			t.setPseudo(title); //Pseudo et login ont le même objectif
			
			Teams ts = new Teams();
			ts.ajoutTeam(t);
		}
		if(request.getParameter("action").equals("Supprimer")) {
			
			int id = Integer.parseInt(request.getParameter("id"));
			
			Teams ts = new Teams();
			ts.suppTeam(id);
		}
		doGet(request, response);
	}

}
