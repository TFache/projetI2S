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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchema;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


@XmlRootElement(name="etudiant", namespace="https://www.google.com") //Pour changer le nom de l'ent�te
//@XmlRootElement est obligatoire ; namespace d�finit un espace de noms
@XmlType(propOrder= {"numero","prenom","nom","date","famille"})//propOrder ordonne les attributs dans le fichier XML ; n'inclue pas les �l�ments XmlTransient
public class Personne implements Serializable {


	private int numero;
	private String nom;
	private String prenom;
	private Date date;
	private List<Object> famille;
	
	public Personne() {
		
	}
	
	
	public Personne(int numero, String nom, String prenom, Date date, List<Object> famille) {
		super();
		this.numero = numero;
		this.nom = nom;
		this.prenom = prenom;
		this.date = date;
		this.famille = famille;
	}


	@XmlElement(name = "nom_de_famille", required=true)//name permet de renommer l'�l�ment dans le fichier XML
	//Si XmlElement est pr�sent, alors XmlValue ne peut pas l'�tre
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	@XmlElement(required=true)//required pr�cise que l'�l�ment est n�cessaire
	@XmlID
	@XmlIDREF
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	//@XmlTransient //si tu veux que la variable ne soit pas affich�e
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@XmlAttribute(name="numero_etudiant") //XmlAttribute d�finit la variable en tant qu'attribut, name permet de la renommer dans le fichier XML
	public int getNumero() {
		return numero;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	
	@XmlElementWrapper(name="famille") //Permet de nommet l'ent�te d'une liste
	@XmlElements({//Sert � d�finir plus finement les �l�ments d'une liste
		//name nomme un �l�ment de la liste, type d�finit la classe de cet �l�ment
		@XmlElement(name="prenom", type=String.class),
		@XmlElement(name="ann�e_naissance", type=int.class)
	})
	public List<Object> getFamille() {
		return famille;
	}



	public void setFamille(List<Object> famille) {
		this.famille = famille;
	}



	public static void main(String[] args) throws Exception {
		
			JAXBContext jc = JAXBContext.newInstance(Personne.class);
			@SuppressWarnings("deprecation")
			Personne p_1 = new Personne(1,"Fache","Thomas", new Date(99, 10, 19), new ArrayList<Object>());
			p_1.getFamille().add((String) "D�borah");
			p_1.getFamille().add((int) 1998);
			File xml = new File("resultat.xml");
			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(p_1, xml);
			
		
	}
}

