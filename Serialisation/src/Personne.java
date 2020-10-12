import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="etudiant") //Pour changer le nom de l'entête ; @XmlRootElement est obligatoire
public class Personne implements Serializable {

	private String nom;
	private String prenom;
	private Date date;
	
	public Personne() {
		
	}
	public Personne(String nom, String prenom, Date date) {
		this.nom = nom;
		this.prenom=prenom;
		this.date = date;
	}

	@XmlAttribute //Pour définir la variable en tant qu'attribut
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
	@XmlTransient //si tu veux que la variable ne soit pas affichée
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	public static void main(String[] args) throws Exception {
		
			JAXBContext jc = JAXBContext.newInstance(Personne.class);
			@SuppressWarnings("deprecation")
			Personne p_1 = new Personne("Fache","Thomas", new Date(1999, 11, 19));
			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(p_1, new File("resultat.xml"));
			
		
	}
}

