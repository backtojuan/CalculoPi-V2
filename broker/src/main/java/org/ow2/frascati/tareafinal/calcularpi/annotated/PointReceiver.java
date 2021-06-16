/** 
  * Tarea Final: CalcularPi-RMI
  *
  * Author: Lina Salinas, Juan José Valencia
*/
package org.ow2.frascati.tareafinal.calcularpi.annotated;
import org.osoa.sca.annotations.Service;

//Recibir puntos e información del cliente
@Service
public interface PointReceiver <T extends Comparable<? super T>> extends Remote{
    public float receivePoints(long totalPuntos, long seed, long numNodos);
}