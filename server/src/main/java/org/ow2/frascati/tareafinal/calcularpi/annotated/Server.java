/** 
  * Tarea Final: CalcularPi-RMI
  *
  * Author: Lina Salinas, Juan José Valencia, Jhon Edward Mora
*/
package org.ow2.frascati.tareafinal.calcularpi.annotated;

import org.osoa.sca.annotations.Property;
import java.util.Random;
/**
 * The multiplication service implementation.
 */
public class Server implements PointGenerator
{    
    /**
     * Default constructor.
     */
    public Server()
    {
        System.out.println("SERVER created.");
    }

    /**
     * Point generation process.
     */ 
    public long generatePoints(long totalPoints, long seed, int quadrant) {
    	//Create pseudo randomizer with given seed
    	Random rnd = new Random(seed);
    	
    	//Create return variable
    	long ret = 0;
    	
    	//Create multipliers that will modify result of rnd.nextDouble depending on given quadrant.
    	int xMultiplier = 1;
    	int yMultiplier = 1;
    	if(quadrant == FIRST) {
    		xMultiplier = 1;
    		yMultiplier = 1;
    	}else if(quadrant == SECOND) {
    		xMultiplier = 1;
    		yMultiplier = -1;
    	}else if(quadrant == THIRD) {
    		xMultiplier = -1;
    		yMultiplier = -1;
    	}else if(quadrant == FOURTH){
    		xMultiplier = -1;
    		yMultiplier = 1;
    	}
    	
    	//Iterate until total points is finished
    	for(int i = 0; i<totalPoints; i++) {
    		//Create a random x coordinate
    		double x = rnd.nextDouble()*xMultiplier;
    		
    		//Craeate a random y coordinate
    		double y = rnd.nextDouble()*yMultiplier;
    		
    		//Verify if given coordinate is inside the circle of radius 1
    		if((x*x)+(y*y) <= 1) {
    			ret++;
    		}
    	}
    	
    	return ret;
    }
}