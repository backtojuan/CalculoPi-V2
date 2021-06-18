/** 
  * Tarea Final: CalcularPi-RMI
  *
  * Author: Lina Salinas, Juan José Valencia
*/
package org.ow2.frascati.tareafinal.calcularpi.annotated;

public class Thred extends Thread {

	private long seed;
	private long totalPoints;
	private PointGenerator pg;

	private long pointsInside = 0;	

	public Thred (PointGenerator pg, long seed, long totalPoints) {
		super();
		this.pg = pg;
 		this.seed = seed;
		this.totalPoints = totalPoints;
	}

	public void run() {
		pointsInside = pg.generatePoints(totalPoints, seed);
		System.out.println("Thread Finished and counted "+pointsInside);
	}

	public long getPointsInside() {
		return pointsInside;
	}
}
