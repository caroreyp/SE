package interfazGrafica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;

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
public class BaseConocimiento extends JDialog implements ActionListener
{
	/**
	 * Son los atributos que definen la barra de menus de la vantana BaseConocimiento.
	 */
	private JMenuBar menubar;
	private JMenu menuconocimiento;
	private JMenu menuayuda;
	private JMenu menuroles;
	private JMenu menupruebas;
	private JMenu menupreguntas;
	private JMenu menuescala;
	private JMenu menucompetencia;
	private JMenu menureglas;
	private JMenuItem itemverroles;
	private JMenuItem itemadicionarroles;
	private JMenuItem itemmodificarroles;
	private JMenuItem itemeliminarroles;
	private JMenuItem itemverpruebas;
	private JMenuItem itemadicionarpruebas;
	private JMenuItem itemmodificarpruebas;
	private JMenuItem itemeliminarpruebas;
	private JMenuItem itemverpreguntas;
	private JMenuItem itemadicionarpreguntas;
	private JMenuItem itemmodificarpreguntas;
	private JMenuItem itemeliminarpreguntas;
	private JMenuItem itemverescalas;
	private JMenuItem itemadicionarescalas;
	private JMenuItem itemmodificarescalas;
	private JMenuItem itemeliminarescalas;
	private JMenuItem itemvercompetencias;
	private JMenuItem itemadicionarcompetencias;
	private JMenuItem itemmodificarcompetencias;
	private JMenuItem itemeliminarcompetencias;
	private JMenuItem itemverreglas;
	private JMenuItem itemadicionarreglas;
	private JMenuItem itemmodificarreglas;
	private JMenuItem itemeliminarreglas;
	private JMenuItem itemcerrar;
	private JMenuItem itemguias;
	private JMenuItem itemacercade;
	/*****************************************************************************/
	
	/**
	 * Es la relacion que se estable con la ventana de inicio.
	 */
	private InterfazInicio inicio;
	
	/**
	 * Es la relacion con el panel que contiene la imagen y los botones.
	 */
	private PanelConocimiento panelconocimiento;
	
