package interfazGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.table.*;

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
public class PruebaCandidatoI extends JDialog implements ActionListener {

	private JButton btEvaluar;
	private JTable tbl;
	private ArrayList respuestas;
	private boolean salir;
	private boolean contestotodo;
	private JLabel separador1;
	private JLabel separador2;
	private JLabel separador3;
	private JLabel etiquetafecha;
	private JLabel etiquetadia;
	private JTextField diaactual;
	private JLabel etiquetanumerodia;
	private JTextField numerodia;
	private JLabel etiquetames;
	private JTextField mesactual;
	private JLabel etiquetaaño;
	private JTextField añoactual;
	private JLabel etiquetainicio;
	private JTextField horainicio;
	private JLabel etiquetafin;
	private JTextField horafin;
	private JLabel etiquetamaximo;
	private JTextField tiempomaximo;
	private JLabel etiquetarestante;
	private JTextField tiemporestante;
	private Thread hilo;
	private Object contador = new Object ();
	private int tiempomaximominutos;
	private GregorianCalendar cal;
	private InterfazInicio inicio;
	private Candidato candidatoactual;
	private Prueba tipopruebaactual;
	private Convocatoria convocatoriaactual;
	private ArrayList rolesactuales;
	private String fechaprueba;
	
	public PruebaCandidatoI(InterfazInicio interfaz, Candidato actualcandidato, Prueba pruebaselect, Convocatoria convoselect, ArrayList rolesselect)
	{
		super(interfaz, true);
		setLayout(new FlowLayout());
        setTitle("Prueba Candidato ("+pruebaselect.getNombre()+") - SESP");
		setResizable(false);
		setDefaultCloseOperation(2);
		int anchoventana = 1010;
		int altoventana =687;
		setBounds((interfaz.getWidthvetana()/2)-(anchoventana/2),(interfaz.getHeigthvetana()/2)-(altoventana/2),anchoventana,altoventana);
		
		ImageIcon icono =new ImageIcon("./images/IconosInterfaz/IconoVentana.PNG"); 
		Image imagenicono=icono.getImage();
		this.setIconImage(imagenicono);
		
		inicio = interfaz;
		candidatoactual = actualcandidato;
		tipopruebaactual = pruebaselect;
		convocatoriaactual = convoselect;
		rolesactuales = rolesselect;
		
		tiempomaximominutos=pruebaselect.getTiempomaximo();

		separador1 = new JLabel("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
		separador1.setForeground(new Color(7,63,64));
		separador2 = new JLabel("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
		separador2.setForeground(new Color(7,63,64));
		separador3 = new JLabel("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
		separador3.setForeground(new Color(7,63,64));
		
		cal = new GregorianCalendar();
		etiquetafecha=new JLabel("fecha actual en sistema");
		etiquetafecha.setForeground(new Color(7,63,64));
		
		fechaprueba = cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DAY_OF_MONTH);
		
		etiquetadia=new JLabel("        dia :");
		etiquetadia.setForeground(new Color(7,63,64));
		diaactual = new JTextField(8);
		int numerod=(cal.get(Calendar.DAY_OF_WEEK)-1);
		String nombredia="";
		if(numerod==1)
		{
			nombredia="Lunes";
		}
		if(numerod==2)
		{
			nombredia="Martes";
		}
		if(numerod==3)
		{
			nombredia="Miercoles";
		}
		if(numerod==4)
		{
			nombredia="Jueves";
		}
		if(numerod==5)
		{
			nombredia="Viernes";
		}
		if(numerod==6)
		{
			nombredia="Sabado";
		}
		if(numerod==0)
		{
			nombredia="Domingo";
		}
		diaactual.setText(nombredia);
		diaactual.setEditable(false);
		
		etiquetanumerodia=new JLabel(" # dia :");
		etiquetanumerodia.setForeground(new Color(7,63,64));
		numerodia = new JTextField(3);
		numerodia.setText(""+cal.get(Calendar.DAY_OF_MONTH));
		numerodia.setEditable(false);
		
		etiquetames=new JLabel("     mes :");
		etiquetames.setForeground(new Color(7,63,64));
		mesactual = new JTextField(8);
		int numeromes=(cal.get(Calendar.MONTH)+1);
		String nombremes="";
		if(numeromes==1)
		{
			nombremes="Enero";
		}
		if(numeromes==2)
		{
			nombremes="Febrero";
		}
		if(numeromes==3)
		{
			nombremes="Marzo";
		}
		if(numeromes==4)
		{
			nombremes="Abril";
		}
		if(numeromes==5)
		{
			nombremes="Mayo";
		}
		if(numeromes==6)
		{
			nombremes="Junio";
		}
		if(numeromes==7)
		{
			nombremes="Julio";
		}
		if(numeromes==8)
		{
			nombremes="Agosto";
		}
		if(numeromes==9)
		{
			nombremes="Septiembre";
		}
		if(numeromes==10)
		{
			nombremes="Octubre";
		}
		if(numeromes==11)
		{
			nombremes="Noviembre";
		}
		if(numeromes==12)
		{
			nombremes="Diciembre";
		}
		mesactual.setText(nombremes);
		mesactual.setEditable(false);
		
		etiquetaaño=new JLabel("      año :");
		etiquetaaño.setForeground(new Color(7,63,64));
		añoactual = new JTextField(4);
		añoactual.setText(""+cal.get(Calendar.YEAR));
		añoactual.setEditable(false);
		
		etiquetainicio=new JLabel("hora inicio");
		etiquetainicio.setForeground(new Color(7,63,64));
		horainicio = new JTextField(5);
		horainicio.setText(cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND));
		horainicio.setEditable(false);
		
		etiquetafin=new JLabel(" hora final ");
		etiquetafin.setForeground(new Color(7,63,64));
		horafin = new JTextField(5);
		horafin.setEditable(false);
		
		etiquetamaximo=new JLabel("tiempo maximo");
		etiquetamaximo.setForeground(new Color(7,63,64));
		tiempomaximo = new JTextField(4);
		tiempomaximo.setText(tiempomaximominutos+" : 00");
		tiempomaximo.setEditable(false);
		
		etiquetarestante=new JLabel("tiempo restante");
		etiquetarestante.setForeground(new Color(7,63,64));
		tiemporestante = new JTextField(4);
		tiemporestante.setEditable(false);
		
		contestotodo = false;
		salir = false;
		respuestas = new ArrayList();
		
		tbl = new JTable(new ComboBoxTableModel());
		
		// Create the combo box editor
	    JComboBox comboBox = new JComboBox(ComboBoxTableModel.getValidStates());
	    comboBox.setEditable(false);
	    DefaultCellEditor editor = new DefaultCellEditor(comboBox);

	    // Assign the editor to the second column
	    TableColumnModel tcm = tbl.getColumnModel();
	    tcm.getColumn(1).setCellEditor(editor);

	    // Set column widths
	    tcm.getColumn(0).setPreferredWidth(700);
	    tcm.getColumn(1).setPreferredWidth(100);

	    tbl.setForeground(Color.BLACK);
	    tbl.setGridColor(new Color(35,91,92));
	    
	    // Set row heighht
	    tbl.setRowHeight(25);

	    tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tbl.setPreferredScrollableViewportSize(tbl.getPreferredSize());

	    tbl.setFont(new Font( "Arial", Font.TRUETYPE_FONT, 15 ));
	    
	    String nombreconvoselec = "";
	    String nombrerolesselec = "";
	    if(!(pruebaselect.getNombre()).equalsIgnoreCase("Prueba Libre"))
	    {
	    	if(convoselect != null)
	    	{
	    		if(rolesselect != null)
		    	{
	    			nombreconvoselec = "Convocatoria seleccionada : "+convoselect.getNombre();
	    			nombrerolesselec = "   -   Rol(es) seleccionados: ";
	    	    	for (int i = 0; i < rolesselect.size(); i++) 
	    	    	{
	    				Rol rolaux = (Rol)rolesselect.get(i);
	    				nombrerolesselec+=rolaux.getNombre()+"  ";
	    			}
	    		    
		    	}
	    	}
	    }
	    tbl.setToolTipText("Haga Clic en los campos vacios de la tabla para ingresar su respuesta.");
	    JLabel etiqueta = new JLabel(actualcandidato.getNombres()+" conteste todas las preguntas del cuestionario.");
	    etiqueta.setForeground(new Color(35,91,92));
	    add(etiqueta, "North");
	    JPanel tabla = new JPanel (new BorderLayout());
	    tabla.setPreferredSize(new Dimension(818,618));
	    tabla.add(new JScrollPane(tbl), "Center");
	    add(tabla, "Center");
	    
	    JPanel evaluar = new JPanel(new FlowLayout());
	    evaluar.setPreferredSize(new Dimension(160,620));
	    
	    btEvaluar = new JButton("Evaluar");
	    btEvaluar.addActionListener(this);
	    btEvaluar.setActionCommand("Evaluar");
	    
	    btEvaluar.setPreferredSize(new Dimension(117+15,40+5));
	    btEvaluar.setBackground(new Color(60,116,107));
	    btEvaluar.setForeground(new Color(187,192,125));
	    btEvaluar.setFont(new Font( "dandelion in the spring", Font.TRUETYPE_FONT, 34+3 ));
	    btEvaluar.setToolTipText("Al terminar de responder las preguntas de la prueba presione aqui para Evaluar.");
	    
	    JPanel panelimagen = new JPanel(new FlowLayout());
	    panelimagen.setPreferredSize(new Dimension(150,290));
	    panelimagen.setBackground(new Color(122,196,159));
	    panelimagen.add(new JLabel(new ImageIcon("./images/IconosInterfaz/fondo.PNG")));
	    panelimagen.setToolTipText(nombreconvoselec+nombrerolesselec);
	    
	    evaluar.add(panelimagen);
	    evaluar.add(etiquetafecha);
	    evaluar.add(etiquetadia);
	    evaluar.add(diaactual);
	    evaluar.add(etiquetanumerodia);
	    evaluar.add(numerodia);
	    evaluar.add(new JLabel("             "));
	    evaluar.add(etiquetames);
	    evaluar.add(mesactual);
	    evaluar.add(etiquetaaño);
	    evaluar.add(añoactual);
	    evaluar.add(new JLabel("            "));
	    evaluar.add(separador1);
	    evaluar.add(etiquetamaximo);
	    evaluar.add(tiempomaximo);
	    evaluar.add(etiquetarestante);
	    evaluar.add(tiemporestante);
	    evaluar.add(separador2);
	    evaluar.add(etiquetainicio);
	    evaluar.add(horainicio);
	    evaluar.add(etiquetafin);
	    evaluar.add(horafin);
	    evaluar.add(separador3);
	    evaluar.add(btEvaluar);
	    add(evaluar, "East");
	    tiempoPrueba();
	}
	
	public void tiempoPrueba()
	{
		if (hilo == null)
		{
			hilo = new ThreadCarga ();
			hilo.start();
		}	
	}
	
	class ThreadCarga extends Thread 
	{
		public void run()
		{
			int maximo = tiempomaximominutos-1;
			int segundos = cal.get(Calendar.SECOND);
			int minutos = cal.get(Calendar.MINUTE);
			int horas = cal.get(Calendar.HOUR_OF_DAY);
			for (int j = maximo; j >= 0; j--)
			{
				for (int i = 59; i >= 0; i--)
				{
					horafin.setText((horas)+":"+(minutos)+":"+(segundos));
					if(j<10 &&  i>=10)
					{
						tiemporestante.setText("0"+j+" : "+i);
					}
					else if(j>=10 &&  i<10)
					{
						tiemporestante.setText(j+" : 0"+i);
					}
					else if(j<10 &&  i<10)
					{
						tiemporestante.setText("0"+j+" : 0"+i);
					}
					else
					{
						tiemporestante.setText(j+" : "+i);
					}
					synchronized (contador) 
					{
						try
						 {
							contador.wait(17*(59));		
						 }
						 catch (InterruptedException e)
						 {
							 JOptionPane.showMessageDialog(null, "Error en la ejecucion","Mensage:", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
						 }
					}
					segundos++;
					if(segundos==60)
					{
						minutos++;
						segundos=0;
					}
					if(minutos==60)
					{
						horas++;
						minutos=0;
					}
					hilo = null;
					if(salir == true)
					{
						break;
					}
				}
				if(salir == true)
				{
					break;
				}
			}
			if(salir == false)
			{
				tiempoTerminado();
			}
		}
	}
	
	public void tiempoTerminado()
	{
		JOptionPane.showMessageDialog(this,"Se acabo el tiempo maximo para presentar la prueba, ya pasaron "+tiempomaximominutos+" minutos.","Tiempo Terminado", JOptionPane.WARNING_MESSAGE,new ImageIcon("./images/IconosInterfaz/advertencia.PNG"));
		contestotodo = true;
		salir = true;
		
		respuestas.clear();
		int cantidadFilas = tbl.getRowCount();
		for (int i = 0; i < cantidadFilas; i++)
		{
			String preguntaActual = (String) tbl.getValueAt(i, 0);
			String repuestaActual = (String) tbl.getValueAt(i, 1);
			if(repuestaActual == null)
			{
				repuestaActual = "";
			}
			Respuesta respuestaPregunta = new Respuesta(preguntaActual, repuestaActual);
			respuestas.add(respuestaPregunta);
		}
		Resultado resultado = new Resultado();
		resultado.setFechaprueba(fechaprueba);
		resultado.setNombredia(diaactual.getText());
		resultado.setHorainicial(horainicio.getText());
		resultado.setHorafinal(horafin.getText());
		String tiempores = tiemporestante.getText();
		int minutosmaximo = tiempomaximominutos;
		int minutosrestantes = Integer.parseInt(""+tiempores.charAt(0)+tiempores.charAt(1));
		int minutosduracion = minutosmaximo - minutosrestantes;
		resultado.setDuracionprueba(minutosduracion);
		resultado.setRevisado("No");
		resultado.setComentarios("");
		inicio.analizarRespuestasPrueba(respuestas, candidatoactual, tipopruebaactual, convocatoriaactual, rolesactuales, resultado);
		dispose();
	}
	
	public void dispose( ){
		if(salir == false)
		{
			//*****************************
			boolean contestopreguntas=true;
			String preguntaFaltante = "";
			int cantidadFilas = tbl.getRowCount();
			for (int i = 0; i < cantidadFilas; i++)
			{
				String preguntaActual = (String) tbl.getValueAt(i, 0);
				String repuestaActual = (String) tbl.getValueAt(i, 1);
				if(repuestaActual == null || repuestaActual.equalsIgnoreCase(""))
				{
					preguntaFaltante = preguntaActual;
					contestopreguntas=false;
					contestotodo=false;
					break;
				}
			}
			if(contestopreguntas==true)
			{
				JOptionPane.showMessageDialog(this,"Fin de la prueba, presione el boton evaluar.","Prueba Candidato", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
			}
			else
			{
				JOptionPane.showMessageDialog(this,"La prueba no puede ser cancelada, termine de responder las preguntas.","Prueba Candidato", JOptionPane.WARNING_MESSAGE,new ImageIcon("./images/IconosInterfaz/advertencia.PNG"));
			}
			//*****************************
		}
		if(contestotodo==true)
		{
			contestotodo = false;
			salir = true;
			super.dispose();
		}
    }
	
    /**
     * Ejecuta la acción que corresponde a la opción del menú que fue seleccionada
     * @param evento Es el evento de seleccionar una opción del menú - evento!=null
     */
	public void actionPerformed(ActionEvent evento)
	{
		String clic = evento.getActionCommand();	
		if (clic.equals("Evaluar"))
		{
			boolean contestopreguntas=true;
			String preguntaFaltante = "";
			respuestas.clear();
			int cantidadFilas = tbl.getRowCount();
			for (int i = 0; i < cantidadFilas; i++)
			{
				String preguntaActual = (String) tbl.getValueAt(i, 0);
				String repuestaActual = (String) tbl.getValueAt(i, 1);
				if(repuestaActual == null || repuestaActual.equalsIgnoreCase(""))
				{
					preguntaFaltante = preguntaActual;
					contestopreguntas=false;
					contestotodo=false;
					respuestas.clear();
					break;
				}
				else
				{
					Respuesta respuestaPregunta = new Respuesta(preguntaActual, repuestaActual);
					respuestas.add(respuestaPregunta);
				}
			}
			
			if(contestopreguntas==true)
			{
				if(respuestas.size() == cantidadFilas)
				{
					contestotodo = true;
					salir = true;
					JOptionPane.showMessageDialog(this,"Fin de la prueba, los resultados seran notificados.","Prueba Candidato", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
					Resultado resultado = new Resultado();
					resultado.setFechaprueba(fechaprueba);
					resultado.setNombredia(diaactual.getText());
					resultado.setHorainicial(horainicio.getText());
					resultado.setHorafinal(horafin.getText());
					String tiempores = tiemporestante.getText();
					int minutosmaximo = tiempomaximominutos;
					int minutosrestantes = Integer.parseInt(""+tiempores.charAt(0)+tiempores.charAt(1));
					int minutosduracion = minutosmaximo - minutosrestantes;
					resultado.setDuracionprueba(minutosduracion);
					resultado.setRevisado("No");
					resultado.setComentarios("");
					inicio.analizarRespuestasPrueba(respuestas, candidatoactual, tipopruebaactual, convocatoriaactual, rolesactuales,resultado);
					super.dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(this,"Faltaron preguntas por contestar, se registraron solo "+respuestas.size()+" respuestas.","Advertencia", JOptionPane.WARNING_MESSAGE,new ImageIcon("./images/IconosInterfaz/advertencia.PNG"));
				}
			}
			else{
				JOptionPane.showMessageDialog(this,"Falta contestar la respuesta de la prengunta:"+"\n"+preguntaFaltante+"\n"+"Conteste todas las preguntas con Si o No.","Advertencia", JOptionPane.WARNING_MESSAGE,new ImageIcon("./images/IconosInterfaz/advertencia.PNG"));
			}
		}
		else if (clic.equals("salir"))
		{
			dispose();		
		}
	}
}