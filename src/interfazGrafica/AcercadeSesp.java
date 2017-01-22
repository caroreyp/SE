package interfazGrafica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Universidad Pedagogica y Tecnologica de Colombia
 * Facultad de Ingenieria
 * Escuela de Ingenieria de Sistemas y Computacion
 * Grupo de Investigacion de Software
 * Sistema Experto para la Seleccion de Personal
 * Caso de Estudio: Desarrollo de Software
 * @author Hilda Carolina Rey Pizza
 * Trabajo de Grado
 * 2012
 */
public class AcercadeSesp extends JDialog
{
	private JLabel imagen;
	
	public AcercadeSesp(InterfazInicio interfaz)
	{
		super(interfaz, true);
		setLayout(new FlowLayout());
        setTitle("Acerca de - SESP");
		setResizable(false);
		setDefaultCloseOperation(2);
		int anchoventana = 925;
		int altoventana =645;
		setBounds((interfaz.getWidthvetana()/2)-(anchoventana/2),(interfaz.getHeigthvetana()/2)-(altoventana/2),anchoventana,altoventana);
		
		ImageIcon icono =new ImageIcon("./images/IconosInterfaz/IconoVentana.PNG"); 
		Image imagenicono=icono.getImage();
		this.setIconImage(imagenicono);
		
		imagen = new JLabel(new ImageIcon("./images/IconosInterfaz/acercade.PNG"));
		
		JTextArea texto = new JTextArea(""+"\n"+
				                        "ACERCA DE ..."+"\n"+
				                        ""+"\n"+
				                        "UNIVERSIDAD  PEDAGOGICA  Y  TECNOLOGICA  DE  COLOMBIA"+"\n"+
				                        "FACULTAD  DE  INGENIERIA"+"\n"+
				                        "ESCUELA  DE  INGENIERIA  DE  SISTEMAS  Y  COMPUTACION"+"\n"+
				                        "GRUPO  DE  INVESTIGACION  EN  SOFTWARE"+"\n"+
				                        ""+"\n"+
				                        "SISTEMA EXPERTO PARA LA SELECCION DE PERSONAL"+"\n"+
				                        "CASO DE ESTUDIO: DESARROLLO DE SOFTWARE"+"\n"+
				                        ""+"\n"+
				                        "                                                                                                                  Trabajo de Grado"+"\n"+
				                        "                                                                                                                            Version 2.5"+"\n"+
				                        "Hilda Carolina Rey Pizza"+"\n"+
				                        "Director de Proyecto: Ing. Javier Ballesteros Ricaute");
		texto.setBackground(new Color(97,93,92));
		texto.setForeground(new Color(255,255,255));
		texto.setEditable(false);
		texto.setFont(new Font( "dandelion in the spring", Font.TRUETYPE_FONT, 36 ));
		
		JPanel panelcolor = new JPanel(new FlowLayout());
		panelcolor.setPreferredSize(new Dimension (923,607));
		panelcolor.setBackground(new Color(97,93,92));
		
		panelcolor.add(imagen);
		panelcolor.add(texto);
		add(panelcolor);
	}
}