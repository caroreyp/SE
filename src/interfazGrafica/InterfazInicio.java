package interfazGrafica;

import java.awt.*;
import javax.swing.*;

import mundo.*;
import basedeDatos.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Dimension;

import java.io.*;
import java.nio.MappedByteBuffer; 
import java.nio.channels.FileChannel; 

import java.util.regex.*;

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
public class InterfazInicio extends JFrame implements ActionListener
{
	
	/**
	 * Son las relaciones entre la clase InterfazInicio y las demas clases de la Interfaz Grafica.
	 * Estas clases representan las ventanas o dialogos utilizados por los usuarios administradores y candidatos.
	 */
	private PruebaCandidatoI pruevacandidato;
	private RegistrarCandidato datos;
	private CrearAdministrador crearadmin;
	private ValidarContraseña contraseña;
	private ValidarCandidato candidato;
	private SeleccionarTipoPrueba seleccionarprueba;
	private VacanteExclusivo exclusivo;
	private VacanteMultiple multiple;
	private SesionAdministradorI sesionadministrador;
	private SesionCandidatoI sesioncandidato;
	private CambiarContraseña cambiarcontraseña;
	private AsignarPermiso asignarpermiso;
	private VerResultado verresultado;
	private ResultadoPrueba resultadoprueba;
	private AcercadeSesp acercadesesp;
	private VerPermisos verpermisos;
	private BaseConocimiento baseconocimiento;
	private SeleccionarAccion seleccionaraccion;
	private VerDatosRoles  verdatosroles;
	private VerDatosPruebas  verdatospruebas;
	private VerDatosPreguntas  verdatospreguntas;
	private SeleccionarDatosporID seleccionardatos;
	private AdicionarDatosRol adicionardatosrol;
	private ModificarDatosRol modificardatosrol;
	private AdicionarDatosPrueba adicionardatosprueba;
	private ModificarDatosPrueba modificardatosprueba;
	private AdicionarDatosPregunta adicionardatospregunta;
	private ModificarDatosPregunta modificardatospregunta;
	private CrearConvocatoria crearconvocatoria;
	private VerConvocatorias verconvocatorias;
	private SeleccionarConvocatoria seleccionarconvocatoria;
	private SeleccionarResultadosConvocatoria seleccionarresultadosconvo;
	private GuiasUsuario guiasusuario;
	private VerDescripcion verdescripcion;
	private SeleccionarResultadosRevisados seleccionarresultadosrevis;
	private SelecionarReglaConocimiento selecionarreglaconocimiento;
	private VerReglasConocimiento verreglasconocimiento;
	private AdicionarReglaConocimiento adicionarregla;
	private ModificarReglaConocimiento modificarregla;
	private EliminarReglaConocimiento eliminarregla;
	private VerDatosEscalas  verdatosescalas;
	private VerDatosCompetencias verdatoscompeten;
	private AdicionarDatosEscala adicionardatosescal;
	private AdicionarDatosCaracteristica adicionarcaracteristica;
	private AdicionarDatosCompetencia adicionarcompetencia;
	private ModificarDatosEscala modificarescala;
	private ModificarDatosCompetencia modificarcompetencia;
	private SeleccionarConvocatoriaAnalisis convocatoriaanalisis;
	private VerAnalisisResultados veranalisisresultados;
	private VerDatosCaracteristicas vercaracteristicas;
	private SeleccionarCaracterisiticasEscalaId seleccionarcaract;
	private ModificarDatosCaracteristica modificarcaracter;
	/*****************************************************************************/
	
	/**
	 * Es el vector que almacena las caracterisiticas de la escala que sera creada o modificada.
	 */
	private ArrayList caracteristicasescal;

	/**
	 * Es la relacion con la clase pricipal del mundo.
	 */
	private MotorInferencia motorinferencia;
	
	
	/**
	 * Son los atributos que definen la barra de menus de la vantana InterfazInicio.
	 */
	private JMenuBar menubar;
	private JMenu menuadmin;
	private JMenu menucandidato;
	private JMenu menuayuda;
	private JMenuItem itemsesionadmin;
	private JMenuItem itemcerrar;
	private JMenuItem itemregistrar;
	private JMenuItem itemsesioncandidat;
	private JMenuItem itemguias;
	private JMenuItem itemacercade;
	/*****************************************************************************/
	
	/**
	 * Es la relacion con el panel que contiene la imagen y los botones.
	 */
	private PanelInicio panelinicio;
	
	/**
	 * Es la variable que almacena el valor de numero de pixeles que tiene la pantalla de ancho.
	 */
	private int widthvetana;
	
	/**
	 * Es la variable que almacena el valor de numero de pixeles que tiene la pantalla de alto.
	 */
	private int heigthvetana;
	 
