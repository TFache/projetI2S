import javax.jws.WebMethod;

@javax.jws.WebService(targetNamespace = "https://play.pokemonshowdown.com")
public class WebService {

	@WebMethod(operationName = "nomMethode")
	public String afficher() {
		return "Service web : check !";
	}
}
