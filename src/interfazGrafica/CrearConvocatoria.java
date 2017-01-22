package interfazGrafica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import mundo.*;
import basedeDatos.*;

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
public class CrearConvocatoria extends JDialog implements ActionListener
{
	private JTable tbnombre;
	private JTable tbduracion;
	private JButton btaceptar;
	private JButton btcancelar;
	private InterfazInicio inicio;
	private Administrador adminactual;
	private boolean salir;
	
	public CrearConvocatoria(InterfazInicio interfaz, Administrador actualadmin)
	{
		super(interfaz, true);
		setLayout(new FlowLayout());
        setTitle("Crear Convocatoria - SESP");
        setResizable(false);
		setDefaultCloseOperation(2);
		int aumentoancho = 30;
		int anchoventana = 440+aumentoancho;
		int altoventana =200+10;
		setBounds((interfaz.getWidthvetana()/2)-(anchoventana/2),160,anchoventana,altoventana);

		inicio=interfaz;
		adminactual = actualadmin;
		salir = false;
	    //********************************************
	    tbnombre = new JTable(new SimpleTableModel());
	    tbnombre.setValueAt("Nombre Convocatoria", 0, 0);
		
	    // Assign the editor to the second column
	    TableColumnModel tcm3 = tbnombre.getColumnModel();
		// Set column widths
	    tcm3.getColumn(0).setPreferredWidth(150);
	    tcm3.getColumn(1).setPreferredWidth(230);
	    
	    tbnombre.setForeground(Color.BLACK);
	    tbnombre.setGridColor(Color.BLACK);
	    
	    // Set row heighht
	    tbnombre.setRowHeight(20);
	    tbnombre.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tbnombre.setPreferredScrollableViewportSize(tbnombre.getPreferredSize());
	    tbnombre.setToolTipText("Haga doble click para escribir. Para cada dato presione Enter para finalizar.");
		//********************************************
	    
	    //********************************************
	    tbduracion = new JTable(new SimpleTableModel());
	    tbduracion.setValueAt("Numero Dias Duracion", 0, 0);
		
	    // Assign the editor to the second column
	    TableColumnModel tcm4 = tbduracion.getColumnModel();
		// Set column widths
	    tcm4.getColumn(0).setPreferredWidth(150);
	    tcm4.getColumn(1).setPreferredWidth(230);
	    
	    tbduracion.setForeground(Color.BLACK);
	    tbduracion.setGridColor(Color.BLACK);
	    
	    // Set row heighht
	    tbduracion.setRowHeight(20);
	    tbduracion.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tbduracion.setPreferredScrollableViewportSize(tbduracion.getPreferredSize());
	    tbduracion.setToolTipText("Haga doble click para escribir. Para cada dato presione Enter para finalizar.");
		//********************************************
	    
	    btaceptar = new JButton("Aceptar");
		btcancelar = new JButton("Cancelar");
		
		btaceptar.addActionListener(this);
	    btaceptar.setActionCommand("Aceptar");
		btcancelar.addActionListener(this);
		btcancelar.setActionCommand("Cancelar");
		
		btcancelar.setPreferredSize(new Dimension(117+15,40+5));
		btcancelar.setBackground(new Color(60,116,107));
		btcancelar.setForeground(new Color(187,192,125));
		btcancelar.setFont(new Font( "dandelion in the spring", Font.TRUETYPE_FONT, 32+5 ));
		
		btaceptar.setPreferredSize(new Dimension(117+15,40+5));
		btaceptar.setBackground(new Color(60,116,107));
		btaceptar.setForeground(new Color(187,192,125));
		btaceptar.setFont(new Font( "dandelion in the spring", Font.TRUETYPE_FONT, 32+5 ));
		
		int tbancho = 380;
		int tbalto = 2*((tbnombre.getRowCount()*20)+8);

		JPanel panel = new JPanel(new FlowLayout());
	    panel.setPreferredSize(new Dimension(tbancho+10,tbalto));
	    panel.setBackground(new Color(60,116,107));
	  
	    panel.add(tbnombre);
	    panel.add(tbduracion);
	    
	    JPanel panelcolor = new JPanel(new FlowLayout());
		panelcolor.setPreferredSize(new Dimension (430+aumentoancho,160+15));
		panelcolor.setBackground(new Color(68,146,132));
	    
		JLabel espacioa = new JLabel(" ");
	    espacioa.setPreferredSize(new Dimension(420+aumentoancho,10));	
	    panelcolor.add(espacioa);
	    panelcolor.add(panel);
	    JLabel espaciob = new JLabel(" ");
	    espaciob.setPreferredSize(new Dimension(420+aumentoancho,10));	
	    panelcolor.add(espaciob);
	    panelcolor.add(btaceptar);
		panelcolor.add(new JLabel("      "));
		panelcolor.add(btcancelar);
	    add(panelcolor);
	}
	
	public boolean esAñoBisiesto(int año)
	{
		int añobisiesto = 2000;
		boolean bisiesto = false;
		GregorianCalendar calendario = new GregorianCalendar();
		int añoactual = calendario.get(Calendar.YEAR);
		for (int i = 0; i < 20; i++) 
		{
			if(añoactual>=año)
			{
				if(añobisiesto == año)
				{
					bisiesto = true;
					break;
				}
				else
				{
					añobisiesto = añobisiesto + 4;
				}
			}
			else
			{
				break;
			}
		}
		return bisiesto;
	}
	
