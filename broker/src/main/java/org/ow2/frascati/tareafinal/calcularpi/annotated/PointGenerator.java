/** 
  * Tarea Final: CalcularPi-RMI
  *
  * Author: Lina Salinas, Juan José Valencia
*/
package org.ow2.frascati.tareafinal.calcularpi.annotated;

import java.rmi.Remote;
import java.rmi.RemoteException;
import org.osoa.sca.annotations.Service;

//Generar puntos por el servidor
@Service
public interface PointGenerator<T extends Comparable<? super T>> extends Remote{
	public long generatePoints(long totalPoints, long seed);
}