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
public class VerDescripcion extends JDialog
{
	private JLabel imagen;
	
	public VerDescripcion(InterfazInicio interfaz)
	{
		super(interfaz, true);
		setLayout(new FlowLayout());
        setTitle("Descripcion Base de Conocimiento - SESP");
		setResizable(false);
		setDefaultCloseOperation(2);
		int anchoventana = 925;
		int altoventana =680;
		setBounds((interfaz.getWidthvetana()/2)-(anchoventana/2),(interfaz.getHeigthvetana()/2)-(altoventana/2),anchoventana,altoventana);
		
		ImageIcon icono =new ImageIcon("./images/IconosInterfaz/IconoVentana.PNG"); 
		Image imagenicono=icono.getImage();
		this.setIconImage(imagenicono);
		
		imagen = new JLabel(new ImageIcon("./images/IconosInterfaz/descripcion.PNG"));
		
		JTextArea texto = new JTextArea(""+"\n"+
                "          DESCRIPCION  BASE  DEL  CONOCIMIENTO"+"\n"+
                ""+"\n"+
                "Una base de conocimiento es un repositorio centralizado de informacion"+"\n"+
                "Se utiliza para optimizar la recoleccion de datos y organizacion de "+"\n"+
                "estos para fines especificos."+"\n"+
                "Una base de conocimiento bien organizada puede ahorrar dinero y "+"\n"+
                "gastos innecesarios en recursos, al disminuir la cantidad de tiempo"+"\n"+
                "empleado dedicado a tratar de manejar informacion."+"\n"+
                "No es una coleccion estatica de informacion, sino un recurso dinamico"+"\n"+
                "que en si tiene la capacidad de aprender y ampliar sus"+"\n"+
                "conocimientos de acuerdo a las necesidades que se"+"\n"+
                "van planteando en el periodo de vida del software.");
		texto.setBackground(new Color(97,93,92));
		texto.setForeground(new Color(255,255,255));
		texto.setEditable(false);
		texto.setFont(new Font( "dandelion in the spring", Font.TRUETYPE_FONT, 42 ));
		
		JPanel panelcolor = new JPanel(new FlowLayout());
		panelcolor.setPreferredSize(new Dimension (923,642));
		panelcolor.setBackground(new Color(97,93,92));
		
		panelcolor.add(imagen);
		panelcolor.add(texto);
		add(panelcolor);
	}
}