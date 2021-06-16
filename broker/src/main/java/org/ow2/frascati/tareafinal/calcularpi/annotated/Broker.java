/** 
  * Tarea Final: CalcularPi-RMI
  *
  * Author: Lina Salinas, Juan José Valencia, Jhon Mora
*/
package org.ow2.frascati.tareafinal.calcularpi.annotated;

import org.osoa.sca.annotations.Service;
import org.osoa.sca.annotations.Reference;

/**
 * Broker class that will invoke processor nodes to generate points needed for
 * the Monte Carlo simulation This class implements the method calcPi, which
 * will receive the input parameters needed in the simulation and will start the
 * required servers.
 */
@Service
public class Broker implements IBroker {

	// Referenced servers
	private PointGenerator s1;
	/*private PointGenerator s2;
	private PointGenerator s3;
	private PointGenerator s4;
	private PointGenerator s5;
	private PointGenerator s6;
	private PointGenerator s7;
	private PointGenerator s8;*/
	
	@Reference
	public final void setServer1(PointGenerator service) {
		s1 = service;
	}
	

	/**
	 * Default constructor method, as needed by FraSCAti
	 */
	public Broker() {
		System.out.println("Broker started");
	}

	/**
	 * Starts the point generation process and calculates an approximation of Pi
	 * with obtained results from different servers.
	 * 
	 * @param totalPuntos the total points to be generated for the estimation
	 * @param seed        the seed to be used by the pseudorandom number generator
	 *                    for the process of point generation
	 * @param numNodos    total number of computational nodes to be used in the
	 *                    estimation
	 */
	public float calcPi(long totalPuntos, long seed, long numNodos) {
		long pointDivision = totalPuntos / numNodos;
		long found = 0;
		found += s1.generatePoints(pointDivision,seed,PointGenerator.FIRST);
		/*found += s2.generatePoints(pointDivision,seed,PointGenerator.SECOND);
		found += s3.generatePoints(pointDivision,seed,PointGenerator.THIRD);
		found += s4.generatePoints(Math.ceil(pointDivision),seed,PointGenerator.FOURTH);*/
		if(numNodos==8) {
			/*
			found += s5.generatePoints(pointDivision,seed,PointGenerator.FIRST);
			found += s6.generatePoints(pointDivision,seed,PointGenerator.SECOND);
			found += s7.generatePoints(pointDivision,seed,PointGenerator.THIRD);
			found += s8.generatePoints(pointDivision,seed,PointGenerator.FOURTH);*/
		}
		return 4*(found/totalPuntos);
	}
}
