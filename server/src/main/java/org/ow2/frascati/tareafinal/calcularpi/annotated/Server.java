/** 
  * Tarea Final: CalcularPi-RMI
  *
  * Author: Lina Salinas, Juan José Valencia, Jhon Edward Mora
*/
package org.ow2.frascati.tareafinal.calcularpi.annotated;

import org.osoa.sca.annotations.Property;
import org.osoa.sca.annotations.Init;
import org.osoa.sca.annotations.Reference;
import org.osoa.sca.annotations.Service;

import java.util.Random;

/**
 * The multiplication service implementation.
 */
public class Server implements PointGenerator, Runnable
{    
	@Property
	public String serveruri;

  private IBroker br;

  public final void setServeruri(String serveruri){
    this.serveruri = serveruri;
  }

  @Reference(name="comBroker")
  public final void setBrokerService(IBroker service)
  {
    this.br = service;
  }

    /**
     * Default constructor.
     */
    public Server()
    {
    }

    /**
     * Point generation process.
     */ 
    public final long generatePoints(long totalPoints, long seed) 
    {	
		  long puntoscirculo = 0;
      final long seedf = seed;
      final Random rnd = new Random(seedf);        

      //Generar los puntos
      for(int i=0; i<totalPoints; i++)
      {          
        //Generar los puntos
        double puntox = rnd.nextDouble();
        double puntoy = rnd.nextDouble();          
        
        //Verificar si el punto cumple con la ecuación del circulo 
        double radio = (puntox * puntox + puntoy * puntoy);
        if(radio <= 1)
        {
          puntoscirculo++;
        }       
      }
      return puntoscirculo;
    }

	//--------------------------------------------------------------------------
	// Implementation of the Runnable interface
	// --------------------------------------------------------------------------
	public final void run()
	{
    System.out.println("Attaching server to provided broker on URI: "+serveruri);
    br.attachServer(serveruri);
	}
}