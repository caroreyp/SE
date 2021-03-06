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
public class SeleccionarConvocatoria extends JDialog implements ActionListener
{
	private JTable tbconvocatoria;
	private JButton btaceptar;
	private JButton btcancelar;
	private InterfazInicio inicio;
	private JLabel etiqueta;
	private String tipopruebaseleccionada;
	private List convocatoriasvigentes;
	private Candidato candidatactual;
	
	public SeleccionarConvocatoria(InterfazInicio interfaz, String tipoprueba, Candidato actualcandidat)
	{
		super(interfaz, true);
		setLayout(new FlowLayout());
        setTitle("Seleccionar Convocatoria - SESP");
		setResizable(false);
		setDefaultCloseOperation(2);
		int aumentoancho = 40;
		int anchoventana = 350+aumentoancho;
		int altoventana =235+10;
		setBounds((interfaz.getWidthvetana()/2)-(anchoventana/2),120,anchoventana,altoventana);
		
		//********************************************
		tbconvocatoria = new JTable(new SimpleTableModel());
		// Create the combo box editor
	    JComboBox comboboxconvo = new JComboBox();
	    tbconvocatoria.setValueAt("Convocatoria:", 0, 0);
	    
	    convocatoriasvigentes = null;//convocatorias no finalizadas de la BD
		try 
		{
			Conector conector = new Conector();
			conector.iniciarConexionBaseDatos();
			convocatoriasvigentes= ConvocatoriaBD.buscarNoFinalizadas(conector);
			conector.terminarConexionBaseDatos();
		} 
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
		}
		int numeroconvovigentes = 0;
		if(convocatoriasvigentes != null)
		{
			numeroconvovigentes = convocatoriasvigentes.size();
		}
		for (int i = 0; i < numeroconvovigentes; i++)
		{
			Convocatoria convoaux = (Convocatoria)convocatoriasvigentes.get(i);
			int idconvoc = convoaux.getIdconvocatoria();
			String nombre = convoaux.getNombre();
			comboboxconvo.addItem(idconvoc+" - "+nombre);
		}

	    DefaultCellEditor editor2 = new DefaultCellEditor(comboboxconvo);
	    // Assign the editor to the second column
	    TableColumnModel tcm2 = tbconvocatoria.getColumnModel();
	    tcm2.getColumn(1).setCellEditor(editor2);
		// Set column widths
	    tcm2.getColumn(0).setPreferredWidth(80);
	    tcm2.getColumn(1).setPreferredWidth(220);
	    tbconvocatoria.setForeground(Color.BLACK);
	    tbconvocatoria.setGridColor(new Color(35,91,92));
	    // Set row heighht
	    tbconvocatoria.setRowHeight(20);
	    tbconvocatoria.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tbconvocatoria.setPreferredScrollableViewportSize(tbconvocatoria.getPreferredSize());
		//********************************************
		
		inicio = interfaz;	
		tipopruebaseleccionada = tipoprueba;
		candidatactual = actualcandidat;
		etiqueta = new JLabel("  Seleccione la Convocatoria");
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
	    espacioup.setPreferredSize(new Dimension(320+aumentoancho,10));	
	    panelcolor.add(espacioup);
	    
	    panelcolor.add(etiqueta);
		
		JLabel espaciou = new JLabel(" ");
	    espaciou.setPreferredSize(new Dimension(320+aumentoancho,3));	
	    panelcolor.add(espaciou);
	    
		panelcolor.add(new JScrollPane(tbconvocatoria));
		
		JLabel espacios = new JLabel(" ");
	    espacios.setPreferredSize(new Dimension(320+aumentoancho,10));	
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
			String convocatoriaselecconada = (String) tbconvocatoria.getValueAt(0, 1);
			if(convocatoriaselecconada == null && convocatoriasvigentes.size() > 0)
			{
				JOptionPane.showMessageDialog(this,"Debe seleccionar una convocatoria para avanzar.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
			}
			else if(convocatoriaselecconada == null && convocatoriasvigentes.size() == 0)
			{
				JOptionPane.showMessageDialog(this,"No hay Convocatorias vigentes para presentar pruebas para "+tipopruebaseleccionada+".","Seleccionar Convocatoria", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("./images/IconosInterfaz/informacion.PNG"));
			}
			else
			{
				if(tipopruebaseleccionada.equalsIgnoreCase("Vacante Exclusivo"))
				{
					dispose();
					inicio.verVacanteExclusivo(convocatoriaselecconada, candidatactual);
				}
				if(tipopruebaseleccionada.equalsIgnoreCase("Vacante Multiple"))
				{
					dispose();
					inicio.verVacanteMultiple(convocatoriaselecconada, candidatactual);
				}
			}
		}
		else if (clic.equals("Cancelar")){
			dispose();
		}
	}
}