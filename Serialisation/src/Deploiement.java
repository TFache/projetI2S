import javax.xml.ws.Endpoint;

public class Deploiement {

	
	public static void main(String[] args) {
		
		
		String url = "http://localhost:8989/";
		Endpoint.publish(url, new WebService());
		
		System.out.println("Service d�ploy� : WSDL (Web Service Description Language) accessible � :");
		System.out.println("http://localhost:8989/?wwsdl");
	}

}
