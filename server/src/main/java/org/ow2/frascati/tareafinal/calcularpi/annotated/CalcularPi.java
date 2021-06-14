/** 
  * Tarea Final: CalcularPi-RMI
  *
  * Author: Lina Salinas, Juan José Valencia
*/
package org.ow2.frascati.tareafinal.calcularpi.annotated;

import org.osoa.sca.annotations.Service;

@Service
public interface CalcularPi{
  public float calcPi(long puntoscirculo, long puntostotales); 
}