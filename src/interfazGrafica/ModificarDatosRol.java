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

import basedeDatos.Conector;
import basedeDatos.RolBD;

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
public class ModificarDatosRol extends JDialog implements ActionListener
{
	private JTable tbid;
	private JTable tbdatos;
	private JButton btaceptar;
	private JButton btcancelar;
	private InterfazInicio inicio;
	private Rol rolseleccionado;
	
	public ModificarDatosRol(InterfazInicio interfaz, Rol encontrado)
	{
		super(interfaz, true);
		setLayout(new FlowLayout());
        setTitle("Modificar Datos Rol - SESP");
        setResizable(false);
		setDefaultCloseOperation(2);
		int aumentoancho = 30;
		int anchoventana = 350+aumentoancho;
		int altoventana =200+10;
		setBounds((interfaz.getWidthvetana()/2)-(anchoventana/2),160,anchoventana,altoventana);
		
		inicio = interfaz;
		rolseleccionado = encontrado;
		//********************************************
		tbid = new JTable(1,2);
		tbid.setValueAt("      ID ROL", 0, 0);
		tbid.setValueAt(encontrado.getIdrol(), 0, 1);
		
		tbid.setEnabled(false);
		
	    // Assign the editor to the second column
	    TableColumnModel tcm2 = tbid.getColumnModel();
		// Set column widths
	    tcm2.getColumn(0).setPreferredWidth(80);
	    tcm2.getColumn(1).setPreferredWidth(200);
	    
	    tbid.setForeground(Color.BLACK);
	    tbid.setGridColor(Color.BLACK);
	    // Set row heighht
	    tbid.setRowHeight(20);
	    tbid.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tbid.setPreferredScrollableViewportSize(tbid.getPreferredSize());
		//********************************************
	    
	  //********************************************
	    tbdatos = new JTable(new SimpleTableModel());
	    tbdatos.setValueAt("NOMBRE", 0, 0);
	    tbdatos.setValueAt(encontrado.getNombre(), 0, 1);
		
	    // Assign the editor to the second column
	    TableColumnModel tcm3 = tbdatos.getColumnModel();
		// Set column widths
	    tcm3.getColumn(0).setPreferredWidth(80);
	    tcm3.getColumn(1).setPreferredWidth(200);
	    
	    tbdatos.setForeground(Color.BLACK);
	    tbdatos.setGridColor(Color.BLACK);
	    
	    // Set row heighht
	    tbdatos.setRowHeight(20);
	    tbdatos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tbdatos.setPreferredScrollableViewportSize(tbdatos.getPreferredSize());
	    tbdatos.setToolTipText("Haga doble click para escribir. Para cada dato presione Enter para finalizar.");
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
		
		int tbancho = 280;
		int tbalto = 2*(tbid.getRowCount()*20);

		JPanel panel = new JPanel(new FlowLayout());
	    panel.setPreferredSize(new Dimension(tbancho+10,tbalto+15));
	    panel.setBackground(new Color(60,116,107));
	  
	    panel.add(tbid);
	    panel.add(tbdatos);
	    
	    JPanel panelcolor = new JPanel(new FlowLayout());
		panelcolor.setPreferredSize(new Dimension (340+aumentoancho,160+15));
		panelcolor.setBackground(new Color(68,146,132));
	    
		JLabel espacioa = new JLabel(" ");
	    espacioa.setPreferredSize(new Dimension(340+aumentoancho,10));	
	    panelcolor.add(espacioa);
	    panelcolor.add(panel);
	    JLabel espaciob = new JLabel(" ");
	    espaciob.setPreferredSize(new Dimension(340+aumentoancho,10));	
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
			String nombredigitado = (String) tbdatos.getValueAt(0, 1);
			if(nombredigitado == null)
			{
				JOptionPane.showMessageDialog(this,"Presione Enter al terminar de escribir el nombre del rol.","Modificar Rol",JOptionPane.WARNING_MESSAGE,new ImageIcon("./images/IconosInterfaz/advertencia.PNG"));
			}
			else
			{
				rolseleccionado.setNombre(nombredigitado);
				try 
	    		{
	    			Conector conectora = new Conector();
	    			conectora.iniciarConexionBaseDatos();
	    			Rol exister = RolBD.buscarNombre(nombredigitado, conectora);
	    			conectora.terminarConexionBaseDatos();
	    			if(exister == null)
	    			{
	    				Conector conectorb = new Conector();
		    			conectorb.iniciarConexionBaseDatos();
	    				RolBD.actualizar(rolseleccionado, conectorb);
	    				conectorb.terminarConexionBaseDatos();
		    			JOptionPane.showMessageDialog(this,"Se Modifico el rol seleccionado.","Modificar Rol", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
	    			}
	    			else
	    			{
	    				JOptionPane.showMessageDialog(this,"Este rol ya es parte de la base del conocimiento.","Modificar Rol", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
	    			}
	    		}
	    		catch (Exception e)
	    		{
	    			JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
	    		}
				super.dispose();
			}
		}
		else if (clic.equals("Cancelar")){
			dispose();
		}
	}
}