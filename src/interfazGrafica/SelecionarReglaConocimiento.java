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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
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
public class SelecionarReglaConocimiento extends JDialog implements ActionListener
{
	private JTable tbdesde;
	private JTable tbhasta;
	private JButton btaceptar;
	private JButton btcancelar;
	private InterfazInicio inicio;
	private JLabel etiqueta;
	private String accionregla;
	
	public SelecionarReglaConocimiento(InterfazInicio interfaz, String accion)
	{
		super(interfaz, true);
		setLayout(new FlowLayout());
        setTitle("Seleccionar Regla Conocimiento - SESP");
		setResizable(false);
		setDefaultCloseOperation(2);
		int aumentoancho = 50;
		int anchoventana = 370+aumentoancho;
		int altoventana =250+10;
		setBounds((interfaz.getWidthvetana()/2)-(anchoventana/2),120,anchoventana,altoventana);
		
		//********************************************
		tbdesde = new JTable(new SimpleTableModel());
		// Create the combo box editor
	    JComboBox comboboxd = new JComboBox();
	    tbdesde.setValueAt("      Desde:", 0, 0);
	    comboboxd.addItem("Pregunta");
	    comboboxd.addItem("Competencia");
	    
	    DefaultCellEditor editor = new DefaultCellEditor(comboboxd);
	    // Assign the editor to the second column
	    TableColumnModel tcm = tbdesde.getColumnModel();
	    tcm.getColumn(1).setCellEditor(editor);
		// Set column widths
	    tcm.getColumn(0).setPreferredWidth(80);
	    tcm.getColumn(1).setPreferredWidth(220);
	    tbdesde.setForeground(Color.BLACK);
	    tbdesde.setGridColor(new Color(35,91,92));
	    // Set row heighht
	    tbdesde.setRowHeight(20);
	    tbdesde.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tbdesde.setPreferredScrollableViewportSize(tbdesde.getPreferredSize());
		//********************************************
	    
	  //********************************************
	    tbhasta = new JTable(new SimpleTableModel());
		// Create the combo box editor
	    JComboBox comboboxh = new JComboBox();
	    tbhasta.setValueAt("      Hasta:", 0, 0);
	    comboboxh.addItem("Escala");
	    comboboxh.addItem("Rol");
	    
	    DefaultCellEditor editor2 = new DefaultCellEditor(comboboxh);
	    // Assign the editor to the second column
	    TableColumnModel tcm2 = tbhasta.getColumnModel();
	    tcm2.getColumn(1).setCellEditor(editor2);
		// Set column widths
	    tcm2.getColumn(0).setPreferredWidth(80);
	    tcm2.getColumn(1).setPreferredWidth(220);
	    tbhasta.setForeground(Color.BLACK);
	    tbhasta.setGridColor(new Color(35,91,92));
	    // Set row heighht
	    tbhasta.setRowHeight(20);
	    tbhasta.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tbhasta.setPreferredScrollableViewportSize(tbhasta.getPreferredSize());
		//********************************************
		
		inicio = interfaz;
		accionregla = accion;
		etiqueta = new JLabel("      Seleccione las entidades");
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
		panelcolor.setPreferredSize(new Dimension (360+aumentoancho,210+15));
		panelcolor.setBackground(new Color(68,146,132));
		
		JLabel espacioup = new JLabel(" ");
	    espacioup.setPreferredSize(new Dimension(350+aumentoancho,10));	
	    panelcolor.add(espacioup);
	    
	    panelcolor.add(etiqueta);
		
		JLabel espaciou = new JLabel(" ");
	    espaciou.setPreferredSize(new Dimension(350+aumentoancho,3));	
	    panelcolor.add(espaciou);
	    
	    panelcolor.add(tbdesde);
	    panelcolor.add(tbhasta);
		
		JLabel espacios = new JLabel(" ");
	    espacios.setPreferredSize(new Dimension(350+aumentoancho,10));	
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
			String desdeentidad = (String) tbdesde.getValueAt(0, 1);
			if(desdeentidad == null)
			{
				JOptionPane.showMessageDialog(this,"Debe seleccionar desde una entidad para avanzar.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
			}
			else
			{
				String hastaentidad = (String) tbhasta.getValueAt(0, 1);
				if(hastaentidad == null)
				{
					JOptionPane.showMessageDialog(this,"Debe seleccionar hasta una entidad para avanzar.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
				}
				else
				{
					this.dispose();
					if(desdeentidad.equalsIgnoreCase("Pregunta") && hastaentidad.equalsIgnoreCase("Escala"))
					{
						if(accionregla.equalsIgnoreCase("Ver"))
						{
							List reglas = null;
							try
					        {
					            Conector conector = new Conector();
					            conector.iniciarConexionBaseDatos();
					            reglas= PreguntaEscalaBD.listar(conector);
					            conector.terminarConexionBaseDatos();
					        }
					        catch (Exception e)
							{
					        	JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
							}
					        if(reglas != null)
					        {
					        	inicio.verVerReglasConocimiento(desdeentidad, hastaentidad, reglas);
					        }
						}
						if(accionregla.equalsIgnoreCase("Adicionar"))
						{
							inicio.verAdicionarReglaConocimiento(desdeentidad, hastaentidad);
						}
						if(accionregla.equalsIgnoreCase("Modificar"))
						{
							inicio.verModificarReglaConocimiento(desdeentidad, hastaentidad);
						}
						if(accionregla.equalsIgnoreCase("Eliminar"))
						{
							inicio.verEliminarReglaConocimiento(desdeentidad, hastaentidad);
						}
					}
					else if(desdeentidad.equalsIgnoreCase("Competencia") && hastaentidad.equalsIgnoreCase("Escala"))
					{
						if(accionregla.equalsIgnoreCase("Ver"))
						{
							List reglas = null;
							try
					        {
					            Conector conector = new Conector();
					            conector.iniciarConexionBaseDatos();
					            reglas= CompetenciaEscalaBD.listar(conector);
					            conector.terminarConexionBaseDatos();
					        }
					        catch (Exception e)
							{
					        	JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
							}
					        if(reglas != null)
					        {
					        	inicio.verVerReglasConocimiento(desdeentidad, hastaentidad, reglas);
					        }
						}
						if(accionregla.equalsIgnoreCase("Adicionar"))
						{
							inicio.verAdicionarReglaConocimiento(desdeentidad, hastaentidad);
						}
						if(accionregla.equalsIgnoreCase("Modificar"))
						{
							inicio.verModificarReglaConocimiento(desdeentidad, hastaentidad);
						}
						if(accionregla.equalsIgnoreCase("Eliminar"))
						{
							inicio.verEliminarReglaConocimiento(desdeentidad, hastaentidad);
						}
					}
					else if(desdeentidad.equalsIgnoreCase("Competencia") && hastaentidad.equalsIgnoreCase("Rol"))
					{
						if(accionregla.equalsIgnoreCase("Ver"))
						{
							List reglas = null;
							try
					        {
					            Conector conector = new Conector();
					            conector.iniciarConexionBaseDatos();
					            reglas= CompetenciaRolBD.listar(conector);
					            conector.terminarConexionBaseDatos();
					        }
					        catch (Exception e)
							{
					        	JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
							}
					        if(reglas != null)
					        {
					        	inicio.verVerReglasConocimiento(desdeentidad, hastaentidad, reglas);
					        }
						}
						if(accionregla.equalsIgnoreCase("Adicionar"))
						{
							inicio.verAdicionarReglaConocimiento(desdeentidad, hastaentidad);
						}
						if(accionregla.equalsIgnoreCase("Modificar"))
						{
							inicio.verModificarReglaConocimiento(desdeentidad, hastaentidad);
						}
						if(accionregla.equalsIgnoreCase("Eliminar"))
						{
							inicio.verEliminarReglaConocimiento(desdeentidad, hastaentidad);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(this,"Le regla o relacion desde "+desdeentidad+" hasta "+hastaentidad+" no es valida como regla del conocimiento.","Seleccionar Regla", JOptionPane.WARNING_MESSAGE,new ImageIcon("./images/IconosInterfaz/advertencia.PNG"));
					}
				}
			}
		}
		else if (clic.equals("Cancelar")){
			dispose();
		}
	}
}