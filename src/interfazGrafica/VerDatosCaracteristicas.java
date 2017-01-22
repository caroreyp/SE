package interfazGrafica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

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
public class VerDatosCaracteristicas extends JDialog implements ActionListener
{
	private JTable tbcompetencias;
	private JButton btaceptar;
	private JButton btcancelar;
	private InterfazInicio inicio;
	
	public VerDatosCaracteristicas(InterfazInicio interfaz, List caracteristicas)
	{
		super(interfaz, true);
		setLayout(new FlowLayout());
        setTitle("Ver Datos Caracteristicas - SESP");
        setResizable(false);
		setDefaultCloseOperation(2);
		
		int numerocaracter = 0;//numero de competencias en la BD

        if(caracteristicas != null)
        {
        	numerocaracter = caracteristicas.size();
        }
        int aumentoancho = 10;
		int anchoventana = 1015+aumentoancho;
		int altoventana =159+10;
		if(numerocaracter < 27)
		{
			altoventana = altoventana + (20*numerocaracter);
		}
		else
		{
			altoventana =679;
		}
		
		setBounds((interfaz.getWidthvetana()/2)-(anchoventana/2),(interfaz.getHeigthvetana()/2)-(altoventana/2),anchoventana,altoventana);
		
		inicio=interfaz;
        
		//********************************************
		tbcompetencias = new JTable(numerocaracter+1,3);
		tbcompetencias.setValueAt("ID CARACTERISTICA", 0, 0);
		tbcompetencias.setValueAt("              NOMBRE", 0, 1);
		tbcompetencias.setValueAt("                                                                                                               DESCRIPCION", 0, 2);
		
		tbcompetencias.setEnabled(false);
		
		for (int i = 0; i < numerocaracter; i++)
		{
			Caracteristica competenaux = (Caracteristica)caracteristicas.get(i);
			tbcompetencias.setValueAt(competenaux.getIdcaracteristica(), i+1, 0);
			tbcompetencias.setValueAt(competenaux.getNombre(), i+1, 1);
			tbcompetencias.setValueAt(competenaux.getDescripcion(), i+1, 2);
		}
	    // Assign the editor to the second column
	    TableColumnModel tcm2 = tbcompetencias.getColumnModel();
		// Set column widths
	    tcm2.getColumn(0).setPreferredWidth(125);
	    tcm2.getColumn(1).setPreferredWidth(150);
	    tcm2.getColumn(2).setPreferredWidth(685);
	    
	    tbcompetencias.setForeground(Color.BLACK);
	    tbcompetencias.setGridColor(Color.BLACK);
	    // Set row heighht
	    tbcompetencias.setRowHeight(20);
	    tbcompetencias.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tbcompetencias.setPreferredScrollableViewportSize(tbcompetencias.getPreferredSize());
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
		
		int tbancho = 960;
		int tbalto = (tbcompetencias.getRowCount()*20);

		JPanel panel = new JPanel(new FlowLayout());
	    panel.setPreferredSize(new Dimension(tbancho+10,tbalto+10));
	    panel.setBackground(new Color(60,116,107));
	  
	    panel.add(tbcompetencias);
	    
	    JScrollPane barras = new JScrollPane(panel);
	    barras.setPreferredSize(new Dimension(995,altoventana-109));
	    
	    JPanel panelcolor = new JPanel(new FlowLayout());
	    panelcolor.setPreferredSize(new Dimension (1005+aumentoancho,altoventana-39+15));
		panelcolor.setBackground(new Color(68,146,132));
		
		panelcolor.add(barras);
		JLabel espacioa = new JLabel(" ");
	    espacioa.setPreferredSize(new Dimension(1000+aumentoancho,3));	
	    panelcolor.add(espacioa);
		panelcolor.add(btaceptar);
		panelcolor.add(new JLabel("          "));
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