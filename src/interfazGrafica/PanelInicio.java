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
public class PanelInicio extends JPanel implements ActionListener
{
    private InterfazInicio inicio;
   	private JButton botonsesionadmin;
    private JButton botonregistrar;
    private JButton botonsesioncandidato;
    private int numeroimagenresolucion;

	public PanelInicio(InterfazInicio interfaz, int width, int heigth)
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
		
		botonsesionadmin = new JButton (new ImageIcon("./images/IconosInterfaz/BotonSesionAdministrador.PNG"));
		botonregistrar = new JButton (new ImageIcon("./images/IconosInterfaz/BotonRegistrarCandidato.PNG"));
		botonsesioncandidato = new JButton (new ImageIcon("./images/IconosInterfaz/BotonSesionCandidato.PNG"));
		
		botonsesionadmin.setPreferredSize(new Dimension(243,57));
		botonregistrar.setPreferredSize(new Dimension(243,57));
		botonsesioncandidato.setPreferredSize(new Dimension(243,57));
		
		botonsesionadmin.setBackground(new Color(68,146,132));
		botonregistrar.setBackground(new Color(68,146,132));
		botonsesioncandidato.setBackground(new Color(68,146,132));
		
		botonsesionadmin.setForeground(new Color(218,233,129));
		botonregistrar.setForeground(new Color(218,233,129));
		botonsesioncandidato.setForeground(new Color(218,233,129));
		
		botonsesionadmin.setText("Sesion Administrador");
		botonregistrar.setText("Registrar Candidato");
		botonsesioncandidato.setText("Sesion Candidato");
		
		botonsesionadmin.setFont(new Font( "dandelion in the spring", Font.TRUETYPE_FONT, 30 ));
		botonregistrar.setFont(new Font( "dandelion in the spring", Font.TRUETYPE_FONT, 34 ));
		botonsesioncandidato.setFont(new Font( "dandelion in the spring", Font.TRUETYPE_FONT, 38 ));
		
		int aumentoresolucion = ((width*710)/1024)-710;
		
		GridBagConstraints gbcsesionadmin = new GridBagConstraints( 0, 0, 0, 0, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( -550+15, 660+50+aumentoresolucion, 0, 0 ), 0, 0 );
		GridBagConstraints gbcregistrar = new GridBagConstraints( 0, 0, 0, 0, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( -410+15, 660+50+aumentoresolucion, 0, 0 ), 0, 0 );
		GridBagConstraints gbcsesioncandidato = new GridBagConstraints( 0, 0, 0, 0, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( -270+15, 660+50+aumentoresolucion, 0, 0 ), 0, 0 );
		
		botonsesionadmin.addActionListener(this);
		botonsesionadmin.setActionCommand("Sesion Administrador");
		
		botonregistrar.addActionListener(this);
		botonregistrar.setActionCommand("Registrar");
		
		botonsesioncandidato.addActionListener(this);
		botonsesioncandidato.setActionCommand("Sesion Candidato");
		
		botonsesionadmin.setToolTipText("Inicia la Sesion de Administrador.");
		botonregistrar.setToolTipText("Registra un nuevo Candidato.");
		botonsesioncandidato.setToolTipText("Inicia la Sesion de Candidato.");
        
        add( botonsesionadmin, gbcsesionadmin );
        add( botonregistrar, gbcregistrar );
        add( botonsesioncandidato, gbcsesioncandidato );
	}
	
    /**
     * Permite establecer el cronograma como imagen de fondo.
     * @param g
     */
	public void paintComponent(Graphics g) 
	{ 
		Dimension tamanio=getSize(); 
		ImageIcon imagenfondo=new ImageIcon("./images/InterfazInicio/InterfazInicio"+numeroimagenresolucion+".PNG"); 
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
		if (clic.equals("Sesion Administrador"))
		{
			inicio.verValidarAdministrador();
		}
		if (clic.equals("Registrar"))
		{
			inicio.verRegistrarCandidato();
		}
		if (clic.equals("Sesion Candidato"))
		{
			inicio.verValidarCandidato();
		}
	}	
}