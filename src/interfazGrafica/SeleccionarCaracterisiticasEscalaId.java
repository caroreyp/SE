package interfazGrafica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumnModel;

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
public class SeleccionarCaracterisiticasEscalaId extends JDialog implements ActionListener
{
	private JButton btaceptar;
	private JButton btcancelar;
	private InterfazInicio inicio;
	private JLabel etiquetatxt;
	private JTable tbid;
	
	public SeleccionarCaracterisiticasEscalaId(InterfazInicio interfaz)
	{
		super(interfaz, true);
		setLayout(new FlowLayout());
        setTitle("Seleccionar Caraterisiticas de una Escala - SESP");
		setResizable(false);
		setDefaultCloseOperation(2);
		int aumentoancho = 140;
		int anchoventana = 520+aumentoancho;
		int altoventana =240+10;
		setBounds((interfaz.getWidthvetana()/2)-(anchoventana/2),140,anchoventana,altoventana);
		
		//********************************************
		etiquetatxt = new JLabel(" Escriba el Id de la Escala para ver las caracterisiticas");
		etiquetatxt.setForeground(new Color(171,223,140));
		etiquetatxt.setFont(new Font( "dandelion in the spring", Font.TRUETYPE_FONT, 33+7 ));
		etiquetatxt.setPreferredSize(new Dimension (490+aumentoancho,40+5));
		
		tbid = new JTable(new SimpleTableModel());
		// Create the combo box editor
		JTextField caja = new JTextField();
		tbid.setValueAt("Id Escala:", 0, 0);
	    DefaultCellEditor editor2 = new DefaultCellEditor(caja);
	    // Assign the editor to the second column
	    TableColumnModel tcm2 = tbid.getColumnModel();
	    tcm2.getColumn(1).setCellEditor(editor2);
		// Set column widths
	    tcm2.getColumn(0).setPreferredWidth(100);
	    tcm2.getColumn(1).setPreferredWidth(240);
	    tbid.setForeground(Color.BLACK);
	    tbid.setGridColor(new Color(35,91,92));
	    tbid.setToolTipText("Haga doble click para escribir. Para cada dato presione Enter para finalizar.");
	    // Set row heighht
	    tbid.setRowHeight(20);
	    tbid.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tbid.setPreferredScrollableViewportSize(tbid.getPreferredSize());
		//********************************************
	    
		inicio=interfaz;
		
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
		panelcolor.setPreferredSize(new Dimension (510+aumentoancho,200+15));
		panelcolor.setBackground(new Color(68,146,132));
		
		JLabel espacioa = new JLabel(" ");
	    espacioa.setPreferredSize(new Dimension(500+aumentoancho,15));	
	    panelcolor.add(espacioa);
	    panelcolor.add(etiquetatxt);
	    JLabel espaciob = new JLabel(" ");
	    espaciob.setPreferredSize(new Dimension(500+aumentoancho,3));	
	    panelcolor.add(espaciob);
		panelcolor.add(new JScrollPane(tbid));
		JLabel espacioc = new JLabel(" ");
	    espacioc.setPreferredSize(new Dimension(500+aumentoancho,10));	
	    panelcolor.add(espacioc);
	    panelcolor.add(btaceptar);
		panelcolor.add(new JLabel("      "));
		panelcolor.add(btcancelar);
	    
	    add(panelcolor);
	}
	
	public void actionPerformed(ActionEvent evento)
	{
		String clic = evento.getActionCommand();	
		if (clic.equals("Aceptar"))
		{
			String iddigitado = (String) tbid.getValueAt(0, 1);
			if(iddigitado == null)
			{
				JOptionPane.showMessageDialog(this,"Presione Enter al terminar de escribir el numero del Id.","Seleccionar Datos",JOptionPane.WARNING_MESSAGE,new ImageIcon("./images/IconosInterfaz/advertencia.PNG"));
			}
			else
			{
				int idnumerico = 0;
				try
		        {
		            idnumerico = Integer.parseInt(iddigitado);
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
			            lista= CaracteristicaBD.buscarCaracterisiticasEscala(idnumerico, conector);
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
					if(tamañolista > 0)
					{
						this.dispose();
						inicio.verVerDatosCaracteristicas(lista);
					}
					else
					{
						JOptionPane.showMessageDialog(this,"El Id de la escala digitado no esta almacenado.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
					}
		        }
			}
		}
		else if (clic.equals("Cancelar")){
			dispose();
		}
	}
}