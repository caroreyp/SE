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
public class VerDatosCompetencias extends JDialog implements ActionListener
{
	private JTable tbcompetencias;
	private JButton btmodificar;
	private JButton bteliminar;
	private JButton btaceptar;
	private JButton btcancelar;
	private InterfazInicio inicio;
	
	public VerDatosCompetencias(InterfazInicio interfaz)
	{
		super(interfaz, true);
		setLayout(new FlowLayout());
        setTitle("Ver Datos Competencias - SESP");
        setResizable(false);
		setDefaultCloseOperation(2);
		
		int numerocompetencias = 0;//numero de competencias en la BD
		List competencias = null;
		try
        {
            Conector conector = new Conector();
            conector.iniciarConexionBaseDatos();
            competencias= CompetenciaBD.listar(conector);
            conector.terminarConexionBaseDatos();
        }
        catch (Exception e)
		{
        	JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
		}
        
        if(competencias != null)
        {
        	numerocompetencias = competencias.size();
        }
        int aumentoancho = 10;
		int anchoventana = 1015+aumentoancho;
		int altoventana =159+10;
		if(numerocompetencias < 27)
		{
			altoventana = altoventana + (20*numerocompetencias);
		}
		else
		{
			altoventana =679;
		}
		
		setBounds((interfaz.getWidthvetana()/2)-(anchoventana/2),(interfaz.getHeigthvetana()/2)-((altoventana+65)/2),anchoventana,altoventana+65);
		
		inicio=interfaz;
        
		//********************************************
		tbcompetencias = new JTable(new VerCompetenciasTableModel());
		tbcompetencias.setValueAt(" ID COMPETENCIA", 0, 0);
		tbcompetencias.setValueAt("              NOMBRE", 0, 1);
		tbcompetencias.setValueAt("                                                                                                               DESCRIPCION", 0, 2);
		
		for (int i = 0; i < numerocompetencias; i++)
		{
			Competencia competenaux = (Competencia)competencias.get(i);
			tbcompetencias.setValueAt(competenaux.getIdcompetencia(), i+1, 0);
			tbcompetencias.setValueAt(competenaux.getNombre(), i+1, 1);
			tbcompetencias.setValueAt(competenaux.getDescripcion(), i+1, 2);
		}
	    // Assign the editor to the second column
	    TableColumnModel tcm2 = tbcompetencias.getColumnModel();
		// Set column widths
	    tcm2.getColumn(0).setPreferredWidth(110);
	    tcm2.getColumn(1).setPreferredWidth(150);
	    tcm2.getColumn(2).setPreferredWidth(700);
	    
	    tbcompetencias.setForeground(Color.BLACK);
	    tbcompetencias.setGridColor(Color.BLACK);
	    // Set row heighht
	    tbcompetencias.setRowHeight(20);
	    tbcompetencias.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tbcompetencias.setPreferredScrollableViewportSize(tbcompetencias.getPreferredSize());
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
		
		int tbancho = 960;
		int tbalto = (tbcompetencias.getRowCount()*20);

		JPanel panel = new JPanel(new FlowLayout());
	    panel.setPreferredSize(new Dimension(tbancho+10,tbalto+10));
	    panel.setBackground(new Color(60,116,107));
	  
	    panel.add(tbcompetencias);
	    
	    JScrollPane barras = new JScrollPane(panel);
	    barras.setPreferredSize(new Dimension(995,altoventana-109));
	    
	    JPanel panelcolor = new JPanel(new FlowLayout());
	    panelcolor.setPreferredSize(new Dimension (1005+aumentoancho,altoventana-39+15+65));
		panelcolor.setBackground(new Color(68,146,132));
		
		panelcolor.add(barras);
		JLabel espacioa = new JLabel(" ");
	    espacioa.setPreferredSize(new Dimension(1000+aumentoancho,3));	
	    panelcolor.add(espacioa);
		panelcolor.add(btmodificar);
	    panelcolor.add(new JLabel("      "));
	    panelcolor.add(bteliminar);
		JLabel espaciob = new JLabel(" ");
	    espaciob.setPreferredSize(new Dimension(1000+aumentoancho,3));	
	    panelcolor.add(espaciob);
		panelcolor.add(btaceptar);
		panelcolor.add(new JLabel("          "));
		panelcolor.add(btcancelar);
		
		add(panelcolor);
	}
	
	/**
	 * Es el metodo que actualiza la tabla con los datos que han sido modificados o eliminados 
	 */
	public void actualizarTablaVerDatos()
	{
		int numerocompetencias = 0;//numero de competencias en la BD
		List competencias = null;
		try
        {
            Conector conector = new Conector();
            conector.iniciarConexionBaseDatos();
            competencias= CompetenciaBD.listar(conector);
            conector.terminarConexionBaseDatos();
        }
        catch (Exception e)
		{
        	JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
		}
        
        if(competencias != null)
        {
        	numerocompetencias = competencias.size();
        }
        for (int i = 1; i < tbcompetencias.getRowCount(); i++)
		{
        	tbcompetencias.setValueAt("", i, 0);
			tbcompetencias.setValueAt("", i, 1);
			tbcompetencias.setValueAt("", i, 2);
		}
        for (int i = 0; i < numerocompetencias; i++)
		{
			Competencia competenaux = (Competencia)competencias.get(i);
			tbcompetencias.setValueAt(competenaux.getIdcompetencia(), i+1, 0);
			tbcompetencias.setValueAt(competenaux.getNombre(), i+1, 1);
			tbcompetencias.setValueAt(competenaux.getDescripcion(), i+1, 2);
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
			int rowselect = tbcompetencias.getSelectedRow();
			if(rowselect != -1 && rowselect != 0)
			{
				int idseleccionado = (Integer) tbcompetencias.getValueAt(rowselect, 0);
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
			            lista= CompetenciaBD.listar(conector);
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
						Competencia competenaux = (Competencia)lista.get(i);
						int idcompeten = competenaux.getIdcompetencia();
						if(idcompeten == idnumerico)
						{
							entidadencontrada.add(competenaux);
							break;
						}
					}
					if(entidadencontrada.size() > 0)
					{
						inicio.verModificarDatosEntidad("Competencia",entidadencontrada);
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
			int rowselect = tbcompetencias.getSelectedRow();
			if(rowselect != -1 && rowselect != 0)
			{
				int idseleccionado = (Integer) tbcompetencias.getValueAt(rowselect, 0);
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
			            lista= CompetenciaBD.listar(conector);
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
						Competencia competenaux = (Competencia)lista.get(i);
						int idcompeten = competenaux.getIdcompetencia();
						if(idcompeten == idnumerico)
						{
							entidadencontrada.add(competenaux);
							break;
						}
					}
					if(entidadencontrada.size() > 0)
					{
						inicio.eliminarDatosEnEntidad("Competencia",entidadencontrada);
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