package interfazGrafica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
public class SeleccionarAccion extends JDialog implements ActionListener
{
	private JTable tbaciones;
	private JButton btaceptar;
	private JButton btcancelar;
	private InterfazInicio inicio;
	private JLabel etiqueta;
	private String entidadseleccionada;
	
	public SeleccionarAccion(InterfazInicio interfaz, String entidad)
	{
		super(interfaz, true);
		setLayout(new FlowLayout());
        setTitle("Seleccionar Accion en "+entidad+" - SESP");
		setResizable(false);
		setDefaultCloseOperation(2);
		int aumentoancho = 70;
		int anchoventana = 340+aumentoancho;
		int altoventana =239+10;
		setBounds((interfaz.getWidthvetana()/2)-(anchoventana/2),120,anchoventana,altoventana);
		
		//********************************************
		tbaciones = new JTable(new SimpleTableModel());
		// Create the combo box editor
	    JComboBox comboboxaciones = new JComboBox();
	    tbaciones.setValueAt("Accion:", 0, 0);
	    
	    comboboxaciones.addItem("Ver");
	    comboboxaciones.addItem("Adicionar");
	    comboboxaciones.addItem("Modificar");
	    comboboxaciones.addItem("Eliminar");
	    DefaultCellEditor editor2 = new DefaultCellEditor(comboboxaciones);
	    // Assign the editor to the second column
	    TableColumnModel tcm2 = tbaciones.getColumnModel();
	    tcm2.getColumn(1).setCellEditor(editor2);
		// Set column widths
	    tcm2.getColumn(0).setPreferredWidth(60);
	    tcm2.getColumn(1).setPreferredWidth(190);
	    tbaciones.setForeground(Color.BLACK);
	    tbaciones.setGridColor(new Color(35,91,92));
	    // Set row heighht
	    tbaciones.setRowHeight(20);
	    tbaciones.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tbaciones.setPreferredScrollableViewportSize(tbaciones.getPreferredSize());
		//********************************************
	    
	    inicio = interfaz;	
	    entidadseleccionada=entidad;
	    
		etiqueta = new JLabel(" Seleccione la Accion a Realizar");
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
		panelcolor.setPreferredSize(new Dimension (330+aumentoancho,200+15));
		panelcolor.setBackground(new Color(68,146,132));
		
		JLabel espacioa = new JLabel(" ");
	    espacioa.setPreferredSize(new Dimension(320+aumentoancho,15));	
	    panelcolor.add(espacioa);
		panelcolor.add(etiqueta);
		
		JLabel espaciob = new JLabel(" ");
	    espaciob.setPreferredSize(new Dimension(320+aumentoancho,3));	
	    panelcolor.add(espaciob);
	    panelcolor.add(new JScrollPane(tbaciones));
		JLabel espacioc = new JLabel(" ");
	    espacioc.setPreferredSize(new Dimension(320+aumentoancho,10));	
	    panelcolor.add(espacioc);
		
	    panelcolor.add(btaceptar);
	    panelcolor.add(new JLabel("      "));
	    panelcolor.add(btcancelar);
	    
	    add(panelcolor);
	}
	
	public void actionPerformed(ActionEvent evento){
		String clic = evento.getActionCommand();	
		if (clic.equals("Aceptar"))
		{
			String accionselecconada=(String) tbaciones.getValueAt(0, 1);
			if(accionselecconada == null)
			{
				JOptionPane.showMessageDialog(this,"Debe seleccionar una accion para avanzar.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
			}
			else
			{
				if(accionselecconada.equalsIgnoreCase("Ver"))
				{
					this.dispose();
					inicio.verDatosEntidad(entidadseleccionada);
				}
				if(accionselecconada.equalsIgnoreCase("Adicionar"))
				{
					this.dispose();
					inicio.verAdicionarDatos(entidadseleccionada);
				}
				if(accionselecconada.equalsIgnoreCase("Modificar"))
				{
					this.dispose();
					if(entidadseleccionada.equalsIgnoreCase("Regla"))
					{
						inicio.verSelecionarReglaConocimiento("Modificar");
					}
					else
					{
						inicio.verSeleccionarDatosporID(entidadseleccionada,"Modificar");
					}
				}
				if(accionselecconada.equalsIgnoreCase("Eliminar"))
				{
					this.dispose();
					if(entidadseleccionada.equalsIgnoreCase("Regla"))
					{
						inicio.verSelecionarReglaConocimiento("Eliminar");
					}
					else
					{
						inicio.verSeleccionarDatosporID(entidadseleccionada,"Eliminar");
					}
				}
			}
		}
		else if (clic.equals("Cancelar")){
			dispose();
		}
	}
}