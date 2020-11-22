package fr.polytech;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Etudiants etudiantsListe = new Etudiants();
		request.setAttribute("resultat", etudiantsListe.afficherTous());
		this.getServletContext().getRequestDispatcher("/WEB-INF/dao.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Un if pour chaque méthode : ajout, suppression ou modification d'un étudiant. La recherche s'effectue avec du JavaScript.
		if(request.getParameter("action").equals("Ajouter")) {
			Etudiant etudiant = new Etudiant();
			etudiant.setId(Integer.parseInt(request.getParameter("id")));
			etudiant.setNom(request.getParameter("nom"));
			etudiant.setPrenom(request.getParameter("prenom"));
			
			Etudiants etudiants = new Etudiants();
			etudiants.ajoutEtudiant(etudiant);
		}
		if(request.getParameter("action").equals("Supprimer")) {
			Etudiants sup = new Etudiants();
			
			sup.suppEtudiant(Integer.parseInt(request.getParameter("id")));
		}
		if(request.getParameter("action").equals("Modifier")) {
			Etudiant etudiant = new Etudiant();
			etudiant.setId(Integer.parseInt(request.getParameter("id")));
			etudiant.setNom(request.getParameter("nom"));
			etudiant.setPrenom(request.getParameter("prenom"));
			
			Etudiants mod = new Etudiants();
			mod.modifEtudiant(etudiant);
		}
		
		
		doGet(request, response);
	}

}
