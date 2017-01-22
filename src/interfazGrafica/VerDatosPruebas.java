package interfazGrafica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
public class VerDatosPruebas extends JDialog implements ActionListener
{
	private JTable tbpruebas;
	private JButton btmodificar;
	private JButton bteliminar;
	private JButton btaceptar;
	private JButton btcancelar;
	private InterfazInicio inicio;
	
	public VerDatosPruebas(InterfazInicio interfaz)
	{
		super(interfaz, true);
		setLayout(new FlowLayout());
        setTitle("Ver Datos Pruebas - SESP");
        setResizable(false);
		setDefaultCloseOperation(2);
		int aumentoancho = 30;
		int anchoventana = 580+aumentoancho;
		int altoventana =229+10+65;
		setBounds((interfaz.getWidthvetana()/2)-(anchoventana/2),140,anchoventana,altoventana);
		
		inicio=interfaz;
		
		int numeropruebas = 0;//numero de pruebas en la BD
		
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
        	numeropruebas = pruebas.size();
        }
		//********************************************
        tbpruebas = new JTable(new VerPruebasTableModel());
		tbpruebas.setValueAt("  ID PRUEBA", 0, 0);
		tbpruebas.setValueAt("           NOMBRE PRUEBA", 0, 1);
		tbpruebas.setValueAt("          TIPO PRUEBA", 0, 2);
		tbpruebas.setValueAt("   TIEMPO MAXIMO", 0, 3);
		
		for (int i = 0; i < numeropruebas; i++)
		{
			Prueba pruebaaux = (Prueba)pruebas.get(i);
			tbpruebas.setValueAt(pruebaaux.getIdprueba(), i+1, 0);
			tbpruebas.setValueAt(pruebaaux.getNombre(), i+1, 1);
			tbpruebas.setValueAt(pruebaaux.getTipoprueba(), i+1, 2);
			tbpruebas.setValueAt(pruebaaux.getTiempomaximo()+" minutos", i+1, 3);
		}	

	    // Assign the editor to the second column
	    TableColumnModel tcm2 = tbpruebas.getColumnModel();
		// Set column widths
	    tcm2.getColumn(0).setPreferredWidth(80);
	    tcm2.getColumn(1).setPreferredWidth(170);
	    tcm2.getColumn(2).setPreferredWidth(140);
	    tcm2.getColumn(3).setPreferredWidth(110);
	    
	    tbpruebas.setForeground(Color.BLACK);
	    tbpruebas.setGridColor(Color.BLACK);
	    // Set row heighht
	    tbpruebas.setRowHeight(20);
	    tbpruebas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tbpruebas.setPreferredScrollableViewportSize(tbpruebas.getPreferredSize());
		//********************************************
	    
	    btmodificar = new JButton("Modificar");
		bteliminar = new JButton("Eliminar");
		
		btmodificar.addActionListener(this);
		btmodificar.setActionCommand("Modificar");
		bteliminar.addActionListener(this);
		bteliminar.setActionCommand("Eliminar");
		
		bteliminar.setPreferredSize(new Dimension(126+15,35+5));
		bteliminar.setBackground(new Color(60,116,107));
		bteliminar.setForeground(new Color(187,192,125));
		bteliminar.setFont(new Font( "dandelion in the spring", Font.TRUETYPE_FONT, 32+5 ));
		bteliminar.setToolTipText("Elimina los datos seleccionados en la tabla actual.");
		
		btmodificar.setPreferredSize(new Dimension(126+15,35+5));
		btmodificar.setBackground(new Color(60,116,107));
		btmodificar.setForeground(new Color(187,192,125));
		btmodificar.setFont(new Font( "dandelion in the spring", Font.TRUETYPE_FONT, 32+5 ));
		btmodificar.setToolTipText("Modifica los datos seleccionados en la tabla actual.");
		
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
		
		int tbancho = 500;
		int tbalto = (tbpruebas.getRowCount()*20);

		JPanel panel = new JPanel(new FlowLayout());
	    panel.setPreferredSize(new Dimension(tbancho+10,tbalto+10));
	    panel.setBackground(new Color(60,116,107));
	  
	    panel.add(tbpruebas);
	    
	    JScrollPane barras = new JScrollPane(panel);
	    barras.setPreferredSize(new Dimension(530,90));
	    
	    JPanel panelcolor = new JPanel(new FlowLayout());
		panelcolor.setPreferredSize(new Dimension (570+aumentoancho,190+15+65));
		panelcolor.setBackground(new Color(68,146,132));
	    
