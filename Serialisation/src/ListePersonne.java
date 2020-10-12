import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ListePersonne implements Serializable {

	private int nombre;
	private String nom;
	private String prenom;
	public ListePersonne() {
		
	}
	public ListePersonne(int nombre, String nom, String prenom) {
		super();
		this.nombre = nombre;
		this.nom = nom;
		this.prenom = prenom;
	}
	public int getNombre() {
		return nombre;
	}
	public void setNombre(int nombre) {
		this.nombre = nombre;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	
	public static void main(String[] args) throws Exception {
		JAXBContext jc = JAXBContext.newInstance(ListePersonne.class);
		ArrayList<ListePersonne> p = new ArrayList<ListePersonne>();
		p.add(new ListePersonne(1, "Fache", "Thomas"));
		p.add(new ListePersonne(1, "Fache", "Déborah"));
		
	}
}
