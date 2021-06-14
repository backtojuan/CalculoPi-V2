/** 
  * Tarea Final: calcularPi-RMI
  *
  * Author: Lina Salinas, Juan José Valencia, Jhon Edward Mora
*/
package org.ow2.frascati.tareafinal.calcularpi.annotated;

import org.osoa.sca.annotations.Init;
import org.osoa.sca.annotations.Reference;

import java.util.Scanner;
import java.util.Random;

import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ClientImpl
  implements Runnable
{
  //--------------------------------------------------------------------------
  // SCA Reference
  // --------------------------------------------------------------------------

  private CalcularPi s;

  @Reference(name="calcPi")
  public final void setCalcularService(CalcularPi service)
  {
    this.s = service;
  }

  //--------------------------------------------------------------------------
  // Default constructor
  // --------------------------------------------------------------------------

  public ClientImpl()
  {
    System.out.println("CLIENT created");
  }

  @Init
  public final void init()
  {
    System.out.println("CLIENT initialized");
  }

  //--------------------------------------------------------------------------
  // Implementation of the Runnable interface
  // --------------------------------------------------------------------------

  public final void run()
  {
		System.out.println("Call the service...");   
    try
    {									
			//Buscar archivo
			String filePath = new File("").getAbsolutePath();						
			File file = new File(filePath + "/client/src/main/resources/test.csv");
			Scanner reader = new Scanner(file);
			
			//Variables de entrada 			
      long puntos = 0;
      long seed = 0;  

      //Variables de tiempo
      long start = 0;
      long end = 0;
      long averageTime = 0;

      //Variables de calculo
      float result = 0;
      long puntoscirculo = 0;      

      //Preparar archivo de salida
      String salida = "Resultado Pi, Tiempos respuesta(ms), Numero de nodos" + "\n";      
      File results = new File(filePath + "/client/src/main/resources/salida.csv");
      FileWriter fw = new FileWriter(results);
      BufferedWriter bw = new BufferedWriter(fw);

      Random rnd = new Random();
					
      //Leer la primera linea pero descartarla
			String headers = reader.nextLine();

      //Llevar conteo de linea del archivo
      int lineaActual = 0;

			//Calculo actual (5 calculos en total)						
			while(reader.hasNextLine())
      {	
        //Leer la linea
        String data = reader.nextLine();
			  String[] line = data.split(",");
								
			  //Asignar valores
        puntos = Long.parseLong(line[0]);
        seed = Long.parseLong(line[1]);
        rnd.setSeed(seed);

        //Repetir el calculo 10 veces
        for(int j=0; j<10;j++)
        {
          //Tomar el tiempo justo antes de empezar la ejecución
          start = System.currentTimeMillis();

          //Generar los puntos
          for(int i=0; i<puntos; i++){          
            //Generar los puntos
            double puntox = rnd.nextDouble();
            double puntoy = rnd.nextDouble();          
        
            //Verificar si el punto cumple con la ecuación del circulo 
            double radio = (puntox * puntox + puntoy * puntoy);
            if(radio <= 1){
              puntoscirculo++;
            }                     
          }    

          //Pedir el resultado
          result = s.calcPi(puntoscirculo,puntos);  
          
          //Tomar el tiempo justo después de terminar el calculo
          end = System.currentTimeMillis();
          
          //Anexar a la salida el resultado
          salida += result + ",";        
          System.out.println(result);
          
          
          //Calcular el tiempo de ejecución
          averageTime += (end - start);
          
          //Anexar a la salida el tiempo de respuesta y el numero de procesadores
          salida += averageTime + ",";        
          salida += 1 + "," + "\n";
          System.out.println(salida);
          //Reiniciar la variable contador de puntos de circulo
          puntoscirculo = 0;  
                       
        }

        System.out.println(averageTime);
        System.out.println();        
        lineaActual++;
			}             
		}
		catch(FileNotFoundException fnfe){
			System.out.println("No se puede encontrar el archivo que tiene la información");
			fnfe.printStackTrace();
		}	
    catch(IOException ioe){
      ioe.printStackTrace();
    }
  }
}