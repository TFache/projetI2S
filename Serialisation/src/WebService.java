import javax.jws.WebMethod;

@javax.jws.WebService(targetNamespace = "https://www.google.com")
public class WebService {

	@WebMethod(operationName = "nomMethode")
	public String afficher() {
		return "Service web : check !";
	}
}
