package projet.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InscriptionSV
 */
@WebServlet("/InscriptionSV")
public class InscriptionSV extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected Authentification authentification = new Authentification();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InscriptionSV() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//si inscription réussie, redirection, sinon rien ne se passe
		if(authentification.isInscription()) response.sendRedirect("login");
		else this.getServletContext().getRequestDispatcher("/WEB-INF/Inscription/inscription.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login_insc = request.getParameter("login_insc");
		request.setAttribute("login_insc", login_insc);

		String password_insc = request.getParameter("password_insc");
		request.setAttribute("password_insc", password_insc);
		
		
		authentification.inscription(request);
		request.setAttribute("authentification", authentification);
		
		
		doGet(request, response);
	}

}
