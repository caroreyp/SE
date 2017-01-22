package interfazGrafica;

import java.awt.Color;
import java.awt.Dimension;
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
import java.awt.Font;

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
public class PanelConocimiento extends JPanel implements ActionListener
{
     private InterfazInicio inicio;
     private JButton botonroles;
     private JButton botonpruebas;
     private JButton botonpreguntas;
     private JButton botonescalas;
     private JButton botoncompetencias;
     private JButton botonreglas;
     private int numeroimagenresolucion;
     
	public PanelConocimiento(InterfazInicio interfaz, int width, int heigth)
	{
		inicio=interfaz;
		TitledBorder borde = BorderFactory.createTitledBorder("");
		borde.setTitleColor(Color.BLACK);
		setBorder (borde);
		setLayout( new GridBagLayout( ) );
		
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
		
		botonroles = new JButton (new ImageIcon("./images/IconosInterfaz/BotonRoles.PNG"));
		botonpruebas = new JButton (new ImageIcon("./images/IconosInterfaz/BotonPruebas.PNG"));
		botonpreguntas = new JButton (new ImageIcon("./images/IconosInterfaz/BotonPreguntas.PNG"));
		botonescalas = new JButton (new ImageIcon("./images/IconosInterfaz/BotonEscalas.PNG"));
		botoncompetencias = new JButton (new ImageIcon("./images/IconosInterfaz/BotonCompetencias.PNG"));
		botonreglas = new JButton (new ImageIcon("./images/IconosInterfaz/BotonReglas.PNG"));
		
		botonroles.setPreferredSize(new Dimension(231,58));
		botonpruebas.setPreferredSize(new Dimension(231,58));
		botonpreguntas.setPreferredSize(new Dimension(231,58));
		
		botonescalas.setPreferredSize(new Dimension(231,58));
		botoncompetencias.setPreferredSize(new Dimension(231,58));
		botonreglas.setPreferredSize(new Dimension(231,58));
		
		botonroles.setBackground(new Color(152,175,133));
		botonpruebas.setBackground(new Color(152,175,133));
		botonpreguntas.setBackground(new Color(152,175,133));
		
		botonescalas.setBackground(new Color(152,175,133));
		botoncompetencias.setBackground(new Color(152,175,133));
		botonreglas.setBackground(new Color(152,175,133));
		
		botonroles.setForeground(new Color(255,255,213));
		botonpruebas.setForeground(new Color(255,255,213));
		botonpreguntas.setForeground(new Color(255,255,213));
		
		botonescalas.setForeground(new Color(255,255,213));
		botoncompetencias.setForeground(new Color(255,255,213));
		botonreglas.setForeground(new Color(255,255,213));
		
		botonroles.setText("Roles");
		botonpruebas.setText("Pruebas");
		botonpreguntas.setText("Preguntas");
		
		botonescalas.setText("Escalas");
		botoncompetencias.setText("Competencias");
		botonreglas.setText("Reglas");
		
		botonroles.setFont(new Font( "dandelion in the spring", Font.TRUETYPE_FONT, 45 ));
		botonpruebas.setFont(new Font( "dandelion in the spring", Font.TRUETYPE_FONT, 45 ));
		botonpreguntas.setFont(new Font( "dandelion in the spring", Font.TRUETYPE_FONT, 45 ));
		
		botonescalas.setFont(new Font( "dandelion in the spring", Font.TRUETYPE_FONT, 45 ));
		botoncompetencias.setFont(new Font( "dandelion in the spring", Font.TRUETYPE_FONT, 44 ));
		botonreglas.setFont(new Font( "dandelion in the spring", Font.TRUETYPE_FONT, 45 ));
		
		int aumentoresolucion = ((width*710)/1024)-710;
		
		GridBagConstraints gbcroles = new GridBagConstraints( 0, 0, 0, 0, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( -500+50, 150+50+aumentoresolucion, 0, 0 ), 0, 0 );
		GridBagConstraints gbcpruebas = new GridBagConstraints( 0, 0, 0, 0, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( -350+50, 150+50+aumentoresolucion, 0, 0 ), 0, 0 );
		GridBagConstraints gbcpreguntas = new GridBagConstraints( 0, 0, 0, 0, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( -200+50, 150+50+aumentoresolucion, 0, 0 ), 0, 0 );
		
		GridBagConstraints gbcescalas = new GridBagConstraints( 0, 0, 0, 0, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( -500+50, 660+50+aumentoresolucion, 0, 0 ), 0, 0 );
		GridBagConstraints gbccompetencias = new GridBagConstraints( 0, 0, 0, 0, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( -350+50, 660+50+aumentoresolucion, 0, 0 ), 0, 0 );
		GridBagConstraints gbcreglas = new GridBagConstraints( 0, 0, 0, 0, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( -200+50, 660+50+aumentoresolucion, 0, 0 ), 0, 0 );

		botonroles.addActionListener(this);
		botonroles.setActionCommand("Roles");
		
		botonpruebas.addActionListener(this);
		botonpruebas.setActionCommand("Pruebas");
		
		botonpreguntas.addActionListener(this);
		botonpreguntas.setActionCommand("Preguntas");
		
		botonescalas.addActionListener(this);
		botonescalas.setActionCommand("Escalas");
		
		botoncompetencias.addActionListener(this);
		botoncompetencias.setActionCommand("Competencias");
		
		botonreglas.addActionListener(this);
		botonreglas.setActionCommand("Reglas");
		
		botonroles.setToolTipText("Administrar las Roles.");
		botonpruebas.setToolTipText("Administrar las Pruebas.");
		botonpreguntas.setToolTipText("Administrar las Preguntas.");
        
		botonescalas.setToolTipText("Administrar las Escalas.");
		botoncompetencias.setToolTipText("Administrar las Competencias.");
		botonreglas.setToolTipText("Administrar las Reglas.");
		
        add( botonroles, gbcroles );
        add( botonpruebas, gbcpruebas );
        add( botonpreguntas, gbcpreguntas );
        add( botonescalas, gbcescalas );
        add( botoncompetencias, gbccompetencias );
        add( botonreglas, gbcreglas );
	}
	
    /**
     * Permite establecer el cronograma como imagen de fondo.
     * @param g
     */
	public void paintComponent(Graphics g) 
	{ 
		Dimension tamanio=getSize(); 
		ImageIcon imagenfondo=new ImageIcon("./images/BaseConocimiento/BaseConocimiento"+numeroimagenresolucion+".PNG");
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
		if (clic.equals("Roles"))
		{
			inicio.verSeleccionarAccion("Rol");
		}
		if (clic.equals("Pruebas"))
		{
			inicio.verSeleccionarAccion("Prueba");
		}
		if (clic.equals("Preguntas"))
		{
			inicio.verSeleccionarAccion("Pregunta");
		}
		if (clic.equals("Escalas"))
		{
			inicio.verSeleccionarAccion("Escala");
		}
		if (clic.equals("Competencias"))
		{
			inicio.verSeleccionarAccion("Competencia");
		}
		if (clic.equals("Reglas"))
		{
			inicio.verSeleccionarAccion("Regla");
		}
	}	
}