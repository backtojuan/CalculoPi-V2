/** 
  * Tarea Final: CalcularPi-RMI
  *
  * Author: Lina Salinas, Juan José Valencia
*/
package org.ow2.frascati.tareafinal.calcularpi.annotated;

import org.osoa.sca.annotations.Service;

@Service
public interface IBroker
  {
    public void getPoints(long resultpoints);
    public void attachClient(String clienturi);
    public void detachClient(String clienturi);
    public void attachServer(String serveruri);
    public void detachServer(String serveruri);
  }