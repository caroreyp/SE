package interfazGrafica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import basedeDatos.*;
import mundo.*;

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
public class SesionCandidatoI extends JDialog implements ActionListener
{
	/**
	 * Son los atributos que definen la barra de menus de la ventana SesionCandidatoI.
	 */
	private JMenuBar menubar;
	private JMenu menucandidato;
	private JMenu menuayuda;
	private JMenuItem itemverpermisos;
	private JMenuItem itempresentarprueba;
	private JMenuItem itemcerrar;
	private JMenuItem itemguias;
	private JMenuItem itemacercade;
	/*****************************************************************************/
	
	/**
	 * Es la variable que toma el valor si la ventana es cerrada o no.
	 */
	private boolean salir;
	
	/**
	 * Es la relacion que se estable con la ventana de inicio.
	 */
	private InterfazInicio inicio;
	
	
	/**
	 * Es la relacion con el panel que contiene la imagen y los botones.
	 */
	private PanelCandidato panelcandidato;
	
	/**
	 * Es la variable que almacena el valor de la candidato actual.
	 */
	private Candidato candidatactual;
	
	/**
	 * Es la variable que almacena el valor de la sesion actual.
	 */
	private Sesion sesionactual;
	
	public SesionCandidatoI(InterfazInicio interfaz, Candidato actualcandidat, Sesion sesioncandid)
	{
		super(interfaz, true);
		setLayout(new FlowLayout());
        setTitle("Sesion Candidato "+actualcandidat.getNombres()+"  Fecha : "+sesioncandid.getFechasesion()+"  Hora de inicio sesión : "+sesioncandid.getHorainicial()+" - SESP");
		setResizable(false);
		setDefaultCloseOperation(2);
		setBounds(0,0,interfaz.getWidthvetana(),interfaz.getHeigthvetana()-40);
		
		ImageIcon icono =new ImageIcon("./images/IconosInterfaz/IconoVentana.PNG"); 
		Image imagenicono=icono.getImage();
		this.setIconImage(imagenicono);
		
		inicio=interfaz;
		candidatactual = actualcandidat;
		sesionactual = sesioncandid;
		
		menubar = new JMenuBar();
		menubar.setBackground(new Color(239,239,239));
		
		menucandidato = new JMenu("Candidato");
		menucandidato.setForeground(Color.BLACK);
		menucandidato.setMnemonic(KeyEvent.VK_C);
		
		menuayuda = new JMenu("Ayuda");
		menuayuda.setForeground(Color.BLACK);
		menuayuda.setMnemonic(KeyEvent.VK_Y);
		
		itemverpermisos = new JMenuItem("Ver Permisos");
		itemverpermisos.setForeground(Color.BLACK);
		itemverpermisos.addActionListener(this);
		itemverpermisos.setActionCommand("Ver Permisos");
		itemverpermisos.setIcon(new ImageIcon("./images/IconosInterfaz/ver.PNG"));
		
		itempresentarprueba = new JMenuItem("Presentar Prueba");
		itempresentarprueba.setForeground(Color.BLACK);
		itempresentarprueba.addActionListener(this);
		itempresentarprueba.setActionCommand("Presentar Prueba");
		itempresentarprueba.setIcon(new ImageIcon("./images/IconosInterfaz/presentarprueba.PNG"));
		
		itemcerrar = new JMenuItem("Cerrar Sesion");
		itemcerrar.setForeground(Color.BLACK);
		itemcerrar.addActionListener(this);
		itemcerrar.setActionCommand("Cerrar Sesion");
		itemcerrar.setIcon(new ImageIcon("./images/IconosInterfaz/cerrar.PNG"));
		
		itemguias = new JMenuItem("Guias de Usuario");
		itemguias.setForeground(Color.BLACK);
		itemguias.addActionListener(this);
		itemguias.setActionCommand("Guias de Usuario");
		itemguias.setIcon(new ImageIcon("./images/IconosInterfaz/ayuda.PNG"));
		
		itemacercade = new JMenuItem("Acerca de SESP");
		itemacercade.setForeground(Color.BLACK);
		itemacercade.addActionListener(this);
		itemacercade.setActionCommand("Acerca de SESP");
		itemacercade.setIcon(new ImageIcon("./images/IconosInterfaz/item.PNG"));
		
		menucandidato.add(itemverpermisos);
		menucandidato.add(itempresentarprueba);
		menucandidato.add(new JSeparator());
		menucandidato.add(itemcerrar);
		
		menuayuda.add(itemguias);
		menuayuda.add(itemacercade);
		
		menubar.add(menucandidato);
		menubar.add(menuayuda);
		
		setJMenuBar(menubar);
		
		JPanel panelcolor = new JPanel(new FlowLayout());
		panelcolor.setPreferredSize(new Dimension (interfaz.getWidthvetana()-8,interfaz.getHeigthvetana()-102));
		panelcolor.setBackground(new Color(69,145,107));
		
		panelcandidato = new PanelCandidato(interfaz, actualcandidat, interfaz.getWidthvetana(), interfaz.getHeigthvetana());
		panelcolor.add(panelcandidato);
		add(panelcolor);
	}
	
	public void dispose( ){
		if(salir == false){
			int respuesta = JOptionPane.showConfirmDialog(this,"¿Cerrar Sesion Candidato?","Cerrar Sesion",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
	        if (respuesta == JOptionPane.YES_OPTION){
	        	salir = true;
	        	GregorianCalendar calendario = new GregorianCalendar();
	    		String horafinal = calendario.get(Calendar.HOUR_OF_DAY)+":"+calendario.get(Calendar.MINUTE)+":"+calendario.get(Calendar.SECOND);
	    		sesionactual.setHorafinal(horafinal);
	    		try 
	    		{
	    			Conector conector = new Conector();
	    			conector.iniciarConexionBaseDatos();
	    			SesionBD.actualizar(sesionactual, conector);
	    			conector.terminarConexionBaseDatos();
	    		}
	    		catch (Exception e)
	    		{
	    			JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
	    		}
	            super.dispose();
	        }
		}
    }
	
	/**
     * Ejecuta la acción que corresponde a la opción del menú que fue seleccionada
     * @param evento Es el evento de seleccionar una opción del menú - evento!=null
     */
	public void actionPerformed(ActionEvent evento)
	{
		String clic = evento.getActionCommand();	
		if (clic.equals("Ver Permisos"))
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
	        	inicio.verVerPermisos(permisocandidat);
	        }
	        else
			{
				JOptionPane.showMessageDialog(this,"No se ha asignado ningun permiso, solicite un permiso con el administrador.","Permiso Candidato", JOptionPane.WARNING_MESSAGE,new ImageIcon("./images/IconosInterfaz/advertencia.PNG"));
			}
		}
		else if (clic.equals("Presentar Prueba"))
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
		else if (clic.equals("Cerrar Sesion"))
		{
			dispose();
		}
		else if (clic.equals("Guias de Usuario"))
		{
			inicio.verGuiasUsuario(23,26);
		}
		else if (clic.equals("Acerca de SESP"))
		{
			inicio.verAcercadeSesp();
		}
	}
}