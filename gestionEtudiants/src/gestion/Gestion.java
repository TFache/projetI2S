package gestion;

import java.util.ArrayList;

import javax.jws.WebService;

import etudiants.*;

@WebService
public class Gestion {

	public ListeEtudiants liste;
	
	public Gestion() {
		this.liste = new ListeEtudiants();
	}
	
	public void ajouter(String nom, String prenom, String email) {
		this.liste.ajouterEtudiant(nom, prenom, email);
	}
	
	public ArrayList<Etudiant> toutAfficher() {
		return this.liste.afficherTous();
	}
	
	public void suppression(int id) {
		this.liste.supprimerEtudiant(id);
	}
	
	public void modifier(int id, String param, String newVal) {
		this.liste.modifierEtudiant(id, param, newVal);
	}
	
	public Etudiant consulter(int id) {
		return this.liste.consulterEtudiant(id);
	}
}
