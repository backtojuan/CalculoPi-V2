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

import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

  private static GUI gui;

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
      if(gui == null) 
      {
        gui = new GUI();
        gui.setLocationRelativeTo(null);
        gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gui.setVisible(true);        
      }      
    }			
    catch(Exception e) 
    {
      System.out.println("No se pudo lanzar la interfaz grafica");
      e.printStackTrace();
    }
  }
}