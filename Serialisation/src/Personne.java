import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name="etudiant") //Pour changer le nom de l'entête ; @XmlRootElement est obligatoire
@XmlType(propOrder= {"numero","prenom","nom","date","famille"})//propOrder ordonne les attriuts dans le fichier XML ; n'inclue pas les éléments XmlTransient
public class Personne implements Serializable {


	private int numero;
	private String nom;
	private String prenom;
	private Date date;
	private List<Personne> famille;
	
	public Personne() {
		
	}
	

	
	public Personne(int numero, String nom, String prenom, Date date, List<Personne> famille) {
		super();
		this.numero = numero;
		this.nom = nom;
		this.prenom = prenom;
		this.date = date;
		this.famille = famille;
	}


	@XmlElement(name = "nom_de_famille", required=true)//name permet de renommer l'élément dans le fichier XML
	//Si XmlElement est présent, alors XmlValue ne peut pas l'être
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	@XmlElement(required=true)//required précise que l'élément est nécessaire
	@XmlID
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	//@XmlTransient //si tu veux que la variable ne soit pas affichée
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	@XmlAttribute(name="numero_etudiant") //XmlAttribute définit la variable en tant qu'attribut, name permet de la renommer dans le fichier XML
	public int getNumero() {
		return numero;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	
	
	public List<Personne> getFamille() {
		return famille;
	}



	public void setFamille(List<Personne> famille) {
		this.famille = famille;
	}



	public static void main(String[] args) throws Exception {
		
			JAXBContext jc = JAXBContext.newInstance(Personne.class);
			@SuppressWarnings("deprecation")
			Personne f = new Personne(0,"Fache","Déborah",new Date(98, 06, 14), null); 
			Personne p_1 = new Personne(1,"Fache","Thomas", new Date(99, 10, 19), new ArrayList<Personne>());
			p_1.getFamille().add(f);
			File xml = new File("resultat.xml");
			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(p_1, xml);
			
		
	}
}

