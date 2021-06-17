/** 
  * Tarea Final: CalcularPi-RMI
  *
  * Author: Lina Salinas, Juan José Valencia
*/
package org.ow2.frascati.tareafinal.calcularpi.annotated;

import org.osoa.sca.annotations.Service;

//Generar puntos por el servidor (el broker pasa los puntos y la semilla al servidor)
@Service
public interface PointGenerator{
	public long generatePoints(long totalPoints, long seed);
}