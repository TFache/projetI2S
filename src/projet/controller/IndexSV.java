package projet.controller;

import java.io.IOException;
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
			t.setId(Integer.parseInt(request.getParameter("id")));
			t.setLink(request.getParameter("link"));
			t.setPseudo(request.getParameter("login")); //Pseudo et login ont le même objectif
			
			Teams ts = new Teams();
			ts.ajoutTeam(t);
		}
		doGet(request, response);
	}

}