	public String obtenerFechaFinal(int añoinicial, int mesinicial, int diainicial, int numerodias)
	{
		int añofinal = añoinicial;
		int mesfinal = mesinicial;
		int diafinal = diainicial;
		
		String fechaobtenida = "";
		
		for (int i = 0; i < numerodias; i++) 
		{
			if((diafinal == 31) && (mesfinal == 1 || mesfinal == 3 || mesfinal == 5 || mesfinal == 7 || mesfinal == 8 || mesfinal == 10 || mesfinal == 12) )
			{
				if(mesfinal == 12)
				{
					diafinal=1;
					mesfinal=1;
					añofinal++;
				}
				else
				{
					diafinal=1;
					mesfinal++;
				}
			}
			else if((diafinal < 32) && (mesfinal == 1 || mesfinal == 3 || mesfinal == 5 || mesfinal == 7 || mesfinal == 8 || mesfinal == 10 || mesfinal == 12) )
			{
				diafinal++;
			}
			else if((diafinal == 30) && (mesfinal == 4 || mesfinal == 6 || mesfinal == 9 || mesfinal == 11) )
			{
				diafinal=1;
				mesfinal++;
			}
			else if((diafinal < 31) && (mesfinal == 4 || mesfinal == 6 || mesfinal == 9 || mesfinal == 11) )
			{
				diafinal++;
			}
			else if((diafinal == 29) && (mesfinal == 2) && (esAñoBisiesto(añofinal) == true))
			{
				diafinal=1;
				mesfinal++;
			}
			else if((diafinal < 30) && (mesfinal == 2) && (esAñoBisiesto(añofinal) == true))
			{
				diafinal++;
			}
			else if((diafinal == 28) && (mesfinal == 2)&& (esAñoBisiesto(añofinal) == false))
			{
				diafinal=1;
				mesfinal++;
			}
			else if((diafinal < 29) && (mesfinal == 2) && (esAñoBisiesto(añofinal) == false))
			{
				diafinal++;
			}
		}
		fechaobtenida  = añofinal+"-"+mesfinal+"-"+diafinal;
		return fechaobtenida;
	}
	
	public void dispose( ){
		if(salir == false){
			int respuesta = JOptionPane.showConfirmDialog(this,"¿Cancelar la creacion de la convocatoria?","Confirmacion para salir",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
	        if (respuesta == JOptionPane.YES_OPTION){
	        	salir = true;
	            super.dispose();
	        }
		}
    }

	/**
     * Ejecuta la acción que corresponde a la opción del menú que fue seleccionada
     * @param evento Es el evento de seleccionar una opción del menú - evento!=null
     */
	public void actionPerformed(ActionEvent evento){
		String clic = evento.getActionCommand();	
		if (clic.equals("Aceptar"))
		{
			String nombredigitado = (String) tbnombre.getValueAt(0, 1);
			if(nombredigitado == null)
			{
				JOptionPane.showMessageDialog(this,"Presione Enter al terminar de escribir el nombre de la convocatoria.","Crear Convocatoria",JOptionPane.WARNING_MESSAGE,new ImageIcon("./images/IconosInterfaz/advertencia.PNG"));
			}
			else
			{
				String diasdigitado = (String) tbduracion.getValueAt(0, 1);
				if(diasdigitado == null)
				{
					JOptionPane.showMessageDialog(this,"Presione Enter al terminar de escribir el numero de dias de la convocatoria.","Crear Convocatoria",JOptionPane.WARNING_MESSAGE,new ImageIcon("./images/IconosInterfaz/advertencia.PNG"));
				}
				else
				{
					try 
					{
						int numerodias = Integer.parseInt(diasdigitado);
						GregorianCalendar calendario = new GregorianCalendar();
						int añoinicial = calendario.get(Calendar.YEAR);
						int mesinicial = (calendario.get(Calendar.MONTH)+1);
						int diainicial = calendario.get(Calendar.DAY_OF_MONTH);
						String fechainicial = añoinicial+"-"+mesinicial+"-"+diainicial;
						String horainicial = calendario.get(Calendar.HOUR_OF_DAY)+":"+calendario.get(Calendar.MINUTE)+":"+calendario.get(Calendar.SECOND);
						String fechafinal = obtenerFechaFinal(añoinicial, mesinicial, diainicial, numerodias);
						String horafinal = "06:00:00";
						Convocatoria nuevaconvocator = new Convocatoria();
						nuevaconvocator.setIdadmin(adminactual.getIdadmin());
						nuevaconvocator.setNombre(nombredigitado);
						nuevaconvocator.setFechainicial(fechainicial);
						nuevaconvocator.setHorainicial(horainicial);
						nuevaconvocator.setFechafinal(fechafinal);
						nuevaconvocator.setHorafinal(horafinal);
						nuevaconvocator.setFinalizada("No");
						try 
			    		{
			    			Conector conector = new Conector();
			    			conector.iniciarConexionBaseDatos();
			    			ConvocatoriaBD.insertar(nuevaconvocator, conector);
			    			conector.terminarConexionBaseDatos();
			    			JOptionPane.showMessageDialog(this,"La Convocatoria ha sido creada.","Crear Convocatoria", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
			    		}
			    		catch (Exception e)
			    		{
			    			JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
			    		}
					} 
					catch (Exception e) 
					{
						JOptionPane.showMessageDialog(this,"El numero de dias es un valor numerico.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
					}
					salir = true;
					super.dispose();
				}
			}
		}
		else if (clic.equals("Cancelar")){
			dispose();
		}
	}
}