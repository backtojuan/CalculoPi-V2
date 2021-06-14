/** 
  * Tarea Final: CalcularPi-RMI
  *
  * Author: Lina Salinas, Juan José Valencia, Jhon Edward Mora
*/
package org.ow2.frascati.tareafinal.calcularpi.annotated;

import org.osoa.sca.annotations.Property;
/**
 * The multiplication service implementation.
 */
public class Server implements CalcularPi
{    
    /**
     * Default constructor.
     */
    public Server()
    {
        System.out.println("SERVER created.");
    }

    /**
     * calcPi implementation.
     */ 
    public final float calcPi(long puntoscirculo, long puntostotales) 
    {
        float pi = ((float) puntoscirculo)/puntostotales;                
        pi = 4*pi;                        
        return pi;
    }
}