package org.ow2.frascati.tareafinal.calcularpi.annotated;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.util.UUID;


public class GUI extends JFrame {

	private JPanel contentPane;
	private JTextField pointsTextF, seedTextF, nodesnumberTextF;	
	private JButton calculateBtton, loadFileBtton;

	//Creates the frame.
	public GUI() {
        
        //Organizar contentPane y titulo
		setTitle("Calculo de PI distribuido");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 150, 425, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(25, 25, 25, 25));
		setContentPane(contentPane);
		contentPane.setLayout(null);

        //Sección de semilla
		JLabel seedLabel = new JLabel("Poner semilla");
		seedLabel.setBounds(10, 20, 249, 14);
		contentPane.add(seedLabel);        
		seedTextF = new JTextField();
		seedTextF.setBounds(320, 20, 62, 20);
		
        contentPane.add(seedTextF);
		seedTextF.setColumns(10);

        //Sección de puntos
		JLabel pointsLabel = new JLabel("Poner numero de puntos");
		pointsLabel.setBounds(10, 50, 249, 14);
		contentPane.add(pointsLabel);
		pointsTextF = new JTextField();
		pointsTextF.setBounds(320, 50, 62, 20);
		
        contentPane.add(pointsTextF);
		pointsTextF.setColumns(10);

        //Sección de nodos
        JLabel nodesLabel = new JLabel("Numero de nodos de procesamiento");
		nodesLabel.setBounds(10, 80, 249, 14);
		contentPane.add(nodesLabel);
		nodesnumberTextF = new JTextField();		
		nodesnumberTextF.setBounds(320, 80, 62, 20);
		
        contentPane.add(nodesnumberTextF);
		nodesnumberTextF.setColumns(10);

		//Botón para evento de cálculo
        calculateBtton = new JButton("Calcular Pi");
		calculateBtton.setBounds(10, 110, 160, 23);
		contentPane.add(calculateBtton);

		loadFileBtton = new JButton("Cargar archivo");
		loadFileBtton.setBounds(180, 110, 160, 23);
		contentPane.add(loadFileBtton);
	}

	public JTextField getSeedTextField() {
		return seedTextF;
	}

	public JTextField getPointsTextField() {
		return pointsTextF;
	}

	public JTextField getNodesTextField() {
		return nodesnumberTextF;
	}

	public JButton getEventButton() {
		return calculateBtton;
	}

	public JButton getLoadFileButton() {
		return loadFileBtton;
	}
}