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
public class SeleccionarResultadosRevisados extends JDialog implements ActionListener
{
	private JTable tbtiporesult;
	private JButton btaceptar;
	private JButton btcancelar;
	private InterfazInicio inicio;
	private JLabel etiqueta;
	
	public SeleccionarResultadosRevisados(InterfazInicio interfaz)
	{
		super(interfaz, true);
		setLayout(new FlowLayout());
        setTitle("Seleccionar Tipo de Resultados - SESP");
		setResizable(false);
		setDefaultCloseOperation(2);
		int aumentoancho = 70;
		int anchoventana = 370+aumentoancho;
		int altoventana =235+10;
		setBounds((interfaz.getWidthvetana()/2)-(anchoventana/2),120,anchoventana,altoventana);
		
		//********************************************
		tbtiporesult = new JTable(new SimpleTableModel());
		// Create the combo box editor
	    JComboBox comboboxtipo = new JComboBox();
	    tbtiporesult.setValueAt("Resultados:", 0, 0);
	    comboboxtipo.addItem("Sin Revisar");
	    comboboxtipo.addItem("Revisados");

	    DefaultCellEditor editor2 = new DefaultCellEditor(comboboxtipo);
	    // Assign the editor to the second column
	    TableColumnModel tcm2 = tbtiporesult.getColumnModel();
	    tcm2.getColumn(1).setCellEditor(editor2);
		// Set column widths
	    tcm2.getColumn(0).setPreferredWidth(80);
	    tcm2.getColumn(1).setPreferredWidth(220);
	    tbtiporesult.setForeground(Color.BLACK);
	    tbtiporesult.setGridColor(new Color(35,91,92));
	    // Set row heighht
	    tbtiporesult.setRowHeight(20);
	    tbtiporesult.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tbtiporesult.setPreferredScrollableViewportSize(tbtiporesult.getPreferredSize());
		//********************************************
		
		inicio = interfaz;	
		etiqueta = new JLabel(" Seleccione el Tipo de Resultado");
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
		panelcolor.setPreferredSize(new Dimension (360+aumentoancho,195+15));
		panelcolor.setBackground(new Color(68,146,132));
		
		JLabel espacioup = new JLabel(" ");
	    espacioup.setPreferredSize(new Dimension(350+aumentoancho,10));	
	    panelcolor.add(espacioup);
	    
	    panelcolor.add(etiqueta);
		
		JLabel espaciou = new JLabel(" ");
	    espaciou.setPreferredSize(new Dimension(350+aumentoancho,3));	
	    panelcolor.add(espaciou);
	    
		panelcolor.add(new JScrollPane(tbtiporesult));
		
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
			String tiposelecconado = (String) tbtiporesult.getValueAt(0, 1);
			if(tiposelecconado == null)
			{
				JOptionPane.showMessageDialog(this,"Debe seleccionar un tipo de resultado para avanzar.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
			}
			else
			{
				this.dispose();
				if(tiposelecconado.equalsIgnoreCase("Sin Revisar"))
				{
					inicio.verSeleccionarResultadosConvocatoria("Sin Revisar");
				}
				if(tiposelecconado.equalsIgnoreCase("Revisados"))
				{
					inicio.verSeleccionarResultadosConvocatoria("Revisados");
				}
			}
		}
		else if (clic.equals("Cancelar")){
			dispose();
		}
	}
}