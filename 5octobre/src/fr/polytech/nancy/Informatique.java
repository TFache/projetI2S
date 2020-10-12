package fr.polytech.nancy;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Informatique
 */
@WebServlet("/Informatique")
public class Informatique extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public Informatique() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = request.getParameter("nom"); String prenom = request.getParameter("prenom");
		request.setAttribute("nom", nom); request.setAttribute("prenom", prenom);
		this.getServletContext().getRequestDispatcher("/WEB-INF/informatique.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
