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
public class SeleccionarTipoPrueba extends JDialog implements ActionListener
{
	private JTable tbprueba;
	private JButton btaceptar;
	private JButton btcancelar;
	private InterfazInicio inicio;
	private JLabel etiqueta;
	private Candidato candidatactual;
	
	public SeleccionarTipoPrueba(InterfazInicio interfaz, Candidato actualcandidat)
	{
		super(interfaz, true);
		setLayout(new FlowLayout());
        setTitle("Presentar Prueba - SESP");
		setResizable(false);
		setDefaultCloseOperation(2);
		int aumentoancho = 60;
		int anchoventana = 350+aumentoancho;
		int altoventana =235+10;
		setBounds((interfaz.getWidthvetana()/2)-(anchoventana/2),120,anchoventana,altoventana);
		
		//********************************************
		tbprueba = new JTable(new SimpleTableModel());
	    JComboBox comboboxprueba = new JComboBox();
	    tbprueba.setValueAt("Prueba:", 0, 0);
	    
	    List pruebas = null;//pruebas de la BD
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
		int numeropruebas = 0;
		if(pruebas != null){
			numeropruebas = pruebas.size();
		}
		for (int i = 0; i < numeropruebas; i++)
		{
			Prueba pruebaaux = (Prueba)pruebas.get(i);
			String nombre = pruebaaux.getNombre();
			comboboxprueba.addItem(nombre);
		}
		  
	    DefaultCellEditor editor2 = new DefaultCellEditor(comboboxprueba);
	    // Assign the editor to the second column
	    TableColumnModel tcm2 = tbprueba.getColumnModel();
	    tcm2.getColumn(1).setCellEditor(editor2);
		// Set column widths
	    tcm2.getColumn(0).setPreferredWidth(80);
	    tcm2.getColumn(1).setPreferredWidth(220);
	    tbprueba.setForeground(Color.BLACK);
	    tbprueba.setGridColor(new Color(35,91,92));
	    // Set row heighht
	    tbprueba.setRowHeight(20);
	    tbprueba.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tbprueba.setPreferredScrollableViewportSize(tbprueba.getPreferredSize());
		//********************************************
		
		inicio = interfaz;	
		candidatactual = actualcandidat;
		etiqueta = new JLabel("Seleccione el Tipo de Prueba");
		etiqueta.setForeground(new Color(171,223,140));
		etiqueta.setFont(new Font( "dandelion in the spring", Font.TRUETYPE_FONT, 33+7 ));
		etiqueta.setPreferredSize(new Dimension (295+aumentoancho,40+5));
		
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
		panelcolor.setPreferredSize(new Dimension (340+aumentoancho,195+15));
		panelcolor.setBackground(new Color(68,146,132));
		
		JLabel espacioup = new JLabel(" ");
	    espacioup.setPreferredSize(new Dimension(330+aumentoancho,10));	
	    panelcolor.add(espacioup);
	    
	    panelcolor.add(etiqueta);
		
		JLabel espaciou = new JLabel(" ");
	    espaciou.setPreferredSize(new Dimension(330+aumentoancho,3));	
	    panelcolor.add(espaciou);
	    
		panelcolor.add(new JScrollPane(tbprueba));
		
		JLabel espacios = new JLabel(" ");
	    espacios.setPreferredSize(new Dimension(330+aumentoancho,10));	
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
			String pruebaselecconada=(String) tbprueba.getValueAt(0, 1);
			if(pruebaselecconada == null)
			{
				JOptionPane.showMessageDialog(this,"Debe seleccionar un tipo de prueba para avanzar.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
			}
			else
			{
				if(pruebaselecconada.equalsIgnoreCase("Vacante Exclusivo"))
				{
					dispose();
					inicio.verSeleccionarConvocatoria("Vacante Exclusivo", candidatactual);
				}
				if(pruebaselecconada.equalsIgnoreCase("Vacante Multiple"))
				{
					dispose();
					inicio.verSeleccionarConvocatoria("Vacante Multiple", candidatactual);
				}
				if(pruebaselecconada.equalsIgnoreCase("Prueba Libre"))
				{
					Prueba pruebaseleccionada = null;
					try 
					{
						Conector conector = new Conector();
						conector.iniciarConexionBaseDatos();
						pruebaseleccionada= PruebaBD.buscarNombre("Prueba Libre", conector);
						conector.terminarConexionBaseDatos();
					} 
					catch (Exception e)
					{
						JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
					}
					if(pruebaseleccionada != null)
					{
						dispose();
						List roles = null;
					    try 
					    {
						    Conector conector = new Conector();
						    conector.iniciarConexionBaseDatos();
						    roles= RolBD.listar(conector);
						    conector.terminarConexionBaseDatos();
					    } catch (Exception e){
						    JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
					    }
					    if(roles != null)
					    {
					    	ArrayList todosroles = new ArrayList();
					    	for (int i = 0; i < roles.size(); i++) 
					    	{
								Rol rolaux = (Rol)roles.get(i);
								todosroles.add(rolaux);
							}
					    	inicio.verPruevaCandidato(candidatactual,pruebaseleccionada, null, todosroles);
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