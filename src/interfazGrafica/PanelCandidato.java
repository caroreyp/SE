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

import mundo.Candidato;
import mundo.Permiso;
import basedeDatos.Conector;
import basedeDatos.PermisoBD;

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
public class PanelCandidato extends JPanel implements ActionListener
{
     private InterfazInicio inicio;
     private JButton botonpresentarprueba;
     private Candidato candidatactual;
     private int numeroimagenresolucion;
	
	public PanelCandidato(InterfazInicio interfaz, Candidato actualcandidat, int width, int heigth)
	{
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
		
		inicio=interfaz;
		candidatactual = actualcandidat;
		

		botonpresentarprueba = new JButton (new ImageIcon("./images/IconosInterfaz/BotonPresentarPrueba.PNG"));
		botonpresentarprueba.setPreferredSize(new Dimension(210,57));
		
		botonpresentarprueba.setBackground(new Color(153,175,114));
		botonpresentarprueba.setForeground(new Color(253,253,204));
		
		botonpresentarprueba.setText("Presentar Prueba");
		botonpresentarprueba.setFont(new Font( "dandelion in the spring", Font.TRUETYPE_FONT, 32 ));
		
		int aumentoresolucion = ((width*710)/1024)-710;
		
		GridBagConstraints gbcpresentarprueba = new GridBagConstraints( 0, 0, 0, 0, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( -410, 660+50+aumentoresolucion, 0, 0 ), 0, 0 );
		
		botonpresentarprueba.addActionListener(this);
		botonpresentarprueba.setActionCommand("Presentar Prueba");
		
		botonpresentarprueba.setToolTipText("Presentar la Prueba que el Candidato disponga.");
        
        add( botonpresentarprueba, gbcpresentarprueba );
	}
	
    /**
     * Permite establecer el cronograma como imagen de fondo.
     * @param g
     */
	public void paintComponent(Graphics g) 
	{ 
		Dimension tamanio=getSize(); 
		ImageIcon imagenfondo=new ImageIcon("./images/SesionCandidato/SesionCandidato"+numeroimagenresolucion+".PNG");
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
		if (clic.equals("Presentar Prueba"))
		{
			Permiso permisocandidat = null;
			try
	        {
	            Conector conector = new Conector();
	            conector.iniciarConexionBaseDatos();
	            permisocandidat = PermisoBD.buscarCandidato(candidatactual.getIdcandidato(), conector);
	            conector.terminarConexionBaseDatos();
	        }
	        catch (Exception e)
			{
	        	JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
			}
	        
	        if(permisocandidat != null)
			{
				inicio.verSeleccionarTipoPrueba(candidatactual);
			}
			else
			{
				JOptionPane.showMessageDialog(this,"No puede presentar la prueba, no se ha asignado ningun permiso.","Permiso Candidato", JOptionPane.WARNING_MESSAGE,new ImageIcon("./images/IconosInterfaz/advertencia.PNG"));
			}
		}
	}	
}