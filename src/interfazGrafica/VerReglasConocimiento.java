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
public class VerReglasConocimiento extends JDialog implements ActionListener
{
	private JTable tbreglas;
	private JButton btaceptar;
	private JButton btcancelar;
	private InterfazInicio inicio;
	
	public VerReglasConocimiento(InterfazInicio interfaz, String desdeentidad, String hastaentidad, List reglas)
	{
		super(interfaz, true);
		setLayout(new FlowLayout());
        setTitle("Ver Reglas entre "+desdeentidad+" y "+hastaentidad+" - SESP");
        setResizable(false);
		setDefaultCloseOperation(2);
		int aumentoancho = 30;
		int anchoventana = 380+aumentoancho+600;
		int altoventana =260+10+200;
		setBounds((interfaz.getWidthvetana()/2)-(anchoventana/2),140,anchoventana,altoventana);
		
		inicio=interfaz;
		int numeroreglas = 0;//numero roles en la BD
		
        if(reglas != null)
        {
        	numeroreglas = reglas.size();
        }
		
		//********************************************
        tbreglas = new JTable(numeroreglas+1,2);
        tbreglas.setValueAt(" ID   -   "+desdeentidad.toUpperCase(), 0, 0);
        tbreglas.setValueAt(" ID   -   "+hastaentidad.toUpperCase(), 0, 1);
		
        tbreglas.setEnabled(false);
		
        if(desdeentidad.equalsIgnoreCase("Pregunta") && hastaentidad.equalsIgnoreCase("Escala"))
        {
        	for (int i = 0; i < numeroreglas; i++)
    		{
        		PreguntaEscala reglaux = (PreguntaEscala)reglas.get(i);
        		Pregunta pregunta = null;
        		Escala escala = null;
        		try
		        {
		            Conector conector = new Conector();
		            conector.iniciarConexionBaseDatos();
		            pregunta = PreguntaBD.buscarIdPregunta(reglaux.getIdpregunta(), conector);
		            conector.terminarConexionBaseDatos();
		        }
		        catch (Exception e)
				{
		        	JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
				}
		        try
		        {
		            Conector conector = new Conector();
		            conector.iniciarConexionBaseDatos();
		            escala = EscalaBD.buscarIdEscala(reglaux.getIdescala(), conector);
		            conector.terminarConexionBaseDatos();
		        }
		        catch (Exception e)
				{
		        	JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
				}
		        if(pregunta != null)
		        {
		        	if(escala != null)
			        {
		        		tbreglas.setValueAt(" "+reglaux.getIdpregunta()+"    -   "+pregunta.getTexto(), i+1, 0);
		    			tbreglas.setValueAt(" "+reglaux.getIdescala()+"    -   "+escala.getNombre(), i+1, 1);
			        }
		        }
    		}
        }
        if(desdeentidad.equalsIgnoreCase("Competencia") && hastaentidad.equalsIgnoreCase("Escala"))
        {
        	for (int i = 0; i < numeroreglas; i++)
    		{
        		CompetenciaEscala reglaux = (CompetenciaEscala)reglas.get(i);
        		Competencia competencia = null;
        		Escala escala = null;
        		try
		        {
		            Conector conector = new Conector();
		            conector.iniciarConexionBaseDatos();
		            competencia = CompetenciaBD.buscarIdCompetencia(reglaux.getIdcompetencia(), conector);
		            conector.terminarConexionBaseDatos();
		        }
		        catch (Exception e)
				{
		        	JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
				}
		        try
		        {
		            Conector conector = new Conector();
		            conector.iniciarConexionBaseDatos();
		            escala = EscalaBD.buscarIdEscala(reglaux.getIdescala(), conector);
		            conector.terminarConexionBaseDatos();
		        }
		        catch (Exception e)
				{
		        	JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
				}
		        if(competencia != null)
		        {
		        	if(escala != null)
			        {
		        		tbreglas.setValueAt(" "+reglaux.getIdcompetencia()+"    -   "+competencia.getNombre() , i+1, 0);
		    			tbreglas.setValueAt(" "+reglaux.getIdescala()+"    -   "+escala.getNombre(), i+1, 1);
			        }
		        }
    		}
        }
        if(desdeentidad.equalsIgnoreCase("Competencia") && hastaentidad.equalsIgnoreCase("Rol"))
        {
        	for (int i = 0; i < numeroreglas; i++)
    		{
        		CompetenciaRol reglaux = (CompetenciaRol)reglas.get(i);
        		Competencia competencia = null;
        		Rol rol = null;
        		try
		        {
		            Conector conector = new Conector();
		            conector.iniciarConexionBaseDatos();
		            competencia = CompetenciaBD.buscarIdCompetencia(reglaux.getIdcompetencia(), conector);
		            conector.terminarConexionBaseDatos();
		        }
		        catch (Exception e)
				{
		        	JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
				}
		        try
		        {
		            Conector conector = new Conector();
		            conector.iniciarConexionBaseDatos();
		            rol = RolBD.buscarIdRol(reglaux.getIdrol(), conector);
		            conector.terminarConexionBaseDatos();
		        }
		        catch (Exception e)
				{
		        	JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
				}
		        if(competencia != null)
		        {
		        	if(rol != null)
			        {
		        		tbreglas.setValueAt(" "+reglaux.getIdcompetencia()+"    -   "+competencia.getNombre(), i+1, 0);
		    			tbreglas.setValueAt(" "+reglaux.getIdrol()+"    -   "+rol.getNombre(), i+1, 1);
			        }
		        }
    		}
        }

	    // Assign the editor to the second column
	    TableColumnModel tcm2 = tbreglas.getColumnModel();
		// Set column widths
	    tcm2.getColumn(0).setPreferredWidth(140+500);
	    tcm2.getColumn(1).setPreferredWidth(140+100);
	    
	    tbreglas.setForeground(Color.BLACK);
	    tbreglas.setGridColor(Color.BLACK);
	    // Set row heighht
	    tbreglas.setRowHeight(20);
	    tbreglas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tbreglas.setPreferredScrollableViewportSize(tbreglas.getPreferredSize());
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
		
		int tbancho = 280;
		int tbalto = (tbreglas.getRowCount()*20);

		JPanel panel = new JPanel(new FlowLayout());
	    panel.setPreferredSize(new Dimension(tbancho+10,tbalto+10));
	    panel.setBackground(new Color(60,116,107));
	  
	    panel.add(tbreglas);
	    
	    JScrollPane barras = new JScrollPane(panel);
	    barras.setPreferredSize(new Dimension(310+600,120+200));
	    
	    JPanel panelcolor = new JPanel(new FlowLayout());
		panelcolor.setPreferredSize(new Dimension (370+aumentoancho+600,220+15+200));
		panelcolor.setBackground(new Color(68,146,132));
		
		JLabel espacioa = new JLabel(" ");
	    espacioa.setPreferredSize(new Dimension(360+aumentoancho+600,10));	
	    panelcolor.add(espacioa);
	    panelcolor.add(barras);
	    JLabel espaciob = new JLabel(" ");
	    espaciob.setPreferredSize(new Dimension(360+aumentoancho+600,10));	
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