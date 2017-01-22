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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import basedeDatos.*;
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
public class VacanteMultiple extends JDialog implements ActionListener
{
	private JLabel etiqueta;
	private JTable tbroles;
	private JButton btaceptar;
	private JButton btcancelar;
	private InterfazInicio inicio;
	private String convocatoria;
	private Candidato candidatactual;
	
	public VacanteMultiple(InterfazInicio interfaz, String convocatoriaselecconada, Candidato actualcandidat)
	{
		super(interfaz, true);
		setLayout(new FlowLayout());
        setTitle("Vacante Multiple - SESP");
		setResizable(false);
		setDefaultCloseOperation(2);
		int aumentoancho = 80;
		int anchoventana = 450+aumentoancho;
		int altoventana =330+10;
		setBounds((interfaz.getWidthvetana()/2)-(anchoventana/2),120,anchoventana,altoventana);
		
		//**************************************************
		tbroles = new JTable(new RolesTableModel());
		// Create the combo box editor
	    JComboBox comboboxseleccionar = new JComboBox(RolesTableModel.getValidStates());
	    comboboxseleccionar.setEditable(false);
	    DefaultCellEditor editor2 = new DefaultCellEditor(comboboxseleccionar);
	    // Assign the editor to the second column
	    TableColumnModel tcm2 = tbroles.getColumnModel();
	    tcm2.getColumn(1).setCellEditor(editor2);
		// Set column widths
	    tcm2.getColumn(0).setPreferredWidth(200+30);
	    tcm2.getColumn(1).setPreferredWidth(100);
	    tbroles.setForeground(Color.BLACK);
	    tbroles.setGridColor(new Color(35,91,92));
	    
	    // Set row heighht
	    tbroles.setRowHeight(20);
	    tbroles.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tbroles.setPreferredScrollableViewportSize(tbroles.getPreferredSize());
		//**************************************************
		
	    inicio=interfaz;
	    convocatoria = convocatoriaselecconada;
	    candidatactual = actualcandidat;
	    etiqueta = new JLabel("Seleccione los roles a los que se postula");
		etiqueta.setForeground(new Color(171,223,140));
		etiqueta.setFont(new Font( "dandelion in the spring", Font.TRUETYPE_FONT, 33+7 ));
		etiqueta.setPreferredSize(new Dimension (385+aumentoancho,40+5));
		
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
		
		JScrollPane barras = new JScrollPane(tbroles);
		barras.setPreferredSize(new Dimension(318+30,120));
		
		JPanel panelcolor = new JPanel(new FlowLayout());
		panelcolor.setPreferredSize(new Dimension (440+aumentoancho,290+15));
		panelcolor.setBackground(new Color(68,146,132));
				
		JLabel espacioup = new JLabel(" ");
	    espacioup.setPreferredSize(new Dimension(430+aumentoancho,15));	
	    panelcolor.add(espacioup);
	    
	    panelcolor.add(etiqueta);
		
		JLabel espaciou = new JLabel(" ");
	    espaciou.setPreferredSize(new Dimension(430+aumentoancho,3));	
	    panelcolor.add(espaciou);
	    
	    panelcolor.add(barras);
		
		JLabel espacios = new JLabel(" ");
	    espacios.setPreferredSize(new Dimension(430+aumentoancho,15));	
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
			if(candidatactual != null)
			{
				Prueba pruebaseleccionada = null;
				try 
				{
					Conector conector = new Conector();
					conector.iniciarConexionBaseDatos();
					pruebaseleccionada= PruebaBD.buscarNombre("Vacante Multiple", conector);
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
						ArrayList rolesseleccionado = new ArrayList();
						Rol nombrerol = null;
						int numeroroles=tbroles.getRowCount();
						for (int i = 0; i < numeroroles; i++) 
						{
							String rolnombre=(String) tbroles.getValueAt(i, 0);
							String seleccion=(String) tbroles.getValueAt(i, 1);
							if(seleccion.equalsIgnoreCase("Seleccionar"))
							{
								try 
								{
									Conector conector = new Conector();
									conector.iniciarConexionBaseDatos();
									nombrerol= RolBD.buscarNombre(rolnombre.trim(), conector);
									conector.terminarConexionBaseDatos();
								} 
								catch (Exception e)
								{
									JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
								}
								if(nombrerol != null)
								{
									rolesseleccionado.add(nombrerol);
									nombrerol = null;
								}
							}
						}
						if(rolesseleccionado.size()>1)
						{
							dispose();
							inicio.verPruevaCandidato(candidatactual,pruebaseleccionada, convoseleccionada, rolesseleccionado);
						}
						else
						{
							JOptionPane.showMessageDialog(this,"Debe seleccionar dos o mas roles para la prueba de vacante multiple.","Seleccionar Roles", JOptionPane.WARNING_MESSAGE,new ImageIcon("./images/IconosInterfaz/advertencia.PNG"));
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