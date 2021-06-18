/** 
  * Tarea Final: CalcularPi-RMI
  *
  * Author: Lina Salinas, Juan José Valencia, Jhon Mora
*/
package org.ow2.frascati.tareafinal.calcularpi.annotated;

import java.rmi.Naming;

import java.util.LinkedList;
import java.util.ArrayList;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Broker class that will invoke processor nodes to generate points needed for
 * the Monte Carlo simulation.
 */
public class Broker implements IBroker, PointReceiver, Runnable {
	
	//Clients and Servers Uris
	private static LinkedList<String> clientUris=new LinkedList();
	private static LinkedList<String> serverUris=new LinkedList();

	//Servers and Clients list
	private static LinkedList<PointGenerator> servers = new LinkedList<PointGenerator>();
	private static LinkedList<Exposer> clients = new LinkedList<Exposer>();

	//Total points inside the circle found by the nodes
	private long pointsInside;

	//Cntrolar hilos
	private Thred thread;
	private final static long blocksize = 100000000;

	/**
	 * Default constructor method, as needed by FraSCAti
	 */
	public Broker() {
	}

	//Subscribe client to broker
	public synchronized void attachClient(String clientUri) {
		try {
			System.out.println("Attaching new client with URI: " + clientUri);
            Exposer client = (Exposer) Naming.lookup(clientUri);
			clients.add(client);
        } catch (Exception e) {
            System.out.println("Error when binding with client : " + clientUri);
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
			System.out.println("Attaching new server with URI: " + serverUri);
			PointGenerator server = (PointGenerator) Naming.lookup(serverUri);
			servers.add(server);
        } catch (Exception e) {
            System.out.println("Error on binding with server with uri: " + serverUri);
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

	//Recibir puntos del cliente y mandar a los servidores
	@Override
	public float receivePoints(long totalPuntos, long seed, long numNodos){		
		System.out.println("Running a total of " + servers.size()+" servers.");
		while(totalPuntos>0){			
			ArrayList<Thred> threads = new ArrayList<Thred>();				
			ExecutorService executor = Executors.newFixedThreadPool(servers.size());	
			// Por cada servidor attacheado empezar thread
			for(final PointGenerator server : servers){
				if(totalPuntos>0){
					long points = blocksize> totalPuntos ? totalPuntos:blocksize;
					thread = new Thred(server, seed, totalPuntos);
					threads.add(thread);
					executor.execute(thread);
					totalPuntos-=points;
				}
				else{
					break;
				}
			}	
			executor.shutdown();
			while(!executor.isTerminated());

			for(Thred t : threads){
				pointsInside += t.getPointsInside();
			}
		}
		float pi = ((float) pointsInside)/totalPuntos;                
        pi = 4*pi;
		return pi;
	}

	@Override
	public void run(){
		System.out.println("Broker is running");
	}
}