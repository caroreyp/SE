package interfazGrafica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
public class AdicionarDatosCompetencia extends JDialog implements ActionListener
{
	private JTable tbnombre;
	private JTable tbdescripcion;
	private JButton btaceptar;
	private JButton btcancelar;
	
	/**
	 * Es la relacion que se estable con la ventana de inicio.
	 */
	private InterfazInicio inicio;
	
	public AdicionarDatosCompetencia(InterfazInicio interfaz)
	{
		super(interfaz, true);
		setLayout(new FlowLayout());
        setTitle("Adicionar Datos Competencia - SESP");
        setResizable(false);
		setDefaultCloseOperation(2);
		int aumentoancho = 30;
		int anchoventana = 960+aumentoancho;
		int altoventana =195+10;
		setBounds((interfaz.getWidthvetana()/2)-(anchoventana/2),160,anchoventana,altoventana);
		
		inicio=interfaz;
	    
	    //********************************************
		tbnombre = new JTable(new SimpleTableModel());
		tbnombre.setValueAt("NOMBRE", 0, 0);
		
	    // Assign the editor to the second column
	    TableColumnModel tcm3 = tbnombre.getColumnModel();
		// Set column widths
	    tcm3.getColumn(0).setPreferredWidth(120);
	    tcm3.getColumn(1).setPreferredWidth(780);
	    
	    tbnombre.setForeground(Color.BLACK);
	    tbnombre.setGridColor(Color.BLACK);
	    
	    // Set row heighht
	    tbnombre.setRowHeight(20);
	    tbnombre.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tbnombre.setPreferredScrollableViewportSize(tbnombre.getPreferredSize());
	    tbnombre.setToolTipText("Haga doble click para escribir. Para cada dato presione Enter para finalizar.");
		//********************************************
	    
	  //********************************************
	    tbdescripcion = new JTable(new SimpleTableModel());
	    tbdescripcion.setValueAt("DESCRIPCION", 0, 0);
		
	    // Assign the editor to the second column
	    TableColumnModel tcm4 = tbdescripcion.getColumnModel();
		// Set column widths
	    tcm4.getColumn(0).setPreferredWidth(120);
	    tcm4.getColumn(1).setPreferredWidth(780);
	    
	    tbdescripcion.setForeground(Color.BLACK);
	    tbdescripcion.setGridColor(Color.BLACK);
	    
	    // Set row heighht
	    tbdescripcion.setRowHeight(20);
	    tbdescripcion.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tbdescripcion.setPreferredScrollableViewportSize(tbdescripcion.getPreferredSize());
	    tbdescripcion.setToolTipText("Haga doble click para escribir. Para cada dato presione Enter para finalizar.");
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
		
		int tbancho = 900;
		int tbalto = 2*((tbnombre.getRowCount()*20)+7);

		JPanel panel = new JPanel(new FlowLayout());
	    panel.setPreferredSize(new Dimension(tbancho+10,tbalto));
	    panel.setBackground(new Color(60,116,107));
	  
	    panel.add(tbnombre);
	    panel.add(tbdescripcion);
	    
	    JPanel panelcolor = new JPanel(new FlowLayout());
		panelcolor.setPreferredSize(new Dimension (950+aumentoancho,155+15));
		panelcolor.setBackground(new Color(68,146,132));
	    
		JLabel espacioa = new JLabel(" ");
	    espacioa.setPreferredSize(new Dimension(940+aumentoancho,12));	
	    panelcolor.add(espacioa);
	    panelcolor.add(panel);
	    JLabel espaciob = new JLabel(" ");
	    espaciob.setPreferredSize(new Dimension(940+aumentoancho,12));	
	    panelcolor.add(espaciob);
	    panelcolor.add(btaceptar);
		panelcolor.add(new JLabel("          "));
		panelcolor.add(btcancelar);
	    
	    add(panelcolor);
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
				JOptionPane.showMessageDialog(this,"Presione Enter al terminar de escribir el nombre de la competencia.","Adicionar Competencia",JOptionPane.WARNING_MESSAGE,new ImageIcon("./images/IconosInterfaz/advertencia.PNG"));
			}
			else
			{
				String descripciondigitado = (String) tbdescripcion.getValueAt(0, 1);
				if(descripciondigitado == null)
				{
					JOptionPane.showMessageDialog(this,"Presione Enter al terminar de escribir la descripcion de la competencia.","Adicionar Competencia",JOptionPane.WARNING_MESSAGE,new ImageIcon("./images/IconosInterfaz/advertencia.PNG"));
				}
				else
				{
					Competencia nuevacompet = new Competencia();
					nuevacompet.setNombre(nombredigitado.trim());
					nuevacompet.setDescripcion(descripciondigitado.trim());
					try 
		    		{
		    			Conector conectora = new Conector();
		    			conectora.iniciarConexionBaseDatos();
		    			Competencia existen = CompetenciaBD.buscarNombre(nombredigitado.trim(), conectora);
		    			conectora.terminarConexionBaseDatos();
		    			if(existen == null)
		    			{
		    				Conector conectorc = new Conector();
			    			conectorc.iniciarConexionBaseDatos();
			    			CompetenciaBD.insertar(nuevacompet, conectorc);
		    				conectorc.terminarConexionBaseDatos();
			    			JOptionPane.showMessageDialog(this,"Se Adiciono la competencia como base del conocimiento.","Adicionar Competencia", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
		    			}
		    			else
		    			{
		    				JOptionPane.showMessageDialog(this,"La escala con el nombre "+nombredigitado+" ya esta adicionada.","Adicionar Competencia", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
		    			}
		    		}
		    		catch (Exception e)
		    		{
		    			JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
		    		}
		    		super.dispose();
				}
			}
		}
		else if (clic.equals("Cancelar")){
			dispose();
		}
	}
}