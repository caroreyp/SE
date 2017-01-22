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
public class AdicionarDatosPrueba extends JDialog implements ActionListener
{
	private JTable tbnombre;
	private JTable tbtipo;
	private JTable tbtiempo;
	private JButton btaceptar;
	private JButton btcancelar;
	
	/**
	 * Es la relacion que se estable con la ventana de inicio.
	 */
	private InterfazInicio inicio;
	
	public AdicionarDatosPrueba(InterfazInicio interfaz)
	{
		super(interfaz, true);
		setLayout(new FlowLayout());
        setTitle("Adicionar Datos Prueba - SESP");
        setResizable(false);
		setDefaultCloseOperation(2);
		int aumentoancho = 30;
		int anchoventana = 440+aumentoancho;
		int altoventana =220+10;
		setBounds((interfaz.getWidthvetana()/2)-(anchoventana/2),160,anchoventana,altoventana);
		
		inicio=interfaz;
	    
	    //********************************************
	    tbnombre = new JTable(new SimpleTableModel());
	    tbnombre.setValueAt("NOMBRE PRUEBA", 0, 0);
		
	    // Assign the editor to the second column
	    TableColumnModel tcm3 = tbnombre.getColumnModel();
		// Set column widths
	    tcm3.getColumn(0).setPreferredWidth(170);
	    tcm3.getColumn(1).setPreferredWidth(210);
	    
	    tbnombre.setForeground(Color.BLACK);
	    tbnombre.setGridColor(Color.BLACK);
	    
	    // Set row heighht
	    tbnombre.setRowHeight(20);
	    tbnombre.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tbnombre.setPreferredScrollableViewportSize(tbnombre.getPreferredSize());
	    tbnombre.setToolTipText("Haga doble click para escribir. Para cada dato presione Enter para finalizar.");
		//********************************************
	    
	  //********************************************
	    tbtipo = new JTable(new SimpleTableModel());
	    tbtipo.setValueAt("TIPO PRUEBA", 0, 0);
		
	    // Assign the editor to the second column
	    TableColumnModel tcm4 = tbtipo.getColumnModel();
		// Set column widths
	    tcm4.getColumn(0).setPreferredWidth(170);
	    tcm4.getColumn(1).setPreferredWidth(210);
	    
	    tbtipo.setForeground(Color.BLACK);
	    tbtipo.setGridColor(Color.BLACK);
	    
	    // Set row heighht
	    tbtipo.setRowHeight(20);
	    tbtipo.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tbtipo.setPreferredScrollableViewportSize(tbtipo.getPreferredSize());
	    tbtipo.setToolTipText("Haga doble click para escribir. Para cada dato presione Enter para finalizar.");
		//********************************************
	    
	    //********************************************
	    tbtiempo = new JTable(new SimpleTableModel());
	    tbtiempo.setValueAt("TIEMPO MAXIMO (en minutos)", 0, 0);
		
	    // Assign the editor to the second column
	    TableColumnModel tcm5 = tbtiempo.getColumnModel();
		// Set column widths
	    tcm5.getColumn(0).setPreferredWidth(170);
	    tcm5.getColumn(1).setPreferredWidth(210);
	    
	    tbtiempo.setForeground(Color.BLACK);
	    tbtiempo.setGridColor(Color.BLACK);
	    
	    // Set row heighht
	    tbtiempo.setRowHeight(20);
	    tbtiempo.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tbtiempo.setPreferredScrollableViewportSize(tbtiempo.getPreferredSize());
	    tbtiempo.setToolTipText("Haga doble click para escribir. Para cada dato presione Enter para finalizar.");
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
		int tbalto = 3*((tbnombre.getRowCount()*20)+6);

		JPanel panel = new JPanel(new FlowLayout());
	    panel.setPreferredSize(new Dimension(tbancho+10,tbalto));
	    panel.setBackground(new Color(60,116,107));
	  
	    panel.add(tbnombre);
	    panel.add(tbtipo);
	    panel.add(tbtiempo);
	    
	    JPanel panelcolor = new JPanel(new FlowLayout());
		panelcolor.setPreferredSize(new Dimension (430+aumentoancho,180+15));
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
				JOptionPane.showMessageDialog(this,"Presione Enter al terminar de escribir el nombre de la prueba.","Adicionar Prueba",JOptionPane.WARNING_MESSAGE,new ImageIcon("./images/IconosInterfaz/advertencia.PNG"));
			}
			else
			{
				String tipodigitado = (String) tbtipo.getValueAt(0, 1);
				if(tipodigitado == null)
				{
					JOptionPane.showMessageDialog(this,"Presione Enter al terminar de escribir el tipo de la prueba.","Adicionar Prueba",JOptionPane.WARNING_MESSAGE,new ImageIcon("./images/IconosInterfaz/advertencia.PNG"));
				}
				else
				{
					String tiempodigitado = (String) tbtiempo.getValueAt(0, 1);
					if(tiempodigitado == null)
					{
						JOptionPane.showMessageDialog(this,"Presione Enter al terminar de escribir el tiempo maximo de la prueba.","Adicionar Prueba",JOptionPane.WARNING_MESSAGE,new ImageIcon("./images/IconosInterfaz/advertencia.PNG"));
					}
					else
					{
						try 
						{
							int tiempomaximo = Integer.parseInt(tiempodigitado);
							Prueba nuevaprueba = new Prueba();
							nuevaprueba.setNombre(nombredigitado);
							nuevaprueba.setTipoprueba(tipodigitado);
							nuevaprueba.setTiempomaximo(tiempomaximo);

							try 
				    		{
				    			Conector conectora = new Conector();
				    			conectora.iniciarConexionBaseDatos();
				    			Prueba existep = PruebaBD.buscarNombre(nombredigitado, conectora);
				    			conectora.terminarConexionBaseDatos();
				    			if(existep == null)
				    			{
				    				Conector conectorb = new Conector();
					    			conectorb.iniciarConexionBaseDatos();
				    				PruebaBD.insertar(nuevaprueba, conectorb);
				    				conectorb.terminarConexionBaseDatos();
					    			JOptionPane.showMessageDialog(this,"Se Adiciono la prueba como base del conocimiento.","Adicionar Prueba", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
				    			}
				    			else
				    			{
				    				JOptionPane.showMessageDialog(this,"Esta prueba ya es parte de la base del conocimiento.","Adicionar Prueba", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
				    			}
				    		}
				    		catch (Exception e)
				    		{
				    			JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
				    		}
				    		super.dispose();
						} 
						catch (Exception e) 
						{
							JOptionPane.showMessageDialog(this,"El tiempo maximo es un valor numerico.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
						}
					}
				}
			}
		}
		else if (clic.equals("Cancelar")){
			dispose();
		}
	}
}