package interfazGrafica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import mundo.Convocatoria;

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
public class VerConvocatorias extends JDialog implements ActionListener
{
	private JTable tbconvocatorias;
	private JButton btaceptar;
	private JButton btcancelar;
	private InterfazInicio inicio;
	
	public VerConvocatorias(InterfazInicio interfaz)
	{
		super(interfaz, true);
		setLayout(new FlowLayout());
        setTitle("Ver Convocatorias - SESP");
        setResizable(false);
		setDefaultCloseOperation(2);
		int aumentoancho = 30;
		int anchoventana = 680+aumentoancho;
		int altoventana =229+10;
		setBounds((interfaz.getWidthvetana()/2)-(anchoventana/2),140,anchoventana,altoventana);
		
		inicio=interfaz;
		
		int numeroconvocatorias = 0;//numero de convocatorias en la BD
		
		List convocatorias = null;
		try
        {
            Conector conector = new Conector();
            conector.iniciarConexionBaseDatos();
            convocatorias= ConvocatoriaBD.listar(conector);
            conector.terminarConexionBaseDatos();
        }
        catch (Exception e)
		{
        	JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
		}
        
        if(convocatorias != null)
        {
        	numeroconvocatorias = convocatorias.size();
        }
		//********************************************
		tbconvocatorias = new JTable(numeroconvocatorias+1,5);
		tbconvocatorias.setValueAt("   Id Convocatoria", 0, 0);
		tbconvocatorias.setValueAt("            Nombre Convocatoria", 0, 1);
		tbconvocatorias.setValueAt("    Fecha Inicial", 0, 2);
		tbconvocatorias.setValueAt("     Fecha Final", 0, 3);
		tbconvocatorias.setValueAt("     Finalizada", 0, 4);
		
		tbconvocatorias.setEnabled(false);
		
		for (int i = 0; i < numeroconvocatorias; i++)
		{
			Convocatoria convocatoriaaux = (Convocatoria)convocatorias.get(i);
			tbconvocatorias.setValueAt(convocatoriaaux.getIdconvocatoria(), i+1, 0);
			tbconvocatorias.setValueAt(convocatoriaaux.getNombre(), i+1, 1);
			tbconvocatorias.setValueAt(convocatoriaaux.getFechainicial(), i+1, 2);
			tbconvocatorias.setValueAt(convocatoriaaux.getFechafinal(), i+1, 3);
			tbconvocatorias.setValueAt(convocatoriaaux.getFinalizada(), i+1, 4);
		}
	    //DefaultCellEditor editor2 = new DefaultCellEditor(caja);
	    // Assign the editor to the second column
	    TableColumnModel tcm2 = tbconvocatorias.getColumnModel();
	    //tcm2.getColumn(1).setCellEditor(editor2);
		// Set column widths
	    tcm2.getColumn(0).setPreferredWidth(110);
	    tcm2.getColumn(1).setPreferredWidth(190);
	    tcm2.getColumn(2).setPreferredWidth(100);
	    tcm2.getColumn(3).setPreferredWidth(100);
	    tcm2.getColumn(4).setPreferredWidth(100);
	    
	    tbconvocatorias.setForeground(Color.BLACK);
	    tbconvocatorias.setGridColor(Color.BLACK);
	    // Set row heighht
	    tbconvocatorias.setRowHeight(20);
	    tbconvocatorias.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tbconvocatorias.setPreferredScrollableViewportSize(tbconvocatorias.getPreferredSize());
		//********************************************
	    
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
		
		int tbancho = 600;
		int tbalto = (tbconvocatorias.getRowCount()*20);

		JPanel panel = new JPanel(new FlowLayout());
	    panel.setPreferredSize(new Dimension(tbancho+10,tbalto+10));
	    panel.setBackground(new Color(60,116,107));
	  
	    panel.add(tbconvocatorias);
	    
	    JScrollPane barras = new JScrollPane(panel);
	    barras.setPreferredSize(new Dimension(630,90));
	    
	    JPanel panelcolor = new JPanel(new FlowLayout());
		panelcolor.setPreferredSize(new Dimension (670+aumentoancho,190+15));
		panelcolor.setBackground(new Color(68,146,132));
	    
		JLabel espacioa = new JLabel(" ");
	    espacioa.setPreferredSize(new Dimension(670+aumentoancho,10));	
	    panelcolor.add(espacioa);
	    panelcolor.add(barras);
	    JLabel espaciob = new JLabel(" ");
	    espaciob.setPreferredSize(new Dimension(670+aumentoancho,10));	
	    panelcolor.add(espaciob);
	    panelcolor.add(btaceptar);
	    panelcolor.add(new JLabel("      "));
	    panelcolor.add(btcancelar);
	    add(panelcolor);
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
	}
}