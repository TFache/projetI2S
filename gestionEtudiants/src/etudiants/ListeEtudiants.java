package etudiants;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ListeEtudiants implements Serializable{

	private ArrayList<Etudiant> liste = new ArrayList<Etudiant>();

	public ListeEtudiants() {
		super();
	}

	public ListeEtudiants(ArrayList<Etudiant> liste) {
		super();
		this.liste = liste;
	}

	@Override
	public String toString() {
		return "Liste des étudiants :" + liste;
	}

	public void ajouterEtudiant(String nom, String prenom, String email) {
		this.liste.add(new Etudiant(nom, prenom, email));
	}
	
	public ArrayList<Etudiant> afficherTous() {
		return this.liste;
	}
	
	public void supprimerEtudiant(int id) {
		
			liste.remove(id);
	}
	
	public void modifierEtudiant(int id, String param, String newVal) {
		if(param.equals("nom")) {
			this.liste.set(id, new Etudiant(newVal, this.liste.get(id).getPrenom(), this.liste.get(id).getEmail()));
		}
		if(param.equals("prenom")) {
			this.liste.set(id, new Etudiant(this.liste.get(id).getNom(), newVal, this.liste.get(id).getEmail()));
		}
		if(param.equals("email")) {
			this.liste.set(id, new Etudiant(this.liste.get(id).getNom(), this.liste.get(id).getPrenom(), newVal));
		}
	}
	
	public Etudiant consulterEtudiant(int id) {
		return liste.get(id);
	}
}
