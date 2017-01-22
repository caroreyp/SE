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
public class SesionAdministradorI extends JDialog implements ActionListener
{
	/**
	 * Son los atributos que definen la barra de menus de la ventana SesionAdministradorI.
	 */
	private JMenuBar menubar;
	private JMenu menuadministrador;
	private JMenu menuayuda;
	private JMenu menuconvocatorias;
	private JMenu menupruebas;
	private JMenu menuconfiguracion;
	private JMenu menuexperto;
	private JMenuItem itemcrearconvo;
	private JMenuItem itemverconvo;
	private JMenuItem itemanalisisr;
	private JMenuItem itemresultados;
	private JMenuItem itemcambiarc;
	private JMenuItem itemcrearadmin;
	private JMenuItem itempermiso;
	private JMenuItem itemcerrar;
	private JMenuItem itemguias;
	private JMenuItem itemacercade;
	private JMenuItem itemconocimiento;
	private JMenuItem itemdescripcion;
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
	private PanelAdministrador paneladministrador;
	
	/**
	 * Es la variable que almacena el valor del administrador actual.
	 */
	private Administrador adminactual;
	
	/**
	 * Es la variable que almacena el valor de la sesion actual.
	 */
	private Sesion sesionactual;
	
	public SesionAdministradorI(InterfazInicio interfaz, Administrador actualadmin, Sesion sesionadmin)
	{
		super(interfaz, true);
		setLayout(new FlowLayout());
        setTitle("Sesion Administrador "+actualadmin.getNombres()+"  Fecha : "+sesionadmin.getFechasesion()+"  Hora de inicio sesión : "+sesionadmin.getHorainicial()+" - SESP");
		setResizable(false);
		setDefaultCloseOperation(2);
		
		setBounds(0,0,interfaz.getWidthvetana(),interfaz.getHeigthvetana()-40);
		
		ImageIcon icono =new ImageIcon("./images/IconosInterfaz/IconoVentana.PNG"); 
		Image imagenicono=icono.getImage();
		this.setIconImage(imagenicono);
		
		inicio=interfaz;
		adminactual = actualadmin;
		sesionactual = sesionadmin;
		salir = false;
		
		menubar = new JMenuBar();
		menubar.setBackground(new Color(239,239,239));
		
		menuadministrador = new JMenu("Administrador");
		menuadministrador.setForeground(Color.BLACK);
		menuadministrador.setMnemonic(KeyEvent.VK_A);
		
		menuayuda = new JMenu("Ayuda");
		menuayuda.setForeground(Color.BLACK);
		menuayuda.setMnemonic(KeyEvent.VK_Y);
		
		menuconvocatorias = new JMenu("Convocatorias");
		menuconvocatorias.setForeground(Color.BLACK);
		menuconvocatorias.setIcon(new ImageIcon("./images/IconosInterfaz/convocatorias.PNG"));
		
		menupruebas = new JMenu("Pruebas");
		menupruebas.setForeground(Color.BLACK);
		menupruebas.setIcon(new ImageIcon("./images/IconosInterfaz/pruebas.PNG"));
		
		menuconfiguracion = new JMenu("Configuracion");
		menuconfiguracion.setForeground(Color.BLACK);
		menuconfiguracion.setIcon(new ImageIcon("./images/IconosInterfaz/configuracion.PNG"));
		
		menuexperto = new JMenu("Experto");
		menuexperto.setForeground(Color.BLACK);
		menuexperto.setIcon(new ImageIcon("./images/IconosInterfaz/experto.PNG"));
		
		itemcrearconvo = new JMenuItem("Crear Convocatoria");
		itemcrearconvo.setForeground(Color.BLACK);
		itemcrearconvo.addActionListener(this);
		itemcrearconvo.setActionCommand("Crear Convocatoria");
		itemcrearconvo.setIcon(new ImageIcon("./images/IconosInterfaz/adicionar.PNG"));
		
		itemverconvo = new JMenuItem("Ver Convocatorias");
		itemverconvo.setForeground(Color.BLACK);
		itemverconvo.addActionListener(this);
		itemverconvo.setActionCommand("Ver Convocatorias");
		itemverconvo.setIcon(new ImageIcon("./images/IconosInterfaz/verconvocatorias.PNG"));
		
		itemanalisisr = new JMenuItem("Analisis de Resultados");
		itemanalisisr.setForeground(Color.BLACK);
		itemanalisisr.addActionListener(this);
		itemanalisisr.setActionCommand("Analisis de Resultados");
		itemanalisisr.setIcon(new ImageIcon("./images/IconosInterfaz/graficos.PNG"));
		
		itemresultados = new JMenuItem("Ver Resultados");
		itemresultados.setForeground(Color.BLACK);
		itemresultados.addActionListener(this);
		itemresultados.setActionCommand("Ver Resultados");
		itemresultados.setIcon(new ImageIcon("./images/IconosInterfaz/verresultados.PNG"));
		
		itemcambiarc = new JMenuItem("Cambiar Contraseña");
		itemcambiarc.setForeground(Color.BLACK);
		itemcambiarc.addActionListener(this);
		itemcambiarc.setActionCommand("Cambiar Contraseña");
		itemcambiarc.setIcon(new ImageIcon("./images/IconosInterfaz/actualizar.PNG"));
		
		itemcrearadmin = new JMenuItem("Crear Administrador");
		itemcrearadmin.setForeground(Color.BLACK);
		itemcrearadmin.addActionListener(this);
		itemcrearadmin.setActionCommand("Crear Administrador");
		itemcrearadmin.setIcon(new ImageIcon("./images/IconosInterfaz/adicionar.PNG"));
		
		itemcerrar = new JMenuItem("Cerrar Sesion");
		itemcerrar.setForeground(Color.BLACK);
		itemcerrar.addActionListener(this);
		itemcerrar.setActionCommand("Cerrar Sesion");
		itemcerrar.setIcon(new ImageIcon("./images/IconosInterfaz/cerrar.PNG"));
		
		itempermiso = new JMenuItem("Asignar Permiso");
		itempermiso.setForeground(Color.BLACK);
		itempermiso.addActionListener(this);
		itempermiso.setActionCommand("Asignar Permiso");
		itempermiso.setIcon(new ImageIcon("./images/IconosInterfaz/asignarpermiso.PNG"));
		
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
		
		itemconocimiento = new JMenuItem("Base del Conocimiento");
		itemconocimiento.setForeground(Color.BLACK);
		itemconocimiento.addActionListener(this);
		itemconocimiento.setActionCommand("Base del Conocimiento");
		itemconocimiento.setIcon(new ImageIcon("./images/IconosInterfaz/conocimiento.PNG"));
		
		itemdescripcion = new JMenuItem("Descripcion");
		itemdescripcion.setForeground(Color.BLACK);
		itemdescripcion.addActionListener(this);
		itemdescripcion.setActionCommand("Descripcion");
		itemdescripcion.setIcon(new ImageIcon("./images/IconosInterfaz/item.PNG"));
		
		menuconvocatorias.add(itemcrearconvo);
		menuconvocatorias.add(itemverconvo);
		menuconvocatorias.add(itemanalisisr);
		
		menupruebas.add(itemresultados);
		menupruebas.add(itempermiso);
		
		menuconfiguracion.add(itemcambiarc);
		menuconfiguracion.add(itemcrearadmin);
		
		menuadministrador.add(menuconvocatorias);
		menuadministrador.add(menupruebas);
		menuadministrador.add(menuconfiguracion);
		menuadministrador.add(menuexperto);
		menuadministrador.add(new JSeparator());
		menuadministrador.add(itemcerrar);
		
		menuayuda.add(itemguias);
		menuayuda.add(itemacercade);
		
		menuexperto.add(itemconocimiento);
		menuexperto.add(itemdescripcion);
		
		menubar.add(menuadministrador);
		menubar.add(menuayuda);
		
		setJMenuBar(menubar);
		
		JPanel panelcolor = new JPanel(new FlowLayout());
		panelcolor.setPreferredSize(new Dimension (interfaz.getWidthvetana()-8,interfaz.getHeigthvetana()-102));
		panelcolor.setBackground(new Color(68,146,132));

		paneladministrador = new PanelAdministrador(interfaz, actualadmin, interfaz.getWidthvetana(), interfaz.getHeigthvetana());
		panelcolor.add(paneladministrador);
		add(panelcolor);
	}
	