		JLabel espacioa = new JLabel(" ");
	    espacioa.setPreferredSize(new Dimension(570+aumentoancho,10));	
	    panelcolor.add(espacioa);
	    panelcolor.add(barras);
	    JLabel espaciob = new JLabel(" ");
	    espaciob.setPreferredSize(new Dimension(570+aumentoancho,10));	
	    panelcolor.add(espaciob);
	    panelcolor.add(btmodificar);
	    panelcolor.add(new JLabel("      "));
	    panelcolor.add(bteliminar);
	    JLabel espacioc = new JLabel(" ");
	    espacioc.setPreferredSize(new Dimension(570+aumentoancho,10));	
	    panelcolor.add(espacioc);
	    panelcolor.add(btaceptar);
	    panelcolor.add(new JLabel("      "));
	    panelcolor.add(btcancelar);
	    add(panelcolor);
	}
	
	/**
	 * Es el metodo que actualiza la tabla con los datos que han sido modificados o eliminados 
	 */
	public void actualizarTablaVerDatos()
	{
		int numeropruebas = 0;//numero roles en la BD
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
        	numeropruebas = pruebas.size();
        }
        for (int i = 1; i < tbpruebas.getRowCount(); i++)
		{
			tbpruebas.setValueAt("", i, 0);
			tbpruebas.setValueAt("", i, 1);
			tbpruebas.setValueAt("", i, 2);
			tbpruebas.setValueAt("", i, 3);
		}
        for (int i = 0; i < numeropruebas; i++)
		{
			Prueba pruebaaux = (Prueba)pruebas.get(i);
			tbpruebas.setValueAt(pruebaaux.getIdprueba(), i+1, 0);
			tbpruebas.setValueAt(pruebaaux.getNombre(), i+1, 1);
			tbpruebas.setValueAt(pruebaaux.getTipoprueba(), i+1, 2);
			tbpruebas.setValueAt(pruebaaux.getTiempomaximo()+" minutos", i+1, 3);
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
			dispose();
		}
		else if (clic.equals("Cancelar")){
			dispose();
		}
		else if (clic.equals("Modificar"))
		{
			int rowselect = tbpruebas.getSelectedRow();
			if(rowselect != -1 && rowselect != 0)
			{
				int idseleccionado = (Integer) tbpruebas.getValueAt(rowselect, 0);
				ArrayList entidadencontrada = new ArrayList();
				int idnumerico = 0;
				try
		        {
		            idnumerico = (idseleccionado);
		        }
		        catch (Exception e)
				{
		        	JOptionPane.showMessageDialog(this,"El Id debe ser un valor numerico.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
				}
		        if(idnumerico > 0)
		        {
					int tamañolista = 0;
					List lista = null;
					try
			        {
			            Conector conector = new Conector();
			            conector.iniciarConexionBaseDatos();
			            lista= PruebaBD.listar(conector);
			            conector.terminarConexionBaseDatos();
			        }
			        catch (Exception e)
					{
			        	JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
					}
			        if(lista != null)
			        {
			        	tamañolista = lista.size();
			        }
					for (int i = 0; i < tamañolista; i++)
					{
						Prueba pruebaaux = (Prueba)lista.get(i);
						int idprueba = pruebaaux.getIdprueba();
						if(idprueba == idnumerico)
						{
							entidadencontrada.add(pruebaaux);
							break;
						}
					}
					if(entidadencontrada.size() > 0)
					{
						inicio.verModificarDatosEntidad("Prueba",entidadencontrada);
						actualizarTablaVerDatos();
					}
					else
					{
						JOptionPane.showMessageDialog(this,"El Id digitado no esta almacenado.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
					}
		        }
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Seleccione un Id de la tabla.","Seleccionar ID",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
			}
		}
		else if (clic.equals("Eliminar"))
		{
			int rowselect = tbpruebas.getSelectedRow();
			if(rowselect != -1 && rowselect != 0)
			{
				int idseleccionado = (Integer) tbpruebas.getValueAt(rowselect, 0);
				ArrayList entidadencontrada = new ArrayList();
				int idnumerico = 0;
				try
		        {
		            idnumerico = (idseleccionado);
		        }
		        catch (Exception e)
				{
		        	JOptionPane.showMessageDialog(this,"El Id debe ser un valor numerico.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
				}
		        if(idnumerico > 0)
		        {
					int tamañolista = 0;
					List lista = null;
					try
			        {
			            Conector conector = new Conector();
			            conector.iniciarConexionBaseDatos();
			            lista= PruebaBD.listar(conector);
			            conector.terminarConexionBaseDatos();
			        }
			        catch (Exception e)
					{
			        	JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
					}
			        if(lista != null)
			        {
			        	tamañolista = lista.size();
			        }
					for (int i = 0; i < tamañolista; i++)
					{
						Prueba pruebaaux = (Prueba)lista.get(i);
						int idprueba = pruebaaux.getIdprueba();
						if(idprueba == idnumerico)
						{
							entidadencontrada.add(pruebaaux);
							break;
						}
					}
					if(entidadencontrada.size() > 0)
					{
						inicio.eliminarDatosEnEntidad("Prueba",entidadencontrada);
						actualizarTablaVerDatos();
					}
					else
					{
						JOptionPane.showMessageDialog(this,"El Id digitado no esta almacenado.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
					}
		        }
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Seleccione un Id de la tabla.","Seleccionar ID",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
			}
		}
	}
}