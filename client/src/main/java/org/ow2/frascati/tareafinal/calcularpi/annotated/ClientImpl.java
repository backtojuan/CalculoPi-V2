/** 
  * Tarea Final: calcularPi-RMI
  *
  * Author: Lina Salinas, Juan José Valencia, Jhon Edward Mora
*/
package org.ow2.frascati.tareafinal.calcularpi.annotated;

import org.osoa.sca.annotations.Init;
import org.osoa.sca.annotations.Reference;
import org.osoa.sca.annotations.Property;

import java.util.Scanner;
import java.util.Random;

import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ClientImpl
  implements Runnable,Exposer
{
  //--------------------------------------------------------------------------
  // SCA Reference
  // --------------------------------------------------------------------------
  
  @Property
	private String clienturi;
  
  private IBroker br;

  private PointReceiver calcBr;

  public final void setClienturi(String uri){
    clienturi = uri;
  }

  @Reference(name="comBroker")
  public final void setBrokerService(IBroker service)
  {
    this.br = service;
  }

  @Reference(name="calcBroker")
  public final void setCalcBrokerService(PointReceiver service){
    calcBr = service;
  }

  //GUI
  private static GUI gui;

  //--------------------------------------------------------------------------
  // Default constructor
  // --------------------------------------------------------------------------

  public ClientImpl()
  {
    System.out.println("CLIENT created");
  }


  public void expose(){
    System.out.println("client exposed");
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
      if(gui == null) 
      { //RMI Methods
        System.out.println("Attaching client on uri: "+clienturi);
        br.attachClient(clienturi);
        //GUI Methods
        gui = new GUI();
        gui.setLocationRelativeTo(null);
        gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gui.setVisible(true);
        calcBttonEvent();
        //loadFileBttonEvent();
      }      
    }			
    catch(Exception e) 
    {
      System.out.println("No se pudo lanzar la gui");
      e.printStackTrace();
    }
  }


  public void calcBttonEvent()
  {
    gui.getEventButton().addActionListener
    (
      new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
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
          
          //Asignar valores
          puntos = Long.parseLong(gui.getPointsTextField().getText().trim());
          seed = Long.parseLong(gui.getSeedTextField().getText().trim());

          //Botar resultados
          float pi = calcBr.receivePoints(puntos, seed, 1);
          String results = "Pi is "+pi;
          System.out.println(results);

          //Repetir el calculo 10 veces
          for(int j=0; j<10;j++)
          {
            //Tomar el tiempo justo antes de empezar la ejecución
            start = System.currentTimeMillis();

            //Generar los puntos
            
            
            //Pedir el resultado
             

            results += result + "\n";
              
            //Tomar el tiempo justo después de terminar el calculo
            end = System.currentTimeMillis();                
                        
            //Calcular el tiempo de ejecución
            averageTime += (end - start);

            //Reiniciar la variable contador de puntos de circulo
            puntoscirculo = 0;
          }          
        }
      }
    ); 
  }

  public void loadFileBttonEvent()
  {
    gui.getLoadFileButton().addActionListener
    (
      new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          try
          {
            Scanner reader = new Scanner(gui.OpenFile());
			
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
            String filePath = new File("").getAbsolutePath();		    
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


                //Pedir el resultado
                  
                
                //Tomar el tiempo justo después de terminar el calculo
                end = System.currentTimeMillis();
                
                //Anexar a la salida el resultado
                salida += result + ",";        
                                                
                //Calcular el tiempo de ejecución
                averageTime += (end - start);
                
                //Anexar a la salida el tiempo de respuesta y el numero de procesadores
                salida += averageTime + ",";        
                salida += 1 + "," + "\n";
                System.out.println(salida);
                //Reiniciar la variable contador de puntos de circulo
                puntoscirculo = 0;                            
              } 
              lineaActual++;
            }             
          }
          catch(FileNotFoundException fnfe)
          {
            System.out.println("No se puede encontrar el archivo que tiene la información");
            fnfe.printStackTrace();
          }	
          catch(IOException ioe)
          {
            ioe.printStackTrace();
          }
        }
      }      
    );
  }
}