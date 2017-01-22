package interfazGrafica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
public class VacanteExclusivo extends JDialog implements ActionListener
{
	private JLabel etiqueta;
	private JTable tbroles;
	private JButton btaceptar;
	private JButton btcancelar;
	private InterfazInicio inicio;
	private String convocatoria;
	private List roles;
	private Candidato candidatactual;
	
	public VacanteExclusivo(InterfazInicio interfaz, String convocatoriaselecconada, Candidato actualcandidat)
	{
		super(interfaz, true);
		setLayout(new FlowLayout());
        setTitle("Vacante Exclusivo - SESP");
        setResizable(false);
		setDefaultCloseOperation(2);
		int aumentoancho = 60;
		int anchoventana = 370+aumentoancho;
		int altoventana =230+10;
		setBounds((interfaz.getWidthvetana()/2)-(anchoventana/2),140,anchoventana,altoventana);
		
		//********************************************
		tbroles = new JTable(new SimpleTableModel());
		// Create the combo box editor
	    JComboBox comboboxrol = new JComboBox();
	    tbroles.setValueAt("Rol:", 0, 0);
	    
	    roles = null;//roles de la BD
		try {
			Conector conector = new Conector();
			conector.iniciarConexionBaseDatos();
			roles= RolBD.listar(conector);
			conector.terminarConexionBaseDatos();
		} catch (Exception e){
			JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
		}
		int numeroroles = 0;
		if(roles != null){
			numeroroles = roles.size();
		}
		for (int i = 0; i < numeroroles; i++)
		{
			Rol rolaux = (Rol)roles.get(i);
			String nombre = rolaux.getNombre();
			comboboxrol.addItem(nombre);
		}
		  
	    DefaultCellEditor editor2 = new DefaultCellEditor(comboboxrol);
	    // Assign the editor to the second column
	    TableColumnModel tcm2 = tbroles.getColumnModel();
	    tcm2.getColumn(1).setCellEditor(editor2);
		// Set column widths
	    tcm2.getColumn(0).setPreferredWidth(40);
	    tcm2.getColumn(1).setPreferredWidth(260);
	    tbroles.setForeground(Color.BLACK);
	    tbroles.setGridColor(new Color(35,91,92));
	    // Set row heighht
	    tbroles.setRowHeight(20);
	    tbroles.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tbroles.setPreferredScrollableViewportSize(tbroles.getPreferredSize());
		//********************************************
		
	    inicio = interfaz;
	    convocatoria = convocatoriaselecconada;
	    candidatactual = actualcandidat;
		etiqueta = new JLabel("Seleccione el Rol para la Prueba");
		etiqueta.setForeground(new Color(171,223,140));
		etiqueta.setFont(new Font( "dandelion in the spring", Font.TRUETYPE_FONT, 33+7 ));
		etiqueta.setPreferredSize(new Dimension (315+aumentoancho,40+5));
		
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
		
		JPanel panelcolor = new JPanel(new FlowLayout());
		panelcolor.setPreferredSize(new Dimension (360+aumentoancho,190+15));
		panelcolor.setBackground(new Color(68,146,132));
		
		JLabel espacioup = new JLabel(" ");
	    espacioup.setPreferredSize(new Dimension(340+aumentoancho,15));	
	    panelcolor.add(espacioup);
	    
	    panelcolor.add(etiqueta);
		
		JLabel espaciou = new JLabel(" ");
	    espaciou.setPreferredSize(new Dimension(340+aumentoancho,3));	
	    panelcolor.add(espaciou);
	    
	    panelcolor.add(tbroles);
		
		JLabel espacios = new JLabel(" ");
	    espacios.setPreferredSize(new Dimension(340+aumentoancho,15));	
	    panelcolor.add(espacios);
		
	    panelcolor.add(btaceptar);
		panelcolor.add(new JLabel("      "));
		panelcolor.add(btcancelar);
	    
	    add(panelcolor);
	}
	
	public void actionPerformed(ActionEvent evento){
		String clic = evento.getActionCommand();	
		if (clic.equals("Aceptar"))
		{
			String rolselecconado=(String) tbroles.getValueAt(0, 1);
			if(rolselecconado == null && roles.size() > 0)
			{
				JOptionPane.showMessageDialog(this,"Debe seleccionar un tipo de rol para avanzar.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
			}
			else if(rolselecconado == null && roles.size() == 0)
			{
				JOptionPane.showMessageDialog(this,"No hay Roles para presentar pruebas para Vacante Exclusivo.","Seleccionar Rol", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
			}
			else
			{
				if(candidatactual != null)
				{
					Prueba pruebaseleccionada = null;
					try 
					{
						Conector conector = new Conector();
						conector.iniciarConexionBaseDatos();
						pruebaseleccionada= PruebaBD.buscarNombre("Vacante Exclusivo", conector);
						conector.terminarConexionBaseDatos();
					} 
					catch (Exception e)
					{
						JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
					}
					if(pruebaseleccionada != null)
					{
						Convocatoria convoseleccionada = null;
						List convocators = null;
						int numeroconvocators = 0;
						try 
						{
							Conector conector = new Conector();
							conector.iniciarConexionBaseDatos();
							convocators = ConvocatoriaBD.buscarNoFinalizadas(conector);
							conector.terminarConexionBaseDatos();
						} 
						catch (Exception e)
						{
							JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
						}
						if(convocators != null)
						{
							numeroconvocators = convocators.size();
						}
						for (int i = 0; i < numeroconvocators; i++)
						{
							Convocatoria convocatoraux = (Convocatoria)convocators.get(i);
							int idconvoc = convocatoraux.getIdconvocatoria();
							String nombre = convocatoraux.getNombre();
							if(convocatoria.equalsIgnoreCase(idconvoc+" - "+nombre))
							{
								convoseleccionada = convocatoraux;
								break;
							}
						}
						if(convoseleccionada != null)
						{
							ArrayList rolseleccionado = new ArrayList();
							Rol nombrerol = null;
							try 
							{
								Conector conector = new Conector();
								conector.iniciarConexionBaseDatos();
								nombrerol= RolBD.buscarNombre(rolselecconado.trim(), conector);
								conector.terminarConexionBaseDatos();
							} 
							catch (Exception e)
							{
								JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
							}
							if(nombrerol != null)
							{
								rolseleccionado.add(nombrerol);
							}
							if(rolseleccionado.size()>0)
							{
								dispose();
								inicio.verPruevaCandidato(candidatactual,pruebaseleccionada, convoseleccionada, rolseleccionado);
							}
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