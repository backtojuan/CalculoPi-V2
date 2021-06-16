/** 
  * Tarea Final: CalcularPi-RMI
  *
  * Author: Lina Salinas, Juan José Valencia
*/
package org.ow2.frascati.tareafinal.calcularpi.annotated;
import org.osoa.sca.annotations.Service;

//Recibir resultado
@Service
public interface CalculatePi{
    public void getResult(long pi);
}