	public void dispose( ){
		if(salir == false){
			int respuesta = JOptionPane.showConfirmDialog(this,"¿Cerrar Sesion Administrador?","Cerrar Sesion",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
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
		if (clic.equals("Crear Convocatoria"))
		{
			inicio.verCrearConvocatoria(adminactual);
		}
		else if (clic.equals("Ver Convocatorias"))
		{
			inicio.verVerConvocatorias();
		}
		else if (clic.equals("Analisis de Resultados"))
		{
			inicio.verSeleccionarConvocatoriaAnalisis();
		}
		else if (clic.equals("Ver Resultados"))
		{
			inicio.verSeleccionarResultadosRevisados();
		}
		else if (clic.equals("Asignar Permiso"))
		{
			inicio.verAsignarPermiso(adminactual);
		}
		else if (clic.equals("Cambiar Contraseña"))
		{
			inicio.verCambiarContraseña(adminactual);
		}
		else if (clic.equals("Crear Administrador"))
		{
			inicio.verDatosAdministrador();
		}
		else if (clic.equals("Base del Conocimiento"))
		{
			inicio.verBaseConocimiento();
		}
		else if (clic.equals("Descripcion"))
		{
			inicio.verVerDescripcion();
		}
		else if (clic.equals("Cerrar Sesion"))
		{
			dispose();
		}
		else if (clic.equals("Guias de Usuario"))
		{
			inicio.verGuiasUsuario(7,16);
		}
		else if (clic.equals("Acerca de SESP"))
		{
			inicio.verAcercadeSesp();
		}
	}
}