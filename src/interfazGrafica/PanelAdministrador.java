package interfazGrafica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import mundo.Administrador;

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
public class PanelAdministrador extends JPanel implements ActionListener
{
	 private InterfazInicio inicio;
	 private JButton botonverresultados;
	 private JButton botonasignarpermiso; 
	 private JButton botonbaseconoci;
     private Administrador adminactual;
     private int numeroimagenresolucion;

	public PanelAdministrador(InterfazInicio interfaz, Administrador actualadmin, int width, int heigth)
	{
		
		TitledBorder borde = BorderFactory.createTitledBorder("");
		borde.setTitleColor(Color.BLACK);
		setBorder (borde);
		setLayout( new GridBagLayout( ) );
		
		inicio=interfaz;
		adminactual = actualadmin;
		
		setPreferredSize(new Dimension (width-16,heigth-111));
		numeroimagenresolucion = 0;
		if(width == 800 && heigth == 600)
		{
			numeroimagenresolucion = 1; 
		}
		else if((width > 800 && width < 1024) && (heigth < 768 && heigth > 600))
		{
			numeroimagenresolucion = 1;
		}
		else if(width == 1024 && heigth == 768)
		{
			numeroimagenresolucion = 2;
		}
		else if((width > 1024 && width < 1152) && (heigth < 864 && heigth > 768))
		{
			numeroimagenresolucion = 2;
		}
		else if(width == 1152 && heigth == 864)
		{
			numeroimagenresolucion = 3;
		}
		else if((width > 1152 && width < 1280))
		{
			numeroimagenresolucion = 3;
		}
		else if(width == 1280 && heigth == 600)
		{
			numeroimagenresolucion = 4;
		}
		else if((width == 1280) && (heigth < 720 && heigth > 600))
		{
			numeroimagenresolucion = 4;
		}
		else if(width == 1280 && heigth == 720)
		{
			numeroimagenresolucion = 5;
		}
		else if((width == 1280) && (heigth < 768 && heigth > 720))
		{
			numeroimagenresolucion = 5;
		}
		else if(width == 1280 && heigth == 768)
		{
			numeroimagenresolucion = 6;
		}
		else if((width == 1280) && (heigth < 800 && heigth > 768))
		{
			numeroimagenresolucion = 6;
		}
		else if(width == 1280 && heigth == 800)
		{
			numeroimagenresolucion = 7;
		}
		else if((width == 1280) && (heigth < 960 && heigth > 800))
		{
			numeroimagenresolucion = 7;
		}
		else if(width == 1280 && heigth == 960)
		{
			numeroimagenresolucion = 8;
		}
		else if((width == 1280) && (heigth < 1024 && heigth > 960))
		{
			numeroimagenresolucion = 8;
		}
		else if(width == 1280 && heigth == 1024)
		{
			numeroimagenresolucion = 9;
		}
		else if((width > 1280 && width < 1360))
		{
			numeroimagenresolucion = 9;
		}
		else if(width == 1360 && heigth == 768)
		{
			numeroimagenresolucion = 10;
		}
		else if((width > 1360 && width < 1366) && (heigth == 768))
		{
			numeroimagenresolucion = 10;
		}
		else if(width == 1366 && heigth == 768)
		{
			numeroimagenresolucion = 11;
		}
		else if((width > 1366 && width < 1400) && (heigth < 1050 && heigth > 768))
		{
			numeroimagenresolucion = 11;
		}
		else if(width == 1400 && heigth == 1050)
		{
			numeroimagenresolucion = 12;
		}
		else if((width > 1400 && width < 1440))
		{
			numeroimagenresolucion = 12;
		}
		else if(width == 1440 && heigth == 900)
		{
			numeroimagenresolucion = 13;
		}
		else if((width > 1440 && width < 1600) && (heigth < 1200 && heigth > 900))
		{
			numeroimagenresolucion = 13;
		}
		else if(width == 1600 && heigth == 1200)
		{
			numeroimagenresolucion = 14;
		}
		else if((width > 1600 && width < 1680))
		{
			numeroimagenresolucion = 14;
		}
		else if(width == 1680 && heigth == 1050)
		{
			numeroimagenresolucion = 15;
		}
		else if((width > 1680 && width < 1900) && (heigth < 1200 && heigth > 1050))
		{
			numeroimagenresolucion = 15;
		}
		else if(width == 1920 && heigth == 1080)
		{
			numeroimagenresolucion = 16;
		}
		else if(width >= 1900 && heigth >= 1200)
		{
			numeroimagenresolucion = 17;
		}
		else
		{
			JOptionPane.showMessageDialog(this,"        La resolucion de pantalla que esta utilizando es muy grande para ejecutar el Sistema Experto"+"\n"+"Si es posible cierre el Sistema Experto y utilice una resolucion de pantalla de menor tamaño a la actual.","Advertencia", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/advertencia.PNG"));
		}
		
		botonverresultados = new JButton (new ImageIcon("./images/IconosInterfaz/BotonVerResultados.PNG"));
		botonasignarpermiso = new JButton (new ImageIcon("./images/IconosInterfaz/BotonAsignarPermiso.PNG"));
		botonbaseconoci = new JButton (new ImageIcon("./images/IconosInterfaz/BotonBaseConoci.PNG"));
		
		botonverresultados.setPreferredSize(new Dimension(249,57));
		botonasignarpermiso.setPreferredSize(new Dimension(249,57));
		botonbaseconoci.setPreferredSize(new Dimension(249,57));
		
		botonverresultados.setBackground(new Color(60,116,107));
		botonasignarpermiso.setBackground(new Color(60,116,107));
		botonbaseconoci.setBackground(new Color(60,116,107));
		
		botonverresultados.setForeground(new Color(194,236,218));
		botonasignarpermiso.setForeground(new Color(194,236,218));
		botonbaseconoci.setForeground(new Color(194,236,218));
		
		botonverresultados.setText("Ver Resultados");
		botonasignarpermiso.setText("Asignar Permiso");
		botonbaseconoci.setText("Base Conocimiento");
		
		botonverresultados.setFont(new Font( "dandelion in the spring", Font.TRUETYPE_FONT, 41 ));
		botonasignarpermiso.setFont(new Font( "dandelion in the spring", Font.TRUETYPE_FONT, 41 ));
		botonbaseconoci.setFont(new Font( "dandelion in the spring", Font.TRUETYPE_FONT, 36 ));
		
		int aumentoresolucion = ((width*710)/1024)-710;
		
		GridBagConstraints gbcverresultados = new GridBagConstraints( 0, 0, 0, 0, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( -550+50, 660+50+aumentoresolucion, 0, 0 ), 0, 0 );
		GridBagConstraints gbcasignarpermiso = new GridBagConstraints( 0, 0, 0, 0, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( -410+50, 660+50+aumentoresolucion, 0, 0 ), 0, 0 );
		GridBagConstraints gbcbaseconoci = new GridBagConstraints( 0, 0, 0, 0, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( -270+50, 660+50+aumentoresolucion, 0, 0 ), 0, 0 );
		
		botonverresultados.addActionListener(this);
		botonverresultados.setActionCommand("Ver Resultados");
		
		botonasignarpermiso.addActionListener(this);
		botonasignarpermiso.setActionCommand("Asignar Permiso");
		
		botonbaseconoci.addActionListener(this);
		botonbaseconoci.setActionCommand("Base del Conocimiento");
		
		botonverresultados.setToolTipText("Ver los Resultados de las Pruebas de los Candidatos.");
		botonasignarpermiso.setToolTipText("Asignar un Nuevo Permiso a un Candidato Para Presentar Prueba.");
		botonbaseconoci.setToolTipText("Abrir la Base del Conocimiento del Sistema Experto.");
        
        add( botonverresultados, gbcverresultados );
        add( botonasignarpermiso, gbcasignarpermiso );
        add( botonbaseconoci, gbcbaseconoci );
	}
	
    /**
     * Permite establecer el cronograma como imagen de fondo.
     * @param g
     */
	public void paintComponent(Graphics g) 
	{ 
		Dimension tamanio=getSize(); 
		ImageIcon imagenfondo=new ImageIcon("./images/SesionAdministrador/SesionAdministrador"+numeroimagenresolucion+".PNG");
		g.drawImage(imagenfondo.getImage(), 0, 0, tamanio.width,tamanio.height,null); 
		setOpaque(false); 
		super.paintComponent(g); 
	}

    /**
     * Ejecuta la acción que corresponde al los botones
     * @param evento Es el evento de pulsar el boton agregar - evento!=null
     */
	public void actionPerformed(ActionEvent evento) 
	{
		String clic = evento.getActionCommand();
		if (clic.equals("Ver Resultados"))
		{
			inicio.verSeleccionarResultadosRevisados();
		}
		if (clic.equals("Asignar Permiso"))
		{
			inicio.verAsignarPermiso(adminactual);
		}
		else if (clic.equals("Base del Conocimiento"))
		{
			inicio.verBaseConocimiento();
		}
	}	
}