	public BaseConocimiento(InterfazInicio interfaz)
	{
		super(interfaz, true);
		setLayout(new FlowLayout());
        setTitle("Base del Conocimiento - SESP");
		setResizable(false);
		setDefaultCloseOperation(2);
		setBounds(0,0,interfaz.getWidthvetana(),interfaz.getHeigthvetana()-40);
		
		ImageIcon icono =new ImageIcon("./images/IconosInterfaz/IconoVentana.PNG"); 
		Image imagenicono=icono.getImage();
		this.setIconImage(imagenicono);
		
		inicio=interfaz;
		
		menubar = new JMenuBar();
		menubar.setBackground(new Color(239,239,239));
		
		menuconocimiento = new JMenu("Base del Conocimiento");
		menuconocimiento.setForeground(Color.BLACK);
		menuconocimiento.setMnemonic(KeyEvent.VK_B);
		
		menuayuda = new JMenu("Ayuda");
		menuayuda.setForeground(Color.BLACK);
		menuayuda.setMnemonic(KeyEvent.VK_Y);
		
		menuroles = new JMenu("Roles");
		menuroles.setForeground(Color.BLACK);
		menuroles.setIcon(new ImageIcon("./images/IconosInterfaz/item.PNG"));
		
		menupruebas = new JMenu("Pruebas");
		menupruebas.setForeground(Color.BLACK);
		menupruebas.setIcon(new ImageIcon("./images/IconosInterfaz/item.PNG"));
		
		menupreguntas = new JMenu("Preguntas");
		menupreguntas.setForeground(Color.BLACK);
		menupreguntas.setIcon(new ImageIcon("./images/IconosInterfaz/item.PNG"));
		
		menuescala = new JMenu("Escalas");
		menuescala.setForeground(Color.BLACK);
		menuescala.setIcon(new ImageIcon("./images/IconosInterfaz/item.PNG"));
		
		menucompetencia = new JMenu("Competencias");
		menucompetencia.setForeground(Color.BLACK);
		menucompetencia.setIcon(new ImageIcon("./images/IconosInterfaz/item.PNG"));
		
		menureglas = new JMenu("Reglas");
		menureglas.setForeground(Color.BLACK);
		menureglas.setIcon(new ImageIcon("./images/IconosInterfaz/item.PNG"));
		
		itemverroles = new JMenuItem("Ver");
		itemverroles.setForeground(Color.BLACK);
		itemverroles.addActionListener(this);
		itemverroles.setActionCommand("Roles Ver");
		itemverroles.setIcon(new ImageIcon("./images/IconosInterfaz/ver.PNG"));

		itemadicionarroles = new JMenuItem("Adicionar");
		itemadicionarroles.setForeground(Color.BLACK);
		itemadicionarroles.addActionListener(this);
		itemadicionarroles.setActionCommand("Roles Adicionar");
		itemadicionarroles.setIcon(new ImageIcon("./images/IconosInterfaz/adicionar.PNG"));
		
		itemmodificarroles = new JMenuItem("Modificar");
		itemmodificarroles.setForeground(Color.BLACK);
		itemmodificarroles.addActionListener(this);
		itemmodificarroles.setActionCommand("Roles Modificar");
		itemmodificarroles.setIcon(new ImageIcon("./images/IconosInterfaz/actualizar.PNG"));
		
		itemeliminarroles = new JMenuItem("Eliminar");
		itemeliminarroles.setForeground(Color.BLACK);
		itemeliminarroles.addActionListener(this);
		itemeliminarroles.setActionCommand("Roles Eliminar");
		itemeliminarroles.setIcon(new ImageIcon("./images/IconosInterfaz/eliminar.PNG"));
		//*************************************************************
		
		itemverpruebas = new JMenuItem("Ver");
		itemverpruebas.setForeground(Color.BLACK);
		itemverpruebas.addActionListener(this);
		itemverpruebas.setActionCommand("Pruebas Ver");
		itemverpruebas.setIcon(new ImageIcon("./images/IconosInterfaz/ver.PNG"));

		itemadicionarpruebas = new JMenuItem("Adicionar");
		itemadicionarpruebas.setForeground(Color.BLACK);
		itemadicionarpruebas.addActionListener(this);
		itemadicionarpruebas.setActionCommand("Pruebas Adicionar");
		itemadicionarpruebas.setIcon(new ImageIcon("./images/IconosInterfaz/adicionar.PNG"));
		
		itemmodificarpruebas = new JMenuItem("Modificar");
		itemmodificarpruebas.setForeground(Color.BLACK);
		itemmodificarpruebas.addActionListener(this);
		itemmodificarpruebas.setActionCommand("Pruebas Modificar");
		itemmodificarpruebas.setIcon(new ImageIcon("./images/IconosInterfaz/actualizar.PNG"));
		
		itemeliminarpruebas = new JMenuItem("Eliminar");
		itemeliminarpruebas.setForeground(Color.BLACK);
		itemeliminarpruebas.addActionListener(this);
		itemeliminarpruebas.setActionCommand("Pruebas Eliminar");
		itemeliminarpruebas.setIcon(new ImageIcon("./images/IconosInterfaz/eliminar.PNG"));
		//*************************************************************
		
		itemverpreguntas = new JMenuItem("Ver");
		itemverpreguntas.setForeground(Color.BLACK);
		itemverpreguntas.addActionListener(this);
		itemverpreguntas.setActionCommand("Preguntas Ver");
		itemverpreguntas.setIcon(new ImageIcon("./images/IconosInterfaz/ver.PNG"));

		itemadicionarpreguntas = new JMenuItem("Adicionar");
		itemadicionarpreguntas.setForeground(Color.BLACK);
		itemadicionarpreguntas.addActionListener(this);
		itemadicionarpreguntas.setActionCommand("Preguntas Adicionar");
		itemadicionarpreguntas.setIcon(new ImageIcon("./images/IconosInterfaz/adicionar.PNG"));
		
		itemmodificarpreguntas = new JMenuItem("Modificar");
		itemmodificarpreguntas.setForeground(Color.BLACK);
		itemmodificarpreguntas.addActionListener(this);
		itemmodificarpreguntas.setActionCommand("Preguntas Modificar");
		itemmodificarpreguntas.setIcon(new ImageIcon("./images/IconosInterfaz/actualizar.PNG"));
		
		itemeliminarpreguntas = new JMenuItem("Eliminar");
		itemeliminarpreguntas.setForeground(Color.BLACK);
		itemeliminarpreguntas.addActionListener(this);
		itemeliminarpreguntas.setActionCommand("Preguntas Eliminar");
		itemeliminarpreguntas.setIcon(new ImageIcon("./images/IconosInterfaz/eliminar.PNG"));
		//*************************************************************
		
		itemverescalas = new JMenuItem("Ver");
		itemverescalas.setForeground(Color.BLACK);
		itemverescalas.addActionListener(this);
		itemverescalas.setActionCommand("Escalas Ver");
		itemverescalas.setIcon(new ImageIcon("./images/IconosInterfaz/ver.PNG"));

		itemadicionarescalas = new JMenuItem("Adicionar");
		itemadicionarescalas.setForeground(Color.BLACK);
		itemadicionarescalas.addActionListener(this);
		itemadicionarescalas.setActionCommand("Escalas Adicionar");
		itemadicionarescalas.setIcon(new ImageIcon("./images/IconosInterfaz/adicionar.PNG"));
		
		itemmodificarescalas = new JMenuItem("Modificar");
		itemmodificarescalas.setForeground(Color.BLACK);
		itemmodificarescalas.addActionListener(this);
		itemmodificarescalas.setActionCommand("Escalas Modificar");
		itemmodificarescalas.setIcon(new ImageIcon("./images/IconosInterfaz/actualizar.PNG"));
		
		itemeliminarescalas = new JMenuItem("Eliminar");
		itemeliminarescalas.setForeground(Color.BLACK);
		itemeliminarescalas.addActionListener(this);
		itemeliminarescalas.setActionCommand("Escalas Eliminar");
		itemeliminarescalas.setIcon(new ImageIcon("./images/IconosInterfaz/eliminar.PNG"));
		//*************************************************************
		
		itemvercompetencias = new JMenuItem("Ver");
		itemvercompetencias.setForeground(Color.BLACK);
		itemvercompetencias.addActionListener(this);
		itemvercompetencias.setActionCommand("Competencias Ver");
		itemvercompetencias.setIcon(new ImageIcon("./images/IconosInterfaz/ver.PNG"));

		itemadicionarcompetencias = new JMenuItem("Adicionar");
		itemadicionarcompetencias.setForeground(Color.BLACK);
		itemadicionarcompetencias.addActionListener(this);
		itemadicionarcompetencias.setActionCommand("Competencias Adicionar");
		itemadicionarcompetencias.setIcon(new ImageIcon("./images/IconosInterfaz/adicionar.PNG"));
		
		itemmodificarcompetencias = new JMenuItem("Modificar");
		itemmodificarcompetencias.setForeground(Color.BLACK);
		itemmodificarcompetencias.addActionListener(this);
		itemmodificarcompetencias.setActionCommand("Competencias Modificar");
		itemmodificarcompetencias.setIcon(new ImageIcon("./images/IconosInterfaz/actualizar.PNG"));
		
		itemeliminarcompetencias = new JMenuItem("Eliminar");
		itemeliminarcompetencias.setForeground(Color.BLACK);
		itemeliminarcompetencias.addActionListener(this);
		itemeliminarcompetencias.setActionCommand("Competencias Eliminar");
		itemeliminarcompetencias.setIcon(new ImageIcon("./images/IconosInterfaz/eliminar.PNG"));
		//*************************************************************
		
		itemverreglas = new JMenuItem("Ver");
		itemverreglas.setForeground(Color.BLACK);
		itemverreglas.addActionListener(this);
		itemverreglas.setActionCommand("Reglas Ver");
		itemverreglas.setIcon(new ImageIcon("./images/IconosInterfaz/ver.PNG"));

		itemadicionarreglas = new JMenuItem("Adicionar");
		itemadicionarreglas.setForeground(Color.BLACK);
		itemadicionarreglas.addActionListener(this);
		itemadicionarreglas.setActionCommand("Reglas Adicionar");
		itemadicionarreglas.setIcon(new ImageIcon("./images/IconosInterfaz/adicionar.PNG"));
		
		itemmodificarreglas = new JMenuItem("Modificar");
		itemmodificarreglas.setForeground(Color.BLACK);
		itemmodificarreglas.addActionListener(this);
		itemmodificarreglas.setActionCommand("Reglas Modificar");
		itemmodificarreglas.setIcon(new ImageIcon("./images/IconosInterfaz/actualizar.PNG"));
		
		itemeliminarreglas = new JMenuItem("Eliminar");
		itemeliminarreglas.setForeground(Color.BLACK);
		itemeliminarreglas.addActionListener(this);
		itemeliminarreglas.setActionCommand("Reglas Eliminar");
		itemeliminarreglas.setIcon(new ImageIcon("./images/IconosInterfaz/eliminar.PNG"));
		//*************************************************************
		
		itemcerrar = new JMenuItem("Cerrar");
		itemcerrar.setForeground(Color.BLACK);
		itemcerrar.addActionListener(this);
		itemcerrar.setActionCommand("Cerrar");
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
		
		menuroles.add(itemverroles);
		menuroles.add(itemadicionarroles);
		menuroles.add(itemmodificarroles);
		menuroles.add(itemeliminarroles);
		
		menupruebas.add(itemverpruebas);
		menupruebas.add(itemadicionarpruebas);
		menupruebas.add(itemmodificarpruebas);
		menupruebas.add(itemeliminarpruebas);
		
		menupreguntas.add(itemverpreguntas);
		menupreguntas.add(itemadicionarpreguntas);
		menupreguntas.add(itemmodificarpreguntas);
		menupreguntas.add(itemeliminarpreguntas);
		
		menuescala.add(itemverescalas);
		menuescala.add(itemadicionarescalas);
		menuescala.add(itemmodificarescalas);
		menuescala.add(itemeliminarescalas);
		
		menucompetencia.add(itemvercompetencias);
		menucompetencia.add(itemadicionarcompetencias);
		menucompetencia.add(itemmodificarcompetencias);
		menucompetencia.add(itemeliminarcompetencias);
		
		menureglas.add(itemverreglas);
		menureglas.add(itemadicionarreglas);
		menureglas.add(itemmodificarreglas);
		menureglas.add(itemeliminarreglas);
		
		menuconocimiento.add(menuroles);
		menuconocimiento.add(menupruebas);
		menuconocimiento.add(menupreguntas);
		menuconocimiento.add(menuescala);
		menuconocimiento.add(menucompetencia);
		menuconocimiento.add(menureglas);
		menuconocimiento.add(new JSeparator());
		menuconocimiento.add(itemcerrar);
		
		menuayuda.add(itemguias);
		menuayuda.add(itemacercade);
		
		menubar.add(menuconocimiento);
		menubar.add(menuayuda);
		
		setJMenuBar(menubar);
		
		JPanel panelcolor = new JPanel(new FlowLayout());
		panelcolor.setPreferredSize(new Dimension (interfaz.getWidthvetana()-8,interfaz.getHeigthvetana()-102));
		panelcolor.setBackground(new Color(60,116,107));

		panelconocimiento = new PanelConocimiento(interfaz, interfaz.getWidthvetana(), interfaz.getHeigthvetana());
		panelcolor.add(panelconocimiento);
		add(panelcolor);
	}
	
