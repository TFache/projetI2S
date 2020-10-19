package publication;

import javax.xml.ws.Endpoint;

import gestion.Gestion;



public class Publication {

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8989/", new Gestion()); 
		System.out.println("http://localhost:8989/?wsdl");

	}

}
