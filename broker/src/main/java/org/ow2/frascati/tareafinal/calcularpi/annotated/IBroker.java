package org.ow2.frascati.tareafinal.calcularpi.annotated;
import org.osoa.sca.annotations.Service;

/**
 * Interfaz para utilizar el componente Broker en FraSCAti
 */
@Service
public interface IBroker {
    public float calcPi(long totalPuntos, long seed, long numNodos);
}
