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
public class AdicionarDatosPregunta extends JDialog implements ActionListener
{
	private JTable tbnumeropre;
	private JTable tbtextopre;
	private JButton btaceptar;
	private JButton btcancelar;
	
	/**
	 * Es la relacion que se estable con la ventana de inicio.
	 */
	private InterfazInicio inicio;
	
	public AdicionarDatosPregunta(InterfazInicio interfaz)
	{
		super(interfaz, true);
		setLayout(new FlowLayout());
        setTitle("Adicionar Datos Pregunta - SESP");
        setResizable(false);
		setDefaultCloseOperation(2);
		int aumentoancho = 30;
		int anchoventana = 960+aumentoancho;
		int altoventana =195+10;
		setBounds((interfaz.getWidthvetana()/2)-(anchoventana/2),160,anchoventana,altoventana);
		
		inicio=interfaz;
	    
	    //********************************************
		tbnumeropre = new JTable(new SimpleTableModel());
		tbnumeropre.setValueAt("ORDEN NUMERICO", 0, 0);
		
	    // Assign the editor to the second column
	    TableColumnModel tcm3 = tbnumeropre.getColumnModel();
		// Set column widths
	    tcm3.getColumn(0).setPreferredWidth(140);
	    tcm3.getColumn(1).setPreferredWidth(760);
	    
	    tbnumeropre.setForeground(Color.BLACK);
	    tbnumeropre.setGridColor(Color.BLACK);
	    
	    // Set row heighht
	    tbnumeropre.setRowHeight(20);
	    tbnumeropre.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tbnumeropre.setPreferredScrollableViewportSize(tbnumeropre.getPreferredSize());
	    tbnumeropre.setToolTipText("Haga doble click para escribir. Para cada dato presione Enter para finalizar.");
		//********************************************
	    
	    //********************************************
	    tbtextopre = new JTable(new SimpleTableModel());
	    tbtextopre.setValueAt("TEXTO PREGUNTA", 0, 0);
		
	    // Assign the editor to the second column
	    TableColumnModel tcm4 = tbtextopre.getColumnModel();
		// Set column widths
	    tcm4.getColumn(0).setPreferredWidth(140);
	    tcm4.getColumn(1).setPreferredWidth(760);
	    
	    tbtextopre.setForeground(Color.BLACK);
	    tbtextopre.setGridColor(Color.BLACK);
	    
	    // Set row heighht
	    tbtextopre.setRowHeight(20);
	    tbtextopre.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tbtextopre.setPreferredScrollableViewportSize(tbtextopre.getPreferredSize());
	    tbtextopre.setToolTipText("Haga doble click para escribir. Para cada dato presione Enter para finalizar.");
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
		int tbalto = 2*((tbnumeropre.getRowCount()*20)+7);

		JPanel panel = new JPanel(new FlowLayout());
	    panel.setPreferredSize(new Dimension(tbancho+10,tbalto));
	    panel.setBackground(new Color(60,116,107));
	  
	    panel.add(tbnumeropre);
	    panel.add(tbtextopre);
	    
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
			String numerodigitado = (String) tbnumeropre.getValueAt(0, 1);
			if(numerodigitado == null)
			{
				JOptionPane.showMessageDialog(this,"Presione Enter al terminar de escribir el numero de orden de la pregunta.","Adicionar Pregunta",JOptionPane.WARNING_MESSAGE,new ImageIcon("./images/IconosInterfaz/advertencia.PNG"));
			}
			else
			{
				String textodigitado = (String) tbtextopre.getValueAt(0, 1);
				if(textodigitado == null)
				{
					JOptionPane.showMessageDialog(this,"Presione Enter al terminar de escribir el texto de la pregunta.","Adicionar Pregunta",JOptionPane.WARNING_MESSAGE,new ImageIcon("./images/IconosInterfaz/advertencia.PNG"));
				}
				else
				{
					try 
					{
						int numeroorden = Integer.parseInt(numerodigitado);
						Pregunta nuevapregunta = new Pregunta();
						nuevapregunta.setOrdennumerico(numeroorden);
						nuevapregunta.setTexto(textodigitado);
						try 
			    		{
			    			Conector conectora = new Conector();
			    			conectora.iniciarConexionBaseDatos();
			    			Pregunta existen = PreguntaBD.buscarNumeroOrden(numeroorden, conectora);
			    			conectora.terminarConexionBaseDatos();
			    			if(existen == null)
			    			{
			    				Conector conectorb = new Conector();
				    			conectorb.iniciarConexionBaseDatos();
			    				Pregunta existep = PreguntaBD.buscarTexto(textodigitado, conectorb);
			    				conectorb.terminarConexionBaseDatos();
				    			if(existep == null)
				    			{
				    				Conector conectorc = new Conector();
					    			conectorc.iniciarConexionBaseDatos();
				    				PreguntaBD.insertar(nuevapregunta, conectorc);
				    				conectorc.terminarConexionBaseDatos();
					    			JOptionPane.showMessageDialog(this,"Se Adiciono la pregunta como base del conocimiento.","Adicionar Pregunta", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
				    			}
				    			else
				    			{
				    				JOptionPane.showMessageDialog(this,"Esta pregunta ya es parte de la base del conocimiento.","Adicionar Pregunta", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
				    			}
			    			}
			    			else
			    			{
			    				JOptionPane.showMessageDialog(this,"Esta numero de orden para la pregunta ya esta adicionado.","Adicionar Pregunta", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
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
						JOptionPane.showMessageDialog(this,"El numero de orden es un valor numerico.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
					}
				}
			}
		}
		else if (clic.equals("Cancelar")){
			dispose();
		}
	}
}