	/**
     * Ejecuta la acción que corresponde a la opción del menú que fue seleccionada
     * @param evento Es el evento de seleccionar una opción del menú - evento!=null
     */
	public void actionPerformed(ActionEvent evento)
	{
		String clic = evento.getActionCommand();	
		if (clic.equals("Roles Ver"))
		{
			inicio.verDatosEntidad("Rol");
		}
		else if (clic.equals("Roles Adicionar"))
		{
			inicio.verAdicionarDatos("Rol");
		}
		else if (clic.equals("Roles Modificar"))
		{
			inicio.verSeleccionarDatosporID("Rol","Modificar");
		}
		else if (clic.equals("Roles Eliminar"))
		{
			inicio.verSeleccionarDatosporID("Rol","Eliminar");
		}
		else if (clic.equals("Pruebas Ver"))
		{
			inicio.verDatosEntidad("Prueba");
		}
		else if (clic.equals("Pruebas Adicionar"))
		{
			inicio.verAdicionarDatos("Prueba");
		}
		else if (clic.equals("Pruebas Modificar"))
		{
			inicio.verSeleccionarDatosporID("Prueba","Modificar");
		}
		else if (clic.equals("Pruebas Eliminar"))
		{
			inicio.verSeleccionarDatosporID("Prueba","Eliminar");
		}
		else if (clic.equals("Preguntas Ver"))
		{
			inicio.verDatosEntidad("Pregunta");
		}
		else if (clic.equals("Preguntas Adicionar"))
		{
			inicio.verAdicionarDatos("Pregunta");
		}
		else if (clic.equals("Preguntas Modificar"))
		{
			inicio.verSeleccionarDatosporID("Pregunta","Modificar");
		}
		else if (clic.equals("Preguntas Eliminar"))
		{
			inicio.verSeleccionarDatosporID("Pregunta","Eliminar");
		}
		else if (clic.equals("Escalas Ver"))
		{
			inicio.verDatosEntidad("Escala");
		}
		else if (clic.equals("Escalas Adicionar"))
		{
			inicio.verAdicionarDatos("Escala");
		}
		else if (clic.equals("Escalas Modificar"))
		{
			inicio.verSeleccionarDatosporID("Escala","Modificar");
		}
		else if (clic.equals("Escalas Eliminar"))
		{
			inicio.verSeleccionarDatosporID("Escala","Eliminar");
		}
		else if (clic.equals("Competencias Ver"))
		{
			inicio.verDatosEntidad("Competencia");
		}
		else if (clic.equals("Competencias Adicionar"))
		{
			inicio.verAdicionarDatos("Competencia");
		}
		else if (clic.equals("Competencias Modificar"))
		{
			inicio.verSeleccionarDatosporID("Competencia","Modificar");
		}
		else if (clic.equals("Competencias Eliminar"))
		{
			inicio.verSeleccionarDatosporID("Competencia","Eliminar");
		}
		else if (clic.equals("Reglas Ver"))
		{
			inicio.verDatosEntidad("Regla");
		}
		else if (clic.equals("Reglas Adicionar"))
		{
			inicio.verAdicionarDatos("Regla");
		}
		else if (clic.equals("Reglas Modificar"))
		{
			inicio.verSelecionarReglaConocimiento("Modificar");
		}
		else if (clic.equals("Reglas Eliminar"))
		{
			inicio.verSelecionarReglaConocimiento("Eliminar");
		}
		else if (clic.equals("Cerrar"))
		{
			dispose();
		}
		else if (clic.equals("Guias de Usuario"))
		{
			inicio.verGuiasUsuario(17,22);
		}
		else if (clic.equals("Acerca de SESP"))
		{
			inicio.verAcercadeSesp();
		}
	}
}