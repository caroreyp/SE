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

import mundo.Candidato;
import mundo.Convocatoria;
import mundo.Prueba;
import mundo.PruebaCandidato;
import mundo.Resultado;
import basedeDatos.CandidatoBD;
import basedeDatos.Conector;
import basedeDatos.ConvocatoriaBD;
import basedeDatos.PruebaBD;
import basedeDatos.PruebaCandidatoBD;
import basedeDatos.ResultadoBD;

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
public class VerAnalisisResultados extends JDialog implements ActionListener
{
	private JTable tbconvocatorias;
	private JButton btaceptar;
	private JButton btcancelar;
	private InterfazInicio inicio;
	
	public VerAnalisisResultados(InterfazInicio interfaz, String convocator)
	{
		super(interfaz, true);
		setLayout(new FlowLayout());
        setTitle("Ver Analisis Resultados "+convocator+" - SESP");
        setResizable(false);
		setDefaultCloseOperation(2);
		int aumentoancho = 30;
		int anchoventana = 680+aumentoancho;
		int altoventana =229+200+10;
		setBounds((interfaz.getWidthvetana()/2)-(anchoventana/2),140,anchoventana,altoventana);
		
		inicio=interfaz;

		int idconvocatoriaselec = 0;
	    if(convocator.equalsIgnoreCase("Pruebas Libres"))
	    {
	    	idconvocatoriaselec = -1;
	    }
	    else
	    {
	    	List convocatorias = null;//convocatorias de la BD
			try
	        {
	            Conector conector = new Conector();
	            conector.iniciarConexionBaseDatos();
	            convocatorias = ConvocatoriaBD.listar(conector);
	            conector.terminarConexionBaseDatos();
	        }
	        catch (Exception e)
			{
	        	JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
			}
	        int numeroconvocatorias = 0;//numero de convocatorias en la BD
	        if(convocatorias != null)
	        {
	        	numeroconvocatorias = convocatorias.size();
	        }
	        Convocatoria convoseleccionada = null;
	        for (int i = 0; i < numeroconvocatorias; i++)
		    {
			    Convocatoria convocatoriaaux = (Convocatoria)convocatorias.get(i);
			    int idconvo = convocatoriaaux.getIdconvocatoria();
			    String nombre = convocatoriaaux.getNombre();
			    String convocatoraux = idconvo+" - "+nombre;
			    if(convocatoraux.equalsIgnoreCase(convocator))
			    {
			    	convoseleccionada = convocatoriaaux;
			    	idconvocatoriaselec = convoseleccionada.getIdconvocatoria();
			    	break;
			    }
		    }
	    }
		
        List pruebascandidatos = null;//pruebascandidatos de la BD
		try
		{
			Conector conector = new Conector();
			conector.iniciarConexionBaseDatos();
			pruebascandidatos= PruebaCandidatoBD.listarPuntajeDescendente(idconvocatoriaselec, conector);
			conector.terminarConexionBaseDatos();
		} 
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
		}
		int numeropruebascandidatos = 0;
		if(pruebascandidatos != null)
		{
			numeropruebascandidatos = pruebascandidatos.size();
		}
		tbconvocatorias = new JTable(numeropruebascandidatos+1,4);
		tbconvocatorias.setValueAt("   # Resultado", 0, 0);
		tbconvocatorias.setValueAt("                      Prueba", 0, 1);
		tbconvocatorias.setValueAt("                               Candidato", 0, 2);
		tbconvocatorias.setValueAt("     Puntaje", 0, 3);
		tbconvocatorias.setEnabled(false);
		int fila = 1;
		for (int i = 0; i < numeropruebascandidatos; i++)
		{
			PruebaCandidato pruebaca = (PruebaCandidato)pruebascandidatos.get(i);
			int idconvocator = pruebaca.getIdconvocator();
			if(idconvocator == idconvocatoriaselec)
			{
				int idprueb = pruebaca.getIdprueba();
				int idcandid = pruebaca.getIdcandidato();
				int idresult = pruebaca.getIdresultado();
				Prueba prueb = null;
				Candidato candid = null;	
				Resultado result = null;
				try
				{
					Conector conector = new Conector();
					conector.iniciarConexionBaseDatos();
					result = ResultadoBD.buscarIdResultado(idresult, conector);
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
					prueb = PruebaBD.buscarIdPrueba(idprueb, conector);
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
					candid = CandidatoBD.buscarIdCandidato(idcandid, conector);
					conector.terminarConexionBaseDatos();
				} 
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(this,"Error al conectar con la Base de Datos.","Error", JOptionPane.ERROR_MESSAGE,new ImageIcon("./images/IconosInterfaz/error.PNG"));
				}
				if(result != null)
				{
					if(prueb != null)
					{
						if(candid != null)
						{
							String nombreprueba = prueb.getNombre();
							String nombrecandidato = candid.getNombres()+" "+candid.getApellidos();
							tbconvocatorias.setValueAt(result.getIdresultado(), fila, 0);
							tbconvocatorias.setValueAt(nombreprueba, fila, 1);
							tbconvocatorias.setValueAt(nombrecandidato, fila, 2);
							tbconvocatorias.setValueAt(result.getPuntaje(), fila, 3);
							fila++;
						}
						
					}
				}
			}
		}
		//********************************************

	    //DefaultCellEditor editor2 = new DefaultCellEditor(caja);
	    // Assign the editor to the second column
	    TableColumnModel tcm2 = tbconvocatorias.getColumnModel();
	    //tcm2.getColumn(1).setCellEditor(editor2);
		// Set column widths
	    tcm2.getColumn(0).setPreferredWidth(90);
	    tcm2.getColumn(1).setPreferredWidth(180);
	    tcm2.getColumn(2).setPreferredWidth(250);
	    tcm2.getColumn(3).setPreferredWidth(80);
	    
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
	    barras.setPreferredSize(new Dimension(630,90+200));
	    
	    JPanel panelcolor = new JPanel(new FlowLayout());
		panelcolor.setPreferredSize(new Dimension (670+aumentoancho,190+200+15));
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