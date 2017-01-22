package interfazGrafica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
public class VerPermisos extends JDialog implements ActionListener
{
	private JTable tbpermisos;
	private JButton btaceptar;
	private JButton btcancelar;
	private InterfazInicio inicio;
	
	public VerPermisos(InterfazInicio interfaz, Permiso permisocandidat)
	{
		super(interfaz, true);
		setLayout(new FlowLayout());
        setTitle("Ver Permiso Candidato - SESP");
        setResizable(false);
		setDefaultCloseOperation(2);
		int aumentoancho = 30;
		int anchoventana = 635+aumentoancho;
		int altoventana =190+10;
		setBounds((interfaz.getWidthvetana()/2)-(anchoventana/2),140,anchoventana,altoventana);
		
		inicio=interfaz;
		//********************************************
		tbpermisos = new JTable(2,4);
		tbpermisos.setValueAt("NUMERO PERMISO", 0, 0);
		tbpermisos.setValueAt("FECHA ASIGNACION", 0, 1);
		tbpermisos.setValueAt("HORA ASIGNACION", 0, 2);
		tbpermisos.setValueAt("PERMISO ASIGNADO POR:", 0, 3);
		
		tbpermisos.setEnabled(false);
		
		Administrador adminpermiso = null;
		try
        {
            Conector conector = new Conector();
            conector.iniciarConexionBaseDatos();
            adminpermiso = AdministradorBD.buscarIdAdministrador(permisocandidat.getIdadmin(), conector);
            conector.terminarConexionBaseDatos();
        }
        catch (Exception e)
		{
        	JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
		}
        
        if(permisocandidat != null)
        {
        	if(adminpermiso != null)
            {
        		tbpermisos.setValueAt(permisocandidat.getIdpermiso(), 1, 0);
        		tbpermisos.setValueAt(permisocandidat.getFechapermiso(), 1, 1);
        		tbpermisos.setValueAt(permisocandidat.getHorapermiso(), 1, 2);
        		tbpermisos.setValueAt(adminpermiso.getNombres()+" "+adminpermiso.getApellidos(), 1, 3);
            }
        }
	    // Assign the editor to the second column
	    TableColumnModel tcm2 = tbpermisos.getColumnModel();
	    //tcm2.getColumn(1).setCellEditor(editor2);
		// Set column widths
	    tcm2.getColumn(0).setPreferredWidth(115);
	    tcm2.getColumn(1).setPreferredWidth(120);
	    tcm2.getColumn(2).setPreferredWidth(120);
	    tcm2.getColumn(3).setPreferredWidth(230);
	    tbpermisos.setForeground(Color.BLACK);
	    tbpermisos.setGridColor(Color.BLACK);
	    // Set row heighht
	    tbpermisos.setRowHeight(20);
	    tbpermisos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tbpermisos.setPreferredScrollableViewportSize(tbpermisos.getPreferredSize());
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
		
		JPanel panel = new JPanel(new FlowLayout());
	    panel.setPreferredSize(new Dimension(595,50));
	    panel.setBackground(new Color(60,116,107));
	    panel.add(tbpermisos);
	    
	    JPanel panelcolor = new JPanel(new FlowLayout());
		panelcolor.setPreferredSize(new Dimension (625+aumentoancho,150+15));
		panelcolor.setBackground(new Color(68,146,132));
		
		JLabel espacioa = new JLabel(" ");
	    espacioa.setPreferredSize(new Dimension(615+aumentoancho,8));	
	    panelcolor.add(espacioa);
		panelcolor.add(panel);
		JLabel espaciob = new JLabel(" ");
	    espaciob.setPreferredSize(new Dimension(615+aumentoancho,10));	
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