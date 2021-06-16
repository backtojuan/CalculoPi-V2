/** 
  * Tarea Final: CalcularPi-RMI
  *
  * Author: Lina Salinas, Juan José Valencia, Jhon Mora
*/
package org.ow2.frascati.tareafinal.calcularpi.annotated;

import org.osoa.sca.annotations.Service;
import org.osoa.sca.annotations.Reference;

import java.rmi.Naming;
import java.util.LinkedList;

/**
 * Broker class that will invoke processor nodes to generate points needed for
 * the Monte Carlo simulation.
 */
@Service
public class Broker implements IBroker{
	
	//Total points inside the circle found by the nodes
	private long found;

	//Clients and Servers Uris
	private static LinkedList<String> clientUris=new LinkedList();
	private static LinkedList<String> serverUris=new LinkedList();

	//Servers and Clients list
	LinkedList<Server> servers = new LinkedList<Server>();
	LinkedList<Client> clients = new LinkedList<ClientImpl>();

	/**
	 * Default constructor method, as needed by FraSCAti
	 */
	public Broker() {
		System.out.println("Broker started");
	}

	//Subscribe client to broker
	public synchronized void attachClient(String clientUri) {
		try {
            Client client = (ClientImpl) Naming.lookup(clientUri);
            System.out.println("Conectado nuevo cliente :" + clientUri);
        } catch (Exception e) {
            System.out.println("error al hacer binding con : " + clientUri);
            e.printStackTrace();
        }
	}
	
	//Unsubscribe client from broker
	public synchronized void detachClient(String clientUri) {
		int index = clientUris.indexOf(clientUri);
		String uriRemoved=clientUris.remove(index);
		clients.remove(index);		
		assert(clientUri.equals(uriRemoved));		
	}

	//Subscribe server to broker
	public synchronized void attachServer(String serverUri) {
		try {
            Server sever =(Server) Naming.lookup(serverUri);
            System.out.println("Conectado nuevo server :" + serverUri);
        } catch (Exception e) {
            System.out.println("error al hacer binding con : " + serverUri);
            e.printStackTrace();
        }
	}
	
	//Unsubscribe server from broker
	public synchronized void detachServer(String serverUri) {
		int index = serverUris.indexOf(serverUri);
		String uriRemoved=serverUris.remove(index);
		servers.remove(index);	
		assert(serverUri.equals(uriRemoved));				
	}

	public synchronized void getPoints(long resultpoints){
		this.found += found;
	}
}
