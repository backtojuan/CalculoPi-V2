/** 
  * Tarea Final: CalcularPi-RMI
  *
  * Author: Lina Salinas, Juan José Valencia
*/
package org.ow2.frascati.tareafinal.calcularpi.annotated;

import org.osoa.sca.annotations.Service;

@Service
public interface PointGenerator {
	
	public static final int FIRST = 1;
	public static final int SECOND = 2;
	public static final int THIRD = 3;
	public static final int FOURTH = 4;
	public long generatePoints(long totalPoints, long seed, int quadrant);
}