	 /**
	  * Es el metodo que construye e inicializa la interfaz de la aplicación
	  */	
	public InterfazInicio ()
	{
		setLayout(new FlowLayout());
        setTitle("Sistema Experto para la Seleccion de Personal - SESP");
		setResizable(false);
		setDefaultCloseOperation(2);
		
		//se obtiene la resolucion  de pantalla actual utilizada por el usuario.
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension tamaño = tk.getScreenSize();
		widthvetana = (int) tamaño.getWidth();
		heigthvetana = (int) tamaño.getHeight();
		setBounds(0,0,widthvetana,heigthvetana-40);
		
		ImageIcon icono =new ImageIcon("./images/IconosInterfaz/IconoVentana.PNG"); 
		Image imagenicono=icono.getImage();
		this.setIconImage(imagenicono);
		
		//se verifica si el tipo de letra esta instalado en el sisitema operativo.
		Font tipoletra = new Font( "dandelion in the spring", Font.TRUETYPE_FONT, 32 );
		String familiatipoletra = tipoletra.getFamily();
		if(familiatipoletra.equalsIgnoreCase("Dialog"))
		{
			instalarTipoLetra();
		}
		
		motorinferencia = new MotorInferencia();
		
		caracteristicasescal = new ArrayList();
		
		menubar = new JMenuBar();
		menubar.setBackground(new Color(239,239,239));
		menuadmin = new JMenu("Administrador");
		menuadmin.setForeground(Color.BLACK);
		menuadmin.setMnemonic(KeyEvent.VK_A);
		menucandidato = new JMenu("Candidato");
		menucandidato.setForeground(Color.BLACK);
		menucandidato.setMnemonic(KeyEvent.VK_C);
		menuayuda = new JMenu("Ayuda");
		menuayuda.setForeground(Color.BLACK);
		menuayuda.setMnemonic(KeyEvent.VK_Y);
		itemsesionadmin = new JMenuItem("Sesion Administrador");
		itemsesionadmin.setForeground(Color.BLACK);
		itemsesionadmin.addActionListener(this);
		itemsesionadmin.setActionCommand("Sesion Administrador");
		itemsesionadmin.setIcon(new ImageIcon("./images/IconosInterfaz/administrador.PNG"));
		itemcerrar = new JMenuItem("Cerrar");
		itemcerrar.setForeground(Color.BLACK);
		itemcerrar.addActionListener(this);
		itemcerrar.setActionCommand("Cerrar");
		itemcerrar.setIcon(new ImageIcon("./images/IconosInterfaz/cerrar.PNG"));
		itemregistrar = new JMenuItem("Registrar");
		itemregistrar.setForeground(Color.BLACK);
		itemregistrar.addActionListener(this);
		itemregistrar.setActionCommand("Registrar");
		itemregistrar.setIcon(new ImageIcon("./images/IconosInterfaz/adicionar.PNG"));
		itemsesioncandidat = new JMenuItem("Sesion Candidato");
		itemsesioncandidat.setForeground(Color.BLACK);
		itemsesioncandidat.addActionListener(this);
		itemsesioncandidat.setActionCommand("Sesion Candidato");
		itemsesioncandidat.setIcon(new ImageIcon("./images/IconosInterfaz/candidato.PNG"));
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
		menuadmin.add(itemsesionadmin);
		menuadmin.add(new JSeparator());
		menuadmin.add(itemcerrar);
		menucandidato.add(itemregistrar);
		menucandidato.add(itemsesioncandidat);
		menuayuda.add(itemguias);
		menuayuda.add(itemacercade);
		menubar.add(menuadmin);
		menubar.add(menucandidato);
		menubar.add(menuayuda);
		setJMenuBar(menubar);
		
		JPanel panelcolor = new JPanel(new FlowLayout());
		panelcolor.setPreferredSize(new Dimension (widthvetana-8,heigthvetana-102));
		panelcolor.setBackground(new Color(60,116,107));

		panelinicio = new PanelInicio(this, widthvetana, heigthvetana);
		panelcolor.add(panelinicio);
		add(panelcolor);
		instalarBaseDatos();
		List pruebas = null;
		try 
		{
		  Conector conector = new Conector();
		  conector.iniciarConexionBaseDatos();
		  pruebas= PruebaBD.listar(conector);
		  conector.terminarConexionBaseDatos();
		} 
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
		}
		if(pruebas != null)
		{
			actualizarFinalizacionConvocatorias();
			borrarSesionesAnteriores();
		}
	}
	
	/**
	 * Es el metodo que crea y visualiza el dialogo ValidarContraseña
	 */
	public void verValidarAdministrador()
	{
		contraseña = new ValidarContraseña(this);
		contraseña.setVisible(true);
	}
	
	/**
	 * Es el metodo que crea y visualiza el dialogo RegistrarCandidato
	 */
	public void verRegistrarCandidato()
	{
		datos = new RegistrarCandidato(this);
		datos.setVisible(true);
	}
	
	/**
	 * Es el metodo que crea y visualiza el dialogo ValidarCandidato
	 */
	public void verValidarCandidato()
	{
		candidato = new ValidarCandidato(this);
		candidato.setVisible(true);
	}
	
	/**
	 * Es el metodo que crea una sesion con el administrador que recibe como parametro.
	 */
	public void verSesionAdministrador(Administrador actualadmin)
	{
		Sesion sesio = new Sesion();
		GregorianCalendar calendario = new GregorianCalendar();
		String fechasesion = calendario.get(Calendar.YEAR)+"-"+(calendario.get(Calendar.MONTH)+1)+"-"+calendario.get(Calendar.DAY_OF_MONTH);
		String horainicial = calendario.get(Calendar.HOUR_OF_DAY)+":"+calendario.get(Calendar.MINUTE)+":"+calendario.get(Calendar.SECOND);
		sesio.setFechasesion(fechasesion);
		sesio.setHorainicial(horainicial);
		sesio.setHorafinal("NULL");
		try 
		{
			Conector conector = new Conector();
			conector.iniciarConexionBaseDatos();
			SesionBD.insertar(sesio, conector);
			conector.terminarConexionBaseDatos();
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
		}
		Sesion sesioningr = null;
		try 
		{
			Conector conector = new Conector();
			conector.iniciarConexionBaseDatos();
			sesioningr = SesionBD.buscarFechaHora(fechasesion, horainicial, conector);
			conector.terminarConexionBaseDatos();
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
		}
		if(sesioningr != null)
		{
			SesionAdministrador nuevasesion = new SesionAdministrador();
			nuevasesion.setIdsesion(sesioningr.getIdsesion());
			nuevasesion.setIdadministrador(actualadmin.getIdadmin());
			try 
			{
				Conector conector = new Conector();
				conector.iniciarConexionBaseDatos();
				SesionAdministradorBD.insertar(nuevasesion, conector);
				conector.terminarConexionBaseDatos();
				
				sesionadministrador = new SesionAdministradorI(this, actualadmin, sesioningr);
				sesionadministrador.setVisible(true);
			}
			catch (Exception e)
			{
				JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
			}
		}
	}
	
	/**
	 * Es el metodo que crea una sesion con el candidato que recibe como parametro.
	 */
	public void verSesionCandidato(Candidato actualcandidat)
	{
		Sesion sesio = new Sesion();
		GregorianCalendar calendario = new GregorianCalendar();
		String fechasesion = calendario.get(Calendar.YEAR)+"-"+(calendario.get(Calendar.MONTH)+1)+"-"+calendario.get(Calendar.DAY_OF_MONTH);
		String horainicial = calendario.get(Calendar.HOUR_OF_DAY)+":"+calendario.get(Calendar.MINUTE)+":"+calendario.get(Calendar.SECOND);
		sesio.setFechasesion(fechasesion);
		sesio.setHorainicial(horainicial);
		sesio.setHorafinal("NULL");
		try 
		{
			Conector conector = new Conector();
			conector.iniciarConexionBaseDatos();
			SesionBD.insertar(sesio, conector);
			conector.terminarConexionBaseDatos();
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
		}
		Sesion sesioningr = null;
		try 
		{
			Conector conector = new Conector();
			conector.iniciarConexionBaseDatos();
			sesioningr = SesionBD.buscarFechaHora(fechasesion, horainicial, conector);
			conector.terminarConexionBaseDatos();
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
		}
		if(sesioningr != null)
		{
			SesionCandidato nuevasesion = new SesionCandidato();
			nuevasesion.setIdsesion(sesioningr.getIdsesion());
			nuevasesion.setIdcandidato(actualcandidat.getIdcandidato());
			try 
			{
				Conector conector = new Conector();
				conector.iniciarConexionBaseDatos();
				SesionCandidatoBD.insertar(nuevasesion, conector);
				conector.terminarConexionBaseDatos();
				
				sesioncandidato = new SesionCandidatoI(this, actualcandidat, sesioningr);
				sesioncandidato.setVisible(true);
			}
			catch (Exception e)
			{
				JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
			}
		}
	}
	
	/**
	 * Es el metodo que crea y visualiza el dialogo verSeleccionarTipoPrueba
	 */
	public void verSeleccionarTipoPrueba(Candidato actualcandidat)
	{
		actualizarFinalizacionConvocatorias();
		List pruebas = null;
		try 
		{
		  Conector conector = new Conector();
		  conector.iniciarConexionBaseDatos();
		  pruebas= PruebaBD.listar(conector);
		  conector.terminarConexionBaseDatos();
		} 
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
		}
		if(pruebas != null)
		{
			seleccionarprueba = new SeleccionarTipoPrueba(this, actualcandidat);
			seleccionarprueba.setVisible(true);
		}
	}
	
	/**
	 * Es el metodo que crea y visualiza el dialogo VacanteExclusivo
	 */
	public void verVacanteExclusivo(String convocatoriaselecconada, Candidato actualcandidat)
	{
		exclusivo = new VacanteExclusivo(this, convocatoriaselecconada, actualcandidat);
		exclusivo.setVisible(true);
	}
	
	/**
	 * Es el metodo que crea y visualiza el dialogo VacanteMultiple
	 */
	public void verVacanteMultiple(String convocatoriaselecconada, Candidato actualcandidat)
	{
		multiple = new VacanteMultiple(this, convocatoriaselecconada, actualcandidat);
		multiple.setVisible(true);
	}
	
	/**
	 * Es el metodo que crea y visualiza el dialogo PruebaCandidatoI, el cual permite al candidato presentar la prueba.
	 */
	public void verPruevaCandidato(Candidato actualcandidato, Prueba pruebaselect, Convocatoria convoselect, ArrayList rolesselect)
	{
		List preguntas = null;
	    try 
	    {
		    Conector conector = new Conector();
		    conector.iniciarConexionBaseDatos();
		    preguntas= PreguntaBD.listar(conector);
		    conector.terminarConexionBaseDatos();
	    } catch (Exception e){
		    JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
	    }
	    if(preguntas != null)
	    {
	    	if(actualcandidato != null)
	    	{
	    		if(pruebaselect != null)
		    	{
	    			pruevacandidato = new PruebaCandidatoI(this, actualcandidato, pruebaselect, convoselect, rolesselect);
	    			pruevacandidato.setVisible(true);
		    	}
	    		else
				{
					JOptionPane.showMessageDialog(this,"No se encontro el tipo de prueba seleccionado.","Presentar Prueba", JOptionPane.WARNING_MESSAGE,new ImageIcon("./images/IconosInterfaz/advertencia.PNG"));
				}
	    	}
	    	else
			{
				JOptionPane.showMessageDialog(this,"No se encontro el candidato para preentar la prueba.","Presentar Prueba", JOptionPane.WARNING_MESSAGE,new ImageIcon("./images/IconosInterfaz/advertencia.PNG"));
			}
	    }
	}
	
	/**
	 * Es el metodo que crea y visualiza el dialogo CambiarContraseña
	 */
	public void verCambiarContraseña(Administrador actualadmin)
	{
		cambiarcontraseña = new CambiarContraseña(this, actualadmin);
		cambiarcontraseña.setVisible(true);
	}
	
	/**
	 * Es el metodo que crea y visualiza el dialogo AsignarPermiso
	 */
	public void verAsignarPermiso(Administrador adminactual)
	{
		asignarpermiso = new AsignarPermiso(this, adminactual);
		asignarpermiso.setVisible(true);
	}
	
	/**
	 * Es el metodo que crea y visualiza el dialogo VerResultado
	 */
	public void verVerResultado(String tiporesultado, String convoseleccionada)
	{
		verresultado = new VerResultado(this, tiporesultado, convoseleccionada);
		verresultado.setVisible(true);
	}
	
	/**
	 * Es el metodo que crea y visualiza el dialogo ResultadoPrueba
	 */
	public void verResultadoPrueba(PruebaCandidato pruebacselec)
	{
		resultadoprueba = new ResultadoPrueba(this, pruebacselec);
		resultadoprueba.setVisible(true);
	}
	
	/**
	 * Es el metodo que crea y visualiza el dialogo DatosAdministrador
	 */
	public void verDatosAdministrador()
	{
		crearadmin = new CrearAdministrador(this);
		crearadmin.setVisible(true);
	}
	
	/**
	 * Es el metodo que crea y visualiza el dialogo AcercadeSesp
	 */
	public void verAcercadeSesp()
	{
		acercadesesp = new AcercadeSesp(this);
		acercadesesp.setVisible(true);
	}
	
	/**
	 * Es el metodo que crea y visualiza el dialogo VerPermisos
	 */
	public void verVerPermisos(Permiso permisocandidat)
	{
		verpermisos = new VerPermisos(this, permisocandidat);
		verpermisos.setVisible(true);
	}

	/**
	 * Es el metodo que crea y visualiza el dialogo BaseConocimiento
	 */
	public void verBaseConocimiento()
	{
		baseconocimiento = new BaseConocimiento(this);
		baseconocimiento.setVisible(true);
	}
	
	/**
	 * Es el metodo que crea y visualiza el dialogo SeleccionarAccion
	 */
	public void verSeleccionarAccion(String entidad)
	{
		seleccionaraccion = new SeleccionarAccion(this, entidad);
		seleccionaraccion.setVisible(true);
	}
	
	/**
	 * Es el metodo que crea y visualiza el dialogo VerDatos segun el nombre de la entidad que recibe como parametro.
	 */
	public void verDatosEntidad(String entidad)
	{
		if(entidad.equalsIgnoreCase("Rol"))
		{
			verdatosroles = new VerDatosRoles(this);
			verdatosroles.setVisible(true);
		}
		if(entidad.equalsIgnoreCase("Prueba"))
		{
			verdatospruebas = new VerDatosPruebas(this);
			verdatospruebas.setVisible(true);
		}
		if(entidad.equalsIgnoreCase("Pregunta"))
		{
			verdatospreguntas = new VerDatosPreguntas(this);
			verdatospreguntas.setVisible(true);
		}
		if(entidad.equalsIgnoreCase("Escala"))
		{
			verdatosescalas = new VerDatosEscalas(this);
			verdatosescalas.setVisible(true);
		}
		if(entidad.equalsIgnoreCase("Competencia"))
		{
			verdatoscompeten = new VerDatosCompetencias(this);
			verdatoscompeten.setVisible(true);
		}
		if(entidad.equalsIgnoreCase("Regla"))
		{
			verSelecionarReglaConocimiento("Ver");
		}
	}
	
	/**
	 * Es el metodo que crea y visualiza el dialogo AdicionarDatos segun el nombre de la entidad que recibe como parametro.
	 */
	public void verAdicionarDatos(String entidad)
	{
		if(entidad.equalsIgnoreCase("Rol"))
		{
			adicionardatosrol = new AdicionarDatosRol(this);
			adicionardatosrol.setVisible(true);
		}
		if(entidad.equalsIgnoreCase("Prueba"))
		{
			adicionardatosprueba = new AdicionarDatosPrueba(this);
			adicionardatosprueba.setVisible(true);
		}
		if(entidad.equalsIgnoreCase("Pregunta"))
		{
			adicionardatospregunta = new AdicionarDatosPregunta(this);
			adicionardatospregunta.setVisible(true);
		}
		if(entidad.equalsIgnoreCase("Escala"))
		{
			adicionardatosescal = new AdicionarDatosEscala(this);
			adicionardatosescal.setVisible(true);
			caracteristicasescal.clear();
		}
		if(entidad.equalsIgnoreCase("Competencia"))
		{
			adicionarcompetencia = new AdicionarDatosCompetencia(this);
			adicionarcompetencia.setVisible(true);
		}
		if(entidad.equalsIgnoreCase("Regla"))
		{
			verSelecionarReglaConocimiento("Adicionar");
		}
	}
	
	/**
	 * Es el metodo que crea y visualiza el dialogo AdicionarDatosCaracteristica
	 */
	public void verAdicionarDatosCaracteristica()
	{
		adicionarcaracteristica = new AdicionarDatosCaracteristica(this);
		adicionarcaracteristica.setVisible(true);
	}
	
	/**
	 * Es el metodo que adiciona la caracterisitica que recibe como parametro al vector caracteristicasescal, para luego ser agregada a la escala actual.
	 * @param nuevacaracter
	 */
	public void prepararCaracterisiticaEscala(Caracteristica nuevacaracter)
	{
		caracteristicasescal.add(nuevacaracter);
	}
	
	/**
	 * Es el metodo que adiciona las caracterisiticas del vector caracteristicasescal a la escala de nombre que recibe como parametro.
	 */
	public void adicionarCaracteristicasEscala(String nombredigitado)
	{
		Escala existeescala = null;
		try 
		{
			Conector conectora = new Conector();
			conectora.iniciarConexionBaseDatos();
			existeescala = EscalaBD.buscarNombre(nombredigitado, conectora);
			conectora.terminarConexionBaseDatos();
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
		}
		if(existeescala != null)
		{
			for (int i = 0; i < caracteristicasescal.size(); i++) 
			{
				Caracteristica caractaux = (Caracteristica)caracteristicasescal.get(i);
				caractaux.setIdescala(existeescala.getIdescala());
				try 
	    		{
    				Conector conectorc = new Conector();
	    			conectorc.iniciarConexionBaseDatos();
	    			CaracteristicaBD.insertar(caractaux, conectorc);
    				conectorc.terminarConexionBaseDatos();
	    		}
	    		catch (Exception e)
	    		{
	    			JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
	    		}
			}
			caracteristicasescal.clear();
		}
		else
		{
			JOptionPane.showMessageDialog(this,"La escala con el nombre "+nombredigitado+" no existe.","Adicionar Caracteristicas", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
		}
	}
	
	/**
	 * Es el metodo que crea y visualiza el dialogo SeleccionarDatosporID
	 */
	public void verSeleccionarDatosporID(String entidad, String accion)
	{
		seleccionardatos = new SeleccionarDatosporID(this,entidad,accion);
		seleccionardatos.setVisible(true);
	}
	
	/**
	 * Es el metodo que crea y visualiza el dialogo ModificarDatos segun el nombre de la entidad que recibe como parametro.
	 */
	public void verModificarDatosEntidad(String entidad, ArrayList entidadencontrada)
	{
		if(entidad.equalsIgnoreCase("Rol"))
		{
			Rol encontrado = (Rol)entidadencontrada.get(0);
			modificardatosrol = new ModificarDatosRol(this, encontrado);
			modificardatosrol.setVisible(true);
		}
		if(entidad.equalsIgnoreCase("Prueba"))
		{
			Prueba encontrado = (Prueba)entidadencontrada.get(0);
			modificardatosprueba = new ModificarDatosPrueba(this, encontrado);
			modificardatosprueba.setVisible(true);
		}
		if(entidad.equalsIgnoreCase("Pregunta"))
		{
			Pregunta encontrado = (Pregunta)entidadencontrada.get(0);
			modificardatospregunta = new ModificarDatosPregunta(this, encontrado);
			modificardatospregunta.setVisible(true);
		}
		if(entidad.equalsIgnoreCase("Escala"))
		{
			Escala encontrado = (Escala)entidadencontrada.get(0);
			modificarescala = new ModificarDatosEscala(this, encontrado);
			modificarescala.setVisible(true);
			caracteristicasescal.clear();
		}
		if(entidad.equalsIgnoreCase("Competencia"))
		{
			Competencia encontrado = (Competencia)entidadencontrada.get(0);
			modificarcompetencia = new ModificarDatosCompetencia(this, encontrado);
			modificarcompetencia.setVisible(true);
		}
		if(entidad.equalsIgnoreCase("Caracteristica"))
		{
			Caracteristica encontrado = (Caracteristica)entidadencontrada.get(0);
			modificarcaracter = new ModificarDatosCaracteristica(this, encontrado);
			modificarcaracter.setVisible(true);
		}
	}
	
	/**
	 * Es el metodo que crea y visualiza el dialogo SeleccionarConvocatoriaAnalisis
	 */
	public void verSeleccionarConvocatoriaAnalisis()
	{
		convocatoriaanalisis = new SeleccionarConvocatoriaAnalisis(this);
		convocatoriaanalisis.setVisible(true);
	}
	
	/**
	 * Es el metodo que crea y visualiza el dialogo VerAnalisisResultados
	 */
	public void verVerAnalisisResultados(String convocator)
	{
		veranalisisresultados = new VerAnalisisResultados(this, convocator);
		veranalisisresultados.setVisible(true);
	}
	
	/**
	 * Es el metodo que crea y visualiza el dialogo VerDatosCaracteristicas
	 */
	public void verVerDatosCaracteristicas(List caracteristicas)
	{
		vercaracteristicas = new VerDatosCaracteristicas(this, caracteristicas);
		vercaracteristicas.setVisible(true);
	}
	
	/**
	 * Es el metodo que crea y visualiza el dialogo SeleccionarCaracterisiticasEscalaId
	 */
	public void verSeleccionarCaracterisiticasEscalaId()
	{
		seleccionarcaract = new SeleccionarCaracterisiticasEscalaId(this);
		seleccionarcaract.setVisible(true);
	}
	
	/**
	 * Es el metodo que elimina la entidad del vector entidadencontrada segun el nombre de la entidad que recibe como parametro.
	 */
	public void eliminarDatosEnEntidad(String entidad, ArrayList entidadencontrada)
	{
		if(entidad.equalsIgnoreCase("Rol"))
		{
			Rol encontrado = (Rol)entidadencontrada.get(0);
			int respuesta = JOptionPane.showConfirmDialog(this,"Los datos seleccionados seran eliminados de forma permanente"+"\n"+"                       ¿Desea eliminarlos definitivamente?","Confirmacion para eliminar",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
	        if (respuesta == JOptionPane.YES_OPTION){
	        	try
		        {
		            Conector conector = new Conector();
		            conector.iniciarConexionBaseDatos();
		            RolBD.eliminar(encontrado.getIdrol(), conector);
		            conector.terminarConexionBaseDatos();
		            JOptionPane.showMessageDialog(this,"Los datos seleccionados han sido eliminados.","Elininar Datos",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
		        }
		        catch (Exception e)
				{
		        	JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
				}
	        }
		}
		if(entidad.equalsIgnoreCase("Prueba"))
		{
			Prueba encontrado = (Prueba)entidadencontrada.get(0);
			int respuesta = JOptionPane.showConfirmDialog(this,"Los datos seleccionados seran eliminados de forma permanente"+"\n"+"                       ¿Desea eliminarlos definitivamente?","Confirmacion para eliminar",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
	        if (respuesta == JOptionPane.YES_OPTION){
	        	String nombrepru = encontrado.getNombre();
	        	if(nombrepru.equalsIgnoreCase("Vacante Exclusivo") || nombrepru.equalsIgnoreCase("Vacante Multiple") || nombrepru.equalsIgnoreCase("Prueba Libre"))
	        	{
	        		JOptionPane.showMessageDialog(this,"La prueba "+nombrepru+" no puede ser eliminada por ser parte importante de la base del conocimiento.","Elininar Datos",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
	        	}
	        	else
	        	{
	        		try
			        {
			            Conector conector = new Conector();
			            conector.iniciarConexionBaseDatos();
			            PruebaBD.eliminar(encontrado.getIdprueba(), conector);
			            conector.terminarConexionBaseDatos();
			            JOptionPane.showMessageDialog(this,"Los datos seleccionados han sido eliminados.","Elininar Datos",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
			        }
			        catch (Exception e)
					{
			        	JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
					}
	        	}
	        }
		}
		if(entidad.equalsIgnoreCase("Pregunta"))
		{
			Pregunta encontrado = (Pregunta)entidadencontrada.get(0);
			int respuesta = JOptionPane.showConfirmDialog(this,"Los datos seleccionados seran eliminados de forma permanente"+"\n"+"                       ¿Desea eliminarlos definitivamente?","Confirmacion para eliminar",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
	        if (respuesta == JOptionPane.YES_OPTION){
	        	try
		        {
		            Conector conector = new Conector();
		            conector.iniciarConexionBaseDatos();
		            PreguntaBD.eliminar(encontrado.getIdpregunta(), conector);
		            conector.terminarConexionBaseDatos();
		            JOptionPane.showMessageDialog(this,"Los datos seleccionados han sido eliminados.","Elininar Datos",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
		        }
		        catch (Exception e)
				{
		        	JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
				}
	        }
		}
		if(entidad.equalsIgnoreCase("Escala"))
		{
			Escala encontrado = (Escala)entidadencontrada.get(0);
			int respuesta = JOptionPane.showConfirmDialog(this,"Los datos seleccionados seran eliminados de forma permanente"+"\n"+"                       ¿Desea eliminarlos definitivamente?","Confirmacion para eliminar",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
	        if (respuesta == JOptionPane.YES_OPTION){
	        	try
		        {
		            Conector conector = new Conector();
		            conector.iniciarConexionBaseDatos();
		            EscalaBD.eliminar(encontrado.getIdescala(), conector);
		            conector.terminarConexionBaseDatos();
		            JOptionPane.showMessageDialog(this,"Los datos seleccionados han sido eliminados.","Elininar Datos",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
		        }
		        catch (Exception e)
				{
		        	JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
				}
	        }
		}
		if(entidad.equalsIgnoreCase("Competencia"))
		{
			Competencia encontrado = (Competencia)entidadencontrada.get(0);
			int respuesta = JOptionPane.showConfirmDialog(this,"Los datos seleccionados seran eliminados de forma permanente"+"\n"+"                       ¿Desea eliminarlos definitivamente?","Confirmacion para eliminar",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
	        if (respuesta == JOptionPane.YES_OPTION){
	        	try
		        {
		            Conector conector = new Conector();
		            conector.iniciarConexionBaseDatos();
		            CompetenciaBD.eliminar(encontrado.getIdcompetencia(), conector);
		            conector.terminarConexionBaseDatos();
		            JOptionPane.showMessageDialog(this,"Los datos seleccionados han sido eliminados.","Elininar Datos",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/informacion.PNG"));
		        }
		        catch (Exception e)
				{
		        	JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/error.PNG"));
				}
	        }
		}
		if(entidad.equalsIgnoreCase("Caracteristica"))
		{
			Caracteristica encontrado = (Caracteristica)entidadencontrada.get(0);
			int respuesta = JOptionPane.showConfirmDialog(this,"Los datos seleccionados seran eliminados de forma permanente"+"\n"+"                       ¿Desea eliminarlos definitivamente?","Confirmacion para eliminar",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
	        if (respuesta == JOptionPane.YES_OPTION){
	        	try
		        {
		            Conector conector = new Conector();
		            conector.iniciarConexionBaseDatos();
		            CaracteristicaBD.eliminar(encontrado.getIdcaracteristica(), conector);
		            conector.terminarConexionBaseDatos();
		            JOptionPane.showMessageDialog(this,"Los datos seleccionados han sido eliminados.","Elininar Datos",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
		        }
		        catch (Exception e)
				{
		        	JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
				}
	        }
		}
	}
	
	/**
	 * Es el metodo que crea y visualiza el dialogo CrearConvocatoria
	 */
	public void verCrearConvocatoria(Administrador actualadmin)
	{
		crearconvocatoria = new CrearConvocatoria(this, actualadmin);
		crearconvocatoria.setVisible(true);
	}
	
	/**
	 * Es el metodo que crea y visualiza el dialogo VerConvocatorias
	 */
	public void verVerConvocatorias()
	{
		actualizarFinalizacionConvocatorias();
		verconvocatorias = new VerConvocatorias(this);
		verconvocatorias.setVisible(true);
	}
	
	/**
	 * Es el metodo que crea y visualiza el dialogo SeleccionarConvocatoria
	 */
	public void verSeleccionarConvocatoria(String tipoprueba, Candidato actualcandidat)
	{
		seleccionarconvocatoria = new SeleccionarConvocatoria(this, tipoprueba, actualcandidat);
		seleccionarconvocatoria.setVisible(true);
	}
	
	/**
	 * Es el metodo que crea y visualiza el dialogo SeleccionarResultadosConvocatoria
	 */
	public void verSeleccionarResultadosConvocatoria(String tiporesultado)
	{
		seleccionarresultadosconvo = new SeleccionarResultadosConvocatoria(this, tiporesultado);
		seleccionarresultadosconvo.setVisible(true);
	}
	
	/**
	 * Es el metodo que crea y visualiza el dialogo GuiasUsuario
	 */
	public void verGuiasUsuario(int numeroinicio, int numerofin)
	{
		guiasusuario = new GuiasUsuario(this, numeroinicio, numerofin);
		guiasusuario.setVisible(true);
	}
	
	/**
	 * Es el metodo que crea y visualiza el dialogo VerDescripcion
	 */
	public void verVerDescripcion()
	{
		verdescripcion = new VerDescripcion(this);
		verdescripcion.setVisible(true);
	}
	
	/**
	 * Es el metodo que crea y visualiza el dialogo SeleccionarResultadosRevisados
	 */
	public void verSeleccionarResultadosRevisados()
	{
		actualizarFinalizacionConvocatorias();
		seleccionarresultadosrevis = new SeleccionarResultadosRevisados(this);
		seleccionarresultadosrevis.setVisible(true);
	}
	
	/**
	 * Es el metodo que crea y visualiza el dialogo SelecionarReglaConocimiento
	 */
	public void verSelecionarReglaConocimiento(String accion)
	{
		selecionarreglaconocimiento = new SelecionarReglaConocimiento(this, accion);
		selecionarreglaconocimiento.setVisible(true);
	}
	
	/**
	 * Es el metodo que crea y visualiza el dialogo VerReglasConocimiento
	 */
	public void verVerReglasConocimiento(String desdeentidad, String hastaentidad, List reglas)
	{
		verreglasconocimiento = new VerReglasConocimiento(this, desdeentidad, hastaentidad, reglas);
		verreglasconocimiento.setVisible(true);
	}
	
	/**
	 * Es el metodo que crea y visualiza el dialogo AdicionarReglaConocimiento
	 */
	public void verAdicionarReglaConocimiento(String desdeentidad, String hastaentidad)
	{
		adicionarregla = new AdicionarReglaConocimiento(this, desdeentidad, hastaentidad);
		adicionarregla.setVisible(true);
	}
	
	/**
	 * Es el metodo que crea y visualiza el dialogo ModificarReglaConocimiento
	 */
	public void verModificarReglaConocimiento(String desdeentidad, String hastaentidad)
	{
		modificarregla = new ModificarReglaConocimiento(this, desdeentidad, hastaentidad);
		modificarregla.setVisible(true);
	}
	
	/**
	 * Es el metodo que crea y visualiza el dialogo EliminarReglaConocimiento
	 */
	public void verEliminarReglaConocimiento(String desdeentidad, String hastaentidad)
	{
		eliminarregla = new EliminarReglaConocimiento(this, desdeentidad, hastaentidad);
		eliminarregla.setVisible(true);
	}
	
	/**
	 * Es el metodo coloca en blanco el campo de texto de los comentarios al revisar un resultado de prueba.
	 */
	public void colocarComentariosResultado()
	{
		resultadoprueba.colocarTextoComentarios("");
	}
	
	/**
	 * Es el metodo que encripta el texto que recibe como parametro y luego lo retorna en un formato especifico.
	 * @param password
	 * @return passwordencriptadosh
	 */
	public String encriptarPassword(String password)
	{
		String passwordencriptadomd="";
		String passwordencriptadosh="";
		for (int i = 0; i < 4; i++) {
			if(i==0){
				passwordencriptadomd=Encriptar.getStringMessageDigest(password, Encriptar.MD5);
				passwordencriptadosh=Encriptar.getStringMessageDigest(passwordencriptadomd, Encriptar.SHA1);
			}else{
				passwordencriptadomd=Encriptar.getStringMessageDigest(passwordencriptadosh, Encriptar.MD5);
				passwordencriptadosh=Encriptar.getStringMessageDigest(passwordencriptadomd, Encriptar.SHA1);
			}
		}
		return passwordencriptadosh;
	}
	
	/**
	 * Es el metodo que valida el password digitado con el encriptado que esta almacenado en la base de datos.
	 * Se tienen en cuenta los parametros recibidos y se retorna true si corresponden o false en caso contrario. 
	 * @param passworddigitado
	 * @param passwordalmacenadobd
	 * @return passwordcorreto
	 */
	public boolean validarPasswordDigitado(String passworddigitado, String passwordalmacenadobd)
	{
		boolean passwordcorreto=false;
		String passwordencriptadomd="";
		String passwordencriptadosh="";
		for (int i = 0; i < 4; i++) {
			if(i==0){
				passwordencriptadomd=Encriptar.getStringMessageDigest(passworddigitado, Encriptar.MD5);
				passwordencriptadosh=Encriptar.getStringMessageDigest(passwordencriptadomd, Encriptar.SHA1);
			}else{
				passwordencriptadomd=Encriptar.getStringMessageDigest(passwordencriptadosh, Encriptar.MD5);
				passwordencriptadosh=Encriptar.getStringMessageDigest(passwordencriptadomd, Encriptar.SHA1);
			}
		}
		if(passwordencriptadosh.equalsIgnoreCase(passwordalmacenadobd)){
			passwordcorreto=true;
		}
		return passwordcorreto;
	}
	
	/**
	 * Metodo que verifica si existe la base de datos con el nombre seleccionado y si no existe la instala en el servidor.
	 */
	public void instalarBaseDatos()
	{
		String nombrebuscado = "information_schema";
		ArrayList parametrosbd = parametrosConectorBaseDatos();
		if(parametrosbd != null)
		{
			if(parametrosbd.size()>0)
			{
				nombrebuscado =(String)parametrosbd.get(0);
			}
		}
		String nombrebd = null;
		try 
		{
			Conector conector = new Conector("information_schema");
			conector.iniciarConexionBaseDatos();
			nombrebd = CrearBD.buscarBaseDatos(nombrebuscado, conector);
			conector.terminarConexionBaseDatos();
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
		}
		if(nombrebd == null)
		{
			int numerotablascreadas = 0;
			try 
			{
				Conector conectora = new Conector("information_schema");
				conectora.iniciarConexionBaseDatos();
				CrearBD.crearBaseDatos(nombrebuscado, conectora);
				conectora.terminarConexionBaseDatos();
				
				for (int i = 1; i < 20; i++) 
				{
					String nombretabla = i+".sql";
					String contenidotabla = ejecutarCodigoSQLtablas(nombretabla);
					if(!contenidotabla.equalsIgnoreCase(""))
					{
						Conector conectorb = new Conector(nombrebuscado);
						conectorb.iniciarConexionBaseDatos();
						CrearBD.ejecutarCodigoSQL(contenidotabla, conectorb);
						conectorb.terminarConexionBaseDatos();
						numerotablascreadas++;
					}
					else
					{
						Conector conectorg = new Conector(nombrebuscado);
						conectorg.iniciarConexionBaseDatos();
						CrearBD.ejecutarCodigoSQL("DROP DATABASE "+nombrebuscado, conectorg);
						conectorg.terminarConexionBaseDatos();
						JOptionPane.showMessageDialog(this,"No se encontro el archivo "+nombretabla,"Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
					}
				}
				if(numerotablascreadas == 19)
				{
					ejecutarCodigoSQLdatos(nombrebuscado);
				}
			}
			catch (Exception e)
			{
				JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
			}
		}
	}
	
	/**
	 * Es el metodo que actualiza las convocatorias para establecer si estan finalizadas o no, teniendo en cuenta la fecha actual del sisitema y la fecha final almacenada. 
	 */
	public void actualizarFinalizacionConvocatorias()
	{
		int numeroconvocatorias = 0;//numero de convocatorias en la BD
		List convocatorias = null;
		try
        {
            Conector conector = new Conector();
            conector.iniciarConexionBaseDatos();
            convocatorias= ConvocatoriaBD.listar(conector);
            conector.terminarConexionBaseDatos();
        }
        catch (Exception e)
		{
        	JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
		}
        if(convocatorias != null)
        {
        	numeroconvocatorias = convocatorias.size();
        }
        for (int i = 0; i < numeroconvocatorias; i++)
		{
			Convocatoria convocatoriaaux = (Convocatoria)convocatorias.get(i);
			String fechafinal = convocatoriaaux.getFechafinal();
			String horafinal = convocatoriaaux.getHorafinal();
			boolean esvigente = fechaVigente(fechafinal, horafinal);
			if(esvigente == false)
			{
				convocatoriaaux.setFinalizada("Si");
				try 
	    		{
	    			Conector conector = new Conector();
	    			conector.iniciarConexionBaseDatos();
	    			ConvocatoriaBD.actualizar(convocatoriaaux, conector);
	    			conector.terminarConexionBaseDatos();
	    		}
	    		catch (Exception e)
	    		{
	    			JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
	    		}
			}
		}
	}
	
	/**
	 * Es el metodo que actualiza las convocatorias para establecer si estan finalizadas o no, teniendo en cuenta la fecha actual del sisitema y la fecha final almacenada. 
	 */
	public void borrarSesionesAnteriores()
	{
		int numerosesiones = 0;//numero de convocatorias en la BD
		List sesiones = null;
		try
        {
            Conector conector = new Conector();
            conector.iniciarConexionBaseDatos();
            sesiones= SesionBD.listar(conector);
            conector.terminarConexionBaseDatos();
        }
        catch (Exception e)
		{
        	JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
		}
        if(sesiones != null)
        {
        	numerosesiones = sesiones.size();
        }
        for (int i = 0; i < numerosesiones; i++)
		{
        	Sesion sesionaux = (Sesion)sesiones.get(i);
			int idsesion = sesionaux.getIdsesion();
			String fechasesion = sesionaux.getFechasesion();
			boolean esanterior = fechaSesionAnterior(fechasesion);
			if(esanterior == true)
			{
				try 
	    		{
	    			Conector conector = new Conector();
	    			conector.iniciarConexionBaseDatos();
	    			SesionBD.eliminar(idsesion, conector);
	    			conector.terminarConexionBaseDatos();
	    		}
	    		catch (Exception e)
	    		{
	    			JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
	    		}
			}
		}
	}
	
	/**
	 * Es el metodo que valida la fecha y hora final recibidas como parametro con las fecha y hora actual del sistema.
	 * Se retorna true si la fecha y hora de los paramatros estan vigentes, en caso contrario false.
	 */
	public boolean fechaVigente(String fechafinal, String horafinal)
	{
		boolean vigente = true;
		GregorianCalendar calendario = new GregorianCalendar();
		int añoactual = calendario.get(Calendar.YEAR);
		int mesactual = (calendario.get(Calendar.MONTH)+1);
		int diaactual = calendario.get(Calendar.DAY_OF_MONTH);
		String fechaactual = añoactual+"-"+mesactual+"-"+diaactual;
		int horasactuales = calendario.get(Calendar.HOUR_OF_DAY);
		int minutosactuales = calendario.get(Calendar.MINUTE);
		int segundosactuales = calendario.get(Calendar.SECOND);
		String horaactual = horasactuales+":"+minutosactuales+":"+segundosactuales;
		
		int añofinal = 0;
		int mesfinal = 0;
		int diafinal = 0;
		
		int horasfinales = 0;
		int minutosfinales = 0;
		int segundosfinales = 0;

		String numerofecha = "";
		for (int i = 0; i < (fechafinal.length()); i++) 
		{
			char digito = fechafinal.charAt(i);
			if(digito == '0' || digito == '1' || digito == '2' || digito == '3' || digito == '4' || digito == '5' || digito == '6' || digito == '7' || digito == '8' || digito == '9')
			{
				numerofecha += digito;
				if((i+1) == (fechafinal.length()))
				{
					if(añofinal == 0)
					{
						añofinal = Integer.parseInt(numerofecha);
						numerofecha = "";
					}
					else if(mesfinal == 0)
					{
						mesfinal = Integer.parseInt(numerofecha);
						numerofecha = "";
					}
					else if(diafinal == 0)
					{
						diafinal = Integer.parseInt(numerofecha);
						numerofecha = "";
					}
				}
			}
			else
			{
				if(añofinal == 0)
				{
					añofinal = Integer.parseInt(numerofecha);
					numerofecha = "";
				}
				else if(mesfinal == 0)
				{
					mesfinal = Integer.parseInt(numerofecha);
					numerofecha = "";
				}
				else if(diafinal == 0)
				{
					diafinal = Integer.parseInt(numerofecha);
					numerofecha = "";
				}
			}
		}
		String numerohora = "";
		for (int i = 0; i < (horafinal.length()); i++) 
		{
			char digito = horafinal.charAt(i);
			if(digito == '0' || digito == '1' || digito == '2' || digito == '3' || digito == '4' || digito == '5' || digito == '6' || digito == '7' || digito == '8' || digito == '9')
			{
				numerohora += digito;
				if((i+1) == (horafinal.length()))
				{
					if(horasfinales == 0)
					{
						horasfinales = Integer.parseInt(numerohora);
						numerohora = "";
					}
					else if(minutosfinales == 0)
					{
						minutosfinales = Integer.parseInt(numerohora);
						numerohora = "";
					}
					else if(segundosfinales == 0)
					{
						segundosfinales = Integer.parseInt(numerohora);
						numerohora = "";
					}
				}
			}
			else
			{
				if(horasfinales == 0)
				{
					horasfinales = Integer.parseInt(numerohora);
					numerohora = "";
				}
				else if(minutosfinales == 0)
				{
					minutosfinales = Integer.parseInt(numerohora);
					numerohora = "";
				}
				else if(segundosfinales == 0)
				{
					segundosfinales = Integer.parseInt(numerohora);
					numerohora = "";
				}
			}
		}
		String fechafinal2 = añofinal+"-"+mesfinal+"-"+diafinal;
		String horafinal2 = horasfinales+":"+minutosfinales+":"+segundosfinales;
		if(añoactual >= añofinal)
		{
			if(mesactual >= mesfinal)
			{
				if(diaactual >= diafinal)
				{
					if(horasactuales >= horasfinales)
					{
						if(minutosactuales >= minutosfinales)
						{
							vigente = false;
						}
					}
				}
			}
		}
		return vigente;
	}
	
	/**
	 * Es el metodo que verifica con la fecha actual del sistema la fecha que recibe como parametro.
	 * Si la fecha de sesion del paramentro es de mas de tres dias de anterioridad con relacion al fecha actual, esta sesion se elimina da la base de datos.
	 * @param fechasesion
	 * @return anterior
	 */
	public boolean fechaSesionAnterior(String fechasesion)
	{
		boolean anterior = false;
		GregorianCalendar calendario = new GregorianCalendar();
		int añoactual = calendario.get(Calendar.YEAR);
		int mesactual = (calendario.get(Calendar.MONTH)+1);
		int diaactual = calendario.get(Calendar.DAY_OF_MONTH);
		String fechaactual = añoactual+"-"+mesactual+"-"+diaactual;
		
		int añosesion = 0;
		int messesion = 0;
		int diasesion = 0;
		
		String numerofecha = "";
		for (int i = 0; i < (fechasesion.length()); i++) 
		{
			char digito = fechasesion.charAt(i);
			if(digito == '0' || digito == '1' || digito == '2' || digito == '3' || digito == '4' || digito == '5' || digito == '6' || digito == '7' || digito == '8' || digito == '9')
			{
				numerofecha += digito;
				if((i+1) == (fechasesion.length()))
				{
					if(añosesion == 0)
					{
						añosesion = Integer.parseInt(numerofecha);
						numerofecha = "";
					}
					else if(messesion == 0)
					{
						messesion = Integer.parseInt(numerofecha);
						numerofecha = "";
					}
					else if(diasesion == 0)
					{
						diasesion = Integer.parseInt(numerofecha);
						numerofecha = "";
					}
				}
			}
			else
			{
				if(añosesion == 0)
				{
					añosesion = Integer.parseInt(numerofecha);
					numerofecha = "";
				}
				else if(messesion == 0)
				{
					messesion = Integer.parseInt(numerofecha);
					numerofecha = "";
				}
				else if(diasesion == 0)
				{
					diasesion = Integer.parseInt(numerofecha);
					numerofecha = "";
				}
			}
		}
		if((añosesion) <= añoactual)
		{
			if((messesion) <= mesactual)
			{
				if((diasesion+3) <= diaactual)
				{
					anterior = true;
				}	
			}
		}		
		return anterior;
	}
	
	/**
	 * Es el metodo que retorna el valor de la variable widthvetana
	 */
	public int getWidthvetana() 
	{
		return widthvetana;
	}

	/**
	 * Es el metodo que retorna el valor de la variable heigthvetana
	 */
	public int getHeigthvetana() 
	{
		return heigthvetana;
	}
	
	/**
	 * Es el metodo que instala el tipo de letra utilizado en la interfaz grafica del sistema experto.
	 * Se copia el archivo de fuente en el directorio correspondiente a las fuentes o tipos de letra del sistema operativo.
	 */
	public void instalarTipoLetra()
	{
		try
        {          
            FileInputStream fIn =  new FileInputStream("./images/dandelion in the spring.ttf");
            FileOutputStream fOut = new FileOutputStream("C:/windows/fonts/dandelion in the spring.ttf");
           
            FileChannel fIChan = fIn.getChannel();
            FileChannel fOChan = fOut.getChannel();
           
            long fSize = fIChan.size();
           
            MappedByteBuffer mBuf = 
            fIChan.map(FileChannel.MapMode.READ_ONLY, 0, fSize); 
            fOChan.write(mBuf);//con esto copiamos el archivo
            
            //nunca olvidemos cerrar los archivos
            fIChan.close();
            fIn.close();
            fOChan.close();
            fOut.close();
            JOptionPane.showMessageDialog(this,"El tipo de letra ha sido instalada en el sistema operativo.","Instalar Tipo Letra", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
            JOptionPane.showMessageDialog(this,"Cierre el Sistema Experto - SESP y vuelva a iniciar el ejecutable.","Instalar Tipo Letra", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
        }
        catch(Exception ef)
        {
        	JOptionPane.showMessageDialog(this,"Error al instalar el tipo de letra dandelion in the spring en el sistema operativo","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
        	JOptionPane.showMessageDialog(this,"Intente instalar manualmente el archivo (dandelion in the spring.ttf) que esta en la carpeta images o intente"+"\n"+"            copiar manualmente el archivo (dandelion in the spring.ttf) en el directorio C:/windows/fonts","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
    	}
	}
	
	/**
	 * Es el metodo que analiza las respuestas de la prueba de los candiadtos.
	 * Este genera los resultados de la prueba y se crea un registro con el resultado y los parametros recibidos en la base de datos.
	 * @param respuestas
	 * @param candidatoactual
	 * @param tipopruebaactual
	 * @param convocatoriaactual
	 * @param rolesactuales
	 * @param resultadoactual
	 */
	public void analizarRespuestasPrueba(ArrayList respuestas, Candidato candidatoactual, Prueba tipopruebaactual, Convocatoria convocatoriaactual, ArrayList rolesactuales, Resultado resultadoactual)
	{
		//se elimina el permiso asignado al candidato al terminar de presentar la prueba.
		Permiso permisocandidato = null;
		try 
		{
			Conector conector = new Conector();
			conector.iniciarConexionBaseDatos();
			permisocandidato = PermisoBD.buscarCandidato(candidatoactual.getIdcandidato(), conector);
			conector.terminarConexionBaseDatos();
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
		}
		if(permisocandidato != null)
		{
			try 
			{
				Conector conector = new Conector();
				conector.iniciarConexionBaseDatos();
				PermisoBD.eliminar(permisocandidato.getIdpermiso(), conector);
				conector.terminarConexionBaseDatos();
			}
			catch (Exception e)
			{
				JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
			}
		}
		//se calcula los puntajes, luego se analizan estos puntajes para determinar las observaciones, para finalmente almacenar los resultados de la prueba candidato
		motorinferencia.calcularPuntajesTodasLasEscalas(respuestas);
		motorinferencia.calcularPuntajeRoles(rolesactuales);
		motorinferencia.calcularPuntajeResultadoPrueba(resultadoactual, rolesactuales);
		motorinferencia.determinarObservacionesResultadoPrueba();
		motorinferencia.almacenarResultadoPruebaCandidato(candidatoactual, tipopruebaactual, convocatoriaactual);
	}
	
	/**
	 * Es el metodo que evalua si es valido el correo electronico o email recibido como parametro
	 * Se retorna true si es valido o false en caso contrario
	 * @param email
	 */
	public boolean validarEmail(String email) {         
		Pattern p = Pattern.compile("[a-zA-Z0-9]+[.[a-zA-Z0-9_-]+]*@[a-z0-9][\\w\\.-]*[a-z0-9]\\.[a-z][a-z\\.]*[a-z]$");
		Matcher m = p.matcher(email);        
		return m.matches();    
	}
	
	/**
	 * Es el metodo que obtiene y retorna los parametros de nombre de base de datos y host servidor, del archivo plano parametrosBD.txt.
	 * @return parametros
	 */
	public ArrayList parametrosConectorBaseDatos()
	{
		ArrayList parametros = new ArrayList(); 
		try
		{
			FileInputStream fr = new FileInputStream( "./images/parametrosBD.txt" );
	    	DataInputStream entrada = new DataInputStream(fr);
	        String parametro = "";
	        String valorparametro = "";
	        boolean esvalor = false;
	        while((parametro=entrada.readLine())!= null)
	        {
	        	for (int i = 0; i < parametro.length(); i++) 
	        	{
	        		if(esvalor == true)
	        		{
	        			valorparametro += parametro.charAt(i);
	        		}
	        		if(parametro.charAt(i) == ':')
	        		{
	        			esvalor = true;
	        		}
				}
	        	parametros.add(valorparametro.trim());
	        	valorparametro = "";
	        	esvalor = false;
	        }
		}
		catch (Exception e)
		{
		}
		return parametros;
	}
	
	/**
	 * Metodo obtiene el codigo del archivo que corresponda al nombre que entra como parametro.
	 * @param nombrearchivo
	 * @return codigosqlcrebas
	 */
	public String ejecutarCodigoSQLtablas(String nombrearchivo )
	{
		String codigosqlcrebas="";
		try
		{
			FileReader codigoarchivo = new FileReader ( "./images/BD/"+nombrearchivo );
			BufferedReader reader = new BufferedReader (codigoarchivo);
			String codigo = reader.readLine();
			while(codigo!= null)
			{
				codigosqlcrebas+=codigo+"\n";
				codigo = reader.readLine();
			}
		}
		catch (Exception e)
		{
		}
		return codigosqlcrebas;
	}
	
	/**
	 * Metodo que obtiene y ejecuta lineas de codigo de un archivo determinado en la base de datos del nombre que entra como parametro
	 * @param nombrebuscado
	 */
	public void ejecutarCodigoSQLdatos(String nombrebuscado)
	{
		try
		{
			FileReader codigoarchivo = new FileReader ( "./images/BD/Codigo SQL crebas datos.sql" );
			BufferedReader reader = new BufferedReader (codigoarchivo);
			String codigo = reader.readLine();
			while(codigo!= null)
			{
				if(!codigo.equalsIgnoreCase(""))
				{
					Conector conectord = new Conector(nombrebuscado);
					conectord.iniciarConexionBaseDatos();
					CrearBD.ejecutarCodigoSQL(codigo, conectord);
					conectord.terminarConexionBaseDatos();
				}
				codigo = reader.readLine();
			}
			JOptionPane.showMessageDialog(this,"Se Instalo la Base de Datos Correctamente.","Instalar Base de Datos", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
		}
		catch (Exception e)
		{
			try
			{
				Conector conectorg = new Conector(nombrebuscado);
				conectorg.iniciarConexionBaseDatos();
				CrearBD.ejecutarCodigoSQL("DROP DATABASE "+nombrebuscado, conectorg);
				conectorg.terminarConexionBaseDatos();
				JOptionPane.showMessageDialog(this,"No se encontro el archivo Codigo SQL crebas datos.sql","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
			}
			
		}
	}
	

	/**
	 * Es el metodo confirma antes de cerrar el Sistema Experto preguntando al usuario.
	 */
	public void dispose( ){
        int respuesta = JOptionPane.showConfirmDialog(this,"¿Cerrar el Sistema Experto SESP?","Confirmacion para salir",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
        if (respuesta == JOptionPane.YES_OPTION){
            super.dispose();
        }
    }
	
    /**
     * Ejecuta la acción que corresponde a la opción del menú obotones que fue seleccionada
     * @param evento Es el evento de seleccionar una opción del menú - evento!=null
     */
	public void actionPerformed(ActionEvent evento)
	{
		String clic = evento.getActionCommand();	
		if (clic.equals("Sesion Administrador"))
		{
			verValidarAdministrador();
		}
		else if (clic.equals("Cerrar"))
		{
			dispose();
		}
		else if (clic.equals("Registrar"))
		{
			verRegistrarCandidato();
		}
		else if (clic.equals("Sesion Candidato"))
		{
			verValidarCandidato();
		}
		else if (clic.equals("Guias de Usuario"))
		{
			verGuiasUsuario(1,6);
		}
		else if (clic.equals("Acerca de SESP"))
		{
			verAcercadeSesp();
		}
	}
	
    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Ejecuta la aplicación crea la ventana de inicio y la hace visible.
     * @param args Son los parámetros de la línea de comandos. No se deben usar.
     */
	public static void main(String[] args)
	{
		InterfazInicio interfaz = new InterfazInicio();
		interfaz.setVisible(true);
	}
}